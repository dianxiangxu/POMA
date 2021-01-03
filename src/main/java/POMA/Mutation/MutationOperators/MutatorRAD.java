package POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;

import POMA.Utils;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class MutatorRAD extends MutantTester {

	public MutatorRAD(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph);
	}

	public void init() throws PMException, IOException {
		System.out.println("1.");

		this.mutationMethod = "RAD";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsRAD.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
//		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		// getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		// getGraphLoaded(initialGraphConfig);

		// readGPMSGraph();

		Node nodeB, nodePc;

		for (Node nodeA : UAsOAs) {
			System.out.println("2.");

			for (String obName : graph.getParents(nodeA.getName())) {
				System.out.println("3.");

				nodeB = graph.getNode(obName);
				if (nodeB.getType().toString().equals("PC")) {
					System.out.println("cannot reverse assignment of PC.");
//					System.out.println("a is "+ua.toString()+"| b is "+ub.toString());
					continue;
				}

				String pcName = Utils.getPCOf(graph, nodeB.getName());
				if (pcName.isEmpty()) {
					System.out.println("PC not found.");
					continue;
				}
				nodePc = graph.getNode(pcName);
				try {
					performMutation(nodeA, nodeB, nodePc, testMethod, testSuitePath);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				}
			}

		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node nodeA, Node nodeB, Node nodePc, String testMethod, String testSuitePath)
			throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		System.out.println("4.");

			Graph mutant = createCopy();

			mutant = reverseAssignment(mutant, nodeA, nodeB);
			System.out.println("5.");

			// add function to check node connected to PC or not
			if (GraphUtils.isContained(nodeA, nodePc) != true) {
				System.out.println("6.");
				// add assignment if node a is not PC-connected
				mutant.assign(nodeA.getName(), nodePc.getName());
			}
			System.out.println("7.");

			before = getNumberOfKilledMutants();
			testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);
			after = getNumberOfKilledMutants();
			System.out.println("8.");

			if (before == after)
				System.out.println("Unkilled mutant:" + "RAD:" 
							+ "NodeA:" + nodeA.toString() + " || " 
							+ "NodeB:" + nodeB.toString() + " || " 
							+ "PC:" + nodePc.toString());
			setNumberOfMutants(getNumberOfMutants() + 1);
	}

	private Graph reverseAssignment(Graph mutant, Node nodeA, Node nodeB) throws PMException, IOException {
		mutant.deassign(nodeA.getName(), nodeB.getName());
		mutant.assign(nodeB.getName(), nodeA.getName());
		return mutant;
	}
}
