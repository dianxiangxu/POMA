package POMA.Verification.ReachabilityAnalysis;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<Step> steps = new ArrayList<Step>();

    public Solution(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Solution [steps=" + steps + "]";
    }
}
