package POMA.Verification.BMC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class ObligationTranslator {

	//String pathToObligations = "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	 String pathToObligations =
	 "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml";

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml";

	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEventLabels = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();;
	private List<String> listOfCreatedNodesUA = new ArrayList<String>();
	private List<String> listOfCreatedNodesOA = new ArrayList<String>();
	private List<String> ruleLabels = new ArrayList<String>();
	private String actionsTranslation;
	private List<Action> grantGroupActions = new ArrayList<Action>();
	private List<Action> assignGroupActions = new ArrayList<Action>();

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

	private Set<Rule> getRelevantObligationsAlgorithm(Rule targetObligation, int levelOfRelevancy) {
		Set<Rule> relevantObligations = new HashSet<Rule>();
		for (Rule r : obligation.getRules()) {
			if (r.equals(targetObligation)) {
				return null;
			}
			EventPattern ep = targetObligation.getEventPattern();
			ResponsePattern rp = r.getResponsePattern();
			for (int i = 0; i < levelOfRelevancy; i++) {
				if (affects(rp, ep)) {
					Set<Rule> sr = getRelevantObligationsAlgorithm(targetObligation, i + 1);
					if (sr != null) {
						relevantObligations.addAll(sr);
					}
				}
			}
		}
		return relevantObligations;
	}

	private boolean affects(ResponsePattern rp, EventPattern ep) {
		String eventSubject = ep.getSubject().getAnyUser().get(0);
		String eventOperation = ep.getOperations().get(0);
		String eventTarget = ep.getTarget().getPolicyElements().get(0).getName();
		for (Action action : rp.getActions()) {
			if (action instanceof GrantAction) {
				GrantAction association = (GrantAction) action;
				String what = association.getSubject().getName().toString();
				String where = association.getTarget().getName().toString();
				String op = association.getOperations().get(0);

				if (what.equals(eventSubject) || where.equals(eventTarget) || eventOperation.equals(op)) {
					return true;
				}
			} else if (action instanceof CreateAction) {
				CreateAction createAction = (CreateAction) action;
				String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
				if (what.equals(eventSubject) || what.equals(eventTarget)) {
					return true;
				}
			} else if (action instanceof AssignAction) {
				AssignAction assignAction = (AssignAction) action;
				String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
				String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
				if (what.equals(eventSubject) || where.equals(eventTarget) || what.equals(eventTarget)
						|| where.equals(eventSubject)) {
					return true;
				}
			}
		}
		return false;
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

	private void groupActions(List<Action> actions) {
		grantGroupActions = new ArrayList<Action>();
		assignGroupActions = new ArrayList<Action>();
		for (Action action : actions) {
			if (action instanceof GrantAction) {
				grantGroupActions.add((GrantAction) action);
			} else if (action instanceof AssignAction) {
				assignGroupActions.add((AssignAction) action);
			} else if (action instanceof DeleteAction) {
				DeleteAction deleteAction = (DeleteAction) action;
				if (deleteAction.getAssignments()!=null && deleteAction.getAssignments().getAssignments().size() != 0) {
					assignGroupActions.add(deleteAction);
				} else if (deleteAction.getAssociations().size() != 0) {
					grantGroupActions.add(deleteAction);
				}
			}
		}
	}

	private String processAssignmentRelatedActions(int k) {
		StringBuilder sb_assignments = new StringBuilder();
		AssignAction assignAction;
		DeleteAction deleteAction;
		CreateAction createAction;

		String what = "";
		String where = "";
		String SMTAction = "";
		for (int i = 0; i < assignGroupActions.size(); i++) {
			Action action = assignGroupActions.get(i);
			if (action instanceof AssignAction) {
				assignAction = (AssignAction) action;
				what = assignAction.getAssignments().get(0).getWhat().getName();
				where = assignAction.getAssignments().get(0).getWhere().getName();
				SMTAction = "union";
			} else if (action instanceof DeleteAction) {// TODO: add multiple assignments
				deleteAction = (DeleteAction) action;
				what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
				where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
				SMTAction = "setminus";
			} else if (action instanceof CreateAction) {// TODO: add multiple nodes
				createAction = (CreateAction) action;
				what = createAction.getCreateNodesList().get(0).getWhat().getName();
				where = createAction.getCreateNodesList().get(0).getWhere().getName();
				SMTAction = "union";
			}
			int whatID = mapOfIDs.get(what);
			int whereID = mapOfIDs.get(where);
			if (i == 0) {
				sb_assignments.append(
						"(" + SMTAction + "  OldGRAPH" + k + " (singleton(mkTuple " + whatID + " " + whereID + ")))");
			} else {
				sb_assignments.insert(0, "(" + SMTAction + " ");
				sb_assignments.append(" (singleton(mkTuple " + whatID + " " + whereID + ")))");
			}
		}
		sb_assignments.append(")");
		sb_assignments.append(System.lineSeparator());
		sb_assignments.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
		sb_assignments.append(System.lineSeparator());
		return sb_assignments.toString();
	}

	private String processAssociationRelatedActions(int k) {
		StringBuilder sb_associations = new StringBuilder();
		GrantAction grantAction;
		DeleteAction deleteAction;
		String what = "";
		String where = "";
		String op = "";
		String SMTAction = "";

		for (int i = 0; i < grantGroupActions.size(); i++) {
			Action action = grantGroupActions.get(i);
			if (action instanceof GrantAction) {// TODO: add multiple ars
				grantAction = (GrantAction) action;
				what = grantAction.getSubject().getName();
				where = grantAction.getTarget().getName();
				op = grantAction.getOperations().get(0);
				SMTAction = "union";
			} else if (action instanceof DeleteAction) {// TODO: add multiple associations
				deleteAction = (DeleteAction) action;
				what = deleteAction.getAssociations().get(0).getSubject().getName();
				where = deleteAction.getAssociations().get(0).getTarget().getName();
				SMTAction = "setminus";
			}
			int whatID = mapOfIDs.get(what);
			int whereID = mapOfIDs.get(where);
			int arID = mapOfIDs.get(op);

			if (i == 0) {
				sb_associations.append("(" + SMTAction + "  (Associations " + (k-1) + ") (singleton(mkTuple " + whatID + " "
						+ arID + " " + whereID + ")))");
			} else {
				sb_associations.insert(0, "(" + SMTAction + " ");
				sb_associations.append(" (singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")))");
			}
		}
		sb_associations.append(")");
		sb_associations.append(System.lineSeparator());
		sb_associations.append("(= (Associations " + k + ") (Associations " + (k - 1) + "))))");
		sb_associations.append(System.lineSeparator());
		return sb_associations.toString();
	}

	String processActionsRefactoring(int k) {
		StringBuilder sb_assignments = new StringBuilder();
		StringBuilder sb_associations = new StringBuilder();
		sb_assignments.append("(assert (or ");
		sb_assignments.append(System.lineSeparator());
		sb_associations.append("(assert (or ");
		sb_associations.append(System.lineSeparator());
		for (Rule rule : obligation.getRules()) {
			this.groupActions(rule.getResponsePattern().getActions());
			if (grantGroupActions.size() > 0) {
				sb_associations.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb_associations.append(System.lineSeparator());
				sb_associations.append("(xor (= (Associations " + k + ") ");
				sb_associations.append(System.lineSeparator());
				sb_associations.append(this.processAssociationRelatedActions(k));
				sb_associations.append(System.lineSeparator());
			}
			if (assignGroupActions.size() > 0) {
				sb_assignments.append("(and (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb_assignments.append(System.lineSeparator());
				sb_assignments.append("(xor (= GRAPH" + k + " ");
				sb_assignments.append(System.lineSeparator());
				sb_assignments.append(this.processAssignmentRelatedActions(k));
				sb_assignments.append(System.lineSeparator());
			}
		}
		sb_assignments.append("(= GRAPH" + k + " OldGRAPH" + k + ")))");
		sb_associations.append("(= (Associations " + k + ") (Associations " + (k - 1) + "))))");
		return sb_assignments.toString() + System.lineSeparator() + System.lineSeparator() + sb_associations.toString();
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
