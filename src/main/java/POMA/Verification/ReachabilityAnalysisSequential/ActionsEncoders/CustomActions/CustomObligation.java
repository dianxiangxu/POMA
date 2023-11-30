package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.RelationType;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.parser.FOLGrammar;

public class CustomObligation {
	private String label;
	private String axiom;
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

	public String getAxiom(HashMap<String, Integer> mapOfIDs) throws Exception {
		return CustomAction.generateSMT(CustomAction.parseQuery(axiom), mapOfIDs);
	}

	public void setAxiom(String axiom) {
		this.axiom = axiom;
	}
}
