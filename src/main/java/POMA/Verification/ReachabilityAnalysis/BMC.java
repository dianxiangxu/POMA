package POMA.Verification.ReachabilityAnalysis;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.Obligations;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Verification.ReachabilityAnalysis.fol.model.*;
import POMA.Verification.ReachabilityAnalysis.fol.parser.FOLGrammar;
import POMA.Verification.ReachabilityAnalysis.model.Solution;

abstract class BMC {

	private Solver solver = Solver.CVC4;
	private int bound = 3;
	private String smtCodeFilePath = "";
	HashMap<String, Integer> mapOfIDs;

	void setSolver(Solver solver) {
		this.solver = solver;
	}

	void setBound(int bound) {
		this.bound = bound;
	}

	void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}

	abstract String generateHeadCode() throws Exception;

	abstract String generateTailCode();

	abstract String generateIterationCode(int k);

	private void saveCodeToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

	abstract List<String> getObligationLabels();

	abstract List<String> getObligationEventVariables();

	public Solution check(String query) throws Exception {

		List<String> obligationLabels = getObligationLabels();
		List<String> confirmedObligations = new ArrayList<String>();
		int count = 0;
		// for (String label : obligationLabels) {
		// if (confirmedObligations.contains(label)) {
		// continue;
		// }
		boolean solved = false;
		String headCode = generateHeadCode();
		String iterationCode = "";

		IFormula formula = parseQuery(query);
		if (formula == null) {
			return null;
		}
		Solution solution = null;
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			String smtlibv2Code = headCode + iterationCode;
			smtlibv2Code += postProcessQuery(formula, (k - 1));
			System.out.println("=============================================");
			System.out.println("Processing step: "+k+"...");
			smtlibv2Code += generateTailCode();
			if (k == bound) {
				// System.out.println(smtlibv2Code);
			}
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
			solution = solver.runSolver(pathToFile, k, confirmedObligations, obligationLabels,
					getObligationEventVariables(), mapOfIDs);
			solved = solution == null ? false : true;
		}
		count++;
		// }
		System.out.println("Total Runs: " + count);
		return solution;
	}

	public Solution solveConstraint(Graph graph, Prohibitions prohibitions, // not now
			Obligations obligations, String constraint) {
		return null;
	}

	public Solution solveConstraint(String constraint) throws Exception {
		try {
			Solution s = check(constraint);
			return s;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	private IFormula parseQuery(String query) {
		FOLGrammar parser = new FOLGrammar(new ByteArrayInputStream(query.getBytes()));

		while (true) {
			try {
				IFormula f = FOLGrammar.parse();
				return f;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			} catch (Error e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		return null;
	}

	private String postProcessQuery(IFormula f, int k) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		List<String> queryVars = new ArrayList<String>();
		String formula = f.toSMT();

		String formulaWithStep = formula.replace("{k}", Integer.toString(k)).replace("{(k + 1)}",
				Integer.toString((k + 1)));

		String[] splittedFormula = formulaWithStep.split(" ");
		for (String formulaElement : splittedFormula) {
			if (formulaElement.contains("queryVAR") && !queryVars.contains(formulaElement)) {
				sb.append("(declare-fun " + formulaElement + " () Int)");
				sb.append(System.lineSeparator());
				queryVars.add(formulaElement);
				continue;
			}
			if (formulaElement.contains("[")) {
				String cleanFormulaElement = formulaElement.replace("[", "").replace("]", "");
				Integer elementId = mapOfIDs.getOrDefault(cleanFormulaElement, -1);
				formulaWithStep = formulaWithStep.replace(formulaElement, Integer.toString(elementId));
			}
		}
		sb.append("(assert " + formulaWithStep + ")");
		return sb.toString();
	}
}
