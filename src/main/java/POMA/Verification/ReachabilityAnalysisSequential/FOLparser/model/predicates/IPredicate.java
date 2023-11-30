package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates;

import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

public interface IPredicate extends IFormula{
	public void setTuple(List<ITerm> tuple);
	public List<ITerm> getTuple();
	public String toSMTCustomFunction() throws Exception;
}
