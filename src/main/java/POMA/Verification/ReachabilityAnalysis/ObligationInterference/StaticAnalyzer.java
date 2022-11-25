package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.HashMap;

import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
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
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.pip.graph.Graph;

public class StaticAnalyzer {

	Obligation obligation;
	Graph graph;
	List<Conflict> CONFLICTS = new ArrayList<Conflict>();

	public enum CONFLICT_TYPE {
		AssignAssignAction, AssignCreatePEAction, AssignDeleteAssignAction, AssignGrantAction, AssignDeletePEAction,
		GrantDeleteGrantAction, GrantAssignAction, GrantDeleteAssignAction, GrantDeletePEAction, GrantCreatePEAction,
		AssignDeleteGrantAction, CreatePEAssignAction, CreatePECreatePEAction, CreatePEDeleteGrantAction,
		CreatePEDeleteAssignAction, CreatePEDeletePEAction, DeletePEDeletePEAction, DeletePEDeleteAssignAction,
		DeletePEDeleteGrantAction, DeletePECreatePEAction, DeletePEAddAssignAction, ConditionConflict, NoConflict
//        EventGrantConflict,
//        EventCreateConflict,
//        EventAssignConflict,
	}

//    private CONFLICT_TYPE affectsEvent(Rule obligationA, Rule obligationB) {
//        EventPattern epB = obligationB.getEventPattern();
//        ResponsePattern rpA = obligationA.getResponsePattern();
//        String eventSubject = epB.getSubject().getAnyUser().get(0);
//        String eventOperation = epB.getOperations().get(0);
//        String eventTarget = epB.getTarget().getPolicyElements().get(0).getName();
//        for (Action action : rpA.getActions()) {
//            if (action instanceof GrantAction) {
//                GrantAction association = (GrantAction) action;
//                String what = association.getSubject().getName().toString();
//                String where = association.getTarget().getName().toString();
//                String op = association.getOperations().get(0);
//                if (what.equals(eventSubject) || where.equals(eventTarget) ||
//                        eventOperation.equals(op)) {
//                	CONFLICTS.add(new Conflict(obligationA.getLabel(), 
//                			obligationB.getLabel(), 
//							aA, aC, "event", "conflictType"));
//                    return CONFLICT_TYPE.EventGrantConflict;
//                }
//            } else if (action instanceof CreateAction) {
//                CreateAction createAction = (CreateAction) action;
//                String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
//                if (what.equals(eventSubject) || what.equals(eventTarget)) {
//                    return CONFLICT_TYPE.EffectCreateConflict;
//                }
//            } else if (action instanceof AssignAction) {
//                AssignAction assignAction = (AssignAction) action;
//                String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
//                String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
//                if (what.equals(eventSubject) || where.equals(eventTarget) ||
//                        what.equals(eventTarget)
//                        || where.equals(eventSubject)) {
//                    return CONFLICT_TYPE.EffectAssignConflict;
//                }
//            }
//        }
//        return CONFLICT_TYPE.NoConflict;
//    }

	private void affectsResponse(Rule obligationA, Rule obligationB, String accessEvent) {
		ResponsePattern rpA = obligationA.getResponsePattern();
		ResponsePattern rpB = obligationB.getResponsePattern();
		CONFLICT_TYPE ct = CONFLICT_TYPE.NoConflict;
		for (Action actionA : rpA.getActions()) {
			if (actionA instanceof GrantAction) {
				isConflictingGrantAction(obligationA, obligationB, (GrantAction) actionA, rpB, accessEvent);
			} else if (actionA instanceof AssignAction) {
				isConflictingAssignAction(obligationA, obligationB, (AssignAction) actionA, rpB, accessEvent);
			} else if (actionA instanceof CreateAction) {
				if (((CreateAction) actionA).getCreateNodesList() != null) {
					if (((CreateAction) actionA).getCreateNodesList().size() > 0) {
						isConflictingCreatePEAction(obligationA, obligationB, (CreateAction) actionA, rpB, accessEvent);
					}
				}
			} else if (actionA instanceof DeleteAction) {
				if (((DeleteAction) actionA).getNodes() != null) {
					if (((DeleteAction) actionA).getNodes().size() > 0) {
						isConflictingDeletePEAction(obligationA, obligationB, (DeleteAction) actionA, rpB, accessEvent);
					}
				}
			}
		}
	}

	private void isConflictingGrantAction(Rule obligationA, Rule obligationB, GrantAction actionA, ResponsePattern rpB,
			String accessEvent) {
		String subjectA = actionA.getSubject().getName();
		String targetA = actionA.getSubject().getName();
		String operationA = actionA.getOperations().get(0);

		for (Action actionB : rpB.getActions()) {
			if (actionB instanceof GrantAction) {
				GrantAction grantB = (GrantAction) actionB;
				String subjectB = grantB.getSubject().getName();
				String targetB = grantB.getTarget().getName();
				String operationB = grantB.getOperations().get(0);
				if ((subjectA.equals(subjectB) || targetA.equals(targetB) || subjectA.equals(targetB)
						|| targetA.equals(subjectB)) && operationA.equals(operationB)) {
					CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
							accessEvent, CONFLICT_TYPE.AssignGrantAction));
				}
			} else if (actionB instanceof AssignAction) {
				AssignAction assignB = (AssignAction) actionB;
				Assignment assignment = assignB.getAssignments().get(0);
				String whatB = assignment.getWhat().getName().toString();
				String whereB = assignment.getWhere().getName().toString();
				if (subjectA.equals(whatB) || targetA.equals(whereB) || subjectA.equals(whereB)
						|| targetA.equals(whatB)) {
					CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
							accessEvent, CONFLICT_TYPE.GrantAssignAction));
				}
			} else if (actionB instanceof DeleteAction) {
				DeleteAction deleteB = (DeleteAction) actionB;
				if (deleteB.getAssociations() != null) {
					GrantAction grant = deleteB.getAssociations().get(0);
					String subjectB = grant.getSubject().getName().toString();
					String targetB = grant.getTarget().getName().toString();
					String operationB = grant.getOperations().get(0);
					if ((subjectA.equals(subjectB) || targetA.equals(targetB) || subjectA.equals(targetB)
							|| targetA.equals(subjectB)) && operationA.equals(operationB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
								accessEvent, CONFLICT_TYPE.GrantDeleteGrantAction));
					}
				} else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
						&& deleteB.getAssignments().getAssignments().get(0) != null) {
					Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
					String whatB = assignment.getWhat().getName().toString();
					String whereB = assignment.getWhere().getName().toString();
					if (subjectA.equals(whatB) || targetA.equals(whereB) || subjectA.equals(whereB)
							|| targetA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
								accessEvent, CONFLICT_TYPE.GrantDeleteAssignAction));
					}
				} else if (deleteB.getNodes() != null) {
					String whatB = deleteB.getNodes().get(0).getName().toString();
					if (subjectA.equals(whatB) || targetA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
								accessEvent, CONFLICT_TYPE.GrantDeletePEAction));
					}
				}
			} else if (actionB instanceof CreateAction) {
				CreateAction createB = (CreateAction) actionB;
				if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
					String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
					String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
					if (subjectA.equals(whatB) || targetA.equals(whereB) || subjectA.equals(whereB)
							|| targetA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), actionA, actionB,
								accessEvent, CONFLICT_TYPE.GrantCreatePEAction));
					}
				}
			}
		}
	}

	private void isConflictingAssignAction(Rule obligationA, Rule obligationB, AssignAction assignA,
			ResponsePattern rpB, String accessEvent) {
		String whatA = assignA.getAssignments().get(0).getWhat().getName().toString();
		String whereA = assignA.getAssignments().get(0).getWhere().getName().toString();

		for (Action action : rpB.getActions()) {
			if (action instanceof AssignAction) {
				AssignAction assignB = (AssignAction) action;
				Assignment assignment = assignB.getAssignments().get(0);
				String whatB = assignment.getWhat().getName().toString();
				String whereB = assignment.getWhere().getName().toString();
				if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
					CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), assignA, action,
							accessEvent, CONFLICT_TYPE.AssignAssignAction));
				}
			} else if (action instanceof CreateAction) {
				CreateAction createB = (CreateAction) action;
				if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
					String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
					String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), assignA, action,
								accessEvent, CONFLICT_TYPE.AssignCreatePEAction));
					}
				}
			} else if (action instanceof DeleteAction) {
				DeleteAction deleteB = (DeleteAction) action;
				if (deleteB.getAssociations() != null) {
					GrantAction grant = deleteB.getAssociations().get(0);
					String subjectB = grant.getSubject().getName().toString();
					String targetB = grant.getTarget().getName().toString();
					if ((whatA.equals(subjectB) || whereA.equals(targetB) || whatA.equals(targetB)
							|| whereA.equals(subjectB))) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), assignA, action,
								accessEvent, CONFLICT_TYPE.AssignDeleteGrantAction));
					}
				} else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
						&& deleteB.getAssignments().getAssignments().get(0) != null) {
					Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
					String whatB = assignment.getWhat().getName().toString();
					String whereB = assignment.getWhere().getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), assignA, action,
								accessEvent, CONFLICT_TYPE.AssignDeleteAssignAction));
					}
				} else if (deleteB.getNodes() != null) {
					String whatB = deleteB.getNodes().get(0).getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), assignA, action,
								accessEvent, CONFLICT_TYPE.AssignDeletePEAction));
					}
				}
			}
		}
	}

	private void isConflictingCreatePEAction(Rule obligationA, Rule obligationB, CreateAction createPEA,
			ResponsePattern rpB, String accessEvent) {
		String whatA = createPEA.getCreateNodesList().get(0).getWhat().getName();
		String whereA = createPEA.getCreateNodesList().get(0).getWhere().getName();

		for (Action action : rpB.getActions()) {
			if (action instanceof AssignAction) {
				AssignAction assignB = (AssignAction) action;
				Assignment assignment = assignB.getAssignments().get(0);
				String whatB = assignment.getWhat().getName().toString();
				String whereB = assignment.getWhere().getName().toString();
				if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
					CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), createPEA, action,
							accessEvent, CONFLICT_TYPE.CreatePEAssignAction));
				}
			} else if (action instanceof CreateAction) {
				CreateAction createB = (CreateAction) action;
				if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
					String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
					String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), createPEA, action,
								accessEvent, CONFLICT_TYPE.CreatePECreatePEAction));
					}
				}
			} else if (action instanceof DeleteAction) {
				DeleteAction deleteB = (DeleteAction) action;
				if (deleteB.getAssociations() != null) {
					GrantAction grant = deleteB.getAssociations().get(0);
					String subjectB = grant.getSubject().getName().toString();
					String targetB = grant.getTarget().getName().toString();
					if ((whatA.equals(subjectB) || whereA.equals(targetB) || whatA.equals(targetB)
							|| whereA.equals(subjectB))) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), createPEA, action,
								accessEvent, CONFLICT_TYPE.CreatePEDeleteGrantAction));
					}
				} else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
						&& deleteB.getAssignments().getAssignments().get(0) != null) {
					Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
					String whatB = assignment.getWhat().getName().toString();
					String whereB = assignment.getWhere().getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), createPEA, action,
								accessEvent, CONFLICT_TYPE.CreatePEDeleteAssignAction));
					}
				} else if (deleteB.getNodes() != null) {
					String whatB = deleteB.getNodes().get(0).getName().toString();
					if (whatA.equals(whatB) || whereA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), createPEA, action,
								accessEvent, CONFLICT_TYPE.CreatePEDeletePEAction));
					}
				}
			}
		}
	}

	private void isConflictingDeletePEAction(Rule obligationA, Rule obligationB, DeleteAction deletePEA,
			ResponsePattern rpB, String accessEvent) {

		String whatA = deletePEA.getNodes().get(0).getName();

		for (Action action : rpB.getActions()) {
			if (action instanceof AssignAction) {
				AssignAction assignB = (AssignAction) action;
				Assignment assignment = assignB.getAssignments().get(0);
				String whatB = assignment.getWhat().getName().toString();
				String whereB = assignment.getWhere().getName().toString();
				if (whatA.equals(whatB) || whatA.equals(whereB)) {
					CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), deletePEA, action,
							accessEvent, CONFLICT_TYPE.DeletePEAddAssignAction));
				}
			} else if (action instanceof CreateAction) {
				CreateAction createB = (CreateAction) action;
				if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
					String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
					String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
					if (whatA.equals(whatB) || whatA.equals(whereB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), deletePEA, action,
								accessEvent, CONFLICT_TYPE.DeletePECreatePEAction));
					}
				}
			} else if (action instanceof DeleteAction) {
				DeleteAction deleteB = (DeleteAction) action;
				if (deleteB.getAssociations() != null) {
					GrantAction grant = deleteB.getAssociations().get(0);
					String subjectB = grant.getSubject().getName().toString();
					String targetB = grant.getTarget().getName().toString();
					if ((whatA.equals(subjectB) || whatA.equals(targetB))) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), deletePEA, action,
								accessEvent, CONFLICT_TYPE.DeletePEDeleteGrantAction));
					}
				} else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
						&& deleteB.getAssignments().getAssignments().get(0) != null) {
					Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
					String whatB = assignment.getWhat().getName().toString();
					String whereB = assignment.getWhere().getName().toString();
					if (whatA.equals(whatB) || whatA.equals(whereB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), deletePEA, action,
								accessEvent, CONFLICT_TYPE.DeletePEDeleteAssignAction));
					}
				} else if (deleteB.getNodes() != null) {
					String whatB = deleteB.getNodes().get(0).getName().toString();
					if (whatA.equals(whatB)) {
						CONFLICTS.add(new Conflict(obligationA.getLabel(), obligationB.getLabel(), deletePEA, action,
								accessEvent, CONFLICT_TYPE.DeletePEDeletePEAction));
					}
				}
			}
		}
	}

	private void findConditionConflicts(Rule rA, Rule rC, String accessEvent) {

		List<Action> actionsA = rA.getResponsePattern().getActions();

		if (rA.getLabel().equals(rC.getLabel())) // don't need to check the conflicts of the same obligation
		{
			return;
		}

		List<Action> actionsC = rC.getResponsePattern().getActions();

//		if(rC.getLabel().equals("obligation3")) {
//			System.out.println("hello");
//		}
			
		for (Action aA : actionsA) {
			List<String> actionPolicyElements = getActionPolicyElements(aA);

			for (Action aC : actionsC) {
				List<String> conditionPolicyElements = getConditionPolicyElements(aC);

				for (String actionPolicyElement : actionPolicyElements) {

					if (actionPolicyElement.equals("current_target") || actionPolicyElement.equals("current_user")
							|| actionPolicyElement.equals("current_user")) {
						CONFLICTS.add(new Conflict(rA.getLabel(), rC.getLabel(), aA, aC, accessEvent,
								CONFLICT_TYPE.ConditionConflict));
						continue;
					}

					for (String conditionPolicyElement : conditionPolicyElements) {
						if (conditionPolicyElement.equals("current_target")
								|| conditionPolicyElement.equals("current_user")) {
							CONFLICTS.add(new Conflict(rA.getLabel(), rC.getLabel(), aA, aC, accessEvent,
									CONFLICT_TYPE.ConditionConflict));
							continue;
						}
						if (actionPolicyElement.equals(conditionPolicyElement)) {
							CONFLICTS.add(new Conflict(rA.getLabel(), rC.getLabel(), aA, aC, accessEvent,
									CONFLICT_TYPE.ConditionConflict));
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

	private void findConflicts(Obligation obligation, String obligationLabel1, String obligationLabel2,
			String accessEvent) {
		for (Rule targetObligation : obligation.getRules()) {
			for (Rule sourceObligation : obligation.getRules()) {

				if (sourceObligation.getLabel().equals(targetObligation.getLabel())) {
					continue;
				}
				if (!sourceObligation.getLabel().equals(obligationLabel1)
						&& !targetObligation.getLabel().equals(obligationLabel1)) {
					continue;
				}
				if (!sourceObligation.getLabel().equals(obligationLabel2)
						&& !targetObligation.getLabel().equals(obligationLabel2)) {
					continue;
				}
//                CONFLICT_TYPE ct = affectsEvent(targetObligation, sourceObligation);
				try {
//                    if (!ct.equals(null) && !ct.equals(CONFLICT_TYPE.NoConflict)) {
//                        conflicts.add("Precondition conflict found: " + ct + " in obligations: "
//                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
//                    }
					affectsResponse(targetObligation, sourceObligation, accessEvent);
//                        conflicts.add("Actions conflict found: " + ctResponse + " in obligations: "
//                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
					findConditionConflicts(targetObligation, sourceObligation, accessEvent);
				} catch (Exception e) {
					System.out.println(e);

				}

			}
		}
	}

	private Solution getSolution(String label1, String label2) throws Exception {
		ObligationChecker checker = new ObligationChecker(graph, obligation);
		System.out.println("Getting solution for: " + label1 + " : " + label2);
		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		checker.setBound(2);
		checker.enableSMTOutput(false);
		String precondition = "OBLIGATIONLABEL(" + label1 + ", ?user1, ?ar, ?o);";

		String postcondition = "OBLIGATIONLABEL(" + label2 + ", ?user2, ?ar, ?o);";

		return checker.solveConstraint(precondition, postcondition, graph);
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

	private void processPairs(List<String> labels, Obligation obligation, String accessEvent) throws Exception {
		for (String label1 : labels) {
			for (String label2 : labels) {
				if (!label1.equals(label2)) {
//					Solution solution = getSolution(label1, label2);
//					System.out.println(solution);
//					if (solution == null) {
//						continue;
//					}
//					List<ObligationFiring> obligationFirings = solution.getObligationFirings();
//
//					Integer obligationLabelAIndex = IntStream.range(0, obligationFirings.size())
//							.filter(i -> obligationFirings.get(i).getObligationLabel().equals(label1)).findFirst()
//							.orElse(-1);
//					Integer obligationLabelBIndex = IntStream.range(0, obligationFirings.size())
//							.filter(i -> obligationFirings.get(i).getObligationLabel().equals(label2)).findFirst()
//							.orElse(-1); // TODO: multiple obligations run with the same label
//					if (obligationLabelAIndex.equals(-1) || obligationLabelBIndex.equals(-1)) {
//						continue;
//					}
//					String obligationLabelA = solution.getObligationFirings().get(obligationLabelAIndex)
//							.getObligationLabel();
//					String obligationLabelB = solution.getObligationFirings().get(obligationLabelBIndex)
//							.getObligationLabel();
//					findConflicts(obligation, obligationLabelA, obligationLabelB, accessEvent);
					findConflicts(obligation, label1, label2, accessEvent);
				}
			}
		}
		System.out.println(CONFLICTS);
	}

	private void analyzeDependencies() throws Exception {
		long start = System.currentTimeMillis();

		List<Rule> rules = obligation.getRules();
		Map<String, List<String>> groupedObligations = new HashMap<String, List<String>>();
		groupObligationsByAccessEvent(rules, groupedObligations);
		for (Map.Entry<String, List<String>> mapElement : groupedObligations.entrySet()) {
			List<String> labels = mapElement.getValue();
			String accessEvent = mapElement.getKey();
			if (labels.size() > 1) {
				processPairs(labels, obligation, accessEvent);
			}
		}

		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
		System.out.println("The job took: " + sec + " seconds");
	}

	public StaticAnalyzer(Graph graph, Obligation obligation) {
		this.graph = graph;
		this.obligation = obligation;
	}

	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers2.json");
		String yml = new String(
				Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));
//         Graph graph =
//         Utils.readAnyGraph("Policies/SolverVerification/GPMS/Graph.json");
//         String yml = new String(
//         Files.readAllBytes(Paths.get("Policies/SolverVerification/GPMS/Obligations.yml")));
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

		StaticAnalyzer da = new StaticAnalyzer(graph, obligation);
		da.analyzeDependencies();
	}

}
