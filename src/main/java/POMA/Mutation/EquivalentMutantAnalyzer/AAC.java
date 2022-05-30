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

public class AAC extends MutantTester {
	int i;
	static Graph g;
	public AAC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "AAC";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsAAC.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 0;
		
		OperationSet allAccessRightSet = Utils.getAllAccessRights();
		for (Node sourceNode : UAs) {
			for (Node targetNode : UAsOAs) {
				if (targetNode.getName().equals(sourceNode.getName()))
					continue;
				
				if (graph.getSourceAssociations(sourceNode.getName()) == null)
					continue;
				
				Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
				List<String> targetNodes = new ArrayList<String>(sources.keySet());
				if (targetNodes.contains(targetNode.getName()))
					continue;
				
				for (String accessRight : allAccessRightSet) {
					OperationSet accessRights = new OperationSet();

					accessRights.add(accessRight);
					AccessRequest q = decideEquivalentMutantForAAC(sourceNode.getName(), targetNode.getName(), accessRights);
					if (q == null) {
						System.out.println("Mutant equivalent!(AAC:" + i + ") Add AssoCiation:(" + sourceNode.getName() + "," + accessRights + "," + targetNode.getName() + ")");
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(AAC:" + i + ") Add AssoCiation:(" + sourceNode.getName() + "," + accessRights + ","  + targetNode.getName() + ")");
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
//		System.out.print(i); //i = 112
	}
	
	public AccessRequest decideEquivalentMutantForAAC (String sourceName, String targetName, OperationSet accessRights) throws PMException, IOException {
		Set<String> ascendantListA = new HashSet<String>();
		Set<String> ascendantListB = new HashSet<String>();
		
		Utils.getAllAscendant(sourceName, ascendantListA, graph);
		ascendantListA.add(sourceName);
		
		for (String ascendant : ascendantListA) {
			
			PReviewDecider deciderO = new PReviewDecider(createCopy(), prohibitions);
			Map<String, Set<String>> CapabilityList = deciderO.getCapabilityList(ascendant, null);

			Graph mutant = createCopy();
			mutant.associate(sourceName, targetName, accessRights);
			
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
			
			mutant.associate(sourceName, targetName, accessRights);
			
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
