package POMA.Verification.ReachabilityAnalysisGPMS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Utils;
import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class ObligationChecker extends BMC {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private Map<String, String> eventMembers = new HashMap<String, String>();

	// String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";
	// String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json";

	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy265.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json";
	// String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json";
	String pathToGraph = "Policies/ForBMC/GPMSFull/Graph.json";

	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml";
	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_conditions.yml";
	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_conditions.yml";
	// String pathToObligations =
	// "Policies/ForBMC/LawFirmSimplified/Obligations_simple1.yml";

	// String pathToObligations =
	// "Policies/ForBMC/GPMSSimplified/Obligations_simple.yml";

	String pathToObligations = "Policies/ForBMC/GPMSFull/ObligationsEdited2.yml";

	GraphTranslator gt;
	ObligationTranslator ot;

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();

		ObligationChecker checker = new ObligationChecker();

		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC2/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC3/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC4/BMC");
		// checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC5/BMC");
		checker.check();
		long end = System.currentTimeMillis();

		float sec = (end - start) / 1000F;
		System.out.println(sec + " seconds");

	}

	public ObligationChecker() throws Exception {
		gt = new GraphTranslator(pathToGraph);

		ot = new ObligationTranslator(pathToObligations);
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
		obligationsResponse.addAll(ot.getProcessedObligations());
		ot.findAllAbsentElements();
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
		obligationLabels.addAll(ot.getRuleLabels());
		eventMembers.putAll(ot.getEventMembers());
		gt.setAddedElementsFromObligations(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O);
		gt.getGraphElements();
		mapOfIDs = gt.getMapOfIDs();
		ot.setMapOfIds(mapOfIDs);
		ot.executeCustomPlugins(new GPMSPlugin(gt));
		obligationLabels.addAll(ot.plugin.getAddedObligationLables());
	}

	public ObligationChecker(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);
	}

	public String generateHeadCode() throws Exception {
		String headcode = gt.translateHeadCode(obligationLabels, eventMembers);
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
			smtlibv2Code += "(get-value (" + label + "))";
			smtlibv2Code += System.lineSeparator();
		}
		return smtlibv2Code;
	}

	public String generateAssertKCode(int k, String query, QUERY_TYPE queryType) {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += ";QUERY";
		smtlibv2Code += System.lineSeparator();

		switch (queryType) {
			case LABEL:
				smtlibv2Code += "(assert (= (" + query + " " + k + ") 1))";
				break;
			case PERMIT:
				smtlibv2Code += "(assert (member (mkTuple" + query + ") (ASSOC* " + k + ")))";
				break;
			case DENY:
				smtlibv2Code += "(assert (not (member (mkTuple" + query + ") (ASSOC* " + k + "))))";
				break;
			case PERMIT_UA_ONLY:
				smtlibv2Code += "(assert (not (= (as emptyset (Set (Tuple Int Int Int))) (join (singleton (mkTuple"
						+ query + ")) (ASSOC* " + k + "))))";
				break;
			case PERMIT_AT_ONLY:
				smtlibv2Code += "(assert (not (= (as emptyset (Set (Tuple Int Int Int))) (join (ASSOC* " + k
						+ ") (singleton (mkTuple" + query + ")) )))";
				break;
			case DENY_UA_ONLY:
				smtlibv2Code += "(assert  (= (as emptyset (Set (Tuple Int Int Int))) (join (singleton (mkTuple" + query
						+ ")) (ASSOC* " + k + ")))";
				break;
			case DENY_AT_ONLY:
				smtlibv2Code += "(assert  (= (as emptyset (Set (Tuple Int Int Int))) (join (ASSOC* " + k
						+ ") (singleton (mkTuple" + query + "))))";
				break;
			case ASSOC_UA_ONLY:
				smtlibv2Code += "(assert (not (= (as emptyset (Set (Tuple Int Int Int))) (join (singleton (mkTuple"
						+ query + ")) (ASSOC " + k + "))))";
				break;
			case ASSOC_AT_ONLY:
				smtlibv2Code += "(assert (not (= (as emptyset (Set (Tuple Int Int Int))) (join (ASSOC " + k
						+ ") (singleton (mkTuple" + query + ")) )))";
				break;
			case NO_ASSOC_UA_ONLY:
				smtlibv2Code += "(assert (= (as emptyset (Set (Tuple Int Int Int))) (join (singleton (mkTuple"
						+ query + ")) (ASSOC " + k + ")))";
				break;	
			case NO_ASSOC_AT_ONLY:
				smtlibv2Code += "(assert  (= (as emptyset (Set (Tuple Int Int Int))) (join (ASSOC " + k
						+ ") (singleton (mkTuple" + query + "))))";
				break;				
			case UO:
				smtlibv2Code += "(assert (member (mkTuple " + query + ") (ASSIGN* " + (k + 1) + ")))";
				break;
			case UAOA:
				smtlibv2Code += "(assert (member (mkTuple " + query + ") (ASSIGN* " + k + ")))";
				break;
			case UO_explicit:
				smtlibv2Code += "(assert (member (mkTuple " + query + ") (ASSIGN " + (k + 1) + ")))";
				break;
			case UAOA_explicit:
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

	public String generateIterationCode(int k) {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());

		sb.append(
				";--------------------------------------------------------------------------------------------------------------------\r\n"
						+ ";STEP" + k);

		sb.append(System.lineSeparator());
		sb.append(ot.translateObligationEvents(k));
		// sb.append(System.lineSeparator());
		// sb.append(ot.translateGraphIntersection(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processActions(k));
		sb.append(System.lineSeparator());
		sb.append(ot.processCustomStateVariables(k));
		sb.append(System.lineSeparator());
		sb.append(gt.translateARCheck(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

}
