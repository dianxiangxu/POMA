package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations;

import POMA.Verification.ReachabilityAnalysisSequential.Encoders.ActionEncoder.ActionType;
import POMA.Verification.ReachabilityAnalysisSequential.Encoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisSequential.Encoders.ActionEncoder.RelationType;

public class Prerequisite {

	private RelationType relationType;
	private HierarchyType hierarchyType;
	private ActionType prerequisiteType;

	private String actionsSet = "";
	private String actionSetFlatten = "";
	private int actionIndex = 0;
	

	
	public Prerequisite(RelationType relationType, HierarchyType hierarchyType, ActionType prerequisiteType,
			String actionsSet, String actionSetFlatten, int actionIndex) {
		this.relationType = relationType;
		this.hierarchyType = hierarchyType;
		this.prerequisiteType = prerequisiteType;
		this.actionsSet = actionsSet;
		this.actionSetFlatten = actionSetFlatten;
		this.actionIndex = actionIndex;
	}

	public ActionType getPrerequisiteType() {
		return prerequisiteType;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public HierarchyType getHierarchyType() {
		return hierarchyType;
	}

	public String getActionSet() {
		return actionsSet;
	}

	public String getActionSetFlatten() {
		return actionSetFlatten;
	}

	public int getActionIndex() {
		return actionIndex;
	}
}
