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
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class ICA extends MutantTester {
	int i;
	static Graph g;
	public ICA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
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
		EvrNode what, where, oldWhere;
		
		getAllEvrNodes(Utils.createObligationCopy());
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				//these types of action not handled
				if (!(actionToChange instanceof CreateAction)) {
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
				
				CreateAction newAction = new CreateAction();
				//CreateAction consists of a list of create action
				//Create action consists of node_what and node_where
				//Here, only mutate node_where to another random existing node
				//This can be extended to mutate both nodes
				List<CreateNode> createNodesList = ((CreateAction)actionToChange).getCreateNodesList();
				for (CreateNode cr : createNodesList) {
					what = cr.getWhat();
					//make a copy of newAction
					List<CreateNode> tmpCreateNodesList = new ArrayList<>();
					for (CreateNode n : createNodesList) {
						tmpCreateNodesList.add(n);
					}
					tmpCreateNodesList.remove(cr);
					
					
					oldWhere = cr.getWhere();
					for (Node newWhere : UAsOAs) {
						if (newWhere.getName().equals(oldWhere.getName()))
							continue;
						if (!newWhere.getType().toString().equals(oldWhere.getType()))
							continue;
						where = new EvrNode(newWhere.getName(), newWhere.getType().toString(), newWhere.getProperties());
						CreateNode node = new CreateNode(what, where);
						newAction.addCreateNode(node);
						
						//keep conditions identical
						List<CreateNode> tmpList = new ArrayList<>();
						for (CreateNode n : tmpCreateNodesList) {
							tmpList.add(n);
						}
						newAction.setCreateNodesList(tmpList);
						newAction.setCreateNodesList(tmpList);
						
						newAction.setRules(null);
						newAction.setCondition(actionToChange.getCondition());
						newAction.setNegatedCondition(actionToChange.getNegatedCondition());
						newActions.add(newAction);
						
						mutant = updateActions(mutant, ruleLabel, newActions);
						
						Utils.setObligationMutant(mutant);

						AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
						if (q == null) {
							System.out.println("Mutant equivalent!(ICA:" + i + ") Inccorect Create Action:" + rule.getLabel());
						} else {
							Utils.addToARList(q);
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//							System.out.println("Mutant not equivalent!(ICA:" + i + ") Inccorect Create Action:" + rule.getLabel());
//							System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
						}
						i++;
						setNumberOfMutants(getNumberOfMutants() + 1);	
						newActions.remove(newAction);
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
	EvrNode getRandomWhere (String mutateName) {
		for (EvrNode node : EvrNodes) {
			if (node.getName().equals(mutateName))
				continue;
			if (!node.getType().equals("OA"))
				continue;
			return node;
		}
		return null;
	}
}
