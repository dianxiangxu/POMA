package POMA.Verification.ReachabilityAnalysis.model;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	private List<ObligationFiring> obligationFirings = new ArrayList<ObligationFiring>();

	public void setObligationFirings(List<ObligationFiring> obligationFirings) {
		this.obligationFirings = obligationFirings;
	}

	Variables variables;

	public List<ObligationFiring> getObligationFirings() {
		return obligationFirings;
	}

	public Solution(List<ObligationFiring> obligationFirings, Variables variables) {
		this.obligationFirings = obligationFirings;
		this.variables = variables;
	}

	public Variables getVariables() {
		return variables;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 1;
		sb.append("Solution [" + System.lineSeparator());
		for (ObligationFiring firing : obligationFirings) {
			sb.append(i + ": " + firing + System.lineSeparator());
			i++;
		}
		sb.append("] ");
		sb.append(System.lineSeparator() + variables);
		return sb.toString();
	}
}
