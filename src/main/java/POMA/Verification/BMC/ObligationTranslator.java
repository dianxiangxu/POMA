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

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	String pathToObligations = "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml";

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

	public String processObligations() throws EVRException, IOException {
		// obligation = readObligations();

		return null;
	}

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

	String processActionsImproving(int k) {
		boolean grantExists = false;
		StringBuilder sb = new StringBuilder();
		HashMap<String, GrantAction> grantActions = new HashMap<String, GrantAction>();
		HashMap<String, DeleteAction> deleteGrantActions = new HashMap<String, DeleteAction>();

		sb.append("(assert (or ");
		sb.append(System.lineSeparator());
		for (Rule rule : obligation.getRules()) {

			List<Action> actions = rule.getResponsePattern().getActions();
			if (actions.size() == 1
					|| (actions.get(0) instanceof GrantAction && actions.get(1) instanceof DeleteAction)) {
				Action action = actions.get(0);
				Action action2 = actions.get(1);

				if (action instanceof GrantAction) {
					grantActions.put(rule.getLabel(), (GrantAction) action);
					grantExists = true;
				}
				if (action2 instanceof DeleteAction && ((DeleteAction) action2).getAssociations().size() > 0) {
					DeleteAction deleteAction = (DeleteAction) action2;
					deleteGrantActions.put(rule.getLabel(), deleteAction);
					grantExists = true;
					continue;
				}
				sb.append(System.lineSeparator());
				sb.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb.append(System.lineSeparator());
				sb.append("(xor (= GRAPH" + k + " ");
				sb.append(System.lineSeparator());
				sb.append(processSingleAction(action, k, rule.getLabel()) + ")" + System.lineSeparator());
				sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
				sb.append(System.lineSeparator());
				continue;
			} else {
				String firstAction = "";
				boolean create_or_assign_processed = false;

				for (Action action : actions) {
					if (action instanceof GrantAction) {
						grantActions.put(rule.getLabel(), (GrantAction) action);
						grantExists = true;
						continue;
					}
					if (action instanceof DeleteAction && ((DeleteAction) action).getAssociations().size() > 0) {
						DeleteAction deleteAction = (DeleteAction) action;
						deleteGrantActions.put(rule.getLabel(), deleteAction);
						grantExists = true;
						continue;
					}
					if (!create_or_assign_processed && action instanceof AssignAction) {
						sb.append(System.lineSeparator());
						sb.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
						sb.append(System.lineSeparator());
						sb.append("(xor (= GRAPH" + k + " ");
						sb.append(System.lineSeparator());
						create_or_assign_processed = true;
					}
					if (action instanceof AssignAction) {

						firstAction = processSingleAction(action, k, rule.getLabel());

					} else if (action instanceof DeleteAction && ((DeleteAction) action).getAssignments() != null) {
						DeleteAction deleteAction = (DeleteAction) action;

						String what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
						String where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
						int whatID = mapOfIDs.get(what);
						int whereID = mapOfIDs.get(where);
						sb.append(
								" (setminus " + firstAction + "(singleton(mkTuple " + whatID + " " + whereID + "))))");
						firstAction = "";
						break;

					} else if (action instanceof CreateAction) {
						firstAction = processSingleAction(action, k, rule.getLabel());
					}

				}
				if (!firstAction.equals("")) {
					sb.append(System.lineSeparator());
					sb.append(firstAction);
				}

				sb.append(System.lineSeparator());
				sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
				sb.append(System.lineSeparator());

			}
		}
		sb.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
		sb.append(System.lineSeparator());
		if (!grantExists) {
			sb.append("(assert (= (Associations " + k + ") (Associations " + (k - 1) + ")))");
		} else {
			if (grantActions.size() > 1) {
				sb.append(System.lineSeparator());
				sb.append(processMultipleGrants(grantActions, deleteGrantActions, k));
			} else {
				for (HashMap.Entry<String, GrantAction> entry : grantActions.entrySet()) {
					String label = entry.getKey();
					GrantAction action = entry.getValue();
					sb.append(System.lineSeparator());
					sb.append(processSingleAction(action, k, label));
				}
			}
			// if (deleteGrantActions.size() > 1) {
			// sb.append(System.lineSeparator());
			// // sb.append(processMultipleDeleteGrants(deleteGrantActions, k));
			// }
		}
		return sb.toString();
	}

	String processMultipleGrants(HashMap<String, GrantAction> grantActions,
			HashMap<String, DeleteAction> deleteGrantActions, int k) {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert ( or");
		for (HashMap.Entry<String, GrantAction> entry : grantActions.entrySet()) {
			String label = entry.getKey();
			GrantAction grantAction = (GrantAction) entry.getValue();
			DeleteAction deleteAction = deleteGrantActions.get(label);
			String what = grantAction.getSubject().getName().toString();
			String where = grantAction.getTarget().getName().toString();
			String op = grantAction.getOperations().get(0);
			int whatID = mapOfIDs.get(what);
			int arID = mapOfIDs.get(op);
			int whereID = mapOfIDs.get(where);

			String what2 = deleteAction.getAssociations().get(0).getSubject().getName().toString();
			String where2 = deleteAction.getAssociations().get(0).getTarget().getName().toString();
			String op2 = deleteAction.getAssociations().get(0).getOperations().get(0);
			int whatID2 = mapOfIDs.get(what2);
			int arID2 = mapOfIDs.get(op2);
			int whereID2 = mapOfIDs.get(where2);
			sb.append(System.lineSeparator());
			sb.append("(and (= (" + label + " " + (k - 1) + ") 1) (xor (= (Associations " + k
					+ ") (setminus (union (singleton(mkTuple " + whatID + " " + arID + " " + whereID
					+ ")) (Associations " + (k - 1) + "))(singleton(mkTuple " + whatID2 + " " + arID2 + " " + whereID2
					+ ")))) (= (Associations " + k + ") (Associations " + (k - 1) + "))))");
		}
		sb.append("(= (Associations " + k + ") (Associations " + (k - 1) + "))))");

		return sb.toString();
	}

	String processMultipleDeleteGrants(HashMap<String, DeleteAction> deleteGrantActions, int k) {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert ( or");
		for (HashMap.Entry<String, DeleteAction> entry : deleteGrantActions.entrySet()) {
			String label = entry.getKey();
			DeleteAction grantAction = (DeleteAction) entry.getValue();
			String what = grantAction.getAssociations().get(0).getSubject().getName().toString();
			String where = grantAction.getAssociations().get(0).getTarget().getName().toString();
			String op = grantAction.getAssociations().get(0).getOperations().get(0);
			int whatID = mapOfIDs.get(what);
			int arID = mapOfIDs.get(op);
			int whereID = mapOfIDs.get(where);
			sb.append(System.lineSeparator());
			sb.append("(and (= (" + label + " " + (k - 1) + ") 1) (xor (= (Associations " + k
					+ ") (setminus (singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")) (Associations "
					+ (k - 1) + "))) (= (Associations " + k + ") (Associations " + (k - 1) + "))))");
		}
		sb.append("(= (Associations " + k + ") (Associations " + (k - 1) + "))))");

		return sb.toString();
	}

	String processSingleAction(Action action, int k, String label) {
		if (action instanceof AssignAction) {
			AssignAction assignAction = (AssignAction) action;
			String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
			String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
			int whatID = mapOfIDs.get(what);
			int whereID = mapOfIDs.get(where);

			return System.lineSeparator() + "(union (singleton(mkTuple " + whatID + " " + whereID + ")) OldGRAPH" + k
					+ ")";

		} else if (action instanceof GrantAction) {
			GrantAction grantAction = (GrantAction) action;
			String what = grantAction.getSubject().getName().toString();
			String where = grantAction.getTarget().getName().toString();
			String op = grantAction.getOperations().get(0);
			int whatID = mapOfIDs.get(what);
			int arID = mapOfIDs.get(op);
			int whereID = mapOfIDs.get(where);

			return "(assert (xor (and (= (" + label + " " + (k - 1) + ") 1) (xor (= (Associations " + k
					+ ") (union (singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")) (Associations "
					+ (k - 1) + "))) (= (Associations " + k + ") (Associations " + (k - 1) + "))))(= (Associations " + k
					+ ") (Associations " + (k - 1) + "))))";

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
