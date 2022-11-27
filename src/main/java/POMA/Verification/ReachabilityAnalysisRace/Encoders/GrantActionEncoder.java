package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.AssociationCustom;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class GrantActionEncoder extends ActionEncoder {

	private GrantAction grantAction;
	private AssociationCustom association;

	public GrantActionEncoder(GrantAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSOCIATE);
		grantAction = action;
		retrieveActionPolicyElements();
		encodeActionPrecondition();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(grantAction);
		encodeNegatedCondition();
		encodeNegatedPrecondition();
	}

	protected void encodeActionPrecondition() {
		List<Integer> ops = association.getOps();
		if(ops.size()==1) {
			setPrecondition("(not (member (mkTuple " + association.getSubject() + " " + ops.get(0) + " "
					+ association.getTarget() + ") (ASSOC {k-1})))");
			return;
		}
		String precondition = "(and ";
		for (Integer op : association.getOps()) {
			precondition += "(not (member (mkTuple " + association.getSubject() + " " + op + " "
					+ association.getTarget() + ") (ASSOC {k-1})))"; // have to check every access right
		}
		precondition += ")";
		setPrecondition(precondition); // duplicate

	}

	protected void encodeActionPostcondition() {
		String innerAction = "";
		for (Integer op : association.getOps()) {
			innerAction = handleAssociationAction(op, innerAction);
		}
		setPostcondition(innerAction);
	}

	private String handleAssociationAction(Integer ar, String innerAction) {
		if (innerAction.isEmpty()) {
			return "(union " + "(ASSOC " + "{k-1}" + ")" + "(singleton(mkTuple " + association.getSubject() + " " + ar
					+ " " + association.getTarget() + ")))";
		} else {
			return "(union " + innerAction + "(singleton(mkTuple " + association.getSubject() + " " + ar + " "
					+ association.getTarget() + ")))";
		}
	}

	protected void retrieveActionPolicyElements() {
		EvrNode whatNode = grantAction.getSubject();
		EvrNode whereNode = grantAction.getTarget();
		List<String> ops = grantAction.getOperations();
		String what = whatNode.getFunction() != null ? whatNode.getFunction().getName() : whatNode.getName();
		String where = whereNode.getFunction() != null ? whereNode.getFunction().getName() : whereNode.getName();
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
		this.association = new AssociationCustom(whatID, whereID, mappedOps);
	}

	protected void encodeActionPostconditionFlatten() {
		// no need for flattening with association
	}
}
