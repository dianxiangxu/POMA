package POMA.Verification.BMC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.TranslationWithSets.AssociationRelation;

public class ObligationChecker extends BMC {

	private List<String> obligationsResponse = new ArrayList<String>();
	private List<String> obligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();
	private List<String> listOfAddedNodesUA_U = new ArrayList<String>();
	private List<String> listOfAddedNodesOA_O= new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();
	private HashMap<String, Integer> mapOfIDs;

	String pathToGraph = "Policies/ForBMC/LawFirmSimplified/CasePolicy.json";
	//String pathToGraph = "Policies/ForBMC/GPMSSimplified/EditingPolicy.json";
	GraphTranslator gt = new GraphTranslator(pathToGraph);
	ObligationTranslator ot;

	public ObligationChecker() throws Exception {
		mapOfIDs = gt.getMapOfIDs();
		ot = new ObligationTranslator(mapOfIDs);
		ot.findAllAbsentElements();
		//ot.processObligations();		
		obligationsEvents.addAll(ot.getProcessedObligationsEventLabels());
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
	
		String headcode = gt.translateHeadCode(listOfAddedAssociations, listOfAddedNodesUA_U, listOfAddedNodesOA_O, 
				obligationLabels);
		return headcode;
	
	}

	public String generateAssertKCode(int k) {		
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code +=";PROPERTY";
		smtlibv2Code += System.lineSeparator();
	//	smtlibv2Code +="(assert (member (mkTuple LeadAttorneysU approve Case3Info) (AccessRights "+k+")))";

		//smtlibv2Code +="(assert (member (mkTuple \"0\" \""+obligationsEvents.get(k)+"\" \"0\") (AccessRightsOnlyAR "+(k-1)+")))";


		smtlibv2Code += "(assert (= (obligation4 "+k+") 1))";

		smtlibv2Code += System.lineSeparator();

		// smtlibv2Code += "(assert (member (mkTuple "+AttorneysID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+Attorneys1ID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+Attorneys2ID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		//  smtlibv2Code += System.lineSeparator();
		//  smtlibv2Code += "(assert (member (mkTuple "+Attorneys3ID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+LeadAttorneysUID+" "+approveID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		// smtlibv2Code += System.lineSeparator();
		// smtlibv2Code += "(assert (member (mkTuple "+CSuitID+" "+acceptID+" "+Case3InfoID+") (AccessRights " + k + ")))";

		smtlibv2Code += System.lineSeparator();

		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());

		sb.append(";--------------------------------------------------------------------------------------------------------------------\r\n"
		 		+ ";STEP"+k);

		sb.append(System.lineSeparator());
		sb.append(ot.translateObligationEvents(k));
		sb.append(System.lineSeparator());
		sb.append(ot.translateGraphIntersection(k));
		sb.append(System.lineSeparator());
		//sb.append(ot.processActions(k));
		sb.append(ot.processActionsRefactoring(k));
		sb.append(System.lineSeparator());
		sb.append(gt.translateARCheck(k));
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	
	public static void main(String[] args) throws Exception {
		ObligationChecker checker = new ObligationChecker();
		
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC2");
		checker.check();
		System.out.println(checker.mapOfIDs);
	}

}
