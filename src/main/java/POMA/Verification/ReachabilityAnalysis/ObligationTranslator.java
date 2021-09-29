package POMA.Verification.ReachabilityAnalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;

public class ObligationTranslator {

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	//  String pathToObligations =
	//  "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml";
	//String pathToObligations = "Policies/ForBMC/GPMSSimplified/Obligations_conditions.yml";

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple1.yml";
	 String pathToObligations =
	 "Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml";
	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEventLabels = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();;
	private List<String> listOfCreatedNodesUA = new ArrayList<String>();
	private List<String> listOfCreatedNodesOA = new ArrayList<String>();
	private List<String> ruleLabels = new ArrayList<String>();
	private String actionsTranslation;
	private List<Action> grantGroupActions = new ArrayList<Action>();
	private List<Action> assignGroupActions = new ArrayList<Action>();
	private Map<String, String> eventMembers = new HashMap<String, String>();

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

	// private boolean affects(ResponsePattern rp, EventPattern ep) {
	// String eventSubject = ep.getSubject().getAnyUser().get(0);
	// String eventOperation = ep.getOperations().get(0);
	// String eventTarget = ep.getTarget().getPolicyElements().get(0).getName();
	// for (Action action : rp.getActions()) {
	// if (action instanceof GrantAction) {
	// GrantAction association = (GrantAction) action;
	// String what = association.getSubject().getName().toString();
	// String where = association.getTarget().getName().toString();
	// String op = association.getOperations().get(0);

	// if (what.equals(eventSubject) || where.equals(eventTarget) ||
	// eventOperation.equals(op)) {
	// return true;
	// }
	// } else if (action instanceof CreateAction) {
	// CreateAction createAction = (CreateAction) action;
	// String what =
	// createAction.getCreateNodesList().get(0).getWhat().getName().toString();
	// if (what.equals(eventSubject) || what.equals(eventTarget)) {
	// return true;
	// }
	// } else if (action instanceof AssignAction) {
	// AssignAction assignAction = (AssignAction) action;
	// String what =
	// assignAction.getAssignments().get(0).getWhat().getName().toString();
	// String where =
	// assignAction.getAssignments().get(0).getWhere().getName().toString();
	// if (what.equals(eventSubject) || where.equals(eventTarget) ||
	// what.equals(eventTarget)
	// || where.equals(eventSubject)) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }

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

	// Translate events + condition for the whole obligation
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
			String condition;
			condition = processEventCondition(r, k);
			sb.append("(assert \r\n" + "(xor \r\n" + "(= (" + obligationLabel + " " + (k - 1) + ") 0) \r\n"
					+ "(and (member (mkTuple " + subjectID + " " + arID + " " + targetID + ") (ASSOC* " + (k - 1)
					+ "))  " + condition + " (= (" + obligationLabel + " " + (k - 1) + ") 1))\r\n" + ")\r\n"
					+ ")				\r\n");
			ruleLabels.add(r.getLabel());
		}
		return sb.toString();
	}

	String processEventCondition(Rule r, int k) {
		if (r.getResponsePattern().getCondition() == null)
			return "";
		List<Function> conditions = r.getResponsePattern().getCondition().getCondition();
		List<Arg> args = conditions.get(0).getArgs();
		String ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
		String descendant = args.get(1).getFunction().getArgs().get(0).getValue();
		int ancestorId = mapOfIDs.get(ancestor);
		int descendantId = mapOfIDs.get(descendant);
		return "(member (mkTuple " + ancestorId + " " + descendantId + ") (ASSIGN* " + (k - 1) + "))";

	}

	String translateGraphIntersection(int k) {
		return "(declare-fun ASSIGN*" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
				+ "(declare-fun OldASSIGN" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
				+ "(assert (= OldASSIGN" + k + " (intersection (Tclosure " + (k - 1) + ") ASSIGN*" + (k - 1) + ")))"
				+ System.lineSeparator();
	}

	private void translateObligationRules() {
		processedObligations.add("");
		processedObligationsEventLabels.add("");
		for (Rule r : obligation.getRules()) {
			String subject = r.getEventPattern().getSubject().getAnyUser().get(0); // TODO: Add multiple users
			String ar = r.getEventPattern().getOperations().get(0);
			String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
			System.out.println(subject + " : " + ar + " : " + target);
			ruleLabels.add(r.getLabel());
			eventMembers.put(subject, target);
			processedObligationsEventLabels.addAll(getEvents(r));
		}
	}

	// private Set<Rule> getRelevantObligationsAlgorithm(Rule targetObligation, int
	// levelOfRelevancy) {
	// Set<Rule> relevantObligations = new HashSet<Rule>();
	// for (Rule r : obligation.getRules()) {
	// if (r.equals(targetObligation)) {
	// return null;
	// }
	// EventPattern ep = targetObligation.getEventPattern();
	// ResponsePattern rp = r.getResponsePattern();
	// for (int i = 0; i < levelOfRelevancy; i++) {
	// if (affects(rp, ep)) {
	// Set<Rule> sr = getRelevantObligationsAlgorithm(targetObligation, i + 1);
	// if (sr != null) {
	// relevantObligations.addAll(sr);
	// }
	// }
	// }
	// }
	// return relevantObligations;
	// }

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
				if ((deleteAction.getAssignments() != null
						&& deleteAction.getAssignments().getAssignments().size() != 0)
						|| (deleteAction.getNodes() != null && deleteAction.getNodes().size() != 0)) {
					assignGroupActions.add(deleteAction);
				} else if (deleteAction.getAssociations() != null && deleteAction.getAssociations().size() != 0) {
					grantGroupActions.add(deleteAction);
				}
			}
		}
	}

	private String processAssignmentRelatedActions(int k, boolean isFlatten) {
		StringBuilder sb_assignments = new StringBuilder();
		StringBuilder sb_assignments_not_flattened = new StringBuilder();

		AssignAction assignAction;
		DeleteAction deleteAction;
		CreateAction createAction;

		String what = "";
		String where = "";
		String SMTAction = "";
		boolean deleteNode = false;
		for (int i = 0; i < assignGroupActions.size(); i++) {
			Action action = assignGroupActions.get(i);

			if (action instanceof AssignAction) {
				assignAction = (AssignAction) action;
				if ((assignAction.getAssignments().get(0).getWhat().getType().equals("U")
						&& assignAction.getAssignments().get(0).getWhere().getType().equals("UA"))
						|| (assignAction.getAssignments().get(0).getWhat().getType().equals("O")
								&& assignAction.getAssignments().get(0).getWhere().getType().equals("OA"))) {
					what = assignAction.getAssignments().get(0).getWhat().getName();
					where = assignAction.getAssignments().get(0).getWhere().getName();
					if (isFlatten)
						handleAddAssignmentActionUUA(k, sb_assignments, what, where);
					else
						handleAddAssignmentNoFlattenAction(k, sb_assignments_not_flattened, what, where);
				} else if ((assignAction.getAssignments().get(0).getWhat().getType().equals("UA")
						&& assignAction.getAssignments().get(0).getWhere().getType().equals("UA"))
						|| (assignAction.getAssignments().get(0).getWhat().getType().equals("OA")
								&& assignAction.getAssignments().get(0).getWhere().getType().equals("OA"))) {
					what = assignAction.getAssignments().get(0).getWhat().getName();
					where = assignAction.getAssignments().get(0).getWhere().getName();
					if (isFlatten)
						handleAddAssignmentActionUAUA(k, sb_assignments, what, where);
					else
						handleAddAssignmentNoFlattenAction(k, sb_assignments_not_flattened, what, where);
				}
			}
			if (action instanceof DeleteAction) {
				deleteAction = (DeleteAction) action;
				if (deleteAction.getAssignments() != null) {
					if ((deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("U")
							&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType().equals("UA"))
							|| (deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("O")
									&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType()
											.equals("OA"))) {
						what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
						where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
						if (isFlatten)
							handleDeleteAssignmentActionUUA(k, sb_assignments, what, where);
						else
							handleDeleteAssignmentNoFlattenAction(k, sb_assignments_not_flattened, what, where);
					}
				}
				if (deleteAction.getAssignments() != null) {
					if ((deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("UA")
							&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType().equals("UA"))
							|| (deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("OA")
									&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType()
											.equals("OA"))) {
						what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
						where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
						if (isFlatten)
							handleDeleteAssignmentActionUAUA(k, sb_assignments, what, where);
						else
							handleDeleteAssignmentNoFlattenAction(k, sb_assignments_not_flattened, what, where);
					}
				}
			}

		}
		if (isFlatten) {
			handleAssignmentTrailing(k, sb_assignments);
			return sb_assignments.toString();

		} else {
			handleAssignmentNoFlattenTrailing(k, sb_assignments_not_flattened);
			return sb_assignments_not_flattened.toString();
		}
	}

	private void handleDeleteAssignmentActionUUA(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(setminus (ASSIGN* " + (k - 1) + ") (setminus (join (singleton (mkTuple " + whatID + " "
				+ whereID + ")) (ASSIGN* " + (k - 1) + ")) (join (join (singleton (mkTuple " + whatID + " " + whatID
				+ ")) (setminus (setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID
				+ "))) (singleton (mkTuple " + whatID + " " + whatID + ")))) (ASSIGN* " + (k - 1) + "))))");
	}

	private void handleDeleteAssignmentActionUAUA(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(setminus (ASSIGN* " + (k - 1) + ") (setminus (setminus (union (singleton (mkTuple "
				+ whatID + " " + whereID + ")) (join (singleton (mkTuple " + whatID + " " + whereID + ")) (ASSIGN* "
				+ (k - 1) + "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
				+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
				+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
				+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
				+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1)
				+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
				+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
				+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
				+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
				+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1) + "))))");
		// sb_assignments.append("(setminus (ASSIGN* " + (k - 1) + ") (setminus (join (singleton (mkTuple " + whatID + " "
		// 		+ whereID + ")) (ASSIGN* " + (k - 1) + ")) (join (join (singleton (mkTuple " + whatID + " " + whatID
		// 		+ ")) (setminus (setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID
		// 		+ "))) (singleton (mkTuple " + whatID + " " + whatID + ")))) (ASSIGN* " + (k - 1) + "))))");
	}

	private void handleDeleteAssignmentNoFlattenAction(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments
				.append("(setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))");
	}

	private void handleAddAssignmentActionUUA(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(union (singleton (mkTuple " + whatID + " " + whereID
				+ ")) (union (join (singleton (mkTuple " + whatID + " " + whereID + ")) (join (singleton (mkTuple "
				+ whereID + " " + whereID + ")) (ASSIGN* " + (k - 1) + "))) (ASSIGN* " + (k - 1) + ")))");
	}

	private void handleAddAssignmentActionMultipleUUA(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(union (singleton (mkTuple " + whatID + " " + whereID
				+ ")) (union (join (singleton (mkTuple " + whatID + " " + whereID + ")) (join (singleton (mkTuple "
				+ whereID + " " + whereID + ")) (ASSIGN* " + (k - 1) + "))) (ASSIGN* " + (k - 1) + ")))");
	}


	private void handleAddAssignmentActionUAUA(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(union (join (join (union (singleton (mkTuple " + whatID + " " + whatID
				+ ")) (join (ASSIGN* " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whatID
				+ ")))) (singleton (mkTuple " + whatID + " " + whereID + "))) (union (singleton (mkTuple " + whereID
				+ " " + whereID + ")) (join (singleton (mkTuple " + whereID + " " + whereID + ")) (ASSIGN* " + (k - 1)
				+ ") ))) (ASSIGN* " + (k - 1) + "))");
	}

	private void handleAddAssignmentNoFlattenAction(int k, StringBuilder sb_assignments, String what, String where) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		sb_assignments.append("(union (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))");
	}

	private void handleAssignmentTrailing(int k, StringBuilder sb_assignments) {
		sb_assignments.append(")");
		sb_assignments.append(System.lineSeparator());
		sb_assignments.append("(= (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))))");
		sb_assignments.append(System.lineSeparator());
	}

	private void handleAssignmentNoFlattenTrailing(int k, StringBuilder sb_assignments) {
		sb_assignments.append(")");
		sb_assignments.append(System.lineSeparator());
		sb_assignments.append("(= (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))))");
		sb_assignments.append(System.lineSeparator());
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
				op = deleteAction.getAssociations().get(0).getOperations().get(0);
				SMTAction = "setminus";
			}
			handleAssociationAction(k, sb_associations, what, where, op, SMTAction, i);
		}
		handleAssociationTrailing(k, sb_associations);
		return sb_associations.toString();
	}

	private void handleAssociationAction(int k, StringBuilder sb_associations, String what, String where, String op,
			String SMTAction, int i) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		int arID = mapOfIDs.get(op);

		if (i == 0) {
			sb_associations.append("(" + SMTAction + "  (ASSOC " + (k - 1) + ") (singleton(mkTuple " + whatID + " "
					+ arID + " " + whereID + ")))");
		} else {
			sb_associations.insert(0, "(" + SMTAction + " ");
			sb_associations.append(" (singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")))");
		}
	}

	private void handleAssociationTrailing(int k, StringBuilder sb_associations) {
		sb_associations.append(")");
		sb_associations.append(System.lineSeparator());
		sb_associations.append("(= (ASSOC " + k + ") (ASSOC " + (k - 1) + "))))");
		sb_associations.append(System.lineSeparator());
	}

	String processActions(int k) {
		StringBuilder sb_assignments_flatten = new StringBuilder();
		StringBuilder sb_assignments_not_flattened = new StringBuilder();
		StringBuilder sb_associations = new StringBuilder();
		sb_assignments_flatten.append("(assert (or ");
		sb_assignments_flatten.append(System.lineSeparator());
		sb_assignments_not_flattened.append("(assert (or");
		sb_assignments_not_flattened.append(System.lineSeparator());
		sb_associations.append("(assert (or ");
		sb_associations.append(System.lineSeparator());
		for (Rule rule : obligation.getRules()) {
			this.groupActions(rule.getResponsePattern().getActions());
			String condition = processActionCondition(rule, k);
			if (grantGroupActions.size() > 0) {
				sb_associations.append("(and "+ condition+" (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb_associations.append(System.lineSeparator());
				sb_associations.append("(xor (= (ASSOC " + k + ") ");
				sb_associations.append(System.lineSeparator());
				sb_associations.append(this.processAssociationRelatedActions(k));
				sb_associations.append(System.lineSeparator());
			}
			if (assignGroupActions.size() > 0) {
				sb_assignments_flatten.append("(and " + condition
						+ "  (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb_assignments_flatten.append(System.lineSeparator());
				sb_assignments_flatten.append("(xor (= (ASSIGN* " + k + ") ");
				sb_assignments_flatten.append(System.lineSeparator());
				sb_assignments_flatten.append(this.processAssignmentRelatedActions(k, true));
				sb_assignments_flatten.append(System.lineSeparator());

				sb_assignments_not_flattened.append("(and " + condition
						+ "  (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
				sb_assignments_not_flattened.append(System.lineSeparator());
				sb_assignments_not_flattened.append("(xor (= (ASSIGN " + k + ") ");
				sb_assignments_not_flattened.append(System.lineSeparator());
				sb_assignments_not_flattened.append(this.processAssignmentRelatedActions(k, false));
				sb_assignments_not_flattened.append(System.lineSeparator());
			}
		}
		sb_assignments_flatten.append("(= (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))))");
		sb_assignments_not_flattened.append("(= (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))))");
		sb_associations.append("(= (ASSOC " + k + ") (ASSOC " + (k - 1) + "))))");
		return sb_assignments_flatten.toString() + System.lineSeparator() + sb_assignments_not_flattened.toString()
				+ System.lineSeparator() + sb_associations.toString();
		// return System.lineSeparator() + sb_associations.toString();

	}

	String processActionCondition(Rule r, int k) {
		if (r.getResponsePattern().getActions().get(0).getCondition() == null)
			return "";
		List<Function> conditions = r.getResponsePattern().getActions().get(0).getCondition().getCondition();
		List<Arg> args = conditions.get(0).getArgs();
		String ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
		String descendant = args.get(1).getFunction().getArgs().get(0).getValue();
		int ancestorId = mapOfIDs.get(ancestor);
		int descendantId = mapOfIDs.get(descendant);
		return "(member (mkTuple " + ancestorId + " " + descendantId + ") (ASSIGN* " + (k - 1) + "))";

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

	Map<String, String> getEventMembers() {
		return eventMembers;
	}
}
