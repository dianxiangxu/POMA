package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public class DenyPredicate implements IPredicate{

	List<ITerm >tuple = new ArrayList<ITerm>();

	
	public DenyPredicate() {
		
	}
	public List<ITerm> getTuple() {
		return tuple;
	}

	public void setTuple(List<ITerm> tuple) {
		this.tuple = tuple;
	}
	
	@Override
	public String toString() {
		return "DenyPredicate [tuple=" + tuple + "]";
	}


	public String toSMT() throws Exception {
		if (tuple.size() != 3) {
			throw new Exception(
					"Incorrect DENY predictate format. Please use the following format: DENY(source, ar, target)");
		}
		String smtlibv2Code = "";
		String s = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String accessright = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		String t = tuple.get(2) instanceof Constant ? tuple.get(2).getElement() : null;

		String uVar = " queryVAR" + tuple.get(0).getElement().replace("?", "") + " ";//UDV
		String arVar = " queryVAR" + tuple.get(1).getElement().replace("?", "")+ " ";//UDV
		String uoVar = " queryVAR" + tuple.get(2).getElement().replace("?", "")+ " ";//UDV

		String uaVar = " queryCONSTDENYUA_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_"
				+ tuple.get(2).getElement() + "_" + "{(k + 1)} "; 
		String atVar = " queryCONSTDENYAT_" + tuple.get(0).getElement() + "_" + tuple.get(1).getElement() + "_"
				+ tuple.get(2).getElement() + "_" + "{(k + 1)} ";

		String userSpec = s != null ? "(member (mkTuple  [" + s + "] " + uaVar + ") (ASSIGN* " + "{(k + 1)}" + "))"
				: "(member (mkTuple" + uVar + uaVar + ") (ASSIGN* " + "{(k + 1)}" + "))";
		String targetSpec = t != null ? "(member (mkTuple  [" + t + "] " + atVar + ") (ASSIGN* " + "{(k + 1)}" + "))"
				: "(member (mkTuple" + uoVar + atVar + ") (ASSIGN* " + "{(k + 1)}" + "))";

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += accessright != null
				? "(not(and" + userSpec + "(member (mkTuple " + uaVar + "[" + accessright + "] " + atVar + ") (ASSOC "
						+ "{(k + 1)}" + "))" + targetSpec + "))"
				: "(not(and" + userSpec + "(member (mkTuple " + uaVar + arVar + atVar + ") (ASSOC " + "{(k + 1)}" + "))"
						+ targetSpec + "))";

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
