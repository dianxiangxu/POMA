package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations;

public class Trace {
	public enum TraceType {
		ASSIGNOUTPUT, ASSOCIATEOUTPUT, NEGATEDPRECONDITION, CONDITIONANDPRECONDITION, CONDITIONANDNEGATEDPRECONDITION, NEGATEDCONDITION
	}

	private String obligationLabel = "";
	private String inputCondition = "";
	private String outputCondition = "";
	private String outputConditionFlattened = "";

	private TraceType traceType;

	public Trace(String obligationLabel, String inputCondition, String outputCondition, String outputConditionFlattened,
			TraceType traceType) {
		this.obligationLabel = obligationLabel;
		this.inputCondition = inputCondition;
		this.outputCondition = outputCondition;
		this.outputConditionFlattened = outputConditionFlattened;
		this.traceType = traceType;
	}

	public Trace(String obligationLabel, String inputCondition, String outputCondition, TraceType traceType) {
		this.obligationLabel = obligationLabel;
		this.inputCondition = inputCondition;
		this.outputCondition = outputCondition;
		this.traceType = traceType;
	}

	public String getObligationLabel() {
		return obligationLabel;
	}

	public String getInputCondition() {
		return inputCondition;
	}

	public String getOutputCondition() {
		return outputCondition;
	}

	public String getOutputConditionFlattened() {
		return outputConditionFlattened;
	}

	public TraceType getTraceType() {
		return traceType;
	}

	public boolean affectsConfiguration() {
		return !outputCondition.isBlank();
	}

}
