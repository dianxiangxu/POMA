package POMA.Verification.ReachabilityAnalysis.fol.model.connectives;

import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.IFormula;

public class Negation implements IFormula {

	private IFormula subformula = null;

	public Negation(IFormula subformula) {
		this.subformula = subformula;
	}

	@Override
	public String toString() {
		return "Negation [subformula=" + subformula + "]";
	}
}
