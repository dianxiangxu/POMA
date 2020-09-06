package project.POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class MutatorAAGR extends MutantTester {
	public void init(String testMethod) throws PMException, IOException {
		this.mutationMethod = "AAGR";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsAAGR.csv";
		String testSuitePath = "CSV/testSuits/"+testMethod+"testSuite.csv";
//		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		
		for (Node nodeA : UAsOAs) {
			for (Node nodeB : UAsOAs) {
				if (nodeB.getType() != nodeA.getType()) {
					//nodes in one assignment must share same node type
					continue;
				}
				if (nodeA.toString().equals(nodeB.toString())) {
					//nodes in one assignment must be different nodes
					continue;
				}
				if (GraphUtils.isContained(nodeB, nodeA)) {
					//this will incur a cycle
					continue;
				}
				if (graph.isAssigned(nodeA.getName(), nodeB.getName())) {
					//assignment <a,b> already exists
					continue;
				}
				performMutation(nodeA, nodeB, testMethod, testSuitePath);
			}
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node nodeA, Node nodeB, String testMethod, String testSuitePath) throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		
		try {
			Graph mutant = createCopy();
			
			mutant = addAssignment(mutant, nodeA, nodeB);
			
			//if a cycle, here will throw e, below will not be conducted

			before = getNumberOfKilledMutants();
			testMutant(mutant, testSuite,testMethod , getNumberOfMutants(), mutationMethod);
			after = getNumberOfKilledMutants();
			
			if (before == after)
				System.out.println("Unkilled mutant:" + "CAD:" + "a:" + nodeA.toString() + " || " + "b:" + nodeB.toString());
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
		catch (PMException e) {
			//throw an error when detecting cycle after reverse assignment
			e.printStackTrace();
			System.out.println("CAD_" + nodeA.toString() + "_" + nodeB.toString());
		}
	}

	private Graph addAssignment(Graph mutant, Node nodeA, Node nodeB) throws PMException, IOException {
		mutant.assign(nodeA.getName(), nodeB.getName());
		return mutant;
	}
}
