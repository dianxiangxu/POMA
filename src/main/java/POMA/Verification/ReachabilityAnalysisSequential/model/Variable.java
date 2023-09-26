package POMA.Verification.ReachabilityAnalysisSequential.model;

public class Variable {
	String name;
	String assignment;

	public Variable(String name, String assignment) {
		this.name = name;
		this.assignment = assignment;
	}
	
	public String getName() {
		return name;
	}

	public String getAssignment() {
		return assignment;
	}
	
	@Override
	public String toString() {
		return "Variable [" + name + ":" + assignment + "]";
	}
}
