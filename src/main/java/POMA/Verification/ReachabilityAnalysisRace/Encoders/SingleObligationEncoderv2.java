package POMA.Verification.ReachabilityAnalysisRace.Encoders;

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
import POMA.Verification.ReachabilityAnalysisRace.SMTComposer;
import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.Prerequisite;
import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.Trace;
import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Relations.Trace.TraceType;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.HierarchyType;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class SingleObligationEncoderv2 {

	List<String> _operationSetsAssignAdd = new ArrayList<String>();
	List<String> _operationSetsAssociateAdd = new ArrayList<String>();
	List<String> _operationSetsAssignRemove = new ArrayList<String>();
	List<String> _operationSetsAssociateRemove = new ArrayList<String>();

	private List<Prerequisite> filterInheritedPrerequisites(List<Prerequisite> inheritedPrerequisites,
			RelationType relationType, HierarchyType hierarchyType) {
		return inheritedPrerequisites.stream()
				.filter(ip -> ip.getRelationType().equals(relationType) && ip.getHierarchyType().equals(hierarchyType))
				.collect(Collectors.toList());
	}

	private void preprocessPrerequisites(List<Prerequisite> allPrerequisites, ActionEncoder actionEncoder,
			int actionIndex) {
		actionEncoder.setPrerequisites(filterInheritedPrerequisites(allPrerequisites, actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType()));
		actionEncoder.processPrerequisitesInternally();
		allPrerequisites.add(new Prerequisite(actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType(), actionEncoder.getActionType(),
				actionEncoder.getPostconditionSet(), actionEncoder.getPostconditionFlattenSet(), actionIndex));
	}

	private List<ActionEncoder> preprocessActions(List<Action> actions, HashMap<String, Integer> mapOfIDs, String label) {

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		List<Prerequisite> allPrerequisites = new ArrayList<Prerequisite>();
		for (int i = 0; i < actions.size(); i++) {
			Action action = actions.get(i);
			ActionEncoder actionEncoder;
			if (action instanceof AssignAction) {
				actionEncoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
				String operationSet = label+"_"+"AssignAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_operationSetsAssignAdd.add(operationSet);
			} else if (action instanceof GrantAction) {
				actionEncoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
				String operationSet = label+"_"+"GrantAction_{k}_" + i;
				actionEncoder.operationSet = operationSet;
				_operationSetsAssociateAdd.add(operationSet);
			} else {
				continue;
			}
			preprocessPrerequisites(allPrerequisites, actionEncoder, i); // preprocess for static analysis of
																			// prerequisites

			encodedActions.add(actionEncoder);
		}
		return encodedActions;
	}

	
	private String encodeRelationTransition() {
		String assign = encodeAssignmentTransitions();
		String assoc = "";
//		System.out.println(assign);
		return "\t;RELATION TRANSITION"+ System.lineSeparator() +assign + System.lineSeparator() + assoc;
	}
	
	private String encodeAssignmentTransitions() {
		String assign = "";
		String operationSetEncodingPlus = "";
		
		for(int i=0; i< _operationSetsAssignAdd.size(); i++) {
			String operationSet = _operationSetsAssignAdd.get(i);
			if(i==0) {
				operationSetEncodingPlus += " " + operationSet;
				continue;
			}
			
			operationSetEncodingPlus = "(set.union " + operationSet +" " +operationSetEncodingPlus+")";
			_operationSetsAssignAdd.get(i);
		}
		
		assign = "\t\t(set.union (ASSIGN {k-1}) " + operationSetEncodingPlus + ")";
		if(_operationSetsAssignRemove.size()==0) {
			return "\t(= (ASSIGN {k}) " +System.lineSeparator()+ assign + System.lineSeparator()+"\t)";
		}
		String operationSetEncodingMinus = "";
		for(int i=0; i< _operationSetsAssignRemove.size(); i++) {
			String operationSet = _operationSetsAssignRemove.get(i);
			if(i==0) {
				operationSetEncodingMinus += " " + operationSet;
				continue;
			}
			operationSetEncodingMinus = "\t\t(set.union " + operationSet + " " +operationSetEncodingMinus + ")";
			_operationSetsAssignRemove.get(i);
		}
		
		return "\t(= (ASSIGN {k}) " +System.lineSeparator()+ "\t\t(set.minus "+System.lineSeparator()+assign+" " +System.lineSeparator()+ operationSetEncodingMinus +System.lineSeparator()+  ")"+System.lineSeparator()+")";
	}
	
	private String encodeCompleteTraces(List<ActionEncoder> actionsWithConflict, StringBuilder sb,
			String independentActionsEncoding) {

		for (int i = 0; i < actionsWithConflict.size(); i++) {
			sb.append(System.lineSeparator());
			sb.append("(or");
			sb.append(System.lineSeparator());
			ActionEncoder encoder = actionsWithConflict.get(i);
			processNegatedConditions(sb, encoder);
			// use original condition + action
			sb.append(System.lineSeparator());
			sb.append("(and");
			sb.append(System.lineSeparator());
			processConditions(sb, encoder);
			processPostcondition(sb, encoder);
			sb.append(System.lineSeparator());
			sb.append(")");
			sb.append(System.lineSeparator());
		}

		sb.append(System.lineSeparator() + independentActionsEncoding + System.lineSeparator()+ encodeRelationTransition());
		return sb.toString();
	}

	private void processPostcondition(StringBuilder sb, ActionEncoder actionEncoder) {
		sb.append("\t;POSTCONDITION: " + actionEncoder.operationSet + System.lineSeparator() + "\t(= "
				+ actionEncoder.operationSet + " " + actionEncoder.postcondition + ")");
	}


	private void processConditions(StringBuilder sb, ActionEncoder encoder) {
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

	private void processNegatedConditions(StringBuilder sb, ActionEncoder encoder) {
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
	}

	private String encodeIndependentActions(List<ActionEncoder> actionsNoConflict) {
		String encoding = "\t;independent actions" + System.lineSeparator();

		for (ActionEncoder action : actionsNoConflict) {
			encoding += System.lineSeparator() + "\t(=>" + action.precondition + " (= " + action.operationSet + " "
					+ action.postcondition + "))" + System.lineSeparator();
			encoding += System.lineSeparator() + "\t(=>" + " (= " + action.operationSet + " "
					+action.postcondition + ") " + action.precondition + ")"
					+ System.lineSeparator();
		}

		return encoding;
	}

	private String encodeTraces(List<ActionEncoder> actions) throws Exception {
		List<ActionEncoder> independentActions = actions.stream().filter(action -> action.prerequisites.size() == 0)
				.collect(Collectors.toList());
		List<ActionEncoder> conflictingActions = actions.stream().filter(action -> action.prerequisites.size() > 0)
				.collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		sb.append("(and" + System.lineSeparator());
		String independentActionsEncoding = encodeIndependentActions(independentActions);
		encodeCompleteTraces(conflictingActions, sb, independentActionsEncoding);
		sb.append(")");
		sb.append(System.lineSeparator());
//		sb.append(System.lineSeparator());
//
//		sb.append(")");
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	private String encodeObligation(List<ActionEncoder> convertedActions, String label) throws Exception {
		String traces = encodeTraces(convertedActions);
		
		return processOperatingSets()+System.lineSeparator() + "(assert (=> (= ( " + label + " {k}) true)" + System.lineSeparator()
				+ System.lineSeparator() + traces + System.lineSeparator() + ")" + System.lineSeparator()+  ")";
	}
	
	private String processOperatingSets() {
		String operatingSetsDeclaration = "";
		List<String> operationSetsAssign = new ArrayList<String>();
		operationSetsAssign.addAll(_operationSetsAssignAdd);
		operationSetsAssign.addAll(_operationSetsAssignRemove);
		List<String> operationSetsAssociate = new ArrayList<String>();
		operationSetsAssociate.addAll(_operationSetsAssociateAdd);
		operationSetsAssociate.addAll(_operationSetsAssociateRemove);

		for(String name : operationSetsAssign) {
			operatingSetsDeclaration+=System.lineSeparator() +"(declare-fun "+name+" () (Set (Tuple Int Int)))"+ System.lineSeparator();
		}
		for(String name : operationSetsAssociate) {
			operatingSetsDeclaration+=System.lineSeparator() +"(declare-fun "+name+" () (Set (Tuple Int Int Int)))"+ System.lineSeparator();
		}
		return operatingSetsDeclaration;
	}

	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/1O2Grants.yml")));
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/Assign.yml")));
		Obligation obligation = EVRParser.parse(yml);
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		SingleObligationEncoderv2 soe = new SingleObligationEncoderv2();
		List<ActionEncoder> convertedActions = soe
				.preprocessActions(obligation.getRules().get(0).getResponsePattern().getActions(), mapOfIDs, obligation.getRules().get(0).getLabel());

//		 System.out.println(soe.encodeTraces(convertedActions));
		
		
		
		
		System.out.println(composer.generateHeadCode()); 
		
//		soe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel());
		 System.out.println(soe.encodeObligation(convertedActions, obligation.getRules().get(0).getLabel()));
		
		
		
//		 System.out.println(ActionEncoder.replaceKWithValue(soe.encodeConflictingTraces(convertedActions),
//		 1));
	}

}
