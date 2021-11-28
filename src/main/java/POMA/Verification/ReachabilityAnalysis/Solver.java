package POMA.Verification.ReachabilityAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;

public class Solver {

	//public static final Solver Z3 = new Solver("Z3", "/usr/local/bin/z3");
	public static final Solver CVC4 = new Solver("CVC4", "VerificationFiles/CVC4/cvc4.exe");
	private String name;
	private String executable;
	List<String> obligationLabelsWithStep = new ArrayList<String>();
	List<String> obligationVarsWithAssignments = new ArrayList<String>();

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

	protected Solution runSolver(String pathToFile, int k, List<String> confirmedObligations,
			List<String> obligationLabels, List<String> obligationEventVariables, HashMap<String, Integer> mapOfIDs,
			boolean showSMTOutput) throws IOException {
		String[] cmdArguments = commandArguments(pathToFile);
		Process proc = Runtime.getRuntime().exec(cmdArguments);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		String result = null;
		List<String> output = new ArrayList<String>();
		while ((result = stdInput.readLine()) != null) {
			if (showSMTOutput)
				System.out.println(k + ": " + result);
			if (result.equals("sat")) {
				while ((result = stdInput.readLine()) != null) {
					String line = result.replaceAll("[()]", "");
					output.add(line);
					String[] stringArray = result.split("BOUND_VARIABLE|\\)\\)|\\)|\\(ite \\(=");
					String[] labelArray = (stringArray[0]).split(" \\(lambda \\(\\(|\\(\\(");
					String label = labelArray[1];
					for (String s : stringArray) {
						if (s.length() < 5 && s.contains("1") && !confirmedObligations.contains(label)) {
							confirmedObligations.add(label);
							break;
						}
					}
				}
				Solution solution = findSolution(output, obligationLabels, mapOfIDs);
				return solution;
			}
		}
		return null;
	}

	Solution findSolution(List<String> output, List<String> obligationLabels, HashMap<String, Integer> mapOfIDs) {
		ObligationFiring[] arrayOfSteps = new ObligationFiring[100];

		for (String line : output) {
			for (String label : obligationLabels) {
				if (isContain(line, label)) {
					String[] splittedLine = line.split(" ");
					String stepNumber = splittedLine[splittedLine.length - 1];
					if (Boolean.parseBoolean(stepNumber)) {
						stepNumber = "0";
					}
					if (Character.isDigit(stepNumber.charAt(0))) {
						ObligationFiring step = new ObligationFiring();
						step.setObligationLabel(label);
						for (String line2 : output) {
							String[] splittedLine2 = line2.split(" ");
							String varAssignment = splittedLine2[splittedLine2.length - 1];
							if (isContain(line2, label + "U_" + stepNumber)) {
								int varAssignmentInt = Integer.parseInt(varAssignment);
								step.setSubject(getKeyFromValue(varAssignmentInt, mapOfIDs));
							} else if (isContain(line2, label + "UO_" + stepNumber)) {
								int varAssignmentInt = Integer.parseInt(varAssignment);
								step.setObject(getKeyFromValue(varAssignmentInt, mapOfIDs));
							} else if (isContain(line2, label + "ar_" + stepNumber)) {
								int varAssignmentInt = Integer.parseInt(varAssignment);
								step.setEvent(getKeyFromValue(varAssignmentInt, mapOfIDs));
							}
						}
						arrayOfSteps[Integer.parseInt(stepNumber)] = step;
					}
				}
			}
		}
		return new Solution(Arrays.asList(arrayOfSteps).stream().filter(Objects::nonNull).collect(Collectors.toList()));
	}

	private static boolean isContain(String source, String subItem) {
		String pattern = "\\b" + subItem + "\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		return m.find();
	}

	private static String getKeyFromValue(int value, HashMap<String, Integer> mapOfIDs) {
		if (value == 0) {
			return "NONE";
		}
		try {
			String key = mapOfIDs.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), value))
					.findFirst().get().getKey();
			return key;
		} catch (Exception e) {
			System.out.println("BREAKING VALUE: " + value);
			return "NONE";
		}
	}
}
