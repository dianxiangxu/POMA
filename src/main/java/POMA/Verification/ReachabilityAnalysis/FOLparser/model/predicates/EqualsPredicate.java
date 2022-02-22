package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public class EqualsPredicate implements IPredicate {
	List<ITerm> tuple = new ArrayList<ITerm>();

	public EqualsPredicate() {
	}

	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public String toString() {
		return "EqualsPredicate [tuple=" + tuple + "]";
	}

	public String toSMT() throws Exception {
		if (tuple.size() != 2) {
			throw new Exception(
					"Incorrect EQUALS predictate format. Please use the following format: EQUALS(left, right)");
		}
		String smtlibv2Code = "";
		String l= tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String r = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		
		String lEncoding = "queryVAR" + tuple.get(0).getElement().replace("?", "");
		String rEncoding = "queryVAR" + tuple.get(1).getElement().replace("?", "");
		
		String lSpec = l != null ? " [" + l + "] " : lEncoding;
		String rSpec = r != null ? " [" + r + "] " : rEncoding;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(= " + lSpec  + " " + rSpec + " )";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

}
