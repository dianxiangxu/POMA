package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisSequential.FOLparser.model.terms.ITerm;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.AssignActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.DeleteAssignActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.DeleteGrantActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.GrantActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions.CustomObligation;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.CustomActions.CustomAction;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder.RelationType;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.Prerequisite;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class ObligationEncoder {

	List<String> _actionSetsAssignAdd = new ArrayList<String>();
	List<String> _actionSetsAssignAddFlat = new ArrayList<String>();
	List<String> _actionSetsAssociateAdd = new ArrayList<String>();
	List<String> _actionSetsAssociateRemove = new ArrayList<String>();
	List<String> _actionSetsAssignRemove = new ArrayList<String>();
	List<String> _actionSetsAssignRemoveFlat = new ArrayList<String>();
	List<ActionEncoder> _actionEncoders = new ArrayList<ActionEncoder>();
	String _label;
	boolean isAssignRelated = false;
	boolean isAssociateRelated = false;

	String _customizationPath = "";

	public void setCustomizationPath(String _customizationPath) {
		this._customizationPath = _customizationPath;
	}

	List<String> _customActionVariables = new ArrayList<String>();

	List<String> _customAxioms = new ArrayList<String>();

	String raceConditionEncoding = "";
	List<String> conditionalInterferenceVariables = new ArrayList<String>();

	public List<String> getConditionalInterferenceVariables() {
		return conditionalInterferenceVariables;
	}

	private List<ActionEncoder> preprocessActions(List<Action> actions, HashMap<String, Integer> mapOfIDs, String label,
			CustomObligation co) {

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		List<Prerequisite> allPrerequisites = new ArrayList<Prerequisite>();

		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
			int actionIndex = i;
			ActionEncoder actionEncoder;

			if (co != null) {
				List<CustomAction> customActions = co.getActions();
				Optional<CustomAction> foundCustomAction = customActions.stream()
						.filter(a -> a.getIndex() == actionIndex).findFirst();

				if (foundCustomAction.isPresent()) {
					CustomAction customAction = foundCustomAction.get();
					try {
						actionEncoder = convertCustomActionToEncoder(customAction, action, mapOfIDs, label,
								co.getAxiom(mapOfIDs), i);
						encodedActions.add(actionEncoder);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
				}
			}
			if (isActionAssignmentRelated(action)) {
				isAssignRelated = true;
			}
			if (isActionAssociationRelated(action)) {
				isAssociateRelated = true;
			}

			if (action instanceof AssignAction) {
				actionEncoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
				String operationSet = label + "_" + "AssignAction_{k}_" + i;
				String operationSetFlat = label + "_" + "AssignAction_{k}_" + i + "_*";
				actionEncoder.operationSet = operationSet;
				actionEncoder.operationSetFlat = operationSetFlat;
				_actionSetsAssignAdd.add(operationSet);
				_actionSetsAssignAddFlat.add(operationSetFlat);
				actionEncoder.setActionId(label + "_" + "AssignAction_{k}_" + i);
			} else if (action instanceof GrantAction) {
				actionEncoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
				String operationSet = label + "_" + "GrantAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_actionSetsAssociateAdd.add(operationSet);
				actionEncoder.setActionId(label + "_" + "GrantAction_{k}_" + i);
			} else if (action instanceof DeleteAction) {
				DeleteAction deleteAction = (DeleteAction) action;
				if (deleteAction.getAssignments() != null) {
					actionEncoder = new DeleteAssignActionEncoder(deleteAction, mapOfIDs);
					String operationSet = label + "_" + "DeleteAssignAction_{k}_" + i;
					actionEncoder.operationSet = operationSet;
					String operationSetFlat = label + "_" + "DeleteAssignAction_{k}_" + i + "_*";
					actionEncoder.operationSet = operationSet;
					actionEncoder.operationSetFlat = operationSetFlat;
					_actionSetsAssignRemove.add(operationSet);
					_actionSetsAssignRemoveFlat.add(operationSetFlat);
					actionEncoder.setActionId(label + "_" + "DeleteAssignAction_{k}_" + i);
				} else if (deleteAction.getAssociations() != null) {
					actionEncoder = new DeleteGrantActionEncoder(deleteAction, mapOfIDs);
					String operationSet = label + "_" + "DeleteGrantAction_{k}_" + i;
					actionEncoder.operationSet = operationSet;
					_actionSetsAssociateRemove.add(operationSet);
					actionEncoder.setActionId(label + "_" + "DeleteGrantAction_{k}_" + i);
				} else {
					continue;
				}

			} else {
				continue;
			}
			preprocessPrerequisites(allPrerequisites, actionEncoder, i);
			encodedActions.add(actionEncoder);
			encodeRaceConditionVariables(actionEncoder.getConditionalInterference());
		}
		return encodedActions;
	}

	private void encodeRaceConditionVariables(HashMap<String, List<String>> conditionalInterference) {
		if (conditionalInterference.keySet().size() == 0) {
			return;
		}
		raceConditionEncoding += System.lineSeparator() + ";RACE CONDITION VARIABLES ENCODING";

		for (String key : conditionalInterference.keySet()) {
			String civariable = "RC_" + key; 
			raceConditionEncoding += System.lineSeparator() + "(declare-fun " + civariable + " () Bool)"
					+ System.lineSeparator();
			List<String> interferences = conditionalInterference.get(key);
			raceConditionEncoding += "(assert (= " + civariable + System.lineSeparator() +"(not "+ System.lineSeparator();

			if (interferences.size() > 1) {
				raceConditionEncoding += System.lineSeparator() + "(and " + System.lineSeparator();
				for (String interference : interferences) {
					raceConditionEncoding += interference + System.lineSeparator();
				}
				raceConditionEncoding += ")" + System.lineSeparator();
			} else {
				raceConditionEncoding += System.lineSeparator()+interferences.get(0) + System.lineSeparator();
			}
			conditionalInterferenceVariables.add(civariable);
		}

		raceConditionEncoding += ")" + System.lineSeparator() + ")" + System.lineSeparator() + ")";
	}

	private ActionEncoder convertCustomActionToEncoder(CustomAction customAction, Action action,
			HashMap<String, Integer> mapOfIDs, String label, String query, int i) throws Exception {
		List<ITerm> tuple = customAction.getActionTuple();
		ActionEncoder encoder = null;
		String customConditionEncoding = null;
		if (customAction.getCondition() != null) {
			customConditionEncoding = customAction.getConditionEncoding(mapOfIDs);
			extractCustomVariables(customConditionEncoding);
		}
		if (customAction.getAction().toLowerCase().contains("assign")) {
			String ancestor = tuple.get(0).getElement();
			String descendant = tuple.get(1).getElement();

			if (ancestor.startsWith("?")) {
				ancestor = ancestor + "_" + "customvar_{k}";
				if (!_customActionVariables.contains(ancestor))
					_customActionVariables.add(ancestor);
			} else {
				ancestor = mapOfIDs.get(ancestor).toString();
			}
			if (descendant.startsWith("?")) {
				descendant = descendant + "_" + "customvar_{k}";
				if (!_customActionVariables.contains(descendant))
					_customActionVariables.add(descendant);
			} else {
				descendant = mapOfIDs.get(descendant).toString();
			}
			isAssignRelated = true;
			String operationSet = label + "_" + "AssignAction_{k}_" + i;
			String operationSetFlat = label + "_" + "AssignAction_{k}_" + i + "_*";

			encoder = new AssignActionEncoder(
					new POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.AssignmentCustom(
							ancestor, descendant, "", ""),
					mapOfIDs, customConditionEncoding);
			encoder.operationSet = operationSet;
			encoder.operationSetFlat = operationSetFlat;
			_actionSetsAssignAdd.add(operationSet);
			_actionSetsAssignAddFlat.add(operationSetFlat);
		} else if (customAction.getAction().toLowerCase().contains("associate")) {
			String source = tuple.get(0).getElement();
			String ar = tuple.get(1).getElement();
			String target = tuple.get(2).getElement();

			if (source.startsWith("?")) {
				source = source + "_" + "customvar_{k}";
				if (!_customActionVariables.contains(source))
					_customActionVariables.add(source);
			} else {
				source = mapOfIDs.get(source).toString();
			}
			if (target.startsWith("?")) {
				target = target + "_" + "customvar_{k}";
				if (!_customActionVariables.contains(target))
					_customActionVariables.add(target);
			} else {
				target = mapOfIDs.get(target).toString();
			}
			isAssociateRelated = true;
			String operationSet = label + "_" + "GrantAction_{k}_" + i;

			encoder = new GrantActionEncoder(
					new POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.Relations.AssociationCustom(
							source, target, "", List.of(mapOfIDs.get(ar))),
					mapOfIDs, customConditionEncoding);
			encoder.operationSet = operationSet;
			_actionSetsAssociateAdd.add(operationSet);
		}
		extractCustomVariables(query);

		return encoder;
	}

	public void extractCustomVariables(String input) {
		Pattern pattern = Pattern.compile("\\?\\w+_\\{k\\}");
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			String customVariable = matcher.group();
			if (!_customActionVariables.contains(customVariable)) {
				_customActionVariables.add(customVariable);
			}
		}
	}

	private List<CustomObligation> processCustomObligations(String pathToCustomization) {
		try {
			String content = new String(Files.readAllBytes(Paths.get(pathToCustomization)));
			if (content == null || content.trim().isEmpty()) {
				return null;
			}
			List<CustomObligation> customObligations = new ArrayList<CustomObligation>();
			CustomObligation obligation = null;
			List<CustomAction> actions = new ArrayList<CustomAction>();
			String[] lines = content.split("\n");
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i].trim();
				if (line.startsWith("CustomFunctions:")) {
					if (obligation != null) {
						obligation.setActions(actions);
						customObligations.add(obligation);
						actions = new ArrayList<CustomAction>();
					}
					obligation = new CustomObligation();
					obligation.setLabel(line.split(":")[1].trim());
				} else if (line.startsWith("query:")) {
					obligation.setAxiom(line.split(":")[1].trim());
				} else if (line.startsWith("action_")) {
					int endIndex = line.indexOf(':');
					int actionIndex = Integer.parseInt(line.substring("action_".length(), endIndex));

					CustomAction action = new CustomAction();
					action.setIndex(actionIndex);

					if (lines[i + 1].contains("condition:")) {
						action.setCondition(lines[++i].split(":")[1].trim());
					}
					action.setAction(lines[++i].split(":")[1].trim());
					actions.add(action);
				}

			}

			obligation.setActions(actions);
			customObligations.add(obligation);
			return customObligations;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void preprocessPrerequisites(List<Prerequisite> allPrerequisites, ActionEncoder actionEncoder,
			int actionIndex) {
		actionEncoder.setPrerequisites(filterPrerequisites(allPrerequisites, actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType()));
		actionEncoder.processPrerequisites();
		allPrerequisites.add(new Prerequisite(actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType(), actionEncoder.getActionType(),
				actionEncoder.getPostconditionSet(), actionEncoder.getPostconditionFlattenSet(), actionIndex));
	}

	private List<Prerequisite> filterPrerequisites(List<Prerequisite> prerequisites, RelationType relationType,
			HierarchyType hierarchyType) {
		return prerequisites.stream()
				.filter(p -> p.getRelationType().equals(relationType) && p.getHierarchyType().equals(hierarchyType))
				.collect(Collectors.toList());
	}

	private String initializeActionSets() {
		String operatingSetsDeclaration = ";Configuration Modification Sets";
		List<String> operationSetsAssign = new ArrayList<String>();
		operationSetsAssign.addAll(_actionSetsAssignAdd);
		operationSetsAssign.addAll(_actionSetsAssignRemove);
		operationSetsAssign.addAll(_actionSetsAssignAddFlat);
		operationSetsAssign.addAll(_actionSetsAssignRemoveFlat);

		List<String> operationSetsAssociate = new ArrayList<String>();
		operationSetsAssociate.addAll(_actionSetsAssociateAdd);
		operationSetsAssociate.addAll(_actionSetsAssociateRemove);

		for (String name : operationSetsAssign) {
			operatingSetsDeclaration += System.lineSeparator() + "(declare-fun " + name + " () (Set (Tuple Int Int)))"
					+ System.lineSeparator();
		}
		for (String name : operationSetsAssociate) {
			operatingSetsDeclaration += System.lineSeparator() + "(declare-fun " + name
					+ " () (Set (Tuple Int Int Int)))" + System.lineSeparator();
		}

		if (_customActionVariables.size() > 0) {
			operatingSetsDeclaration += System.lineSeparator() + ";Custom Variables";
			for (String name : _customActionVariables) {
				operatingSetsDeclaration += System.lineSeparator() + "(declare-fun " + name + " () Int)"
						+ System.lineSeparator();
			}

		}

		return operatingSetsDeclaration;
	}

	private String encodeObligation(List<ActionEncoder> actions, String label, CustomObligation co,
			HashMap<String, Integer> mapOfIDs, int k) throws Exception {
		StringBuilder sb = new StringBuilder();

		List<ActionEncoder> independentActions = actions.stream().filter(action -> action.prerequisites.size() == 0)
				.collect(Collectors.toList());
		List<ActionEncoder> conflictingActions = actions.stream().filter(action -> action.prerequisites.size() > 0)
				.collect(Collectors.toList());
		encodeConflictingActions(sb, conflictingActions);
		_actionEncoders = actions;
		String customAxiom = "";
		if (co != null) {
			customAxiom = encodeObligationAxiom(label, co.getAxiom(mapOfIDs), k);
		}
		return initializeActionSets() + customAxiom + System.lineSeparator() + "(assert (=> (= ( " + label
				+ " {k-1}) true)" + System.lineSeparator() + "(and" + System.lineSeparator() + sb.toString()
				+ encodeIndependentActions(independentActions) + ")" + System.lineSeparator() + ")"
				+ System.lineSeparator() + ")" + encodeEventNegation(label) + System.lineSeparator() + raceConditionEncoding;
	}

	private String encodeObligationAxiom(String label, String axiom, int k) throws Exception {
		axiom = "(assert (=> (= ( " + label + " {k-1}) true)" + axiom + "))";
		return System.lineSeparator() + ";AXIOM" + System.lineSeparator() + ActionEncoder.replaceKWithValue(axiom, k)
				+ System.lineSeparator();
	}

	private String encodeEventNegation(String label) {
		String eventNegation = "(assert (=> (= ( " + label + " {k-1}) false) (and";
		List<String> operationSetsAssign = new ArrayList<String>();
		List<String> operationSetsAssociate = new ArrayList<String>();

		operationSetsAssign.addAll(_actionSetsAssignAdd);
		operationSetsAssign.addAll(_actionSetsAssignRemove);
		operationSetsAssign.addAll(_actionSetsAssignAddFlat);
		operationSetsAssign.addAll(_actionSetsAssignRemoveFlat);
		operationSetsAssociate.addAll(_actionSetsAssociateAdd);
		operationSetsAssociate.addAll(_actionSetsAssociateRemove);
		for (String operationSet : operationSetsAssign) {
			eventNegation += "(= " + operationSet + " (as set.empty (Set (Tuple Int Int))))";
		}
		for (String operationSet : operationSetsAssociate) {
			eventNegation += "(= " + operationSet + " (as set.empty (Set (Tuple Int Int Int))))";
		}

		eventNegation += ")))";
		return eventNegation;
	}

	private String encodeIndependentActions(List<ActionEncoder> actionsNoConflict) {
		String encoding = System.lineSeparator() + "\t;INDEPENDENT ACTIONS" + System.lineSeparator();
		encoding += "\t(and" + System.lineSeparator();
		for (ActionEncoder action : actionsNoConflict) {

			if (action.getRelationType() == RelationType.ASSIGN) {
				encoding += System.lineSeparator() + "\t\t;ACTION: " + action.operationSet + System.lineSeparator()
						+ "\t\t(=>" + action.precondition + " (and (= " + action.operationSet + " "
						+ action.postconditionSet + ") (= " + action.operationSetFlat + " "
						+ action.postconditionFlattenSet + ")))" + System.lineSeparator();
				encoding += System.lineSeparator() + "\t\t(=>" + action.negatedPrecondition + " (and (= "
						+ action.operationSet + " " + action.negatedPostconditionSet + ") (= " + action.operationSetFlat
						+ " " + action.negatedPostconditionSet + ")))" + System.lineSeparator();
			} else {
				encoding += System.lineSeparator() + "\t\t;ACTION: " + action.operationSet + System.lineSeparator()
						+ "\t\t(=>" + action.precondition + " (= " + action.operationSet + " " + action.postconditionSet
						+ "))" + System.lineSeparator();
				encoding += System.lineSeparator() + "\t\t(=>" + action.negatedPrecondition + " (= "
						+ action.operationSet + " " + action.negatedPostconditionSet + "))" + System.lineSeparator();
			}
		}
		encoding += System.lineSeparator() + "\t)" + System.lineSeparator();
		return encoding;
	}

	private String encodeConflictingActions(StringBuilder sb, List<ActionEncoder> actionsWithConflict) {
		if (actionsWithConflict.size() == 0) {
			return "";
		}

		sb.append(System.lineSeparator());
		
		for (int i = 0; i < actionsWithConflict.size(); i++) {
			sb.append(System.lineSeparator());
			sb.append("(or");
			sb.append(System.lineSeparator());
			ActionEncoder encoder = actionsWithConflict.get(i);
			encodeNegatedConditions(sb, encoder);
			// use original condition + action
			sb.append(System.lineSeparator());
			sb.append("\t(and");
			sb.append(System.lineSeparator());
			encodeConditions(sb, encoder);
			encodePostcondition(sb, encoder);
			sb.append(System.lineSeparator());
			sb.append("\t)");
			sb.append(System.lineSeparator());
			sb.append(")");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	private void encodeConditions(StringBuilder sb, ActionEncoder encoder) {
		if (!encoder.getCondition().isEmpty()) {
			sb.append("\t;CONDITION: " + encoder.operationSet);
			sb.append(System.lineSeparator());
			sb.append(encoder.getCondition());
			sb.append(System.lineSeparator());
		}
		if (!encoder.getPrecondition().isEmpty()) {
			sb.append("\t;PRECONDITION: " + encoder.operationSet);
			sb.append(System.lineSeparator());
			sb.append(encoder.getPrecondition());
			sb.append(System.lineSeparator());
		}
	}

	private void encodeNegatedConditions(StringBuilder sb, ActionEncoder encoder) {
		sb.append("\t(and" + System.lineSeparator());
		if (!encoder.getNegatedPrecondition().isEmpty()) {
			sb.append("\t;NEGATED PRECONDITION: " + encoder.operationSet);
			sb.append(System.lineSeparator());
			sb.append(encoder.getNegatedPrecondition());
			sb.append(System.lineSeparator());
		}
		if (!encoder.getNegatedCondition().isEmpty()) {
			sb.append("\t;NEGATED CONDITION: " + encoder.operationSet);
			sb.append(System.lineSeparator());
			sb.append(encoder.getNegatedCondition());
			sb.append(System.lineSeparator());
		}
		sb.append("\t;NEGATED POSTCONDITION: " + encoder.operationSet + System.lineSeparator() + "\t(= "
				+ encoder.operationSet + " " + encoder.negatedPostconditionSet + "\t)");
		sb.append(System.lineSeparator() + "\t)" + System.lineSeparator());
	}

	private void encodePostcondition(StringBuilder sb, ActionEncoder encoder) {
		sb.append("\t;POSTCONDITION: " + encoder.operationSet + System.lineSeparator() + "\t(= " + encoder.operationSet
				+ " " + encoder.postconditionSet + ")");
		if (!encoder.postconditionFlattenSet.isEmpty()) {
			sb.append(System.lineSeparator() + "\t;POSTCONDITION FLATTEN: " + encoder.operationSetFlat
					+ System.lineSeparator() + "\t(= " + encoder.operationSetFlat + " "
					+ encoder.postconditionFlattenSet + ")");
		}
	}

	public String encodeObligation(Rule obligation, HashMap<String, Integer> mapOfIDs, int k) {
		List<CustomObligation> customObligations;
		_label = obligation.getLabel();
		CustomObligation co = null;
		if (!_customizationPath.isEmpty()) {
			customObligations = processCustomObligations(_customizationPath);
			customObligations = customObligations.stream().filter(obj -> obligation.getLabel().equals(obj.getLabel()))
					.collect(Collectors.toList());
			if (customObligations.size() > 0) {
				co = customObligations.get(0);
			}
		}

		List<ActionEncoder> convertedActions = preprocessActions(obligation.getResponsePattern().getActions(), mapOfIDs,
				obligation.getLabel(), co);
		try {
			return ActionEncoder
					.replaceKWithValue(encodeObligation(convertedActions, obligation.getLabel(), co, mapOfIDs, k), k);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	boolean isActionAssignmentRelated(Action a) {
		if (a instanceof AssignAction || a instanceof CreateAction) {
			return true;
		}
		if (a instanceof DeleteAction && ((DeleteAction) a).getAssignments() != null) {
			return true;
		}
		return false;
	}

	boolean isActionAssociationRelated(Action a) {
		if (a instanceof GrantAction) {
			return true;
		}
		if (a instanceof DeleteAction && ((DeleteAction) a).getAssociations() != null) {
			return true;
		}
		return false;
	}

//
//	public static void main(String[] args) throws Exception {
//		Graph graph = Utils.readAnyGraph("Policies/TEST/ADDCOPI/Graph.json");
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/ADDCOPI/AddCoPI.yml")));
//		Obligation obligation = EVRParser.parse(yml);
//		SMTComposer composer = new SMTComposer(graph, obligation, "");
//		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
//		ObligationEncoder oe = new ObligationEncoder();
////		CustomObligation co = oe.processCustomObligation("Policies/TEST/ADDCOPI/customization_v2.txt");	
//
////		List<ActionEncoder> convertedActions = oe.preprocessActions(
////				obligation.getRules().get(0).getResponsePattern().getActions(), mapOfIDs,
////				obligation.getRules().get(0).getLabel(), co);
//
////		System.out.println(composer.generateHeadCode());
////
////		System.out.println(ActionEncoder
////				.replaceKWithValue(oe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel()), 1));
//		return;
//
//	}
	public List<String> getCustomActionVariables() {
		return _customActionVariables;
	}

	public void set_customActionVariables(List<String> _customActionVariables) {
		this._customActionVariables = _customActionVariables;
	}
}
