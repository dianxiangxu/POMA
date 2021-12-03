package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IteratorUtils;

import CaseStudies.gpms.customFunctions.AddPropertiesToNodeExecutor;
import CaseStudies.gpms.customFunctions.AllChildrenHavePropertiesExecutor;
import CaseStudies.gpms.customFunctions.CoPIToAddExecutor;
import CaseStudies.gpms.customFunctions.CoPIToDeleteExecutor;
import CaseStudies.gpms.customFunctions.CompareNodeNamesExecutor;
import CaseStudies.gpms.customFunctions.ConcatExecutor;
import CaseStudies.gpms.customFunctions.CreateNodeExecutor1;
import CaseStudies.gpms.customFunctions.DeleteNodeExecutor;
import CaseStudies.gpms.customFunctions.GetAncestorInPCExecutor;
import CaseStudies.gpms.customFunctions.GetAncestorsInPCExecutor;
import CaseStudies.gpms.customFunctions.GetChildExecutor;
import CaseStudies.gpms.customFunctions.GetChildInPCExecutor;
import CaseStudies.gpms.customFunctions.GetChildrenUsersInPolicyClassExecutor;
import CaseStudies.gpms.customFunctions.IRBApprovalRequired;
import CaseStudies.gpms.customFunctions.IsNodeInListExecutor;
import CaseStudies.gpms.customFunctions.RemovePropertyFromChildrenExecutor;
import CaseStudies.gpms.customFunctions.SPToAddExecutor;
import CaseStudies.gpms.customFunctions.SPToDeleteExecutor;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.Decider;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class ROB extends MutantTester {
	int i, j;
	static Graph g;
	public ROB(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		i = 0;
		j = 0;
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			
			Obligation obligation = Utils.createObligationCopy();
			Obligation mutant = Utils.createObligationCopy();
			mutant = removeOneRule(mutant, ruleLabel);
			Utils.setObligationMutant(mutant);
			
			try {
				AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, ruleLabel);
				if (q == null) {
					//ROB has no chance to generate equivalent mutants if the obligation is reachable and response pattern does make changes
					if (Utils.obligationIsReachable(mutant, ruleLabel) && Utils.privilegeIsChanged(mutant, ruleLabel)) {
						//FIXME: need more parsing work on subject, operation set, and target
						q = new AccessRequest(rule.getEventPattern().getSubject().toString(), rule.getEventPattern().getOperations().toString(), rule.getEventPattern().getTarget().toString());
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
					} else {
						System.out.println("Mutant equivalent!(ROB:" + i + ") Remove One Obligation:" + rule.getLabel());
						j++;
					}
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(ROB:" + i + ") Remove One Obligation:" + rule.getLabel());
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
			} catch (PMException e) {
				System.out.println("Error in triggering obligation");
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
//		System.out.println(i);
//		System.out.println(j);
	}
	
	private Obligation removeOneRule(Obligation obligation, String ruleLabel) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel))
				continue;
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
}
