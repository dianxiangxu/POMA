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

public class REU_Solver extends MutantTester {
	static Graph g;
	public REU_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}

	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
//		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, UserConstraint, RConstraint, PConstraint;
		String fireObligation = "";
		int i = 0; //# of mutants
		
		boolean find;
		int mutationType = 0;
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			EventPattern event = rule.getEventPattern();
			Subject subject = event.getSubject();
			String user = subject.getUser();
			if (user == null) {
				List<String> anyUser = subject.getAnyUser();
				if (anyUser == null || anyUser.isEmpty()) {
					EvrProcess process = subject.getProcess();
					if (process == null) {
						//if subject in eventpattern is null, skip to next rule
						System.out.println("INFO(REU) Obligation" + ruleLabel + " has no defined subject!" );
						continue;
					} else {
						System.out.println("ERROR(REU) Mutantion on process hot handled!" );
//						mutant = removeEventProcess(mutant, ruleLabel);
						continue;
					}
				} else {
					if (anyUser.size() == 1) {
						//generate mutant
						System.out.println("REU: subject be removed:" + anyUser.get(0));
						Obligation mutant = Utils.createObligationCopy();
						mutant = removeEventAnyUser(mutant, ruleLabel, 0);
						Utils.setObligationMutant(mutant);
						
						//generate UserConstraint
						UserConstraint = "NOT(ASSIGN(?user,"+anyUser.get(0)+"))";
						
						//generate RConstraint
						RConstraint = null;
						for (String operation : event.getOperations()) {
							for (String target : Utils.getAllTarget(event)) {
								String a = "PERMIT(?user,"+operation+","+target+")";
								String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
								String all = Utils.andP(a, b);
								if (RConstraint == null)
									RConstraint = all;
								else
									RConstraint = Utils.orP(RConstraint, all);
							}
						}
						RConstraint = Utils.andP(UserConstraint, RConstraint);
						
						ResponsePattern responsePattern = rule.getResponsePattern();
						//generate Pconstraint
						PConstraint = Utils.generatePConstraint(responsePattern);
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
					} else {
						//FIXME: no sample currently/code need implementation
						//j is the index of subject which is removed
						//change j to control mutant generation
						for (int j = 0; j < anyUser.size(); j++) {
							//generate mutant, s to be removed
							String s = anyUser.get(j);
							System.out.println("REU: subject be removed:" + s);
							Obligation mutant = Utils.createObligationCopy();
							mutant = removeEventAnyUser(mutant, ruleLabel, j);
							Utils.setObligationMutant(mutant);
							
							//generate UserConstraint
							UserConstraint = null;
							for (String sub : anyUser) {
								if (!s.equals(sub)) {
									if (UserConstraint == null)
										UserConstraint = "NOT(ASSIGN(?user,"+sub+"))";
									else
										UserConstraint = Utils.andP(UserConstraint, "NOT(ASSIGN(?user,"+sub+"))");
								} else {
									if (UserConstraint == null)
										UserConstraint = "ASSIGN(?user,"+s+")";
									else
										UserConstraint = Utils.andP(UserConstraint, "ASSIGN(?user,"+s+")");
								}
							}
							//generate RConstraint
							RConstraint = null;
							for (String operation : event.getOperations()) {
								for (String target : Utils.getAllTarget(event)) {
									String a = "PERMIT(?user,"+operation+","+target+")";
									String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
									String all = Utils.andP(a, b);
									if (RConstraint == null)
										RConstraint = all;
									else
										RConstraint = Utils.orP(RConstraint, all);
								}
							}
							RConstraint = Utils.andP(UserConstraint, RConstraint);
							
							ResponsePattern responsePattern = rule.getResponsePattern();
							//generate Pconstraint
							PConstraint = Utils.generatePConstraint(responsePattern);
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
			} else {
				//generate mutant
				System.out.println("REU: subject be removed:" + user);
				Obligation mutant = Utils.createObligationCopy();
				mutant = removeEventAnyUser(mutant, ruleLabel, 0);
				Utils.setObligationMutant(mutant);
				
				//generate UserConstraint
				UserConstraint = "NOT(ASSIGN(?user,"+user+"))";
				
				//generate RConstraint
				RConstraint = null;
				for (String operation : event.getOperations()) {
					for (String target : Utils.getAllTarget(event)) {
						String a = "PERMIT(?user,"+operation+","+target+")";
						String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
						String all = Utils.andP(a, b);
						if (RConstraint == null)
							RConstraint = all;
						else
							RConstraint = Utils.orP(RConstraint, all);
					}
				}
				RConstraint = Utils.andP(UserConstraint, RConstraint);
				
				ResponsePattern responsePattern = rule.getResponsePattern();
				//generate Pconstraint
				PConstraint = Utils.generatePConstraint(responsePattern);
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
	
	private Obligation clearEventUser(Obligation obligation, String ruleLabel) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject("");
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(null);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	private Obligation removeEventAnyUser(Obligation obligation, String ruleLabel, int index) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				List<String> anyUser = eventPattern.getSubject().getAnyUser();
				anyUser.remove(index);
				//change anyUser to empty array
				Subject subject = new Subject(anyUser);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(anyUser);
				eventPattern.setSubject(subject);
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}

	public String getRandomUserName(List<String> Us) {
		//0 can be replaced by a random number from 0 to length(Us)
		String userName = Us.get(0);
		System.out.println(userName);
		return userName;
	}
}
