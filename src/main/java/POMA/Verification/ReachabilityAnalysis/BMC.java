package POMA.Verification.ReachabilityAnalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class BMC {
	public enum QUERY_TYPE {
		OBLIGATION, ACCESS_REQUEST, OOA, OAOA, UUA, UAUA
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
		// String query = "obligation3";
		//String query = " "+ mapOfIDs.get("BM") + " " + mapOfIDs.get("approve") + " " + mapOfIDs.get("PDSWhole");
		String query = " " + mapOfIDs.get("Vlad") + " " + mapOfIDs.get("CoPI2");

		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");
			// String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1,
			// query, QUERY_TYPE.OBLIGATION) + tailCode;
			String smtlibv2Code = headCode + iterationCode
					+ generateAssertKCode(k - 1, query, QUERY_TYPE.UUA) + tailCode;
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
}
