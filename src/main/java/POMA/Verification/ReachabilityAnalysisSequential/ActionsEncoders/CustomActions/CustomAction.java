package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.parser.FOLGrammar;

public class CustomAction {

	private String relationType;
	private String precondition;
	private String effect;
	static FOLGrammar parser = null;

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getPrecondition(int k) throws Exception {
		return generateSMT(parseQuery(precondition), k, new ArrayList<String>(), new ArrayList<String>());
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public String getEffect(int k) throws Exception {
		return generateSMT(parseQuery(effect), k, new ArrayList<String>(), new ArrayList<String>());
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public IFormula parseQuery(String query) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(query.getBytes());
		if (parser == null) {
			parser = new FOLGrammar(inputStream);
		} else {
			parser.ReInit(inputStream);
		}

		while (true) {
			try {
				IFormula f = FOLGrammar.parse();
				return f;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
			} catch (Error e) {
				System.out.println(e.getMessage());
				break;
			}
		}
		return null;
	}

	public String generateSMT(IFormula f, int k, List<String> queryVARS, List<String> queryConst) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		String formula = f.toSMT();

		String formulaWithStep = formula.replace("{k}", Integer.toString(k)).replace("{(k + 1)}",
				Integer.toString((k + 1)));

		String[] splittedFormula = formula.split(" ");
		for (String formulaElement : splittedFormula) {
			if (formulaElement.contains("queryCONST") && !queryConst.contains(formulaElement)) {
				sb.append("(declare-fun " + formulaElement + " () Int)");
				sb.append(System.lineSeparator());
				queryConst.add(formulaElement);
				continue;
			}
			if (formulaElement.contains("queryVAR") && !queryVARS.contains(formulaElement)) {
				sb.append("(declare-fun " + formulaElement + " () Int)");
				sb.append(System.lineSeparator());
				queryVARS.add(formulaElement);
				continue;
			}
//				if (formulaElement.contains("[")) {
//					String cleanFormulaElement = formulaElement.replace("[", "").replace("]", "");
//					Integer elementId = mapOfIDs.getOrDefault(cleanFormulaElement, -1);
//					formulaWithStep = formulaWithStep.replace(formulaElement, Integer.toString(elementId));
//				}
		}
		return formulaWithStep;
	}
}