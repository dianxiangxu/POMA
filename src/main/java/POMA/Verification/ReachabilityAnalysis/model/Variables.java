package POMA.Verification.ReachabilityAnalysis.model;

import java.util.ArrayList;
import java.util.List;

public class Variables {

    private List<Variable> variables = new ArrayList<Variable>();

    public List<Variable> getVariables() {
        return variables;
    }

    public Variables(List<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("Variables ["+System.lineSeparator());
        for(Variable variable : variables){
            sb.append(i+": "+variable+ System.lineSeparator());
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}
