package POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class ROA_Solver extends MutantTester {
	int i;
	static Graph g;
	public ROA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		List<String> constraintR = new ArrayList<String>();
		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String constraints;
		i = 0;
		int index;
		List<Action> newActions = new ArrayList<>();
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			EventPattern event = rule.getEventPattern();
			//FIXME: need implementation
			//RNP: need a parser function translating event pattern to reachability constraint
			//this function may return multiple strings and each string consisting an access event to trigger obligation
			constraintR = Utils.getReachabilityConstraint(event);
			if (constraintR == null)
				continue;
			
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToDelete : actions) {
				index++;
				
				//Do obligation mutation
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();//delete one action from current action list
				newActions.clear();
				for (Action action : actions) {
					newActions.add(action);
				}
				newActions.remove(actionToDelete); 
				mutant = updateActions(mutant, ruleLabel, newActions);
				Utils.setObligationMutant(mutant);
				
				//FIXME: need implementation 
				//constraintN = (condition is false) or (negated condition is true)
				//parse function getNecessityConstraint returns a list of strings and
				//each string consisting constraint which makes condition satisfied/unsatisfied by true/false
				if (actionToDelete.getCondition() != null) {
					constraintN.addAll(Utils.getNecessityConstraint(actionToDelete.getCondition().getCondition(), false));
				} 
				if (actionToDelete.getNegatedCondition() != null){
					constraintN.addAll(Utils.getNecessityConstraint(actionToDelete.getNegatedCondition().getCondition(), true));
				}
//				if (constraintN == null)
//					continue;

				//FIXME: need implementation 
				//constraintP: newAction should change permit or deny
				constraintP = Utils.getPropagationConstraint(actionToDelete);
				if (constraintP == null)
					continue;
				
				boolean killed = false;
				for (String r : constraintR) {
//					for (String n : constraintN) {
						for (String p : constraintP) {
							//handle analysing by smt solver
//							constraints = r + n + p;
							constraints = r + " AND " + p;
							System.out.println(i + ":" + constraints);
							
							//sendToSolver() returns a sequence of access requests, qList,where qList makes constraints satisfied
							//test suites TS = qList + qEventPattern + qAction(assert request)
							//TS shows different results between initial configuration and mutant
							List<AccessRequest> eventList = Utils.sendToSolver(graph, Utils.createProhibitionsCopy(), Utils.createObligationCopy(), constraints);
							if (eventList == null) {
								//fail to find event list by solver
								//try next constraints for the same mutant
								continue;
							}
							
							try {
								//verifyEventList() return true if eventList can kill mutant
								boolean q = Utils.verifyEventList(obligation, mutant, eventList);
								if (q == false) {
									//try next constraints for the same mutant
									continue;
								} else {
									//FIXME: should save eventList+assert request into test suite
//									Utils.addToARList(q);
									setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
									System.out.println("Mutant Killed!");
//									System.out.println("Mutant not equivalent!(ROA:" + i + ") Remove One Condition:" + rule.getLabel());
									killed = true;
									break;
								}
							} catch (PMException e) {
								System.out.println("Error in triggering obligation");
								setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
							}
						}
//						if (killed)
//							break;
//					}
					if (killed)
						break;	
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);	
			}
		}
	}
	
	private Obligation updateActions(Obligation obligation, String ruleLabel, List<Action> newActions) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				ResponsePattern responsePattern = newRule.getResponsePattern();
				responsePattern.setActions(newActions);
				newRule.setResponsePattern(responsePattern);
			}
			newRules.add(newRule);
		}
		obligation.setRules(newRules);
		return obligation;
	}
}
