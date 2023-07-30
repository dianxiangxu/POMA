package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.util.HashMap;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Conditions.ConditionCustom;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Conditions.ConditionCustom.ConditionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations.AssignmentCustom;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;


public class DeleteAssignActionEncoder extends ActionEncoder {

	private DeleteAction deleteAction;
	private AssignmentCustom assignment;
	
	public DeleteAssignActionEncoder(DeleteAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSIGN, ActionType.REMOVE);
		deleteAction = action;
		retrieveActionPolicyElements();
		if(assignment.getWhatType().equals("UA") || assignment.getWhatType().equals("U")) {
			setPreconditionHierarchyType(HierarchyType.UA);
			setPostconditionHierarchyType(HierarchyType.UA);
		}
		if(assignment.getWhatType().equals("OA") || assignment.getWhatType().equals("O")) {
			setPreconditionHierarchyType(HierarchyType.OA);
			setPostconditionHierarchyType(HierarchyType.OA);
		}
		encodeActionPreconditions();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(deleteAction);	
		encodeNegatedCondition();
		encodeNegatedPrecondition();		
	}

	protected void encodeActionPreconditions() {
		conditions.add(new ConditionCustom(
				"(set.member (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ") (ASSIGN {k-1}))",
				"(set.singleton(tuple " + assignment.getWhat() + " " + assignment.getWhere() + "))",
				this.getConditionHierarchyType(), ConditionType.INCLUSIVE));
		setPrecondition("(set.member (tuple " + assignment.getWhat() + " "+assignment.getWhere()+") (ASSIGN {k-1}))"); //does it exist?				
	}
    
	protected void encodeActionPostcondition() {
		setPostcondition("(set.setminus (ASSIGN {k-1}) (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))");
		setNegatedPostconditionSet("(as set.empty (Set (Tuple Int Int)))");
	}	
	
	protected void encodeActionPostconditionFlatten() {
		if(assignment.getWhatType().equals("UA") || assignment.getWhereType().equals("OA") || assignment.getWhereType().isBlank()){
			setPostconditionFlatten(getATATEncoding());
		}
		else if(assignment.getWhatType().equals("U") || assignment.getWhatType().equals("O")) {
			setPostconditionFlatten(getPEATEncoding());
		}
	}		
	
	private String getATATEncoding() {
		return "(set.setminus (ASSIGN* " + "{k-1}" + ") (set.setminus (set.setminus (set.union (set.singleton (tuple " + assignment.getWhat() + " "
				+ assignment.getWhere() + ")) (rel.join (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ")) (ASSIGN* " + "{k-1}"
				+ "))) (rel.join (rel.join (intersection (rel.join (set.union  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")) (rel.join (ASSIGN* " + "{k-1}" + ")  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")))) (set.transpose (set.union  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat() + ")) (rel.join (ASSIGN* "
				+ "{k-1}" + ")  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))))) NODES) (set.setminus (ASSIGN "
				+ "{k-1}" + ") (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))) (ASSIGN* " + "{k-1}"
				+ "))) (rel.join (rel.join (intersection (rel.join (set.union  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")) (rel.join (ASSIGN* " + "{k-1}" + ")  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")))) (set.transpose (set.union  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat() + ")) (rel.join (ASSIGN* "
				+ "{k-1}" + ")  (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))))) NODES) (set.setminus (ASSIGN "
				+ "{k-1}" + ") (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))) (ASSIGN* " + "{k-1}" + "))))";
	}
	
	private String getPEATEncoding() {
		return "(set.setminus (ASSIGN* " + "{k-1}" + ") (set.setminus (rel.join (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
		+ ")) (ASSIGN* " + "{k-1}" + ")) (rel.join (rel.join (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat()
		+ ")) (set.setminus (set.setminus (ASSIGN " + "{k-1}" + ") (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
		+ "))) (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))) (ASSIGN* " + "{k-1}" + "))))";
	}
	protected void retrieveActionPolicyElements() {
		Assignment assignment = deleteAction.getAssignments().getAssignments().get(0);

		EvrNode whatNode = assignment.getWhat();
		EvrNode whereNode = assignment.getWhere();

		String what = whatNode.getFunction() != null ? whatNode.getFunction().getName() : whatNode.getName();
		String where = whereNode.getFunction() != null ? whereNode.getFunction().getName() : whereNode.getName();
		String whatID = "";
		String whereID = "";
		String whatType = "";
		String whereType = "";
		
		if (what.equals("current_user")) {
			whatID = "{user}";
		} else if (what.equals("current_target")) {
			whatID = "{target}";
		} else {
			whatID = mapOfIDs.get(what).toString();
			whatType = whatNode.getType();
		}

		if (where.equals("current_user")) {
			whereID = "{user}";
		} else if (where.equals("current_target")) {
			whereID = "{target}";
		} else {
			whereID = mapOfIDs.get(where).toString();
			whereType = whereNode.getType();
		}
				
		this.assignment = new AssignmentCustom(whatID, whereID, whatType, whereType);
	}
}
