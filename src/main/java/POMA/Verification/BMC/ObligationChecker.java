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

	public ObligationChecker(int amount) throws Exception {
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
		smtlibv2Code += "(declare-fun Associations (Int) (Set (Tuple String String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckUA () (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckAT () (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckUA (insert (mkTuple \"UA_test1\" \"UA_test1\") (mkTuple \"UA1_1\" \"UA1_1\") (mkTuple \"UA2_2\" \"UA2_2\") (singleton (mkTuple \"UA3_1_2\" \"UA3_1_2\")))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckAT (insert (mkTuple \"Container1\" \"Container1\")  (singleton (mkTuple \"Container2\" \"Container2\")))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun Containment (Int) (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun Tclosure(Int) (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AssociationsForUA (Int) (Set (Tuple String String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun UA_U_Reachability (Int) (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AT_Reachability (Int) (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AssignmentAdded (Int) (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun AssociationAdded (Int) (Set (Tuple String String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun FinalJoin(Int) (Set (Tuple String String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckUAOnlyAR () (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun FinalJoinOnlyAR (Int) (Set (Tuple String String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun SetToCheckATOnlyAR () (Set (Tuple String String)))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckUAOnlyAR (insert (mkTuple \"UA_test1\" \"0\") (mkTuple \"UA1_1\" \"0\") (mkTuple \"UA2_2\" \"0\") (singleton (mkTuple \"UA3_1_2\" \"0\")))))\n";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= SetToCheckATOnlyAR (insert (mkTuple \"Container1\" \"0\")  (singleton (mkTuple \"Container2\" \"0\")))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (Tclosure "+0+") (tclosure (Containment "+0+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (Associations "+0+") (insert (mkTuple \"UA_test1\" \"test1\" \"Container1\") (mkTuple \"UA_test1\" \"test3\" \"Container1\") (singleton (mkTuple \"UA_test1\" \"test2\" \"Container2\")))))";
		//smtlibv2Code += "(assert (= (Associations "+0+") (insert (mkTuple \"UA_test1\" \"test3\" \"Container1\") (singleton (mkTuple \"UA_test1\" \"test2\" \"Container2\")))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (Containment "+0+") (insert (mkTuple \"UA1_1\" \"UA1_1\") (mkTuple \"UA_test1\" \"UA_test1\")(mkTuple \"UA1_1\" \"Container1\") (mkTuple \"Container1\" \"Container1\") (mkTuple \"Container1\" \"PC1\") (mkTuple \"UA_test1\" \"PC1\")(mkTuple \"Container2\" \"Container2\") (mkTuple \"UA2_2\" \"Container2\") (mkTuple \"UA2_2\" \"UA2_2\") (mkTuple \"UA3_1_2\" \"Container2\") (mkTuple \"UA3_1_2\" \"UA3_1_2\") (mkTuple \"UA3_1_2\" \"Container1\") (mkTuple \"Container1\" \"PC1\") (singleton (mkTuple \"Container2\" \"PC1\")))))\n";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (UA_U_Reachability "+0+") (join SetToCheckUA (Tclosure "+0+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AT_Reachability "+0+") (join SetToCheckAT (Tclosure "+0+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AssociationsForUA "+0+") (join (UA_U_Reachability "+0+") (Associations "+0+")) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (FinalJoin "+0+") (join (AssociationsForUA "+0+") (transpose (AT_Reachability "+0+"))) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (FinalJoinOnlyAR "+0+") (join (transpose (join (FinalJoin "+0+")  SetToCheckATOnlyAR)) SetToCheckUAOnlyAR)))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateAssertKCode(int k, int randomNum) {		
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code +="(assert (member (mkTuple \"UA1_1\" \"test1\" \"Container1\") (FinalJoin "+k+")))";
		//smtlibv2Code +="(assert (member (mkTuple \"0\" \""+obligationsEvents.get(k)+"\" \"0\") (FinalJoinOnlyAR "+(k-1)+")))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateIterationCode(int k, int randomNum) {

		String smtlibv2Code = "(assert (= (Tclosure "+k+") (tclosure (Containment "+k+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (or";
		for(int i = 1; i < obligationsResponse.size(); i++)
		{
			String obligation = obligationsResponse.get(i);
		if(obligation.charAt(0) == '1') {
			smtlibv2Code += "(and (= (Containment "+k+") (union" + obligation.substring(1)+ "(Containment "+(k-1)+"))) (= (Associations "+k+") (Associations "+(k-1)+")))";
		}		
		else if(obligation.charAt(0) == '2')  {
			smtlibv2Code += "(and (= (Containment "+k+") (Containment "+(k-1)+")) (= (Associations "+k+") (union " + obligation.substring(1) + " (Associations "+(k-1)+")))";

		}			
		
		}
		smtlibv2Code +="))\n";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (UA_U_Reachability "+k+") (join SetToCheckUA (Tclosure "+k+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AT_Reachability "+k+") (join SetToCheckAT (Tclosure "+k+"))))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (AssociationsForUA "+k+") (join (UA_U_Reachability "+k+") (Associations "+k+")) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (FinalJoin "+k+") (join (AssociationsForUA "+k+") (transpose (AT_Reachability "+k+"))) ))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (FinalJoinOnlyAR "+k+") (join (transpose (join (FinalJoin "+k+")  SetToCheckATOnlyAR)) SetToCheckUAOnlyAR)))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	
	public static void main(String[] args) throws Exception {
		ObligationChecker checker = new ObligationChecker(115);
		checker.setSMTCodePath("SMTLIBv2Files/SMTLIB2Input/BMCFiles/BMC");
		checker.check();
	}

}
