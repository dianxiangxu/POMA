package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.AssociationCustom;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.ActionType;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class GrantActionEncoder extends ActionEncoder {

	private GrantAction grantAction;
	private AssociationCustom association;

	public GrantActionEncoder(GrantAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSOCIATE, ActionType.ADD);
		grantAction = action;
		retrieveActionPolicyElements();
		if(association.getTargetType().equals("OA")) {
			setPreconditionHierarchyType(HierarchyType.OTHER);
			setPostconditionHierarchyType(HierarchyType.OTHER);
		}
		else {
			setPreconditionHierarchyType(HierarchyType.UA);
			setPostconditionHierarchyType(HierarchyType.UA);
		}
		encodeActionPreconditions();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(grantAction);
		encodeNegatedCondition();
		encodeNegatedPrecondition();
	}

	protected void encodeActionPreconditions() {
		List<Integer> ops = association.getOps();
		if(ops.size()==1) {
			setPrecondition("(not (set.member (tuple " + association.getSubject() + " " + ops.get(0) + " "
					+ association.getTarget() + ") (ASSOC {k-1})))");
			return;
		}
		String precondition = "(and ";
		for (Integer op : association.getOps()) {
			precondition += "(not (set.member (tuple " + association.getSubject() + " " + op + " "
					+ association.getTarget() + ") (ASSOC {k-1})))"; // have to check every access right
		}
		precondition += ")";
		setPrecondition(precondition); // duplicate

	}

	protected void encodeActionPostcondition() {
		String innerAction = "";
		String innerActionSet = "";
		for (Integer op : association.getOps()) {
			innerAction = handleAssociationAction(op, innerAction);
			innerActionSet = handleAssociationSet(op, innerActionSet);
		}
		setPostcondition(innerAction);
		setPostconditionSet(innerActionSet);
	}

	private String handleAssociationAction(Integer ar, String innerAction) {
		if (innerAction.isEmpty()) {
			return "(set.union " + "(ASSOC " + "{k-1}" + ")" + "(set.singleton(tuple " + association.getSubject() + " " + ar
					+ " " + association.getTarget() + ")))";
		} else {
			return "(set.union " + innerAction + "(set.singleton(tuple " + association.getSubject() + " " + ar + " "
					+ association.getTarget() + ")))";
		}
	}
	
	private String handleAssociationSet(Integer ar, String innerAction) {
		if (innerAction.isEmpty()) {
			return "(set.singleton(tuple " + association.getSubject() + " " + ar
					+ " " + association.getTarget() + "))";
		} else {
			return "(set.union " + innerAction + "(set.singleton(tuple " + association.getSubject() + " " + ar + " "
					+ association.getTarget() + ")))";
		}
	}

	protected void retrieveActionPolicyElements() {
		EvrNode whatNode = grantAction.getSubject();
		EvrNode whereNode = grantAction.getTarget();
		List<String> ops = grantAction.getOperations();
		String what = whatNode.getFunction() != null ? whatNode.getFunction().getName() : whatNode.getName();
		String where = whereNode.getFunction() != null ? whereNode.getFunction().getName() : whereNode.getName();
		String whereType = whereNode.getFunction() != null ? "" : whereNode.getType();
		String whatID = "";
		String whereID = "";

		if (what.equals("current_user")) {
			whatID = "{user}";
		} else if (what.equals("current_target")) {
			whatID = "{target}";
		} else {
			whatID = mapOfIDs.get(what).toString();
		}

		if (where.equals("current_user")) {
			whereID = "{user}";
		} else if (where.equals("current_target")) {
			whereID = "{target}";
		} else {
			whereID = mapOfIDs.get(where).toString();
		}
		List<Integer> mappedOps = new ArrayList<Integer>();
		for (String op : ops) {
			mappedOps.add(mapOfIDs.get(op));
		}
		this.association = new AssociationCustom(whatID, whereID, whereType, mappedOps);
	}

	protected void encodeActionPostconditionFlatten() {
		// no need for flattening with association
	}
}
