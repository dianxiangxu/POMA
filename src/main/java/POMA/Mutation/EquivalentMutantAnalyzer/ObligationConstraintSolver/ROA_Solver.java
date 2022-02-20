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

public class ROA_Solver extends MutantTester {
	static Graph g;
	public ROA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
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
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			EventPattern eventPattern = rule.getEventPattern();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();

			//this mutant is included in ROB
			if (actions.size() <= 1)
				continue;
			
			for (Action actionToDelete : actions) {
				//generate mutant
				Obligation mutant = Utils.createObligationCopy();//delete one action from current action list
				newActions.clear();
				for (Action action : actions) {
					newActions.add(action);
				}
				newActions.remove(actionToDelete); 
				mutant = updateActions(mutant, ruleLabel, newActions);
				Utils.setObligationMutant(mutant);
				
				//generate RConstraint
				RConstraint = null;
				for (String subject : Utils.getAllSubject(eventPattern)) {
					for (String operation : eventPattern.getOperations()) {
						for (String target : Utils.getAllTarget(eventPattern)) {
							String a = "PERMIT(?user,"+operation+","+target+")";
							String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
							String c = "ASSIGN(?user,"+subject+")";
							String all = Utils.andP(Utils.andP(a, b), c);
							if (RConstraint == null)
								RConstraint = all;
							else
								RConstraint = Utils.orP(RConstraint, all);
						}
					}
				}
				
				//generate Pconstraint
				PConstraint = Utils.generatePConstraintOneAction(actionToDelete);
				//generate preConstraint
				preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
				System.out.println(i + "Pre:" + preConstraint);
				//generate postConstraint
				postConstraint = Utils.generatePostConstraint(responsePattern);
				System.out.println(i + "Post:" + postConstraint);
				
				Boolean res = Utils.killMutant (mutant, ruleLabel, preConstraint, postConstraint);
				if (res) {
					System.out.println("Mutant killed!");
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
				} else
					System.out.println("Mutant not killed!");
				setNumberOfMutants(getNumberOfMutants() + 1);
				i++;
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
