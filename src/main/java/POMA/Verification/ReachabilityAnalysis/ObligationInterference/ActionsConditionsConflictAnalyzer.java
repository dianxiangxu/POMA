package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.HashMap;

import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.ObligationChecker;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.pip.graph.Graph;

public class ActionsConditionsConflictAnalyzer {

	Obligation obligation;
	Graph graph;
	List<Conflict> CONFLICTS = new ArrayList<Conflict>();
//	public enum CONFLICT_TYPE {
//		AddAssignmentAddAssignmentAction, AddAssignmentCreatePEAction, AddAssignmentDeleteAssignmentAction,
//		AddAssignmentAddGrantAction, AddAssignmentDeletePEAction, AddDeleteGrantAction, AddGrantAddAssignmentAction,
//		AddGrantDeleteAssignmentAction, AddGrantDeletePEAction, AddGrantCreatePEAction, AddAssignmentDeleteGrantAction,
//		CreatePEAddAssignmentAction, CreatePECreatePEAction, CreatePEDeleteGrantAction, CreatePEDeleteAssignmentAction,
//		CreatePEDeletePEAction, DeletePEDeletePEAction, DeletePEDeleteAssignmentAction, DeletePEDeleteGrantAction,
//		DeletePECreatePEAction, DeletePEAddAssignmentAction, DirtyAssociation, DirtyCycle, DirtyProhibition,
//		DirtyReadCondition, GrantPrecondition, AssignmentPrecondition, NoConflict
//	}

	private List<Rule> getObligationsWithConditions(List<Rule> allObligations) {
		List<Rule> obligationsWithConditions = new ArrayList<Rule>();
		for (Rule r : allObligations) {
			List<Action> actions = r.getResponsePattern().getActions();
			for (Action a : actions) {
				if (a.getCondition() != null || a.getNegatedCondition() != null) {
					obligationsWithConditions.add(r);
					break;
				}
			}
		}
		return obligationsWithConditions;
	}

	private void findConditionConflicts(List<Rule> allObligations, List<Rule> obligationsWithConditions) {

		for (Rule rA : allObligations) {
			List<Action> actionsA = rA.getResponsePattern().getActions();

			for (Rule rC : obligationsWithConditions) {
				if (rA.getLabel().equals(rC.getLabel())) // don't need to check the conflicts of the same obligation
				{
					continue;
				}

				List<Action> actionsC = rC.getResponsePattern().getActions();

				for (Action aA : actionsA) {
					List<String> actionPolicyElements = getActionPolicyElements(aA);

					for (Action aC : actionsC) {
						List<String> conditionPolicyElements = getConditionPolicyElements(aC);

						for (String actionPolicyElement : actionPolicyElements) {

							if (actionPolicyElement.equals("current_target")
									|| actionPolicyElement.equals("current_user")
									|| actionPolicyElement.equals("current_user")) {
								System.out.println("Possible conflict detected");
		
								continue;
							}
							
							for (String conditionPolicyElement : conditionPolicyElements) {
								if (conditionPolicyElement.equals("current_target")
										|| conditionPolicyElement.equals("current_user")) {
									System.out.println("Possible conflict detected");
									continue;
								}
								if (actionPolicyElement.equals(conditionPolicyElement)) {
									System.out.println("Possible conflict detected");
								}
							}
						}
					}

				}

			}

		}
	}

	private List<String> getActionPolicyElements(Action action) {
		List<String> policyElements = new ArrayList<String>();
		if (action instanceof GrantAction) {
			GrantAction association = (GrantAction) action;
			String what = association.getSubject().getName().toString();
			String where = association.getTarget().getName().toString();
			// String op = association.getOperations().get(0);
			policyElements.add(what);
			policyElements.add(where);
		} else if (action instanceof CreateAction) {
			CreateAction createAction = (CreateAction) action;
			String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
			policyElements.add(what);
		} else if (action instanceof AssignAction) {
			AssignAction assignAction = (AssignAction) action;
			String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
			String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
			// String op = association.getOperations().get(0);
			policyElements.add(what);
			policyElements.add(where);
		}
		return policyElements;
	}

	private List<String> getConditionPolicyElements(Action action) {
		List<String> policyElements = new ArrayList<String>();
		if (null == action.getCondition()) {
			return policyElements;
		}
		List<Function> conditions = action.getCondition().getCondition();
		List<Arg> args = conditions.get(0).getArgs();
		String ancestor = "";
		String descendant = "";
		if (args.get(0).getFunction().getName().equals("current_target")) {
			policyElements.add("current_target");
		} else if (args.get(0).getFunction().getName().equals("current_user")) {
			policyElements.add("current_user");
		} else {
			ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
			policyElements.add(ancestor);
		}
		if (args.get(1).getFunction().getName().equals("current_target")) {
			policyElements.add("current_target");
		} else if (args.get(1).getFunction().getName().equals("current_user")) {
			policyElements.add("current_user");
		} else {
			descendant = args.get(1).getFunction().getArgs().get(0).getValue();
			policyElements.add(descendant);
		}
		return policyElements;
	}

	 private void groupObligationsByAccessEvent(List<Rule> rules, Map<String, List<String>> groupedObligations) {
	        for (Rule r : rules) {
	            List<String> accessEvents = r.getEventPattern().getOperations();
	            for (String accessEvent : accessEvents) {
	                List<String> currentLabels = new ArrayList<>();
	                if (groupedObligations.containsKey(accessEvent)) {
	                    currentLabels = groupedObligations.get(accessEvent);
	                    groupedObligations.replace(accessEvent, currentLabels);
	                }
	                currentLabels.add(r.getLabel());
	                groupedObligations.put(accessEvent, currentLabels);
	            }
	        }
	    }
	
	
	private void analyzeDependencies() {
		List<Rule> allObligations = obligation.getRules();
		List<Rule> obligationsWithConditions = getObligationsWithConditions(obligation.getRules());
		Map<String, List<String>> groupedObligations = new HashMap<String, List<String>>();
        groupObligationsByAccessEvent(allObligations, groupedObligations);
        for (Map.Entry<String, List<String>> mapElement : groupedObligations.entrySet()) {
            List<String> labels = mapElement.getValue();
            if (labels.size() > 1) {
                //processPairs(labels, obligation);
        		findConditionConflicts(allObligations, obligationsWithConditions);
            }
        }
	}

	public ActionsConditionsConflictAnalyzer(Graph graph, Obligation obligation) {
		this.graph = graph;
		this.obligation = obligation;
	}

	
	
	public static void main(String[] args) throws Exception {
//         Graph graph =
//         Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers2.json");
//         String yml = new String(
//         Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));
		Graph graph = Utils.readAnyGraph("Policies/SolverVerification/GPMS/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/SolverVerification/GPMS/Obligations.yml")));
//        Graph graph = Utils.readAnyGraph("Policies/ForBMC/LeoBug2/Graph.json");
//        String yml = new String(
//                Files.readAllBytes(Paths.get("Policies/ForBMC/LeoBug2/Obligations.yml")));
		Obligation obligation = EVRParser.parse(yml);
		EPPOptions eppOptions = new EPPOptions();

		// PDP pdp = new PDP(new PAP(graph, null, new MemObligations()), eppOptions);
		// // if (graph.exists("super_pc_rep")) {
		// // graph.deleteNode("super_pc_rep");
		// // }
		// PReviewDecider decider = new PReviewDecider(graph);
		// pdp.getPAP().getObligationsPAP().add(obligation, true);
		// System.out.println(decider.list("PI", "", "PDSWhole"));
		// System.out.println(decider.list("Chair", "", "PDSWhole"));
		// pdp.getEPP().processEvent(new EventContext("submit",
		// graph.getNode("PDSWhole")), "Vlad", "");
		// System.out.println(decider.list("Chair", "", "PDSWhole"));

		// System.out.println(decider.list("BM", "", "PDSWhole"));

		// pdp.getEPP().processEvent(new EventContext("approve",
		// graph.getNode("PDSWhole")), "UChair", "");
		// System.out.println(decider.list("BM", "", "PDSWhole"));

		ActionsConditionsConflictAnalyzer da = new ActionsConditionsConflictAnalyzer(graph, obligation);
		da.analyzeDependencies();
	}

}
