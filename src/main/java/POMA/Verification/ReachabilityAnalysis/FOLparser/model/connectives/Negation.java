package POMA.Verification.ReachabilityAnalysis.FOLparser.model.connectives;


import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates.PermitPredicate;

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
		if (subformula instanceof PermitPredicate) {
			throw new Exception(
					"Negation of PERMIT predicate is not supported. Please use DENY instead.");
		}
		return "(not " + subformula.toSMT() + ")";
	}
}
