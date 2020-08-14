package project.POMA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CVC4Runner {

	
	public void runFromSMTLIB2(String pathToFile) throws IOException {
		
		Runtime rt = Runtime.getRuntime();
		String[] commands = { "CVC4/cvc4.exe", "--incremental", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		StringBuilder sb = new StringBuilder();
		while ((s = stdInput.readLine()) != null) {
			if (!s.contains("sat")) {
				//System.out.println(s);
				sb.append(s);
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
