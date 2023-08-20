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

import POMA.Utils;
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

	Obligation obligation;
	HashMap<String, Integer> mapOfIDs;
	List<Node> listOfNodes;

	public List<Node> getListOfNodes() {
		return listOfNodes;
	}

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, List<Node> listOfNodes) {
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

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, String pathToObligations, List<Node> listOfNodes) {
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

	public ObligationsEncoder(HashMap<String, Integer> mapOfIDs, Obligation obligation, List<Node> listOfNodes) {
		this.mapOfIDs = mapOfIDs;
		this.obligation = obligation;
		this.listOfNodes = listOfNodes;
		preprocessObligationRules();
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
			
			String condition = processEventCondition(r, k,obligationU,obligationUO);
			
			sb.append("(assert (=> (= (" + obligationLabel + " " + (k - 1) + ") true) (and\r\n" + " (set.member (tuple  "
					+ obligationU + " " + obligationS + ") (ASSIGN* " + (k - 1) + "))\r\n" + " (set.member (tuple  "
					+ obligationU + " " + obligationUA + ") (ASSIGN* " + (k - 1) + "))\r\n" + " (set.member (tuple "
					+ obligationUA + " " + obligationAR + " " + obligationAT + ") (ASSOC " + (k - 1) + "))\r\n"
					+ " (set.member (tuple  " + obligationUO + " " + obligationT + ") (ASSIGN* " + (k - 1) + "))\r\n"
					+ " (set.member (tuple  " + obligationUO + " " + obligationAT + ") (ASSIGN* " + (k - 1) + "))\r\n"
					+ " (set.member (tuple  " + obligationU + " " + obligationU + ") USERS)\r\n"
					 + " (distinct " + obligationS + " " + obligationU + ")\r\n"
					// + " (distinct " + obligationUO + " " + obligationT + ")\r\n" 
					 +condition
					+ ")))");
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
		String descendant ="";
		if(args.get(0).getFunction().getName().equals("current_target")) {
			ancestor = target;
		}
		else if(args.get(0).getFunction().getName().equals("current_user")) {
			ancestor = subject;
		}
		else {
			ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
			int ancestorId = mapOfIDs.get(ancestor);
			ancestor = Integer.toString(ancestorId);
		}
		if(args.get(1).getFunction().getName().equals("current_target")) {
			descendant = target;
		}
		else if(args.get(1).getFunction().getName().equals("current_user")) {
			descendant = subject;
		}
		else {
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
	String processEffects(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("; 5.2 a->Eff");
		for (Rule rule : obligation.getRules()) {
			sb.append(System.lineSeparator());
			ObligationEncoder oe = new ObligationEncoder();
			sb.append(oe.encodeObligation(rule, mapOfIDs,  k));
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

		sb.append("; AT LEAST ONE");
		sb.append(System.lineSeparator());
		sb.append("(assert (or");
		for (String label : ruleLabels) {
			sb.append("(= (" + label + " " + (k - 1) + ") true)");
		}
		sb.append("))");
		sb.append(System.lineSeparator());

		// // AT MOST ONE
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
		// sb.append("; AT LEAST ONE");
		// sb.append(System.lineSeparator());
		// sb.append("(assert (or");
		// for (String label : ruleLabels) {
		// sb.append("(= (" + label + " " + (k - 1) + ") true)");
		// }
		// sb.append("))");
		// sb.append(System.lineSeparator());
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
