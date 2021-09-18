package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.model.Condition;
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

public class NCD extends MutantTester {
	int i;
	static Graph g;
	public NCD(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		i = 0;
		Action newAction;
		List<Action> newActions = new ArrayList<>();
		int index;
		
		//negate condition before actions
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			Obligation obligation = Utils.createObligationCopy();
			Obligation mutant = Utils.createObligationCopy();
			mutant = negateCondition(mutant, ruleLabel);
			if (mutant == null)
				continue;
			Utils.setObligationMutant(mutant);
			
			AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
			if (q == null) {
				System.out.println("Mutant equivalent!(NCD:" + i + ") Remove One Condition:" + rule.getLabel());
			} else {
				Utils.addToARList(q);
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//				System.out.println("Mutant not equivalent!(NCD:" + i + ") Remove One Condition:" + rule.getLabel());
//				System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
			}
			i++;
			setNumberOfMutants(getNumberOfMutants() + 1);	
		}
		
		//negate condition within actions
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			index = 0;
			for (Action action : actions) {
				System.out.println("Running (NCD) " + ruleLabel + "|actionIndex:" + index);
				
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();
				newAction = negateDeepCondition(action);
				
				//no mutant generated due to no condition nor negatedcondition
				if (newAction == null) {
					index++;
					continue;
				}
				newActions.clear();
				for (Action actionT : actions) {
					newActions.add(actionT);
				}
				newActions.remove(action);
				newActions.add(newAction);
				mutant = updateActions(mutant, ruleLabel, newActions);
				Utils.setObligationMutant(mutant);
				AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
				if (q == null) {
					System.out.println("Mutant equivalent!(NCD:" + i + ") Remove One Condition:" + rule.getLabel());
				} else {
					Utils.addToARList(q);
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					System.out.println("Mutant not equivalent!(NCD:" + i + ") Remove One Condition:" + rule.getLabel());
//					System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
				}
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);	
				index++;
			}
		}		
	}
	
	private Obligation negateCondition(Obligation obligation, String ruleLabel) throws PMException, IOException {
		List<Rule> rules = obligation.getRules();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				ResponsePattern responsePattern = newRule.getResponsePattern();
				Condition condition = responsePattern.getCondition();
				NegatedCondition negatedCondition = responsePattern.getNegatedCondition();
				List<Function> newConditions = new ArrayList<>();
				List<Function> newNegatedConditions = new ArrayList<>();
				
				
				if (condition == null) {
					if (negatedCondition == null)
						return null;
					else {
						//only have negateCondition
						newConditions = negatedCondition.getCondition();
						Condition newCondition = new Condition();
						newCondition.setCondition(newConditions);
						
						responsePattern.setCondition(newCondition);
						responsePattern.setNegatedCondition(null);
					}
				} else if (negatedCondition == null) {
					//only have Condition
					newNegatedConditions = condition.getCondition();
					NegatedCondition newNegatedCondition = new NegatedCondition();
					newNegatedCondition.setCondition(newNegatedConditions);
					
					responsePattern.setCondition(null);
					responsePattern.setNegatedCondition(newNegatedCondition);
				} else {
					//have both Conditon and negateCondition
					newConditions = negatedCondition.getCondition();
					newNegatedConditions = condition.getCondition();
					
					condition.setCondition(newConditions);
					negatedCondition.setCondition(newNegatedConditions);
					responsePattern.setCondition(condition);
					responsePattern.setNegatedCondition(negatedCondition);
				}
				
				newRule.setResponsePattern(responsePattern);
			}
		}
		return obligation;
	}
	

	private Action negateDeepCondition(Action action){
//		Action newAction
		Condition condition = action.getCondition();
		NegatedCondition negatedCondition = action.getNegatedCondition();
		List<Function> newConditions = new ArrayList<>();
		List<Function> newNegatedConditions = new ArrayList<>();
		if (condition == null) {
			if (negatedCondition == null)
				return null;
			else {
				//only have negateCondition
				newConditions = negatedCondition.getCondition();
				Condition newCondition = new Condition();
				newCondition.setCondition(newConditions);
				
				action.setCondition(newCondition);
				action.setNegatedCondition(null);
			}
		} else if (negatedCondition == null) {
			//only have Condition
			newNegatedConditions = condition.getCondition();
			NegatedCondition newNegatedCondition = new NegatedCondition();
			newNegatedCondition.setCondition(newNegatedConditions);
			
			action.setCondition(null);
			action.setNegatedCondition(newNegatedCondition);
		} else {
			//have both Conditon and negateCondition
			newConditions = negatedCondition.getCondition();
			newNegatedConditions = condition.getCondition();
			
			condition.setCondition(newConditions);
			negatedCondition.setCondition(newNegatedConditions);
			action.setCondition(condition);
			action.setNegatedCondition(negatedCondition);
		}
		return action;
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
