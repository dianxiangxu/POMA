package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.model.Solution;
import POMA.Verification.Translator.AssociationRelation;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;

public class SMTComposer extends Planner {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private Map<String, String> eventMembers = new HashMap<String, String>();
	private List<String> obligationEventVariables = new ArrayList<String>();
	String pathToGraph;

	ConfigurationEncoder gt;
	ObligationsEncoder ot;

	public static void main(String[] args) throws Exception {

//		 Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmRunning/LawFirmPolicy.json");
//		 String yml = new String(
//		 		Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmRunning/Obligations_built_in_functions.yml")));

		// CUSTOM FUNCTIONS TEST
//		Graph graph = Utils.readAnyGraph("Policies/TEST/ADDCOPI/Graph.json");
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/ADDCOPI/obligation.yml")));
//		String precondition = "OBLIGATIONLABEL(submit_proposal, ?u0, ?ar0,?at0);";
//
//		String postcondition = "OBLIGATIONLABEL(chair_approve, ?u, ?ar,?at);";
		
//		// RACE CONDITIONS TEST
//		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
//
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/AssignANDGrant.yml")));
		
		//GUI TEST
		Graph graph = Utils.readAnyGraph("Policies/GUI Test/custom_functions/Graph.json");

		String yml = new String(Files.readAllBytes(Paths.get("Policies/GUI Test/custom_functions/AddCoPI.yml")));
		
//		String precondition = "OBLIGATIONLABEL(obligation1_obligation2, ?u0, ?ar0,?at0);";

//		String postcondition = "OBLIGATIONLABEL(obligation1_obligation2, ?u, ?ar,?at);";
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/SequencesTestEnabling/AssignAssign.yml")));

		Obligation obligation = EVRParser.parse(yml);
		SMTComposer checker = new SMTComposer(graph, obligation, "C:\\Users\\dubro\\git\\POMA\\Policies\\GUI Test\\custom_functions\\customization.txt", false);
//		SMTComposer checker = new SMTComposer(graph, obligation, "");

		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		long start = System.currentTimeMillis();
		checker.setBound(4);
		checker.enableSMTOutput(true);

//		Solution solution = checker.solveConstraint(precondition, postcondition, graph);
//		checker.raceConditionFinder(graph);

//		 String precondition = "";
		String postcondition = "OBLIGATIONLABEL(add_copi, ?u, ?ar,?at);";
		Solution solution = checker.solveConstraint(null, postcondition, graph);
		
//		 checker.solveConstraint("PERMIT(LeadAttorneys,approve,AcceptedCases);",
//		 graph);
		System.out.println(solution);
//		System.out.println(checker.mapOfIDs);
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");

		// SolutionSimulator ss = new SolutionSimulator(solution, graph, obligation);
		// ss.simulate();
	}

	public SMTComposer(String customObligationSpecPath) throws Exception {
		gt = new ConfigurationEncoder(pathToGraph);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationsEncoder(mapOfIDs, listOfNodes, customObligationSpecPath);
		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventPolicyElements());
	}

	public SMTComposer(String pathToGraph, String pathToObligations, String customObligationSpecPath) throws Exception {
		this.pathToGraph = pathToGraph;
		gt = new ConfigurationEncoder(pathToGraph);
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels,
				eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationsEncoder(mapOfIDs, pathToObligations, listOfNodes, customObligationSpecPath);

		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventPolicyElements());
	}

	public SMTComposer(Graph graph, Obligation obligations, String customObligationSpecPath, boolean processRaceCondition) throws Exception {

		gt = new ConfigurationEncoder(graph);
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels,
				eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationsEncoder(mapOfIDs, obligations, listOfNodes, customObligationSpecPath, processRaceCondition);

		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels,
				eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		eventMembers.putAll(ot.getEventPolicyElements());
	}

	public SMTComposer(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);
	}

	public List<String> getObligationEventVariables() {
		return obligationEventVariables;
	}

	public String generateHeadCode() throws Exception {
		String headcode = gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O,
				obligationLabels, eventMembers, listOfNodes);
		listOfNodes = gt.getListOfNodes();
		return headcode;
	}

	List<String> getObligationLabels() {
		return obligationLabels;
	}

	protected String generateTailCode(List<String> queryVARS, int k) {
		obligationEventVariables.addAll(ot.getObligationEventVariables());
		List<String> customVariables = ot.getCustomActionVariables();
		List<String> conditionalInterferenceVariables = ot.getConditionalInterferenceVariables();

		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += "(check-sat)";
		smtlibv2Code += System.lineSeparator();
		for (String label : obligationLabels) {
			smtlibv2Code += "(get-value (" + label + "))";
			smtlibv2Code += System.lineSeparator();
		}
		for (String eventVars : obligationEventVariables) {
			smtlibv2Code += "(get-value (" + eventVars + "))";
			smtlibv2Code += System.lineSeparator();
		}
		for (String udVar : queryVARS) {
			smtlibv2Code += "(get-value (" + udVar + "))";
			smtlibv2Code += System.lineSeparator();
		}
		for (String customFunctionVariable : customVariables) {
			for (int i = 1; i <= k; i++) {
				try {
					String customVariable = ActionEncoder.replaceKWithValue(customFunctionVariable, i);
					if (!_customFunctionVariables.contains(customVariable)) {
						_customFunctionVariables.add(customVariable);
					}
					smtlibv2Code += "(get-value (" + customVariable + "))";
					smtlibv2Code += System.lineSeparator();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (String conditionalInterferenceVariable : conditionalInterferenceVariables) {
//			for (int i = 1; i <= k; i++) {
				try {
					String customVariable = ActionEncoder.replaceKWithValue(conditionalInterferenceVariable, k);
					if (!_conditionalInterferenceVariables.contains(customVariable)) {
						_conditionalInterferenceVariables.add(customVariable);
					}
					smtlibv2Code += "(get-value (" + customVariable + "))";
					smtlibv2Code += System.lineSeparator();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			}
		}
		for (int i = 0; i <= k; i++) {
			smtlibv2Code += "(get-value ((" + "ASSIGN" + " " + i + ")))";
			smtlibv2Code += System.lineSeparator();
		}
		for (int i = 0; i <= k; i++) {
			smtlibv2Code += "(get-value ((" + "ASSOC" + " " + i + ")))";
			smtlibv2Code += System.lineSeparator();
		}

		return smtlibv2Code;
	}
	public List<String> getRaceObligationLabels(){
		return ot.getRaceObligationLabels();
	}

	public String generateIterationCode(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append(
				";--------------------------------------------------------------------------------------------------------------------\r\n"
						+ ";STEP" + k);
		sb.append(System.lineSeparator());
		sb.append(ot.translateObligationPreconditions(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processEffect(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
