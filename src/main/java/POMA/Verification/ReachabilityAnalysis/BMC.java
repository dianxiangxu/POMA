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

abstract class BMC {
	public enum QUERY_TYPE {
		LABEL, PERMIT, DENY, HIERARCHY, NOT_HIERARCHY,
		      ASSOC, NO_ASSOC, ASSIGN, ASSIGN_explicit
	}

	private Solver solver = Solver.CVC4;
	private int bound = 8;
	private String smtCodeFilePath = "";
	HashMap<String, Integer> mapOfIDs;

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
		String tailCode = generateTailCode();
		String iterationCode = "";
		String queryLabel = "obligation3";
		// String queryAR = " "+ mapOfIDs.get("BM") + " " + mapOfIDs.get("approve") + "
		// " + mapOfIDs.get("PDSWhole");
		// String queryASSIGNMENT = " " + mapOfIDs.get("Vlad") + " " +
		// mapOfIDs.get("CoPI2");
		String query2 = " " + mapOfIDs.get("LeadAttorneys") + " " + mapOfIDs.get("approve") + " "
				+ mapOfIDs.get("Case3");
		String query3 = " " + mapOfIDs.get("Attorney1") + " " + mapOfIDs.get("Attorney1");
		Integer s = mapOfIDs.get("Attorneys3");
	    Integer ar = mapOfIDs.get("accept");
		Integer t =  mapOfIDs.get("Case3");
		AccessRequest accessRequest = new AccessRequest(s, ar, t);
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");
			// String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1,
			// query, QUERY_TYPE.OBLIGATION) + tailCode;
			// String smtlibv2Code = headCode + iterationCode 
			// + generateAssertKCode(k - 1, queryLabel, QUERY_TYPE.LABEL)
			String smtlibv2Code = headCode + iterationCode 
			+ generateAssertKCode(k - 1, queryLabel, QUERY_TYPE.ASSOC, accessRequest)
					//+ generateAssertKCode(k - 1, query3, QUERY_TYPE.PERMIT_UA_ONLY)
					+ tailCode;
			if (k == bound) {
				// System.out.println(smtlibv2Code);
			}
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
			solved = solver.runSolver(pathToFile, k, confirmedObligations);
		}
		count++;
		// }
		System.out.println("Total Runs: " + count);
	}

	public List<AccessRequest> solveConstraint(Graph graph, Prohibitions prohibitions, // not now
			Obligations obligations, String contraint) {
		return null;
	}
}
