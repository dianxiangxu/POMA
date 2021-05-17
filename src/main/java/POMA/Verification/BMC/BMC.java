package POMA.Verification.BMC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BMC {

	private Solver solver = Solver.CVC4;
	private int bound = 11;
	private String smtCodeFilePath = "";

	public void setSolver(Solver solver) {
		this.solver = solver;
	}

	public void setBound(int bound) {
		this.bound = bound;
	}

	public void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}

	abstract public String generateHeadCode() throws Exception;

	public String generateTailCode() {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += "(check-sat)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(get-value (AccessRights 0))";
		return smtlibv2Code;
	}

	abstract public String generateAssertKCode(int k);

	abstract String generateIterationCode(int k);

	private void saveCodeToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

	public void check() throws Exception {
		boolean solved = false;
		String headCode = generateHeadCode();
		String tailCode = generateTailCode();
		String iterationCode = "";
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");
			String smtlibv2Code = headCode  + iterationCode + generateAssertKCode(k-1)  + tailCode  ;
			if (k == bound) {
				//System.out.println(smtlibv2Code);
			}
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
		    solved = solver.runSolver(pathToFile, k);
		}
	}
}
