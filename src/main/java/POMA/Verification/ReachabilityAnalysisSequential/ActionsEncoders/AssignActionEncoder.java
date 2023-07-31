package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders;

import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Conditions.ConditionCustom;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Conditions.ConditionCustom.ConditionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.AssignmentCustom;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.Prerequisite;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;

public class AssignActionEncoder extends ActionEncoder {

	private AssignAction assignAction;
	private AssignmentCustom assignment;

	public AssignActionEncoder(AssignAction action, HashMap<String, Integer> mapOfIDs) {
		super(mapOfIDs, RelationType.ASSIGN, ActionType.ADD);
		assignAction = action;
		retrieveActionPolicyElements();
		if (assignment.getWhatType().equals("UA") || assignment.getWhatType().equals("U")) {
			setPreconditionHierarchyType(HierarchyType.UA);
			setPostconditionHierarchyType(HierarchyType.UA);
		}
		if (assignment.getWhatType().equals("OA") || assignment.getWhatType().equals("O")) {
			setPreconditionHierarchyType(HierarchyType.UA);
			setPostconditionHierarchyType(HierarchyType.OA);
		}
		encodeActionPreconditions();
		encodeActionPostcondition();
		encodeActionPostconditionFlatten();
		encodeCondition(assignAction);
		encodeNegatedCondition();
		encodeNegatedPrecondition();
	}

	protected void encodeActionPreconditions() {
		conditions.add(new ConditionCustom(
				"(not (set.member (tuple " + assignment.getWhat() + " " + assignment.getWhere() + ") (ASSIGN {k-1})))",
				"(set.singleton(tuple " + assignment.getWhat() + " " + assignment.getWhere() + "))",
				this.getConditionHierarchyType(), ConditionType.EXCLUSIVE));
		conditions.add(new ConditionCustom(
				"(not (set.member (tuple " + assignment.getWhere() + " " + assignment.getWhat() + ") (ASSIGN {k-1})))",
				"(set.singleton(tuple " + assignment.getWhere() + " " + assignment.getWhat() + "))",
				this.getConditionHierarchyType(), ConditionType.EXCLUSIVE));

		setPrecondition("(and" + "(not (set.member (tuple " + assignment.getWhat() + " " + assignment.getWhere()
				+ ") (ASSIGN {k-1})))" // duplicate
				+ "(not (= " + assignment.getWhat() + " " + assignment.getWhere() + "))" // x=y: cycle
				+ "(not (set.member (tuple " + assignment.getWhere() + " " + assignment.getWhat() + ") (ASSIGN* {k-1})))" // ensure
																														// that
																														// no
																														// assignment
																														// results
																														// in
																														// creating
																														// a
																														// cycle
				+ ")");
		setPreconditionSet("(set.singleton(tuple " + assignment.getWhat() + " " + assignment.getWhere() + "))");
	}

	protected void encodeActionPostcondition() {
		setPostcondition("(set.union (ASSIGN {k-1}) (set.singleton (tuple " + assignment.getWhat() + " "
				+ assignment.getWhere() + ")))");
		setPostconditionSet("(set.singleton( tuple " + assignment.getWhat() + " " + assignment.getWhere() + "))");
		setNegatedPostconditionSet("(as set.empty (Set (Tuple Int Int)))");
	}

	protected void encodeActionPostconditionFlatten() {
		if (assignment.getWhatType().equals("UA")) {
			setPostconditionFlatten(getATATEncoding());
		} else if (assignment.getWhatType().equals("OA")) {
			setPostconditionFlatten(getATATEncoding());
		} else if (assignment.getWhatType().equals("U")) {
			setPostconditionFlatten(getPEATEncoding());
		} else if (assignment.getWhatType().equals("O")) {
			setPostconditionFlatten(getPEATEncoding());
		}
	}

	private String getATATEncoding() {
		setPostconditionFlattenSet("(rel.join (rel.join (set.union (set.singleton (tuple " + assignment.getWhat() + " "
				+ assignment.getWhat() + ")) (rel.join (ASSIGN* " + "{k-1}" + ") (set.singleton (tuple "
				+ assignment.getWhat() + " " + assignment.getWhat() + ")))) (set.singleton (tuple " + assignment.getWhat()
				+ " " + assignment.getWhere() + "))) (set.union (set.singleton (tuple " + assignment.getWhere() + " "
				+ assignment.getWhere() + ")) (rel.join (set.singleton (tuple " + assignment.getWhere() + " "
				+ assignment.getWhere() + ")) (ASSIGN* " + "{k-1}" + "))))");

		return "(set.union (ASSIGN* " + "{k-1}" + ") (rel.join (rel.join (set.union (set.singleton (tuple " + assignment.getWhat() + " "
				+ assignment.getWhat() + ")) (rel.join (ASSIGN* " + "{k-1}" + ") (set.singleton (tuple "
				+ assignment.getWhat() + " " + assignment.getWhat() + ")))) (set.singleton (tuple " + assignment.getWhat()
				+ " " + assignment.getWhere() + "))) (set.union (set.singleton (tuple " + assignment.getWhere() + " "
				+ assignment.getWhere() + ")) (rel.join (set.singleton (tuple " + assignment.getWhere() + " "
				+ assignment.getWhere() + ")) (ASSIGN* " + "{k-1}" + ")))))";
	}

	private String getPEATEncoding() {
		setPostconditionFlattenSet("(set.union (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
				+ ")) (rel.join (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
				+ ")) (rel.join (set.singleton (tuple " + assignment.getWhere() + " " + assignment.getWhere() + ")) (ASSIGN* "
				+ "{k-1}" + "))))");
		return "(set.union (ASSIGN* {k-1}) (set.union (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
				+ ")) (rel.join (set.singleton (tuple " + assignment.getWhat() + " " + assignment.getWhere()
				+ ")) (rel.join (set.singleton (tuple " + assignment.getWhere() + " " + assignment.getWhere() + ")) (ASSIGN* "
				+ "{k-1}" + ")))))";
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
