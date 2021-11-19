package POMA.Verification.ReachabilityAnalysis.fol.model.connectives;

import POMA.Verification.ReachabilityAnalysis.fol.model.IFormula;

public class Conjunctive implements IFormula {
	private IFormula subformulaA = null, subformulaB = null;

	public Conjunctive(IFormula innerA, IFormula innerB) {
		this.subformulaA = innerA;
		this.subformulaB = innerB;
	}

	public IFormula getSubformulaA() {
		return subformulaA;
	}

	public IFormula getSubformulaB() {
		return subformulaB;
	}

	@Override
	public String toString() {
		return "Conjunctive [subformulaA=" + subformulaA + ", subformulaB=" + subformulaB + "]";
	}

	public String toSMT() throws Exception{
		return "(and " +subformulaA.toSMT()+" "+ subformulaB.toSMT()+")";
	}
}
