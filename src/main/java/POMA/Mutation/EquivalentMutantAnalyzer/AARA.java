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

public class AARA extends MutantTester {
	int i;
	static Graph g;
	public AARA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AARA";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAARA.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		OperationSet allAccessRightSet = Utils.getAllAccessRights();
		for (Node sourceNode : UAs) {
			if (graph.getSourceAssociations(sourceNode.getName()) == null)
				continue;
			Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				
				for (String accessRight : allAccessRightSet) {
					if (accessRights.contains(accessRight)) {
						continue;
					}
					AccessRequest q = decideEquivalentMutantForAARA(sourceNode.getName(), targetNode, accessRights, accessRight);
					if (q == null) {
						System.out.println("Mutant equivalent!(AARA:" + i + ") Add Access Right:" + accessRight + " to Association (" + sourceNode.getName() + "," + accessRights.toString() + "," + targetNode + ")");
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(AARA:" + i + ") Add Access Right:" + accessRight + " to Association (" + sourceNode.getName() + "," + accessRights.toString() + "," + targetNode + ")");
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
	}
	
	public AccessRequest decideEquivalentMutantForAARA (String SourceName, String targetName, OperationSet accessRights, String accessRight) throws PMException, IOException {
//		System.out.printf(oldSourceNode + "|" + newSourceNode + "|" + targetNode + "\n");
		Set<String> ascendantListA = new HashSet<String>();
		Set<String> ascendantListB = new HashSet<String>();
		
		Utils.getAllAscendant(SourceName, ascendantListA, graph);
		ascendantListA.add(SourceName);
		
		for (String ascendant : ascendantListA) {
			
			PReviewDecider deciderO = new PReviewDecider(createCopy(), prohibitions);
			Map<String, Set<String>> CapabilityList = deciderO.getCapabilityList(ascendant, null);

			Graph mutant = createCopy();
			addAccessRightToAssociate(mutant, SourceName, targetName, accessRights, accessRight);
			
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
		
		
		Utils.getAllAscendant(targetName, ascendantListB, graph);
		ascendantListB.add(targetName);
		for (String ascendant : ascendantListB) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);
			
			addAccessRightToAssociate(mutant, SourceName, targetName, accessRights, accessRight);
			
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
	
	private Graph addAccessRightToAssociate(Graph mutant, String SourceName, String targetName,
			OperationSet accessRights, String accessRight) throws PMException, IOException {
		OperationSet tmpAccessRights = new OperationSet();
		tmpAccessRights.addAll(accessRights);
		tmpAccessRights.add(accessRight);
		mutant.dissociate(SourceName, targetName);
		mutant.associate(SourceName, targetName, tmpAccessRights);
		return mutant;
	}
}
