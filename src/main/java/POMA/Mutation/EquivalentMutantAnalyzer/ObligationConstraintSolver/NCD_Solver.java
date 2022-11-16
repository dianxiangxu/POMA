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

public class NCD_Solver extends MutantTester {
	static Graph g;
	public NCD_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
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
				if (actionToDelete.getCondition() != null && actionToDelete.getNegatedCondition() != null) {
					//dont know how to handle it yet
					index++;
					continue;
				}
				if (actionToDelete.getCondition() == null && actionToDelete.getNegatedCondition() == null) {
					//the action does not have conditon, just skip to next action
					index++;
					continue;
				}
				newActions.clear();
				Obligation mutant = Utils.createObligationCopy();
				Condition ListF = new Condition();
				NegatedCondition NListF = new NegatedCondition();
				Condition tmpA = new Condition();
				NegatedCondition tmpB = new NegatedCondition();
				for (int j = 0; j < actions.size(); j++) {
					Action act = actions.get(j);
					if (index == j) {
						//negate condition of the action, change condition to negatedCondition, and vice verse
						ListF = act.getCondition();
						NListF = act.getNegatedCondition();
						if (NListF != null) {
							tmpA.setCondition(NListF.getCondition());
							act.setCondition(tmpA);
						} else {
							act.setCondition(null);
						}
						if (ListF != null) {
							tmpB.setCondition(ListF.getCondition());
							act.setNegatedCondition(tmpB);
						} else {
							act.setNegatedCondition(null);
						}
					}
					newActions.add(act);
				}
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
				i++;
				index++;
				actionToDelete.setCondition(ListF);
				actionToDelete.setNegatedCondition(NListF);
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
