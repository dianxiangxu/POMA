package POMA.Verification.VerificationAllComb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CVC4Runner {

	public void runFromSMTLIB2AllCombTest(String pathToFile) throws IOException {
		Pattern p = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
		Runtime rt = Runtime.getRuntime();
		String[] commands = { "CVC4/cvc4.exe", "--incremental", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		StringBuilder sb = new StringBuilder();

		List<String> outputList = new ArrayList<String>();
		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error")) {
				// System.out.println(s);
				// sb.append(s.split("\"")[1]+System.lineSeparator()) ;
				outputList.add(s);
			}

		}
		String currentRelation = "";
		for (int i = 0; i < outputList.size(); i++) {
			if (i < outputList.size() - 1) {
				if (outputList.get(i + 1).contains("((")) {
					if (!currentRelation.equals(outputList.get(i).split("\"")[1])) {
						currentRelation = outputList.get(i).split("\"")[1];
						sb.append(System.lineSeparator()+currentRelation + System.lineSeparator());
						sb.append(outputList.get(i + 1).split("\"")[1]);
					}
					else {
						sb.append(" "+outputList.get(i + 1).split("\"")[1]);
					} 

				}
			}
		}

		 System.out.println(sb.toString());
		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}

	
	public void runFromSMTLIB2SSetTheory(String pathToFile) throws IOException {
		Pattern p = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
		Runtime rt = Runtime.getRuntime();
		String[] commands = { "CVC4/cvc4.exe", "--incremental", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		StringBuilder sb = new StringBuilder();

		List<String> outputList = new ArrayList<String>();
		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat") && !s.contains("error")) {
				// System.out.println(s);
				// sb.append(s.split("\"")[1]+System.lineSeparator()) ;
				outputList.add(s);
			}

		}
		String currentRelation = "";
		for (int i = 0; i < outputList.size(); i++) {
			if (i < outputList.size() - 1) {
				if (outputList.get(i + 1).contains("((")) {
					if (!currentRelation.equals(outputList.get(i).split("\"")[1])) {
						currentRelation = outputList.get(i).split("\"")[1];
						sb.append(System.lineSeparator()+currentRelation + System.lineSeparator());
						sb.append(outputList.get(i + 1).split("\"")[1]);
					}
					else {
						sb.append(" "+outputList.get(i + 1).split("\"")[1]);
					} 

				}
			}
		}

		 System.out.println(sb.toString());
		// Read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}
	}
}
