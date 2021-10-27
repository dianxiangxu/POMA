package POMA.Verification.ReachabilityAnalysis;

public class OldTranslationRepository {
    // Translate events + condition for the whole obligation
    // String translateObligationEvents(int k) {
    //     StringBuilder sb = new StringBuilder();
    //     for (Rule r : obligation.getRules()) {
    //         String subject = r.getEventPattern().getSubject().getAnyUser().get(0);
    //         String ar = r.getEventPattern().getOperations().get(0);
    //         String target = r.getEventPattern().getTarget().getPolicyElements().get(0).getName();
    //         String obligationLabel = r.getLabel();
    //         int subjectID = mapOfIDs.get(subject);
    //         int arID = mapOfIDs.get(ar);
    //         int targetID = mapOfIDs.get(target);
    //         String condition = processEventCondition(r, k);
    //         sb.append("(assert \r\n" + "(or \r\n" + "(= (" + obligationLabel + " " + (k - 1) + ") 0) \r\n"
    //                 + "(and (member (mkTuple " + subjectID + " " + arID + " " + targetID + ") (ASSOC* " + (k - 1)
    //                 + "))  " + condition + " (= (" + obligationLabel + " " + (k - 1) + ") 1))\r\n" + ")\r\n"
    //                 + ")				\r\n");
    //         ruleLabels.add(r.getLabel());
    //     }
    //     return sb.toString();
    // }
    String translateGraphIntersection(int k) {
        return "(declare-fun ASSIGN*" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
                + "(declare-fun OldASSIGN" + k + " () (Set (Tuple Int Int)))" + System.lineSeparator()
                + "(assert (= OldASSIGN" + k + " (intersection (Tclosure " + (k - 1) + ") ASSIGN*" + (k - 1) + ")))"
                + System.lineSeparator();
    }
    
    // String processActions(int k) {
    //     StringBuilder sb_assignments_flatten = new StringBuilder();
    //     StringBuilder sb_assignments_not_flattened = new StringBuilder();
    //     StringBuilder sb_associations = new StringBuilder();
    //     sb_assignments_flatten.append("(assert (or ");
    //     sb_assignments_flatten.append(System.lineSeparator());
    //     sb_assignments_not_flattened.append("(assert (or");
    //     sb_assignments_not_flattened.append(System.lineSeparator());
    //     sb_associations.append("(assert (or ");
    //     sb_associations.append(System.lineSeparator());
    //     for (Rule rule : obligation.getRules()) {
    //         this.groupActions(rule.getResponsePattern().getActions());
    //         String condition = processActionCondition(rule, k);
    //         if (grantGroupActions.size() > 0) {
    //             sb_associations.append("(and " + condition + " (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
    //             sb_associations.append(System.lineSeparator());
    //             sb_associations.append("(xor (= (ASSOC " + k + ") ");
    //             sb_associations.append(System.lineSeparator());
    //             sb_associations.append(this.processAssociationRelatedActions(k));
    //             sb_associations.append(System.lineSeparator());
    //         }
    //         if (assignGroupActions.size() > 0) {
    //             sb_assignments_flatten
    //                     .append("(and " + condition + "  (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
    //             sb_assignments_flatten.append(System.lineSeparator());
    //             sb_assignments_flatten.append("(xor (= (ASSIGN* " + k + ") ");
    //             sb_assignments_flatten.append(System.lineSeparator());
    //             sb_assignments_flatten.append(this.processAssignmentRelatedActions(k, true));
    //             sb_assignments_flatten.append(System.lineSeparator());

    //             sb_assignments_not_flattened
    //                     .append("(and " + condition + "  (= (" + rule.getLabel() + " " + (k - 1) + ") 1)");
    //             sb_assignments_not_flattened.append(System.lineSeparator());
    //             sb_assignments_not_flattened.append("(xor (= (ASSIGN " + k + ") ");
    //             sb_assignments_not_flattened.append(System.lineSeparator());
    //             sb_assignments_not_flattened.append(this.processAssignmentRelatedActions(k, false));
    //             sb_assignments_not_flattened.append(System.lineSeparator());
    //         }
    //     }
    //     sb_assignments_flatten.append("(= (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))))");
    //     sb_assignments_not_flattened.append("(= (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))))");
    //     sb_associations.append("(= (ASSOC " + k + ") (ASSOC " + (k - 1) + "))))");
    //     return sb_assignments_flatten.toString() + System.lineSeparator() + sb_assignments_not_flattened.toString()
    //             + System.lineSeparator() + sb_associations.toString();
    //     // return System.lineSeparator() + sb_associations.toString();

    // }
    // private void handleAssignmentTrailing(int k, StringBuilder sb_assignments) {
    //     sb_assignments.append(")");
    //     sb_assignments.append(System.lineSeparator());
    //     sb_assignments.append("(= (ASSIGN* " + k + ") (ASSIGN* " + (k - 1) + "))))");
    //     sb_assignments.append(System.lineSeparator());
    // }

    // private void handleAssignmentNoFlattenTrailing(int k, StringBuilder sb_assignments) {
    //     sb_assignments.append(")");
    //     sb_assignments.append(System.lineSeparator());
    //     sb_assignments.append("(= (ASSIGN " + k + ") (ASSIGN " + (k - 1) + "))))");
    //     sb_assignments.append(System.lineSeparator());
    // }
    // private void groupActions(List<Action> actions) {
    //     grantGroupActions = new ArrayList<Action>();
    //     assignGroupActions = new ArrayList<Action>();
    //     for (Action action : actions) {
    //         if (action instanceof GrantAction) {
    //             grantGroupActions.add((GrantAction) action);
    //         } else if (action instanceof AssignAction) {
    //             assignGroupActions.add((AssignAction) action);
    //         } else if (action instanceof DeleteAction) {
    //             DeleteAction deleteAction = (DeleteAction) action;
    //             if ((deleteAction.getAssignments() != null
    //                     && deleteAction.getAssignments().getAssignments().size() != 0)
    //                     || (deleteAction.getNodes() != null && deleteAction.getNodes().size() != 0)) {
    //                 assignGroupActions.add(deleteAction);
    //             } else if (deleteAction.getAssociations() != null && deleteAction.getAssociations().size() != 0) {
    //                 grantGroupActions.add(deleteAction);
    //             }
    //         }
    //     }
    // }

    // private void handleAddAssignmentActionMultipleUUA(int k, StringBuilder sb_assignments, String what, String where,
    //         String obligationLabel) {
    //     int whatID = mapOfIDs.get(what);
    //     int whereID = mapOfIDs.get(where);
    //     sb_assignments.append("(union (singleton (mkTuple " + whatID + " " + whereID
    //             + ")) (union (join (singleton (mkTuple " + whatID + " " + whereID + ")) (join (singleton (mkTuple "
    //             + whereID + " " + whereID + ")) (ASSIGN* " + (k - 1) + "))) (ASSIGN* " + (k - 1) + ")))");
    // }

    // private void handleAssociationTrailing(int k, StringBuilder sb_associations) {
    //     sb_associations.append(")");
    //     sb_associations.append(System.lineSeparator());
    //     sb_associations.append("(= (ASSOC " + k + ") (ASSOC " + (k - 1) + "))))");
    //     sb_associations.append(System.lineSeparator());
    // }
}
