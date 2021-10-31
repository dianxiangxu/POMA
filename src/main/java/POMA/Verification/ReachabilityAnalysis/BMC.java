package POMA.Verification.ReachabilityAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.Obligations;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

abstract class BMC {
	public enum QUERY_TYPE {
		LABEL, PERMIT, UAOA, UO, UO_explicit, UAOA_explicit, DENY, HIERARCHY, NOT_HIERARCHY, PERMIT_UA_ONLY,
		PERMIT_AT_ONLY, ASSOC_UA_ONLY, ASSOC_AT_ONLY, DENY_UA_ONLY, DENY_AT_ONLY, NO_ASSOC_UA_ONLY, NO_ASSOC_AT_ONLY, ASSOC
	}

	private Solver solver = Solver.CVC4;
	private int bound = 5;
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

	abstract String generateAssertKCode(int k, String obligation_label, QUERY_TYPE queryType);

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
		String queryLabel = "obligation2";
		// String queryAR = " "+ mapOfIDs.get("BM") + " " + mapOfIDs.get("approve") + "
		// " + mapOfIDs.get("PDSWhole");
		// String queryASSIGNMENT = " " + mapOfIDs.get("Vlad") + " " +
		// mapOfIDs.get("CoPI2");
		String query2 = " " + mapOfIDs.get("LeadAttorneys") + " " + mapOfIDs.get("approve") + " "
				+ mapOfIDs.get("Case3");
		String query3 = " " + mapOfIDs.get("Attorney1") + " " + mapOfIDs.get("Attorney1");
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");
			// String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1,
			// query, QUERY_TYPE.OBLIGATION) + tailCode;
			String smtlibv2Code = headCode + iterationCode 
			+ generateAssertKCode(k - 1, queryLabel, QUERY_TYPE.LABEL)
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
