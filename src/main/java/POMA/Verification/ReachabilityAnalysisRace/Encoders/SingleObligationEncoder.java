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

public class SingleObligationEncoder {

	private List<Prerequisite> filterInheritedPrerequisites(List<Prerequisite> inheritedPrerequisites,
			RelationType relationType, HierarchyType hierarchyType) {
		return inheritedPrerequisites.stream()
				.filter(ip -> ip.getRelationType().equals(relationType) && ip.getHierarchyType().equals(hierarchyType))
				.collect(Collectors.toList());
	}

	private void preprocessPrerequisites(List<Prerequisite> allPrerequisites, ActionEncoder actionEncoder, int actionIndex) {
		actionEncoder.setPrerequisites(filterInheritedPrerequisites(allPrerequisites,
				actionEncoder.getRelationType(), actionEncoder.getPostconditionHierarchyType()));
		actionEncoder.processPrerequisitesInternally();
		allPrerequisites.add(new Prerequisite(actionEncoder.getRelationType(),
				actionEncoder.getPostconditionHierarchyType(), actionEncoder.getActionType(),
				actionEncoder.getPostconditionSet(), actionEncoder.getPostconditionFlattenSet(), actionIndex));
	}

	private List<ActionEncoder> preprocessActions(List<Action> actions, HashMap<String, Integer> mapOfIDs) {
		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		List<Prerequisite> allPrerequisites = new ArrayList<Prerequisite>();
		for (int i=0; i<actions.size(); i++) {
			Action action = actions.get(i);
			ActionEncoder actionEncoder;
			if (action instanceof AssignAction) {
				actionEncoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
			} else if (action instanceof GrantAction) {
				actionEncoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
			} else {
				continue;
			}
			preprocessPrerequisites(allPrerequisites, actionEncoder, i); //preprocess for static analysis of prerequisites
			
			encodedActions.add(actionEncoder);
		}
		return encodedActions;
	}

	private String encodeTraces(List<ActionEncoder> actionEncoders) {
		// get a set with a range of actions indexes
		Set<Integer> range = IntStream.range(0, actionEncoders.size()).boxed().collect(Collectors.toSet());
		Set<Set<Integer>> powerSet = Sets.powerSet(range);

		StringBuilder sb = new StringBuilder();
		sb.append("(or");
		sb.append(System.lineSeparator());

		for (Set<Integer> set : powerSet) {
			sb.append(";"+set);
			sb.append(System.lineSeparator());
			sb.append("(and");
			sb.append(System.lineSeparator());
			String ASSIGNActions = "";
			String ASSIGNFlattenActions = "";
			String ASSOCIATEActions = "";
			
			
			
			for (int i = 0; i < actionEncoders.size(); i++) {
				ActionEncoder encoder = actionEncoders.get(i);

				if (set.contains(i)) {
					processNegatedConditions(sb, i, encoder);
				} else {
					// use original condition + action
					processConditions(sb, i, encoder);

					if (encoder.getRelationType().equals(RelationType.ASSIGN)) {
						ASSIGNActions = processAssignAction(ASSIGNActions, encoder.getPostcondition());
						ASSIGNFlattenActions = processAssignFlattenAction(ASSIGNFlattenActions,
								encoder.getPostconditionFlatten());
					} else if (encoder.getRelationType().equals(RelationType.ASSOCIATE)) {
						ASSOCIATEActions = processAssociateAction(ASSOCIATEActions, encoder.getPostcondition());
					}
				}				
			}
			processPostconditions(sb, set, ASSIGNActions, ASSIGNFlattenActions, ASSOCIATEActions);
			
			sb.append(")");
			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
		}

		sb.append(")");
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	private String encodeSingleObligationNoConflicts(List<ActionEncoder> actionEncoders) {
		Set<Integer> range = IntStream.range(0, actionEncoders.size()).boxed().collect(Collectors.toSet());
		Set<Set<Integer>> powerSet = Sets.powerSet(range);

		StringBuilder sb = new StringBuilder();
		sb.append("(or");
		sb.append(System.lineSeparator());

		for (Set<Integer> set : powerSet) {
			sb.append(";"+set);
			sb.append(System.lineSeparator());
			sb.append("(and");
			sb.append(System.lineSeparator());
			String ASSIGNActions = "";
			String ASSIGNFlattenActions = "";
			String ASSOCIATEActions = "";
			
			
			
			for (int i = 0; i < actionEncoders.size(); i++) {
				ActionEncoder encoder = actionEncoders.get(i);

				if (set.contains(i)) {
					processNegatedConditions(sb, i, encoder);
				} else {
					// use original condition + action
					processConditions(sb, i, encoder);

					if (encoder.getRelationType().equals(RelationType.ASSIGN)) {
						ASSIGNActions = processAssignAction(ASSIGNActions, encoder.getPostcondition());
						ASSIGNFlattenActions = processAssignFlattenAction(ASSIGNFlattenActions,
								encoder.getPostconditionFlatten());
					} else if (encoder.getRelationType().equals(RelationType.ASSOCIATE)) {
						ASSOCIATEActions = processAssociateAction(ASSOCIATEActions, encoder.getPostcondition());
					}
				}				
			}
			processPostconditions(sb, set, ASSIGNActions, ASSIGNFlattenActions, ASSOCIATEActions);
			
			sb.append(")");
			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
		}

		sb.append(")");
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	
	
	
	private void processPostconditions(StringBuilder sb, Set<Integer> set, String ASSIGNActions,
			String ASSIGNFlattenActions, String ASSOCIATEActions) {
		if (!ASSIGNActions.isEmpty()) {
			sb.append(";ASSIGN ACTION(S): " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSIGN {k}) " + ASSIGNActions + ")");
			sb.append(System.lineSeparator());

			sb.append(";ASSIGN FLATTEN ACTION(S): " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSIGN* {k}) " + ASSIGNFlattenActions + ")");
			sb.append(System.lineSeparator());
		}
		else {
			sb.append(";ASSIGN ACTION-EMPTY: " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSIGN {k}) (ASSIGN {k-1}))");
			sb.append(System.lineSeparator());
			sb.append(";ASSIGN FLATTEN ACTION-EMPTY: " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSIGN* {k}) (ASSIGN* {k-1}))");
			sb.append(System.lineSeparator());				
		}
		if (!ASSOCIATEActions.isEmpty()) {
			sb.append(";ASSOCIATE ACTION(S): " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSOC {k}) " + ASSOCIATEActions + ")");
			sb.append(System.lineSeparator());
		}
		else {
			sb.append(";ASSOCIATE ACTION-EMPTY: " + set);
			sb.append(System.lineSeparator());
			sb.append("(= (ASSOC {k}) (ASSOC {k-1}))");
			sb.append(System.lineSeparator());
		}
	}

	private void processConditions(StringBuilder sb, int i, ActionEncoder encoder) {
		if (!encoder.getCondition().isEmpty()) {
			sb.append(";CONDITION: " + i);
			sb.append(System.lineSeparator());
			sb.append(encoder.getCondition());
			sb.append(System.lineSeparator());
		}
		if (!encoder.getPrecondition().isEmpty()) {
			sb.append(";PRECONDITION: " + i);
			sb.append(System.lineSeparator());
			sb.append(encoder.getPrecondition());
			sb.append(System.lineSeparator());
		}
	}

	

	private void processNegatedConditions(StringBuilder sb, int i, ActionEncoder encoder) {
		if (!encoder.getNegatedPrecondition().isEmpty()) {
			sb.append(";NEGATED PRECONDITION: " + i);
			sb.append(System.lineSeparator());
			sb.append(encoder.getNegatedPrecondition());
			sb.append(System.lineSeparator());
		}
		if (!encoder.getNegatedCondition().isEmpty()) {
			sb.append(";NEGATED CONDITION: " + i);
			sb.append(System.lineSeparator());
			sb.append(encoder.getNegatedCondition());
			sb.append(System.lineSeparator());
		}
	}

	private String processAssignAction(String ASSIGNActions, String postcondition) {
		return ASSIGNActions.isEmpty() ? postcondition
				: ASSIGNActions.replaceFirst("\\(ASSIGN \\{k-1\\}\\)", postcondition);
	}

	private String processAssignFlattenAction(String ASSIGNFlattenActions, String postconditionFlatten) {
		return ASSIGNFlattenActions.isEmpty() ? postconditionFlatten
				: ASSIGNFlattenActions.replaceFirst("\\(ASSIGN\\* \\{k-1\\}\\)", postconditionFlatten);
	}

	private String processAssociateAction(String ASSOCIATEActions, String postcondition) {
		return ASSOCIATEActions.isEmpty() ? postcondition
				: ASSOCIATEActions.replaceFirst("\\(ASSOC \\{k-1\\}\\)", postcondition);
	}


	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
//		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/1O2Grants.yml")));
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/Assign.yml")));
		Obligation obligation = EVRParser.parse(yml);
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		SingleObligationEncoder soe = new SingleObligationEncoder();
		List<ActionEncoder> convertedActions = soe
				.preprocessActions(obligation.getRules().get(0).getResponsePattern().getActions(), mapOfIDs);

	//	System.out.println(soe.encodeTraces(convertedActions));
		System.out.println(composer.generateHeadCode());
		System.out.println(ActionEncoder.replaceKWithValue(soe.encodeTraces(convertedActions), 1));
	}

}
