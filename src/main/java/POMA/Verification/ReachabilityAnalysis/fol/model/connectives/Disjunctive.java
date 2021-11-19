package POMA.Verification.ReachabilityAnalysis.fol.model.connectives;

import POMA.Verification.ReachabilityAnalysis.fol.model.IFormula;

public class Disjunctive implements IFormula{
	private IFormula subformulaA = null, subformulaB = null;

	    public Disjunctive(IFormula subformulaA, IFormula subformulaB) {
	        this.subformulaA = subformulaA;
	        this.subformulaB = subformulaB;
	    }

	    @Override
		public String toString() {
			return "Disjunctive [subformulaA=" + subformulaA + ", subformulaB=" + subformulaB + "]";
		}  

		public String toSMT() throws Exception {
			return "(or " + subformulaA.toSMT() + " " + subformulaB.toSMT() + ")";
		}
}
