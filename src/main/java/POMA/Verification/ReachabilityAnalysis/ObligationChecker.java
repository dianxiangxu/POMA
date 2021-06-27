package POMA.Verification.ReachabilityAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Verification.TranslationWithSets.AssociationRelation;

public class ObligationChecker extends BMC {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private HashMap<String, Integer> mapOfIDs;
	private Map<String, String> eventMembers = new HashMap<String, String>();

	// String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";
	//String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json";


	 String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy265.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json";
	 //String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json";



	GraphTranslator gt = new GraphTranslator(pathToGraph);
	ObligationTranslator ot;

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		

		ObligationChecker checker = new ObligationChecker();

		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		//checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC2/BMC");
		//checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC3/BMC");
		//checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC4/BMC");
		//checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC5/BMC");

		checker.check();
		System.out.println(checker.mapOfIDs);
		long end = System.currentTimeMillis();

		float sec = (end - start) / 1000F;
		System.out.println(sec + " seconds");

	}

	public ObligationChecker() throws Exception {
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationTranslator(mapOfIDs);
		ot.findAllAbsentElements();

		// ot.processObligations();
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

	public String generateHeadCode() throws Exception {
		String headcode = gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O,
				obligationLabels, eventMembers);
		return headcode;
	}

	List<String> getObligationLabels() {
		return obligationLabels;
	}

	public String generateTailCode() {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += "(check-sat)";
		smtlibv2Code += System.lineSeparator();
		for (String label : obligationLabels) {
			smtlibv2Code += "(get-value ("+label + "))";
			smtlibv2Code += System.lineSeparator();
		}
		return smtlibv2Code;
	}

	public String generateAssertKCode(int k, String obligation_label) {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += ";PROPERTY";
		smtlibv2Code += System.lineSeparator();
		// smtlibv2Code +="(assert (member (mkTuple LeadAttorneysU approve Case3Info)
		// (AccessRights "+k+")))";

		// smtlibv2Code +="(assert (member (mkTuple \"0\"
		// \""+obligationsEvents.get(k)+"\" \"0\") (AccessRightsOnlyAR "+(k-1)+")))";

		smtlibv2Code += "(assert (= (" + obligation_label + " " + k + ") 1))";

		// smtlibv2Code += "(assert (= (approve_case "+k+") 1))";
		// smtlibv2Code += "(assert (= (obligation4 " + k + ") 1))";

		smtlibv2Code += System.lineSeparator();

		// smtlibv2Code += "(assert (member (mkTuple "+AttorneysID+" "+acceptID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+Attorneys1ID+" "+acceptID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+Attorneys2ID+" "+acceptID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+Attorneys3ID+" "+acceptID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+LeadAttorneysUID+" "+approveID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+CSuitID+" "+acceptID+"
		// "+Case3InfoID+") (AccessRights " + k + ")))";

		smtlibv2Code += System.lineSeparator();

		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());

		sb.append(
				";--------------------------------------------------------------------------------------------------------------------\r\n"
						+ ";STEP" + k);

		sb.append(System.lineSeparator());
		sb.append(ot.translateObligationEvents(k));
		//sb.append(System.lineSeparator());
		//sb.append(ot.translateGraphIntersection(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processActions(k));
		sb.append(System.lineSeparator());
		sb.append(gt.translateARCheckNoAssignments(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}



}
