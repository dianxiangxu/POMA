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
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class CDA_Solver extends MutantTester {
	static Graph g;
	public CDA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
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
		EvrNode what, where, oldWhere;
		
		getAllEvrNodes(Utils.createObligationCopy());
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			index = 0;
			String ruleLabel = rule.getLabel();
			EventPattern event = rule.getEventPattern();
			
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			constraintR = Utils.getReachabilityConstraint(event);
			if (constraintR == null)
				continue;
			
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			for (Action actionToChange : actions) {
				//Only assign actions are mutated in this mutation operator
				if (!(actionToChange instanceof AssignAction)) {
					index++;
					continue;
				}
				
				//make a copy of newActions
				newActions.clear();
				for (Action action : actions) {
					newActions.add(action);
				}
				newActions.remove(actionToChange);
				
				AssignAction newAction = new AssignAction();
				//AssignAction consists of a list of Assignment
				//Assignment consists of node_what and node_where
				//Here, only mutate node_where to another random existing node
				//This can be extended to mutate both nodes
				List<Assignment> assignments = ((AssignAction)actionToChange).getAssignments();
				for (Assignment as : assignments) {
					what = as.getWhat();
					//make a copy of newAction
					List<Assignment> tmpAssignments = new ArrayList<>();
					for (Assignment n : assignments) {
						tmpAssignments.add(n);
					}
					tmpAssignments.remove(as);
					newAction.setAssignments(tmpAssignments);
					
					oldWhere = as.getWhere();
					for (Node newWhere : UAsOAs) {
						if (newWhere.getName().equals(oldWhere.getName()))
							continue;
						if (!newWhere.getType().toString().equals(oldWhere.getType()))
							continue;
						if (Utils.isContained(newWhere.getName(), what.getName(), graph))
							continue;
						where = new EvrNode(newWhere.getName(), newWhere.getType().toString(), newWhere.getProperties());
						
						System.out.println("(CDA_Solver) " + ruleLabel + "|actionIndex:" + index);
						System.out.println("What:" + what.getName() + "|" + what.getType());
						System.out.println("Old where:" + as.getWhere().getName() + "|" + as.getWhere().getType());
						System.out.println("New where:" + where.getName() + "|" + where.getType());
						Assignment assignment = new Assignment(what, where);
						newAction.addAssignment(assignment);
						
						//keep conditions identical
						newAction.setCondition(actionToChange.getCondition());
						newAction.setNegatedCondition(actionToChange.getNegatedCondition());
						newActions.add(newAction);
						
						//generate mutant
						Obligation mutant = Utils.createObligationCopy();
						mutant = updateActions(mutant, ruleLabel, newActions);
						Utils.setObligationMutant(mutant);
						
						Obligation obM = Utils.createObligationWithCondtionCopy();
						obM = updateActions(obM, ruleLabel, newActions);
						Utils.setObligationMutant(obM);
						
						//generate RConstraint
//						RConstraint = null;
//						for (String subject : Utils.getAllSubject(event)) {
//							for (String operation : event.getOperations()) {
//								for (String target : Utils.getAllTarget(event)) {
//									String a = "PERMIT(?user,"+operation+","+target+")";
//									String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
//									String c = "ASSIGN(?user,"+subject+")";
//									String all = Utils.andP(Utils.andP(a, b), c);
//									if (RConstraint == null)
//										RConstraint = all;
//									else
//										RConstraint = Utils.orP(RConstraint, all);
//								}
//							}
//						}
						
						//generate Pconstraint
						PConstraint = Utils.getPropagationConstraintCDA(what.getName(), oldWhere.getName(), where.getName());
						//generate preConstraint
//						preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
						preConstraint = PConstraint + ";";
						System.out.println(i + "Pre:" + preConstraint);
						//generate postConstraint
						postConstraint = Utils.generatePostConstraint(responsePattern);
						String tmpS = null;
						for (String subject : Utils.getAllSubject(event)) {
							for (String operation : event.getOperations()) {
								for (String target : Utils.getAllTarget(event)) {
									String a = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
									String b = "ASSIGN(?user,"+subject+")";
									String all = Utils.andP(a, b);
									if (tmpS == null)
										tmpS = all;
									else
										tmpS = Utils.orP(tmpS, all);
								}
							}
						}
						postConstraint = Utils.andP(postConstraint, tmpS) + ";"; 
						System.out.println(i + "Post:" + postConstraint);
						
//						Boolean res = Utils.killMutant (mutant, ruleLabel, preConstraint, postConstraint);
						Boolean res = Utils.killMutantT (mutant, ruleLabel, preConstraint, postConstraint, obM);
						if (res) {
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
						}
						setNumberOfMutants(getNumberOfMutants() + 1);
						i++;
						
						//reset mutant
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
