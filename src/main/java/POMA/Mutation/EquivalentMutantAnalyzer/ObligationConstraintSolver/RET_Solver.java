package POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver;

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
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.obligations.model.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces
import POMA.Mutation.ProhibitionMutationOperators.ProhibitionMutation;

public class RET_Solver extends MutantTester {
	static Graph g;
	public RET_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
//		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, TargetConstraint, RConstraint, PConstraint;
		String fireObligation = "";
		int i = 0; //index of mutant
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			EventPattern eventPattern = rule.getEventPattern();
			Target target = eventPattern.getTarget();
			List<EvrNode> policyElements = target.getPolicyElements();
			for (EvrNode peToDelete : policyElements) {
				//generate mutant
				System.out.println("Remove PE: " + peToDelete.getName() + " in rule: " + ruleLabel);
				Obligation mutant = Utils.createObligationCopy();
				mutant = removeEventPolicyElement(mutant, ruleLabel, peToDelete);
				Utils.setObligationMutant(mutant);
				
				Obligation obM = Utils.createObligationWithCondtionCopy();
				obM = removeEventPolicyElement(obM, ruleLabel, peToDelete);
				Utils.setObligationMutant(obM);
				
				//generate TargetConstraint
				TargetConstraint = null;
				if (policyElements.size() == 1) {
					TargetConstraint = "NOT(ASSIGN(?object,"+peToDelete.getName()+"))";
				} else {
					for (String t : Utils.getAllTarget(eventPattern)) {
						if (!t.equals(peToDelete.getName())) {
							if (TargetConstraint == null)
								TargetConstraint = "NOT(ASSIGN(?object,"+t+"))";
							else
								TargetConstraint = Utils.andP(TargetConstraint, "NOT(ASSIGN(?object,"+t+"))");
						} else {
							if (TargetConstraint == null)
								TargetConstraint = "ASSIGN(?object,"+peToDelete.getName()+")";
							else
								TargetConstraint = Utils.andP(TargetConstraint, "NOT(ASSIGN(?object,"+peToDelete.getName()+"))");
						}
					}
				}
				
				//generate RConstraint
//				RConstraint = null;
//				for (String subject : Utils.getAllSubject(eventPattern)) {
//					for (String operation : eventPattern.getOperations()) {
//						String a = "PERMIT(?user,"+operation+",?object)";
//						String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+",?object)";
//						String c = "ASSIGN(?user,"+subject+")";
//						String all = Utils.andP(Utils.andP(a, b), c);
//						if (RConstraint == null)
//							RConstraint = all;
//						else
//							RConstraint = Utils.orP(RConstraint, all);
//					}
//				}
//				RConstraint = Utils.andP(TargetConstraint, RConstraint);
				RConstraint = TargetConstraint;
				
				ResponsePattern responsePattern = rule.getResponsePattern();
				//generate Pconstraint
				PConstraint = Utils.generatePConstraint(responsePattern);
				//generate preConstraint
				if (RConstraint == null)
					preConstraint = PConstraint + ";";
				else
					preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
				System.out.println(i + "Pre:" + preConstraint);
				//generate postConstraint
				postConstraint = Utils.generatePostConstraint(responsePattern);
				String tmpS = null;
//				for (String subject : Utils.getAllSubject(eventPattern)) {
					for (String operation : eventPattern.getOperations()) {
						String a = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+",?object)";
//						String b = "ASSIGN(?user,"+subject+")";
//						String all = Utils.andP(a, b);
						String all = a;
						if (tmpS == null)
							tmpS = all;
						else
							tmpS = Utils.orP(tmpS, all);
					}
//				}
				postConstraint = Utils.andP(postConstraint, tmpS) + ";"; 
				System.out.println(i + "Post:" + postConstraint);
				
//				Boolean res = Utils.killMutant (mutant, ruleLabel, preConstraint, postConstraint);
				Boolean res = Utils.killMutantT (mutant, ruleLabel, preConstraint, postConstraint, obM);
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
	
	private Obligation removeEventPolicyElement(Obligation obligation, String ruleLabel, EvrNode peToDelete) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				Target target = eventPattern.getTarget();
				List<EvrNode> policyElements = target.getPolicyElements();
				if (policyElements.size() == 1) {
//					target.setPolicyElements(null);
//					eventPattern.setTarget(target);	
					eventPattern.setTarget(null);	
					newRule.setEventPattern(eventPattern);
				} else {
					List<EvrNode> newPEs = new ArrayList<>();
					for (EvrNode node : policyElements) {
						if (node.getName().equals(peToDelete.getName())) {
							continue;
						}
						newPEs.add(node);
					}
					
					target.setPolicyElements(newPEs);
					eventPattern.setTarget(target);	
					newRule.setEventPattern(eventPattern);
				}
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
}
