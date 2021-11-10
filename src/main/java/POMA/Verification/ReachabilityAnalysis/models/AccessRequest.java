package POMA.Verification.ReachabilityAnalysis.models;

public class AccessRequest implements Request{

    Integer S;
    Integer ar;
    Integer T;

    public AccessRequest(Integer s, Integer ar, Integer t) {
        this.S = s;
        this.ar = ar;
        this.T = t;
    }

    public Integer getS() {
        return S;
    }

    public void setS(Integer s) {
        S = s;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getT() {
        return T;
    }

    public void setT(Integer t) {
        T = t;
    }
    
}
