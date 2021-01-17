package POMA.Mutation.ProhibitionMutationOperators;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class ProhibitionMutation {
	static Prohibitions prohibitions;
	static List<Prohibition> prohibitionList = new ArrayList<>();
	static ArrayList<Boolean> testResults = new ArrayList<Boolean>();
	static Graph g = new MemGraph();
	static List<Node> UAs;
	private double numberOfKilledMutants = 0;
	private int numberOfMutants = 0;
	String testMethod;
	
	public ProhibitionMutation(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		this.testMethod = testMethod;
		this.g = graph;
		this.prohibitions = prohibitions;
//		try {
//			//graph = Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
////			if (!Utils.verifyTestSuitIsForGraph(graph, getTestSuitPathByMethod(testMethod))) {
////				throw new GraphDoesNotMatchTestSuitException("Please verify that the testing suit is for this graph");
////			}
//			getGraphLoaded();
//
//		} catch (PMException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void getDefaultGraphLoaded () throws PMException {
		g.createPolicyClass("PC1", null);

		g.createNode("Container1", UA, null, "PC1");
		g.createNode("Container2", UA, null, "PC1");
		g.createNode("at1", UA, null, "Container1");
		g.createNode("at2", UA, null, "Container2");
		g.createNode("at3", UA, null, "Container1", "Container2");

		g.createNode("ua1", UA, null, "PC1");
		g.associate("ua1", "Container1", new OperationSet("ar1"));
		g.associate("ua1", "Container2", new OperationSet("ar1"));
		g.createNode("ua2", UA, null, "PC1");
		g.associate("ua2", "Container1", new OperationSet("ar2"));
		g.associate("ua2", "Container2", new OperationSet("ar2"));

//		System.out.println(GraphSerializer.toJson(g));
		
		getUAsInGraph(g);
	}
	
	public static Graph readGraph(String filepath) throws PMException, IOException {
		//graph can be changed in function getGraphLoaded()
		getDefaultGraphLoaded();
//		getGraphLoaded();
		return g;
	}

    public static void getDefaultProhibitionLoaded() throws PMException, IOException {
    	String filepath = "src/main/java/POMA/Mutation/ProhibitionMutationOperators/prohibitions1.json";
		File file = getFileFromResources(filepath);
		String json = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		prohibitions = new MemProhibitions();
		ProhibitionsSerializer.fromJson(prohibitions, json);
		prohibitionList = prohibitions.getAll();
//		System.out.println(json);
//		System.out.println(prohibitions.getAll().get(0).isIntersection());
//		System.out.println(prohibitions);
	}
    
    public static Prohibitions readProhibition(String filepath) throws PMException, IOException {
		//graph can be changed in function getGraphLoaded()
    	getDefaultProhibitionLoaded();
//    	getProhibitionLoaded();
		return prohibitions;
	}
	
    public static File getFileFromResources(String fileName) {
		File resource = new File(fileName);
		return resource;
	}
	
	public static Prohibitions createCopy() throws PMException {
		Prohibitions mutant = new MemProhibitions();
		String json = ProhibitionsSerializer.toJson(prohibitions);

		ProhibitionsSerializer.fromJson(mutant, json);
		return mutant;
	}
	
	public static void saveDataToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}
	

	public static void calculateOriginTestResults () throws PMException {
		PReviewDecider decider = new PReviewDecider(g, prohibitions);
//		System.out.println("ua1, at1, ar1"+": "+decider.check("ua1", "", "at1", "ar1"));
//		System.out.println("ua1, at2, ar1"+": "+decider.check("ua1", "", "at2", "ar1"));
//
//		System.out.println("ua1, at3, ar1"+": "+decider.check("ua1", "", "at3", "ar1"));
//		System.out.println("ua1, Container1, ar1"+": "+decider.check("ua1", "", "Container1", "ar1"));
//		System.out.println("ua1, Container2, ar1"+": "+decider.check("ua1", "", "Container2", "ar1"));
		testResults.add(decider.check("ua1", "", "at1", "ar1"));
		testResults.add(decider.check("ua1", "", "at2", "ar1"));
		testResults.add(decider.check("ua1", "", "at3", "ar1"));
		testResults.add(decider.check("ua1", "", "Container1", "ar1"));
		testResults.add(decider.check("ua1", "", "Container2", "ar1"));
//		System.out.println(testResults);

		//System.out.println(ProhibitionsSerializer.toJson(prohibitions));

//		System.out.println("origin ar1 results : " + testResults);
	}
	
	public static ArrayList<Boolean> getMutantTestResults (Prohibitions mutant) throws PMException {
		ArrayList<Boolean> results = new ArrayList<Boolean>();
	//	PReviewDecider decider = new PReviewDecider(g, mutant);
		PReviewDecider decider = new PReviewDecider(g, mutant);

//		System.out.println("ua1, at1, ar1"+": "+decider.check("ua1", "", "at1", "ar1"));
//		System.out.println("ua1, at2, ar1"+": "+decider.check("ua1", "", "at2", "ar1"));
//
//		System.out.println("ua1, at3, ar1"+": "+decider.check("ua1", "", "at3", "ar1"));
//		System.out.println("ua1, Container1, ar1"+": "+decider.check("ua1", "", "Container1", "ar1"));
//		System.out.println("ua1, Container2, ar1"+": "+decider.check("ua1", "", "Container2", "ar1"));
		//check all possible access rights
		results.add(decider.check("ua1", "", "at1", "ar1"));
		results.add(decider.check("ua1", "", "at2", "ar1"));
		results.add(decider.check("ua1", "", "at3", "ar1"));
		results.add(decider.check("ua1", "", "Container1", "ar1"));
		results.add(decider.check("ua1", "", "Container2", "ar1"));
		//System.out.println(ProhibitionsSerializer.toJson(mutant));
//		System.out.println(results);

//		System.out.println("mutant ar1 results : " + results);
		return results;
	}
	
	public static List<Prohibition> getProhibitionList () {
		return prohibitionList;
	}
	
	public static ArrayList<Boolean> getOriginTestResults () {
		return testResults;
	}
	
	//if ro and rm are same, return true; otherwise return false
	public static boolean compareTestResults (ArrayList<Boolean> ro, ArrayList<Boolean> rm) {
		if (ro.size() != rm.size())
			return false;
		int size = ro.size();
		for (int i = 0; i < size; i ++) {
			if (ro.get(i) != rm.get(i)) 
				return false;
		}
		return true;
	}
	
	public static OperationSet getAllAccessRights(Graph graph) throws PMException, IOException {
		OperationSet ARSet = new OperationSet();
		for (Node SourceNode : UAs) {
			if (graph.getSourceAssociations(SourceNode.getName()) == null) {
				continue;
			}
			Map<String, OperationSet> sources = graph.getSourceAssociations(SourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				ARSet.addAll(accessRights);
			}
		}
		// System.out.println("allAccessRightSet is :" + ARSet);
		return ARSet;
	}
	
	private static void getUAsInGraph(Graph graph) throws PMException {
		UAs = new ArrayList<Node>();

		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		for (Node node : nodes) {
			if (node.getType() == UA) {
				UAs.add(node);
			}
		}
	}
	
	public static Node[] getNodesInGraph() throws PMException {
		Node[] nodes = g.getNodes().toArray(new Node[g.getNodes().size()]);
		return nodes;
	}
	
	public double getNumberOfKilledMutants() {
		return numberOfKilledMutants;
	}

	public int getNumberOfMutants() {
		return numberOfMutants;
	}

	public void setNumberOfKilledMutants(double numberOfKilledMutants) {
		this.numberOfKilledMutants = numberOfKilledMutants;
	}

	public void setNumberOfMutants(int numberOfMutants) {
		this.numberOfMutants = numberOfMutants;
	}
	
	public double calculateMutationScore(double numberOfMutations, double numberOfKilledMutants) {
		if(numberOfMutations == 0) {
			return 0;
		}
		return (numberOfKilledMutants / numberOfMutations * 100);
	}
}