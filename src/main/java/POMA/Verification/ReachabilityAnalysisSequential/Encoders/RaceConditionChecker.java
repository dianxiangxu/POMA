package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.ActionType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.RelationType;

public class RaceConditionChecker {
	String _assignments;
	String _transitiveAssignments;
	String _previousTransitiveAssignments;

	String _association;
	List<ObligationEncoder> _obligationEncoders;
	String _sequenceName;

	private final static String ASSIGN_TYPE = "ASSIGN";
	private final static String TRANSITIVE_ASSIGN_TYPE = "ASSIGN*";
	private final static String PREVIOUS_TRANSITIVE_ASSIGN_TYPE = "ASSIGN_PREV*";

	private final Map<String, List<Boolean>> actionPostconditionMap = new HashMap<>();

	public RaceConditionChecker() {

	}

	public static void main(String[] args) throws Exception {

	}

	public String raceCheckEncoder() {
		String[] obligationEventGroupLabels = _sequenceName.split("__");
		List<ObligationEncoder> filteredObligationEncoders = _obligationEncoders.stream().filter(
				obligationEncoder -> Arrays.asList(obligationEventGroupLabels).contains(obligationEncoder._label))
				.collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		sb.append(generateHeadCode() + System.lineSeparator());
		List<String> tuplesAssign = extractAssignTuples(_assignments);
		sb.append(encodeAssignments(tuplesAssign, ASSIGN_TYPE));
		sb.append(System.lineSeparator());
		List<String> tuplesTransitiveAssign = extractAssignTuples(_transitiveAssignments);
		sb.append(encodeAssignments(tuplesTransitiveAssign, TRANSITIVE_ASSIGN_TYPE));
		sb.append(System.lineSeparator());
		List<String> tuplesPreviousTransitiveAssign = extractAssignTuples(_previousTransitiveAssignments);
		sb.append(encodeAssignments(tuplesPreviousTransitiveAssign, PREVIOUS_TRANSITIVE_ASSIGN_TYPE));
		sb.append(System.lineSeparator());
		List<String> tuplesAssoc = extractAssocTuples(_association);
		sb.append(encodeAssociations(tuplesAssoc));

		String getValueCommands = "";

		for (ObligationEncoder oe : filteredObligationEncoders) {

			String defineActionIdVariables = oe._actionEncoders.stream()
					.map(actionEncoder -> "(declare-fun " + actionEncoder.getActionId() + " () Bool)")
					.collect(Collectors.joining("\n"));
			sb.append(System.lineSeparator() + defineActionIdVariables);
			getValueCommands += oe._actionEncoders.stream()
					.map(actionEncoder -> "\n(get-value ( " + actionEncoder.getActionId() + "))")
					.collect(Collectors.joining("\n"));
			encodeRaceCheck(sb, oe._actionEncoders);
		}
		sb.append(generateTailCode(getValueCommands));
		String output = sb.toString().replace("{k}", "");
		return output;
	}

	private List<String> extractAssocTuples(String input) {
		List<String> tuples = new ArrayList<>();
		String regex = "tuple (\\d+) (\\d+) (\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String tuple = "(tuple " + matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + ")";
			tuples.add(tuple);
		}

		return tuples;
	}

	private List<String> extractAssignTuples(String input) {
		List<String> tuples = new ArrayList<>();
		String regex = "tuple (\\d+) (\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String tuple = "(tuple " + matcher.group(1) + " " + matcher.group(2) + ")";
			tuples.add(tuple);
		}

		return tuples;
	}

	public String encodeRaceCheck(StringBuilder sb, List<ActionEncoder> possiblyConflictingActions) {
		if (possiblyConflictingActions.size() == 0) {
			return "";
		}

		sb.append(System.lineSeparator());

		for (int i = 0; i < possiblyConflictingActions.size(); i++) {
			ActionEncoder encoder = possiblyConflictingActions.get(i);
			sb.append(System.lineSeparator());
			sb.append("\t(assert ");
			sb.append(System.lineSeparator());
			if (encoder.getRelationType().equals(RelationType.ASSIGN)) {
				String postconditionFlattenSet = (encoder.postconditionFlattenSet).replace("(ASSIGN* {k-1})", PREVIOUS_TRANSITIVE_ASSIGN_TYPE);
				if (encoder.getActionType().equals(ActionType.ADD)) {
					sb.append("\t;POSTCONDITION: " + System.lineSeparator() + "\t(= " + encoder.getActionId()
					+ "  (set.subset" + " " + encoder.postconditionSet + " ASSIGN))");
//					sb.append("\t;POSTCONDITION: " + System.lineSeparator() + "\t(= " + encoder.getActionId()
//							+ " (and (set.subset" + " " + encoder.postconditionSet + " ASSIGN) (set.subset "
//							+ postconditionFlattenSet + " ASSIGN*)))");
				} else if (encoder.getActionType().equals(ActionType.REMOVE)) {
					sb.append("\t;POSTCONDITION TRANSITIVE: " + System.lineSeparator() + "\t(= " + encoder.getActionId()
							+ " (and (not (set.subset" + " " + postconditionFlattenSet
							+ " ASSIGN)) (not (set.subset " + encoder.postconditionSet + " ASSIGN))))");
				}
			} else if (encoder.getRelationType().equals(RelationType.ASSOCIATE)) {
				if (encoder.getActionType().equals(ActionType.ADD)) {
					sb.append("\t;POSTCONDITION: " + System.lineSeparator() + "\t(= " + encoder.getActionId()
							+ " (set.subset" + " " + encoder.postconditionSet + " ASSOC))");
				} else if (encoder.getActionType().equals(ActionType.REMOVE)) {
					sb.append("\t;POSTCONDITION: " + System.lineSeparator() + "\t(= " + encoder.getActionId()
							+ " (not (set.subset" + " " + encoder.postconditionSet + " ASSOC)))");
				}
			}
			sb.append(System.lineSeparator() + "\t)" + System.lineSeparator());
		}
		return sb.toString();
	}

	private String encodeAssignments(List<String> tuples, String type) {
		if (tuples.size() == 1) {
			return "(assert (= " + type + " (set.singleton " + tuples.get(0) + ")))";
		}
		String assignments = "(assert (= " + type + " (set.insert ";
		for (int i = 0; i < tuples.size(); i++) {
			if (i == tuples.size() - 1) {
				assignments += "(set.singleton " + tuples.get(i) + "";
				break;
			}
			assignments += " " + tuples.get(i) + " ";

		}
		return assignments + "))))";
	}

	private String encodeAssociations(List<String> tuples) {
		if (tuples.size() == 1) {
			return "(assert (= ASSOC (set.singleton " + tuples.get(0) + ")))";
		}
		String associations = "(assert (= ASSOC (set.insert ";
		for (int i = 0; i < tuples.size(); i++) {
			if (i == tuples.size() - 1) {
				associations += "(set.singleton " + tuples.get(i) + "";
				break;
			}
			associations += " " + tuples.get(i) + " ";

		}
		return associations + "))))";
	}

	private String generateHeadCode() {
		return "(set-logic QF_ALL)\r\n" + "(set-option :produce-models true)\r\n"
				+ "(set-option :produce-unsat-cores true)\r\n" + "(declare-fun ASSIGN () (Set (Tuple Int Int)))\r\n"
				+ "(declare-fun ASSIGN* () (Set (Tuple Int Int)))\r\n"
				+ "(declare-fun ASSIGN_PREV* () (Set (Tuple Int Int)))\r\n"
				+ "(declare-fun ASSOC () (Set (Tuple Int Int Int)))\r\n";
	}

	private String generateTailCode(String getValueCommands) {
		return "\r\n(check-sat)\r\n" + getValueCommands;
	}

	public Boolean searchForRace(String output) {
		Pattern pattern = Pattern.compile("\\(\\((.*?) (true|false)\\)\\)");
		Matcher matcher = pattern.matcher(output);

		while (matcher.find()) {
			String key = matcher.group(1);
			boolean newValue = Boolean.parseBoolean(matcher.group(2));

			List<Boolean> values = actionPostconditionMap.computeIfAbsent(key, k -> new ArrayList<>());
			if (!values.isEmpty() && !values.contains(newValue)) {
				System.out.println(
						"Race condition found for postconditions of action " + key + ": " + values + " vs. " + newValue);
				values.add(newValue);

				return true;
			}
			values.add(newValue);
		}
		return false;
	}

	public void setConfiguration(String assignments, String transitiveAssignments, String previousTransitiveAssignments,
			String association, List<ObligationEncoder> obligationEncoders, String sequenceName) {
		this._assignments = assignments;
		this._transitiveAssignments = transitiveAssignments;
		this._previousTransitiveAssignments = previousTransitiveAssignments;
		this._association = association;
		this._obligationEncoders = obligationEncoders;
		this._sequenceName = sequenceName;
	}
}
