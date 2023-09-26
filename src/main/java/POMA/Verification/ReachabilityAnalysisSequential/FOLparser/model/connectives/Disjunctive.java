package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.connectives;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;

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
