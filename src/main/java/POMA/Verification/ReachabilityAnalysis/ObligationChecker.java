package POMA.Verification.ReachabilityAnalysis;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;

public class ObligationChecker extends Planner {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private Map<String, String> eventMembers = new HashMap<String, String>();
	private List<String> obligationEventVariables = new ArrayList<String>();

	// String pathToGraph =
	// "Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json"; // +++++++
	String pathToGraph;
	// String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json";

	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy265.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json";

	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml"; // +++
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json";

	GraphTranslator gt;
	ObligationTranslator ot;

	public static void main(String[] args) throws Exception {
		// Create with paths
		// ObligationChecker checker = new
		// ObligationChecker("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json",
		// "Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml");

		// Create with objects
		// Graph graph =
		// Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json");
		// String yml = new String(
		// Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));

		// Graph graph =
		// Utils.readAnyGraph("Policies/ForBMC/GPMSSimplified/LawFirmPolicy.json");
		// String yml = new String(
		// Files.readAllBytes(Paths.get("Policies/ForBMC/GPMSSimplified/Obligations_simple3.yml")));

		 Graph graph = Utils.readAnyGraph("Policies/ForBMC/LeoBug2/Graph.json");
		 String yml = new String(
		 		Files.readAllBytes(Paths.get("Policies/ForBMC/LeoBug2/Obligations.yml")));
		// Graph graph =
		// Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers2.json");
		// String yml = new String(
		// Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));
		Obligation obligation = EVRParser.parse(yml);
		ObligationChecker checker = new ObligationChecker(graph, obligation);
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		long start = System.currentTimeMillis();
		checker.setBound(5);
		checker.enableSMTOutput(true);
		String precondition = "NOT(ASSIGN(?user,Chair));";

		//String postcondition = "((ASSOCIATE(BM,approve,PDSWhole) OR (NOT(ASSOCIATE(Chair,approve,PDSWhole)) OR NOT(ASSOCIATE(Chair,disapprove,PDSWhole)))) AND OBLIGATIONLABEL(obligation2,?u,approve,PDSWhole));";
		String postcondition = "OBLIGATIONLABEL(obligation4,?user,approve,PDSWhole);";

		Solution solution = checker.solveConstraint(precondition, postcondition);
		
		// ObligationChecker checker2 = new ObligationChecker(graph, obligation);

		// Solution solution21 = checker2.solveConstraint(precondition, postcondition);
		// checker.setBound(3);
		// checker.enableSMTOutput(true);

		// Solution solution = checker
		// .solveConstraint("EXISTS(AttorneysMain);");

		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC2/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC3/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC4/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC5/BMC");

		// Solution solution = checker.solveConstraint("(PERMIT(Attorneys2U, accept,
		// Case3Info) OR PERMIT(Attorneys2U, accept, Case3Info));");
		// Solution solution = checker.solveConstraint("OBLIGATIONLABEL(Attorneys2,
		// Attorneys1);");
		System.out.println(solution);
		System.out.println(checker.mapOfIDs);
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");

		SolutionSimulator ss = new SolutionSimulator(solution, graph, obligation);
		//ss.simulate();
	}

	public ObligationChecker() throws Exception {
		gt = new GraphTranslator(pathToGraph);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationTranslator(mapOfIDs);
		ot.findAllAbsentElements();
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
	}

	public ObligationChecker(String pathToGraph, String pathToObligations) throws Exception {
		this.pathToGraph = pathToGraph;
		gt = new GraphTranslator(pathToGraph);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationTranslator(mapOfIDs, pathToObligations);
		ot.findAllAbsentElements();
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
	}

	public ObligationChecker(Graph graph, Obligation obligations) throws Exception {
		gt = new GraphTranslator(graph);
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationTranslator(mapOfIDs, obligations);
		ot.findAllAbsentElements();
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
	}

	public ObligationChecker(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);
	}

	public List<String> getObligationEventVariables() {
		return obligationEventVariables;
	}

	public String generateHeadCode() throws Exception {
		String headcode = gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O,
				obligationLabels, eventMembers);
		return headcode;
	}

	List<String> getObligationLabels() {
		return obligationLabels;
	}

	public String generateTailCode(List<String> queryVARS) {
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
