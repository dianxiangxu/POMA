package POMA.Verification.ReachabilityAnalysis;

public class Step {
    String obligationLabel;
    String subject;
    String event;
    String object;

    public Step(String obligationLabel, String subject, String event, String object) {
        this.obligationLabel = obligationLabel;
        this.subject = subject;
        this.event = event;
        this.object = object;
    }

    public Step(){

    }
    
    public String getObligationLabel() {
        return obligationLabel;
    }

    public void setObligationLabel(String obligationLabel) {
        this.obligationLabel = obligationLabel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Step [event=" + event + ", object=" + object + ", obligationLabel=" + obligationLabel + ", subject="
                + subject + "]";
    }
    
}
