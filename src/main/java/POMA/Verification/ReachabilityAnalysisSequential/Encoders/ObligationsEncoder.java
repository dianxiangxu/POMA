package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.RaceObligationProcessor;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder;
import POMA.Verification.Translator.AssociationRelation;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.graph.model.nodes.NodeType;
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

public class ObligationsEncoder {

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
	List<String> _customActionVariables = new ArrayList<String>();
	List<String> _raceObligationLabels = new ArrayList<String>();
	List<String> _conditionalInterferenceVariables = new ArrayList<String>();
	List<ObligationEncoder> _obligationEncoders = new ArrayList<ObligationEncoder>();

	public List<ObligationEncoder> getObligationEncoders() {
		return _obligationEncoders;
	}

	Obligation obligation;
	HashMap<String, Integer> mapOfIDs;
	List<Node> listOfNodes;
	private String customObligationSpecPath = "";

	public List<Node> getListOfNodes() {
		return listOfNodes;
	}

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, List<Node> listOfNodes,
			String customObligationSpecPath) {
		this.customObligationSpecPath = customObligationSpecPath;

		this.mapOfIDs = mapOfIDs;
		this.listOfNodes = listOfNodes;
		try {
			obligation = readObligations();
		} catch (EVRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		preprocessObligationRules();
	}

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, String pathToObligations, List<Node> listOfNodes,
			String customObligationSpecPath) {
		this.customObligationSpecPath = customObligationSpecPath;

		this.mapOfIDs = mapOfIDs;
		this.pathToObligations = pathToObligations;
		this.listOfNodes = listOfNodes;
		try {
			obligation = readObligations();
		} catch (EVRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		preprocessObligationRules();
	}

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, Obligation obligation, List<Node> listOfNodes,
			String customObligationSpecPath, boolean processRaceConditions) {
		this.customObligationSpecPath = customObligationSpecPath;

		this.mapOfIDs = mapOfIDs;
		this.obligation = obligation;
		this.listOfNodes = listOfNodes;
		if (processRaceConditions) {
			RaceObligationProcessor rop = new RaceObligationProcessor();
			List<Rule> raceObligations = rop.getObligationsWithRaces(obligation);
			List<Rule> allObligations = new ArrayList<Rule>();
			allObligations.addAll(obligation.getRules());
			allObligations.addAll(raceObligations);
			obligation.setRules(allObligations);
			_raceObligationLabels.addAll(raceObligations.stream().map(Rule::getLabel).collect(Collectors.toList()));
		}
		preprocessObligationRules();
	}

	public List<String> getRaceObligationLabels() {
		return _raceObligationLabels;
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
					try {
						if (!Utils.nodeExistsInList(listOfNodes, what)) {
							listOfNodes.add(new Node(what, NodeType.valueOf(type)));
						}
					} catch (Exception e) {
						e.printStackTrace();
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

			String subject = "";
			if (r.getEventPattern().getSubject().getAnyUser() != null) {
				subject = r.getEventPattern().getSubject().getAnyUser().get(0);
			}
			String target = "";
			if (r.getEventPattern().getTarget() != null
					&& r.getEventPattern().getTarget().getPolicyElements() != null) {
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

			sb.append("(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true) (and\r\n"
					+ " (set.member (tuple  " + obligationU + " " + obligationS + ") (ASSIGN* " + (k - 1) + "))\r\n"
					+ " (set.member (tuple  " + obligationU + " " + obligationUA + ") (ASSIGN* " + (k - 1) + "))\r\n"
					+ " (set.member (tuple " + obligationUA + " " + obligationAR + " " + obligationAT + ") (ASSOC "
					+ (k - 1) + "))\r\n" + " (set.member (tuple  " + obligationUO + " " + obligationT + ") (ASSIGN* "
					+ (k - 1) + "))\r\n" + " (set.member (tuple  " + obligationUO + " " + obligationAT + ") (ASSIGN* "
					+ (k - 1) + "))\r\n" + " (set.member (tuple  " + obligationU + " " + obligationU + ") USERS)\r\n"
					+ " (distinct " + obligationS + " " + obligationU + ")\r\n" + " (distinct " + obligationUO + " "
					+ obligationT + ")\r\n" + ")))");
			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
			ruleLabels.add(r.getLabel());
		}
		return sb.toString();
	}

	void processVariables(String obligationU, String obligationUA, String obligationAT, String obligationUO,
			String obligationS, String obligationT, String obligationAR, StringBuilder sb, Integer subjectID,
			Integer targetID, List<Integer> arIDs) {
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

	String processEventCondition(Rule r, int k, String subject, String target) {
		if (r.getResponsePattern().getCondition() == null)
			return "";
		List<Function> conditions = r.getResponsePattern().getCondition().getCondition();
		List<Arg> args = conditions.get(0).getArgs();
		String ancestor = "";
		String descendant = "";
		if (args.get(0).getFunction().getName().equals("current_target")) {
			ancestor = target;
		} else if (args.get(0).getFunction().getName().equals("current_user")) {
			ancestor = subject;
		} else {
			ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
			int ancestorId = mapOfIDs.get(ancestor);
			ancestor = Integer.toString(ancestorId);
		}
		if (args.get(1).getFunction().getName().equals("current_target")) {
			descendant = target;
		} else if (args.get(1).getFunction().getName().equals("current_user")) {
			descendant = subject;
		} else {
			descendant = args.get(1).getFunction().getArgs().get(0).getValue();
			int descendantId = mapOfIDs.get(descendant);
			descendant = Integer.toString(descendantId);
		}
		return "(member (tuple " + ancestor + " " + descendant + ") (ASSIGN* " + (k - 1) + "))";
	}

	private void preprocessObligationRules() {
		processedObligations.add("");
		processedObligationsEventLabels.add("");
		for (Rule r : obligation.getRules()) {
			String subject = "";
			if (r.getEventPattern().getSubject().getAnyUser() != null) {
				subject = r.getEventPattern().getSubject().getAnyUser().get(0); // TODO: Add multiple users
			}
			if (r.getEventPattern().getTarget() != null
					&& r.getEventPattern().getTarget().getPolicyElements() != null) {
				String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
				eventMembers.put(subject, target);
			}
			// System.out.println(subject + " : " + ar + " : " + target);
			ruleLabels.add(r.getLabel());

			processedObligationsEventLabels.addAll(getEvents(r));
		}
	}

	// 5.2
	String processEffect(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.2 a->Eff");
		List<String> actionSetsAssignAdd = new ArrayList<String>();
		List<String> actionSetsAssignAddFlat = new ArrayList<String>();
		List<String> actionSetsAssociateAdd = new ArrayList<String>();
		List<String> actionSetsAssociateRemove = new ArrayList<String>();
		List<String> actionSetsAssignRemove = new ArrayList<String>();
		List<String> actionSetsAssignRemoveFlat = new ArrayList<String>();
		List<String> assignRelatedObligationLabels = new ArrayList<String>();
		List<String> associateRelatedObligationLabels = new ArrayList<String>();
		_obligationEncoders = new ArrayList<ObligationEncoder>();
		for (Rule rule : obligation.getRules()) {
			ObligationEncoder oe = new ObligationEncoder();
			oe.setCustomizationPath(customObligationSpecPath);

			sb.append(System.lineSeparator());
			sb.append(oe.encodeObligation(rule, mapOfIDs, k));
			sb.append(System.lineSeparator());
			actionSetsAssignAdd.addAll(oe._actionSetsAssignAdd);
			actionSetsAssignAddFlat.addAll(oe._actionSetsAssignAddFlat);
			actionSetsAssociateAdd.addAll(oe._actionSetsAssociateAdd);
			actionSetsAssociateRemove.addAll(oe._actionSetsAssociateRemove);
			actionSetsAssignRemove.addAll(oe._actionSetsAssignRemove);
			actionSetsAssignRemoveFlat.addAll(oe._actionSetsAssignRemoveFlat);
			if (oe.isAssignRelated) {
				assignRelatedObligationLabels.add(rule.getLabel());
			}
			if (oe.isAssociateRelated) {
				associateRelatedObligationLabels.add(rule.getLabel());
			}
			_customActionVariables = Stream
					.concat(_customActionVariables.stream(), oe.getCustomActionVariables().stream()).distinct()
					.collect(Collectors.toList());
			_conditionalInterferenceVariables.addAll(oe.getConditionalInterferenceVariables());
			_obligationEncoders.add(oe);
		}
		sb.append(System.lineSeparator());
		try {
			sb.append(ActionEncoder.replaceKWithValue(
					encodeRelationTransition(actionSetsAssignAdd, actionSetsAssignAddFlat, actionSetsAssociateAdd,
							actionSetsAssociateRemove, actionSetsAssignRemove, actionSetsAssignRemoveFlat),
					k));
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.3 change implies the execution");
		sb.append(System.lineSeparator());
		sb.append(implyExecution(k, assignRelatedObligationLabels, associateRelatedObligationLabels));
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.4 Exactly one naive");
		sb.append(System.lineSeparator());
		sb.append(exactlyOneNaive(k));
		return sb.toString();
	}

	private String encodeRelationTransition(List<String> actionSetsAssignAdd, List<String> actionSetsAssignAddFlat,
			List<String> actionSetsAssociateAdd, List<String> actionSetsAssociateRemove,
			List<String> actionSetsAssignRemove, List<String> actionSetsAssignRemoveFlat) {
		String assign = encodeAssignmentTransition(actionSetsAssignAdd, actionSetsAssignRemove);
		String assignflat = encodeFlatAssignmentTransition(actionSetsAssignAddFlat, actionSetsAssignRemoveFlat);
		String assoc = encodeAssociationTransition(actionSetsAssociateAdd, actionSetsAssociateRemove);
		return System.lineSeparator() + ";RELATION TRANSITION ENCODING" + System.lineSeparator() + assign
				+ System.lineSeparator() + assignflat + System.lineSeparator() + assoc;
	}

	private String encodeAssignmentTransition(List<String> actionSetsAssignAdd, List<String> actionSetsAssignRemove) {
		String assign = "";
		String operationSetEncodingPlus = "";
		if (actionSetsAssignAdd.size() + actionSetsAssignRemove.size() == 0) {
			return "(assert (= (ASSIGN {k}) (ASSIGN {k-1})))";
		}
		for (int i = 0; i < actionSetsAssignAdd.size(); i++) {
			String operationSet = actionSetsAssignAdd.get(i);
			if (i == 0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}

			operationSetEncodingPlus = "(set.union " + operationSet + " " + operationSetEncodingPlus + ")";
			actionSetsAssignAdd.get(i);
		}

		assign = "\t(set.union (ASSIGN {k-1}) " + operationSetEncodingPlus + ")";
		if (actionSetsAssignRemove.size() == 0) {
			return "(assert (= (ASSIGN {k}) " + System.lineSeparator() + assign + System.lineSeparator() + "))";
		}
		String operationSetEncodingMinus = "";
		for (int i = 0; i < actionSetsAssignRemove.size(); i++) {
			String operationSet = actionSetsAssignRemove.get(i);
			if (i == 0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t(set.union " + operationSet + " " + operationSetEncodingMinus + ")";
			actionSetsAssignRemove.get(i);
		}

		return "(assert (= (ASSIGN {k}) " + System.lineSeparator() + "\t(set.minus " + System.lineSeparator() + assign
				+ " " + System.lineSeparator() + operationSetEncodingMinus + System.lineSeparator() + ")"
				+ System.lineSeparator() + "))";
	}

	private String encodeFlatAssignmentTransition(List<String> actionSetsAssignAddFlat,
			List<String> actionSetsAssignRemoveFlat) {
		String assign = "";
		String operationSetEncodingPlus = "";
		if (actionSetsAssignAddFlat.size() + actionSetsAssignRemoveFlat.size() == 0) {
			return "(assert (= (ASSIGN* {k}) (ASSIGN* {k-1})))";
		}
		for (int i = 0; i < actionSetsAssignAddFlat.size(); i++) {
			String operationSet = actionSetsAssignAddFlat.get(i);
			if (i == 0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}

			operationSetEncodingPlus = "(set.union " + operationSet + " " + operationSetEncodingPlus + ")";
			actionSetsAssignAddFlat.get(i);
		}

		assign = "\t(set.union (ASSIGN* {k-1}) " + operationSetEncodingPlus + ")";
		if (actionSetsAssignRemoveFlat.size() == 0) {
			return "(assert (= (ASSIGN* {k}) " + System.lineSeparator() + assign + System.lineSeparator() + "))";
		}
		String operationSetEncodingMinus = "";
		for (int i = 0; i < actionSetsAssignRemoveFlat.size(); i++) {
			String operationSet = actionSetsAssignRemoveFlat.get(i);
			if (i == 0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t(set.union " + operationSet + " " + operationSetEncodingMinus + ")";
			actionSetsAssignRemoveFlat.get(i);
		}

		return "(assert (= (ASSIGN* {k}) " + System.lineSeparator() + "\t(set.minus " + System.lineSeparator() + assign
				+ " " + System.lineSeparator() + operationSetEncodingMinus + System.lineSeparator() + ")"
				+ System.lineSeparator() + "))";
	}

	private String encodeAssociationTransition(List<String> actionSetsAssociateAdd,
			List<String> actionSetsAssociateRemove) {
		String associate = "";
		String operationSetEncodingPlus = "";
		if (actionSetsAssociateAdd.size() + actionSetsAssociateRemove.size() == 0) {
			return "(assert (= (ASSOC {k}) (ASSOC {k-1})))";
		}
		for (int i = 0; i < actionSetsAssociateAdd.size(); i++) {
			String operationSet = actionSetsAssociateAdd.get(i);
			if (i == 0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}

			operationSetEncodingPlus = "(set.union " + operationSet + " " + operationSetEncodingPlus + ")";
			actionSetsAssociateAdd.get(i);
		}

		associate = "\t(set.union (ASSOC {k-1}) " + operationSetEncodingPlus + ")";
		if (actionSetsAssociateRemove.size() == 0) {
			return "(assert (= (ASSOC {k}) " + System.lineSeparator() + associate + System.lineSeparator() + "))";
		}
		String operationSetEncodingMinus = "";
		for (int i = 0; i < actionSetsAssociateRemove.size(); i++) {
			String operationSet = actionSetsAssociateRemove.get(i);
			if (i == 0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t(set.union " + operationSet + " " + operationSetEncodingMinus + ")";
			actionSetsAssociateRemove.get(i);
		}

		return "(assert (= (ASSOC {k}) " + System.lineSeparator() + "\t(set.minus " + System.lineSeparator() + associate
				+ " " + System.lineSeparator() + operationSetEncodingMinus + System.lineSeparator() + ")"
				+ System.lineSeparator() + "))";
	}

	// 5.3
	String implyExecution(int k, List<String> assignRelatedObligationLabels,
			List<String> associateRelatedObligationLabels) {
		StringBuilder sbASSIGNFlat = new StringBuilder();
		StringBuilder sbASSOC = new StringBuilder();
		StringBuilder sbASSIGN = new StringBuilder();

		if (assignRelatedObligationLabels.size() > 0) {
			sbASSIGNFlat.append("(assert (=> (distinct (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))"
					+ System.lineSeparator() + "(or ");
			sbASSIGNFlat.append(System.lineSeparator());
			sbASSIGN.append("(assert (=> (distinct (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))"
					+ System.lineSeparator() + "(or ");
			for (String label : assignRelatedObligationLabels) {
				sbASSIGNFlat.append("(= (" + label + " " + (k - 1) + ") true)");
				sbASSIGN.append("(= (" + label + " " + (k - 1) + ") true)");
			}
			sbASSIGNFlat.append(")))");
			sbASSIGN.append(")))");
		}

		if (associateRelatedObligationLabels.size() > 0) {

			sbASSOC.append("(assert (=> (distinct (ASSOC " + k + ") (ASSOC " + (k - 1) + "))" + System.lineSeparator()
					+ "(or ");
			sbASSOC.append(System.lineSeparator());

			for (String label : associateRelatedObligationLabels) {
				sbASSOC.append("(= (" + label + " " + (k - 1) + ") true)");
			}
			sbASSOC.append(")))");
		}

		return sbASSIGNFlat.toString() + System.lineSeparator() + sbASSIGN.toString() + System.lineSeparator()
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

		sb.append("; AT LEAST ONE");
		sb.append(System.lineSeparator());
		sb.append("(assert (or");
		for (String label : ruleLabels) {
			sb.append("(= (" + label + " " + (k - 1) + ") true)");
		}
		sb.append("))");
		sb.append(System.lineSeparator());

		// AT MOST ONE
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; AT MOST ONE");
		for (String tuple : labelTuples) {
			String[] tupleArray = tuple.split(":");
			sb.append(System.lineSeparator());
			sb.append("(assert (not (and (= (" + tupleArray[0] + " " + (k - 1) + ") true)(= (" + tupleArray[1] + " "
					+ (k - 1) + ") true))))");
		}
		sb.append(System.lineSeparator());

		sb.append(System.lineSeparator());
		return sb.toString();
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

	Map<String, String> getEventPolicyElements() {
		return eventMembers;
	}

	public List<String> getCustomActionVariables() {
		return _customActionVariables;
	}

	public void setCustomActionVariables(List<String> _customActionVariables) {
		this._customActionVariables = _customActionVariables;
	}

	public List<String> getConditionalInterferenceVariables() {
		return _conditionalInterferenceVariables;
	}
	
}
