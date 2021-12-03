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
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class REU extends MutantTester {
	int i;
	static Graph g;
	public REU(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		AccessRequest ar = new AccessRequest(null, null, null);
		i = 0;
		
		List<String> Us = Utils.getAllUserNames();
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			EventPattern eventPattern = rule.getEventPattern();
			Subject subject = eventPattern.getSubject();
			
			Obligation obligation = Utils.createObligationCopy();
			Obligation mutant = Utils.createObligationCopy();
			String user = subject.getUser();
			if (user == null) {
				List<String> anyUser = subject.getAnyUser();
				if (anyUser == null || anyUser.isEmpty()) {
					EvrProcess process = subject.getProcess();
					if (process == null) {
//						//have to do some default mutation if all all null
//						mutant = clearEventUser(mutant, ruleLabel);
						continue;
					} else {
						//not sure how to change process yet
//						mutant = changeEventProcess(mutant, ruleLabel, changeToUser);
					}
				} else {
					//find a user which could not trigger obligation in initial policy
					List<String> tmpList = new ArrayList();
					tmpList.addAll(Us);
					tmpList.removeAll(anyUser);
					if (!tmpList.isEmpty())
						ar = new AccessRequest(tmpList.get(0), null, null);
					System.out.println("AnyUserToBeCleared" + anyUser);
					mutant = removeEventAnyUser(mutant, ruleLabel);
				}
			} else {
				//find a user which could not trigger obligation in initial policy
				List<String> tmpList = new ArrayList();
				tmpList.addAll(Us);
				tmpList.remove(user);
				if (!tmpList.isEmpty())
					ar = new AccessRequest(user, null, null);
				System.out.println("UserToBeCleared:" + user);
				mutant = clearEventUser(mutant, ruleLabel);
			}
			Utils.setObligationMutant(mutant);
			
			try {
				AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, ar, rule.getLabel());
				if (q == null) {
					System.out.println("Mutant equivalent!(REU:" + i + ") Remove Event User:" + rule.getLabel());
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
					System.out.println("Mutant not equivalent!(REU:" + i + ") Remove Event User:" + rule.getLabel());
					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
			} catch (PMException e) {
				System.out.println("Error in triggering obligation");
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);
		}
	}
	
	private Obligation clearEventUser(Obligation obligation, String ruleLabel) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject("");
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(null);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	private Obligation removeEventAnyUser(Obligation obligation, String ruleLabel) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				List<String> anyUser = eventPattern.getSubject().getAnyUser();
				anyUser.clear();
				//change anyUser to empty array
				Subject subject = new Subject(anyUser);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(anyUser);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}

	public String getRandomUserName(List<String> Us) {
		//0 can be replaced by a random number from 0 to length(Us)
		String userName = Us.get(0);
		System.out.println(userName);
		return userName;
	}
}
