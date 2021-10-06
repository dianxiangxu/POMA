package POMA.Verification.ReachabilityAnalysis.fol.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.terms.ITerm;

public class AssignPredicate implements IPredicate{
	List<ITerm >tuple = new ArrayList<ITerm>();

	public AssignPredicate() {
	}

	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public String toString() {
		return "AssignPredicate [tuple=" + tuple + "]";
	}
}
