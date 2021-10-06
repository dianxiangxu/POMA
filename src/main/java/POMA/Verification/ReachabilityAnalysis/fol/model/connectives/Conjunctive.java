package POMA.Verification.ReachabilityAnalysis.fol.model.connectives;

import POMA.Verification.ReachabilityAnalysis.fol.model.IFormula;

public class Conjunctive implements IFormula{
	private IFormula subformulaA = null, subformulaB = null;

	    public Conjunctive(IFormula innerA, IFormula innerB) {
	        this.subformulaA = innerA;
	        this.subformulaB = innerB;
	    }
	    
	    @Override
		public String toString() {
			return "Conjunctive [subformulaA=" + subformulaA + ", subformulaB=" + subformulaB + "]";
		}
}
