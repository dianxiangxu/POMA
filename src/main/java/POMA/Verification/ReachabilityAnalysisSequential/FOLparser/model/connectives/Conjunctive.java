package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.connectives;

import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

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

	@Override
	public List<ITerm> getTuple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toSMTCustomFunction() throws Exception {
		// TODO Auto-generated method stub
		return "(and " +subformulaA.toSMTCustomFunction()+" "+ subformulaB.toSMTCustomFunction()+")";
		}
}
