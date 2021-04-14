package POMA.Verification.BMC;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.TranslationWithSets.AssociationRelation;

public class ObligationChecker extends BMC {

	List<String> obligationsResponse = new ArrayList<String>();
	List<String> obligationsEvents = new ArrayList<String>();
	List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	List<String> listOfAddedNodesOA_O= new ArrayList<String>();
	List<String> obligationLabels = new ArrayList<String>();

	public ObligationChecker() throws Exception {
		ObligationTranslator ot = new ObligationTranslator();
		ot.processObligations();		
		obligationsEvents.addAll(ot.getProcessedObligationsEvents());
		obligationsResponse.addAll(ot.getProcessedObligations());
		listOfAddedAssociations.addAll(ot.getListOfAddedAssociations());
		listOfAddedNodesUA_U.addAll(ot.getListOfCreatedNodesUA_U());
		listOfAddedNodesOA_O.addAll(ot.getListOfCreatedNodesOA_O());
        obligationLabels.addAll(ot.getRuleLabels());
	}

	public ObligationChecker(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);		
	}

	public String generateHeadCode() throws Exception {
		String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";

		GraphTranslator gt = new GraphTranslator(pathToGraph);
		return gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, 
				obligationLabels);
	
	}

	public String generateAssertKCode(int k) {		
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code +=";PROPERTY";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code +="(assert (member (mkTuple LeadAttorneysU approve Case3Info) (AccessRights "+k+")))";
	//	smtlibv2Code +="(assert (member (mkTuple LeadAttorneysU approve Case3Info) (AccessRights "+k+")))";

		//smtlibv2Code +="(assert (member (mkTuple \"0\" \""+obligationsEvents.get(k)+"\" \"0\") (AccessRightsOnlyAR "+(k-1)+")))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {

		String smtlibv2Code = System.lineSeparator();
		 smtlibv2Code +=";--------------------------------------------------------------------------------------------------------------------\r\n"
		 		+ ";STEP"+k;
			smtlibv2Code += System.lineSeparator();

	   smtlibv2Code +="(assert \r\n"
				+ "(xor \r\n"
				+ "(= (obligation1 "+(k-1)+") 0) \r\n"
				+ "(and (member (mkTuple Attorneys accept Case3Info) (AccessRights "+(k-1)+")) (= (obligation1 "+(k-1)+") 1))\r\n"
				+ ")\r\n"
				+ ")				\r\n"
				+ "\r\n"
				+ "(assert \r\n"
				+ "(xor \r\n"
				+ "(= (obligation2 "+(k-1)+") 0) \r\n"
				+ "(and (member (mkTuple Attorneys1 accept Case3Info) (AccessRights "+(k-1)+")) (= (obligation2 "+(k-1)+") 1))\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(assert \r\n"
				+ "(xor \r\n"
				+ "(= (obligation3 "+(k-1)+") 0) \r\n"
				+ "(and (member (mkTuple Attorneys2 accept Case3Info) (AccessRights "+(k-1)+")) (= (obligation3 "+(k-1)+") 1))\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(assert \r\n"
				+ "(xor \r\n"
				+ "(= (obligation4 "+(k-1)+") 0) \r\n"
				+ "(and (member (mkTuple Attorneys3 accept Case3Info) (AccessRights "+(k-1)+")) (= (obligation4 "+(k-1)+") 1))\r\n"
				+ ")\r\n"
				+ ")	\r\n"
				+ "			\r\n"
				+ "\r\n"
				+ "				\r\n"
				+ "\r\n"
				+ "(declare-fun GRAPH"+k+" () (Set (Tuple Int Int)))\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "(declare-fun OldGRAPH"+k+" () (Set (Tuple Int Int)))\r\n"
				+ "(assert (= OldGRAPH"+k+" (intersection (Tclosure "+(k-1)+") GRAPH"+(k-1)+"))) \r\n"
				+ "\r\n"
				+ "(assert \r\n"
				+ "(xor\r\n"
				+ "\r\n"
				+ ";----------------------------------------------------------------------ADD ASSIGNMENTS ACTIONS\r\n"
				+ "(and  (= (obligation1 "+(k-1)+") 1)\r\n"
				+ "(xor \r\n"
				+ "(= GRAPH"+k+" (setminus (union (singleton(mkTuple Attorneys1 Attorneys)) OldGRAPH"+k+") (singleton(mkTuple Attorneys3 Attorneys)))) \r\n"
				+ "(= GRAPH"+k+" OldGRAPH"+k+")\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(and (= (obligation2 "+(k-1)+") 1)\r\n"
				+ "(xor\r\n"
				+ "(= GRAPH"+k+" (union (singleton(mkTuple Attorneys2 Attorneys1)) OldGRAPH"+k+"))\r\n"
				+ "(= GRAPH"+k+" OldGRAPH"+k+")\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(and (= (obligation3 "+(k-1)+") 1)\r\n"
				+ "(xor\r\n"
				+ "(= GRAPH"+k+" (union (singleton(mkTuple Attorneys3 Attorneys2)) OldGRAPH"+k+"))\r\n"
				+ "(= GRAPH"+k+" OldGRAPH"+k+")\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "(and (= (obligation4 "+(k-1)+") 1)\r\n"
				+ "(xor\r\n"
				+ "(= GRAPH"+k+" (union (singleton(mkTuple C-Suit Attorneys)) OldGRAPH"+k+"))\r\n"
				+ "(= GRAPH"+k+" OldGRAPH"+k+")\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "(= GRAPH"+k+" OldGRAPH"+k+")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(assert \r\n"
				+ "(xor\r\n"
				+ "\r\n"
				+ "(and (= (obligation4 "+(k-1)+") 1) \r\n"
				+ "(xor\r\n"
				+ "(= (Associations "+k+") (union (singleton(mkTuple LeadAttorneys approve Case3)) (Associations "+(k-1)+")))\r\n"
				+ "(= (Associations "+k+") (Associations "+(k-1)+"))\r\n"
				+ ")\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "(= (Associations "+k+") (Associations "+(k-1)+"))\r\n"
				+ ")\r\n"
				+ ")";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (Tclosure "+k+") (tclosure GRAPH"+k+")))";

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (UA_U_Reachability "+k+") (join SetToCheckUA (Tclosure "+k+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AT_Reachability "+k+") (join SetToCheckAT (Tclosure "+k+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AssociationsForUA "+k+") (join (UA_U_Reachability "+k+") (Associations "+k+")) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AccessRights "+k+") (join (AssociationsForUA "+k+") (transpose (AT_Reachability "+k+"))) ))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	
	public static void main(String[] args) throws Exception {
		ObligationChecker checker = new ObligationChecker();
		
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC");
		checker.check();
		//String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";
	}

}
