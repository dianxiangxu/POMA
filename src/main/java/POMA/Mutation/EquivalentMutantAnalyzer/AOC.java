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

public class AOC extends MutantTester {
	int i, j;
	static Graph g;
	public AOC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 0;
		j = 0;
		
		Node[] nodes = Utils.getNodesInGraph();
		List<Prohibition> prohibitionList = Utils.getProhibitionList();
		
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			String subject = p.getSubject();
			Map<String, Boolean> containers = p.getContainers();
			Set<String> containersKeySet = containers.keySet();
			
			for (Node containerToAdd : nodes) {
				//do not add duplicate node/container
				if (containersKeySet.contains(containerToAdd.getName()))
					continue;
				//exclude senseless container
				if (graph.getChildren(containerToAdd.getName()).isEmpty())
					continue;
				
				AccessRequest q = decideEquivalentMutantForAOC(name, subject, containerToAdd, true);
				if (q == null) {
					System.out.println("Mutant equivalent!(AOC:" + i + ") Add One Container: " + containerToAdd.getName() + "(true) in " + name);
					j++;
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(AOC:" + i + ") Add One Container: " + containerToAdd.getName() + "(true) in " + name);
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
				
				q = decideEquivalentMutantForAOC(name, subject, containerToAdd, false);
				if (q == null) {
					System.out.println("Mutant equivalent!(AOC:" + i + ") Add One Container: " + containerToAdd.getName() + "(false) in " + name);
					j++;
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(AOC:" + i + ") Add One Container: " + containerToAdd.getName() + "(false) in " + name);
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
			}
		}
//		System.out.println(i); //i = 130
//		System.out.println(j); //j=78
	}
	
	public AccessRequest decideEquivalentMutantForAOC (String prohibitionName, String subject, Node containerToAdd, Boolean complement) throws PMException {
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
			prohibitionMutant.addContainer(containerToAdd.getName(), complement);
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
