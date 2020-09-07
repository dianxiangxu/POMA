package POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class MutatorAARA extends MutantTester {
	OperationSet allAccessRightSet;

	public void init(String testMethod) throws PMException, IOException {
		this.mutationMethod = "AARA";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsAARA.csv";
		String testSuitePath = "CSV/testSuits/"+testMethod+"testSuite.csv";
		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
//		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		
		allAccessRightSet = GraphUtils.getAllAccessRights();
		for (Node SourceNode : UAs) {
			performMutation(SourceNode, testMethod, testSuitePath);
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node SourceNode, String testMethod, String testSuitePath) throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		
		if (graph.getSourceAssociations(SourceNode.getName()) == null) {
			return;
		}
		
		Map<String, OperationSet> sources = graph.getSourceAssociations(SourceNode.getName());
		List<String> targetNodes = new ArrayList<String>(sources.keySet());
		
		try {
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				
				for (String accessRight : allAccessRightSet) {
					if (accessRights.contains(accessRight)) {
						continue;
					}
					Graph mutant = createCopy();
					
					addAccessRightToAssociate(mutant, SourceNode.getName(), targetNode, accessRights, accessRight);
					before = getNumberOfKilledMutants();
					testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);
					after = getNumberOfKilledMutants();
					if (before == after) {
						System.out.println("Unkilled mutant:" + "AARA:" + "SourceNode:" + SourceNode.getName() + " || " + "targetNode:" + targetNode + " || " + "accessRights:" + accessRights.toString() + " || " + "addedAR:" + accessRight);
					}
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
		catch (PMException e) {
			e.printStackTrace();
		}
	}

	private Graph addAccessRightToAssociate(Graph mutant, String SourceName, String targetName, OperationSet accessRights, String accessRight) throws PMException, IOException {
		OperationSet tmpAccessRights = new OperationSet();
		tmpAccessRights.addAll(accessRights);
		tmpAccessRights.add(accessRight);
		mutant.dissociate(SourceName, targetName);
		mutant.associate(SourceName, targetName, accessRights);
		return mutant;
	}
	

}