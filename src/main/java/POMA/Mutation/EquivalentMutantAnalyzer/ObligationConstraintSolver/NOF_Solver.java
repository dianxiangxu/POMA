package POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class NOF_Solver extends MutantTester {
	static Graph g;
	public NOF_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, RConstraint, PConstraint;
		String changeType;
		int i = 0;//index of mutant
		List<Action> newActions = new ArrayList<>();
		int index = 0;
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			EventPattern eventPattern = rule.getEventPattern();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			index = 0;

			//this mutant is included in ROB
			if (actions.size() <= 1)
				continue;
			
			for (Action actionToDelete : actions) {
				if (actionToDelete.getCondition() == null && actionToDelete.getNegatedCondition() == null) {
					//the action does not have conditon, just skip to next action
					index++;
					continue;
				}
				if (actionToDelete.getCondition() != null) {
					for (Function factor : actionToDelete.getCondition().getCondition()) {
						newActions.clear();
						Obligation mutant = Utils.createObligationCopy();
						Condition ListF = actionToDelete.getCondition();
						NegatedCondition NListF = actionToDelete.getNegatedCondition();
						List<Function> tmp = new ArrayList<Function>();
						
						tmp.addAll(actionToDelete.getCondition().getCondition());
						tmp.remove(factor);
						actionToDelete.getCondition().setCondition(tmp);
						
						tmp.clear();
						if (NListF != null) {
							tmp.addAll(actionToDelete.getNegatedCondition().getCondition());
							tmp.add(factor);
							actionToDelete.getNegatedCondition().setCondition(tmp);
						} else {
							tmp.add(factor);
							NegatedCondition c = new NegatedCondition();
							c.setCondition(tmp);
							actionToDelete.setNegatedCondition(c);
						}
			
						newActions.addAll(actions);
						
						mutant = updateActions(mutant, ruleLabel, newActions);
						Utils.setObligationMutant(mutant);
						
						Obligation obM = Utils.createObligationWithCondtionCopy();
						preConstraint = "";
						postConstraint = "";
						Boolean res = Utils.killMutantT (mutant, ruleLabel, preConstraint, postConstraint, obM);
						if (res) {
							System.out.println("Mutant killed!");
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
						} else
							System.out.println("Mutant not killed!");
						setNumberOfMutants(getNumberOfMutants() + 1);
						
						//revert condition
						actionToDelete.setCondition(ListF);
						actionToDelete.setNegatedCondition(NListF);
					}
				}
				
				if (actionToDelete.getNegatedCondition() != null) {
					for (Function factor : actionToDelete.getNegatedCondition().getCondition()) {
						newActions.clear();
						Obligation mutant = Utils.createObligationCopy();
						Condition ListF = actionToDelete.getCondition();
						NegatedCondition NListF = actionToDelete.getNegatedCondition();
						List<Function> tmp = new ArrayList<Function>();
						
						if (ListF != null) {
							tmp.addAll(actionToDelete.getCondition().getCondition());
							tmp.add(factor);
							actionToDelete.getCondition().setCondition(tmp);
						} else {
							tmp.add(factor);
							Condition c = new Condition();
							c.setCondition(tmp);
							actionToDelete.setCondition(c);
						}
						
						
						tmp.clear();
						tmp.addAll(actionToDelete.getNegatedCondition().getCondition());
						tmp.remove(factor);
						actionToDelete.getNegatedCondition().setCondition(tmp);
						
						newActions.addAll(actions);
						
						mutant = updateActions(mutant, ruleLabel, newActions);
						Utils.setObligationMutant(mutant);
						
						Obligation obM = Utils.createObligationWithCondtionCopy();
						preConstraint = "";
						postConstraint = "";
						Boolean res = Utils.killMutantT (mutant, ruleLabel, preConstraint, postConstraint, obM);
						if (res) {
							System.out.println("Mutant killed!");
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
						} else
							System.out.println("Mutant not killed!");
						setNumberOfMutants(getNumberOfMutants() + 1);
						
						//revert condition
						actionToDelete.setCondition(ListF);
						actionToDelete.setNegatedCondition(NListF);
					}
				}
				
				i++;
				index++;
				
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
