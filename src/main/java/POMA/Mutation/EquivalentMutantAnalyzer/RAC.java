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

public class RAC extends MutantTester {
	int i;
	static Graph g;
	public RAC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "RAC";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsRAC.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		for (Node sourceNode : UAs) {
			if (graph.getSourceAssociations(sourceNode.getName()) == null)
				continue;
			Map<String, OperationSet> sources = graph.getSourceAssociations(sourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			
			for (String targetNode : targetNodes) {
				AccessRequest q = decideEquivalentMutantForRAC(sourceNode.getName(), targetNode);
				if (q == null) {
					System.out.println("Mutant equivalent!(RAC:" + i + ") Remove AssoCiation:(" + sourceNode.getName() + "," + targetNode + ")");
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(RAC:" + i + ") Remove AssoCiation:(" + sourceNode.getName() + "," + targetNode + ")");
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
			}
		}
		
//		System.out.print(i); //i = 8
	}
	
	public AccessRequest decideEquivalentMutantForRAC (String sourceName, String targetName) throws PMException, IOException {
		Set<String> ascendantListA = new HashSet<String>();
		Set<String> ascendantListB = new HashSet<String>();
		
		Utils.getAllAscendant(sourceName, ascendantListA, graph);
		ascendantListA.add(sourceName);
		
		for (String ascendant : ascendantListA) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> CapabilityList = decider.getCapabilityList(ascendant, null);

			mutant.dissociate(sourceName, targetName);
			
			decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> CapabilityListMutant = decider.getCapabilityList(ascendant, null);
			
			AccessRequest q = CapabilityList.size() >= CapabilityListMutant.size() ? 
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
			
			mutant.dissociate(sourceName, targetName);
			
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
