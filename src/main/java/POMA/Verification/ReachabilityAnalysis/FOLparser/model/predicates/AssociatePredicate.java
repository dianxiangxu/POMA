package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

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

	public String toSMT() throws Exception {
		if (tuple.size() != 3) {
			throw new Exception(
					"Incorrect ASSOCIATE predictate format. Please use the following format: ASSOCIATE(source, ar, target)");
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
		smtlibv2Code +=  "(member (mkTuple " + userSpec  + arSpec  + targetSpec + ") (ASSOC "
				+ "{(k + 1)}" + "))" ;

		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
}
