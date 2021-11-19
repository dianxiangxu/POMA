package POMA.Verification.ReachabilityAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Verification.ReachabilityAnalysis.model.Solution;
import POMA.Verification.TranslationWithSets.AssociationRelation;


public class ObligationChecker extends BMC {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private Map<String, String> eventMembers = new HashMap<String, String>();
	private List<String> obligationEventVariables = new ArrayList<String>();

	String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyUsers.json"; // +++++++
	// String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json";

	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy265.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json";

	GraphTranslator gt = new GraphTranslator(pathToGraph);
	ObligationTranslator ot;

	public static void main(String[] args) throws Exception {

		

		ObligationChecker checker = new ObligationChecker();

		long start = System.currentTimeMillis();
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC2/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC3/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC4/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC5/BMC");

		//checker.solveConstraint("(PERMIT(Attorneys2U, accept, Case3Info) OR PERMIT(Attorneys2U, accept, Case3Info));");


		//Solution solution = checker.solveConstraint("OBLIGATIONLABEL(Attorneys2, Attorneys1);");
		Solution solution = checker.solveConstraint("OBLIGATIONLABEL(obligation3);");

		//System.out.println(checker.mapOfIDs);


		long end = System.currentTimeMillis();

		float sec = (end - start) / 1000F;
		System.out.println("The job took: "+sec + " seconds");

	}
	

	public ObligationChecker() throws Exception {
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

	public String generateTailCode() {
		obligationEventVariables.addAll(ot.getObligationEventVariables());
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += "(check-sat)";
		smtlibv2Code += System.lineSeparator();
		//smtlibv2Code += "(get-model)";
		for (String label : obligationLabels) {
			smtlibv2Code += "(get-value (" + label + "))";
			smtlibv2Code += System.lineSeparator();
		}
		for (String label : obligationEventVariables) {
			smtlibv2Code += "(get-value (" + label + "))";
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
