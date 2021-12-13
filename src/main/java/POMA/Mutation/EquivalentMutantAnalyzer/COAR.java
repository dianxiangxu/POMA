package POMA.Mutation.EquivalentMutantAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IteratorUtils;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pdp.decider.Decider;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class COAR extends MutantTester {
	int i;
	static Graph g;
	public COAR(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "COAR";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCOAR.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		OperationSet operationSet = Utils.getAllAccessRights();
		List<Prohibition> prohibitionList = Utils.getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			String subject = p.getSubject();
			Set<String> ops = p.getOperations();
			
			
			for (String newOperation : operationSet) {
				if (ops.contains(newOperation)) {
					//the target_to_add operation is already contained in ops, skip this mutant
					continue;
				}
				for (String opToChange: ops) {
					OperationSet operations = new OperationSet();
					//change opToChange to newOperation
					for (String s : ops) {
						if (s.equals(opToChange))
							continue;
						operations.add(s);
					}
					operations.add(newOperation);
					
					AccessRequest q = decideEquivalentMutantForCOAR(name, subject, operations);
					if (q == null) {
						System.out.println("Mutant equivalent!(COAR:" + i + ") Change One Access Right:" + opToChange + " to " + newOperation + " in " + name);
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(COAR:" + i + ") Change One Access Right:" + opToChange + " to " + newOperation + " in " + name);
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
		
		
//		System.out.print(i); //i = 67
	}
	
	public AccessRequest decideEquivalentMutantForCOAR (String prohibitionName, String subject, OperationSet operations) throws PMException {
//		System.out.printf(oldSourceNode + "|" + newSourceNode + "|" + targetNode + "\n");
		Set<String> attributeToCheckList = new HashSet<String>();
		Set<String> ascendantList = new HashSet<String>();
		
		Utils.getAllAscendant(subject, ascendantList, graph);
		attributeToCheckList.addAll(ascendantList);
		attributeToCheckList.add(subject);
		
		for (String ascendant : attributeToCheckList) {
			Prohibitions mutant = Utils.createProhibitionsCopy();
			
			PReviewDecider decider = new PReviewDecider(graph, mutant);
			Map<String, Set<String>> CapabilityList = decider.getCapabilityList(ascendant, null);
			
			Prohibition prohibitionMutant = mutant.get(prohibitionName);
			
			//make changes to operation set
			prohibitionMutant.setOperations(operations);
			//update prohibition
			mutant.update(prohibitionName, prohibitionMutant);
			
			decider = new PReviewDecider(graph, mutant);
			Map<String, Set<String>> CapabilityListMutant = decider.getCapabilityList(ascendant, null);
			
			AccessRequest q = CapabilityList.size() >= CapabilityListMutant.size() ? 
				Utils.compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
				Utils.compareTwoLists(CapabilityListMutant, CapabilityList, "UA");

			if (q != null)
				return new AccessRequest(ascendant, q.getAR(), q.getTA());
		}
		
		return null;
	}
}
