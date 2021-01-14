package POMA.Mutation.ProhibitionMutationOperators;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;

//Remove one container
public class MutatorROCT extends ProhibitionMutation {
	public MutatorROCT(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions);
		// TODO Auto-generated constructor stub
	}

	public void main(Graph graph) throws PMException, IOException {
		//use default graph now
//		getGraphLoaded();
		//getProhibitionLoaded($PATH);

//		getProhibitionLoaded("src/main/java/POMA/Mutation/ProhibitionMutationOperators/prohibitions1.json");

		calculateOriginTestResults();
		//change subject in prohibition1, save mutant to mutant.json//only for testing
		List<Prohibition> prohibitionList = getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			Prohibitions mutant = createCopy();
			Prohibition prohibitionMutant = mutant.get(name);
			Map<String, Boolean> containers = prohibitionMutant.getContainers();
			Set<String> containersKeySet = containers.keySet();
			for (String containerToRemove : containersKeySet) {
//				System.out.println(containerToRemove);
				boolean complement = containers.get(containerToRemove);
				
				containers.remove(containerToRemove);
				
				//update prohibition
				mutant.update(name, prohibitionMutant);
				
				//try kill mutant
				ArrayList<Boolean> results = getMutantTestResults(mutant);
				if (compareTestResults(getOriginTestResults(), results)) {
					System.out.println("Mutant is not killed!");
					System.out.println("Origin tests results:" + getOriginTestResults());
					System.out.println("Mutant tests results:" + results);
				} else {
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
				}
				setNumberOfMutants(getNumberOfMutants() + 1);
				
				saveDataToFile(ProhibitionsSerializer.toJson(mutant), "src/main/java/POMA/Mutation/ProhibitionMutationOperators/mutant.json");
				
				//restore containers conditions
				containers.put(containerToRemove, complement);
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
