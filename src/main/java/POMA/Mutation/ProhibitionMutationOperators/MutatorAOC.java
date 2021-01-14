package POMA.Mutation.ProhibitionMutationOperators;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;

//Add one container
public class MutatorAOC extends ProhibitionMutation {
	public MutatorAOC(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions);
		// TODO Auto-generated constructor stub
	}

	public void main(Graph graph) throws PMException, IOException {
		//use default graph if no graph
		if (graph == null)
			getDefaultGraphLoaded();
		//getProhibitionLoaded(graph);

		
//		getProhibitionLoaded("src/main/java/POMA/Mutation/ProhibitionMutationOperators/prohibitions1.json");

		calculateOriginTestResults();
		
		//get all available containers/nodes
		Node[] nodes = getNodesInGraph();
		
		//change subject in prohibition1, save mutant to mutant.json//only for testing
		List<Prohibition> prohibitionList = getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			Prohibitions mutant = createCopy();
			Prohibition prohibitionMutant = mutant.get(name);
			Map<String, Boolean> containers = prohibitionMutant.getContainers();
			Set<String> containersKeySet = containers.keySet();
			//mutant: default complement is true
			System.out.println("Start mutator AOC/(complement=true)");
			for (Node containerToAdd : nodes) {
				//do not add duplicate node/container
				if (containersKeySet.contains(containerToAdd.getName()))
					continue;
				
				containers.put(containerToAdd.getName(), true);
				
				//update prohibition
				mutant.update(name, prohibitionMutant);
				
				//try kill mutant
				ArrayList<Boolean> results = getMutantTestResults(mutant);
				if (compareTestResults(getOriginTestResults(), results)) {
					System.out.println("Mutant is not killed! Added container is " + containerToAdd.getName());
					System.out.println("Origin tests results:" + getOriginTestResults());
					System.out.println("Mutant tests results:" + results);
				} else {
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
				}
				setNumberOfMutants(getNumberOfMutants() + 1);
//				System.out.println(node.getName());
				
				saveDataToFile(ProhibitionsSerializer.toJson(mutant), "src/main/java/POMA/Mutation/ProhibitionMutationOperators/mutant.json");
				//restore containers conditions
				containers.remove(containerToAdd.getName());
			}
			
			//mutant: default complement is false
			System.out.println("Start mutator AOC/(complement=false)");
			for (Node containerToAdd : nodes) {
				//do not add duplicate node/container
				if (containersKeySet.contains(containerToAdd.getName()))
					continue;
				//default complement is false
				containers.put(containerToAdd.getName(), false);
				
				//update prohibition
				mutant.update(name, prohibitionMutant);
				
				//try kill mutant
				ArrayList<Boolean> results = getMutantTestResults(mutant);
				if (compareTestResults(getOriginTestResults(), results)) {
					System.out.println("Mutant is not killed! Added container is " + containerToAdd.getName());
					System.out.println("Origin tests results:" + getOriginTestResults());
					System.out.println("Mutant tests results:" + results);
				} else {
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
				}
				setNumberOfMutants(getNumberOfMutants() + 1);
//				System.out.println(node.getName());
				
				saveDataToFile(ProhibitionsSerializer.toJson(mutant), "src/main/java/POMA/Mutation/ProhibitionMutationOperators/mutant.json");
//				if (containerToAdd.getName().equals("UA2_2"))
//					break;
				//restore containers conditions
				containers.remove(containerToAdd.getName());
			}
			
		}
		if (getNumberOfMutants() == 0) {
			System.out.println("No mutant generated!");
		} else {
			System.out.println("#Mutant=" + getNumberOfMutants() + 
					"    #Killed=" + getNumberOfKilledMutants() + 
					"    MS=" + getNumberOfKilledMutants()*100/getNumberOfMutants() + "%");
		}
	}
		
	
	
}
