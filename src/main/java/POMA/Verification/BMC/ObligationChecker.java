package POMA.Verification.BMC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import gov.nist.csd.pm.pip.obligations.evr.EVRException;

public class ObligationChecker extends BMC {

	List<String> obligationsResponse = new ArrayList<String>();
	List<String> obligationsEvents = new ArrayList<String>();

	public ObligationChecker() throws Exception {
		ObligationTranslator ot = new ObligationTranslator();
		ot.processObligations();		
		obligationsEvents.addAll(ot.getProcessedObligationsEvents());
		obligationsResponse.addAll(ot.getProcessedObligations());
	}

	public ObligationChecker(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);

		
	}

	public String generateHeadCode() {
		String smtlibv2Code = "";
		smtlibv2Code += "(set-logic ALL_SUPPORTED)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set-option :produce-models true)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun Attorneys () Int)\r\n"
				+ "(declare-fun Attorneys1 () Int)\r\n"
				+ "(declare-fun Attorneys2 () Int)\r\n"
				+ "(declare-fun Attorneys3 () Int)\r\n"
				+ "\r\n"
				+ "(declare-fun LeadAttorneys () Int)\r\n"
				+ "(declare-fun C-Suit () Int)\r\n"
				+ "(declare-fun CasePolicy () Int)\r\n"
				+ "(declare-fun Case3 () Int)\r\n"
				+ "(declare-fun Case3Info () Int)\r\n"
				+ "\r\n"
				+ "(declare-fun withdraw () Int)\r\n"
				+ "(declare-fun refuse () Int)\r\n"
				+ "(declare-fun accept () Int)\r\n"
				+ "(declare-fun disapprove () Int)\r\n"
				+ "(declare-fun approve () Int)\r\n"
				+ "(declare-fun finalApprove () Int)\r\n"
				+ "\r\n"
				+ "(declare-fun Signatures () Int)\r\n"
				+ "(declare-fun AttorneysMain () Int)\r\n"
				+ "(declare-fun LeadAttorneysU () Int)\r\n"
				+ "\r\n"
				+ "(assert (= AttorneysMain 0))\r\n"
				+ "(assert (= Attorneys 1))\r\n"
				+ "(assert (= Attorneys1 2))\r\n"
				+ "(assert (= Attorneys2 3))\r\n"
				+ "(assert (= Attorneys3 4))\r\n"
				+ "\r\n"
				+ "(assert (= LeadAttorneys 5))\r\n"
				+ "(assert (= LeadAttorneysU 6))\r\n"
				+ "\r\n"
				+ "(assert (= C-Suit 7))\r\n"
				+ "(assert (= CasePolicy 8))\r\n"
				+ "(assert (= Case3 9))\r\n"
				+ "(assert (= Case3Info 10))\r\n"
				+ "\r\n"
				+ "(assert (= withdraw 11))\r\n"
				+ "(assert (= refuse 12))\r\n"
				+ "(assert (= accept 13))\r\n"
				+ "(assert (= disapprove 14))\r\n"
				+ "(assert (= approve 15))\r\n"
				+ "(assert (= finalApprove 16))";
		
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun Associations (Int) (Set (Tuple Int Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckUA () (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckAT () (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckUA (insert (mkTuple Attorneys Attorneys)\r\n"
				+ "(mkTuple Attorneys1 Attorneys1) \r\n"
				+ "(mkTuple Attorneys2 Attorneys2) \r\n"
				+ "(mkTuple Attorneys3 Attorneys3) \r\n"
				+ "(mkTuple LeadAttorneys LeadAttorneys) \r\n"
				+ "(mkTuple LeadAttorneysU LeadAttorneysU) \r\n"
				+ "(singleton (mkTuple C-Suit C-Suit)))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckAT (insert (mkTuple Case3Info Case3Info)  (singleton (mkTuple Case3 Case3)))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun GRAPH"+0+" () (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun Tclosure(Int) (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AssociationsForUA (Int) (Set (Tuple Int Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))";
		smtlibv2Code += System.lineSeparator();

		smtlibv2Code += "(assert (= (Associations 0)  (insert(mkTuple LeadAttorneys disapprove Case3) ;+\r\n"
				+ "(mkTuple LeadAttorneys withdraw Case3) ;+\r\n"
				+ "(mkTuple Attorneys refuse Case3) \r\n"
				+ "(singleton (mkTuple AttorneysMain accept Case3)))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= GRAPH"+0+" (insert (mkTuple Case3 CasePolicy) (mkTuple Attorneys AttorneysMain)  ;19 good, 20 not\r\n"
				+ ";(mkTuple C-Suit LeadAttorneys) \r\n"
				+ ";(mkTuple C-Suit C-Suit)\r\n"
				+ "(mkTuple Case3Info Case3)\r\n"
				+ "(mkTuple Attorneys2 CasePolicy)\r\n"
				+ "(mkTuple Attorneys3 CasePolicy)\r\n"
				+ "(mkTuple Attorneys1 CasePolicy)\r\n"
				+ "(mkTuple LeadAttorneys CasePolicy)\r\n"
				+ "(mkTuple LeadAttorneys Attorneys)\r\n"
				+ "(mkTuple LeadAttorneysU LeadAttorneys)\r\n"
				+ "(singleton (mkTuple AttorneysMain CasePolicy)))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (Tclosure "+0+") (tclosure GRAPH"+0+")))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (UA_U_Reachability "+0+") (join SetToCheckUA (Tclosure "+0+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AT_Reachability "+0+") (join SetToCheckAT (Tclosure "+0+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AssociationsForUA "+0+") (join (UA_U_Reachability "+0+") (Associations "+0+")) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AccessRights "+0+") (join (AssociationsForUA "+0+") (transpose (AT_Reachability "+0+"))) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun obligation1 (Int) Int)\r\n"
				+ "(declare-fun obligation2 (Int) Int)\r\n"
				+ "(declare-fun obligation3 (Int) Int)\r\n"
				+ "(declare-fun obligation4 (Int) Int)";
		return smtlibv2Code;
	}

	public String generateAssertKCode(int k) {		
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code +=";PROPERTY";
		smtlibv2Code +="(assert (member (mkTuple Attorneys accept Case3Info) (AccessRights "+k+")))";
	//	smtlibv2Code +="(assert (member (mkTuple LeadAttorneysU approve Case3Info) (AccessRights "+k+")))";

		//smtlibv2Code +="(assert (member (mkTuple \"0\" \""+obligationsEvents.get(k)+"\" \"0\") (AccessRightsOnlyAR "+(k-1)+")))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {

		//String smtlibv2Code = "(assert (= (Tclosure "+k+") (tclosure (GRAPH "+k+"))))";
	//	smtlibv2Code += System.lineSeparator();
//		smtlibv2Code += "(assert (or";
//		for(int i = 1; i < obligationsResponse.size(); i++)
//		{
//			String obligation = obligationsResponse.get(i);
//		if(obligation.charAt(0) == '1') {
//			smtlibv2Code += "(and (= (GRAPH "+k+") (union" + obligation.substring(1)+ "(GRAPH "+(k-1)+"))) (= (Associations "+k+") (Associations "+(k-1)+")))";
//		}		
//		else if(obligation.charAt(0) == '2')  {
//			smtlibv2Code += "(and (= (GRAPH "+k+") (GRAPH "+(k-1)+")) (= (Associations "+k+") (union " + obligation.substring(1) + " (Associations "+(k-1)+")))";
//
//		}			
//		
		//}
		//smtlibv2Code +="))\n";
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
	}

}
