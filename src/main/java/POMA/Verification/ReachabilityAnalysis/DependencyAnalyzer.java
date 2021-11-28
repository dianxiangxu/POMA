package POMA.Verification.ReachabilityAnalysis;

public class DependencyAnalyzer {
    // private boolean affects(ResponsePattern rp, EventPattern ep) {
    // String eventSubject = ep.getSubject().getAnyUser().get(0);
    // String eventOperation = ep.getOperations().get(0);
    // String eventTarget = ep.getTarget().getPolicyElements().get(0).getName();
    // for (Action action : rp.getActions()) {
    // if (action instanceof GrantAction) {
    // GrantAction association = (GrantAction) action;
    // String what = association.getSubject().getName().toString();
    // String where = association.getTarget().getName().toString();
    // String op = association.getOperations().get(0);

    // if (what.equals(eventSubject) || where.equals(eventTarget) ||
    // eventOperation.equals(op)) {
    // return true;
    // }
    // } else if (action instanceof CreateAction) {
    // CreateAction createAction = (CreateAction) action;
    // String what =
    // createAction.getCreateNodesList().get(0).getWhat().getName().toString();
    // if (what.equals(eventSubject) || what.equals(eventTarget)) {
    // return true;
    // }
    // } else if (action instanceof AssignAction) {
    // AssignAction assignAction = (AssignAction) action;
    // String what =
    // assignAction.getAssignments().get(0).getWhat().getName().toString();
    // String where =
    // assignAction.getAssignments().get(0).getWhere().getName().toString();
    // if (what.equals(eventSubject) || where.equals(eventTarget) ||
    // what.equals(eventTarget)
    // || where.equals(eventSubject)) {
    // return true;
    // }
    // }
    // }
    // return false;
    // }

    // private Set<Rule> getRelevantObligationsAlgorithm(Rule targetObligation, int
    // levelOfRelevancy) {
    // Set<Rule> relevantObligations = new HashSet<Rule>();
    // for (Rule r : obligation.getRules()) {
    // if (r.equals(targetObligation)) {
    // return null;
    // }
    // EventPattern ep = targetObligation.getEventPattern();
    // ResponsePattern rp = r.getResponsePattern();
    // for (int i = 0; i < levelOfRelevancy; i++) {
    // if (affects(rp, ep)) {
    // Set<Rule> sr = getRelevantObligationsAlgorithm(targetObligation, i + 1);
    // if (sr != null) {
    // relevantObligations.addAll(sr);
    // }
    // }
    // }
    // }
    // return relevantObligations;
    // }
}
