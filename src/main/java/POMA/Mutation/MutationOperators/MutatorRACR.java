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

public class MutatorRACR extends MutantTester {
	public MutatorRACR(String testMethod, Graph graph) {
		super(testMethod, graph);
	}
	public void init() throws PMException, IOException {
		this.mutationMethod = "RACR";
		String testResults = "CSV/"+testMethod+"/"+testMethod+"testResultsRACR.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		//getGraphLoaded("GPMSPolicies/gpms_testing_config.json");
//		getGraphLoaded("GPMSPolicies/bank_policy_config.json");
	//	getGraphLoaded(initialGraphConfig);

	//	readGPMSGraph();

		for (Node sourceNode : UAs) {
			performMutation(sourceNode, testMethod, testSuitePath);
		}
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(Node sourceNode, String testMethod, String testSuitePath) throws PMException, IOException {
		File testSuite = new File(testSuitePath);
		double before, after;
		
		if (graph.getSourceAssociations(sourceNode.getName()) == null) {
			return;
		}
		
		Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
		List<String> targetNodes = new ArrayList<String>(sources.keySet());
		
		for (String targetNode : targetNodes) {
			Graph mutant = createCopy();
					
			removeAssociate(mutant, sourceNode.getName(), targetNode);
				
			before = getNumberOfKilledMutants();
			try {
				testMutant(mutant, testSuite, testMethod, getNumberOfMutants(), mutationMethod);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				continue;
			}
			after = getNumberOfKilledMutants();
			if (before == after) {
				System.out.println("Unkilled mutant:" + "RACR:" 
							+ "sourceNode:" + sourceNode.getName() + " || " 
							+ "TargetNode:" + targetNode);
			}
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
	}

	private Graph removeAssociate(Graph mutant, String SourceName, String targetName) throws PMException, IOException {
		mutant.dissociate(SourceName, targetName);
		return mutant;
	}
}