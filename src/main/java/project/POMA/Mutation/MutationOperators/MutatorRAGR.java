package project.POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class MutatorRAGR extends MutantTester {
	public void init(String testMethod) throws PMException, IOException {
		this.mutationMethod = "RAGR";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsRAGR.csv";
		String testSuitePath = "CSV/testSuits/"+testMethod+"testSuite.csv";
//		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		Node nodeB, nodePc;
		
		for (Node nodeA : UAsOAs) {
			for (String nodeBName : graph.getParents(nodeA.getName())) {
				nodeB = graph.getNode(nodeBName);
				if (nodeB.getType().toString().equals("PC")) {
//					System.out.println("cannot reverse assignment of PC.");
//					System.out.println("a is "+ua.toString()+"| b is "+ub.toString());
					continue;
				}
				
				nodePc = GraphUtils.getPcOf(nodeB);
//				System.out.println("Debug:" + "RAD:" + "a:" + ua.toString() + " || " + "b:" + ub.toString() + " || " + "pc:" + pc.toString());
				if (nodePc == nodeA) {
					System.out.println("PC not found.");
					continue;
				}
				performMutation(nodeA, nodeB, nodePc, testMethod, testSuitePath);
			}
			
		}
		saveCSV(data, new File(testResults), testMethod);
		

	}

	private void performMutation(Node nodeA, Node nodeB, Node nodePc, String testMethod, String testSuitePath) throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		
		try {
			Graph mutant = createCopy();
			
			mutant = RemoveAssignment(mutant, nodeA, nodeB);
			
			//if a cycle, here will throw e, below will not be conducted

			if (GraphUtils.isContained(nodeA, nodePc) != true) {
				//add assignment if node a is not PC-connected
				mutant.assign(nodeA.getName(), nodePc.getName());
			}
			
			before = getNumberOfKilledMutants();
			testMutant(mutant, testSuite,testMethod , getNumberOfMutants(), mutationMethod);
			after = getNumberOfKilledMutants();
			
			if (before == after)
				System.out.println("Unkilled mutant:" + "RAGR:" + "a:" + nodeA.toString() + " || " + "b:" + nodeB.toString() + " || " + "pc:" + nodePc.toString());
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
		catch (PMException e) {
			//throw an error when detecting cycle after reverse assignment
			e.printStackTrace();
			System.out.println("RAGR_" + nodeA.toString() + "_" + nodeB.toString() + "_" + nodePc.toString());
		}
	}

	private Graph RemoveAssignment(Graph mutant, Node nodeA, Node nodeB) throws PMException, IOException {
		mutant.deassign(nodeA.getName(), nodeB.getName());
		return mutant;
	}
}
