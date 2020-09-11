package POMA.Mutation.MutationOperators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.graph.model.nodes.NodeType;

public class MutatorAACR extends MutantTester {
	OperationSet allAccessRightSet;

	public MutatorAACR(String testMethod) {
		super(testMethod);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AACR";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAACR.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		// getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
		// getGraphLoaded("GPMSPolicies/bank_policy_config.json");
		// getGraphLoaded(initialGraphConfig);

		// readGPMSGraph();

		allAccessRightSet = GraphUtils.getAllAccessRights();
		 int i = 0;
		for (Node sourceNode : UAs) {
			 i++;
			 System.out.println(i);
			performMutation(sourceNode, testMethod, testSuitePath);
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node sourceNode, String testMethod, String testSuitePath)
			throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;

		try {
			for (Node targetNode : UAsOAs) {
				if (targetNode.getName().equals(sourceNode.getName())) {
					continue;
				}

				// avoid add extra association between two nodes
				if (graph.getSourceAssociations(sourceNode.getName()) != null) {
					Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
					List<String> targetNodes = new ArrayList<String>(sources.keySet());
					if (targetNodes.contains(targetNode.getName())) {
						continue;
					}
				}

				for (String accessRight : allAccessRightSet) {

					Graph mutant = createCopy();
					OperationSet accessRights = new OperationSet();

					accessRights.add(accessRight);

					try {
						addAssociate(mutant, sourceNode.getName(), targetNode.getName(), accessRights);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
						continue;
					}
					before = getNumberOfKilledMutants();

					testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);

					after = getNumberOfKilledMutants();

					if (before == after) {
						 System.out.println("Unkilled mutant:" + "AACR:" 
								 	+ "sourceNode:" + sourceNode.getName() + " || " 
								 	+ "targetNode:" + targetNode);
					}

					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		} catch (PMException e) {
			e.printStackTrace();
		}
	}

	private Graph addAssociate(Graph mutant, String SourceName, String targetName, OperationSet accessRights)
			throws PMException, IOException {
		mutant.associate(SourceName, targetName, accessRights);
		return mutant;
	}
}