package MarbleExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gov.nist.csd.pm.exceptions.PMException;

public class MarbleExample {
	public static void main(String[] args) throws Exception {

		int k = 30;

		for (int j = 1; j <= k; j++) {
			String marblesPath = "SMTLIBv2Files/SMTLIB2Input/MarbleFiles/marbles"+j+".smt2";
			String translatedMarbles = "";
			translatedMarbles += "(set-logic UFDTLIRA)";
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(declare-fun M (Int) Int)";
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(declare-fun k () Int)";
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(assert (= (M 0) 1))"; // INITIAL STATE - SINGLE STATE OF ONE MARBLE
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(assert (= (M " + j + ") 1000))"; // PROPERTY - EXACTLY 1000 MARBLES in K STEPS
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(assert (= k " + j + "))";
			translatedMarbles += System.lineSeparator();
			// TRANSITION SYSTEM
			for (int i = 1; i <= j; i++) { //steps
				translatedMarbles += "(assert (or (= (M " + i + ") (+ (M (- " + i + " 1)) 1)) (= (M " + i
						+ ") (* (M (- " + i + " 1)) 2))))";//states
				translatedMarbles += System.lineSeparator();
			}
			translatedMarbles += System.lineSeparator();
			translatedMarbles += "(check-sat)";
			saveDataToFile(translatedMarbles, marblesPath);
			runCVC4(marblesPath, j);
		}
	}

	public static List<String> runCVC4(String pathToFile, int j) throws IOException {
		Runtime rt = Runtime.getRuntime();
		String[] commands = { "CVC4/cvc4.exe", pathToFile };
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String s = null;
		StringBuilder sb = new StringBuilder();

		List<String> outputList = new ArrayList<String>();
		List<String> fullOutputList = new ArrayList<String>();

		while ((s = stdInput.readLine()) != null) {
//			if (!s.contains("sat") && !s.contains("error") && s.contains("((FinalJoin")) {
//				outputList.add(s);
//				//System.out.println("OUTPUT!!!!: "+s);
//			}
//			else if(!s.contains("sat") && !s.contains("error")) {
//				fullOutputList.add(s);
//				fullOutputList.add(System.lineSeparator());
//			}
			System.out.println(j+": " + s);
			if(s.equals("sat")) {
				System.out.println("SUCCESS");
				System.exit(0);
			}
		}
		return outputList;
	}

	private static void saveDataToFile(String data, String path) throws PMException, IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(data);
		myWriter.close();

	}
}
