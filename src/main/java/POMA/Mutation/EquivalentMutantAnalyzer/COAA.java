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
import gov.nist.csd.pm.pip.graph.model.nodes.NodeType;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.MutationOperators.GraphUtils;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class COAA extends MutantTester {
	int i;
	static Graph g;
	public COAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "COAA";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCOAA.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		for (Node sourceNode : UAs) {
			if (graph.getSourceAssociations(sourceNode.getName()) == null)
				continue;
			Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
			List<String> oldTargetNodes = new ArrayList<String>(sources.keySet());
			for (String oldTargetNode : oldTargetNodes) {
				Set<String> operateSet = sources.get(oldTargetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				
				for (Node newTargetNode : UAsOAs) {
					if (newTargetNode.getName().equals(sourceNode.getName())) {
						continue;
					}
					if (newTargetNode.getName().equals(oldTargetNode)) {
						continue;
					}
					if (newTargetNode.getType().equals(NodeType.UA)) {
						if (Utils.isContained(newTargetNode, sourceNode, graph)) {
							continue;
						}
					}
					
					AccessRequest q = decideEquivalentMutantForCOAA(sourceNode.getName(), oldTargetNode, newTargetNode.getName(), accessRights);
					if (q == null) {
						System.out.println("Mutant equivalent!(COAA:" + i + ") Change Object Attribute in Association (" + sourceNode.getName() + "," + oldTargetNode + ") to (" + sourceNode.getName() + "," + newTargetNode + ")");
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(COAA:" + i + ") Change Object Attribute in Association (" + oldSourceNode.getName() + "," + targetNode + ") to (" + oldSourceNode.getName() + "," + targetNode + ")");
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
			
		}
//		System.out.print(i); //i = 60
	}
	
	public AccessRequest decideEquivalentMutantForCOAA (String SourceName, String oldTargetName, String newTargetName, OperationSet accessRights) throws PMException {
//		System.out.printf(oldSourceNode + "|" + newSourceNode + "|" + targetNode + "\n");
		Set<String> attributeToCheckList = new HashSet<String>();
		Set<String> ascendantListA = new HashSet<String>();
		Set<String> ascendantListB = new HashSet<String>();
		Set<String> ascendantListC = new HashSet<String>();
		
		Utils.getAllAscendant(SourceName, ascendantListA, graph);
		ascendantListA.add(SourceName);
		
		for (String ascendant : ascendantListA) {
			
			PReviewDecider deciderO = new PReviewDecider(createCopy(), prohibitions);
			Map<String, Set<String>> CapabilityList = deciderO.getCapabilityList(ascendant, null);

			Graph mutant = createCopy();
			mutant.dissociate(SourceName, oldTargetName);
			mutant.associate(SourceName, newTargetName, accessRights);
//			try {
//				mutant.associate(newSourceNode, targetNode, accessRights);
//			} catch(IllegalArgumentException e) {
//				return null;
//			}
			
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
		
		
		Utils.getAllAscendant(oldTargetName, ascendantListB, graph);
		Utils.getAllAscendant(newTargetName, ascendantListC, graph);
		attributeToCheckList.addAll(ascendantListB);
		attributeToCheckList.add(oldTargetName);
		attributeToCheckList.addAll(ascendantListC);
		attributeToCheckList.add(newTargetName);
		for (String ascendant : attributeToCheckList) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);
			
			mutant.dissociate(SourceName, oldTargetName);
			mutant.associate(SourceName, newTargetName, accessRights);
			
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
