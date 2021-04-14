package POMA.PolicyGeneration;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.graph.model.nodes.NodeType;

public class GraphGenerator {

	Graph initialGraph;
	Graph generatedGraph;

	public static void main(String[] args) {
		try {
			 String initialGraphConfig = "Policies/LawUseCase/CasePolicy.json";
			//String initialGraphConfig = "Policies/GPMS_Simple/Complex/complexGPMS.json";

			GraphGenerator graphGenerator = new GraphGenerator(initialGraphConfig);
			 //graphGenerator.generateGraphWithBranching(2);
			graphGenerator.generateGraphsByPCs(10);

			System.out.println("INITIAL SIZE: " + graphGenerator.initialGraph.getNodes().size());
			System.out.println("INITIAL PC SIZE: " + graphGenerator.initialGraph.getPolicyClasses().size());
			System.out.println();
			System.out.println("GENERATED SIZE: " + graphGenerator.generatedGraph.getNodes().size());
			System.out.println("GENERATED PC SIZE: " + graphGenerator.generatedGraph.getPolicyClasses().size());

			Utils.saveDataToFile(GraphSerializer.toJson(graphGenerator.generatedGraph),
					"Policies/LawUseCase/CasePolicy_new.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GraphGenerator(Graph graph) {
		initialGraph = graph;
	}

	public GraphGenerator(String path) throws PMException, IOException {
		initialGraph = Utils.readAnyGraph(path);
	}

	public void generateGraphWithBranching(int branchingFactor) throws PMException {
		Set<String> PCs = initialGraph.getPolicyClasses();
		for (String pc : PCs) {
			List<String> nodesInPC = getNodesContainedBy(pc);
			addBranches(nodesInPC, branchingFactor);
		}
	}

	public void generateGraphsByPCs(int multiplyFactor) throws PMException {
		Set<String> PCs = initialGraph.getPolicyClasses();
		generatedGraph = new MemGraph();
		GraphSerializer.fromJson(generatedGraph, GraphSerializer.toJson(initialGraph));

		for (String pc : PCs) {
			for (int currentMultiplyFactor = 0; currentMultiplyFactor < multiplyFactor-1; currentMultiplyFactor++) {
				String newPCName = pc + currentMultiplyFactor;
				generatedGraph.createPolicyClass(newPCName, null);
				List<String> descendants = getNodesContainedBy(pc);
				for (String descendant : descendants) {
					addSubGraph(pc, descendant, currentMultiplyFactor);
				}
				copyAssociations(currentMultiplyFactor, pc);
			}
		}
	}

	public void generateGraphWithBranching(int branchingFactor, int height) throws PMException {

	}

	public void generateGraphWithMultiplePCs(int numberOfPCs) {

	}

	public void generateGraphWithHeight(int height) {

	}

	private void copyAssociations(int pcNumber, String pc) throws PMException {
		Node pcNode = initialGraph.getNode(pc);
		DepthFirstSearcher dfs = new DepthFirstSearcher(initialGraph);
		List<String> nodes = new ArrayList<String>();
		Visitor visitor = node -> {
			String nodeName = node.getName();
			NodeType nodeType = node.getType();
			if (!nodeType.equals(UA)) {
				return;
			}
			Map<String, OperationSet> associations = initialGraph.getSourceAssociations(nodeName);
			associations.forEach((target, operationsSet) -> {
				try {
					if (generatedGraph.exists(nodeName + pcNumber) && generatedGraph.exists(target + pcNumber)) {
						generatedGraph.associate(nodeName + pcNumber, target + pcNumber, operationsSet);
					}
				} catch (PMException e) {
					e.printStackTrace();
				}
			});

			nodes.add(node.getName());

		};
		dfs.traverse(pcNode, (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private List<String> addSubGraph(String pc, String ancestor, int pcNumber) throws PMException {
		Node pcNode = initialGraph.getNode(ancestor);
		DepthFirstSearcher dfs = new DepthFirstSearcher(initialGraph);
		List<String> nodes = new ArrayList<String>();
		Visitor visitor = node -> {
			String nodeName = node.getName();
			if (nodeName.equals(pc)) {
				return;
			}

			for (String parent : initialGraph.getParents(nodeName)) {
				NodeType nodeType = node.getType();
				if (!generatedGraph.exists(nodeName + pcNumber) && generatedGraph.exists(parent + pcNumber)) {
					generatedGraph.createNode(nodeName + pcNumber, nodeType, null, parent + pcNumber);
				}
				// System.out.println("DESCENDANT" + nodeName);
			}
		};
		dfs.traverse(pcNode, (c, p) -> {
		}, visitor, Direction.PARENTS);
		return nodes;
	}

	private List<String> getNodesContainedBy(String ancestor) throws PMException {
		Node pcNode = initialGraph.getNode(ancestor);
		DepthFirstSearcher dfs = new DepthFirstSearcher(initialGraph);
		List<String> nodes = new ArrayList<String>();
		Visitor visitor = node -> {
			if (!node.getName().equals(ancestor)) {
				nodes.add(node.getName());
			}
		};
		dfs.traverse(pcNode, (c, p) -> {
		}, visitor, Direction.CHILDREN);
		return nodes;
	}

	private void addBranches(List<String> nodesInPC, int branchingFactor) throws PMException {
		generatedGraph = new MemGraph();
		GraphSerializer.fromJson(generatedGraph, GraphSerializer.toJson(initialGraph));
		for (String nodeName : nodesInPC) {
			NodeType nodeType = initialGraph.getNode(nodeName).getType();
			if (!nodeType.equals(UA) && !nodeType.equals(OA)) {
				continue;
			}
			for (int i = 0; i < branchingFactor + 1; i++) {
				String newNodeName = nodeName + i;
				generatedGraph.createNode(newNodeName, nodeType, null, nodeName);
				Set<String> childrenNodes = initialGraph.getChildren(nodeName);
				int j = 0;
				for (String child : childrenNodes) {
					if (generatedGraph.exists(child + i + "_" + j)) {
						continue;
					}
					NodeType nodeTypeContainedNode = initialGraph.getNode(child).getType();
					generatedGraph.createNode(child + i + "_" + j, nodeTypeContainedNode, null, nodeName);
					j++;
				}

			}
		}
	}
}
