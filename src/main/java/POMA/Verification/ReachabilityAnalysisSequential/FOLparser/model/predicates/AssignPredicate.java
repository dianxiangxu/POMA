package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

public class AssignPredicate implements IPredicate {
	List<ITerm> tuple = new ArrayList<ITerm>();

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

	public String toSMT() throws Exception {
		if (tuple.size() != 2) {
			throw new Exception(
					"Incorrect ASSIGN predictate format. Please use the following format: ASSIGN(ancestor, descendant)");
		}
		String smtlibv2Code = "";
		String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String d = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;

		String aEncoding = "queryVAR" + tuple.get(0).getElement().replace("?", "");
		String dEncoding = "queryVAR" + tuple.get(1).getElement().replace("?", "");

		String aSpec = a != null ? " [" + a + "] " : aEncoding;
		String dSpec = d != null ? " [" + d + "] " : dEncoding;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set.member (tuple " + aSpec + " " + dSpec + " ) (ASSIGN* " + "{(k + 1)}" + "))";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
	public String toSMTCustomFunction() throws Exception {
		if (tuple.size() != 2) {
			throw new Exception(
					"Incorrect ASSIGN predictate format. Please use the following format: ASSIGN(ancestor, descendant)");
		}
		String smtlibv2Code = "";
		String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String d = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;

		String aEncoding =  tuple.get(0).getElement()  + "_customvar_{k}";
		String dEncoding =  tuple.get(1).getElement()  + "_customvar_{k}";

		String aSpec = a != null ? " [" + a + "] " : aEncoding;
		String dSpec = d != null ? " [" + d + "] " : dEncoding;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set.member (tuple " + aSpec + " " + dSpec + " ) (ASSIGN* " + "{k}" + "))";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
