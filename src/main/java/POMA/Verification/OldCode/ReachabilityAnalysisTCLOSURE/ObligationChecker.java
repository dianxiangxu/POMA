package POMA.Verification.OldCode.ReachabilityAnalysisTCLOSURE;

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
	private QUERY_TYPE queryType;


	public void setQueryType(QUERY_TYPE query_type) {
		this.queryType = queryType;
	}

	public enum QUERY_TYPE {
		 OBLIGATION, ACCESS_REQUEST, OOA, OAOA, UUA, UAUA
	}

	 String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";
	//String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json";


	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy265.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json";
	 //String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json";



	GraphTranslator gt = new GraphTranslator(pathToGraph);
	ObligationTranslator ot;

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		

		ObligationChecker checker = new ObligationChecker();

		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1Assignments/BMC");
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

	public String generateAssertKCode(int k, String query) {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += ";QUERY";
		switch(queryType){
			case OBLIGATION:
				smtlibv2Code += System.lineSeparator();
				smtlibv2Code += "(assert (= (" + query + " " + k + ") 1))";
				smtlibv2Code += System.lineSeparator();
				smtlibv2Code += System.lineSeparator();
			break;
			case ACCESS_REQUEST:
				smtlibv2Code += System.lineSeparator();
				//smtlibv2Code += "(assert (= (" + query + " " + k + ") 1))";
				smtlibv2Code += "(assert (member (mkTuple" +query+ ") (AccessRights " + k + ")))";
				// smtlibv2Code += "(assert (member (mkTuple "+Attorneys1ID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";
				smtlibv2Code += System.lineSeparator();
				smtlibv2Code += System.lineSeparator();
			break;
			case UUA:
			break;
			case UAUA:
			break;
			case OOA:
			break;
			case OAOA:
			break;
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
		sb.append(ot.translateObligationEvents(k));
		sb.append(System.lineSeparator());
		sb.append(ot.translateGraphIntersection(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processActions(k));
		sb.append(System.lineSeparator());
		sb.append(gt.translateARCheck(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}



}
