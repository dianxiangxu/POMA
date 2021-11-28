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
                    "Incorrect EXISTS predictate format. Please use the following format: EXISTS(node)");
        }
        if(!(tuple.get(0) instanceof Constant)){
            throw new Exception(
                    "Incorrect EXISTS predictate format. Please use the following format: EXISTS(node). The \"node\" term needs to be constant");
        }
        String smtlibv2Code = "";
        String a = tuple.get(0) instanceof Constant ? tuple.get(0).getElement() : null;

        String dVar = " queryVARASSIGN*D_" + tuple.get(0).getElement() + "_" + "{k} ";

        String aSpec =  " [" + a + "] ";
        String dSpec =  dVar;

        smtlibv2Code += System.lineSeparator();
        smtlibv2Code += "(member (mkTuple " + aSpec + dSpec + ") (ASSIGN* " + "{(k + 1)}" + "))";

        smtlibv2Code += System.lineSeparator();
        return smtlibv2Code;
    }
}
