package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

public class IsUserPredicate implements IPredicate {
	List<ITerm> tuple = new ArrayList<ITerm>();

	public IsUserPredicate() {
	}

	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public String toString() {
		return "IsUserPredicate [tuple=" + tuple + "]";
	}

	public String toSMT() throws Exception {
		if (tuple.size() != 1) {
			throw new Exception(
					"Incorrect IsUser predictate format. Please use the following format: ISUSER(user)");
		}
		String smtlibv2Code = "";
		String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;

		String aEncoding = "queryVAR" + tuple.get(0).getElement().replace("?", "");

		String aSpec = a != null ? " [" + a + "] " : aEncoding;
		
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set.member (tuple " + aSpec + " " + aSpec + " ) USERS)";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	@Override
	public String toSMTCustomFunction() throws Exception {
		if (tuple.size() != 1) {
			throw new Exception(
					"Incorrect IsUser predictate format. Please use the following format: ISUSER(user)");
		}
		String smtlibv2Code = "";
		String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;

		String aEncoding =  tuple.get(0).getElement() + "_customvar_{k}";

		String aSpec = a != null ? " [" + a + "] " : aEncoding;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set.member (tuple " + aSpec + " " + aSpec + " ) USERS)";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

}
