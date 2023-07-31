package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations;

import java.util.List;

public class AssociationCustom implements IActionCustom{

	private String subject;
	private String target;
	private String targetType;
	private List<Integer> ops;
	
	public AssociationCustom(String subject, String target, String targetType, List<Integer> ops) {
		this.subject = subject;
		this.target = target;
		this.targetType = targetType;		
		this.ops = ops;
	}

	public String getSubject() {
		return subject;
	}

	public String getTarget() {
		return target;
	}

	public List<Integer> getOps() {
		return ops;
	}

	public String getTargetType() {
		return targetType;
	}
}
