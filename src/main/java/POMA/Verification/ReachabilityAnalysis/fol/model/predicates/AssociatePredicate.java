package POMA.Verification.ReachabilityAnalysis.fol.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.fol.model.terms.Constant;
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

	public String toSMT() {
		String smtlibv2Code = "";
		String ua = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String accessright = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		String at = tuple.get(2) instanceof Constant ? tuple.get(2).getElement() : null;

		String uaVar = " queryVARASSOCUA_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_"
				+ tuple.get(2).getElement() + "_" + "{k} ";
		String atVar = " queryVARASSOCAT_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_"
				+ tuple.get(2).getElement() + "_" + "{k} ";
		String arVar = " queryVARASSOCAR_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_"
				+ tuple.get(2).getElement() + "_" + "{k} ";

		String userSpec = ua != null ? " [" + ua + "] " : uaVar;
		String targetSpec = at != null ? " [" + at + "] " : atVar;
		String arSpec = accessright != null ? " [" + accessright + "] " : arVar;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code +=  "(member (mkTuple " + userSpec  + arSpec  + targetSpec + ") (ASSOC "
				+ "{(k + 1)}" + "))" ;

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
