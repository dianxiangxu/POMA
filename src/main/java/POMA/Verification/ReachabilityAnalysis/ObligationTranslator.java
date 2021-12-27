package POMA.Verification.ReachabilityAnalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml"; // +++
	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_conditions.yml";

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple1.yml";
	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml";// +++
	String pathToObligations;
	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEventLabels = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();;
	private List<String> listOfCreatedNodesUA = new ArrayList<String>();
	private List<String> listOfCreatedNodesOA = new ArrayList<String>();
	private Set<String> ruleLabels = new HashSet<String>();
	private String actionsTranslation;
	private Map<String, String> eventMembers = new HashMap<String, String>();
	private List<String> obligationEventVariables = new ArrayList<String>();

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

	public ObligationTranslator(HashMap<String, Integer> mapOfIDs, String pathToObligations) {
		this.mapOfIDs = mapOfIDs;
		this.pathToObligations = pathToObligations;
		try {
			obligation = readObligations();
		} catch (EVRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		translateObligationRules();
	}

	public ObligationTranslator(HashMap<String, Integer> mapOfIDs, Obligation obligation) {
		this.mapOfIDs = mapOfIDs;
		this.obligation = obligation;
		translateObligationRules();
	}

	public List<String> getProcessedObligations() {
		return processedObligations;
	}

	public List<String> getProcessedObligationsEventLabels() {
		return processedObligationsEventLabels;
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

	public List<String> getObligationEventVariables() {
		return obligationEventVariables;
	}

	// Translate events + condition for the whole obligation
	String translateObligationPreconditions(int k) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append("; 5.1 a->PRE");
		for (Rule r : obligation.getRules()) {
			String subject ="";
			if(r.getEventPattern().getSubject().getAnyUser()!=null){
			subject = r.getEventPattern().getSubject().getAnyUser().get(0);
			}
			String target = "";
			if(r.getEventPattern().getTarget().getPolicyElements() != null){
			target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
			}
			String obligationLabel = r.getLabel();

			Integer subjectID = null;
			if (mapOfIDs.containsKey(subject)) {
				subjectID = mapOfIDs.get(subject);
			}

			List<Integer> arIds = new ArrayList<Integer>();
			for (String ar : r.getEventPattern().getOperations()) {
				arIds.add(mapOfIDs.get(ar));
			}

			Integer targetID = null;
			if (mapOfIDs.containsKey(target)) {
				targetID = mapOfIDs.get(target);
			}

			String obligationU = obligationLabel + "U" + "_" + (k - 1);
			String obligationUA = obligationLabel + "UA" + "_" + (k - 1);
			String obligationAT = obligationLabel + "AT" + "_" + (k - 1);
			String obligationUO = obligationLabel + "UO" + "_" + (k - 1);
			String obligationS = obligationLabel + "S" + "_" + (k - 1);
			String obligationT = obligationLabel + "T" + "_" + (k - 1);
			String obligationAR = obligationLabel + "ar" + "_" + (k - 1);
			obligationEventVariables.addAll(new ArrayList<String>(Arrays.asList(obligationU, obligationUA, obligationAT,
					obligationUO, obligationS, obligationT, obligationAR)));
			sb.append(System.lineSeparator());
			processVariables(obligationU, obligationUA, obligationAT, obligationUO, obligationS, obligationT,
					obligationAR, sb, subjectID, targetID, arIds);

			sb.append("(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true) (and\r\n" + " (member (mkTuple  "
					+ obligationU + " " + obligationS + ") (ASSIGN* " + (k - 1) + "))\r\n" + " (member (mkTuple  "
					+ obligationS + " " + obligationUA + ") (ASSIGN* " + (k - 1) + "))\r\n" + "(member (mkTuple "
					+ obligationUA + " " + obligationAR + " " + obligationAT + ") (ASSOC " + (k - 1) + "))\r\n"
					+ " (member (mkTuple  " + obligationUO + " " + obligationT + ") (ASSIGN* " + (k - 1) + "))\r\n"
					+ " (member (mkTuple  " + obligationT + " " + obligationAT + ") (ASSIGN* " + (k - 1) + "))\r\n"
					//+ " (distinct " + obligationS + " " + obligationU + ")\r\n" 
					//+ " (distinct " + obligationUO + " " + obligationT + ")\r\n" 
					+ ")))");
			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
			ruleLabels.add(r.getLabel());
		}
		return sb.toString();
	}

	void processVariables(String obligationU, String obligationUA, String obligationAT, String obligationUO,
			String obligationS, String obligationT, String obligationAR, StringBuilder sb, Integer subjectID,
			Integer targetID,
			List<Integer> arIDs) {
		sb.append("(declare-fun " + obligationU + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationUA + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationAT + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationUO + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationAR + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationS + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + obligationT + " () Int)");
		sb.append(System.lineSeparator());
		sb.append("(assert (>= " + obligationU + " 0))");
		sb.append(System.lineSeparator());
		sb.append("(assert (>= " + obligationUA + " 0))");
		sb.append(System.lineSeparator());
		sb.append("(assert (>= " + obligationAT + " 0))");
		sb.append(System.lineSeparator());
		sb.append("(assert (>= " + obligationUO + " 0))");
		sb.append(System.lineSeparator());
		sb.append("(assert (or");
		for (int arID : arIDs) {
			sb.append(" (= " + obligationAR + " " + arID + ")");
			sb.append(System.lineSeparator());
		}
		sb.append("))");
		sb.append(System.lineSeparator());
		if (subjectID != null) {
			sb.append("(assert (= " + obligationS + " " + subjectID + "))");
		} else {
			sb.append("(assert (>= " + obligationS + " 0))");
		}
		sb.append(System.lineSeparator());
		if (targetID != null) {
			sb.append("(assert (= " + obligationT + " " + targetID + "))");
		} else {
			sb.append("(assert (>= " + obligationT + " 0))");
		}
		sb.append(System.lineSeparator());
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

	private void translateObligationRules() {
		processedObligations.add("");
		processedObligationsEventLabels.add("");
		for (Rule r : obligation.getRules()) {
			String subject = "";
			if (r.getEventPattern().getSubject().getAnyUser() != null) {
				subject = r.getEventPattern().getSubject().getAnyUser().get(0); // TODO: Add multiple users
			}
			if (r.getEventPattern().getTarget().getPolicyElements() != null) {
				String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
				eventMembers.put(subject, target);
			}
			// System.out.println(subject + " : " + ar + " : " + target);
			ruleLabels.add(r.getLabel());

			processedObligationsEventLabels.addAll(getEvents(r));
		}
	}

	private String processAssignmentRelatedAction(int k, String obligationLabel, List<Action> actions) {
		StringBuilder sb = new StringBuilder();

		AssignAction assignAction;
		DeleteAction deleteAction;
		CreateAction createAction;

		String what = "";
		String where = "";
		String innerAction = "";
		String innerActionNoFlatten = "";

		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
			if (action instanceof AssignAction) {
				assignAction = (AssignAction) action;
				if ((assignAction.getAssignments().get(0).getWhat().getType().equals("U")
						&& assignAction.getAssignments().get(0).getWhere().getType().equals("UA"))
						|| (assignAction.getAssignments().get(0).getWhat().getType().equals("O")
								&& assignAction.getAssignments().get(0).getWhere().getType().equals("OA"))) {
					what = assignAction.getAssignments().get(0).getWhat().getName();
					where = assignAction.getAssignments().get(0).getWhere().getName();
					innerAction = handleAddAssignmentActionUUA(k, what, where, obligationLabel, innerAction);
				} else if ((assignAction.getAssignments().get(0).getWhat().getType().equals("UA")
						&& assignAction.getAssignments().get(0).getWhere().getType().equals("UA"))
						|| (assignAction.getAssignments().get(0).getWhat().getType().equals("OA")
								&& assignAction.getAssignments().get(0).getWhere().getType().equals("OA"))) {
					what = assignAction.getAssignments().get(0).getWhat().getName();
					where = assignAction.getAssignments().get(0).getWhere().getName();
					innerAction = handleAddAssignmentActionUAUA(k, what, where, obligationLabel, innerAction);

				}
				innerActionNoFlatten = handleAddAssignmentNoFlattenAction(k, what, where, obligationLabel,
						innerActionNoFlatten);
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
						innerAction = handleDeleteAssignmentActionUUA(k, what, where, obligationLabel, innerAction);
					}
					if ((deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("UA")
							&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType().equals("UA"))
							|| (deleteAction.getAssignments().getAssignments().get(0).getWhat().getType().equals("OA")
									&& deleteAction.getAssignments().getAssignments().get(0).getWhere().getType()
											.equals("OA"))) {
						what = deleteAction.getAssignments().getAssignments().get(0).getWhat().getName();
						where = deleteAction.getAssignments().getAssignments().get(0).getWhere().getName();
						innerAction = handleDeleteAssignmentActionUAUA(k, what, where, obligationLabel, innerAction);
					}
				}
				innerActionNoFlatten = handleDeleteAssignmentNoFlattenAction(k, what, where, obligationLabel,
						innerActionNoFlatten);
			}
			if (action instanceof CreateAction) {
				createAction = (CreateAction) action;
				if (createAction.getCreateNodesList().get(0) != null) {
					if ((createAction.getCreateNodesList().get(0).getWhat().getType().equals("U")
							&& createAction.getCreateNodesList().get(0).getWhere().getType().equals("UA"))
							|| (createAction.getCreateNodesList().get(0).getWhat().getType().equals("O")
									&& createAction.getCreateNodesList().get(0).getWhere().getType()
											.equals("OA"))) {
						what = createAction.getCreateNodesList().get(0).getWhat().getName();
						where = createAction.getCreateNodesList().get(0).getWhere().getName();
						innerAction = handleAddAssignmentActionUUA(k, what, where, obligationLabel, innerAction);
					}
					if ((createAction.getCreateNodesList().get(0).getWhat().getType().equals("UA")
							&& createAction.getCreateNodesList().get(0).getWhere().getType().equals("UA"))
							|| (createAction.getCreateNodesList().get(0).getWhat().getType().equals("OA")
									&& createAction.getCreateNodesList().get(0).getWhere().getType()
											.equals("OA"))) {
						what = createAction.getCreateNodesList().get(0).getWhat().getName();
						where = createAction.getCreateNodesList().get(0).getWhere().getName();
						innerAction = handleAddAssignmentActionUAUA(k, what, where, obligationLabel, innerAction);
					}
				}
				innerActionNoFlatten = handleAddAssignmentNoFlattenAction(k, what, where, obligationLabel,
						innerActionNoFlatten);
			}
		}
		sb.append(System.lineSeparator());
		sb.append(finishHandlingAssignmentAction(k, obligationLabel, innerAction));
		sb.append(System.lineSeparator());
		sb.append(finishHandlingAssignmentActionNoFlatten(k, obligationLabel, innerActionNoFlatten));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	private String handleDeleteAssignmentActionUUA(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {
			return " (setminus (ASSIGN* " + (k - 1) + ") (setminus (join (singleton (mkTuple " + whatID + " " + whereID
					+ ")) (ASSIGN* " + (k - 1) + ")) (join (join (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (setminus (setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID
					+ "))) (singleton (mkTuple " + whatID + " " + whatID + ")))) (ASSIGN* " + (k - 1) + "))))";
		} else {
			return " (setminus " + innerAction
					+ " (setminus (join (singleton (mkTuple " + whatID + " " + whereID
					+ ")) (ASSIGN* " + (k - 1) + ")) (join (join (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (setminus (setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID
					+ "))) (singleton (mkTuple " + whatID + " " + whatID + ")))) (ASSIGN* " + (k - 1) + "))))";
		}
	}

	private String handleDeleteAssignmentActionUAUA(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {
			return "(setminus (ASSIGN* " + (k - 1) + ") (setminus (setminus (union (singleton (mkTuple " + whatID + " "
					+ whereID + ")) (join (singleton (mkTuple " + whatID + " " + whereID + ")) (ASSIGN* " + (k - 1)
					+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
					+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1)
					+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
					+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1) + "))))";
		} else {
			return "(setminus " + innerAction + " (setminus (setminus (union (singleton (mkTuple " + whatID + " "
					+ whereID + ")) (join (singleton (mkTuple " + whatID + " " + whereID + ")) (ASSIGN* " + (k - 1)
					+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
					+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1)
					+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + whatID + " " + whatID
					+ ")) (join (ASSIGN* " + (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID
					+ ")))) (transpose (union  (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ")  (singleton (mkTuple " + whatID + " " + whatID + ")))))) NODES) (setminus (ASSIGN "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))) (ASSIGN* " + (k - 1) + "))))";
		}
	}

	private String handleDeleteAssignmentNoFlattenAction(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {
			return " (setminus (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))";
		} else {
			return " (setminus " + innerAction + " (singleton (mkTuple " + whatID + " " + whereID + ")))";
		}
	}

	private String handleAddAssignmentActionUUA(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {
			return "(union (singleton (mkTuple " + whatID + " " + whereID + ")) (union (join (singleton (mkTuple "
					+ whatID + " " + whereID + ")) (join (singleton (mkTuple " + whereID + " " + whereID
					+ ")) (ASSIGN* " + (k - 1) + "))) (ASSIGN* " + (k - 1) + ")))";
		} else {
			return "(union (singleton (mkTuple " + whatID + " " + whereID + ")) (union (join (singleton (mkTuple "
					+ whatID + " " + whereID + ")) (join (singleton (mkTuple " + whereID + " " + whereID
					+ ")) (ASSIGN* " + (k - 1) + ")))" + innerAction + "))";
		}
	}

	private String handleAddAssignmentActionUAUA(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {

			return " (union (join (join (union (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whatID + ")))) (singleton (mkTuple " + whatID
					+ " " + whereID + "))) (union (singleton (mkTuple " + whereID + " " + whereID
					+ ")) (join (singleton (mkTuple " + whereID + " " + whereID + ")) (ASSIGN* " + (k - 1)
					+ ") ))) (ASSIGN* " + (k - 1) + "))";
		} else {
			return " (union (join (join (union (singleton (mkTuple " + whatID + " " + whatID + ")) (join (ASSIGN* "
					+ (k - 1) + ") (singleton (mkTuple " + whatID + " " + whatID + ")))) (singleton (mkTuple " + whatID
					+ " " + whereID + "))) (union (singleton (mkTuple " + whereID + " " + whereID
					+ ")) (join (singleton (mkTuple " + whereID + " " + whereID + ")) (ASSIGN* " + (k - 1) + ") )))"
					+ innerAction + ")";
		}
	}

	private String handleAddAssignmentNoFlattenAction(int k, String what, String where, String obligationLabel,
			String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		if (innerAction.isEmpty()) {
			return "( union (ASSIGN " + (k - 1) + ") (singleton (mkTuple " + whatID + " " + whereID + ")))";
		} else {
			return "( union " + innerAction + " (singleton (mkTuple " + whatID + " " + whereID + ")))";
		}
	}

	private String finishHandlingAssignmentAction(int k, String obligationLabel, String completePlanningAction) {
		return "(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true)" + "(= " + "(ASSIGN* " + k + ")"
				+ completePlanningAction + ")))";
	}

	private String finishHandlingAssignmentActionNoFlatten(int k, String obligationLabel,
			String completePlanningAction) {
		return "(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true)" + "(= " + "(ASSIGN " + k + ")"
				+ completePlanningAction + ")))";
	}

	private String processAssociationRelatedActions(int k, String obligationLabel, List<Action> actions) {
		StringBuilder sb_associations = new StringBuilder();
		GrantAction grantAction;
		DeleteAction deleteAction;
		String what = "";
		String where = "";
		String op = "";
		String SMTAction = "";
		String innerAction = "";
		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
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
			innerAction = handleAssociationAction(k, sb_associations, what, where, op, SMTAction, obligationLabel,
					innerAction);
		}
		innerAction = finishHandlingAssociationAction(k, obligationLabel, innerAction);
		return innerAction;
	}

	private String handleAssociationAction(int k, StringBuilder sb_associations, String what, String where, String op,
			String SMTAction, String obligationLabel, String innerAction) {
		int whatID = mapOfIDs.get(what);
		int whereID = mapOfIDs.get(where);
		int arID = mapOfIDs.get(op);

		if (innerAction.isEmpty()) {
			return "(" + SMTAction + "(ASSOC " + (k - 1) + ")" + "(singleton(mkTuple " + whatID + " " + arID + " "
					+ whereID + ")))";
		} else {
			return "(" + SMTAction + innerAction + "(singleton(mkTuple " + whatID + " " + arID + " " + whereID + ")))";
		}
	}

	private String finishHandlingAssociationAction(int k, String obligationLabel, String completePlanningAction) {
		return "(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true)" + "(= (ASSOC " + k + ") "
				+ completePlanningAction + ")))";
	}

	private void separateActions(List<Action> actions, List<Action> assignActions, List<Action> associateActions) {
		for (Action a : actions) {
			if (isActionAssignmentRelated(a)) {
				assignActions.add(a);
			} else if (isActionAssociationRelated(a)) {
				associateActions.add(a);
			}
		}

	}

	// 5.2
	String processEffects(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.2 a->Eff");
		sb.append(System.lineSeparator());
		for (Rule rule : obligation.getRules()) {
			List<Action> assignActions = new ArrayList<Action>();
			List<Action> associateActions = new ArrayList<Action>();
			String obligationLabel = rule.getLabel();
			separateActions(rule.getResponsePattern().getActions(), assignActions, associateActions);

			boolean assignRelatedActionExists = assignActions.size() > 0;
			boolean associateRelatedActionExists = associateActions.size() > 0;

			sb.append(System.lineSeparator());
			if (!assignRelatedActionExists) {
				sb.append(System.lineSeparator());
				sb.append("(assert (=> (=(" + obligationLabel + " " + (k - 1) + ") true) (= (ASSIGN* " + k
						+ ") (ASSIGN* " + (k - 1) + "))))");
				sb.append(System.lineSeparator());
				sb.append("(assert (=> (=(" + obligationLabel + " " + (k - 1) + ") true) (= (ASSIGN " + k + ") (ASSIGN "
						+ (k - 1) + "))))");
				sb.append(System.lineSeparator());
			} else {
				sb.append(processAssignmentRelatedAction(k, obligationLabel, assignActions));
			}
			if (!associateRelatedActionExists) {
				sb.append(System.lineSeparator());
				sb.append("(assert (=> (=(" + obligationLabel + " " + (k - 1) + ") true) (= (ASSOC " + k + ") (ASSOC "
						+ (k - 1) + "))))");
				sb.append(System.lineSeparator());
			} else {
				sb.append(processAssociationRelatedActions(k, obligationLabel, associateActions));
				sb.append(System.lineSeparator());
			}
			sb.append(System.lineSeparator());

			sb.append(System.lineSeparator());
		}
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.3 change implies the execution");
		sb.append(System.lineSeparator());
		sb.append(implyExecution(k));
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.4 Exactly one naive");
		sb.append(System.lineSeparator());
		sb.append(exactlyOneNaive(k));
		return sb.toString();
	}

	// 5.3
	String implyExecution(int k) {
		StringBuilder sbASSIGN = new StringBuilder();
		StringBuilder sbASSOC = new StringBuilder();
		StringBuilder sbASSIGNEXPLICIT = new StringBuilder();

		sbASSIGN.append("(assert (=> (distinct (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))" + System.lineSeparator()
				+ "(or ");
		sbASSIGN.append(System.lineSeparator());

		sbASSIGNEXPLICIT.append(
				"(assert (=> (distinct (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))" + System.lineSeparator() + "(or ");

		sbASSOC.append(
				"(assert (=> (distinct (ASSOC " + k + ") (ASSOC " + (k - 1) + "))" + System.lineSeparator() + "(or ");
		sbASSOC.append(System.lineSeparator());

		for (String label : ruleLabels) {
			sbASSIGN.append("(= (" + label + " " + (k - 1) + ") true)");
			sbASSIGNEXPLICIT.append("(= (" + label + " " + (k - 1) + ") true)");
			sbASSOC.append("(= (" + label + " " + (k - 1) + ") true)");
		}
		sbASSIGN.append(")))");
		sbASSOC.append(")))");
		sbASSIGNEXPLICIT.append(")))");

		return sbASSIGN.toString() + System.lineSeparator() + sbASSIGNEXPLICIT.toString() + System.lineSeparator()
				+ sbASSOC.toString() + System.lineSeparator();
	}

	// 5.4
	String exactlyOneNaive(int k) {
		StringBuilder sb = new StringBuilder();
		List<String> labelTuples = new ArrayList<String>();
		String[] arrayOfLabels = ruleLabels.stream().toArray(String[]::new);
		for (int i = 0; i < arrayOfLabels.length; i++) {
			for (int j = 0; j < arrayOfLabels.length; j++) {
				if (!arrayOfLabels[i].equals(arrayOfLabels[j])) {
					String tuple = arrayOfLabels[i] + ":" + arrayOfLabels[j];
					String[] tupleArray = tuple.split(":");
					String reverseTuple = tupleArray[1] + ":" + tupleArray[0];
					if (!labelTuples.contains(tuple) && !labelTuples.contains(reverseTuple)) {
						labelTuples.add(tuple);
					}
				}
			}
		}
		// AT MOST ONE
		// sb.append(System.lineSeparator());
		// sb.append(System.lineSeparator());
		// sb.append("; AT MOST ONE");
		// for (String tuple : labelTuples) {
		// String[] tupleArray = tuple.split(":");
		// sb.append(System.lineSeparator());
		// sb.append("(assert (not (and (= (" + tupleArray[0] + " " + (k - 1) + ") true)
		// (= (" + tupleArray[1] + " "
		// + (k - 1) + ") true))))");
		// }
		// sb.append(System.lineSeparator());
		// sb.append(System.lineSeparator());
		sb.append("; AT LEAST ONE");
		sb.append(System.lineSeparator());
		sb.append("(assert (or");
		for (String label : ruleLabels) {
			sb.append("(= (" + label + " " + (k - 1) + ") true)");
		}
		sb.append("))");
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	boolean isActionAssignmentRelated(Action a) {
		if (a instanceof AssignAction || a instanceof CreateAction) {
			return true;
		}
		if (a instanceof DeleteAction && ((DeleteAction) a).getAssignments() != null) {
			return true;
		}
		return false;
	}

	boolean isActionAssociationRelated(Action a) {
		if (a instanceof GrantAction) {
			return true;
		}
		if (a instanceof DeleteAction && ((DeleteAction) a).getAssociations() != null) {
			return true;
		}
		return false;
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

	Set<String> getRuleLabels() {
		return ruleLabels;
	}

	String getActionsTranslation() {
		return actionsTranslation;
	}

	Map<String, String> getEventMembers() {
		return eventMembers;
	}
}
