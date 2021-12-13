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

public class CSS extends MutantTester {
	int i;
	static Graph g;
	public CSS(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws PMException, IOException {
		this.mutationMethod = "CSS";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCSS.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 1;
		
		List<Prohibition> prohibitionList = Utils.getProhibitionList();
		for (Prohibition p : prohibitionList) {
			String name = p.getName();
			String oldSubject = p.getSubject();
			String newSubject = "a";
			
			AccessRequest q = decideEquivalentMutantForCSS(name, oldSubject, newSubject);
			if (q == null) {
				System.out.println("Mutant equivalent!(CSS:" + i + ") Change Subject Spec:" + oldSubject + " in " + name);
			} else {
				Utils.addToARList(q);
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//				System.out.println("Mutant not equivalent!(CSS:" + i + ") Change Subject Spec:" + oldSubject + " in " + name);
//				System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
		
//		System.out.print(i); //i = 6
	}
	
	public AccessRequest decideEquivalentMutantForCSS (String prohibitionName,String oldSubject,String newSubject) throws PMException {
//		System.out.printf(oldSourceNode + "|" + newSourceNode + "|" + targetNode + "\n");
		Set<String> attributeToCheckList = new HashSet<String>();
		Set<String> ascendantList = new HashSet<String>();
		
		Utils.getAllAscendant(oldSubject, ascendantList, graph);
		attributeToCheckList.addAll(ascendantList);
		attributeToCheckList.add(oldSubject);
		if (graph.exists(newSubject)) {
			Utils.getAllAscendant(newSubject, ascendantList, graph);
			attributeToCheckList.addAll(ascendantList);
			attributeToCheckList.add(newSubject);
		}
		
		for (String ascendant : attributeToCheckList) {
			Prohibitions mutant = Utils.createProhibitionsCopy();
			
			PReviewDecider decider = new PReviewDecider(graph, mutant);
			Map<String, Set<String>> CapabilityList = decider.getCapabilityList(ascendant, null);
			
			Prohibition prohibitionMutant = mutant.get(prohibitionName);
			//make changes to user_name/subject
			String subject = prohibitionMutant.getSubject();
			//change subject not working?
			prohibitionMutant.setSubject("a");
			
			//this restore prohibitons to initial state
			mutant.update(oldSubject, prohibitionMutant);
			
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
