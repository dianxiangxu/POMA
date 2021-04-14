package POMA.Verification.BMC;

import java.util.AbstractCollection;
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

	HashMap<Integer, String> mapOfIDs = new HashMap<Integer, String>();
	private Set<String> tuples = new HashSet<String>();
	private Set<String> tuplesForUACheck = new HashSet<String>();
	private Set<String> tuplesForOACheck = new HashSet<String>();
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;
	private List<AssociationRelation> associationsFromObligations = new ArrayList<AssociationRelation>();
	private List<String> addedNodesFromObligationsUA_U = new ArrayList<String>();
	private List<String> addedNodesFromObligationsOA_O = new ArrayList<String>();
	private List<String> obligationLabels = new ArrayList<String>();

	Graph graph;

	HashMap<Integer, String> getMapOfIDs() {
		return mapOfIDs;
	}

	GraphTranslator(String pathToGraph) throws Exception {
		graph = Utils.readAnyGraph(pathToGraph);
	}

	private String setObligationLabels() {
		StringBuilder sb = new StringBuilder();
		for(String label : obligationLabels){
			sb.append(System.lineSeparator());
			sb.append("(declare-fun "+ label+" (Int) Int)");
		}
		return sb.toString();
	}

	private String setCVC4Options() {
		StringBuilder sb = new StringBuilder();
		sb.append("(set-logic ALL_SUPPORTED)");
		sb.append(System.lineSeparator());
		sb.append("(set-option :produce-models true)");
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	private void getGraphElements() throws Exception {

		Set<Node> nodes = graph.getNodes();

		int index = 1;
		for (Node node : nodes) {
			mapOfIDs.put(index, node.getName());
			index++;
		}
		for (String nodeName : addedNodesFromObligationsUA_U) {
			mapOfIDs.put(index, nodeName);
			index++;
		}
		OperationSet os = Utils.getAllAccessRights(graph);

		for (String ar : os) {
			mapOfIDs.put(index, ar);
			index++;
		}
		for (AssociationRelation association : associationsFromObligations) {
			Set<String> operationSet = association.getOperationSet();
			for (String ar : operationSet) {
				mapOfIDs.put(index, ar);
				index++;
			}
		}
	}

	private String translateGraphElements() throws Exception {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<Integer, String> entry : mapOfIDs.entrySet()) {
			sb.append("(declare-fun " + entry.getValue() + " () Int)");
			sb.append(System.lineSeparator());
			sb.append("(assert (= " + entry.getValue() + " " + entry.getKey() + "))");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	private void findTClosureForGraph(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(graph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
					|| node.getType().toString().equals("O") || node.getType().toString().equals("OA"))) {
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("OA")) {
					// tuples.add(new AssignmentRelation(node.getName(),
					// node.getName()).toStringNoQuotes());
				}
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("U")) {
					tuplesForUACheck.add(new AssignmentRelation(node.getName(), node.getName()).toStringNoQuotes());
				}
				if (node.getType().toString().equals("OA") || node.getType().toString().equals("O")) {
					tuplesForOACheck.add(new AssignmentRelation(node.getName(), node.getName()).toStringNoQuotes());
				}
				for (String parent : graph.getParents(node.getName())) {
					tuples.add(new AssignmentRelation(node.getName(), parent).toStringNoQuotes());
				}
			}
		};
		dfs.traverse(graph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private String translateSetToCheckUA() {
		for (String UA_U : addedNodesFromObligationsUA_U) {
			tuplesForUACheck.add(new AssignmentRelation(UA_U, UA_U).toStringNoQuotes());
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
			tuplesForUACheck.add(new AssignmentRelation(OA_O, OA_O).toStringNoQuotes());
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

	private String translateSetGraph() {
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun GRAPH0 () (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= GRAPH0 (insert ");
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

	private String populateTuples() throws Exception {
		for (String policyClass : graph.getPolicyClasses()) {
			findTClosureForGraph(policyClass);
		}
		return null;
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
		sb.append("(declare-fun Associations (Int) (Set (Tuple Int Int Int)))");
		sb.append(System.lineSeparator());
		if (listOfAssociations.size() != 1) {
			sb.append(System.lineSeparator() + "(assert (= (Associations 0) (insert");
		} else {
			sb.append(System.lineSeparator() + "(assert (= (Associations 0) ( ");
			for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
				AssociationRelation triple = iterator.next();
				String ua = triple.getUA();
				String at = triple.getAT();
				Iterator<String> iteratorAR = triple.getOperationSet().iterator();
				while (iteratorAR.hasNext()) {
					String assoc = "(mkTuple " + ua + " " + iteratorAR.next() + " " + at + ")" + System.lineSeparator();
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
				String assoc = "(mkTuple " + ua + " " + iteratorAR.next() + " " + at + ")";
				if (!iterator.hasNext() && !iteratorAR.hasNext()) {
					sb.append("(singleton " + assoc + "))))" + System.lineSeparator());
				} else {
					sb.append(assoc + " " + System.lineSeparator());
				}
			}

		}
		return sb.toString();
	}

	private String translateInitialARCheck() {

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AssociationsForUA (Int) (Set (Tuple Int Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun Tclosure(Int) (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (Tclosure " + 0 + ") (tclosure GRAPH0)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (UA_U_Reachability " + 0 + ") (join SetToCheckUA (Tclosure " + 0 + "))))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (AT_Reachability " + 0 + ") (join SetToCheckAT (Tclosure " + 0 + "))))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (AssociationsForUA " + 0 + ") (join (UA_U_Reachability " + 0 + ") (Associations " + 0
				+ "))))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= (AccessRights " + 0 + ") (join (AssociationsForUA " + 0 + ") (transpose (AT_Reachability "
				+ 0 + ")))))");
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	String translateHeadCode(List<AssociationRelation> listOfAddedAssociationsFromObligations,
			List<String> listOfAddedNodesUA_U, List<String> listOfAddedNodesOA_O, List<String> obligationLabels) throws Exception {
		StringBuilder headcode = new StringBuilder();
		associationsFromObligations.addAll(listOfAddedAssociationsFromObligations);
		addedNodesFromObligationsUA_U.addAll(listOfAddedNodesUA_U);
		addedNodesFromObligationsOA_O.addAll(listOfAddedNodesOA_O);
		this.obligationLabels.addAll(obligationLabels);
		getGraphElements();
		populateTuples();
		headcode.append(setCVC4Options());
		headcode.append(translateGraphElements());
		headcode.append(translateSetToCheckUA());
		headcode.append(translateSetToCheckOA());
		headcode.append(translateSetGraph());
		headcode.append(translateAssociations());
		headcode.append(translateInitialARCheck());
		headcode.append(setObligationLabels());
		return headcode.toString();
	}

}