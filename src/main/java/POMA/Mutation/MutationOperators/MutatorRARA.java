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

public class MutatorRARA extends MutantTester {
	public void init(String testMethod) throws PMException, IOException {
		this.mutationMethod = "RARA";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsRARA.csv";
		String testSuitePath = "CSV/testSuits/"+testMethod+"testSuite.csv";
		getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
//		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		
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
				
				for (String accessRight : accessRights) {
					Graph mutant = createCopy();
					
					removeAccessRightFromAssociate(mutant, SourceNode.getName(), targetNode, accessRights, accessRight);
					before = getNumberOfKilledMutants();
					testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);
					after = getNumberOfKilledMutants();
					if (before == after) {
						System.out.println("Unkilled mutant:" + "RARA:" + "SourceNode:" + SourceNode.getName() + " || " + "targetNode:" + targetNode + " || " + "accessRights:" + accessRights.toString() + " || " + "removedAR:" + accessRight);
					}
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
		catch (PMException e) {
			e.printStackTrace();
		}
	}

	private Graph removeAccessRightFromAssociate(Graph mutant, String SourceName, String targetName, OperationSet accessRights, String accessRight) throws PMException, IOException {
		OperationSet tmpAccessRights = new OperationSet();
		tmpAccessRights.addAll(accessRights);
		tmpAccessRights.remove(accessRight);
		mutant.dissociate(SourceName, targetName);
		mutant.associate(SourceName, targetName, accessRights);
		return mutant;
	}
}