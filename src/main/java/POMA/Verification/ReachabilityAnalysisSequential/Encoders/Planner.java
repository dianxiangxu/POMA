package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.Obligations;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.*;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.parser.FOLGrammar;
import POMA.Verification.ReachabilityAnalysisSequential.model.Solution;
import POMA.Verification.ReachabilityAnalysisSequential.model.Variable;

public abstract class Planner {

	private Solver solver = Solver.CVC5;
	private int bound = 8;
	private String smtCodeFilePath = "";
	HashMap<String, Integer> mapOfIDs;
	List<String> _customFunctionVariables = new ArrayList<String>();
	List<String> _conditionalInterferenceVariables = new ArrayList<String>();
//	List<ObligationEncoder> _obligationEncoders;

	public HashMap<String, Integer> getMapOfIDs() {
		return mapOfIDs;
	}

	protected List<Node> listOfNodes = new ArrayList<Node>();

	public List<Node> getListOfNodes() {
		return listOfNodes;
	}

	boolean showSMTOutput = false;
	static FOLGrammar parser = null;

	void setSolver(Solver solver) {
		this.solver = solver;
	}

	public void setBound(int bound) {
		this.bound = bound;
	}

	public void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}

	abstract String generateHeadCode() throws Exception;

	abstract String generateTailCode(List<String> queryVARS, int i);

	abstract String generateIterationCode(int k);

	abstract List<ObligationEncoder> getObligationEncoders();

	private static void saveCodeToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

	public void enableSMTOutput(boolean showSMTOutput) {
		this.showSMTOutput = showSMTOutput;
	}

	abstract List<String> getObligationLabels();

	abstract List<String> getRaceObligationLabels();

	abstract List<String> getObligationEventVariables();

	public Solution solveConstraint(String pre, String post, Graph initialGraph) throws Exception {
		Solution s = check(pre, post, initialGraph);
		return s;
	}

	public boolean solveConstraint(String property, Graph initialGraph) throws Exception {
		return checkStaticConfiguration(property, initialGraph);
	}

//	public void startRaceConditionFinder(String obligation, String post, Graph initialGraph) throws Exception {
//		raceConditionFinder(initialGraph);
//	}

	public Solution check(String pre, String post, Graph initialGraph) throws Exception {

		List<String> obligationLabels = getObligationLabels();
		List<String> confirmedObligations = new ArrayList<String>();
		int count = 0;
		boolean solved = false;
		IFormula formulaPre = pre == null || pre.isEmpty() ? null : parseQuery(pre);
		IFormula formulaPost = post.isEmpty() ? null : parseQuery(post);

		String headCode = generateHeadCode();
		String iterationCode = "";

		Solution solution = null;
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			String smtlibv2Code = headCode + iterationCode;
			List<String> queryVARS = new ArrayList<String>();
			List<String> queryConst = new ArrayList<String>();

			smtlibv2Code += ";PRE PROPERTY";
			smtlibv2Code += formulaPre != null ? generateProperty(formulaPre, (k - 2), queryVARS, queryConst) : "";
			smtlibv2Code += System.lineSeparator() + System.lineSeparator() + ";POST PROPERTY";
			smtlibv2Code += formulaPost != null ? generateProperty(formulaPost, (k - 1), queryVARS, queryConst) : "";
			System.out.println("Time horizon " + k + " processing...");
			smtlibv2Code += generateTailCode(queryVARS, k);

			String pathToFile = smtCodeFilePath + k + ".smt2";

			saveCodeToFile(smtlibv2Code, pathToFile);
			solution = solver.runSolver(pathToFile, k, confirmedObligations, obligationLabels,
					getObligationEventVariables(), mapOfIDs, showSMTOutput, queryVARS, initialGraph, listOfNodes,
					_customFunctionVariables, new ArrayList<String>());
			solved = solution == null ? false : true;
			if (!solved) {
				System.out.println("Solution not found with time horizon: " + k);
			}
		}
		count++;
		System.out.println("Total Runs: " + count);
		return solution;
	}

	public boolean checkStaticConfiguration(String property, Graph initialGraph) throws Exception {
		IFormula formulaProperty = property.isEmpty() ? null : parseQuery(property);
		List<String> queryVARS = new ArrayList<String>();
		List<String> queryConst = new ArrayList<String>();
		String headCode = generateHeadCode();
		String smtlibv2Code = headCode;
		smtlibv2Code += formulaProperty != null ? generateProperty(formulaProperty, -1, queryVARS, queryConst) : "";

		smtlibv2Code += generateTailCode(queryVARS, 0);
		String pathToFile = smtCodeFilePath + 0 + ".smt2";
		saveCodeToFile(smtlibv2Code, pathToFile);
		boolean propertySatisfied = solver.runSolver(pathToFile, 0, mapOfIDs, showSMTOutput, queryVARS, initialGraph,
				listOfNodes);
		return propertySatisfied;
	}

	public String raceConditionFinder(Graph initialGraph) throws Exception {
		List<String> obligationLabels = getObligationLabels();
		List<String> obligationEventGroupSequences = getRaceObligationLabels();
		if (obligationEventGroupSequences.size() == 0) {
			throw new Exception("Obligations with the same event are not found");
		}
		String headCode = generateHeadCode();
		String iterationCode = "";
		for (int k = 1; k <= bound; k++) {
			//loop over obligationEventGroups
			RaceConditionChecker csc = new RaceConditionChecker();
			iterationCode += generateIterationCode(k);
			
			for (String sequence : obligationEventGroupSequences) {
				String smtlibv2Code = headCode + iterationCode;
				System.out.println("Looking for race conditions in the following obligation sequence: " + sequence);
				String encodedLabel = "OBLIGATIONLABEL(" + sequence + ", ?u, ?ar,?at);";
				IFormula formulaPost = parseQuery(encodedLabel);
				List<String> queryVARS = new ArrayList<String>();
				List<String> queryConst = new ArrayList<String>();
				smtlibv2Code += System.lineSeparator() + System.lineSeparator() + ";SEQUENCE PROPERTY";
				smtlibv2Code += formulaPost != null ? generateProperty(formulaPost, (k - 1), queryVARS, queryConst) : "";
				System.out.println("Time horizon " + k + " processing...");
				smtlibv2Code += generateTailCode(queryVARS, k);
				String pathToFile = smtCodeFilePath + k + ".smt2";
				saveCodeToFile(smtlibv2Code, pathToFile);
				Solution solution = solver.runSolver(pathToFile, k, new ArrayList<String>(), obligationLabels,
						getObligationEventVariables(), mapOfIDs, false, queryVARS, initialGraph, listOfNodes,
						_customFunctionVariables, _conditionalInterferenceVariables);
				boolean obligationGroupEventIsExecutable = solution == null ? false : true;
				
				if (!obligationGroupEventIsExecutable) {
					System.out.println("Obligation sequence is not eligible to be run at time horizon: " + k);
					break;//obligation sequence is not eligible to be run, can break here
				}
				if (obligationGroupEventIsExecutable) {
					filterRaceVariables(solution, sequence);
					System.out.println(solution);
					List<ObligationEncoder> encoders = getObligationEncoders();
					String raceCheckFilePath = smtCodeFilePath + "RACECHECK.smt2";
					csc.setConfiguration(solution.getLastAssignments(), solution.getLastTransitiveAssignments(), solution.getTransitiveAssignments(), solution.getLastAssociations(), encoders, sequence);
					String raceCheckSMTEncoding = csc.raceCheckEncoder();
					saveCodeToFile(raceCheckSMTEncoding, raceCheckFilePath);
					String raceCheckResult = solver.runSolver(raceCheckFilePath);
					if(csc.searchForRace(raceCheckResult)) {
						return "Race found.";
					}
				}
			}
		}
		System.out.println("Race was not found");
		return "Race not found";
	}

	private void filterRaceVariables(Solution solution, String label) {
		Set<String> seenNames = new HashSet<String>();
		List<Variable> variables = solution.getVariables().getVariables();

		List<Variable> uniqueFilteredVariables = variables.stream()
				.filter(variable -> variable.getName().contains(label)).distinct()
				.filter(variable -> seenNames.add(variable.getName())).collect(Collectors.toList());
		solution.getVariables().setVariables(uniqueFilteredVariables);
	}

	public Solution solveConstraint(Graph graph, Prohibitions prohibitions, // future
			Obligations obligations, String constraint) {

		return null;
	}

	public IFormula parseQuery(String query) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(query.getBytes());
		if (parser == null) {
			parser = new FOLGrammar(inputStream);
		} else {
			parser.ReInit(inputStream);
		}

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

	public String generateProperty(IFormula f, int k, List<String> queryVARS, List<String> queryConst)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		String formula = f.toSMT();

		String formulaWithStep = formula.replace("{k}", Integer.toString(k)).replace("{(k + 1)}",
				Integer.toString((k + 1)));

		String[] splittedFormula = formulaWithStep.split(" ");
		for (String formulaElement : splittedFormula) {
			if (formulaElement.contains("queryCONST") && !queryConst.contains(formulaElement)) {
				sb.append("(declare-fun " + formulaElement + " () Int)");
				sb.append(System.lineSeparator());
				queryConst.add(formulaElement);
				continue;
			}
			if (formulaElement.contains("queryVAR") && !queryVARS.contains(formulaElement)) {
				sb.append("(declare-fun " + formulaElement + " () Int)");
				sb.append(System.lineSeparator());
				queryVARS.add(formulaElement);
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
