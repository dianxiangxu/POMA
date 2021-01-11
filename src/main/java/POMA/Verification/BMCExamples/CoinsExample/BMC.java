package POMA.Verification.BMCExamples.CoinsExample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BMC {

	private Solver solver = Solver.Z3;
	private int bound = 15;
	private String smtCodeFilePath=""; 
	
	public void setSolver(Solver solver) {
		this.solver = solver;
	}

	public void setBound(int bound) {
		this.bound = bound;
	}
	
	public void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}
	
	abstract public String generateHeadCode();

	public String generateTailCode() {
		String smtlibv2Code = System.lineSeparator();
		smtlibv2Code += "(check-sat)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(get-model)";
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

	public void check() throws IOException {
		boolean solved = false;
		String headCode = generateHeadCode();
		String tailCode = generateTailCode();
		String iterationCode="";
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			String smtlibv2Code = headCode + generateAssertKCode(k) + iterationCode + tailCode;
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
			solved = solver.solve(pathToFile, k);
		}
	}
}
