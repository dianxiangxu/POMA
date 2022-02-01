package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

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
		smtlibv2Code += "(and (= (" + label  + " {(k + 1)}" + ") true)";

		smtlibv2Code += System.lineSeparator();
		
		String s = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		String ar = tuple.get(2) instanceof Constant ? tuple.get(2).getElement() : null;
		String t = tuple.get(3) instanceof Constant ? tuple.get(3).getElement() : null;

		String sVar = label + "U" + "_" + "{(k + 1)}";
		String tVar = label + "T" + "_" + "{(k + 1)}";
		String arVar = label + "ar" + "_" + "{(k + 1)}";
		smtlibv2Code += s != null ? " (= "+sVar+" ["+s+"] )" :"";
		smtlibv2Code += ar != null ? " (= "+arVar+" ["+ar+"] )" :"";
		smtlibv2Code += t != null ? " (= "+tVar+" ["+t+"] )" :"";
		smtlibv2Code += ")";
		return smtlibv2Code;
	}
}
