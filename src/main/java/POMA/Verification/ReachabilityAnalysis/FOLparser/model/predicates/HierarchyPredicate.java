package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public class HierarchyPredicate implements IPredicate {
	List<ITerm> tuple = new ArrayList<ITerm>();

	public HierarchyPredicate() {

	}

	@Override
	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public List<ITerm> getTuple() {
		return tuple;
	}

	@Override
	public String toString() {
		return "Hierarchy [tuple=" + tuple + "]";
	}

	public String toSMT() throws Exception {
		if (tuple.size() != 2) {
			throw new Exception(
					"Incorrect HIERARCHY predictate format. Please use the following format: HIERARCHY(ancestor, descendant)");
		}
		String smtlibv2Code = "";
		String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String d = tuple.get(0) instanceof Constant ? tuple.get(1).getElement() : null;

		String aVar = " queryVARASSIGN*S_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_" + "{k} ";
		String dVar = " queryVARASSIGN*T_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_" + "{k} ";

		String aSpec = a != null ? " [" + a + "] " : aVar;
		String dSpec = d != null ? " [" + d + "] " : dVar;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(or (member (mkTuple " + aSpec + dSpec + ") (ASSIGN* " + "{(k + 1)}" + ")) (member (mkTuple "
				+ dSpec + aSpec + ") (ASSIGN* " + "{(k + 1)}" + ")))";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
