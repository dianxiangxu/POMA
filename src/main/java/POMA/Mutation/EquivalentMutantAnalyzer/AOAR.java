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

public class AOAR extends MutantTester {
	int i;
	static Graph g;
	public AOAR(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AOAR";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAOAR.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		OperationSet operationSet = Utils.getAllAccessRights();
//		System.out.println(operationSet);
		List<Prohibition> prohibitionList = Utils.getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			String subject = p.getSubject();
			Set<String> ops = p.getOperations();
			
			
			for (String newOperation : operationSet) {
				OperationSet operations = new OperationSet();
				if (ops.contains(newOperation))
					continue;
				for (String s : ops) {
					operations.add(s);
				}
				operations.add(newOperation);
				
				AccessRequest q = decideEquivalentMutantForAOAR(name, subject, operations);
				if (q == null) {
					System.out.println("Mutant equivalent!(AOAR:" + i + ") Add One Access Right:" + newOperation + " in " + name);
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(AOAR:" + i + ") Add One Access Right:" + newOperation + " in " + name);
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
			}
		}
//		System.out.print(i); //i = 43
	}
	
	public AccessRequest decideEquivalentMutantForAOAR (String prohibitionName, String subject, OperationSet operations) throws PMException {
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
