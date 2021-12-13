package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction;
import gov.nist.csd.pm.pip.obligations.model.actions.FunctionAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction.Target.Container;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class COA extends MutantTester {
	int i;
	static Graph g;
	public COA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
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
		EvrNode what, where;
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				//these types of action not handled
				if ((actionToChange instanceof FunctionAction) || (actionToChange instanceof DeleteAction)) {
					index++;
					continue;
				}
				Obligation obligation = Utils.createObligationCopy();
				Obligation mutant = Utils.createObligationCopy();
				newActions.clear();
				for (Action action : actions) {
					newActions.add(action);
				}
				newActions.remove(actionToChange);
				
				//change assign to create
				if ((actionToChange instanceof AssignAction)) {
					CreateAction newAction = new CreateAction();
					List<Assignment> assignments = ((AssignAction)actionToChange).getAssignments();
					
					for (Assignment as : assignments) {
						what = as.getWhat();
						where = as.getWhere();
						//FIXME: custom functions not handled
						if ((what.getFunction() != null) || (where.getFunction() != null))
							continue;
						CreateNode node = new CreateNode(what, where);
						newAction.addCreateNode(node);
					}
					newAction.setRules(null);
					newAction.setCondition(actionToChange.getCondition());
					newAction.setNegatedCondition(actionToChange.getNegatedCondition());
					newActions.add(newAction);
				}
				//change create to assign
				if (actionToChange instanceof CreateAction) {
					AssignAction newAction = new AssignAction();
					List<CreateNode> createNodesList = ((CreateAction)actionToChange).getCreateNodesList();
					for (CreateNode cr : createNodesList) {
						what = cr.getWhat();
						where = cr.getWhere();
						//FIXME: custom functions not handled
						if ((what.getFunction() != null) || (where.getFunction() != null))
							continue;
						Assignment assignment = new Assignment(what, where);
						newAction.addAssignment(assignment);
					}
					newAction.setCondition(actionToChange.getCondition());
					newAction.setNegatedCondition(actionToChange.getNegatedCondition());
					newActions.add(newAction);
				}
				//change grant to deny
				if (actionToChange instanceof GrantAction) {
//					//FIXME: add prohibition(deny) not supplied
//					continue;
					EvrNode node = ((GrantAction)actionToChange).getTarget();
					DenyAction.Target target = new Target();
					Container container = new Container(node.getName(), node.getType(), node.getProperties());
					List<Container> containers = new ArrayList<>();
					containers.add(container);
					
					DenyAction newAction = new DenyAction();
					newAction.setSubject(((GrantAction)actionToChange).getSubject());
					newAction.setOperations(((GrantAction)actionToChange).getOperations());
					target.setComplement(false);
					target.setIntersection(false);
					target.setContainers(containers);
					newAction.setTarget(target);
					newAction.setCondition(actionToChange.getCondition());
					newAction.setNegatedCondition(actionToChange.getNegatedCondition());
					newAction.setLabel(node.getName());
					newActions.add(newAction);
				}
				//change deny to grant
				if (actionToChange instanceof DenyAction) {
					
					Target target = ((DenyAction)actionToChange).getTarget();
					List<Container> containers = target.getContainers();
					//containers has at least one element
					Container container = containers.get(0);
					EvrNode node = new EvrNode(container.getName(), container.getType(), container.getProperties());
					
					GrantAction newAction = new GrantAction();
					newAction.setSubject(((DenyAction)actionToChange).getSubject());
					newAction.setOperations(((DenyAction)actionToChange).getOperations());
					newAction.setTarget(node);
					newAction.setCondition(actionToChange.getCondition());
					newAction.setNegatedCondition(actionToChange.getNegatedCondition());
					newActions.add(newAction);
				}
				
				mutant = updateActions(mutant, ruleLabel, newActions);
				
				Utils.setObligationMutant(mutant);

				AccessRequest q = null;
				try {
					q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
					if (q == null) {
						System.out.println("Mutant equivalent!(COA:" + i + ") Change One Action:" + rule.getLabel());
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(COA:" + i + ") Change One Action:" + rule.getLabel());
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
				} catch (PMException e) {
					System.out.println("Error in triggering obligation");
					setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
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
