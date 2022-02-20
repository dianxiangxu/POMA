package POMA.Verification.ReachabilityAnalysis.FOLparser.model.predicates;

import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.Constant;
import POMA.Verification.ReachabilityAnalysis.FOLparser.model.terms.ITerm;

public class NodeExistsPredicate implements IPredicate{
    List<ITerm> tuple = new ArrayList<ITerm>();

    public NodeExistsPredicate() {
	}

    public List<ITerm> getTuple() {
        return tuple;
    }

    public void setTuple(List<ITerm> tuple) {
        this.tuple = tuple;
    }

    @Override
    public String toString() {
        return "NodeExistsPredicate [tuple=" + tuple + "]";
    }

    public String toSMT() throws Exception {
        if (tuple.size() != 1) {
            throw new Exception(
                    "Incorrect NODEEXISTS predictate format. Please use the following format: NODEEXISTS(node)");
        }

        String smtlibv2Code = "";
        String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;
		String aEncoding = "queryVAR" + tuple.get(0).getElement().replace("?", "");


        String aSpec =  a != null ? " [" + a + "] " : aEncoding;
        String dSpec =  " queryCONSTNODEEXISTSD_" + tuple.get(0).getElement() + "_" + "{k} ";

        smtlibv2Code += System.lineSeparator();
        smtlibv2Code += "(member (mkTuple " + aSpec + " " + dSpec + " ) (ASSIGN* " + "{(k + 1)}" + "))";

        smtlibv2Code += System.lineSeparator();
        return smtlibv2Code;
    }
}
