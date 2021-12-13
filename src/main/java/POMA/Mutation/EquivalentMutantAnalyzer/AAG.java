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

public class AAG extends MutantTester {
	int i;
	static Graph g;
	public AAG(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AAG";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAAG.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		//Add a new assignment (nodeA, nodeB)
		//add assignment between user attributes
		for (Node nodeA : UAsOAs) {
			
			for (Node nodeB : UAsOAs) {
				if (nodeB.getType() != nodeA.getType()) {
				// nodes in one assignment must share same node type
				continue;
				}
				if (nodeA.toString().equals(nodeB.toString())) {
					// nodes in one assignment must be different nodes
					continue;
				}
				if (Utils.isContained(nodeB, nodeA, graph)) {
					//this will incur a cycle
					continue;
				}
				if (Utils.isContained(nodeA, nodeB, graph)) {
					//this does not make sense
					continue;
				}
//				if (graph.isAssigned(nodeA.getName(), nodeB.getName())) {
//					// assignment <a,b> already exists
//					continue;
//				}
				
				//From here, adding assignment (A,B) is legal
				AccessRequest q = decideEquivalentMutantForAAG(nodeA, nodeB);
				if (q == null) {
					System.out.println("Mutant equivalent!(AAG:" + i + ") Add assignment from " + nodeA.getName() + " to " + nodeB.getName());
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(AAG)Add assignment from " + nodeA.getName() + " to " + nodeB.getName());
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
			}
		}
	}
	
	public AccessRequest decideEquivalentMutantForAAG (Node nodeA, Node nodeB) throws PMException {
		if (nodeA.getType().toString().equals("UA")) {
			Set<String> ascendantList = new HashSet<String>();
			
			Utils.getAllAscendant(nodeA.getName(), ascendantList, graph);
			ascendantList.add(nodeA.getName());
			for (String ascendant : ascendantList) {
				Graph mutant = createCopy();
				PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
				Map<String, Set<String>> CapabilityList = decider.getCapabilityList(ascendant, null);
				
				mutant.assign(nodeA.getName(), nodeB.getName());
				decider = new PReviewDecider(mutant, prohibitions);
				Map<String, Set<String>> CapabilityListMutant = decider.getCapabilityList(ascendant, null);
				
				AccessRequest q;
				q = CapabilityList.size() >= CapabilityListMutant.size() ? 
					Utils.compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
					Utils.compareTwoLists(CapabilityListMutant, CapabilityList, "UA");

				if (q != null)
					return new AccessRequest(ascendant, q.getAR(), q.getTA());
			}
			return null;
		}
		
		//if type of nodeA is UA, it is possible that nodeA serves as target attribute in an association
		Set<String> ascendantList = new HashSet<String>();
		
		Utils.getAllAscendant(nodeA.getName(), ascendantList, graph);
		ascendantList.add(nodeA.getName());
		
		for (String ascendant : ascendantList) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);

			mutant.assign(nodeA.getName(), nodeB.getName());
			decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACLM = decider.generateACL(ascendant, null);
			
			AccessRequest q;
			
			q = ACL.size() >= ACLM.size() ?
				Utils.compareTwoLists(ACL, ACLM, "OA") :
				Utils.compareTwoLists(ACLM, ACL, "OA");
			if (q != null)
				return new AccessRequest(q.getSA(), q.getAR(), ascendant);
		}
		
		return null;
	}
	
}
