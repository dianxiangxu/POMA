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

public class CUAA extends MutantTester {
	int i;
	static Graph g;
	public CUAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "CUAA";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCUAA.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		for (Node oldSourceNode : UAs) {
			if (graph.getSourceAssociations(oldSourceNode.getName()) == null)
				continue;
			Map<String, OperationSet> sources = graph.getSourceAssociations(oldSourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				
				for (Node newSourceNode : UAs) {
					if (!newSourceNode.getName().equals(oldSourceNode.getName()) && !newSourceNode.getName().equals(targetNode)) {
						AccessRequest q = decideEquivalentMutantForCUAA(oldSourceNode.getName(), newSourceNode.getName(), targetNode, accessRights);
						if (q == null) {
							System.out.println("Mutant equivalent!(CUAA:" + i + ") Change User Attribute in Association (" + oldSourceNode.getName() + "," + targetNode + ") to (" + newSourceNode.getName() + "," + targetNode + ")");
						} else {
							Utils.addToARList(q);
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//							System.out.println("Mutant not equivalent!(CUAA:" + i + ") Change User Attribute in Association (" + oldSourceNode.getName() + "," + targetNode + ") to (" + oldSourceNode.getName() + "," + targetNode + ")");
//							System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
						}
						i++;
						setNumberOfMutants(getNumberOfMutants() + 1);
					}
				}
			}
		}
//		System.out.print(i); //i = 57
	}
	
	public AccessRequest decideEquivalentMutantForCUAA (String oldSourceNode, String newSourceNode, String targetNode, OperationSet accessRights) throws PMException {
//		System.out.printf(oldSourceNode + "|" + newSourceNode + "|" + targetNode + "\n");
		Set<String> attributeToCheckList = new HashSet<String>();
		Set<String> ascendantListA = new HashSet<String>();
		Set<String> ascendantListB = new HashSet<String>();
		Set<String> ascendantListC = new HashSet<String>();
		
		Utils.getAllAscendant(oldSourceNode, ascendantListA, graph);
		Utils.getAllAscendant(newSourceNode, ascendantListB, graph);
		
		attributeToCheckList.addAll(ascendantListA);
		attributeToCheckList.add(oldSourceNode);
		attributeToCheckList.addAll(ascendantListB);
		attributeToCheckList.add(newSourceNode);
		
		for (String ascendant : attributeToCheckList) {
			
			PReviewDecider deciderO = new PReviewDecider(createCopy(), prohibitions);
			Map<String, Set<String>> CapabilityList = deciderO.getCapabilityList(ascendant, null);
			
			Graph mutant = createCopy();
			mutant.dissociate(oldSourceNode, targetNode);
			try {
				mutant.associate(newSourceNode, targetNode, accessRights);
			} catch(IllegalArgumentException e) {
				return null;
			}
			
			PReviewDecider deciderM = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> CapabilityListMutant = deciderM.getCapabilityList(ascendant, null);
			
			AccessRequest q = Utils.pretestArlist(deciderO, deciderM, arList);
			if (q != null)
				return q;
			
			q = CapabilityList.size() >= CapabilityListMutant.size() ? 
				Utils.compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
				Utils.compareTwoLists(CapabilityListMutant, CapabilityList, "UA");

			if (q != null)
				return new AccessRequest(ascendant, q.getAR(), q.getTA());
		}
		
		Utils.getAllAscendant(targetNode, ascendantListC, graph);
		for (String ascendant : ascendantListC) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);
			
			mutant.dissociate(oldSourceNode, targetNode);
			mutant.associate(newSourceNode, targetNode, accessRights);
			
			decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACLM = decider.generateACL(ascendant, null);
			
			AccessRequest q = ACL.size() >= ACLM.size() ?
					Utils.compareTwoLists(ACL, ACLM, "OA") :
						Utils.compareTwoLists(ACLM, ACL, "OA");
					if (q != null)
						return new AccessRequest(q.getSA(), q.getAR(), ascendant);
		}
		
		return null;
	}
}
