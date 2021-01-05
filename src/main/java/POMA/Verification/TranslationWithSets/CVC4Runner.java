package POMA.Verification.TranslationWithSets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import POMA.GlobalVariables;

public class CVC4Runner {
	private String fullOutput = "";
	public List<String> runFromSMTLIB2SetsTheory(String pathToFile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { GlobalVariables.cvc4path, "--incremental", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String s = null;
		StringBuilder sb = new StringBuilder();

		List<String> outputList = new ArrayList<String>();
		List<String> fullOutputList = new ArrayList<String>();

		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error") && s.contains("((FinalJoin")) {
				outputList.add(s);
				//System.out.println("OUTPUT!!!!: "+s);
			}
			else if(!s.contains("sat") && !s.contains("error")) {
				fullOutputList.add(s);
				fullOutputList.add(System.lineSeparator());
			}
		//	System.out.println("OUTPUT"+s);
		}
		setFullOutput(fullOutputList.toString());
		return outputList;
	}
	public List<String> runFromSMTLIB2SetsTheoryIncremental(String pathToFile) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { GlobalVariables.cvc4path, "--incremental", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String s = null;
		StringBuilder sb = new StringBuilder();

		List<String> outputList = new ArrayList<String>();
		List<String> fullOutputList = new ArrayList<String>();

		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error") && s.contains("((FinalJoin")) {
				outputList.add(s);
				//System.out.println("OUTPUT!!!!: "+s);
			}
			else if(!s.contains("sat") && !s.contains("error")) {
				fullOutputList.add(s);
				fullOutputList.add(System.lineSeparator());
			}
			//System.out.println("OUTPUT"+s);
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
