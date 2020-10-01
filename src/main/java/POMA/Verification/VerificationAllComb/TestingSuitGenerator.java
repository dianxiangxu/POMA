package POMA.Verification.VerificationAllComb;

public class TestingSuitGenerator {

	public String generateTest(String subject, String accessRight, String target, String counter) {
		String finalString = "";
		finalString+="(echo \""+subject + " "+target +"\")";
		finalString+=System.lineSeparator();
		finalString+="(push 1)";
		finalString+=System.lineSeparator();

		finalString+="(declare-fun ar () String)";
		finalString+=System.lineSeparator();
		finalString+="(assert (= \""+accessRight+"\" ar))";
		finalString+=System.lineSeparator();		
		finalString+="(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \""+subject+"\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \""+target+"\" (AT relationAssociation)) Tclosure))))";
		finalString+=System.lineSeparator();
		finalString+="(check-sat)";
		finalString+=System.lineSeparator();
		finalString+="(get-value (ar))";
		finalString+=System.lineSeparator();	
		finalString+="(pop 1)";
		finalString+=System.lineSeparator();	

		return finalString;
	}

}
