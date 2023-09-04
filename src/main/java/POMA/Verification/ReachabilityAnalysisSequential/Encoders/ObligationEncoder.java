package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.ActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.AssignActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.DeleteAssignActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.DeleteGrantActionEncoder;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoders.GrantActionEncoder;
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
	 boolean isAssignRelated = false;
	 boolean isAssociateRelated = false;

	private List<ActionEncoder> preprocessActions(List<Action> actions, HashMap<String, Integer> mapOfIDs,
			String label) {

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		List<Prerequisite> allPrerequisites = new ArrayList<Prerequisite>();

		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
			if(isActionAssignmentRelated(action)) {
				isAssignRelated = true;
			}
			if(isActionAssociationRelated(action)) {
				isAssociateRelated = true;
			}
			ActionEncoder actionEncoder;
			if (action instanceof AssignAction) {
				actionEncoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
				String operationSet = label + "_" + "AssignAction_{k}_" + i;
				String operationSetFlat = label + "_" + "AssignAction_{k}_" + i + "_*";
				actionEncoder.operationSet = operationSet;
				actionEncoder.operationSetFlat = operationSetFlat;
				_actionSetsAssignAdd.add(operationSet);
				_actionSetsAssignAddFlat.add(operationSetFlat);
			} else if (action instanceof GrantAction) {
				actionEncoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
				String operationSet = label + "_" + "GrantAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_actionSetsAssociateAdd.add(operationSet);
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
				} else if (deleteAction.getAssociations() != null) {
					actionEncoder = new DeleteGrantActionEncoder(deleteAction, mapOfIDs);
					String operationSet = label + "_" + "DeleteGrantAction_{k}_" + i;
					actionEncoder.operationSet = operationSet;
					_actionSetsAssociateRemove.add(operationSet);
				} else {
					continue;
				}

			} else {
				continue;
			}
			preprocessPrerequisites(allPrerequisites, actionEncoder, i);
			encodedActions.add(actionEncoder);
		}
		return encodedActions;
	}

	private void preprocessPrerequisites(List<Prerequisite> allPrerequisites, ActionEncoder actionEncoder,
			int actionIndex) {
		actionEncoder.setPrerequisites(filterPrerequisites(allPrerequisites, actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType()));
		actionEncoder.processPrerequisitesInternally();
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
		String operatingSetsDeclaration = "";
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
		return operatingSetsDeclaration;
	}

	private String encodeObligation(List<ActionEncoder> actions, String label) throws Exception {
		StringBuilder sb = new StringBuilder();

		List<ActionEncoder> independentActions = actions.stream().filter(action -> action.prerequisites.size() == 0)
				.collect(Collectors.toList());
		List<ActionEncoder> conflictingActions = actions.stream().filter(action -> action.prerequisites.size() > 0)
				.collect(Collectors.toList());
		encodeConflictingActions(sb, conflictingActions);

		return initializeActionSets() + System.lineSeparator() + "(assert (=> (= ( " + label + " {k-1}) true)"
				+ System.lineSeparator() + "(and" + System.lineSeparator() + sb.toString()
				+ encodeIndependentActions(independentActions) + ")" + System.lineSeparator() + ")"
				+ System.lineSeparator() + ")"  
				+ encodeEventNegation(label)
//				+ System.lineSeparator() 
//				+encodeRelationTransition()
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
		for(String operationSet : operationSetsAssign) {
			eventNegation += "(= " +operationSet+ " (as set.empty (Set (Tuple Int Int))))";
		}
		for(String operationSet : operationSetsAssociate) {
			eventNegation += "(= " +operationSet+ " (as set.empty (Set (Tuple Int Int Int))))";
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
				+ encoder.operationSet + " " + encoder.negatedPostconditionSet + ")");
		sb.append(System.lineSeparator() + "\t)" + System.lineSeparator());
	}

	private void encodePostcondition(StringBuilder sb, ActionEncoder encoder) {
		sb.append("\t;POSTCONDITION: " + encoder.operationSet + System.lineSeparator() + "\t(= " + encoder.operationSet
				+ " " + encoder.postconditionSet + ")");
		sb.append("\t;POSTCONDITION FLATTEN: " + encoder.operationSetFlat + System.lineSeparator() + "\t(= " + encoder.operationSetFlat
				+ " " + encoder.postconditionFlattenSet + ")");
	}

	public String encodeObligation(Rule obligation, HashMap<String, Integer> mapOfIDs, int k) {
		List<ActionEncoder> convertedActions = preprocessActions(obligation.getResponsePattern().getActions(),
				mapOfIDs, obligation.getLabel());
		try {
			return ActionEncoder.replaceKWithValue(encodeObligation(convertedActions, obligation.getLabel()), k);
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


	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/Assign.yml")));
		Obligation obligation = EVRParser.parse(yml);
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		ObligationEncoder oe = new ObligationEncoder();
		List<ActionEncoder> convertedActions = oe.preprocessActions(
				obligation.getRules().get(0).getResponsePattern().getActions(), mapOfIDs,
				obligation.getRules().get(0).getLabel());

		System.out.println(composer.generateHeadCode());

		System.out.println(ActionEncoder
				.replaceKWithValue(oe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel()), 1));
	}

}
