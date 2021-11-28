package POMA.Verification.ReachabilityAnalysisGPMS;

import java.util.HashMap;
import java.util.List;

public class ProcessedTuple {
    HashMap<String, String> obligationPreconditions;
    List<String> obligationEffects;

    public ProcessedTuple(HashMap<String, String> obligationPrecondtions, List<String> obligationEffects) {
        this.obligationPreconditions = obligationPrecondtions;
        this.obligationEffects = obligationEffects;
    }
    
    public HashMap<String, String> getObligationPrecondtions() {
        return obligationPreconditions;
    }

    public List<String> getObligationEffects() {
        return obligationEffects;
    }
}
