package POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.IFormula;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.parser.FOLGrammar;

public class CustomAction {

	private String action;
	

	private String precondition;
	private String effect;
	static FOLGrammar parser = null;
	private int index;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getPrecondition(HashMap<String, Integer> mapOfIDs) throws Exception {
		return generateSMT(parseQuery(precondition), mapOfIDs);
	}

	public void setPrecondition(String precondition) {
		this.precondition = precondition;
	}

	public List<ITerm> getEffectTuple() throws Exception {
		IFormula f = parseQuery(effect);
		f.getTuple();
		return f.getTuple();
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public IFormula parseQuery(String query) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(query.getBytes());
//		if (parser == null) {
//			parser = new FOLGrammar(inputStream);
//		} else {
			parser.ReInit(inputStream);
//		}

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

	public String generateSMT(IFormula f, HashMap<String, Integer> mapOfIDs) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		String formula = f.toSMTCustomFunction();

		String[] splittedFormula = formula.split(" ");
		for (String formulaElement : splittedFormula) {
			if (formulaElement.contains("[")) {
				String cleanFormulaElement = formulaElement.replace("[", "").replace("]", "");
				Integer elementId = mapOfIDs.getOrDefault(cleanFormulaElement, -1);
				formula = formula.replace(formulaElement, Integer.toString(elementId));
			}
		}
		return formula;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}