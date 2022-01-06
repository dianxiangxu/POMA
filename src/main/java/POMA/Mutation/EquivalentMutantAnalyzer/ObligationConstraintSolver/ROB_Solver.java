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
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.NegatedCondition;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import prohibition interfaces

public class ROB_Solver extends MutantTester {
	static Graph g;
	public ROB_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testMethod, graph, prohibitions, obligationPath, arList);
	}


	public void init() throws Exception {
		this.mutationMethod = "";
		List<String> constraintR = new ArrayList<String>();
//		List<String> constraintN = new ArrayList<String>();
		List<String> constraintP = new ArrayList<String>();
		String preConstraint, postConstraint, PConstraint;
		int i = 0;//mutant #
		
		List<Rule> rules = Utils.createObligationCopy().getRules();
		for (Rule rule : rules) {
			String ruleLabel = rule.getLabel();
			//FIXME: obligation4 in Lawfirm example not working
			if (ruleLabel.equals("obligation4") && Utils.createObligationCopy().getLabel().equals("LawUseCase Obligations") )
				continue;
			
			EventPattern event = rule.getEventPattern();
			
			//generate mutant 
			Obligation mutant = Utils.createObligationCopy();
			mutant = removeOneRule(mutant, ruleLabel);
			Utils.setObligationMutant(mutant);
			
			//generate Rconstraint
			String RConstraint = null;
			for (String subject : Utils.getAllSubject(event)) {
				for (String operation : event.getOperations()) {
					for (String target : Utils.getAllTarget(event)) {
						String a = "ASSIGN(?u,"+subject+")";
						String b = "PERMIT(?u,"+operation+","+target+")";
						String c = "OBLIGATIONLABEL("+ruleLabel+",?u,"+operation+","+target+")";
						String all = Utils.andP(Utils.andP(a, b), c);
						if (RConstraint == null)
							RConstraint = all;
						else
							RConstraint = Utils.orP(RConstraint, all);
					}
				}
			}	
			
			ResponsePattern responsePattern = rule.getResponsePattern();
			//generate Pconstraint
			PConstraint = Utils.generatePConstraint(responsePattern);
			//generate preConstraint
			preConstraint = Utils.andP(RConstraint, PConstraint) + ";";
			System.out.println(i + "Pre:" + preConstraint);
			//generate postConstraint
			postConstraint = Utils.generatePostConstraint(responsePattern);
			System.out.println(i + "Post:" + postConstraint);
			
			//send to solver
			List<AccessRequest> eventList = Utils.sendToSolver(createCopy(), Utils.createProhibitionsCopy(), Utils.createObligationCopy(), preConstraint, postConstraint);
//			if (eventList == null) {
//				eventList = Utils.sendToSolver(createCopy(), Utils.createProhibitionsCopy(), mutant, preConstraint, postConstraint);
//			}
			if (eventList == null) {
				//equivalent mutant
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
				continue;
			}
			
			//run policy machine
			AccessRequest q = Utils.verifyEventList(Utils.createObligationCopy(), mutant, eventList, ruleLabel);
			if (q == null) {
				//equivalent mutant
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
				continue;
			} else {
				System.out.println("Mutant Killed!");
				//FIXME: should save eventList+assert request, q, into test suite
//				System.out.println(eventList.toString());
				System.out.println(q.getSA() + "," + q.getAR() + "," + q.getTA());
//				Utils.addToARList(eventList);
//				Utils.addToARList(q);
				
				i++;
				setNumberOfMutants(getNumberOfMutants() + 1);
				setNumberOfKilledMutants(getNumberOfKilledMutants() + 1);
			}
		}
	}
		

	
	private Obligation removeOneRule(Obligation obligation, String ruleLabel) {
		if (ruleLabel == null)
			return null;
		List<Rule> rules = obligation.getRules();
		List<Rule> newRules = new ArrayList<>();
		
		for (Rule newRule : rules) {
			if (newRule.getLabel().equals(ruleLabel))
				continue;
			newRules.add(newRule);
		}
		obligation.setRules(newRules);
		return obligation;
	}

}
