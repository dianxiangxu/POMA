package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.util.HashMap;
import java.util.List;

import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;

public abstract class ActionEncoder {

	private String condition = "";
	private String negatedCondition = "";
	private String precondition = "";
	private String negatedPrecondition = "";
	private String postcondition = "";
	private String postconditionFlatten = "";

	private RelationType relationType;
	HashMap<String, Integer> mapOfIDs;

	public enum RelationType {
		ASSIGN, ASSOCIATE
	}

	public ActionEncoder(HashMap<String, Integer> mapOfIDs, RelationType relationType) {
		this.mapOfIDs = mapOfIDs;
		this.relationType = relationType;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getPrecondition() {
		return precondition;
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getPostcondition() {
		return postcondition;
	}

	public void setPostcondition(String postcondition) {
		this.postcondition = postcondition;
	}

	public String getPostconditionFlatten() {
		return postconditionFlatten;
	}

	public void setPostconditionFlatten(String postconditionFlatten) {
		this.postconditionFlatten = postconditionFlatten;
	}

	public RelationType getRelationType() {
		return relationType;
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
		setCondition("(member (mkTuple " + ancestor + " " + descendant + ") (ASSIGN* " + "{k-1}" + "))");
	}

	protected void encodeNegatedPrecondition() {
		if(precondition.isBlank()) {
			return;
		}
		setNegatedPrecondition("(not " + precondition + ")");
	}

	protected void encodeNegatedCondition() {
		if(condition.isBlank()) {
			return;
		}
		setNegatedPrecondition("(not " + condition + ")");
	}
	
	public String getNegatedCondition() {
		return negatedCondition;
	}

	public void setNegatedCondition(String negatedCondition) {
		this.negatedCondition = negatedCondition;
	}

	public String getNegatedPrecondition() {
		return negatedPrecondition;
	}

	public void setNegatedPrecondition(String negatedPrecondition) {
		this.negatedPrecondition = negatedPrecondition;
	}

	abstract protected void retrieveActionPolicyElements();

	abstract protected void encodeActionPrecondition();

	abstract protected void encodeActionPostcondition();

	abstract protected void encodeActionPostconditionFlatten();
}
