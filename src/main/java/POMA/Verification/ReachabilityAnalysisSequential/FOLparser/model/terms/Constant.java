package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms;

public class Constant implements ITerm{
	
	String element;
	
	public Constant(String element) {
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
		return "Constant [Constant=" + element + "]";
	}
}
