package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

public class ObligationLabelPredicate implements IPredicate {
	List<ITerm> tuple = new ArrayList<ITerm>();

	public ObligationLabelPredicate() {
	}

	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public String toString() {
		return "ObligationLabelPredicate [tuple=" + tuple + "]";
	}

	public String toSMT() throws Exception {
		if (tuple.size() != 4) {
			throw new Exception(
					"Incorrect OBLIGATIONLABEL predictate format. Please use the following format: OBLIGATIONLABEL(label)");
		}
		if (!(tuple.get(0) instanceof Constant)) {
			throw new Exception(
					"Incorrect OBLIGATIONLABEL predictate format. Label has to be constant. Please use the following format: OBLIGATIONLABEL(label)");
		}
		String smtlibv2Code = "";
		String label = tuple.get(0).getElement();

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(and (= (" + label + " {k}" + ") true)";

		smtlibv2Code += System.lineSeparator();

		String s = tuple.get(1) instanceof Constant ? "[" + tuple.get(1).getElement() + "]"
				: "queryVAR" + tuple.get(1).getElement().replace("?", "");
		String ar = tuple.get(2) instanceof Constant ? "[" + tuple.get(2).getElement() + "]"
				: "queryVAR" + tuple.get(2).getElement().replace("?", "");
		String t = tuple.get(3) instanceof Constant ? "[" + tuple.get(3).getElement() + "]"
				: "queryVAR" + tuple.get(3).getElement().replace("?", "");

		String sEncoding = label + "U" + "_" + "{k}";
		String arEncoding = label + "ar" + "_" + "{k}";
		String tEncoding = label + "T" + "_" + "{k}";
		smtlibv2Code += " (= " + sEncoding + " " + s + " )";
		smtlibv2Code += " (= " + arEncoding + " " + ar + " )";
		smtlibv2Code += " (= " + tEncoding + " " + t + " )";
		smtlibv2Code += ")";
		return smtlibv2Code;
	}

	@Override
	public String toSMTCustomFunction() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
