package POMA.Verification.VerificationWithSets;

public class CVC4PrepTranslation {

	private String setCVC4LogicAllSupported() {
		return "(set-logic ALL_SUPPORTED)";
	}

	private String setCVC4OptionModels() {
		return "(set-option :produce-models true)";
	}

//	private String declareCVC4Containment() {
//		return "(declare-fun UA_containment () (Set (Tuple String String)))" + System.lineSeparator()
//				+ "(declare-fun OA_containment () (Set (Tuple String String)))";
//	}

	private String declareCVC4Containment() {
		return "(declare-fun Containment () (Set (Tuple String String)))";
	}

//	private String declareCVC4TClosure() {
//		return "(declare-fun UA_tclosure () (Set (Tuple String String)))" + System.lineSeparator()
//				+ "(declare-fun OA_tclosure () (Set (Tuple String String)))";
//	}

	private String declareCVC4TClosure() {
		return "(declare-fun Tclosure () (Set (Tuple String String)))";
	}

	public String assertTClosures() {
		return "(assert (= Tclosure (tclosure Containment)))";
	}

	public String declareAssociation() {
		return "(declare-fun Associations () (Set (Tuple String String String)))";
	}

	public String declareReachabilities() {
		return "(declare-fun UA_U_Reachability () (Set (Tuple String String)))" + System.lineSeparator()
				+ "(declare-fun AT_Reachability () (Set (Tuple String String)))";
	}

	@Override
	public String toString() {

		return setCVC4LogicAllSupported() + System.lineSeparator() + setCVC4OptionModels() + System.lineSeparator()
				+ declareCVC4Containment() + System.lineSeparator() + declareCVC4TClosure() + System.lineSeparator()
				+ declareAssociation() + System.lineSeparator()+declareReachabilities() + System.lineSeparator();
	}

}
