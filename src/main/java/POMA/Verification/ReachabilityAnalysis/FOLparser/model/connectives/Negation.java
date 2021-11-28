package POMA.Verification.ReachabilityAnalysis.FOLparser.model.connectives;


import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;

public class Negation implements IFormula {

	private IFormula subformula = null;

	public Negation(IFormula subformula) {
		this.subformula = subformula;
	}

	@Override
	public String toString() {
		return "Negation [subformula=" + subformula + "]";
	}

	public String toSMT() throws Exception {
		return "(not " + subformula.toSMT() + ")";
	}
}
