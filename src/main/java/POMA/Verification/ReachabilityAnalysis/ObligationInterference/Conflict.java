package POMA.Verification.ReachabilityAnalysis.ObligationInterference;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;



public class Conflict {
	String conflictingActionALabel;
	String conflictingActionBLabel;
	Action conflictingActionA;
	Action conflictingActionB;
	String accessevent;
	StaticAnalyzer.CONFLICT_TYPE conflictType;
	
	public Conflict(String conflictingActionObligation, String conflictingConditionObligation, Action conflictingAction,
			Action conflictCondition, String accessEvent, StaticAnalyzer.CONFLICT_TYPE conflictType) {
		this.conflictingActionALabel = conflictingActionObligation;
		this.conflictingActionBLabel = conflictingConditionObligation;
		this.conflictingActionA = conflictingAction;
		this.conflictingActionB = conflictCondition;
		this.accessevent = accessEvent;
		this.conflictType = conflictType;
	}

	public String getConflictingActionObligation() {
		return conflictingActionALabel;
	}

	public String getConflictingConditionObligation() {
		return conflictingActionBLabel;
	}

	public Action getConflictingAction() {
		return conflictingActionA;
	}

	public Action getConflictCondition() {
		return conflictingActionB;
	}

	public String getEvent() {
		return accessevent;
	}

	public StaticAnalyzer.CONFLICT_TYPE getConflictType() {
		return conflictType;
	}

	@Override
	public String toString() {
		return "Conflict [conflictingActionALabel=" + conflictingActionALabel + ", conflictingActionBLabel="
				+ conflictingActionBLabel + ", conflictingActionA=" + conflictingActionA + ", conflictingActionB="
				+ conflictingActionB + ", accessevent=" + accessevent + ", conflictType=" + conflictType + "]";
	}
}
