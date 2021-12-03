package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.File;
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

public class ROF extends MutantTester {
	int i;
	static Graph g;
	public ROF(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		i = 0;
		int index;
		
		//negate condition before actions
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			if (isAbleToMutate(rule) == false)
				continue;
			Obligation obligation = Utils.createObligationCopy();
			Obligation mutant = Utils.createObligationCopy();
			List<Rule> newRules = mutant.getRules();
			for (Rule r : newRules) {
				if (r.getLabel().equals(ruleLabel)) {
					ResponsePattern responsePattern = r.getResponsePattern();
					Condition condition = responsePattern.getCondition();
					NegatedCondition negatedCondition = responsePattern.getNegatedCondition();
					List<Function> newConditions = new ArrayList<>();
					List<Function> newNegatedConditions = new ArrayList<>();
					List<Function> functions = new ArrayList<>();
				
					if (condition != null && condition.getCondition().size() >= 2) {
						int i = 0;
						functions = condition.getCondition();
						for (Function f : functions) {
							newConditions = addAllExcept(functions, f);
							condition.setCondition(newConditions);
							System.out.println("Running (ROF) " + ruleLabel + "|factorIndex:" + i);
							AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
							if (q == null) {
								System.out.println("Mutant equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
							} else {
								Utils.addToARList(q);
								setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//								System.out.println("Mutant not equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
//								System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
							}
							i++;
							setNumberOfMutants(getNumberOfMutants() + 1);	
						}
					}
				}
			}
			
			mutant = Utils.createObligationCopy();
			newRules = mutant.getRules();
			for (Rule r : newRules) {
				if (r.getLabel().equals(ruleLabel)) {
					ResponsePattern responsePattern = r.getResponsePattern();
					Condition condition = responsePattern.getCondition();
					NegatedCondition negatedCondition = responsePattern.getNegatedCondition();
					List<Function> newConditions = new ArrayList<>();
					List<Function> newNegatedConditions = new ArrayList<>();
					List<Function> functions = new ArrayList<>();
					if (negatedCondition != null && negatedCondition.getCondition().size() >= 2) {
						int i = 0;
						functions = negatedCondition.getCondition();
						for (Function f : functions) {
							newNegatedConditions = addAllExcept(functions, f);
							negatedCondition.setCondition(newNegatedConditions);
							System.out.println("Running (ROF negated) " + ruleLabel + "|factorIndex:" + i);
							AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
							if (q == null) {
								System.out.println("Mutant equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
							} else {
								Utils.addToARList(q);
								setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//								System.out.println("Mutant not equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
//								System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
							}
							i++;
							setNumberOfMutants(getNumberOfMutants() + 1);
						}
					}
				}
			}
		}
		
		//negate condition within actions
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			index = 0;
			for (Action action : actions) {
				if (isAbleToMutate(action) == false) {
					System.out.println("Not available for mutation.");
					index++;
					continue;
				}
				
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();
				List<Rule> newRules = mutant.getRules();
				//locate action and perform mutation
				for (Rule r : newRules) {
					if (r.getLabel().equals(ruleLabel)) {
						ResponsePattern newResponsePattern = r.getResponsePattern();
						List<Action> newActions = newResponsePattern.getActions();
						Action newAction = getAction(newActions, index);
						if (newAction == null) {
							System.out.println("this is a bug");
							break;
						}
								
						Condition condition = newAction.getCondition();
						NegatedCondition negatedCondition = newAction.getNegatedCondition();
						List<Function> newConditions = new ArrayList<>();
						List<Function> newNegatedConditions = new ArrayList<>();
						List<Function> functions = new ArrayList<>();
						
						if (condition != null && condition.getCondition().size() >= 2) {
							int i = 0;
							functions = condition.getCondition();
							for (Function f : functions) {
								newConditions = addAllExcept(functions, f);
								condition.setCondition(newConditions);
								System.out.println("Running (ROF) " + ruleLabel + "|actionIndex:" + index + "|factorIndex:" + i);
								AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
								if (q == null) {
									System.out.println("Mutant equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
								} else {
									Utils.addToARList(q);
									setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//									System.out.println("Mutant not equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
//									System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
								}
								i++;
								setNumberOfMutants(getNumberOfMutants() + 1);
							}
						}
					}
				}
				
				mutant = Utils.createObligationCopy();
				newRules = mutant.getRules();
				for (Rule r : newRules) {
					if (r.getLabel().equals(ruleLabel)) {
						ResponsePattern newResponsePattern = r.getResponsePattern();
						List<Action> newActions = newResponsePattern.getActions();
						Action newAction = getAction(newActions, index);
						if (newAction == null) {
							System.out.println("this is a bug");
							break;
						}
								
						Condition condition = newAction.getCondition();
						NegatedCondition negatedCondition = newAction.getNegatedCondition();
						List<Function> newConditions = new ArrayList<>();
						List<Function> newNegatedConditions = new ArrayList<>();
						List<Function> functions = new ArrayList<>();
						if (negatedCondition != null && negatedCondition.getCondition().size() >= 2) {
							int i = 0;
							functions = negatedCondition.getCondition();
							for (Function f : functions) {
								newNegatedConditions = addAllExcept(functions, f);
								negatedCondition.setCondition(newNegatedConditions);
								System.out.println("Running (ROF negated) " + ruleLabel + "|actionIndex:" + index + "|factorIndex:" + i);
								AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
								if (q == null) {
									System.out.println("Mutant equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
								} else {
									Utils.addToARList(q);
									setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//									System.out.println("Mutant not equivalent!(ROF:" + i + ") Remove One Condition:" + rule.getLabel());
//									System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
								}
								i++;
								setNumberOfMutants(getNumberOfMutants() + 1);
							}
						}
					}
				}
				index++;
			}
		}		
		
	}
	
	private boolean isAbleToMutate(Rule r) {
		ResponsePattern responsePattern = r.getResponsePattern();
		Condition condition = responsePattern.getCondition();
		NegatedCondition negatedCondition = responsePattern.getNegatedCondition();
	
		if (condition == null) {
			if (negatedCondition == null)
				return false;
			else {
				//only have negatedCondition
				if (negatedCondition.getCondition().size() == 1)
					return false;
			}
		} else if (negatedCondition == null) {
			//only have Condition
			if (condition.getCondition().size() == 1)
				return false;
		} else {
			//have both Conditon and negateCondition
			if (condition.getCondition().size() == 1)
				if (negatedCondition.getCondition().size() == 1)
					return false;
		}
		return true;
	}
	
	
	private boolean isAbleToMutate(Action action) {
		Condition condition = action.getCondition();
		NegatedCondition negatedCondition = action.getNegatedCondition();
		if (condition == null) {
			if (negatedCondition == null)
				return false;
			else {
				//only have negateCondition
				if (negatedCondition.getCondition().size() == 1)
					return false;
			}
		} else if (negatedCondition == null) {
			//only have Condition
			if (condition.getCondition().size() == 1)
				return false;
		} else {
			//have both Conditon and negateCondition
			if (condition.getCondition().size() == 1)
				if (negatedCondition.getCondition().size() == 1)
					return false;
		}
		return true;
	}
	
	public List<Function> addAllExcept (List<Function> functions, Function f) {
		List<Function> newFunctions = new ArrayList<>();
		newFunctions.addAll(functions);
		newFunctions.remove(f);
		return newFunctions;
	}
	public Action getAction (List<Action> actions, int index) {
		int i = 0;
		for (Action a : actions) {
			if (i == index)
				return a;
			i++;
		}
		return null;
	}
}
