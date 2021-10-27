package POMA.Verification.ReachabilityAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Utils;
import POMA.Verification.TranslationWithSets.AssignmentRelation;
import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

class GraphTranslator {

	HashMap<String, Integer> mapOfIDs = new HashMap<String, Integer>();
	private Set<String> flattenedTuples = new HashSet<String>();
	private Set<String> tuples = new HashSet<String>();

	private Set<String> tuplesForUACheck = new HashSet<String>();
	private Set<String> tuplesForOACheck = new HashSet<String>();
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;
	private List<AssociationRelation> associationsFromObligations;
	private List<String> addedNodesFromObligationsUA_U;
	private List<String> addedNodesFromObligationsOA_O;
	private List<String> obligationLabels;
	private Map<String, String> eventMembers;

	Graph graph;

	HashMap<String, Integer> getMapOfIDs() {
		return mapOfIDs;
	}

	GraphTranslator(String pathToGraph) {
		try {
			graph = Utils.readAnyGraph(pathToGraph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String setObligationLabels() {
		StringBuilder sb = new StringBuilder();
		for (String label : obligationLabels) {
			sb.append(System.lineSeparator());
			sb.append("(declare-fun " + label + " (Int) Int)");
		}
		return sb.toString();
	}

	private String setCVC4Options() {
		StringBuilder sb = new StringBuilder();
		sb.append("(set-logic ALL)");
		sb.append(System.lineSeparator());
		sb.append("(set-option :produce-models true)");
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	private void getGraphElements() throws Exception {

		Set<Node> nodes = graph.getNodes();

		int index = 1;
		for (Node node : nodes) {
			mapOfIDs.put(node.getName(), index);
			index++;
		}
		for (String nodeName : addedNodesFromObligationsUA_U) {
			mapOfIDs.put(nodeName, index);
			index++;
		}
		for (String nodeName : addedNodesFromObligationsOA_O) {
			mapOfIDs.put(nodeName, index);
			index++;
		}
		OperationSet os = Utils.getAllAccessRights(graph);

		for (String ar : os) {
			mapOfIDs.put(ar, index);
			index++;
		}
		for (AssociationRelation association : associationsFromObligations) {
			Set<String> operationSet = association.getOperationSet();
			for (String ar : operationSet) {
				mapOfIDs.put(ar, index);
				index++;
			}
		}
	}

	// O(V^2)
	private void flattenAssignment() throws PMException {
		Set<Node> nodes = graph.getNodes();
		for (Node node : nodes) {
			DepthFirstSearcher dfs = new DepthFirstSearcher(graph);
			int nodeID = mapOfIDs.get(node.getName());
			Visitor visitor = visitorNode -> {
				// System.out.println(node.getName()+" : "+visitorNode.getName());
				int descendantID = mapOfIDs.get(visitorNode.getName());
				flattenedTuples.add(
						new AssignmentRelation(Integer.toString(nodeID), Integer.toString(nodeID)).toStringNoQuotes());
				flattenedTuples.add(new AssignmentRelation(Integer.toString(nodeID), Integer.toString(descendantID))
						.toStringNoQuotes());
			};
			dfs.traverse(node, (c, p) -> {
			}, visitor, Direction.PARENTS);
		}
	}

	private void findAssignments(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(graph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
					|| node.getType().toString().equals("O") || node.getType().toString().equals("OA"))) {
				int childID = mapOfIDs.get(node.getName());
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("OA")) { // comment if
																										// needed.
					flattenedTuples.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
					tuples.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
				}
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("U")) {
					tuplesForUACheck.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
					tuples.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
				}
				if (node.getType().toString().equals("OA") || node.getType().toString().equals("O")) {
					tuplesForOACheck.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
					tuples.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(childID))
							.toStringNoQuotes());
				}
				for (String parent : graph.getParents(node.getName())) {
					int parentID = mapOfIDs.get(parent);
					tuples.add(new AssignmentRelation(Integer.toString(childID), Integer.toString(parentID))
							.toStringNoQuotes()); // comment
																												// when
																												// needed
																												// flatten			
				}
			}
		};
		dfs.traverse(graph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private String translateSetToCheckUA() {
		for (String UA_U : addedNodesFromObligationsUA_U) {
			int ua_uID = mapOfIDs.get(UA_U);
			tuplesForUACheck
					.add(new AssignmentRelation(Integer.toString(ua_uID), Integer.toString(ua_uID)).toStringNoQuotes());
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun SetToCheckUA () (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= SetToCheckUA (insert ");
		for (Iterator<String> iterator = tuplesForUACheck.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))" + System.lineSeparator());
			} else {
				sb.append(tuple + " " + System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String translateSetToCheckOA() {
		for (String OA_O : addedNodesFromObligationsOA_O) {
			int oa_oID = mapOfIDs.get(OA_O);
			tuplesForOACheck
					.add(new AssignmentRelation(Integer.toString(oa_oID), Integer.toString(oa_oID)).toStringNoQuotes());
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun SetToCheckAT () (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= SetToCheckAT (insert ");
		for (Iterator<String> iterator = tuplesForOACheck.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))" + System.lineSeparator());
			} else {
				sb.append(tuple + " " + System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String translateSetFlattenedAssign() {
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (ASSIGN* 0) (insert ");
		for (Iterator<String> iterator = flattenedTuples.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))" + System.lineSeparator());
			} else {
				sb.append(tuple + " " + System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String translateSetAssign() {
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (ASSIGN 0) (insert ");
		for (Iterator<String> iterator = tuples.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))" + System.lineSeparator());
			} else {
				sb.append(tuple + " " + System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private void populateTuples() throws Exception {
		for (String policyClass : graph.getPolicyClasses()) {
			findAssignments(policyClass);
		}
		// flattenAssignmentsV4();
		flattenAssignment();
		for (Map.Entry<String, String> entry : eventMembers.entrySet()) {
			int userID = mapOfIDs.get(entry.getKey());
			// int targetID = mapOfIDs.get(entry.getValue());
			flattenedTuples
					.add(new AssignmentRelation(Integer.toString(userID), Integer.toString(userID)).toStringNoQuotes());
			// tuples.add(new AssignmentRelation(Integer.toString(targetID),
			// Integer.toString(
			// targetID)).toStringNoQuotes());
		}
		System.out.println("ASSIGNMENTS SIZE: " + flattenedTuples.size());
		// System.exit(0);
	}

	private void findAssociationsInGraph() throws Exception {
		List<String> UAs = Utils.getNodesByTypes(graph, "UA");
		for (String UA : UAs) {
			Map<String, OperationSet> association = graph.getSourceAssociations(UA);
			if (!association.isEmpty()) {
				for (Map.Entry<String, OperationSet> entry : association.entrySet()) {
					String AT = entry.getKey();
					OperationSet os = entry.getValue();
					AssociationRelation associationRelation = new AssociationRelation(UA, os, AT);
					listOfAssociations.add(associationRelation);
				}
			}

		}
	}

	private String translateAssociations() throws Exception {
		findAssociationsInGraph();
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))");
		sb.append(System.lineSeparator());
		if (listOfAssociations.size() != 1) {
			sb.append(System.lineSeparator() + "(assert (= (ASSOC 0) (insert");
		} else {
			sb.append(System.lineSeparator() + "(assert (= (ASSOC 0) ( ");
			for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
				AssociationRelation triple = iterator.next();
				String ua = triple.getUA();
				String at = triple.getAT();
				Iterator<String> iteratorAR = triple.getOperationSet().iterator();
				while (iteratorAR.hasNext()) {
					int uaID = mapOfIDs.get(ua);
					int arID = mapOfIDs.get(iteratorAR.next());
					int atID = mapOfIDs.get(at);
					String assoc = "(mkTuple " + uaID + " " + arID + " " + atID + ")" + System.lineSeparator();
					if (!iterator.hasNext() && !iteratorAR.hasNext()) {
						sb.append("singleton " + assoc + ")))" + System.lineSeparator());
					} else {
						sb.append(assoc + " ");
					}
				}

			}
			return sb.toString();
		}
		for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
			AssociationRelation triple = iterator.next();
			String ua = triple.getUA();
			String at = triple.getAT();
			Iterator<String> iteratorAR = triple.getOperationSet().iterator();
			while (iteratorAR.hasNext()) {
				int uaID = mapOfIDs.get(ua);
				int arID = mapOfIDs.get(iteratorAR.next());
				int atID = mapOfIDs.get(at);
				String assoc = "(mkTuple " + uaID + " " + arID + " " + atID + ")";
				if (!iterator.hasNext() && !iteratorAR.hasNext()) {
					sb.append("(singleton " + assoc + "))))" + System.lineSeparator());
				} else {
					sb.append(assoc + " " + System.lineSeparator());
				}
			}

		}
		return sb.toString();
	}

	private String getAllNodesTranslations() throws Exception {
		Set<Node> nodes = graph.getNodes();
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append("(declare-fun NODES () (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= NODES (insert ");
		for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
			Node node = iterator.next();
			int nodeID = mapOfIDs.get(node.getName());

			if (!iterator.hasNext()) {
				sb.append("(singleton " + "(mkTuple " + nodeID + " " + nodeID + "))))) " + System.lineSeparator());
			} else {
				sb.append("(mkTuple " + nodeID + " " + nodeID + ") " + System.lineSeparator());
			}
		}
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	// private String translateBoundedVariablesDefinition() {
	// 	StringBuilder sb = new StringBuilder();
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(declare-fun ASSOC*UA (Int) (Set (Tuple Int Int Int)))");
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(declare-fun ASSIGN*UUA (Int) (Set (Tuple Int Int)))");
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(declare-fun ASSIGN*AT (Int) (Set (Tuple Int Int)))");
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(declare-fun ASSOC*(Int) (Set (Tuple Int Int Int)))");
	// 	sb.append(System.lineSeparator());

	// 	return sb.toString();
	// }

	// String translateARCheck(int k) {

	// 	StringBuilder sb = new StringBuilder();

	// 	ProhibitionTranslator pt = new ProhibitionTranslator(graph, mapOfIDs);

	// 	if (pt.translateProhibitionSingleContainer(1, k) != null && k == 0) {
	// 		return pt.translateProhibitionSingleContainer(1, k);

	// 	}
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(assert (= (ASSIGN*UUA " + k + ") (join SetToCheckUA (ASSIGN* " + k + "))))");
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(assert (= (ASSIGN*AT " + k + ") (join SetToCheckAT (ASSIGN* " + k + "))))");
	// 	sb.append(System.lineSeparator());

	// 	sb.append("(assert (= (ASSOC*UA " + k + ") (join (ASSIGN*UUA " + k + ") (ASSOC " + k + "))))");
	// 	sb.append(System.lineSeparator());
	// 	sb.append("(assert (= (ASSOC* " + k + ") (join (ASSOC*UA " + k + ") (transpose (ASSIGN*AT " + k + ")))))");
	// 	sb.append(System.lineSeparator());
	// 	return sb.toString();
	// }

	String translateHeadCode(List<AssociationRelation> listOfAddedAssociationsFromObligations,
			List<String> listOfAddedNodesUA_U, List<String> listOfAddedNodesOA_O, List<String> obligationLabels,
			Map<String, String> eventMembers) throws Exception {
		StringBuilder headcode = new StringBuilder();
		associationsFromObligations = listOfAddedAssociationsFromObligations;
		addedNodesFromObligationsUA_U = listOfAddedNodesUA_U;
		addedNodesFromObligationsOA_O = listOfAddedNodesOA_O;
		this.eventMembers = eventMembers;
		this.obligationLabels = obligationLabels;
		getGraphElements();
		populateTuples();
		headcode.append(setCVC4Options());
		headcode.append(translateSetToCheckUA());
		headcode.append(translateSetToCheckOA());
		headcode.append(translateSetFlattenedAssign());
		headcode.append(translateSetAssign());
		headcode.append(translateAssociations());
		headcode.append(getAllNodesTranslations());
		//headcode.append(translateBoundedVariablesDefinition());
		//headcode.append(translateARCheck(0));
		headcode.append(setObligationLabels());
		return headcode.toString();
	}

}