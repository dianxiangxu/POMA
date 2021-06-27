package POMA.Verification.ReachabilityAnalysisHierarchy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Solver {

	public static final Solver Z3 = new Solver("Z3", "/usr/local/bin/z3");
	public static final Solver CVC4 = new Solver("CVC4", "VerificationFiles/CVC4/cvc4.exe");
	private String name;
	private String executable;

	public Solver(String name, String executable) {
		this.name = name;
		this.executable = executable;
	}

	public String[] commandArguments(String pathToFile) {
		String[] arguments = { executable, pathToFile };
		return arguments;
	}

	public String toString() {
		return "Solver " + name + ": " + executable;
	}

	protected boolean runSolver(String pathToFile, int k, List<String> confirmedObligations) throws IOException {
		String[] cmdArguments = commandArguments(pathToFile);
		Process proc = Runtime.getRuntime().exec(cmdArguments);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String result = null;
		while ((result = stdInput.readLine()) != null) {
			System.out.println(k + ": " + result);
			if (result.equals("sat")) {
				while ((result = stdInput.readLine()) != null) {
					System.out.println(result);
					// String[] stringArray = result.split("\\)\\),\\(ite");
					String[] stringArray = result.split("BOUND_VARIABLE|\\)\\)|\\)|\\(ite \\(=");
					String[] labelArray = (stringArray[0]).split(" \\(lambda \\(\\(|\\(\\(");
					// System.out.println("Label: "+ label[1]);
					String label = labelArray[1];
					for (String s : stringArray) {
						// System.out.println(s);
						if (s.length() < 5 && s.contains("1")&&!confirmedObligations.contains(label)) {
							confirmedObligations.add(label);
							break;
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}
