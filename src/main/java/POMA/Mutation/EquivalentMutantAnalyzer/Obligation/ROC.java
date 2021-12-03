package POMA.Mutation.EquivalentMutantAnalyzer.Obligation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.IteratorUtils;

import CaseStudies.gpms.customFunctions.AddPropertiesToNodeExecutor;
import CaseStudies.gpms.customFunctions.AllChildrenHavePropertiesExecutor;
import CaseStudies.gpms.customFunctions.CoPIToAddExecutor;
import CaseStudies.gpms.customFunctions.CoPIToDeleteExecutor;
import CaseStudies.gpms.customFunctions.CompareNodeNamesExecutor;
import CaseStudies.gpms.customFunctions.ConcatExecutor;
import CaseStudies.gpms.customFunctions.CreateNodeExecutor1;
import CaseStudies.gpms.customFunctions.DeleteNodeExecutor;
import CaseStudies.gpms.customFunctions.GetAncestorInPCExecutor;
import CaseStudies.gpms.customFunctions.GetAncestorsInPCExecutor;
import CaseStudies.gpms.customFunctions.GetChildExecutor;
import CaseStudies.gpms.customFunctions.GetChildInPCExecutor;
import CaseStudies.gpms.customFunctions.GetChildrenUsersInPolicyClassExecutor;
import CaseStudies.gpms.customFunctions.IRBApprovalRequired;
import CaseStudies.gpms.customFunctions.IsNodeInListExecutor;
import CaseStudies.gpms.customFunctions.RemovePropertyFromChildrenExecutor;
import CaseStudies.gpms.customFunctions.SPToAddExecutor;
import CaseStudies.gpms.customFunctions.SPToDeleteExecutor;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.Decider;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.obligations.model.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class ROC extends MutantTester {
	int i;
	static Graph g;
	public ROC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		String testResults = "CSV/" + testMethod + "/" + testMethod + "testResults.csv";
		String testSuitePath = getTestSuitPathByMethod(testMethod);
		String changeToUser;
		i = 0;
		
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
					if (responsePattern.getCondition() != null) {
						responsePattern.setCondition(null);
					} else {
						responsePattern.setNegatedCondition(null);
					}
					System.out.println("Running (ROC) " + ruleLabel);
					Utils.setObligationMutant(mutant);
					
					AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
					if (q == null) {
						System.out.println("Mutant equivalent!(ROC:" + i + ") Remove One Condition:" + rule.getLabel());
					} else {
						Utils.addToARList(q);
						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//						System.out.println("Mutant not equivalent!(ROC:" + i + ") Remove One Condition:" + rule.getLabel());
//						System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
					}
					i++;
					setNumberOfMutants(getNumberOfMutants() + 1);	
				}
			}
		}
		
		//negate condition within actions
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			ResponsePattern responsePattern = rule.getResponsePattern();
			List<Action> actions = responsePattern.getActions();
			int index = 0;
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
						if (newAction.getCondition() != null) {
							newAction.setCondition(null);
						} else {
							newAction.setNegatedCondition(null);
						}
						System.out.println("Running (ROC) " + ruleLabel + "|actionIndex:" + index);
						Utils.setObligationMutant(mutant);
						
						AccessRequest q = Utils.decideEquivalentMutant(obligation, mutant, null, rule.getLabel());
						if (q == null) {
							System.out.println("Mutant equivalent!(ROC:" + i + ") Remove One Condition:" + rule.getLabel());
						} else {
							Utils.addToARList(q);
							setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//							System.out.println("Mutant not equivalent!(ROC:" + i + ") Remove One Condition:" + rule.getLabel());
//							System.out.println("    Access request to show difference: " + q.getSA() + " " + q.getAR() + " " + q.getTA());
						}
						i++;
						setNumberOfMutants(getNumberOfMutants() + 1);	
					}
				}
				index++;
			}
		}
	}
	
	private boolean isAbleToMutate(Rule r) {
		ResponsePattern responsePattern = r.getResponsePattern();
		if ((responsePattern.getCondition() == null) && (responsePattern.getNegatedCondition() == null)) 
			return false;
		return true;
	}
	
	
	private boolean isAbleToMutate(Action action) {
		Condition condition = action.getCondition();
		NegatedCondition negatedCondition = action.getNegatedCondition();
		if (condition == null)
			if (negatedCondition == null)
				return false;
		return true;
	}
	
	public List<Function> addAllExcept (List<Function> functions, Function f) {
		List<Function> newFunctions = new ArrayList<>();
		newFunctions.addAll(functions);
		newFunctions.remove(f);
		return newFunctions;
	}
	public List<Function> addFunction (List<Function> functions, Function f) {
		List<Function> newFunctions = new ArrayList<>();
		newFunctions.addAll(functions);
		newFunctions.add(f);
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
