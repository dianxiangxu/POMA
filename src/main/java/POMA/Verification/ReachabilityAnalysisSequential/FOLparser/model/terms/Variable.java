package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms;

public class Variable implements ITerm {
String element;
	
	public Variable(String element) {
		this.element = element;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Variable [Variable=" + element + "]";
	}
}
