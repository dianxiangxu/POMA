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
	private String condition;

	static FOLGrammar parser = null;
	private int index;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		int index = action.indexOf('(');
		String prefix = action.substring(0, index).toUpperCase();
		if (prefix.toLowerCase().equals("grant")) {
			prefix = "ASSOCIATE";
		}
		String rest = action.substring(index);
		this.action = prefix + rest;
	}

	public List<ITerm> getActionTuple() throws Exception {
		IFormula f = parseQuery(action);
		f.getTuple();
		return f.getTuple();
	}

	public static IFormula parseQuery(String query) {
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

	public static String generateSMT(IFormula f, HashMap<String, Integer> mapOfIDs) throws Exception {
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
	public String getCondition() throws Exception {
		return condition;
	}
	public String getConditionEncoding(HashMap<String, Integer> mapOfIDs) throws Exception {
		return generateSMT(CustomAction.parseQuery(condition), mapOfIDs);
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
}