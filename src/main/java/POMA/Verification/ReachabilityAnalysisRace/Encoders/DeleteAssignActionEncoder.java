package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.util.HashMap;

import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.AssignmentCustom;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;


public class DeleteAssignActionEncoder extends ActionEncoder {

	private DeleteAction deleteAction;
	private AssignmentCustom assignment;
	
	public DeleteAssignActionEncoder(DeleteAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSIGN);
		deleteAction = action;
		retrieveActionPolicyElements();
		encodeActionPrecondition();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(deleteAction);		
	}

	protected void encodeActionPrecondition() {
		setPrecondition("(member (mkTuple " + assignment.getWhat() + " "+assignment.getWhere()+") (ASSIGN {k-1}))"); //does it exist?				
	}
    
	protected void encodeActionPostcondition() {
		setPostcondition("(setminus (ASSIGN {k-1}) (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))");
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
		return "(setminus (ASSIGN* " + "{k-1}" + ") (setminus (setminus (union (singleton (mkTuple " + assignment.getWhat() + " "
				+ assignment.getWhere() + ")) (join (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")) (ASSIGN* " + "{k-1}"
				+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")) (join (ASSIGN* " + "{k-1}" + ")  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")))) (transpose (union  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")) (join (ASSIGN* "
				+ "{k-1}" + ")  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))))) NODES) (setminus (ASSIGN "
				+ "{k-1}" + ") (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))) (ASSIGN* " + "{k-1}"
				+ "))) (join (join (intersection (join (union  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")) (join (ASSIGN* " + "{k-1}" + ")  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat()
				+ ")))) (transpose (union  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")) (join (ASSIGN* "
				+ "{k-1}" + ")  (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))))) NODES) (setminus (ASSIGN "
				+ "{k-1}" + ") (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")))) (ASSIGN* " + "{k-1}" + "))))";
	}
	
	private String getPEATEncoding() {
		return "(setminus (ASSIGN* " + "{k-1}" + ") (setminus (join (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere()
		+ ")) (ASSIGN* " + "{k-1}" + ")) (join (join (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat()
		+ ")) (setminus (setminus (ASSIGN " + "{k-1}" + ") (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere()
		+ "))) (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))) (ASSIGN* " + "{k-1}" + "))))";
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
