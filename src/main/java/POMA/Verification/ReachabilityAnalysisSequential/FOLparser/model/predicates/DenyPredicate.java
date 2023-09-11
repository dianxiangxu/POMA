package POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;

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
		String ua = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String accessright = tuple.get(1) instanceof Constant ? tuple.get(1).getElement() : null;
		String at = tuple.get(2) instanceof Constant ? tuple.get(2).getElement() : null;

		String uaVar = " queryVAR" + tuple.get(0).getElement().replace("?", "") + " ";
		String arVar = " queryVAR" + tuple.get(1).getElement().replace("?", "") + " ";
		String atVar = " queryVAR" + tuple.get(2).getElement().replace("?", "") + " ";

		String userSpec = ua != null ? " [" + ua + "] " : uaVar;
		String arSpec = accessright != null ? " [" + accessright + "] " : arVar;
		String targetSpec = at != null ? " [" + at + "] " : atVar;

		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(not (set.member (tuple " + userSpec + arSpec + targetSpec + ") (rel.join (rel.join (rel.join NODES (ASSIGN* {(k + 1)})) (ASSOC {(k + 1)})) (rel.transpose (rel.join NODES (ASSIGN* {(k + 1)}))))))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
