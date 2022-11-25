package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.util.HashMap;

import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.AssignmentCustom;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;


public class AssignActionEncoder extends ActionEncoder {

	private AssignAction assignAction;
	private AssignmentCustom assignment;
	
	public AssignActionEncoder(AssignAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSIGN);
		assignAction = action;
		retrieveActionPolicyElements();
		encodeActionPrecondition();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(assignAction);		
	}

	protected void encodeActionPrecondition() {
		setPrecondition("(and"
						+ "(not (member (mkTuple " + assignment.getWhat() + " "+assignment.getWhere()+") (ASSIGN {k-1})))" //duplicate
						+ "(not (= " + assignment.getWhat() + " " + assignment.getWhere() + "))" //x=y: cycle						
						+ "(not (member (mkTuple " + assignment.getWhere() + " " + assignment.getWhat() + ") (ASSIGN* {k})))" //ensure that no assignment results in creating a cycle							
						+ ")");
	}
    
	protected void encodeActionPostcondition() {
		setPostcondition("(union (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")) (ASSIGN {k-1}))");
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
		return "(union (ASSIGN* " + "{k-1}" + ") (join (join (union (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")) (join (ASSIGN* "
				+ "{k-1}" + ") (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhat() + ")))) (singleton (mkTuple " + assignment.getWhat()
				+ " " + assignment.getWhere() + "))) (union (singleton (mkTuple " + assignment.getWhere() + " " + assignment.getWhere()
				+ ")) (join (singleton (mkTuple " + assignment.getWhere() + " " + assignment.getWhere() + ")) (ASSIGN* " + "{k-1}"
				+ ")))))";
	}
	
	private String getPEATEncoding() {
		return "(union (singleton (mkTuple " + assignment.getWhat() + " " + assignment.getWhere() + ")) (union (join (singleton (mkTuple "
				+ assignment.getWhat() + " " + assignment.getWhere() + ")) (join (singleton (mkTuple " + assignment.getWhere() + " " + assignment.getWhere()
				+ ")) (ASSIGN* " + "{k-1}" + "))) (ASSIGN* " + "{k-1}" + ")))";
	}
	protected void retrieveActionPolicyElements() {
		Assignment assignment = assignAction.getAssignments().get(0);

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
