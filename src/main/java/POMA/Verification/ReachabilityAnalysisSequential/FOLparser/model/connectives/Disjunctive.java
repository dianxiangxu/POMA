package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.connectives;

import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

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

		@Override
		public List<ITerm> getTuple() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toSMTCustomFunction() throws Exception {
			// TODO Auto-generated method stub
			return "(or " + subformulaA.toSMTCustomFunction() + " " + subformulaB.toSMTCustomFunction() + ")";
		}
}
