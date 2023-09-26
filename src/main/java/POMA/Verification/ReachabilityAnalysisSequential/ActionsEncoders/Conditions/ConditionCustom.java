package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Conditions;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.HierarchyType;

public class ConditionCustom {
	private HierarchyType conditionHierarchyType;
	private String conditionElements = "";
	private ConditionType conditionType;
	private String condition;

	public enum ConditionType {
		INCLUSIVE, EXCLUSIVE, OTHER
	}

	public ConditionCustom(String condition, String conditionElements, HierarchyType conditionHierarchyType,
			ConditionType conditionType) {
		this.condition = condition;
		this.conditionHierarchyType = conditionHierarchyType;
		this.conditionElements = conditionElements;
		this.conditionType = conditionType;
	}

	public HierarchyType getConditionHierarchyType() {
		return conditionHierarchyType;
	}

	protected void setConditionHierarchyType(HierarchyType preconditionHierarchyType) {
		this.conditionHierarchyType = preconditionHierarchyType;
	}

	public String getConditionElements() {
		return conditionElements;
	}

	public ConditionType getConditionType() {
		return conditionType;
	}

	public String getCondition() {
		return condition;
	}
}
