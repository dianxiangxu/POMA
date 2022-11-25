package POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Conditions;

public class Trace {
	public enum TraceType {
		CONDITION, 
		PRECONDITION, 
		POSTCONDITIONASSIGN,
		POSTCONDITIONASSOCIATE
	}
	
	private String obligationLabel;

	private String trace;
	private String traceFlattened;
	
	private TraceType traceType;

	
	public Trace(String obligationLabel, String trace, String traceFlattened, TraceType traceType) {
		this.obligationLabel = obligationLabel;
		this.trace = trace;
		this.traceFlattened = traceFlattened;
		this.traceType = traceType;
	}

	public Trace(String obligationLabel, String trace, TraceType traceType) {
		this.obligationLabel = obligationLabel;
		this.trace = trace;
		this.traceType = traceType;
	}
	
	public String getObligationLabel() {
		return obligationLabel;
	}

	public String getTrace() {
		return trace;
	}
	
	public String getTraceFlattened() {
		return traceFlattened;
	}

	public TraceType getTraceType() {
		return traceType;
	}
	
	public boolean affectsConfiguration() {
		return traceType==TraceType.POSTCONDITIONASSIGN || traceType==TraceType.POSTCONDITIONASSOCIATE;
	}
	
}
