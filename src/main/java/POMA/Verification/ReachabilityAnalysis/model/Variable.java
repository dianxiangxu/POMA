package POMA.Verification.ReachabilityAnalysis.model;

public class Variable {
	String name;
	String assignment;

	public Variable(String name, String assignment) {
		this.name = name;
		this.assignment = assignment;
	}

	@Override
	public String toString() {
		return "Variable [" + name + ":" + assignment + "]";
	}
}
