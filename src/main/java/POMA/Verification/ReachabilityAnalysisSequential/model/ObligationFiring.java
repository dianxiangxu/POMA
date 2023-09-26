package POMA.Verification.ReachabilityAnalysisSequential.model;

public class ObligationFiring {
    String obligationLabel;
    
    String event;
    String object;
    String subject;
    String source;
    String target;
    public String getObjectAttribute() {
        return objectAttribute;
    }

    public void setObjectAttribute(String objectAttribute) {
        this.objectAttribute = objectAttribute;
    }

    String objectAttribute;

    public ObligationFiring(String obligationLabel, String subject, String event, String object) {
        this.obligationLabel = obligationLabel;
        this.event = event;
        this.object = object;
        this.subject = subject;
    }

    public ObligationFiring(String obligationLabel, String subject, String source, String event, String object, String target) {
        this.obligationLabel = obligationLabel;
        this.event = event;
        this.object = object;
        this.subject = subject;
        this.source = source;
        this.target = target;

    }
    public ObligationFiring(){

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "ObligationFiring [obligationLabel=" + obligationLabel + ", subject=" + subject + ", event=" + event + ", object=" + object 
                + ", source="+ source + ", target=" + target + "]";
    }
    
}
