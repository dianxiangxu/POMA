package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model;

import java.util.List;
import java.util.Map;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

public interface IFormula {
    public String toSMT() throws Exception;
	public List<ITerm> getTuple();
	public String toSMTCustomFunction() throws Exception;

}
