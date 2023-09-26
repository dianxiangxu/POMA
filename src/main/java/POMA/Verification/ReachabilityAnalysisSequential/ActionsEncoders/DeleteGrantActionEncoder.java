package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.ActionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.RelationType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.AssociationCustom;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;


public class DeleteGrantActionEncoder extends ActionEncoder {

	private DeleteAction deleteGrantAction;
	private AssociationCustom association;
	
	public DeleteGrantActionEncoder(DeleteAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSOCIATE, ActionType.REMOVE);
		deleteGrantAction = action;
		retrieveActionPolicyElements();
		encodeActionPreconditions();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(deleteGrantAction);	
		encodeNegatedCondition();
		encodeNegatedPrecondition();
	}

	protected void encodeActionPreconditions() {
		String precondition = "(and ";
		for(Integer op: association.getOps()) {
			precondition +="(member (mkTuple " + association.getSubject() + " " + op + " " + association.getTarget()+") (ASSOC {k-1}))"; //have to check every access right
		}
		precondition += ")";
		setPrecondition(precondition); //exists						
	
	}
    
	protected void encodeActionPostcondition() {
		String innerAction = "";
		for (Integer op : association.getOps()) {
			innerAction = handleAssociationAction(op, innerAction);
		}	
		setPostcondition(innerAction);
		setNegatedPostconditionSet("(as set.empty (Set (Tuple Int Int Int)))");
		//setPostcondition("(union  (singleton (mkTuple " + association.getSubject() + " " + association.getTarget() + ")) (ASSIGN {k-1}))");
	}	
	
	private String handleAssociationAction(Integer ar, String innerAction) {
		if (innerAction.isEmpty()) {
			return "(setminus " + "(ASSOC " + "{k-1}" + ")" + "(singleton(mkTuple " + association.getSubject() + " " + ar + " "
					+ association.getTarget() + ")))";
		} else {
			return "(setminus " + innerAction + "(singleton(mkTuple " + association.getSubject() + " " + association.getTarget() + ")))";
		}
	}	
	
	protected void retrieveActionPolicyElements() {
		EvrNode whatNode = deleteGrantAction.getAssociations().get(0).getSubject();
		EvrNode whereNode = deleteGrantAction.getAssociations().get(0).getTarget();
		List<String> ops = deleteGrantAction.getAssociations().get(0).getOperations();
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
		//no need for flattening with association
	}	
}
