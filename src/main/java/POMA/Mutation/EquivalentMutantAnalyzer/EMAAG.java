package POMA.Mutation.EquivalentMutantAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

//add assignment
public class EMAAG<OperationSet> extends MutantTester {
	int i;
	static Graph g;
	public EMAAG(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "EMAAG";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsEMAAG.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		Map<String, Map> mapSA = new HashMap<>(); //store association relations based on source attribute
		Map<String, AssociationList> mapTA = new HashMap<>(); //store association relations based on target attribute
		
		
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
				if (Utils.isContained(nodeB, nodeA)) {
					//this will incur a cycle
					continue;
				}
				if (Utils.isContained(nodeA, nodeB)) {
					//this does not make sense
					continue;
				}
				if (graph.isAssigned(nodeA.getName(), nodeB.getName())) {
					// assignment <a,b> already exists
					continue;
				}
				
				//From here, adding assignment (A,B) is legal
				g = createCopy();
				AccessRequest q = Utils.decideEquivalentMutantForAddingAssignment(nodeA, nodeB, g, this.prohibitions, mapSA, mapTA);
				if (q == null) {
					System.out.println("Mutant equivalent!(AAG:" + i + ") Add assignment from " + nodeA.getName() + " to " + nodeB.getName());
				} else {
//					System.out.println("Mutant not equivalent!(AAG)Add assignment from " + nodeA.getName() + " to " + nodeB.getName());
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
			}
		}
	}
}
