package POMA.Verification.BMC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		String[] arguments = {executable, pathToFile};
		return arguments;
	}
	
	public String toString() {
		return "Solver "+name+": "+executable;
	}
	protected boolean runSolver(String pathToFile, int k) throws IOException {
		String[] cmdArguments = commandArguments(pathToFile);
		Process proc = Runtime.getRuntime().exec(cmdArguments);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String result = null;
		while ((result = stdInput.readLine()) != null) {
			System.out.println(k + ": " + result);
			if (result.equals("sat")) {
				while ((result = stdInput.readLine()) != null) {
					System.out.println(result);
				}
				return true;
			}
		}
		return false;
	}
}
