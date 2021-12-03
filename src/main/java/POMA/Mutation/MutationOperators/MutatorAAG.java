package POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
//add assignment
public class MutatorAAG extends MutantTester {
	int i;
	public MutatorAAG(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AAG";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAAG.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
//		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		// getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		// getGraphLoaded(initialGraphConfig);

		// readGPMSGraph();

		for (Node nodeA : UAsOAs) {
			for (Node nodeB : UAsOAs) {
				if (nodeB.getType() != nodeA.getType()) {
					// nodes in one assignment must share same node type
					continue;
				}
				if (nodeA.toString().equals(nodeB.toString())) {
					// nodes in one assignment must be different nodes
					continue;
				}
				if (GraphUtils.isContained(nodeB, nodeA)) {
					//this will incur a cycle
					continue;
				}
				if (GraphUtils.isContained(nodeA, nodeB)) {
					//this does not make sense
					continue;
				}
				if (graph.isAssigned(nodeA.getName(), nodeB.getName())) {
					// assignment <a,b> already exists
					continue;
				}
				try {
					performMutation(nodeA, nodeB, testMethod, testSuitePath);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node nodeA, Node nodeB, String testMethod, String testSuitePath)
			throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		i++;
		
		Graph mutant = createCopy();

		mutant = addAssignment(mutant, nodeA, nodeB);

		before = getNumberOfKilledMutants();
		testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);
		after = getNumberOfKilledMutants();

		if (before == after)
			System.out
					.println("Unkilled mutant" + "(AAG:" + i + ")a:" + nodeA.toString() + " || " + "b:" + nodeB.toString());
		setNumberOfMutants(getNumberOfMutants() + 1);
	}

	private Graph addAssignment(Graph mutant, Node nodeA, Node nodeB) throws PMException, IOException {
		mutant.assign(nodeA.getName(), nodeB.getName());
		return mutant;
	}
}
