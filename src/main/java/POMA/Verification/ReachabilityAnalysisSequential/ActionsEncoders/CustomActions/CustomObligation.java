package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.RelationType;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.parser.FOLGrammar;

public class CustomObligation {
    private String label;
    private List<CustomAction> actions;
	public String getLabel() {
		return label;
	}
	public void setLabel(String name) {
		this.label = name;
	}
	public List<CustomAction> getActions() {
		return actions;
	}
	public void setActions(List<CustomAction> actions) {
		this.actions = actions;
	}



}
