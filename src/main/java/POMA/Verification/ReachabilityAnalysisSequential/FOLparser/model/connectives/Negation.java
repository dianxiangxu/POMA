package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.connectives;


import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates.PermitPredicate;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

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

	@Override
	public List<ITerm> getTuple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toSMTCustomFunction() throws Exception {
		// TODO Auto-generated method stub
		if (subformula instanceof PermitPredicate) {
			throw new Exception(
					"Negation of PERMIT predicate is not supported. Please use DENY instead.");
		}
		return "(not " + subformula.toSMTCustomFunction() + ")";
		}
}
