package POMA.Verification.ReachabilityAnalysisSequential;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.AssociationRelation;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
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
	ObligationEncoder ot;

	public static void main(String[] args) throws Exception {

//		 Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmRunning/LawFirmPolicy.json");
//		 String yml = new String(
//		 		Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmRunning/Obligations_built_in_functions.yml")));
		 Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		 String yml = new String(
		 		Files.readAllBytes(Paths.get("Policies/TEST/AssignAndGrant.yml")));
		Obligation obligation = EVRParser.parse(yml);
		SMTComposer checker = new SMTComposer(graph, obligation);
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		long start = System.currentTimeMillis();
		checker.setBound(2);
		checker.enableSMTOutput(true);

	
		String precondition = "PERMIT(?u,?ar, PendingCases);";
		String postcondition = "";//"OBLIGATIONLABEL(accept_case, ?u, ?ar, PendingCases);";

		//Solution solution = checker.solveConstraint(precondition, postcondition, graph);
		//String precondition = "";
				//String postcondition = "OBLIGATIONLABEL(remove_available_attorney, ?u, ?ar, ?at);";

		Solution solution = checker.solveConstraint(precondition, postcondition, graph);
		//checker.solveConstraint("PERMIT(LeadAttorneys,approve,AcceptedCases);", graph);
		System.out.println(solution);
		System.out.println(checker.mapOfIDs);
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");

		//SolutionSimulator ss = new SolutionSimulator(solution, graph, obligation);
		//ss.simulate();
	}

	public SMTComposer() throws Exception {
		gt = new ConfigurationEncoder(pathToGraph);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationEncoder(mapOfIDs, listOfNodes);
		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
	}

	public SMTComposer(String pathToGraph, String pathToObligations) throws Exception {
		this.pathToGraph = pathToGraph;
		gt = new ConfigurationEncoder(pathToGraph);
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels, eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationEncoder(mapOfIDs, pathToObligations, listOfNodes);
		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
	}

	public SMTComposer(Graph graph, Obligation obligations) throws Exception {
		gt = new ConfigurationEncoder(graph);
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels, eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationEncoder(mapOfIDs, obligations, listOfNodes);
		ot.findAllAbsentElements();
		listOfNodes.addAll(ot.getListOfNodes());
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, obligationLabels, eventMembers, listOfNodes);
		mapOfIDs = gt.getMapOfIDs();
		eventMembers.putAll(ot.getEventMembers());
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
		listOfNodes= gt.getListOfNodes();
		return headcode;
	}

	List<String> getObligationLabels() {
		return obligationLabels;
	}

	protected String generateTailCode(List<String> queryVARS, int k) {
		obligationEventVariables.addAll(ot.getObligationEventVariables());
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
		for(String udVar : queryVARS) {
			smtlibv2Code += "(get-value (" + udVar + "))";
			smtlibv2Code += System.lineSeparator();
		}
		for(int i = 0; i <= k; i++) {
			smtlibv2Code += "(get-value ((" + "ASSIGN" + " "+i+")))";
			smtlibv2Code += System.lineSeparator();
		}
		for(int i = 0; i <= k; i++) {
			smtlibv2Code += "(get-value ((" + "ASSOC" + " "+i+")))";
			smtlibv2Code += System.lineSeparator();
		}
		return smtlibv2Code;
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
		sb.append(ot.processEffects(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
