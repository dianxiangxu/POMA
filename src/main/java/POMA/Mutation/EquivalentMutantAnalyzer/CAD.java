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

public class CAD extends MutantTester {
	int i;
	static Graph g;
	public CAD(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "CAD";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCAD.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		for (Node nodeA : UAsOAs) {
			
			for (String nodeBName : graph.getParents(nodeA.getName())) {
				Node nodeB = graph.getNode(nodeBName);
				if (nodeB.getType().toString().equals("PC")) {
//					System.out.println("a is "+ua.toString()+"| b is "+ub.toString());
					continue;
				}
				for (Node nodeC : UAsOAs) {
					if (nodeC.getType() != nodeA.getType()) {
						continue;
					}
					if (nodeA.toString().equals(nodeC.toString()) || nodeB.toString().equals(nodeC.toString())) {
						continue;
					}
					if (Utils.isContained(nodeC, nodeA, graph)) {
						//this will incur a cycle
						continue;
					}
					if (graph.isAssigned(nodeA.getName(), nodeC.getName())) {
						// assignment <a,c> already exists
						continue;
					}

					AccessRequest q = decideEquivalentMutantForCAD(nodeA, nodeB, nodeC);
					if (q == null) {
						System.out.println("Mutant equivalent!(CAD:" + i + ") Change assignment (" + nodeA.getName() + "," + nodeB.getName() + ") to (" + nodeA.getName() + "," + nodeC.getName() + ")");
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(CAD:" + i + ") Change assignment (" + nodeA.getName() + "," + nodeB.getName() + ") to (" + nodeA.getName() + "," + nodeC.getName() + ")");
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);
				}
			}
		}
	}
	
	public AccessRequest decideEquivalentMutantForCAD (Node nodeA, Node nodeB, Node nodeC) throws PMException {
		Set<String> ascendantList = new HashSet<String>();
		
		if (nodeA.getType().toString().equals("UA")) {
			Utils.getAllAscendant(nodeA.getName(), ascendantList, graph);
			
			ascendantList.add(nodeA.getName());
			
			for (String ascendant : ascendantList) {
				
				PReviewDecider deciderO = new PReviewDecider(createCopy(), prohibitions);
				Map<String, Set<String>> CapabilityList = deciderO.getCapabilityList(ascendant, null);
				
				Graph mutant = createCopy();
				mutant.deassign(nodeA.getName(), nodeB.getName());
				mutant.assign(nodeA.getName(), nodeC.getName());
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
			return null;
		}
		
		//if type of nodeA is UA, it is possible that nodeA serves as target attribute in an association
		Utils.getAllAscendant(nodeA.getName(), ascendantList, graph);
		
		ascendantList.add(nodeA.getName());
		
		for (String ascendant : ascendantList) {
			Graph mutant = createCopy();
			PReviewDecider decider = new PReviewDecider(mutant, prohibitions);
			Map<String, Set<String>> ACL = decider.generateACL(ascendant, null);

			mutant.deassign(nodeA.getName(), nodeB.getName());
			mutant.assign(nodeA.getName(), nodeC.getName());
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
