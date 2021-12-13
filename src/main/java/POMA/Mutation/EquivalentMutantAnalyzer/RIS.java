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

public class RIS extends MutantTester {
	int i;
	static Graph g;
	public RIS(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		List<Prohibition> prohibitionList = Utils.getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			String subject = p.getSubject();
			Set<String> ops = p.getOperations();
			
			AccessRequest q = decideEquivalentMutantForRIS(name, subject);
			if (q == null) {
				System.out.println("Mutant equivalent!(RIS:" + i + ") Reverse InterSection in " + name);
			} else {
				Utils.addToARList(q);
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//				System.out.println("Mutant not equivalent!(RIS:" + i + ") Reverse InterSection in " + name);
//				System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
//		System.out.print(i); //i = 6
	}
	
	public AccessRequest decideEquivalentMutantForRIS (String prohibitionName, String subject) throws PMException {
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
			prohibitionMutant.setIntersection(!prohibitionMutant.isIntersection());
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
