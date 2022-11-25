package POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations;

import java.util.List;

public class AssociationCustom implements IActionCustom{

	private String subject;
	private String target;
	private List<Integer> ops;
	
	public AssociationCustom(String subject, String target, List<Integer> ops) {
		this.subject = subject;
		this.target = target;
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
}
