package POMA.Verification.TranslationWithSets;

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

import POMA.Utils;
import POMA.Exceptions.NoTypeProvidedException;
import POMA.Exceptions.NodeIsNotContainedByPolicyClassException;
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

	private Set<String> tuples = new HashSet<String>();
	private List<String> UAs;
	private Graph ngacGraph;
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;
	private String pathToGraph;
	private Set<String> nodesWithoutContainment = new HashSet<String>();

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
			//System.exit(0);
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

	public String getTranslatedGraph() throws PMException, NoTypeProvidedException, IOException {
		StringBuilder sb = new StringBuilder();
		CVC4PrepTranslation prepTranslation = new CVC4PrepTranslation();
		sb.append(prepTranslation.toString());

		sb.append(translateAssociations());
		// sb.append(AssociationRelation.setCardSetAssociationSetCVC4Assertion(listOfAssociations.size()));
		sb.append(System.lineSeparator());
		sb.append(translateContainment());
		sb.append(System.lineSeparator());
		sb.append(prepTranslation.assertTClosures());
		sb.append(System.lineSeparator());
		// System.out.println(getTestSuits());
		return sb.toString();
	}

	public static String getAllAccessRightsCheckBetweenUAandAT(String UA, String AT)
			throws PMException, NoTypeProvidedException {
//		if (UA.equals(AT)) {
//			throw new IllegalArgumentException("The subject and target cannot match");
//		}
		String toReturn = "";
		toReturn += "(declare-fun SingletonToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SingletonToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= SingletonToCheckUA (singleton (mkTuple  \"" + UA + "\" \"" + UA + "\"))))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= SingletonToCheckAT (singleton (mkTuple \"" + AT + "\" \"" + AT + "\"))))";
		toReturn += System.lineSeparator();
		toReturn += "(assert(not(= SingletonToCheckUA SingletonToCheckAT)))";
		toReturn += System.lineSeparator();
		toReturn += "(assert (= UA_U_Reachability (join SingletonToCheckUA Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AT_Reachability (join SingletonToCheckAT Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
		toReturn += System.lineSeparator();
		toReturn += "(check-sat)";
		toReturn += System.lineSeparator();
		toReturn += "(get-value (FinalJoin))";
		toReturn += System.lineSeparator();
		return toReturn;
	}

	public static String getAllAccessRightsCheckInSetOfUAandAT(List<String[]> input)
			throws PMException, NoTypeProvidedException {
		String toReturn = "";
		toReturn += "(declare-fun SingletonToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SingletonToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		for (String[] array : input) {
//			if (array[0].equals(array[1])) {
//				continue;
//			}
			
			if(array.length<2) {
			continue;	
			}
			toReturn += "(push 1)";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= SingletonToCheckUA (singleton (mkTuple  \"" + array[0] + "\" \"" + array[0]
					+ "\"))))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= SingletonToCheckAT (singleton (mkTuple \"" + array[1] + "\" \"" + array[1]
					+ "\"))))";
			toReturn += System.lineSeparator();
			toReturn += "(assert (= UA_U_Reachability (join SingletonToCheckUA Tclosure )))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= AT_Reachability (join SingletonToCheckAT Tclosure )))";
			toReturn += System.lineSeparator();

			toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
			toReturn += System.lineSeparator();

			toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
			toReturn += System.lineSeparator();
			toReturn += "(check-sat)";
			toReturn += System.lineSeparator();
			toReturn += "(get-model)";
			toReturn += System.lineSeparator();
			toReturn += "(get-value (FinalJoin))";
			toReturn += System.lineSeparator();
			toReturn += "(pop 1)";
			toReturn += System.lineSeparator();
		}
		return toReturn;
	}

	public static String getAllAccessRightsCheckInSetOfUAandATAllComb(List<String[]> input)
			throws PMException, NoTypeProvidedException {
		String toReturn = "";
		toReturn += "(declare-fun SetToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SetToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();
		StringBuilder sbUA = new StringBuilder();
		StringBuilder sbAT = new StringBuilder();
		List<String> uas = new ArrayList<String>();
		List<String> ats = new ArrayList<String>();
		if(input.size()==1) {
			sbUA.append(System.lineSeparator()+"(assert (= SetToCheckUA ");
			sbAT.append(System.lineSeparator()+"(assert (= SetToCheckAT ");
		}
		else {
		sbUA.append(System.lineSeparator()+"(assert (= SetToCheckUA (insert ");
		sbAT.append(System.lineSeparator()+"(assert (= SetToCheckAT (insert ");
		}
		Iterator<String[]> iterator = input.iterator();
		while (iterator.hasNext()) {		
			String[] array = iterator.next();
			String tupleUA = "(mkTuple \"" + array[0] + "\" \"" + array[0] + "\")";
			String tupleAT = "(mkTuple \"" + array[1] + "\" \"" + array[1] + "\")";
			if (!iterator.hasNext()) {
				if(input.size()==1) {
				sbUA.append("(singleton " + tupleUA + ")))");
				sbAT.append("(singleton " + tupleAT + ")))");
				}
				else {
					sbUA.append("(singleton " + tupleUA + "))))");
					sbAT.append("(singleton " + tupleAT + "))))");
				}
			} else {
					if (!uas.contains(array[0])) {
						sbUA.append(tupleUA + " " + System.lineSeparator());
						uas.add(array[0]);
					}
					if (!ats.contains(array[1])) {
						sbAT.append(tupleAT + " " + System.lineSeparator());
						ats.add(array[1]);
					}
				}

			
		}

		toReturn += sbUA.toString();
		toReturn += System.lineSeparator();
		toReturn += sbAT.toString();
		toReturn += System.lineSeparator()+System.lineSeparator();
		toReturn += "(assert (= UA_U_Reachability (join SetToCheckUA Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AT_Reachability (join SetToCheckAT Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
		toReturn += System.lineSeparator();
		toReturn += "(check-sat)";
		toReturn += System.lineSeparator();
		toReturn += "(get-model)";
		toReturn += System.lineSeparator();
		toReturn += "(get-value (FinalJoin))";
		toReturn += System.lineSeparator();
		return toReturn;
	}

	private String translateContainment() {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert (= Containment (insert ");
		for (Iterator<String> iterator = tuples.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))"+System.lineSeparator());
			} else {
				sb.append(tuple + " "+System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String translateAssociations() {
		StringBuilder sb = new StringBuilder();
		if(listOfAssociations.size()!=1) {
			sb.append(System.lineSeparator()+"(assert (= Associations (insert");
		}
		else {
			sb.append(System.lineSeparator()+"(assert (= Associations ( ");
			for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
				AssociationRelation tuple = iterator.next();
				String ua = tuple.getUA();
				String at = tuple.getAT();
				Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
				while (iteratorAR.hasNext()) {
					String assoc = "(mkTuple \"" + ua + "\" \"" + iteratorAR.next() + "\" \"" + at + "\")"+System.lineSeparator();
					if (!iterator.hasNext() && !iteratorAR.hasNext()) {
						sb.append("singleton " + assoc + ")))"+System.lineSeparator());
					} else {
						sb.append(assoc + " ");
					}
				}			


			}
			return sb.toString();
		}
		for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
			AssociationRelation tuple = iterator.next();
		//	System.out.println("!!!!!!!!!!!!!!!!!" + tuple);
			String ua = tuple.getUA();
			String at = tuple.getAT();
			Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
			while (iteratorAR.hasNext()) {
				String assoc = "(mkTuple \"" + ua + "\" \"" + iteratorAR.next() + "\" \"" + at + "\")";
				if (!iterator.hasNext() && !iteratorAR.hasNext()) {
					sb.append("(singleton " + assoc + "))))"+System.lineSeparator());
				} else {
					sb.append(assoc + " "+System.lineSeparator());
				}
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
			if (node.getType().toString().equals("UA")) {
				UAs.add(node.getName());
			}
		};
		dfs.traverse(ngacGraph.getNode(PC), (c, p) -> {
		}, visitor, Direction.CHILDREN);

		return UAs;
	}

	private void findTClosuresForAllPCs() throws PMException {
		for (String policyClass : ngacGraph.getPolicyClasses()) {
			findTClosureForGraph(policyClass);
		}
	}

	public void readAnyGraph(String path) throws PMException, IOException {
		File file_eligibility_policy = new File(path);
		String eligibility_policy = new String(
				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));

		ngacGraph = new MemGraph();

		GraphSerializer.fromJson(ngacGraph, eligibility_policy);
	}

	private void findTClosureForGraph(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
					|| node.getType().toString().equals("O") || node.getType().toString().equals("OA"))
					) {
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("OA")) {
					tuples.add(new AssignmentRelation(node.getName(), node.getName()).toString());
				}
				for (String parent : ngacGraph.getParents(node.getName())) {
					tuples.add(new AssignmentRelation(node.getName(), parent).toString());
				}
			}
		};
		dfs.traverse(ngacGraph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private void isContainedByAnyPC(String child) throws PMException {
		//System.out.println("CHILD: " + child);
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
			if (!nodes.contains(pc)) {
				nodesWithoutContainment.add(child);
			} else if (nodes.contains(pc) && nodesWithoutContainment.contains(child)) {
				nodesWithoutContainment.remove(child);
				return;
			} else if (nodes.contains(pc)) {
				return;
			}
		}
	}

	private void checkGraph() throws PMException, NodeIsNotContainedByPolicyClassException {
		//System.out.println(GraphSerializer.toJson(ngacGraph));
		for (Node node : ngacGraph.getNodes()) {
			if (!node.getType().toString().equals("PC")) {
				isContainedByAnyPC(node.getName());
			}
		}
		if (!nodesWithoutContainment.isEmpty()) {
			throw new NodeIsNotContainedByPolicyClassException(
					"Every node has to be contained by a policy class. The following nodes are not contained by any policy class: "
							+ nodesWithoutContainment.toString());
		}
	}
}
