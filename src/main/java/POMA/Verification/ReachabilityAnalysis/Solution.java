package POMA.Verification.ReachabilityAnalysis;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<ObligationFiring> obligationFirings = new ArrayList<ObligationFiring>();

    public Solution(List<ObligationFiring> obligationFirings) {
        this.obligationFirings = obligationFirings;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        sb.append("Solution ["+System.lineSeparator());
        for(ObligationFiring firing : obligationFirings){
            sb.append(i+": "+firing+ System.lineSeparator());
            i++;
        }
        sb.append("]");
        return sb.toString();
    }
}
