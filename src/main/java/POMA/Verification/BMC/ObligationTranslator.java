package POMA.Verification.BMC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class ObligationTranslator {

	String pathToObligations = "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml";

	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEventLabels = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();;
	private List<String> listOfCreatedNodesUA = new ArrayList<String>();
	private List<String> listOfCreatedNodesOA = new ArrayList<String>();
	private List<String> ruleLabels = new ArrayList<String>();
	private String actionsTranslation;

	Obligation obligation;
	HashMap<String, Integer> mapOfIDs;

	public ObligationTranslator(HashMap<String, Integer> mapOfIDs) {
		this.mapOfIDs = mapOfIDs;
		try {
			obligation = readObligations();
		} catch (EVRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		translateObligationRules();
	}

	public List<String> getProcessedObligations() {
		return processedObligations;
	}

	public List<String> getProcessedObligationsEventLabels() {
		return processedObligationsEventLabels;
	}

	public static void main(String[] args) throws Exception {
		// ObligationTranslator ot = new ObligationTranslator();
		// ot.processObligations();
	}

	public String processObligations() throws EVRException, IOException {
		// obligation = readObligations();

		return null;
	}

	/*
	 * "(assert \r\n" + "(xor \r\n" + "(= (obligation1 "+(k-1)+") 0) \r\n" +
	 * "(and (member (mkTuple "+AttorneysID+" "+acceptID+" "
	 * +Case3InfoID+") (AccessRights "+(k-1)+")) (= (obligation1 "+(k-1)+") 1))\r\n"
	 * + ")\r\n" + ")				\r\n"
	 */

	String translateObligationEvents(int k) {
		StringBuilder sb = new StringBuilder();
		for (Rule r : obligation.getRules()) {
			String subject = r.getEventPattern().getSubject().getAnyUser().get(0);
			String ar = r.getEventPattern().getOperations().get(0);
			String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
			String obligationLabel = r.getLabel();
			int subjectID = mapOfIDs.get(subject);
			int arID = mapOfIDs.get(ar);
			int targetID = mapOfIDs.get(target);

			sb.append("(assert \r\n" + "(xor \r\n" + "(= (" + obligationLabel + " " + (k - 1) + ") 0) \r\n"
					+ "(and (member (mkTuple " + subjectID + " " + arID + " " + targetID + ") (AccessRights " + (k - 1)
					+ ")) (= (" + obligationLabel + " " + (k - 1) + ") 1))\r\n" + ")\r\n" + ")				\r\n");
			ruleLabels.add(r.getLabel());
		}
		return sb.toString();
	}

	String translateGraphIntersection(int k) {
		return "(declare-fun GRAPH" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
				+ "(declare-fun OldGRAPH" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
				+ "(assert (= OldGRAPH" + k + " (intersection (Tclosure " + (k - 1) + ") GRAPH" + (k - 1) + ")))"
				+ System.lineSeparator();
	}

	private void translateObligationRules() {
		processedObligations.add("");
		processedObligationsEventLabels.add("");

		for (Rule r : obligation.getRules()) {
			String subject = r.getEventPattern().getSubject().getAnyUser().get(0);
			String ar = r.getEventPattern().getOperations().get(0);
			String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
			System.out.println(subject + " : " + ar + " : " + target);
			ruleLabels.add(r.getLabel());
			processedObligationsEventLabels.addAll(getEvents(r));
		}
	}

	private Obligation readObligations() throws EVRException, IOException {

		String yml = new String(Files.readAllBytes(Paths.get(pathToObligations)));
		Obligation obligation = EVRParser.parse(yml);
		return obligation;
	}

	private List<String> getEvents(Rule rule) {
		return rule.getEventPattern().getOperations();
	}

	void findAllAbsentElements() {
		for (Rule rule : obligation.getRules()) {
			List<Action> actions = rule.getResponsePattern().getActions();
			for (Action action : actions) {
				if (action instanceof GrantAction) {
					GrantAction association = (GrantAction) action;
					String what = association.getSubject().getName().toString();
					String where = association.getTarget().getName().toString();
					listOfAddedAssociations.add(new AssociationRelation(what,
							new OperationSet((Collection<String>) association.getOperations()), where));
				} else if (action instanceof CreateAction) {
					CreateAction createAction = (CreateAction) action;
					String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
					String type = createAction.getCreateNodesList().get(0).getWhat().getType().toString();
					if (type.equals("U") || type.equals("UA")) {
						listOfCreatedNodesUA.add(what);
					}
					if (type.equals("O") || type.equals("OA")) {
						listOfCreatedNodesOA.add(what);
					}
				}
			}
		}
	}

	String processActions(int k) {
		boolean grantExists = false;
		String grantString = "";
		StringBuilder sb = new StringBuilder();

		sb.append("(assert (xor ");
		sb.append(System.lineSeparator());
		for (Rule rule : obligation.getRules()) {

			List<CreateAction> createActions = new ArrayList<CreateAction>();
			List<Action> actions = rule.getResponsePattern().getActions();
			boolean flag = false;
			if(actions.size()>1)
			 flag = (actions.get(1) instanceof GrantAction);
			if (actions.size() == 1 || flag) {
				Action action = actions.get(0);
				sb.append(System.lineSeparator());
				sb.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb.append(System.lineSeparator());
				sb.append("(xor (= GRAPH" + k + " ");
				sb.append(System.lineSeparator());
				if(!flag){
					sb.append(processSingleAction(action, k) + ")" + System.lineSeparator());
				}
				else{
				sb.append(processSingleAction(action, k) + System.lineSeparator());
				GrantAction grantAction = (GrantAction) actions.get(1);
				String what = grantAction.getSubject().getName().toString();
				String where = grantAction.getTarget().getName().toString();
				String op = grantAction.getOperations().get(0);
				int whatID = mapOfIDs.get(what);
				int arID = mapOfIDs.get(op);
				int whereID = mapOfIDs.get(where);

				grantString = "(assert (xor (and (= (" + rule.getLabel() + " " + (k - 1) + ") 1) (xor (= (Associations "
						+ k + ") (union (singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")) (Associations "
						+ (k - 1) + "))) (= (Associations " + k + ") (Associations " + (k - 1) + "))))(= (Associations "
						+ k + ") (Associations " + (k - 1) + "))))";
				grantExists = true;
				}
				sb.append(System.lineSeparator());
				sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
				sb.append(System.lineSeparator());
				continue;
			}
			String firstAction = "";
			sb.append(System.lineSeparator());
			sb.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
			sb.append(System.lineSeparator());
			sb.append("(xor (= GRAPH" + k + " ");
			sb.append(System.lineSeparator());
			for (Action action : actions) {

				if (action instanceof AssignAction) {
					
					firstAction = processSingleAction(action, k);
					
				}
				else if (action instanceof DeleteAction) {
					DeleteAction deleteAction = (DeleteAction) action;
					String what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
					String where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
					int whatID = mapOfIDs.get(what);
					int whereID = mapOfIDs.get(where);
					sb.append(" (setminus "+ firstAction + "(singleton(mkTuple "+ whatID + " " + whereID + "))))");
				} else if (action instanceof GrantAction) {
					GrantAction grantAction = (GrantAction) action;
					String what = grantAction.getSubject().getName().toString();
					String where = grantAction.getTarget().getName().toString();
					String op = grantAction.getOperations().get(0);
					int whatID = mapOfIDs.get(what);
					int arID = mapOfIDs.get(op);
					int whereID = mapOfIDs.get(where);

					grantString = "(assert (xor (and (= (" + rule.getLabel() + " " + (k - 1)
							+ ") 1) (xor (= (Associations " + k + ") (union (singleton(mkTuple " + whatID + " " + arID
							+ " " + whereID + ")) (Associations " + (k - 1) + "))) (= (Associations " + k
							+ ") (Associations " + (k - 1) + "))))(= (Associations " + k + ") (Associations " + (k - 1)
							+ "))))";
					grantExists = true;
				} else if (action instanceof CreateAction) {
					createActions.add((CreateAction) action);
				}
				
			}
			sb.append(System.lineSeparator());
			sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
			sb.append(System.lineSeparator());
		}

		// System.out.println(processGrantAction(grantActions));
		sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
		sb.append(System.lineSeparator());
		if (!grantExists) {
			sb.append("(assert (= (Associations " + k + ") (Associations " + (k - 1) + ")))");
		} else {
			sb.append(grantString);
		}
		return sb.toString();
	}

	String processSingleAction(Action action, int k) {
		if (action instanceof AssignAction) {
			AssignAction assignAction = (AssignAction) action;
			String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
			String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
			int whatID = mapOfIDs.get(what);
			int whereID = mapOfIDs.get(where);

			return System.lineSeparator() + "(union (singleton(mkTuple " + whatID + " " + whereID + ")) OldGRAPH" + k
					+ ")" ;

		} else if (action instanceof GrantAction) {

		} else if (action instanceof CreateAction) {
			CreateAction createAction = (CreateAction) action;
			String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
			String type = createAction.getCreateNodesList().get(0).getWhat().getType().toString();
			String where = createAction.getCreateNodesList().get(0).getWhere().getName().toString();

			int whatID = mapOfIDs.get(what);
			int whereID = mapOfIDs.get(where);

			if (type.equals("U") || type.equals("UA")) {
				listOfCreatedNodesUA.add(what);
			}
			if (type.equals("O") || type.equals("OA")) {
				listOfCreatedNodesOA.add(what);
			}
			return System.lineSeparator() + "(union (singleton(mkTuple " + whatID + " " + whereID + ")) OldGRAPH" + k
					+ "))" + System.lineSeparator();

			// createActions.add((CreateAction) action);
		}
		return null;
	}

	private String processAssignAction(List<AssignAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getAssignments().get(0).getWhat().getName().toString();
			String where = actions.get(0).getAssignments().get(0).getWhere().getName().toString();
			return "(singleton(mkTuple \"" + what + "\" \"" + where + "\"))";
		}
		ListIterator<AssignAction> iterator = actions.listIterator();
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		sb.append("(insert ");
		while (iterator.hasNext()) {
			Assignment assignment = iterator.next().getAssignments().get(0);
			String what = assignment.getWhat().getName().toString();
			String where = assignment.getWhere().getName().toString();
			if (!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \"" + what + "\" \"" + where + "\"))))");
				break;
			}
			sb.append("(mkTuple \"" + what + "\" \"" + where + "\")");

		}
		return sb.toString();
	}

	private String processGrantAction(List<GrantAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getSubject().getName().toString();
			String where = actions.get(0).getTarget().getName().toString();
			String op = actions.get(0).getOperations().get(0);
			listOfAddedAssociations.add(new AssociationRelation(what,
					new OperationSet((Collection<String>) actions.get(0).getOperations()), where));
			return "(singleton(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\")))";
		}
		ListIterator<GrantAction> iterator = actions.listIterator();
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		while (iterator.hasNext()) {
			GrantAction association = iterator.next();
			String what = association.getSubject().getName().toString();
			String where = association.getTarget().getName().toString();
			String op = association.getOperations().get(0);
			listOfAddedAssociations.add(new AssociationRelation(what,
					new OperationSet((Collection<String>) association.getOperations()), where));
			if (!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\")))");
				break;
			}
			sb.append("(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\") ");

		}
		return sb.toString();
	}

	private String processCreateAction(List<CreateAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getCreateNodesList().get(0).getWhat().getName().toString();
			String type = actions.get(0).getCreateNodesList().get(0).getWhat().getType().toString();
			if (type.equals("U") || type.equals("UA")) {
				// listOfCreatedNodesUA.add(what);
			}
			if (type.equals("O") || type.equals("OA")) {
				// listOfCreatedNodesOA.add(what);
			}
			String where = actions.get(0).getCreateNodesList().get(0).getWhere().toString();
		}
		return "";
	}

	List<AssociationRelation> getListOfAddedAssociations() {
		return listOfAddedAssociations;
	}

	List<String> getListOfCreatedNodesUA_U() {
		return listOfCreatedNodesUA;
	}

	List<String> getListOfCreatedNodesOA_O() {
		return listOfCreatedNodesOA;
	}

	List<String> getRuleLabels() {
		return ruleLabels;
	}

	String getActionsTranslation() {
		return actionsTranslation;
	}
}
