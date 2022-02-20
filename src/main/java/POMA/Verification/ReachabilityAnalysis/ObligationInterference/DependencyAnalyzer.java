package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.ObligationChecker;
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;

public class DependencyAnalyzer {
    public enum CONFLICT_TYPE {
        DirtyAssignment, DirtyAssociation, DirtyCycle, DirtyProhibition, DirtyReadCondition, DirtyReadAssociation,
        DirtyReadAssignment, NoConflict
    }

    private static CONFLICT_TYPE affectsPrecondition(Rule obligationA, Rule obligationB) {
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
                    return CONFLICT_TYPE.DirtyReadAssociation;
                }
            } else if (action instanceof CreateAction) {
                CreateAction createAction = (CreateAction) action;
                String what = createAction.getCreateNodesList().get(0).getWhat().getName().toString();
                if (what.equals(eventSubject) || what.equals(eventTarget)) {
                    return CONFLICT_TYPE.DirtyReadAssignment;
                }
            } else if (action instanceof AssignAction) {
                AssignAction assignAction = (AssignAction) action;
                String what = assignAction.getAssignments().get(0).getWhat().getName().toString();
                String where = assignAction.getAssignments().get(0).getWhere().getName().toString();
                if (what.equals(eventSubject) || where.equals(eventTarget) ||
                        what.equals(eventTarget)
                        || where.equals(eventSubject)) {
                    return CONFLICT_TYPE.DirtyReadAssignment;
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private static CONFLICT_TYPE affectsResponse(Rule obligationA, Rule obligationB) {
        ResponsePattern rpA = obligationA.getResponsePattern();
        ResponsePattern rpB = obligationB.getResponsePattern();
        CONFLICT_TYPE ct = CONFLICT_TYPE.NoConflict;
        for (Action action : rpA.getActions()) {
            if (action instanceof GrantAction) {
                isConflictingGrantWrite((GrantAction) action, rpB);
                ct = CONFLICT_TYPE.DirtyReadAssociation;
            }
            if (action instanceof AssignAction) {
                isConflictingAssignWrite((AssignAction) action, rpB);
                ct = CONFLICT_TYPE.DirtyReadAssignment;
            }
        }
        return ct;
    }

    private static CONFLICT_TYPE isConflictingGrantWrite(GrantAction grantA, ResponsePattern rpB) {
        String subjectA = grantA.getSubject().getName();
        String targetA = grantA.getSubject().getName();
        String operationA = grantA.getOperations().get(0);

        for (Action action : rpB.getActions()) {
            if (action instanceof GrantAction) {
                GrantAction grantB = (GrantAction) action;
                if (subjectA.equals(grantB.getSubject().getName()) && targetA.equals(grantB.getTarget().getName())
                        && operationA.equals(grantB
                                .getOperations().get(0))) {
                    return CONFLICT_TYPE.DirtyAssociation;
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private static CONFLICT_TYPE isConflictingAssignWrite(AssignAction assignA, ResponsePattern rpB) {
        String whatA = assignA.getAssignments().get(0).getWhat().toString();// .getSubject().getName();
        String whereA = assignA.getAssignments().get(0).getWhere().toString();

        for (Action action : rpB.getActions()) {
            if (action instanceof AssignAction) {
                AssignAction assignB = (AssignAction) action;
                if (whatA.equals(assignB.getAssignments().get(0).getWhat().toString()) && whereA.equals(assignB
                        .getAssignments().get(0).getWhere().toString())) {
                    return CONFLICT_TYPE.DirtyAssignment;
                }
            }
        }
        return CONFLICT_TYPE.NoConflict;
    }

    private static void findConflicts(Obligation obligation, String obligationLabel1, String obligationLabel2) {
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
                        obligationLabel2) && !targetObligation.getLabel()
                        .equals(obligationLabel2)) {
                    continue;
                }
                CONFLICT_TYPE ct = affectsPrecondition(targetObligation, sourceObligation);
                try {
                    if (!ct.equals(null) && !ct.equals(CONFLICT_TYPE.NoConflict)) {
                        System.out.println("Precondition conflict found: " + ct + " in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                    CONFLICT_TYPE ctResponse = affectsResponse(targetObligation, sourceObligation);
                    if (!ctResponse.equals(null) && !ctResponse.equals(CONFLICT_TYPE.NoConflict)) {
                        System.out.println("Actions conflict found: in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                } catch (Exception e) {
                    System.out.println(e);

                }

            }
        }
    }

    private static Solution getSolution() throws Exception {
        Graph graph = Utils.readAnyGraph("Policies/ForBMC/LawFirmSimplified/CasePolicyUsers2.json");
        String yml = new String(
                Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));

        Obligation obligation = EVRParser.parse(yml);
        ObligationChecker checker = new ObligationChecker(graph, obligation);
        checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
        long start = System.currentTimeMillis();
        checker.setBound(3);
        checker.enableSMTOutput(true);
        String precondition = "OBLIGATIONLABEL(obligation1, ?user, ?ar, ?o);";

        String postcondition = "(OBLIGATIONLABEL(obligation3, ?user, ?ar, Case3Info) AND PERMIT(Attorneys2U,accept,Case3Info));";

        return checker.solveConstraint(precondition, postcondition);
    }

    public static void main(String[] args) throws Exception {

        String yml = new String(
                Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple2.yml")));

        // String yml = new String(
        // Files.readAllBytes(Paths.get("Policies/ForBMC/GPMSSimplified/Obligations_simple3.yml")));
        Obligation obligation = EVRParser.parse(yml);
        // findConflicts(obligation);
        Solution solution = getSolution();
        System.out.println(solution.toString());
        String obligationLabelA = "";
        String obligationLabelB = "";
        obligationLabelA = solution.getObligationFirings().get(0).getObligationLabel();
       // obligationLabelB = solution.getObligationFirings().get(1).getObligationLabel();

        findConflicts(obligation, obligationLabelA, obligationLabelB);
    }
}
