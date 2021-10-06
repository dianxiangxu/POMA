package POMA.Verification.ReachabilityAnalysis.fol.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.terms.ITerm;

public class AssociatePredicate implements IPredicate {
	
	List<ITerm> tuple = new ArrayList<ITerm>();

	@Override
	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public List<ITerm> getTuple() {
		return tuple;
	}

	public AssociatePredicate() {
		
	}
}
