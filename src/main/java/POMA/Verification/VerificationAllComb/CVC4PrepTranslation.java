package POMA.Verification.VerificationAllComb;

public class CVC4PrepTranslation {

	private String setCVC4LogicAllSupported() {
		return "(set-logic ALL_SUPPORTED)";
	}

	private String setCVC4OptionUnsatCores() {
		return "(set-option :produce-unsat-cores true)";
	}

	private String setCVC4OptionModels() {
		return "(set-option :produce-models true)";
	}

	private String setCVC4OptionAssignments() {
		return "(set-option :produce-assignments true)";
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

	private String declareCVC4AssociationDatatype() {
		return "(declare-datatypes ((association 0))" + System.lineSeparator()
				+ "   (((rec (UA String) (access_rights (Set String)) (AT String)))))";
	}

//	public String assertTClosures() {
//		return "(assert (= UA_tclosure (tclosure UA_containment)))" + System.lineSeparator()
//				+ "(assert (= OA_tclosure (tclosure OA_containment)))";
//	}
	public String assertTClosures() {
		return "(assert (= Tclosure (tclosure Containment)))";
	}

	public String declareSetAssociation() {
		return "(define-sort SetAssociation () (Set association)) (declare-const setAssociation SetAssociation)";
	}

	@Override
	public String toString() {

		return setCVC4LogicAllSupported() + System.lineSeparator() + setCVC4OptionUnsatCores() + System.lineSeparator()
				+ setCVC4OptionModels() + System.lineSeparator() + setCVC4OptionAssignments() + System.lineSeparator()
				+ declareCVC4Containment() + System.lineSeparator() + declareCVC4TClosure() + System.lineSeparator()
				+ declareCVC4AssociationDatatype() + System.lineSeparator() + declareSetAssociation()+ System.lineSeparator();
	}

}
