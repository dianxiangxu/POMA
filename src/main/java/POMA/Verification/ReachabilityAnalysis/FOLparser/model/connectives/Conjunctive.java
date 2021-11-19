package POMA.Verification.ReachabilityAnalysis.FOLparser.model.connectives;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;

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
		// if (tuple.size() != 2) {
		// 	throw new Exception(
		// 			"Incorrect ASSIGN predictate format. Please use the following format: ASSIGN(ancestor, descendant)");
		// }
		return "(and " +subformulaA.toSMT()+" "+ subformulaB.toSMT()+")";
	}
}
