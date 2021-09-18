package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.model.Condition;
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

public class IGA extends MutantTester {
	int i;
	static Graph g;
	public IGA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		i = 0;
		int index;
		List<Action> newActions = new ArrayList<>();
		EvrNode subject, target, oldTarget;
		
		getAllEvrNodes(Utils.createObligationCopy());
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				if (!(actionToChange instanceof GrantAction)) {
					index++;
					continue;
				}
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
					try {
						AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
						if (q == null) {
							System.out.println("Mutant equivalent!(IGA:" + i + ") Inccorect Grant Action:" + rule.getLabel());
						} else {
							Utils.addToARList(q);
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//							System.out.println("Mutant not equivalent!(IGA:" + i + ") Inccorect Grant Action:" + rule.getLabel());
//							System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
						}
					} catch (PMException e) {
						System.out.println("Error in triggering obligation");
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
					}
					
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);	
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
