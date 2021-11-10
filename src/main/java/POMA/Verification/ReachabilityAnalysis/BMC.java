package POMA.Verification.ReachabilityAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.Obligations;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Verification.ReachabilityAnalysis.fol.model.*;
import POMA.Verification.ReachabilityAnalysis.fol.model.connectives.Conjunctive;
import POMA.Verification.ReachabilityAnalysis.fol.model.predicates.*;
import POMA.Verification.ReachabilityAnalysis.fol.model.terms.*;
import POMA.Verification.ReachabilityAnalysis.fol.parser.FOLGrammar;
import POMA.Verification.ReachabilityAnalysis.models.AccessRequest;
import POMA.Verification.ReachabilityAnalysis.models.Request;

abstract class BMC {
	public enum QUERY_TYPE {
		LABEL, PERMIT, DENY, HIERARCHY, NOT_HIERARCHY,
		      ASSOC, NO_ASSOC, ASSIGN, ASSIGN_explicit
	}

	private Solver solver = Solver.CVC4;
	private int bound = 8;
	private String smtCodeFilePath = "";
	HashMap<String, Integer> mapOfIDs;
	
	List<AccessRequest> permitRequests = new ArrayList<AccessRequest>();
	List<AccessRequest> denyRequests = new ArrayList<AccessRequest>();

	void setSolver(Solver solver) {
		this.solver = solver;
	}

	void setBound(int bound) {
		this.bound = bound;
	}

	void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}

	abstract String generateHeadCode() throws Exception;

	abstract String generateTailCode();

	abstract String generateAssertKCode(int k, String obligation_label, QUERY_TYPE queryType,
			AccessRequest... accessRequest);

	abstract String generateIterationCode(int k);

	private void saveCodeToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

	abstract List<String> getObligationLabels();
	abstract List<String> getObligationEventVariables();	
	
	public void check() throws Exception {

		List<String> obligationLabels = getObligationLabels();
		List<String> confirmedObligations = new ArrayList<String>();
		int count = 0;
		// for (String label : obligationLabels) {
		// if (confirmedObligations.contains(label)) {
		// continue;
		// }
		boolean solved = false;
		String headCode = generateHeadCode();
		String iterationCode = "";
		String queryLabel = "obligation3";
		// String queryAR = " "+ mapOfIDs.get("BM") + " " + mapOfIDs.get("approve") + "
		// " + mapOfIDs.get("PDSWhole");
		// String queryASSIGNMENT = " " + mapOfIDs.get("Vlad") + " " +
		// mapOfIDs.get("CoPI2");
		String query2 = " " + mapOfIDs.get("LeadAttorneys") + " " + mapOfIDs.get("approve") + " "
				+ mapOfIDs.get("Case3");
		String query3 = " " + mapOfIDs.get("Attorney1") + " " + mapOfIDs.get("Attorney1");
		Integer s = mapOfIDs.get("Attorneys2");
	    Integer ar = mapOfIDs.get("accept");
		Integer t =  mapOfIDs.get("Case3");
		AccessRequest accessRequest = new AccessRequest(s, ar, t);
		//parseQuery();
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");

			String smtlibv2Code = headCode + iterationCode; 
			smtlibv2Code+=processSMTQueryCode(k);
			smtlibv2Code+= generateAssertKCode(k - 1, queryLabel, QUERY_TYPE.LABEL);
			smtlibv2Code+= generateTailCode();
			if (k == bound) {
				// System.out.println(smtlibv2Code);
			}
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
			solved = solver.runSolver(pathToFile, k, confirmedObligations, 
					obligationLabels, getObligationEventVariables(), mapOfIDs);
		}
		count++;
		// }
		System.out.println("Total Runs: " + count);
	}

	public Solution solveConstraint(Graph graph, Prohibitions prohibitions, // not now
			Obligations obligations, String contraint) {
		return null;
	}

	private String processSMTQueryCode(int k){
		String toReturn = System.lineSeparator();
		int count = 0;
		for(AccessRequest pr : permitRequests){
			toReturn += generateAssertKCode(k - 1,"", QUERY_TYPE.PERMIT, pr);
			toReturn += System.lineSeparator();
			count++;
		}

		return toReturn;
	}
	
	private void parseQuery() {
		FOLGrammar parser = new FOLGrammar(System.in);
		while (true) {
			System.out.println("Reading from standard input...");
			System.out.print("Enter an expression: ");
			try {
				IFormula f = FOLGrammar.parse();
				System.out.println(f);
				processFormula(f);
				break;
			} catch (Exception e) {
				System.out.println("NOK.");
				System.out.println(e.getMessage());
				FOLGrammar.ReInit(System.in);
			} catch (Error e) {
				System.out.println("Oops.");
				System.out.println(e.getMessage());
				break;
			}
		}
	}

	private List<AccessRequest> processFormula(IFormula f) {
		if (f instanceof PermitPredicate) {
			PermitPredicate permitPredicate = (PermitPredicate) f;
			permitRequests.add(processPermissionRequest(permitPredicate));
		}
		if (f instanceof DenyPredicate) {
			DenyPredicate denyPredicate = (DenyPredicate) f;
			denyRequests.add(processPermissionRequest(denyPredicate));
		}
		if (f instanceof Conjunctive) {
			processFormula( ((Conjunctive) f).getSubformulaA());
			processFormula( ((Conjunctive) f).getSubformulaB());
		}
		return permitRequests;
	}

	private AccessRequest processPermissionRequest(IPredicate permitPredicate){
		List<ITerm> terms = permitPredicate.getTuple();
		ITerm source = terms.get(0);
		ITerm accessright = terms.get(1);
		ITerm target = terms.get(2);

		AccessRequest accessRequest = new AccessRequest(null, null, null);
		if (!source.toString().contains("?")) {
			accessRequest.setS(mapOfIDs.get(source.getElement()));
		}
		if (!accessright.toString().contains("?")) {
			accessRequest.setAr(mapOfIDs.get(accessright.getElement()));
		}
		if (!target.toString().contains("?")) {
			accessRequest.setT(mapOfIDs.get(target.getElement()));
		}
		return accessRequest;
	}
}
