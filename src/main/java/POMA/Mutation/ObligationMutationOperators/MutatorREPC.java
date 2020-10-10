package POMA.Mutation.ObligationMutationOperators;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.TestSuitGeneration.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;

public class MutatorREPC extends MutantTester2 {
//	String testMethod = "P";

	public MutatorREPC(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, obligation);
	}

	public void init() throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsREU.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);

		
		performMutation(testMethod, testSuitePath);
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(String testMethod, String testSuitePath) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		File testSuite = new File(testSuitePath);
		Graph graph = createCopy();
		Obligation obligation = createObligationCopy();
		String ruleLabel;
		Obligation mutant;
		
		getAllUserNames();
		
		List<Rule> rules = obligation.getRules();
		for (Rule rule : rules) {
			ruleLabel = rule.getLabel();
			EventPattern eventPattern = rule.getEventPattern();
			Subject subject = eventPattern.getSubject();
			
			mutant = createObligationCopy();
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
						//continue if user/anyuser/process are all null
						continue;
					}
				} else {
					System.out.println("AnyUser" + anyUser);
					mutant = removeEventAnyUser(mutant, ruleLabel);
				}
			} else {
				System.out.println("User" + user);
				mutant = clearEventUser(mutant, ruleLabel);
			}
			setObligationMutant(mutant);

//			//invoke junit to kill obligation_mutant
			testMutant(graph, obligation, testSuite, testMethod, getNumberOfMutants(), "REU");
			setNumberOfMutants(getNumberOfMutants() + 1);
			
		}
//		System.out.println("Total number of mutant is " + getNumberOfMutants());
	}

	private Obligation clearEventUser(Obligation obligation, String ruleLabel) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = Subject.class.getConstructor(String.class).newInstance(null);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	private Obligation removeEventAnyUser(Obligation obligation, String ruleLabel) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
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
				Subject subject = Subject.class.getConstructor(String.class).newInstance(anyUser);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	private Obligation removeEventProcess(Obligation obligation, String ruleLabel, Process process) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//to be implemented
		return obligation;
	}
}
