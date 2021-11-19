package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public interface IPredicate extends IFormula{
	public void setTuple(List<ITerm> tuple);
	public List<ITerm> getTuple();
}
