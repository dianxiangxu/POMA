package POMA.Verification.TranslationWithSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import POMA.GlobalVariables;

public class CVC4Runner {
	private String fullOutput = "";
	public List<String> runFromSMTLIB2SetsTheory(String pathToFile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { GlobalVariables.cvc4path, "--incremental", pathToFile };
		
		long start = System.currentTimeMillis();

		Process proc = rt.exec(commands);
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		String s = null;

		List<String> outputList = new ArrayList<String>();
		List<String> fullOutputList = new ArrayList<String>();

		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error") && (s.contains("((FinalJoin") || s.contains("((result"))) {
				outputList.add(s);
			}
			else if(!s.contains("sat") && !s.contains("error")) {
				fullOutputList.add(s);
				fullOutputList.add(System.lineSeparator());
			}
		}
		setFullOutput(fullOutputList.toString());
		return outputList;
	}
	
	public List<String> runFromSMTLIB2SetsTheoryIncremental(String pathToFile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { GlobalVariables.cvc4path, "--incremental", pathToFile };
		long start = System.currentTimeMillis();

		Process proc = rt.exec(commands);
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		String s = null;

		List<String> outputList = new ArrayList<String>();
		List<String> fullOutputList = new ArrayList<String>();

		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error") && (s.contains("((FinalJoin") || s.contains("((result"))) {
				outputList.add(s);
			}
			else if(!s.contains("sat") && !s.contains("error")) {
				fullOutputList.add(s);
				fullOutputList.add(System.lineSeparator());
			}
		}
		setFullOutput(fullOutputList.toString());
		return outputList;
	}
	public String getFullOutput() {
		return fullOutput;
	}
	private void setFullOutput(String fullOutput) {
		this.fullOutput = fullOutput;
	}	
}
