package POMA.Verification.ReachabilityAnalysis;

public class ObligationFiring {
    String obligationLabel;
    
    String event;
    String user;
    String object;

    String subject;
    String userAttribute;
    String target;
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserAttribute() {
        return userAttribute;
    }

    public void setUserAttribute(String userAttribute) {
        this.userAttribute = userAttribute;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getObjectAttribute() {
        return objectAttribute;
    }

    public void setObjectAttribute(String objectAttribute) {
        this.objectAttribute = objectAttribute;
    }

    String objectAttribute;

    public ObligationFiring(String obligationLabel, String subject, String event, String object) {
        this.obligationLabel = obligationLabel;
        this.user = subject;
        this.event = event;
        this.object = object;
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

    @Override
    public String toString() {
        return "Step [obligationLabel=" + obligationLabel + ", subject=" + subject + ", event=" + event + ", object=" + object   + "]";
    }
    
}
