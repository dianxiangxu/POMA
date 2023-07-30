package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisSequential.SMTComposer;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations.Prerequisite;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations.Trace;
import POMA.Verification.ReachabilityAnalysisSequential.ActionsEncoding.Relations.Trace.TraceType;
import POMA.Verification.ReachabilityAnalysisSequential.Encoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisSequential.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class SingleObligationEncoder {

	List<String> _operationSetsAssignAdd = new ArrayList<String>();
	List<String> _operationSetsAssociateAdd = new ArrayList<String>();
	List<String> _operationSetsAssignRemove = new ArrayList<String>();
	List<String> _operationSetsAssociateRemove = new ArrayList<String>();

	
	private List<ActionEncoder> preprocessActions(List<Action> actions, HashMap<String, Integer> mapOfIDs,
			String label) {

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		List<Prerequisite> allPrerequisites = new ArrayList<Prerequisite>();

		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
			ActionEncoder actionEncoder;
			if (action instanceof AssignAction) {
				actionEncoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
				String operationSet = label + "_" + "AssignAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_operationSetsAssignAdd.add(operationSet);
			} else if (action instanceof GrantAction) {
				actionEncoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
				String operationSet = label + "_" + "GrantAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_operationSetsAssociateAdd.add(operationSet);
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


	private String initializeOperatingSets() {
		String operatingSetsDeclaration = "";
		List<String> operationSetsAssign = new ArrayList<String>();
		operationSetsAssign.addAll(_operationSetsAssignAdd);
		operationSetsAssign.addAll(_operationSetsAssignRemove);
		List<String> operationSetsAssociate = new ArrayList<String>();
		operationSetsAssociate.addAll(_operationSetsAssociateAdd);
		operationSetsAssociate.addAll(_operationSetsAssociateRemove);

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

		return initializeOperatingSets() + System.lineSeparator() + "(assert (=> (= ( " + label + " {k}) true)"
				+ System.lineSeparator() + "(and" + System.lineSeparator() + sb.toString()
				+ encodeIndependentActions(independentActions) + ")" + System.lineSeparator() + ")"
				+ System.lineSeparator() + ")" + System.lineSeparator() + encodeRelationTransition()
				+ System.lineSeparator();
	}

	private String encodeIndependentActions(List<ActionEncoder> actionsNoConflict) {
		String encoding = System.lineSeparator() + "\t;INDEPENDENT ACTIONS" + System.lineSeparator();
		encoding += "\t(and" + System.lineSeparator();
		for (ActionEncoder action : actionsNoConflict) {

			encoding += System.lineSeparator() + "\t\t;ACTION: " + action.operationSet + System.lineSeparator() + "\t\t(=>"
					+ action.precondition + " (= " + action.operationSet + " " + action.postconditionSet + "))"
					+ System.lineSeparator();
			encoding += System.lineSeparator() + "\t\t(=>" + action.negatedPrecondition + " (= " + action.operationSet
					+ " " + action.negatedPostconditionSet + "))" + System.lineSeparator();
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
		sb.append("\t;POSTCONDITION: " + encoder.operationSet + System.lineSeparator() + "\t(= "
				+ encoder.operationSet + " " + encoder.postconditionSet + ")");
	}

	private String encodeRelationTransition() {
		String assign = encodeAssignmentTransition();
		String assoc = encodeAssociationTransition();
		return System.lineSeparator() + ";RELATION TRANSITION ENCODING" + System.lineSeparator() + assign
				+ System.lineSeparator() + assoc;
	}

	private String encodeAssignmentTransition() {
		String assign = "";
		String operationSetEncodingPlus = "";

		for (int i = 0; i < _operationSetsAssignAdd.size(); i++) {
			String operationSet = _operationSetsAssignAdd.get(i);
			if (i == 0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}

			operationSetEncodingPlus = "(set.union " + operationSet + " " + operationSetEncodingPlus + ")";
			_operationSetsAssignAdd.get(i);
		}

		assign = "\t(set.union (ASSIGN {k-1}) " + operationSetEncodingPlus + ")";
		if (_operationSetsAssignRemove.size() == 0) {
			return "(assert (= (ASSIGN {k}) " + System.lineSeparator() + assign + System.lineSeparator() + "))";
		}
		String operationSetEncodingMinus = "";
		for (int i = 0; i < _operationSetsAssignRemove.size(); i++) {
			String operationSet = _operationSetsAssignRemove.get(i);
			if (i == 0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t(set.union " + operationSet + " " + operationSetEncodingMinus + ")";
			_operationSetsAssignRemove.get(i);
		}

		return "(assert (= (ASSIGN {k}) " + System.lineSeparator() + "\t(set.minus " + System.lineSeparator() + assign
				+ " " + System.lineSeparator() + operationSetEncodingMinus + System.lineSeparator() + ")"
				+ System.lineSeparator() + "))";
	}

	private String encodeAssociationTransition() {
		String associate = "";
		String operationSetEncodingPlus = "";

		for (int i = 0; i < _operationSetsAssociateAdd.size(); i++) {
			String operationSet = _operationSetsAssociateAdd.get(i);
			if (i == 0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}

			operationSetEncodingPlus = "(set.union " + operationSet + " " + operationSetEncodingPlus + ")";
			_operationSetsAssociateAdd.get(i);
		}

		associate = "\t(set.union (ASSOC {k-1}) " + operationSetEncodingPlus + ")";
		if (_operationSetsAssociateRemove.size() == 0) {
			return "(assert (= (ASSOC {k}) " + System.lineSeparator() + associate + System.lineSeparator() + "))";
		}
		String operationSetEncodingMinus = "";
		for (int i = 0; i < _operationSetsAssignRemove.size(); i++) {
			String operationSet = _operationSetsAssignRemove.get(i);
			if (i == 0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t(set.union " + operationSet + " " + operationSetEncodingMinus + ")";
			_operationSetsAssignRemove.get(i);
		}

		return "(assert (= (ASSOC {k}) " + System.lineSeparator() + "\t(set.minus " + System.lineSeparator() + associate
				+ " " + System.lineSeparator() + operationSetEncodingMinus + System.lineSeparator() + ")"
				+ System.lineSeparator() + "))";
	}	
	
	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/1O2Grants.yml")));
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/Assign.yml")));
		Obligation obligation = EVRParser.parse(yml);
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		SingleObligationEncoder soe = new SingleObligationEncoder();
		List<ActionEncoder> convertedActions = soe.preprocessActions(
				obligation.getRules().get(0).getResponsePattern().getActions(), mapOfIDs,
				obligation.getRules().get(0).getLabel());

//		 System.out.println(soe.encodeTraces(convertedActions));

		System.out.println(composer.generateHeadCode());

//		soe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel());
//		System.out.println(soe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel()));

		System.out.println(ActionEncoder
				.replaceKWithValue(soe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel()), 1));
	}

}
