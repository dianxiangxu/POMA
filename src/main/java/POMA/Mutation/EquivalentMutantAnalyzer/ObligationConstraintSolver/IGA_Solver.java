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
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class IGA_Solver extends MutantTester {
	static Graph g;
	public IGA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, RConstraint, PConstraint;
		int i = 0; //index of mutant
		int index; //index of action
		List<Action> newActions = new ArrayList<>();
		EvrNode subject, target, oldTarget;
		
		getAllEvrNodes(Utils.createObligationCopy());
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			EventPattern event = rule.getEventPattern();
			constraintR = Utils.getReachabilityConstraint(event);
			if (constraintR == null)
				continue;
			
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				if (!(actionToChange instanceof GrantAction)) {
					index++;
					continue;
				}
				//generate mutant
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();
				//make a copy of newActions
				newActions.clear();
				for (Action action : actions) {
					newActions.add(action);
				}
				newActions.remove(actionToChange);
				
				GrantAction newAction = new GrantAction();
				//Here, only mutate target node to another random existing node
				//This can be extended to mutate subject and mutate operation set as well
				subject = ((GrantAction)actionToChange).getSubject();

				oldTarget = ((GrantAction)actionToChange).getTarget();
				for (Node newWhere : UAsOAs) {
					if (newWhere.getName().equals(subject.getName()))
						continue;
					if (newWhere.getName().equals(oldTarget.getName()))
						continue;
					if (!newWhere.getType().toString().equals(oldTarget.getType()))
						continue;
					target = new EvrNode(newWhere.getName(), newWhere.getType().toString(), newWhere.getProperties());
					
					newAction.setSubject(subject);
					newAction.setOperations(((GrantAction)actionToChange).getOperations());
					newAction.setTarget(target);
					
					//keep conditions identical
					newAction.setCondition(actionToChange.getCondition());
					newAction.setNegatedCondition(actionToChange.getNegatedCondition());
					newActions.add(newAction);
					
					mutant = updateActions(mutant, ruleLabel, newActions);
					Utils.setObligationMutant(mutant);
					
					//generate RConstraint
					RConstraint = null;
					for (String s : Utils.getAllSubject(event)) {
						for (String operation : event.getOperations()) {
							for (String t : Utils.getAllTarget(event)) {
								String a = "PERMIT(?user,"+operation+","+t+")";
								String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+t+")";
								String c = "ASSIGN(?user,"+s+")";
								String all = Utils.andP(Utils.andP(a, b), c);
								if (RConstraint == null)
									RConstraint = all;
								else
									RConstraint = Utils.orP(RConstraint, all);
							}
						}
					}
					
					//generate Pconstraint
					PConstraint = Utils.getPropagationConstraintIGA(subject.getName(), oldTarget.getName(), target.getName(), newAction.getOperations());
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
					
					//reset mutant
					newActions.remove(newAction);
				}
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
	
	//return an OA node as node_where
	EvrNode getRandomWhere (EvrNode oldWhere) throws PMException {
		for (EvrNode node : EvrNodes) {
			if (node.getName().equals(oldWhere.getName()))
				continue;
			if (!node.getType().equals(oldWhere.getType()))
				continue;
			return node;
		}
		return null;
	}
}
