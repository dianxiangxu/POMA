package POMA.Verification.ReachabilityAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Verification.TranslationWithSets.AssociationRelation;
import POMA.Verification.ReachabilityAnalysis.fol.model.*;
import POMA.Verification.ReachabilityAnalysis.fol.model.predicates.PermitPredicate;
import POMA.Verification.ReachabilityAnalysis.fol.model.terms.*;
import POMA.Verification.ReachabilityAnalysis.fol.parser.FOLGrammar;
import POMA.Verification.ReachabilityAnalysis.models.AccessRequest;

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

	public String generateAssertKCode(int k, String query, QUERY_TYPE queryType, AccessRequest... accessRequests) {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += ";QUERY";
		smtlibv2Code += System.lineSeparator();
		switch (queryType) {
		case LABEL:
			smtlibv2Code += "(assert (= (" + query + " " + k + ") true))";
			break;
		case PERMIT:
			smtlibv2Code += processPermitQuery(accessRequests[0], k, false);
			break;
		case ASSOC:
			smtlibv2Code += processAssocQuery(accessRequests[0], k, false);
			break;
		case DENY:
			smtlibv2Code += processPermitQuery(accessRequests[0], k, true);
			break;
		case NO_ASSOC:
			smtlibv2Code += processAssocQuery(accessRequests[0], k, true);;
			break;
		case ASSIGN:
			smtlibv2Code += "(assert (member (mkTuple " + query + ") (ASSIGN* " + (k + 1) + ")))";
			break;
		case ASSIGN_explicit:
			smtlibv2Code += "(assert (member (mkTuple " + query + ") (ASSIGN " + k + ")))";
			break;
		case HIERARCHY:
			String[] query_reverse_array = query.split(" ");
			String query_reverse = " " + query_reverse_array[2] + " " + query_reverse_array[1];
			smtlibv2Code += "(assert (or (member (mkTuple " + query + ") (ASSIGN* " + k + "))(member (mkTuple "
					+ query_reverse + ") (ASSIGN* " + k + "))))";
			break;
		case NOT_HIERARCHY:
			String[] query_reverse_array_negation = query.split(" ");
			String query_reverse_negation = " " + query_reverse_array_negation[2] + " "
					+ query_reverse_array_negation[1];
			smtlibv2Code += "(assert (not (or (member (mkTuple " + query + ") (ASSIGN* " + k + "))(member (mkTuple "
					+ query_reverse_negation + ") (ASSIGN* " + k + ")))))";
			break;
		}
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	private String processPermitQuery(AccessRequest accessRequest, int k, boolean isDeny) {
		String smtlibv2Code = "";
		Integer s = accessRequest.getS();
		Integer accessright = accessRequest.getAr();
		Integer t = accessRequest.getT();
		smtlibv2Code += "(declare-fun query1U" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1UO" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1UA" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1AT" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1ar" + k + " () Int)";

		String userSpec = s != null ? "(member (mkTuple  " + s + " query1UA" + k
				+ ") (ASSIGN* " + (k + 1) + "))"
				: "(member (mkTuple  " + " query1U" + k  + " query1UA" + k + ") (ASSIGN* " + (k + 1) + "))";
		String targetSpec = t != null ? "(member (mkTuple  " + t + " query1AT" + k
				+ ") (ASSIGN* " + (k + 1) + "))"
				: "(member (mkTuple  " + "query1UO" + k + " query1AT" + k + ") (ASSIGN* " + (k + 1) + "))";
		String arSpec = accessright != null ? "(assert (= query1ar" + k + " " + accessright + "))" : "";
		smtlibv2Code += arSpec;
		smtlibv2Code += System.lineSeparator();

		smtlibv2Code += isDeny == false ? "(assert (and" + userSpec + "(member (mkTuple query1UA" + k + " " + "query1ar" + k  
				+ " query1AT" + k
				+ ") (ASSOC " + (k + 1) + "))" + targetSpec + "))" : "(assert (not (and" + userSpec + "(member (mkTuple query1UA" + k + " query1ar" + k
						+ " query1AT" + k + ") (ASSOC " + (k + 1) + "))" + targetSpec + ")))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	private String processAssocQuery(AccessRequest accessRequest, int k, boolean isNegated) {
		String smtlibv2Code = "";
		Integer s = accessRequest.getS();
		Integer accessright = accessRequest.getAr();
		Integer t = accessRequest.getT();
		smtlibv2Code += "(declare-fun query1UA" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1AT" + k + " () Int)";
		smtlibv2Code += "(declare-fun query1ar" + k + " () Int)";

		String uaSpec = s != null ? "(assert (= query1UA" + k + " " + s + "))" : "";
		String atSpec = t != null ? "(assert (= query1AT" + k + " " + t + "))" : "";
		String arSpec = accessright != null ? "(assert (= query1ar" + k + " " + accessright + "))" : "";

		smtlibv2Code += uaSpec;
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += atSpec;
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += arSpec;
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += !isNegated ? "(assert (member (mkTuple query1UA" + k
				+ " query1ar" + k + " query1AT" + k
				+ ") (ASSOC " + (k + 1) + ")))" : "(assert  (not (member (mkTuple query1UA" + k
						+  " query1ar" + k 
						+ " query1AT" + k + ") (ASSOC " + (k + 1) + "))))";
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
		sb.append(ot.translateObligationPreconditions(k));
		// sb.append(System.lineSeparator());
		// sb.append(ot.translateGraphIntersection(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processEffects(k));
		// sb.append(System.lineSeparator());
		// sb.append(gt.translateARCheck(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
