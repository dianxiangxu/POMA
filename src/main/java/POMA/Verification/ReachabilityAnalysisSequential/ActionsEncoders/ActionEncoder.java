package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.ActionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Conditions.ConditionCustom;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Conditions.ConditionCustom.ConditionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.Prerequisite;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;

public abstract class ActionEncoder {

	protected List<String> customVariables = new ArrayList<String>();
	public String customCondition;

	public void setCustomCondition(String customCondition) {
		this.customCondition = customCondition;
	}

	private HashMap<String, List<String>> conditionalInterference = new HashMap<String, List<String>>();

	public HashMap<String, List<String>> getConditionalInterference() {
		return conditionalInterference;
	}
    public void addToConditionalInterference(HashMap<String, List<String>> map, String key, String value) {
        if (!map.containsKey(key)) {
            List<String> newList = new ArrayList<>();
            newList.add(value);
            map.put(key, newList);
        } else {
            map.get(key).add(value);
        }
    }
	private String actionId = "";

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionId() {
		return actionId;
	}
	protected String condition = "";
	protected String negatedCondition = "";
	protected HierarchyType conditionHierarchyType;

	public String precondition = "";
	public String negatedPrecondition = "";
	protected HierarchyType preconditionHierarchyType;

	protected String postcondition = "";
	protected String postconditionFlatten = "";

	protected String preconditionSet = "";
	public String postconditionSet = "";
	public String negatedPostconditionSet = "";

	public String postconditionFlattenSet = "";

	protected HierarchyType postconditionHierarchyType;

	protected RelationType relationType;
	protected ActionType actionType;

	public String operationSet = "";
	public String operationSetFlat = "";

	public List<Prerequisite> prerequisites = new ArrayList<Prerequisite>();

	protected List<ConditionCustom> conditions = new ArrayList<ConditionCustom>();

	protected HashMap<String, Integer> mapOfIDs;

	public enum RelationType {
		ASSIGN, ASSOCIATE, CUSTOM
	}

	public enum HierarchyType {
		UA, OA, OTHER
	}

	public enum ActionType {
		ADD, REMOVE, CUSTOM
	}

	public ActionEncoder(HashMap<String, Integer> mapOfIDs, RelationType relationType, ActionType actionType) {
		this.mapOfIDs = mapOfIDs;
		this.relationType = relationType;
		this.actionType = actionType;
	}

	public String getCondition() {
		return condition;
	}

	public String encodeCustomVariables() {
		return "";
	}

	protected void setCondition(String condition) {
		this.condition = condition;
	}

	public HierarchyType getConditionHierarchyType() {
		return conditionHierarchyType;
	}

	protected void setConditionHierarchyType(HierarchyType conditionHierarchyType) {
		this.conditionHierarchyType = conditionHierarchyType;
	}

	public void processPrerequisites() {
		if (prerequisites.size() != 0) {
			setPrecondition(processPrerequisites(prerequisites));
		}
		encodeNegatedPrecondition();
	}

	public String getPrecondition() {
		return precondition;
	}

	private String processPrerequisites(List<Prerequisite> prerequisites) {
		StringBuilder sb = new StringBuilder();

		if (prerequisites.size() > 1 || conditions.size() > 1) {
			sb.append("\t(and");
			sb.append(System.lineSeparator());
		}
		for (ConditionCustom condition : conditions) {
			ConditionType conditionType = condition.getConditionType();
			for (Prerequisite prerequisite : prerequisites) {
				ActionType prerequisiteType = prerequisite.getPrerequisiteType();
				if ((prerequisiteType.equals(ActionType.ADD) && conditionType.equals(ConditionType.EXCLUSIVE))
						|| (prerequisiteType.equals(ActionType.REMOVE)
								&& conditionType.equals(ConditionType.INCLUSIVE))) {
					sb.append("\t\t" + "(and " + System.lineSeparator() + "\t\t\t" + condition.getCondition() + " ");
					sb.append(System.lineSeparator());
					if (!prerequisite.getActionSetFlatten().isEmpty()) {
						sb.append("\t\t\t" + "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSetFlatten() + " )" + "\t\t" + ")");
						addToConditionalInterference(conditionalInterference, actionId, "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSetFlatten() + " )" + "\t\t" + ")");
					} else {
						sb.append("\t\t\t" + "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSet() + " )" + "\t\t" + ")");
						addToConditionalInterference(conditionalInterference, actionId, "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSet() + " )" + "\t\t" + ")");
					}
				}
				if ((prerequisiteType.equals(ActionType.REMOVE) && conditionType.equals(ConditionType.EXCLUSIVE))
						|| (prerequisiteType.equals(ActionType.ADD) && conditionType.equals(ConditionType.INCLUSIVE))) {
					sb.append("(or " + condition.getCondition() + " ");
					sb.append(System.lineSeparator());
					if (!prerequisite.getActionSetFlatten().isEmpty()) {
						sb.append("(set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSetFlatten() + "\t\t" + ")");
						addToConditionalInterference(conditionalInterference, actionId, "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSetFlatten() + " )" + "\t\t" + ")");
					} else {
						sb.append("(set.subset " + condition.getConditionElements() + " " + prerequisite.getActionSet()
								+ "\t\t" + ")");
						addToConditionalInterference(conditionalInterference, actionId, "(not (set.subset " + condition.getConditionElements() + " "
								+ prerequisite.getActionSet() + " )" + "\t\t" + ")");
					}

				}
				sb.append(System.lineSeparator() + "\t\t" + ")");
				sb.append(System.lineSeparator());
			}
		}
		if (prerequisites.size() > 1 || conditions.size() > 1) {
			sb.append("\t)");
		}
		return sb.toString();
	}

	protected void encodeNegatedPrecondition() {
		if (precondition.isBlank()) {
			return;
		}
		setNegatedPrecondition("(not " + System.lineSeparator() + precondition + "\t)");
	}

	protected void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public HierarchyType getPreconditionHierarchyType() {
		return preconditionHierarchyType;
	}

	protected void setPreconditionHierarchyType(HierarchyType preconditionHierarchyType) {
		this.preconditionHierarchyType = preconditionHierarchyType;
	}

	public String getPostcondition() {
		return postcondition;
	}

	protected void setPostcondition(String postcondition) {
		this.postcondition = postcondition;
	}

	public String getPostconditionFlatten() {
		return postconditionFlatten;
	}

	protected void setPostconditionFlatten(String postconditionFlatten) {
		this.postconditionFlatten = postconditionFlatten;
	}

	public String getPreconditionSet() {
		return preconditionSet;
	}

	protected void setPreconditionSet(String preconditionSet) {
		this.preconditionSet = preconditionSet;
	}

	public String getPostconditionSet() {
		return postconditionSet;
	}

	protected void setPostconditionSet(String postconditionSet) {
		this.postconditionSet = postconditionSet;
	}

	public String getPostconditionFlattenSet() {
		return postconditionFlattenSet;
	}

	protected void setPostconditionFlattenSet(String postconditionFlattenSet) {
		this.postconditionFlattenSet = postconditionFlattenSet;
	}

	public HierarchyType getPostconditionHierarchyType() {
		return postconditionHierarchyType;
	}

	protected void setPostconditionHierarchyType(HierarchyType postconditionHierarchyType) {
		this.postconditionHierarchyType = postconditionHierarchyType;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public List<Prerequisite> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(List<Prerequisite> inheritedPrerequisites) {
		this.prerequisites = inheritedPrerequisites;
	}

	protected void encodeCondition(Action action) {
		if (action.getCondition() == null)
			return;
		List<Arg> args = action.getCondition().getCondition().get(0).getArgs();
		String ancestor = "";
		String descendant = "";
		if (args.get(0).getFunction().getName().equals("current_target")) {
			ancestor = "{target}";
		} else if (args.get(0).getFunction().getName().equals("current_user")) {
			ancestor = "{user}";
		} else {
			ancestor = args.get(0).getFunction().getArgs().get(0).getValue();
			int ancestorId = mapOfIDs.get(ancestor);
			ancestor = Integer.toString(ancestorId);
		}
		if (args.get(1).getFunction().getName().equals("current_target")) {
			descendant = "{target}";
		} else if (args.get(1).getFunction().getName().equals("current_user")) {
			descendant = "{user}";
		} else {
			descendant = args.get(1).getFunction().getArgs().get(0).getValue();
			int descendantId = mapOfIDs.get(descendant);
			descendant = Integer.toString(descendantId);
		}

		conditions.add(new ConditionCustom(
				"(set.member (tuple " + ancestor + " " + descendant + ") (ASSIGN* " + "{k-1}" + "))",
				"(set.singleton(tuple " + ancestor + " " + descendant + "))", HierarchyType.UA,
				ConditionType.EXCLUSIVE));
		setCondition("(set.member (tuple " + ancestor + " " + descendant + ") (ASSIGN* " + "{k-1}" + "))");

		setConditionHierarchyType(HierarchyType.UA);

	}

	protected void encodeNegatedCondition() {
		if (condition.trim().isEmpty()) {
			return;
		}
		setNegatedPrecondition("(not " + condition + ")");
	}

	public String getNegatedCondition() {
		return negatedCondition;
	}

	protected void setNegatedCondition(String negatedCondition) {
		this.negatedCondition = negatedCondition;
	}

	public String getNegatedPrecondition() {
		return "\t" + negatedPrecondition;
	}

	protected void setNegatedPrecondition(String negatedPrecondition) {
		this.negatedPrecondition = negatedPrecondition;
	}

	public void setNegatedPostconditionSet(String negatedPostconditionSet) {
		this.negatedPostconditionSet = negatedPostconditionSet;
	}

	public List<ConditionCustom> getConditions() {
		return conditions;
	}

	abstract protected void retrieveActionPolicyElements();

	abstract protected void encodeActionPreconditions();

	abstract protected void encodeActionPostcondition();

	abstract protected void encodeActionPostconditionFlatten();

	public static String replaceKWithValue(String encoding, int k) throws Exception {
		if (k < 1)
			throw new Exception("k cannot be less then 1");
		return encoding.replace("{k}", Integer.toString(k)).replace("{k-1}", Integer.toString((k - 1)));
	}

}
