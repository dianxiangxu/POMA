package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;

import gov.nist.csd.pm.pip.obligations.MemObligations;
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
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.ObligationChecker;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.epp.events.EventContext;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;

public class DependencyAnalyzer {

    Obligation obligation;
    Graph graph;

    public enum CONFLICT_TYPE {
        AddAssignmentAddAssignmentAction,
        AddAssignmentCreatePEAction,
        AddAssignmentDeleteAssignmentAction,
        AddAssignmentAddGrantAction,
        AddAssignmentDeletePEAction,
        AddDeleteGrantAction,
        AddGrantAddAssignmentAction,
        AddGrantDeleteAssignmentAction,
        AddGrantDeletePEAction,
        AddGrantCreatePEAction, 
        AddAssignmentDeleteGrantAction, 
        CreatePEAddAssignmentAction,
        CreatePECreatePEAction, 
        CreatePEDeleteGrantAction, 
        CreatePEDeleteAssignmentAction, 
        CreatePEDeletePEAction, 
        DeletePEDeletePEAction, 
        DeletePEDeleteAssignmentAction, 
        DeletePEDeleteGrantAction, 
        DeletePECreatePEAction, 
        DeletePEAddAssignmentAction,
        DirtyAssociation,
        DirtyCycle,
        DirtyProhibition,
        DirtyReadCondition,
        GrantPrecondition,
        AssignmentPrecondition,
        NoConflict
    }

    private CONFLICT_TYPE affectsPrecondition(Rule obligationA, Rule obligationB) {
        EventPattern epB = obligationB.getEventPattern();
        ResponsePattern rpA = obligationA.getResponsePattern();
        String eventSubject = epB.getSubject().getAnyUser().get(0);
        String eventOperation = epB.getOperations().get(0);
        String eventTarget = epB.getTarget().getPolicyElements().get(0).getName();
        for (Action action : rpA.getActions()) {
            if (action instanceof GrantAction) {
                GrantAction association = (GrantAction) action;
                String what = association.getSubject().getName().toString();
                String where = association.getTarget().getName().toString();
                String op = association.getOperations().get(0);
                if (what.equals(eventSubject) || where.equals(eventTarget) ||
                        eventOperation.equals(op)) {
                    return CONFLICT_TYPE.GrantPrecondition;
                }
            } else if (action instanceof CreateAction) {
                CreateAction createAction = (CreateAction) action;
                String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
                if (what.equals(eventSubject) || what.equals(eventTarget)) {
                    return CONFLICT_TYPE.AssignmentPrecondition;
                }
            } else if (action instanceof AssignAction) {
                AssignAction assignAction = (AssignAction) action;
                String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
                String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
                if (what.equals(eventSubject) || where.equals(eventTarget) ||
                        what.equals(eventTarget)
                        || where.equals(eventSubject)) {
                    return CONFLICT_TYPE.AssignmentPrecondition;
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private CONFLICT_TYPE affectsResponse(Rule obligationA, Rule obligationB) {
        ResponsePattern rpA = obligationA.getResponsePattern();
        ResponsePattern rpB = obligationB.getResponsePattern();
        CONFLICT_TYPE ct = CONFLICT_TYPE.NoConflict;
        for (Action action : rpA.getActions()) {
            if (action instanceof GrantAction) {
                ct = isConflictingGrantAction((GrantAction) action, rpB);
            } else if (action instanceof AssignAction) {
                ct = isConflictingAssignAction((AssignAction) action, rpB);
            } else if (action instanceof CreateAction) {
                if (((CreateAction) action).getCreateNodesList() != null) {
                    if (((CreateAction) action).getCreateNodesList().size() > 0) {
                        ct = isConflictingCreatePEAction((CreateAction) action, rpB);
                    }
                }
            } else if (action instanceof DeleteAction) {
                if (((DeleteAction) action).getNodes() != null) {
                    if (((DeleteAction) action).getNodes().size() > 0) {
                        ct = isConflictingDeletePEAction((DeleteAction) action, rpB);
                    }
                }
            }
        }
        return ct;
    }

    private CONFLICT_TYPE isConflictingGrantAction(GrantAction grantA, ResponsePattern rpB) {
        String subjectA = grantA.getSubject().getName();
        String targetA = grantA.getSubject().getName();
        String operationA = grantA.getOperations().get(0);

        for (Action action : rpB.getActions()) {
            if (action instanceof GrantAction) {
                GrantAction grantB = (GrantAction) action;
                String subjectB = grantB.getSubject().getName();
                String targetB = grantB.getTarget().getName();
                String operationB = grantB
                        .getOperations().get(0);
                if ((subjectA.equals(
                        subjectB)
                        || targetA.equals(
                                targetB)
                        || subjectA.equals(
                                targetB)
                        || targetA.equals(
                                subjectB))
                        && operationA.equals(operationB)) {
                    return CONFLICT_TYPE.AddAssignmentAddGrantAction;
                }
            } else if (action instanceof AssignAction) {
                AssignAction assignB = (AssignAction) action;
                Assignment assignment = assignB.getAssignments().get(0);
                String whatB = assignment.getWhat().getName().toString();
                String whereB = assignment.getWhere().getName().toString();
                if (subjectA.equals(whatB) || targetA.equals(whereB) || subjectA.equals(whereB)
                        || targetA.equals(whatB)) {
                    return CONFLICT_TYPE.AddGrantAddAssignmentAction;
                }
            } else if (action instanceof DeleteAction) {
                DeleteAction deleteB = (DeleteAction) action;
                if (deleteB.getAssociations() != null) {
                    GrantAction grant = deleteB.getAssociations().get(0);
                    String subjectB = grant.getSubject().getName().toString();
                    String targetB = grant.getTarget().getName().toString();
                    String operationB = grant.getOperations().get(0);
                    if ((subjectA.equals(
                            subjectB)
                            || targetA.equals(
                                    targetB)
                            || subjectA.equals(
                                    targetB)
                            || targetA.equals(
                                    subjectB))
                            && operationA.equals(operationB)) {
                        return CONFLICT_TYPE.AddDeleteGrantAction;
                    }
                } else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
                        && deleteB
                                .getAssignments().getAssignments().get(0) != null) {
                    Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
                    String whatB = assignment.getWhat().getName().toString();
                    String whereB = assignment
                            .getWhere().getName().toString();
                    if (subjectA.equals(
                            whatB) || targetA.equals(whereB) || subjectA.equals(whereB) || targetA.equals(whatB)) {
                        return CONFLICT_TYPE.AddGrantDeleteAssignmentAction;
                    }
                } else if (deleteB.getNodes() != null) {
                    String whatB = deleteB.getNodes().get(0).getName().toString();
                    if (subjectA.equals(
                            whatB) || targetA.equals(whatB)) {
                        return CONFLICT_TYPE.AddGrantDeletePEAction;
                    }
                }
            } else if (action instanceof CreateAction) {
                CreateAction createB = (CreateAction) action;
                if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
                    String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
                    String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
                    if (subjectA.equals(
                            whatB) || targetA.equals(whereB) || subjectA.equals(whereB) || targetA.equals(whatB)) {
                        return CONFLICT_TYPE.AddGrantCreatePEAction;
                    }
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private CONFLICT_TYPE isConflictingAssignAction(AssignAction assignA, ResponsePattern rpB) {
        String whatA = assignA.getAssignments().get(0).getWhat().getName().toString();
        String whereA = assignA.getAssignments().get(0).getWhere().getName().toString();

        for (Action action : rpB.getActions()) {
            if (action instanceof AssignAction) {
                AssignAction assignB = (AssignAction) action;
                Assignment assignment = assignB.getAssignments().get(0);
                String whatB = assignment.getWhat().getName().toString();
                String whereB = assignment.getWhere().getName().toString();
                if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                    return CONFLICT_TYPE.AddAssignmentAddAssignmentAction;
                }
            } else if (action instanceof CreateAction) {
                CreateAction createB = (CreateAction) action;
                if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
                    String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
                    String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.AddAssignmentCreatePEAction;
                    }
                }
            } else if (action instanceof DeleteAction) {
                DeleteAction deleteB = (DeleteAction) action;
                if (deleteB.getAssociations() != null) {
                    GrantAction grant = deleteB.getAssociations().get(0);
                    String subjectB = grant.getSubject().getName().toString();
                    String targetB = grant.getTarget().getName().toString();
                    if ((whatA.equals(
                            subjectB)
                            || whereA.equals(
                                    targetB)
                            || whatA.equals(
                                    targetB)
                            || whereA.equals(
                                    subjectB))) {
                        return CONFLICT_TYPE.AddAssignmentDeleteGrantAction;
                    }
                } else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
                        && deleteB
                                .getAssignments().getAssignments().get(0) != null) {
                    Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
                    String whatB = assignment.getWhat().getName().toString();
                    String whereB = assignment
                            .getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.AddAssignmentDeleteAssignmentAction;
                    }
                } else if (deleteB.getNodes() != null) {
                    String whatB = deleteB.getNodes().get(0).getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.AddAssignmentDeletePEAction;
                    }
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private CONFLICT_TYPE isConflictingCreatePEAction(CreateAction createPEA, ResponsePattern rpB) {
        String whatA = createPEA.getCreateNodesList().get(0).getWhat().getName();
        String whereA = createPEA.getCreateNodesList().get(0).getWhere().getName();

        for (Action action : rpB.getActions()) {
            if (action instanceof AssignAction) {
                AssignAction assignB = (AssignAction) action;
                Assignment assignment = assignB.getAssignments().get(0);
                String whatB = assignment.getWhat().getName().toString();
                String whereB = assignment.getWhere().getName().toString();
                if (whatA.equals(whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                    return CONFLICT_TYPE.CreatePEAddAssignmentAction;
                }
            } else if (action instanceof CreateAction) {
                CreateAction createB = (CreateAction) action;
                if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
                    String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
                    String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.CreatePECreatePEAction;
                    }
                }
            } else if (action instanceof DeleteAction) {
                DeleteAction deleteB = (DeleteAction) action;
                if (deleteB.getAssociations() != null) {
                    GrantAction grant = deleteB.getAssociations().get(0);
                    String subjectB = grant.getSubject().getName().toString();
                    String targetB = grant.getTarget().getName().toString();
                    if ((whatA.equals(
                            subjectB)
                            || whereA.equals(
                                    targetB)
                            || whatA.equals(
                                    targetB)
                            || whereA.equals(
                                    subjectB))) {
                        return CONFLICT_TYPE.CreatePEDeleteGrantAction;
                    }
                } else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
                        && deleteB
                                .getAssignments().getAssignments().get(0) != null) {
                    Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
                    String whatB = assignment.getWhat().getName().toString();
                    String whereB = assignment
                            .getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whereB) || whatA.equals(whereB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.CreatePEDeleteAssignmentAction;
                    }
                } else if (deleteB.getNodes() != null) {
                    String whatB = deleteB.getNodes().get(0).getName().toString();
                    if (whatA.equals(
                            whatB) || whereA.equals(whatB)) {
                        return CONFLICT_TYPE.CreatePEDeletePEAction;
                    }
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private CONFLICT_TYPE isConflictingDeletePEAction(DeleteAction deletePEA, ResponsePattern rpB) {

        String whatA = deletePEA.getNodes().get(0).getName();

        for (Action action : rpB.getActions()) {
            if (action instanceof AssignAction) {
                AssignAction assignB = (AssignAction) action;
                Assignment assignment = assignB.getAssignments().get(0);
                String whatB = assignment.getWhat().getName().toString();
                String whereB = assignment.getWhere().getName().toString();
                if (whatA.equals(whatB) || whatA.equals(whereB)) {
                    return CONFLICT_TYPE.DeletePEAddAssignmentAction;
                }
            } else if (action instanceof CreateAction) {
                CreateAction createB = (CreateAction) action;
                if (createB.getCreateNodesList() != null && createB.getCreateNodesList().get(0) != null) {
                    String whatB = createB.getCreateNodesList().get(0).getWhat().getName().toString();
                    String whereB = createB.getCreateNodesList().get(0).getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whatA.equals(whereB)) {
                        return CONFLICT_TYPE.DeletePECreatePEAction;
                    }
                }
            } else if (action instanceof DeleteAction) {
                DeleteAction deleteB = (DeleteAction) action;
                if (deleteB.getAssociations() != null) {
                    GrantAction grant = deleteB.getAssociations().get(0);
                    String subjectB = grant.getSubject().getName().toString();
                    String targetB = grant.getTarget().getName().toString();
                    if ((whatA.equals(
                            subjectB)
                            || whatA.equals(
                                    targetB))) {
                        return CONFLICT_TYPE.DeletePEDeleteGrantAction;
                    }
                } else if (deleteB.getAssignments() != null && deleteB.getAssignments().getAssignments() != null
                        && deleteB
                                .getAssignments().getAssignments().get(0) != null) {
                    Assignment assignment = deleteB.getAssignments().getAssignments().get(0);
                    String whatB = assignment.getWhat().getName().toString();
                    String whereB = assignment
                            .getWhere().getName().toString();
                    if (whatA.equals(
                            whatB) || whatA.equals(whereB)) {
                        return CONFLICT_TYPE.DeletePEDeleteAssignmentAction;
                    }
                } else if (deleteB.getNodes() != null) {
                    String whatB = deleteB.getNodes().get(0).getName().toString();
                    if (whatA.equals(
                            whatB)) {
                        return CONFLICT_TYPE.DeletePEDeletePEAction;
                    }
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private void findConflicts(Obligation obligation, String obligationLabel1, String obligationLabel2,
            List<String> conflicts) {
        for (Rule targetObligation : obligation.getRules()) {
            for (Rule sourceObligation : obligation.getRules()) {

                if (sourceObligation.getLabel().equals(targetObligation.getLabel())) {
                    continue;
                }
                if (!sourceObligation.getLabel().equals(obligationLabel1) && !targetObligation.getLabel()
                        .equals(obligationLabel1)) {
                    continue;
                }
                if (!sourceObligation.getLabel().equals(
                        obligationLabel2)
                        && !targetObligation.getLabel()
                                .equals(obligationLabel2)) {
                    continue;
                }
                CONFLICT_TYPE ct = affectsPrecondition(targetObligation, sourceObligation);
                try {
                    if (!ct.equals(null) && !ct.equals(CONFLICT_TYPE.NoConflict)) {
                        conflicts.add("Precondition conflict found: " + ct + " in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                    CONFLICT_TYPE ctResponse = affectsResponse(targetObligation, sourceObligation);
                    if (!ctResponse.equals(null) && !ctResponse.equals(CONFLICT_TYPE.NoConflict)) {
                        conflicts.add("Actions conflict found: " + ctResponse + " in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                } catch (Exception e) {
                    System.out.println(e);

                }

            }
        }
    }

    private Solution getSolution(String label1, String label2) throws Exception {
        ObligationChecker checker = new ObligationChecker(graph, obligation);
        checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
        checker.setBound(2);
        checker.enableSMTOutput(false);
        String precondition = "OBLIGATIONLABEL(" + label1 + ", ?user1, ?ar, ?o);";

        String postcondition = "OBLIGATIONLABEL(" + label2 + ", ?user2, ?ar, ?o);";

        return checker.solveConstraint(precondition, postcondition);
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

    private void processPairs(List<String> labels, Obligation obligation) throws Exception {
        List<String> conflicts = new ArrayList<String>();
        for (String label1 : labels) {
            for (String label2 : labels) {
                if (!label1.equals(label2)) {
                    Solution solution = getSolution(label1, label2);
                    System.out.println(solution);
                    if (solution == null) {
                        continue;
                    }
                    String obligationLabelA = solution.getObligationFirings().get(0).getObligationLabel();
                    String obligationLabelB = solution.getObligationFirings().get(1).getObligationLabel();
                    findConflicts(obligation, obligationLabelA, obligationLabelB, conflicts);
                }
            }
        }
        System.out.println(conflicts);
    }

    private void analyzeDependencies() throws Exception {
        long start = System.currentTimeMillis();

        List<Rule> rules = obligation.getRules();
        Map<String, List<String>> groupedObligations = new HashMap<String, List<String>>();
        groupObligationsByAccessEvent(rules, groupedObligations);
        for (Map.Entry<String, List<String>> mapElement : groupedObligations.entrySet()) {
            List<String> labels = mapElement.getValue();
            if (labels.size() > 1) {
                processPairs(labels, obligation);
            }
        }

        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        System.out.println("The job took: " + sec + " seconds");
    }

    public DependencyAnalyzer(Graph graph, Obligation obligation) {
        this.graph = graph;
        this.obligation = obligation;
    }

    public static void main(String[] args) throws Exception {
        // Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers2.json");
        // String yml = new String(
        //         Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));
        Graph graph = Utils.readAnyGraph("Policies/ForBMC/LeoBug2/Graph.json");
        String yml = new String(
                Files.readAllBytes(Paths.get("Policies/ForBMC/LeoBug2/Obligations.yml")));        
        Obligation obligation = EVRParser.parse(yml);
        EPPOptions eppOptions = new EPPOptions();

        PDP pdp = new PDP(new PAP(graph, null, new MemObligations()), eppOptions);
//        if (graph.exists("super_pc_rep")) {
//            graph.deleteNode("super_pc_rep");
//        }
        PReviewDecider decider = new PReviewDecider(graph);
        pdp.getPAP().getObligationsPAP().add(obligation, true);
        System.out.println(decider.list("PI", "", "PDSWhole"));
        System.out.println(decider.list("Chair", "", "PDSWhole"));
        pdp.getEPP().processEvent(new EventContext("submit", graph.getNode("PDSWhole")), "Vlad", "");
         System.out.println(decider.list("Chair", "", "PDSWhole"));

         System.out.println(decider.list("BM", "", "PDSWhole"));

        pdp.getEPP().processEvent(new EventContext("approve", graph.getNode("PDSWhole")), "UChair", "");
        System.out.println(decider.list("BM", "", "PDSWhole"));

        //DependencyAnalyzer da = new DependencyAnalyzer(graph, obligation);
        //da.analyzeDependencies();
    }


}
