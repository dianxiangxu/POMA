package POMA.Verification.ReachabilityAnalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import POMA.Verification.ReachabilityAnalysis.model.Variable;
import POMA.Verification.ReachabilityAnalysis.model.Variables;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class Solver {

	// public static final Solver Z3 = new Solver("Z3", "/usr/local/bin/z3");
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

	protected boolean runSolver(String pathToFile, int k,
			  HashMap<String, Integer> mapOfIDs,
			boolean showSMTOutput, List<String> queryVARS, Graph initialGraph, List<Node> listOfNodes)
			throws IOException {

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
				}
				

				return true;
			}
		}
		return false;
	}
	
	protected Solution runSolver(String pathToFile, int k, List<String> confirmedObligations,
			List<String> obligationLabels, List<String> obligationEventVariables, HashMap<String, Integer> mapOfIDs,
			boolean showSMTOutput, List<String> queryVARS, Graph initialGraph, List<Node> listOfNodes)
			throws IOException {

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
				Solution solution = findSolution(output, obligationLabels, mapOfIDs, queryVARS, k, initialGraph,
						listOfNodes);

				return solution;
			}
		}
		return null;
	}

	Solution findSolution(List<String> output, List<String> obligationLabels, HashMap<String, Integer> mapOfIDs,
			List<String> queryVARS, int k, Graph initialGraph, List<Node> listOfNodes) {
		ObligationFiring[] arrayOfSteps = new ObligationFiring[100];
		String[] assigns = new String[k + 1];
		String[] assocs = new String[k + 1];
		List<Graph> listOfConfigurations = new ArrayList<Graph>();
		List<Variable> variablesList = new ArrayList<Variable>();
		for (String line : output) {
			for (String label : obligationLabels) {
				if (isContain(line, label)) {
					String[] splittedLine = line.split(" ");
					for (String stepNumber : splittedLine) {

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
								} else if (isContain(line2, label + "S_" + stepNumber)) {
									int varAssignmentInt = Integer.parseInt(varAssignment);
									step.setSource(getKeyFromValue(varAssignmentInt, mapOfIDs));
								} else if (isContain(line2, label + "T_" + stepNumber)) {
									int varAssignmentInt = Integer.parseInt(varAssignment);
									step.setTarget(getKeyFromValue(varAssignmentInt, mapOfIDs));
								}

							}
							arrayOfSteps[Integer.parseInt(stepNumber)] = step;
						}
					}
				}
			}

			for (String var : queryVARS) {
				if (isContain(line, var)) {
					String[] splittedLineWithVars = line.split(" ");
					int varAssignment = Integer.parseInt(splittedLineWithVars[splittedLineWithVars.length - 1]);
					String variableName = var.replace("queryVAR", "");
					variablesList.add(new Variable(variableName, getKeyFromValue(varAssignment, mapOfIDs)));
				}
			}

			if (isContain(line, "ASSIGN ")) {
				int index = Integer.parseInt((line.split(" "))[1]);
				assigns[index] = line.replace("union", "").replace("singleton", "").replace("ASSIGN " + index, "");
			}
			if (isContain(line, "ASSOC ")) {
				int index = Integer.parseInt((line.split(" "))[1]);
				assocs[index] = line.replace("union", "").replace("singleton", "").replace("ASSOC " + index, "");
			}

		}
		for (int i = 0; i <= k; i++) {
			Graph graph = new MemGraph();
			try {
				getConfigurationAtTimestep(i, initialGraph, mapOfIDs, graph, assigns[i], assocs[i], listOfNodes);
				if (graph.getNodes().size() != 0)
				{
					if(i==0) {
						Utils.saveGraph(graph, "Policies/ForBMC/TestBuildingGraphForSolution/"+i+"_InitialConfiguration"+ ".json");
					}
					else {
					Utils.saveGraph(graph, "Policies/ForBMC/TestBuildingGraphForSolution/"+i+"_"+arrayOfSteps[i-1].getObligationLabel() + ".json");
					}
					listOfConfigurations.add(graph);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Solution(Arrays.asList(arrayOfSteps).stream().filter(Objects::nonNull).collect(Collectors.toList()),
				new Variables(variablesList), listOfConfigurations);

	}

	private void getConfigurationAtTimestep(int i, Graph initialGraph, HashMap<String, Integer> mapOfIDs, Graph graph,
			String assign, String assoc, List<Node> listOfNodes) {
		Set<String> pcs = new HashSet<String>();
		try {
			pcs = initialGraph.getPolicyClasses();
		} catch (PMException e) {
			e.printStackTrace();
		}
		String[] splittedAssign = assign.split("mkTuple");
		String[] splittedAssoc = assoc.split("mkTuple");

		try {
			processAssignments(splittedAssign, initialGraph, mapOfIDs, pcs, graph, listOfNodes);
			processAssociations(splittedAssoc, mapOfIDs, graph);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void processAssignments(String[] splittedLine, Graph initialGraph, HashMap<String, Integer> mapOfIDs,
			Set<String> pcs, Graph graph, List<Node> listOfNodes) throws Exception {
		List<String[]> listOfTuples = new ArrayList<String[]>();
		
		for (int i = 1; i < splittedLine.length; i++) {
			String[] idTuples = splittedLine[i].split(" ");
			String anc = getKeyFromValue(Integer.parseInt(idTuples[1]), mapOfIDs);
			String desc = getKeyFromValue(Integer.parseInt(idTuples[2]), mapOfIDs);
			if (pcs.contains(desc) && !graph.exists(desc)) {
				graph.createPolicyClass(desc, null);
			}
			listOfTuples.add(new String[] { anc, desc });
		}
		Set<String> newPCs = graph.getPolicyClasses();
		for (String pc : newPCs) {
			buildTreeFromPC(graph, pc, listOfTuples, initialGraph, listOfNodes);
		}
	}

	private static void processAssociations(String[] splittedLine, HashMap<String, Integer> mapOfIDs, Graph graph)
			throws PMException {
		List<String[]> permissions = new ArrayList<String[]>();
		for (int i = 1; i < splittedLine.length; i++) {
			String[] idTuples = splittedLine[i].split(" ");
			String ua = getKeyFromValue(Integer.parseInt(idTuples[1]), mapOfIDs);
			String ar = getKeyFromValue(Integer.parseInt(idTuples[2]), mapOfIDs);
			String at = getKeyFromValue(Integer.parseInt(idTuples[3]), mapOfIDs);
			permissions.add(new String[] { ua, ar, at });
		}
		for (String[] perms : permissions) {
			OperationSet os = new OperationSet();
			String ua = perms[0];
			String ar = perms[1];
			String at = perms[2];
			os.add(ar);
			for (String[] perms2 : permissions) {
				if (ua.equals(perms2[0]) && at.equals(perms2[2])) {
					os.add(perms2[1]);
				}
			}
			graph.associate(ua, at, os);
		}

	}

	private static void buildTreeFromPC(Graph graph, String startingNode, List<String[]> listOfTuples,
			Graph initialGraph, List<Node> listOfNodes) throws Exception {
		List<String[]> descendants = listOfTuples.stream().filter(tuple -> tuple[1].equals(startingNode))
				.collect(Collectors.toList());
		for (String[] desc : descendants) {
			if (!Utils.nodeExistsInList(listOfNodes, desc[0]) || desc[0].equals(startingNode)) {
				continue;
			}
			Node oldGraphDesc = Utils.findNodeInList(listOfNodes, desc[0]);
			if (graph.exists(desc[0])) {
				if (!graph.getChildren(startingNode).contains(desc[0])) {
					graph.assign(desc[0], startingNode);
				}
			} else {
				graph.createNode(desc[0], oldGraphDesc.getType(), null, startingNode);
			}
			buildTreeFromPC(graph, desc[0], listOfTuples, initialGraph, listOfNodes);
		}
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
