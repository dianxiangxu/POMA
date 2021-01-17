package POMA.Mutation.ProhibitionMutationOperators;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;

//Change one operation
public class MutatorCOO extends ProhibitionMutation {
	public MutatorCOO(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions);
		// TODO Auto-generated constructor stub
	}

	public void main(Graph graph) throws PMException, IOException {
		//use default graph now
//		getGraphLoaded();
		//getProhibitionLoaded($PATH);

//		getProhibitionLoaded("src/main/java/POMA/Mutation/ProhibitionMutationOperators/prohibitions1.json");

		calculateOriginTestResults();
		
		//get all available operations to change
		OperationSet operationSet = getAllAccessRights(g);
		System.out.println(operationSet);
		
		//change subject in prohibition1, save mutant to mutant.json//only for testing
		List<Prohibition> prohibitionList = getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			Prohibitions mutant = createCopy();
			Prohibition prohibitionMutant = mutant.get(name);
			Set<String> ops = prohibitionMutant.getOperations();
			OperationSet operations = new OperationSet();
			
			
			for (String newOperation : operationSet) {
				if (ops.contains(newOperation)) {
					//the target_to_add operation is already contained in ops, skip this mutant
					continue;
				}
				for (String opToChange: ops) {
					//change opToChange to newOperation
					for (String s : ops) {
						if (s.equals(opToChange))
							continue;
						operations.add(s);
					}
					operations.add(newOperation);
					prohibitionMutant.setOperations(operations);
					
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
					
					//restore to origin mutant
					operations.remove(newOperation);
					operations.add(opToChange);
					prohibitionMutant.setOperations(operations);
					operations.clear();
				}
				
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
