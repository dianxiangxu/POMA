package POMA.Verification.ReachabilityAnalysisGPMS;

import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import POMA.Verification.ReachabilityAnalysis.AssociationRelation;

import org.apache.commons.lang3.tuple.ImmutablePair;

import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;

public class GPMSPlugin extends Plugin {
    List<String> depts = new ArrayList<String>();

    GraphTranslator gt;
    HashMap<String, String> obligationPreconditions = new HashMap<String, String>();
    HashMap<String, List<String>> academicEntitiesByDepartment = new HashMap<String, List<String>>();
    List<String> listOfDepartments = new ArrayList<String>();
    HashMap<String, ImmutablePair<String, Boolean>> stateActionConditionVariables = new HashMap<String, ImmutablePair<String, Boolean>>();
    int indexOfCoPI = 0;

    String step = "{step}";

    public GPMSPlugin(GraphTranslator gt) {
        this.gt = gt;
    }

    void findAllDepts(Set<Node> allNodes) {
        for (Node node : allNodes) {
            String nodeName = node.getName();
            if (nodeName.contains("Dept")) {
                depts.add(nodeName.replace("Dept", ""));
            }
        }
    }

    void preserveConsistency() {
        Set<String> deptsToDelete = new HashSet<String>();
        for (String keyDept : academicEntitiesByDepartment.keySet()) {
            if (!listOfDepartments.contains(keyDept)) {
                deptsToDelete.add(keyDept);
            }
        }
        for (String dept : deptsToDelete) {
            academicEntitiesByDepartment.remove(dept);
        }
    }

    void removeDepartment(String target) throws Exception {
        String dept = "";

        Node actorNode = gt.graph.getNode(target);
        if (actorNode == null) {
            throw new Exception("The query actor does not exist");
        }
        if (actorNode.getType().toString().equals("U")) {
            Set<String> actorDescendants = gt.graph.getParents(target);
            for (String descendant : actorDescendants) {
                if (descendant.contains("Dept")) {
                    dept = descendant.replace("Dept", "");
                }
            }
        } else {
            for (String department : depts) {
                if (target.contains(department)) {
                    dept = department;
                    break;
                }
            }
        }
        listOfDepartments.remove(dept);
    }

    ImmutablePair<String, List<String>> findAcademicUnits(String actor) throws Exception {
        Set<Node> allNodes = gt.graph.getNodes();
        List<String> academicEntities = new ArrayList<String>();
        findAllDepts(allNodes);
        String dept = "";
        Node actorNode = gt.graph.getNode(actor);
        if (actorNode == null) {
            throw new Exception("The query actor does not exist");
        }
        if (actorNode.getType().toString().equals("U")) {
            Set<String> actorDescendants = gt.graph.getParents(actor);
            for (String descendant : actorDescendants) {
                if (descendant.contains("Dept")) {
                    dept = descendant.replace("Dept", "");
                }
            }
        } else {
            for (String department : depts) {
                if (actor.contains(department)) {
                    dept = department;
                    break;
                }
            }
        }
        listOfDepartments.add(dept);

        Node chairNode = gt.graph.getNode("Chair" + dept);
        if (chairNode == null) {
            throw new Exception("The chair does not exist");
        }
        academicEntities.add(chairNode.getName());
        Node BMNode = gt.graph.getNode("BM" + dept);
        if (BMNode == null) {
            throw new Exception("The BM does not exist");
        }
        academicEntities.add(BMNode.getName());
        String deanNode = gt.graph.getParents(BMNode.getName()).stream().filter(b -> b.contains("Dean")).findFirst()
                .get();
        academicEntities.add(deanNode);
        academicEntitiesByDepartment.put(dept, academicEntities);
        // System.out.println(academicUnits);
        return new ImmutablePair<String, List<String>>(dept, academicEntities);
    }

    private void processCreateProposal(String PI, String obligationLabel) {
        List<String> academicEntities;
        obligationLabelsUsed.add(obligationLabel);
        Rule r = obligation.getRules().stream().filter(b -> b.getLabel().equals(obligationLabel)).findFirst().get();
        obligationPreconditions.put(obligationLabel, processPrecondition(r.getEventPattern(), mapOfIDs));
        try {

            ImmutablePair<String, List<String>> pair = findAcademicUnits(PI);
            academicEntities = pair.right;
            String dept = pair.left;
            List<Action> obligationActions = new ArrayList<Action>();
            obligationActions.add(createAssignAction(PI, "UA", "PI", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(0), "UA", "Chair", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(1), "UA", "Business Manager", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(2), "UA", "Dean", "UA"));
            stateActionConditionVariables.put("CreateProposal" + indexOfCoPI,
                    new ImmutablePair<String, Boolean>("PI" + dept + "Required", true));
            obligationEffects.put(obligationLabel, obligationActions);
            int ancestorId = mapOfIDs.get(PI);
            int descendantId = mapOfIDs.get("PIEligible");
            eventCondition.put("create_proposal", "(mkTuple " + ancestorId + " " + descendantId + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processAddCoPI(String CoPI, String obligationLabel) {
        List<String> academicEntities;
        obligationLabelsUsed.add(obligationLabel);
        if (CoPI == null) {
            return;
        }
        Rule r = obligation.getRules().stream().filter(b -> b.getLabel().equals(obligationLabel)).findFirst().get();
        obligationPreconditions.put(obligationLabel, processPrecondition(r.getEventPattern(), mapOfIDs));
        try {
            ImmutablePair<String, List<String>> pair = findAcademicUnits(CoPI);
            academicEntities = pair.right;
            String dept = pair.left;
            List<Action> obligationActions = new ArrayList<Action>();
            obligationActions.add(createAssignAction(CoPI, "UA", "CoPI", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(0), "UA", "Chair", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(1), "UA", "Business Manager", "UA"));
            obligationActions.add(createAssignAction(academicEntities.get(2), "UA", "An", "UA"));
            stateActionConditionVariables.put(obligationLabel + indexOfCoPI,
                    new ImmutablePair<String, Boolean>("CoPI" + indexOfCoPI + dept + "Required", true));
            obligationEffects.put(obligationLabel + indexOfCoPI, obligationActions);
            int ancestorId = mapOfIDs.get(CoPI);
            int descendantId = mapOfIDs.get("CoPIEligible");

            eventCondition.put(obligationLabel + indexOfCoPI, "(mkTuple " + ancestorId + " " + descendantId + ")");
            addedObligationLables.add(obligationLabel + indexOfCoPI);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processDeleteCoPI(String CoPI, String obligationLabel) {
        List<String> academicEntities;
        obligationLabelsUsed.add(obligationLabel);
        if (CoPI == null) {
            return;
        }
        Rule r = obligation.getRules().stream().filter(b -> b.getLabel().equals(obligationLabel)).findFirst().get();
        obligationPreconditions.put(obligationLabel, processPrecondition(r.getEventPattern(), mapOfIDs));
        try {
            ImmutablePair<String, List<String>> pair = findAcademicUnits(CoPI);
            List<Action> obligationActions = new ArrayList<Action>();
            academicEntities = pair.right;
            String dept = pair.left;
            obligationActions.add(deleteAssignAction(CoPI, "UA", "CoPI", "UA"));
            obligationActions.add(deleteAssignAction(academicEntities.get(0), "UA", "Chair", "UA"));
            obligationActions.add(deleteAssignAction(academicEntities.get(1), "UA", "Business Manager", "UA"));
            obligationActions.add(deleteAssignAction(academicEntities.get(2), "UA", "Dean", "UA"));
            stateActionConditionVariables.put("AddCopi" + indexOfCoPI,
                    new ImmutablePair<String, Boolean>("CoPI" + indexOfCoPI + dept + "Required", false));

            obligationEffects.put(obligationLabel + indexOfCoPI, obligationActions);
            addedObligationLables.add(obligationLabel + indexOfCoPI);

            // processApproveStateVariables(indexOfCoPI);

            indexOfCoPI += 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processAddSP(String SP, String obligationLabel) {
        obligationLabelsUsed.add(obligationLabel);
        if (SP == null) {
            return;
        }
        Rule r = obligation.getRules().stream().filter(b -> b.getLabel().equals(obligationLabel)).findFirst().get();
        obligationPreconditions.put(obligationLabel, processPrecondition(r.getEventPattern(), mapOfIDs));
        try {
            List<Action> obligationActions = new ArrayList<Action>();
            obligationActions.add(createAssignAction(SP, "UA", "SP", "UA"));

            obligationEffects.put(obligationLabel, obligationActions);
            eventCondition.put(obligationLabel, "(mkTuple " + SP + " SPEligible)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processDeleteSP(String SP, String obligationLabel) {

        obligationLabelsUsed.add(obligationLabel);
        if (SP == null) {
            return;
        }
        Rule r = obligation.getRules().stream().filter(b -> b.getLabel().equals(obligationLabel)).findFirst().get();
        obligationPreconditions.put(obligationLabel, processPrecondition(r.getEventPattern(), mapOfIDs));
        try {
            List<Action> obligationActions = new ArrayList<Action>();
            obligationActions.add(deleteAssignAction(SP, "UA", "SP", "UA"));
            obligationEffects.put(obligationLabel, obligationActions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // private void processApproveStateVariables(int index) {
    // List<String> stateVariablesChair = new ArrayList<String>();
    // List<String> stateVariablesBM = new ArrayList<String>();
    // List<String> stateVariablesDean = new ArrayList<String>();

    // for (String dept : listOfDepartments) {
    // stateVariablesChair.add("("+dept + "ChairApproved"+ index +" "+ step+")");
    // stateVariablesBM.add("(" +dept + "BMApproved" + index + " " +step + ")");
    // stateVariablesDean.add("(" +dept + "DeanApproved" + index + " " +step + ")");
    // }
    // stateResponseConditionVariables.put("chair_approve"+ index,
    // stateVariablesChair);
    // stateResponseConditionVariables.put("bm_approve" + index,
    // stateVariablesDean);
    // stateResponseConditionVariables.put("dean_approve" + index,
    // stateVariablesDean);
    // }

    private void processApproveStateVariables() {
        List<String> stateVariablesChair = new ArrayList<String>();
        List<String> stateVariablesBM = new ArrayList<String>();
        List<String> stateVariablesDean = new ArrayList<String>();

        for (int i = 0; i <= indexOfCoPI; i++) {
            stateVariablesChair.add("(= (ChairApproved" + i + " " + step + ") 1)");
            stateVariablesBM.add("(= (BMApproved" + i + " " + step + ") 1)");
            stateVariablesDean.add("(= (DeanApproved" + i + " " + step + ") 1)");
            addedObligationLables.add("ChairApproved" + i);
            addedObligationLables.add("BMApproved" + i);
            addedObligationLables.add("DeanApproved" + i);

        }
        for (int i = 0; i <= indexOfCoPI; i++) {
            stateResponseConditionVariables.put("chair_approve" + i, stateVariablesChair);
            stateResponseConditionVariables.put("bm_approve" + i, stateVariablesBM);
            stateResponseConditionVariables.put("dean_approve" + i, stateVariablesDean);
            addedObligationLables.add("chair_approve" + i);
            addedObligationLables.add("bm_approve" + i);
            addedObligationLables.add("dean_approve" + i);

        }
        obligationLabelsUsed.add("chair_approve");
        obligationLabelsUsed.add("bm_approve");
        obligationLabelsUsed.add("dean_approve");

    }

    @Override
    public void processObligations() {
        processCreateProposal("nazmul", "create_proposal");
        processAddCoPI(null, "add_copi");
        processDeleteCoPI(null, "delete_copi");
       // processAddCoPI("vlad", "add_copi");
      //  processDeleteCoPI("vlad", "delete_copi");
    //    processAddCoPI("liliana", "add_copi");
     //   processDeleteCoPI("liliana", "delete_copi");
        processAddSP(null, "add_sp");
        processDeleteSP(null, "delete_sp");
        processApproveStateVariables();
    }

    // List<String> processGPMSQuery(String query) {
    // String[] splittedQuery = query.split(" ");
    // String actor = splittedQuery[0];
    // try {
    // return findAcademicUnits(actor);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    @Override
    public void exec() {
        // processGPMSQuery("NickC approve PDSWhole");
        processObligations();
        // return new ProcessedTuple(obligationPreconditions, obligationEffects);
    }
}
