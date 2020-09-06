package project.POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class MutatorCAD extends MutantTester {
	public void init(String testMethod) throws PMException, IOException {
		this.mutationMethod = "CAD";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsCAD.csv";
		String testSuitePath = "CSV/testSuits/"+testMethod+"testSuite.csv";
//		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		Node nodeB;
		
		for (Node nodeA : UAsOAs) {
			for (String obName : graph.getParents(nodeA.getName())) {
				nodeB = graph.getNode(obName);
				if (nodeB.getType().toString().equals("PC")) {
//					System.out.println("cannot reverse assignment of PC.");
//					System.out.println("a is "+ua.toString()+"| b is "+ub.toString());
					continue;
				}
				for (Node nodeC : UAsOAs) {
					if (nodeC.getType() != nodeA.getType()) {
						continue;
					}
					if (nodeA.toString().equals(nodeC.toString()) || nodeB.toString().equals(nodeC.toString())) {
						continue;
					}
					if (GraphUtils.isContained(nodeC, nodeA)) {
						//this will incur a cycle
						continue;
					}
					if (graph.isAssigned(nodeA.getName(), nodeC.getName())) {
						//assignment <a,c> already exists
						continue;
					}
					performMutation(nodeA, nodeB, nodeC, testMethod, testSuitePath);
				}
			}
			
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node nodeA, Node nodeB, Node nodeC, String testMethod, String testSuitePath) throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		
		try {
			Graph mutant = createCopy();
			
			mutant = changeAssignmentDescendent(mutant, nodeA, nodeB, nodeC);
			
			//if a cycle, here will throw e, below will not be conducted

			before = getNumberOfKilledMutants();
			testMutant(mutant, testSuite,testMethod , getNumberOfMutants(), mutationMethod);
			after = getNumberOfKilledMutants();
			
			if (before == after)
				System.out.println("Unkilled mutant:" + "CAD:" + "a:" + nodeA.toString() + " || " + "b:" + nodeB.toString() + " || " + "c:" + nodeC.toString());
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
		catch (PMException e) {
			//throw an error when detecting cycle after reverse assignment
			e.printStackTrace();
			System.out.println("CAD_" + nodeA.toString() + "_" + nodeB.toString() + "_" + nodeC.toString());
		}
	}

	private Graph changeAssignmentDescendent(Graph mutant, Node nodeA, Node nodeB, Node nodeC) throws PMException, IOException {
		mutant.deassign(nodeA.getName(), nodeB.getName());
		mutant.assign(nodeA.getName(), nodeC.getName());
		return mutant;
	}
}
