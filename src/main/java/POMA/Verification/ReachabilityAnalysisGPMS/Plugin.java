package POMA.Verification.ReachabilityAnalysisGPMS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;

import java.util.AbstractMap.SimpleEntry;

abstract class Plugin {

    abstract void exec();

    abstract void processObligations();

    HashMap<String, String> eventCondition = new HashMap<String, String>();
    Obligation obligation;
    List<String> obligationLabelsUsed = new ArrayList<String>();
    List<String> addedObligationLables = new ArrayList<String>();
    HashMap<String, List<Action>> obligationEffects = new HashMap<String, List<Action>>();
    HashMap<String, List<String>> stateResponseConditionVariables = new HashMap<String, List<String>>();

    public HashMap<String, List<String>> getStateResponseConditionVariables() {
        return stateResponseConditionVariables;
    }

    public HashMap<String, List<Action>> getObligationEffects() {
        return obligationEffects;
    }

    public List<String> getAddedObligationLables() {
        return addedObligationLables;
    }

    HashMap<String, Integer> mapOfIDs = new HashMap<String, Integer>();

    public void setObligations(Obligation obligation) {
        this.obligation = obligation;
    }

    public List<String> getProcessedObligation() {
        return obligationLabelsUsed;
    }

    public HashMap<String, String> getEventConditions() {
        return eventCondition;
    }
    void setMapOfIds(HashMap<String, Integer> mapOfIDs) {
        this.mapOfIDs = mapOfIDs;
    }

    String processPrecondition(EventPattern eventPattern,
            HashMap<String, Integer> mapOfIDs) {
        String subject = eventPattern.getSubject().getAnyUser().get(0);
        String op = eventPattern.getOperations().get(0);
        String target = eventPattern.getTarget().getPolicyElements().get(0).getName();

        int subjectID = mapOfIDs.get(subject);
        int arID = mapOfIDs.get(op);
        int targetID = mapOfIDs.get(target);

        return  "(mkTuple " + subjectID + " " + arID + " " + targetID + ")";
    }
    AssignAction createAssignAction(String  what, String  whatType,String  where,String  whereType){
        EvrNode  whatEVR = new EvrNode(what, whatType, null);
        EvrNode whereEVR = new EvrNode(where, whereType, null);
        AssignAction assignAction = new AssignAction();
        assignAction.addAssignment(new gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment(whatEVR, 
                whereEVR));
        return assignAction;
    }
    
    DeleteAction deleteAssignAction(String what, String whatType, String where, String whereType) {
        EvrNode whatEVR = new EvrNode(what, whatType, null);
        EvrNode whereEVR = new EvrNode(where, whereType, null);
         AssignAction assignAction = new AssignAction();
        assignAction.addAssignment(new gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment(whatEVR, 
                whereEVR));
        DeleteAction deleteAssignAction = new DeleteAction();
        deleteAssignAction.setAssignments(assignAction);
        return deleteAssignAction;
    }

    GrantAction createGrantAction(String what, String whatType, String operation, String where, String whereType) {
        EvrNode whatEVR = new EvrNode(what, whatType, null);
        EvrNode whereEVR = new EvrNode(where, whereType, null);
        GrantAction grantAction = new GrantAction();
        grantAction.setSubject(whatEVR);
        grantAction.setOperations(Arrays.asList(operation));
        grantAction.setTarget(whereEVR);
        return grantAction;
    }

    DeleteAction deleteGrantAction(String what, String whatType, String operation, String where, String whereType) {
        EvrNode whatEVR = new EvrNode(what, whatType, null);
        EvrNode whereEVR = new EvrNode(where, whereType, null);
        GrantAction grantAction = new GrantAction();
        grantAction.setSubject(whatEVR);
        grantAction.setOperations(Arrays.asList(operation));
        grantAction.setTarget(whereEVR);
        DeleteAction deleteAction = new DeleteAction();
        deleteAction.setAssociations(Arrays.asList(grantAction));
        return deleteAction;
    }


}
