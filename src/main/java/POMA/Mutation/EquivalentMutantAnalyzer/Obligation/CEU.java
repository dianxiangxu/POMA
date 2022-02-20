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

public class CEU extends MutantTester {
	int i;
	static Graph g;
	public CEU(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		AccessRequest ar = new AccessRequest(null, null, null);
		i = 0;
		
		List<Node> Us = Utils.getUsInGraph(graph);
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			EventPattern eventPattern = rule.getEventPattern();
			Subject subject = eventPattern.getSubject();
			for (int i = 0; i < Us.size(); i++) {
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();
				String user = subject.getUser();
				
				if (user == null) {
					List<String> anyUser = subject.getAnyUser();
					if (anyUser == null || anyUser.isEmpty()) {
						EvrProcess process = subject.getProcess();
						if (process == null || process.getValue().isEmpty()) {
							//have to do some default mutation if all null
//							changeToUser = getRandomUserName();
							changeToUser = Utils.getUserName(i, Us);
							mutant = changeEventUser(mutant, ruleLabel, changeToUser);
							
							String newName;
							if (i == 0)
								newName = Utils.getUserName(1, Us);
							else 
								newName = Utils.getUserName(0, Us);
							ar = new AccessRequest(newName, null, null);
//							continue;
						} else {
							//if user/anyuser/process are all null
							String oldProcess = process.getValue();
							changeToUser = Utils.getUserName(i, Us);
							if (oldProcess.equals(changeToUser))
								System.out.println("CEU: need another changeToUser.(This introduces an equivalent mutant)" );
							ar = new AccessRequest(changeToUser, null, null);
							mutant = changeEventProcess(mutant, ruleLabel, changeToUser);
//							continue;
						}
					} else {
//						System.out.println("AnyUser" + anyUser);
						anyUser.clear();
						
//						changeToUser = getRandomUserName();
						changeToUser = Utils.getUserName(i, Us);
						ar = new AccessRequest(changeToUser, null, null);
						anyUser.add(changeToUser);
						mutant = changeEventAnyUser(mutant, ruleLabel, anyUser);
					}
				} else {
//					System.out.println("UserToBeChanged:" + user);
//					changeToUser = getRandomUserName();
					changeToUser = Utils.getUserName(i, Us);
					ar = new AccessRequest(changeToUser, null, null);
					mutant = changeEventUser(mutant, ruleLabel, changeToUser);
				}
				Utils.setObligationMutant(mutant);

				try {
					
					AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, ar, rule.getLabel());
					if (q == null) {
						System.out.println("Mutant equivalent!(CEU:" + i + ") Change Event User:" + rule.getLabel());
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(CEU:" + i + ") Change Event User:" + rule.getLabel());
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					} 
				} catch (PMException e) {
					System.out.println("Error in triggering obligation");
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
			}
			
		}
	}
	
	private Obligation changeEventUser(Obligation obligation, String ruleLabel, String changeToUserName) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject(changeToUserName);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(changeToUserName);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	
	private Obligation changeEventAnyUser(Obligation obligation, String ruleLabel, List<String> newAnyUser) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject(newAnyUser);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(newAnyUser);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	private Obligation changeEventProcess(Obligation obligation, String ruleLabel, String newProcess) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change process to newProcess
				EvrProcess process = new EvrProcess(newProcess);
				Subject subject = new Subject(process);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(newAnyUser);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
}
