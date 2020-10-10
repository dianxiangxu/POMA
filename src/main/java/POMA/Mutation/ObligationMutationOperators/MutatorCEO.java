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
import POMA.Exceptions.NoTypeProvidedException;
import POMA.TestSuitGeneration.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.PolicyClass;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;

//change event operation
public class MutatorCEO extends MutantTester2 {
//	String testMethod = "P";

	public MutatorCEO(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, obligation);
	}

	public void init() throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResultsCEU.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);

		
		performMutation(testMethod, testSuitePath);
		saveCSV(data, new File(testResults), testMethod);
	}

	private void performMutation(String testMethod, String testSuitePath) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, NoTypeProvidedException {
		File testSuite = new File(testSuitePath);
		Graph graph = createCopy();
		Obligation obligation = createObligationCopy();
		String ruleLabel;
		Obligation mutant;
		double before, after;
		
		getAllOperationsInObligation();	
		List<Rule> rules = obligation.getRules();
		
		for (Rule rule : rules) {
			ruleLabel = rule.getLabel();
			EventPattern eventPattern = rule.getEventPattern();
			List<String> operations = eventPattern.getOperations();
			
			for (String operation : operations) {
				for (String changeToOperation : OPs) {
					mutant = createObligationCopy();
			
					//operation as input, to avoid change to same operation
					List<String> changeToOperationSet = getChangeToOpSet(operations, operation, changeToOperation);
					//no difference from mutant
					if (changeToOperationSet == null)
						continue;
					System.out.println("change operation:" + operations.toString() + " to " + changeToOperationSet);
					mutant = updateOperationSet(mutant, ruleLabel, changeToOperationSet);
				
					setObligationMutant(mutant);

					before = getNumberOfKilledMutants();
					//invoke junit to kill obligation_mutant
					testMutant(graph, obligation, testSuite, testMethod, getNumberOfMutants(), "CEU");
					after = getNumberOfKilledMutants();
					if (before == after) {
						//unkilled mutant caught
						System.out.println("Unkilled mutant (AEO) " + ruleLabel + "|" 
								+ operations.toString() + "|" + changeToOperationSet.toString());
					}
					
					setNumberOfMutants(getNumberOfMutants() + 1);
					
				}
			}
			
		}
//		System.out.println("Total number of mutant is " + getNumberOfMutants());
	}
	
	//change operationset (in label: ruleLabel) to changeToOperationSet
	private Obligation updateOperationSet(Obligation obligation, String ruleLabel, List<String> changeToOperationSet) throws PMException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				
				eventPattern.setOperations(changeToOperationSet);				
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}

	public List<String> getChangeToOpSet(List<String> operations, String operation, String changeToOperation) {
		//test code
		if (changeToOperation.equals("delete-copi")) {
			return null;
		}
		if (changeToOperation.equals("add-copi")) {
			return null;
		}
		
		if (changeToOperation.equals(operation)) {
			return null;
		}
		if (operations.contains(changeToOperation)) {
			return null;
		}
		List<String> newOperations = new ArrayList<>();
		//changeToOperation != operation, and changeToOperation not in operations, but in OPs
		for (String newOp : operations) {
			if (newOp.equals(operation)) {
				newOperations.add(changeToOperation);
			} else {
				newOperations.add(newOp);
			}
		}
		return newOperations;
	}
}
