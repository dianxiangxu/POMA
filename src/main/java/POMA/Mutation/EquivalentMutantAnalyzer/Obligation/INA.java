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
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction.Target.Container;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class INA extends MutantTester {
	int i;
	static Graph g;
	public INA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		i = 0;
		int index;
		Target target, oldTarget;
		Container newContainer;
		List<Action> newActions = new ArrayList<>();
		
		getAllEvrNodes(Utils.createObligationCopy());
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				if (!(actionToChange instanceof DenyAction)) {
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
				
				DenyAction newAction = new DenyAction();
				//Here, only mutate target node to another random existing node
				//This can be extended to mutate subject and mutate operation set as well

				oldTarget = ((DenyAction)actionToChange).getTarget();
				List<Container> containers = oldTarget.getContainers();
				for (Container container : containers) {
					//make a copy of newAction
					List<Container> tmpContainers = new ArrayList<>();
					for (Container c : containers) {
						tmpContainers.add(c);
					}
					tmpContainers.remove(container);

					for (Node node : UAsOAs) {
						if (node.getName().equals(container.getName()))
							continue;
						if (!node.getType().toString().equals(container.getType()))
							continue;
						newContainer = new Container(node.getName(), node.getType().toString(), node.getProperties());
						tmpContainers.add(newContainer);
						
						target = new Target();
						target.setContainers(tmpContainers);
						target.setComplement(oldTarget.isComplement());
						target.setIntersection(oldTarget.isIntersection());
						newAction.setLabel(((DenyAction)actionToChange).getLabel());
						newAction.setSubject(((DenyAction)actionToChange).getSubject());
						newAction.setOperations(((DenyAction)actionToChange).getOperations());
						newAction.setTarget(target);
					
						//keep conditions identical
						newAction.setCondition(actionToChange.getCondition());
						newAction.setNegatedCondition(actionToChange.getNegatedCondition());
						newActions.add(newAction);
						
						mutant = updateActions(mutant, ruleLabel, newActions);
					
						Utils.setObligationMutant(mutant);
						AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
						if (q == null) {
							System.out.println("Mutant equivalent!(INA:" + i + ") Inccorect Deny Action:" + rule.getLabel());
						} else {
							Utils.addToARList(q);
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//							System.out.println("Mutant not equivalent!(INA:" + i + ") Inccorect Deny Action:" + rule.getLabel());
//							System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
						}
						i++;
						setNumberOfMutants(getNumberOfMutants() + 1);	
						//revert status 
						newActions.remove(newAction);
						tmpContainers.remove(newContainer);
					}
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
