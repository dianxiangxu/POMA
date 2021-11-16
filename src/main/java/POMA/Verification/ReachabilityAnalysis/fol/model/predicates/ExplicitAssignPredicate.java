package POMA.Verification.ReachabilityAnalysis.fol.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.terms.ITerm;

public class ExplicitAssignPredicate implements IPredicate{
	
	List<ITerm >tuple = new ArrayList<ITerm>();
	
	public ExplicitAssignPredicate() {
	}

	public List<ITerm> getTuple() {
		return tuple;
	}
	
	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}
	
	@Override
	public String toString() {
		return "ExplicitAssignPredicate [tuple=" + tuple + "]";
	}

	public String toSMT() {
		return "";
	}
}
