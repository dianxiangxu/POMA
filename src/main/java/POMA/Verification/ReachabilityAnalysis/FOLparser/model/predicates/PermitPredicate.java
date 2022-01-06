package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public class PermitPredicate implements IPredicate {

	List<ITerm> tuple = new ArrayList<ITerm>();

	public PermitPredicate() {

	}

	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}

	@Override
	public String toString() {
		return "PermitPredicate [tuple=" + tuple + "]";
	}

	public String toSMT() throws Exception {
		if (tuple.size() != 3) {
			throw new Exception(
					"Incorrect PERMIT predictate format. Please use the following format: PERMIT(source, ar, target)");
		}
		String smtlibv2Code = "";
		String s = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String accessright = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		String t = tuple.get(2) instanceof Constant ? tuple.get(2).getElement() : null;

		String uVar = " queryVARPERMITU_" + tuple.get(0).getElement()+"_"+ tuple.get(1).getElement()
				+ "_" + tuple.get(2).getElement() + "_" + "{(k + 1)} ";
		String uoVar = " queryVARPERMITUO_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement()
				+ "_" + tuple.get(2).getElement() + "_" + "{(k + 1)} ";
		String uaVar = " queryVARPERMITUA_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement()
				+ "_" + tuple.get(2).getElement() + "_" + "{(k + 1)} ";
		String atVar = " queryVARPERMITAT_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement()
				+ "_" + tuple.get(2).getElement() + "_" + "{(k + 1)} ";
		String arVar = " queryVARPERMITAR_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement()
				+ "_" + tuple.get(2).getElement() + "_" + "{(k + 1)} ";

		String userSpec = s != null ? "(member (mkTuple  [" + s + "] " + uaVar + ") (ASSIGN* " + "{(k + 1)}" + "))"
				: "(member (mkTuple  " + uVar + uaVar + ") (ASSIGN* " + "{(k + 1)}" + "))";
		String targetSpec = t != null ? "(member (mkTuple  [" + t + "] " + atVar + ") (ASSIGN* " + "{(k + 1)}" + "))"
				: "(member (mkTuple  " + uoVar + atVar + ") (ASSIGN* " + "{(k + 1)}" + "))";

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += accessright != null
				? "(and" + userSpec + "(member (mkTuple " + uaVar + "[" + accessright + "] " + atVar + ") (ASSOC "
						+ "{(k + 1)}" + "))" + targetSpec + ")"
				: "(and" + userSpec + "(member (mkTuple " + uaVar + arVar + atVar + ") (ASSOC " + "{(k + 1)}" + "))"
						+ targetSpec + ")";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
