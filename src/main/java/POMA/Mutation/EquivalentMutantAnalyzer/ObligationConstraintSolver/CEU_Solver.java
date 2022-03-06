package POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrProcess;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;

//import prohibition interfaces

public class CEU_Solver extends MutantTester {
	static Graph g;
	public CEU_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}


	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
//		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, RConstraint, PConstraint;
		String UserConstraint;
		String fireObligation = "";
		String changeType;
		String changeToSubject;
		boolean find;
		int mutationType = 0;
		int i = 0;//# of mutants
		
		
		Graph graph = Utils.createCopy();
		
		List<Node> UAs = Utils.getUAsInGraph(graph);
		List<Node> Us = Utils.getUsInGraph(graph);

		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			//do mutation
			for (int j = 0; j < UAs.size(); j++) {
				Obligation obligation = Utils.createObligationCopy();
				EventPattern event = null;
				Subject subject = null;
				String user = null;
				for (Rule tmpRule : obligation.getRules()) {
					if (tmpRule.getLabel().equals(ruleLabel)) {
						event = tmpRule.getEventPattern();
						subject = event.getSubject();
						break;
					}
				}
				
				user = subject.getUser();
				if (user == null) {
					List<String> anyUser = subject.getAnyUser();
					if (anyUser == null || anyUser.isEmpty()) {
						EvrProcess process = subject.getProcess();
						if (process == null || process.getValue().isEmpty()) {
							//if subject in eventpattern is null, skip to next obligation rule
							System.out.println("INFO(CEU) Obligation" + ruleLabel + " has no defined subject!" );
							break;
						} else {
							System.out.println("ERROR(CEU) Mutantion on process hot handled!" );
							break;
						}
					} else {
						//mutation on anyUser, mutate one subject to changeToSubject
						changeToSubject = Utils.getUserName(j, UAs);
						
						//no mutation if changeToSubject equals to the only subject in anyUser
						if (anyUser.size() == 1 && anyUser.get(0).equals(changeToSubject))
							continue;
						
						for (String s : anyUser) {
							//generate mutant 
							System.out.println("CEU: change from " + s + " to " + changeToSubject);
							List<String> tmp = new ArrayList<String>();
							tmp.addAll(anyUser);
							tmp.remove(s);
							tmp.add(changeToSubject);
							Obligation mutant = Utils.createObligationCopy();
							mutant = changeEventAnyUser(mutant, ruleLabel, tmp);
							Utils.setObligationMutant(mutant);
							
							Obligation obM = Utils.createObligationWithCondtionCopy();
							obM = changeEventAnyUser(obM, ruleLabel, tmp);
							Utils.setObligationMutant(obM);
							
							
							//generate UserConstraint
							UserConstraint = null;
							for (String sub : anyUser) {
								if (!s.equals(sub)) {
									if (UserConstraint == null)
										UserConstraint = "NOT(ASSIGN(?user,"+sub+"))";
									else
										UserConstraint = Utils.andP(UserConstraint, "NOT(ASSIGN(?user,"+sub+"))");
								} else {
									String tmp1 = Utils.andP("ASSIGN(?user,"+s+")", "NOT(ASSIGN(?user,"+changeToSubject+"))");
									String tmp2 = Utils.andP("NOT(ASSIGN(?user,"+s+"))", "ASSIGN(?user,"+changeToSubject+")");
									if (UserConstraint == null)
										UserConstraint = Utils.orP(tmp1, tmp2);
									else
										UserConstraint = Utils.andP(UserConstraint, Utils.orP(tmp1, tmp2));
								}
							}
							
							//generate RConstraint
//							RConstraint = null;
//							for (String operation : event.getOperations()) {
//								for (String target : Utils.getAllTarget(event)) {
//									String a = "PERMIT(?user,"+operation+","+target+")";
//									String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
//									String all = Utils.andP(a, b);
//									if (RConstraint == null)
//										RConstraint = all;
//									else
//										RConstraint = Utils.orP(RConstraint, all);
//								}
//							}
//							RConstraint = Utils.andP(UserConstraint, RConstraint);
							RConstraint = UserConstraint;
							
							ResponsePattern responsePattern = rule.getResponsePattern();
							//generate Pconstraint
							PConstraint = Utils.generatePConstraint(responsePattern);
							//generate preConstraint
							preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
							System.out.println(i + "Pre:" + preConstraint);
							//generate postConstraint
							postConstraint = Utils.generatePostConstraint(responsePattern);
							String tmpS = null;
							for (String operation : event.getOperations()) {
								for (String target : Utils.getAllTarget(event)) {
									String a = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
									if (tmpS == null)
										tmpS = a;
									else
										tmpS = Utils.orP(tmpS, a);
								}
							}
							postConstraint = Utils.andP(postConstraint, tmpS) + ";"; 
							System.out.println(i + "Post:" + postConstraint);
							
//							Boolean res = Utils.killMutant (mutant, ruleLabel, preConstraint, postConstraint);
							Boolean res = Utils.killMutantT (mutant, ruleLabel, preConstraint, postConstraint, obM);
							if (res) {
								setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
							}
							setNumberOfMutants(getNumberOfMutants() + 1);
							i++;
						}
					}
				} else {
					//FIXME: no sample right now. Code needs implementation.
//					changeToSubject = Utils.getUserName(j, UAs);
//					if (changeToSubject.equals(user))
//						continue;
//					
//					//generate mutant 
//					System.out.println("CEU: change from " + user + " to " + changeToSubject);
//					Obligation mutant = Utils.createObligationCopy();
//					mutant = changeEventUser(mutant, ruleLabel, changeToSubject);
//					Utils.setObligationMutant(mutant);
//					
//					//generate Rconstraint
//					RConstraint = null;
//					String tmp1 = Utils.andP("ASSIGN(?user,"+user+")", "NOT(ASSIGN(?user,"+changeToSubject+"))");
//					String tmp2 = Utils.andP("NOT(ASSIGN(?user,"+user+"))", "ASSIGN(?user,"+changeToSubject+")");
//					UserConstraint = Utils.orP(tmp1, tmp2);
//					
//					for (String operation : event.getOperations()) {
//						for (String target : Utils.getAllTarget(event)) {
//							String a = "PERMIT(?user,"+operation+","+target+")";
//							String b = "OBLIGATIONLABEL("+ruleLabel+",?user,"+operation+","+target+")";
//							String all = Utils.andP(a, b);
//							if (RConstraint == null)
//								RConstraint = all;
//							else
//								RConstraint = Utils.orP(RConstraint, all);
//						}
//					}
//					RConstraint = Utils.andP(RConstraint, UserConstraint);
//					
//					ResponsePattern responsePattern = rule.getResponsePattern();
//					//generate Pconstraint
//					PConstraint = Utils.generatePConstraint(responsePattern);
//					//generate preConstraint
//					preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
//					System.out.println(i + "Pre:" + preConstraint);
//					//generate postConstraint
//					postConstraint = Utils.generatePostConstraint(responsePattern);
//					System.out.println(i + "Post:" + postConstraint);
//					
//					Boolean res = Utils.killMutant (mutant, ruleLabel, preConstraint, postConstraint);
//					if (res) {
//						System.out.println("Mutant killed!");
//						setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
//					} else
//						System.out.println("Mutant not killed!");
//					setNumberOfMutants(getNumberOfMutants() + 1);
//					i++;
				}
			}
		}
	}
		
	private Obligation changeEventUser(Obligation obligation, String ruleLabel, String changeToUserName) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject(changeToUserName);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(changeToUserName);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	
	private Obligation changeEventAnyUser(Obligation obligation, String ruleLabel, List<String> newAnyUser) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change newUser to ""
				Subject subject = new Subject(newAnyUser);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(newAnyUser);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
	
	private Obligation changeEventProcess(Obligation obligation, String ruleLabel, String newProcess) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel)) {
				EventPattern eventPattern = newRule.getEventPattern();
				//change process to newProcess
				EvrProcess process = new EvrProcess(newProcess);
				Subject subject = new Subject(process);
//				Subject subject = Subject.class.getConstructor(String.class).newInstance(newAnyUser);
				eventPattern.setSubject(subject);
				
				newRule.setEventPattern(eventPattern);
			}
				
			newRules.add(newRule);
		}

		obligation.setRules(newRules);
		return obligation;
	}
}
