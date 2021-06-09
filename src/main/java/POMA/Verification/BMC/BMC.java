package POMA.Verification.BMC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract class BMC {

	private Solver solver = Solver.CVC4;
	private int bound = 5;
	private String smtCodeFilePath = "";

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

	abstract String generateAssertKCode(int k, String obligation_label);

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
		for (String label : obligationLabels) {
			if(confirmedObligations.contains(label)){
			continue;
			}
			boolean solved = false;
			String headCode = generateHeadCode();
			String tailCode = generateTailCode();
			String iterationCode = "";
			for (int k = 1; k <= bound && !solved; k++) {
				iterationCode += generateIterationCode(k);
				System.out.println("=============================================");
				String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1, label) + tailCode;
				if (k == bound) {
					// System.out.println(smtlibv2Code);
				}
				String pathToFile = smtCodeFilePath + k + ".smt2";
				saveCodeToFile(smtlibv2Code, pathToFile);
				solved = solver.runSolver(pathToFile, k, confirmedObligations);
			}
			System.out.println("CONFIRMED: " + confirmedObligations);
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

			count++;
		}
		System.out.println("Total Runs: " + count);
	}
}
