package project.POMA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

public class CVC4Translator {

	private Set<String> tuplesUA = new HashSet<String>();
	private Set<String> tuplesOA = new HashSet<String>();
	private List<String> UAs;
	private Graph ngacGraph;
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;
	private String pathToGraph;

	public CVC4Translator(String pathToGraph) {
		this.pathToGraph = pathToGraph;
	}

	public CVC4Translator(Graph ngacGraph) {
		this.ngacGraph = ngacGraph;

	}

	public void initTranslation() {

		try {
			if (ngacGraph == null) {
				readAnyGraph(pathToGraph);
				checkGraph();
			} else {
				checkGraph();
			}
		} catch (PMException | IOException e) {
			e.printStackTrace();
			System.out.println("The graph was not loaded correctly");
		} catch (NodeIsNotContainedByPolicyClassException e) {
			System.out.println(e);
			System.exit(0);
			;
		}

		try {
			findTClosuresForAllPCs();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Could not find tclosure correctly");
		}

		try {
			findUAsInGraph();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Problems with finding UAs in the graph");
		}
		try {
			findAssociationsInGraph();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Problems with finding associations in the graph");
		}

	}

	public String getTranslatedGraph() {
		StringBuilder sb = new StringBuilder();
		CVC4PrepTranslation prepTranslation = new CVC4PrepTranslation();
		sb.append(prepTranslation.toString());

		for (AssociationRelation ar : listOfAssociations) {
			sb.append(ar.toString());
		}

		sb.append(translateUAsContainment());
		sb.append(System.lineSeparator());
		sb.append(translateOAsContainment());
		sb.append(System.lineSeparator());
		sb.append(prepTranslation.assertTClosures());
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	private String translateUAsContainment() {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert (= UA_containment (insert ");
		for (Iterator<String> iterator = tuplesUA.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))");
			} else {
				sb.append(tuple + " ");
			}
		}
		return sb.toString();
	}

	private String translateOAsContainment() {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert (= OA_containment (insert ");
		for (Iterator<String> iterator = tuplesOA.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))");
			} else {
				sb.append(tuple + " ");
			}
		}
		return sb.toString();
	}

	private void findAssociationsInGraph() throws PMException {
		for (String UA : UAs) {
			Map<String, OperationSet> association = ngacGraph.getSourceAssociations(UA);
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

	private void findUAsInGraph() throws PMException {
		UAs = new ArrayList<String>();
		for (String policyClass : ngacGraph.getPolicyClasses()) {
			UAs.addAll(findUAsInPC(policyClass));
		}
	}

	private List<String> findUAsInPC(String PC) throws PMException {
		List<String> UAs = new ArrayList<String>();
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if (node.getType().toString().equals("UA") && !node.getName().contains("super")) {
				UAs.add(node.getName());
			}
		};
		dfs.traverse(ngacGraph.getNode(PC), (c, p) -> {
		}, visitor, Direction.CHILDREN);

		return UAs;
	}

	private void findTClosuresForAllPCs() throws PMException {
		for (String policyClass : ngacGraph.getPolicyClasses()) {
			findTClosureForUA(policyClass);
			findTClosureForOA(policyClass);
		}
	}

	private void readDefaultGraph() throws PMException, IOException {
		File super_config = new File("Graphs/super_config.json");
		File file_eligibility_policy = new File("Graphs/EligibilityPolicyClass.json");
		String super_policy = new String(Files.readAllBytes(Paths.get(super_config.getAbsolutePath())));
		String eligibility_policy = new String(
				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));

		ngacGraph = new MemGraph();

		GraphSerializer.fromJson(ngacGraph, super_policy);
		GraphSerializer.fromJson(ngacGraph, eligibility_policy);
	}

	private void readAnyGraph(String path) throws PMException, IOException, NodeIsNotContainedByPolicyClassException {
		File file_eligibility_policy = new File(path);
		String eligibility_policy = new String(
				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));

		ngacGraph = new MemGraph();

		GraphSerializer.fromJson(ngacGraph, eligibility_policy);
	}

	private void findTClosureForUA(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U"))
					&& !node.getName().contains("super")) {
				for (String parent : ngacGraph.getParents(node.getName())) {
					tuplesUA.add(new AssignmentRelation(node.getName(), parent).toString());
				}
			}
		};
		dfs.traverse(ngacGraph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private void findTClosureForOA(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("OA") || node.getType().toString().equals("O"))
					&& !node.getName().contains("super")) {
				for (String parent : ngacGraph.getParents(node.getName())) {
					tuplesOA.add(new AssignmentRelation(node.getName(), parent).toString());
				}
			}
		};
		dfs.traverse(ngacGraph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private boolean isContainedByAnyPC(String child) throws PMException {
		System.out.println("CHILD: " + child);
		for (String pc : ngacGraph.getPolicyClasses()) {

			DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
			Set<String> nodes = new HashSet<>();
			Visitor visitor = node -> {
				if (node.getName().equals(pc)) {
					nodes.add(node.getName());
				}
			};
			dfs.traverse(ngacGraph.getNode(child), (c, p) -> {
			}, visitor, Direction.PARENTS);
			if (nodes.contains(pc)) {
				return true;
			}
		}
		return false;
	}

	private void checkGraph() throws PMException, NodeIsNotContainedByPolicyClassException {
		for (Node node : ngacGraph.getNodes()) {
			if (!node.getType().toString().equals("PC")) {
				if (!isContainedByAnyPC(node.getName())) {
					throw new NodeIsNotContainedByPolicyClassException(
							"Every node has to be contained by a policy class.");
				}
			}
		}
	}
}
