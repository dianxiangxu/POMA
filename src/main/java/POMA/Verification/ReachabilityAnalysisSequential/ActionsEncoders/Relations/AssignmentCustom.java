package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations;

public class AssignmentCustom implements IActionCustom{

	private String what;
	private String where;
	private String whatType;
	private String whereType;

	public AssignmentCustom(String what, String where, String whatType, String whereType) {
		this.what = what;
		this.where = where;
		this.whatType = whatType;
		this.whereType = whereType;
	}

	public String getWhat() {
		return what;
	}

	public String getWhere() {
		return where;
	}

	public String getWhatType() {
		return whatType;
	}

	public String getWhereType() {
		return whereType;
	}	
}
