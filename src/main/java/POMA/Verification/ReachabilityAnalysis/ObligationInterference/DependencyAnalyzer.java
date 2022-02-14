package POMA.Verification.ReachabilityAnalysis.ObligationInterference;

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

public class DependencyAnalyzer {
    public enum CONFLICT_TYPE {
        DirtyAssignment, DirtyAssociation, DirtyCycle, DirtyProhibition, DirtyReadCondition, DirtyReadAssociation,
        DirtyReadAssignment
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
        return null;
    }

    private static boolean affectsResponse(Rule obligationA, Rule obligationB) {
        ResponsePattern rpA = obligationA.getResponsePattern();
        ResponsePattern rpB = obligationB.getResponsePattern();

        for (Action action : rpA.getActions()) {
            if (action instanceof GrantAction) {
                isConflictingGrantWrite((GrantAction) action, rpB);
            }
        }
        return false;
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
        return null;
    }

    private static void findConflicts(Obligation obligation) {
        for (Rule targetObligation : obligation.getRules())
            for (Rule sourceObligation : obligation.getRules()) {
                if (sourceObligation.getLabel().equals(targetObligation.getLabel())) {
                    continue;
                }
                CONFLICT_TYPE ct = affectsPrecondition(targetObligation, sourceObligation);
                try {
                    if (!ct.equals(null)) {
                        System.out.println("Precondition conflict found: " + ct + " in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                    if (affectsResponse(targetObligation, sourceObligation)) {
                        System.out.println("Actions conflict found: in obligations: "
                                + sourceObligation.getLabel() + " and " + targetObligation.getLabel());
                    }
                } catch (Exception e) {
                    System.out.println(e);

                }
            }
    }

    public static void main(String[] args) throws Exception {

        String yml = new String(
                Files.readAllBytes(Paths.get("Policies/ForBMC/LawFirmSimplified/Obligations_simple.yml")));

        Obligation obligation = EVRParser.parse(yml);
        findConflicts(obligation);
    }
}
