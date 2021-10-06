package POMA.Verification.ReachabilityAnalysis.fol.model.predicates;

import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.IFormula;
import POMA.Verification.ReachabilityAnalysis.fol.model.terms.ITerm;

public interface IPredicate extends IFormula{
	public void setTuple(List<ITerm> tuple);
	public List<ITerm> getTuple();
}
