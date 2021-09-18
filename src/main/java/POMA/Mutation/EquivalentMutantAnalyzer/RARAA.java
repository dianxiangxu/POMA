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

public class RARAA extends MutantTester {
	int i;
	static Graph g;
	public RARAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "RARAA";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsRARAA.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		OperationSet allAccessRightSet = Utils.getAllAccessRights();
		for (String deleteAR : allAccessRightSet) {
			AccessRequest q = decideEquivalentMutantForRARAA(deleteAR);
			if (q == null) {
				System.out.println("Mutant equivalent!(RARAA:" + i + ") Remove Access Right" + deleteAR+ " from All AssoCiation");
			} else {
				Utils.addToARList(q);
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//				System.out.println("Mutant not equivalent!(RARAA:" + i + ") Remove Access Right" + deleteAR+ " from All AssoCiation");
//				System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
//		System.out.print(i); //i = 11
	}
	
	public AccessRequest decideEquivalentMutantForRARAA (String deleteAR) throws PMException, IOException {
		Set<String> attributeToCheckList = new HashSet<String>();
		Set<String> attributeToCheckListA = new HashSet<String>();
		Set<String> attributeToCheckListTarget = new HashSet<String>();
		Set<String> attributeToCheckListB = new HashSet<String>();
		
		//get attribute to check list
		for (Node sourceNode : UAs) {
			if (graph.getSourceAssociations(sourceNode.getName()) == null)
				continue;
			Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
			
				if (!accessRights.contains(deleteAR))
					continue;
				
				Utils.getAllAscendant(sourceNode.getName(), attributeToCheckListA, graph);
				attributeToCheckList.addAll(attributeToCheckListA);
				attributeToCheckList.add(sourceNode.getName());
				
				Utils.getAllAscendant(targetNode, attributeToCheckListB, graph);
				attributeToCheckListTarget.addAll(attributeToCheckListB);
				attributeToCheckListTarget.add(targetNode);
			}
		}
		
		//for each attribute as source attribute in association, check Privilege set before and after mutation
		for (String ascendant : attributeToCheckList) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> CapabilityList = decider.getCapabilityList(ascendant, null);

			//do mutation
			for (Node sourceNode : UAs) {
				if (graph.getSourceAssociations(sourceNode.getName()) == null)
					continue;
				Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
				List<String> targetNodes = new ArrayList<String>(sources.keySet());
				for (String targetNode : targetNodes) {
					Set<String> operateSet = sources.get(targetNode);
					OperationSet accessRights = new OperationSet(operateSet);
				
					if (!accessRights.contains(deleteAR))
						continue;
					
					OperationSet tmpAccessRights = new OperationSet();
					tmpAccessRights.addAll(accessRights);
					tmpAccessRights.remove(deleteAR);
					mutant.dissociate(sourceNode.getName(), targetNode);
					mutant.associate(sourceNode.getName(), targetNode, tmpAccessRights);
				}
			}
			
			decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> CapabilityListMutant = decider.getCapabilityList(ascendant, null);
			
			AccessRequest q = CapabilityList.size() >= CapabilityListMutant.size() ? 
				Utils.compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
				Utils.compareTwoLists(CapabilityListMutant, CapabilityList, "UA");

			if (q != null)
				return new AccessRequest(ascendant, q.getAR(), q.getTA());
		}
		
		//for each attribute as target attribute in association, check Privilege set before and after mutation
		for (String ascendant : attributeToCheckListTarget) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);

			//do mutation
			for (Node sourceNode : UAs) {
				if (graph.getSourceAssociations(sourceNode.getName()) == null)
					continue;
				Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
				List<String> targetNodes = new ArrayList<String>(sources.keySet());
				for (String targetNode : targetNodes) {
					Set<String> operateSet = sources.get(targetNode);
					OperationSet accessRights = new OperationSet(operateSet);
				
					if (!accessRights.contains(deleteAR))
						continue;
					
					OperationSet tmpAccessRights = new OperationSet();
					tmpAccessRights.addAll(accessRights);
					tmpAccessRights.remove(deleteAR);
					mutant.dissociate(sourceNode.getName(), targetNode);
					mutant.associate(sourceNode.getName(), targetNode, tmpAccessRights);
				}
			}
			
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
	
	private Graph removeAccessRightFromAssociate(Graph mutant, String SourceName, String targetName, OperationSet accessRights, String accessRight) throws PMException, IOException {
		OperationSet tmpAccessRights = new OperationSet();
		tmpAccessRights.addAll(accessRights);
		tmpAccessRights.remove(accessRight);
		mutant.dissociate(SourceName, targetName);
		mutant.associate(SourceName, targetName, tmpAccessRights);
		return mutant;
	}
}
