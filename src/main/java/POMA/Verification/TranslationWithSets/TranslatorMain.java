package POMA.Verification.TranslationWithSets;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pdp.audit.Auditor;
import gov.nist.csd.pm.pdp.audit.PReviewAuditor;
import gov.nist.csd.pm.pdp.audit.model.Explain;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.graph.model.relationships.Relationship;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.O;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;

import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

import POMA.GlobalVariables;
import POMA.Utils;
import POMA.Exceptions.NoTypeProvidedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;

public class TranslatorMain {
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();
	private String actualOutput=""; 
	private List<String> accessRightsResults;
	private String fullTranslation=""; 
	public String translateGraphOnly(Graph graph) throws Exception{
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();
		return translatedGraph;
	}
	
	public String queryAccessRight(Graph graph, String query) throws Exception {
		String[] splittedQuery = query.split("\\s+");
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();

		translatedGraph += CVC4Translator.getAllAccessRightsCheckBetweenUAandAT(splittedQuery[0], splittedQuery[1]);
		saveDataToFile(translatedGraph, GlobalVariables.swapFile);

		CVC4Runner runner = new CVC4Runner();

		List<String> output = runner.runFromSMTLIB2SetsTheory(GlobalVariables.swapFile);
		System.out.println(processOutput(output, graph));

		return output.toString();
	}

	public String queryAccessRightsEach(Graph graph, String queries) throws Exception {
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();
		String[] lines = queries.split("\\r?\\n");
		List<String[]> inputArray = new ArrayList<String[]>();
		for (String line : lines) {
			inputArray.add(line.split("\\s*,\\s*"));
		}

		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandAT(inputArray);
		setFullTranslation(translatedGraph);

		saveDataToFile(translatedGraph, GlobalVariables.swapFile);
		CVC4Runner runner = new CVC4Runner();
		List<String> output = runner.runFromSMTLIB2SetsTheoryIncremental(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		setActualOutput(runner.getFullOutput());
		return runner.getFullOutput();
	}

	public String queryAccessRightsAllComb(Graph graph, String queries) throws Exception {

		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();

		String[] lines = queries.split("\\r?\\n");
		List<String[]> inputArray = new ArrayList<String[]>();
		for (String line : lines) {
			inputArray.add(line.split("\\s*,\\s*"));
		}
		
		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandATAllComb(inputArray);
		setFullTranslation(translatedGraph);

		saveDataToFile(translatedGraph, GlobalVariables.swapFile);

		CVC4Runner runner = new CVC4Runner();

		List<String> output = runner.runFromSMTLIB2SetsTheory(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		setActualOutput(runner.getFullOutput());

		return output.toString();
	}
	
	public String getAllAccessRights(Graph graph) throws Exception {
		List<String> UA_U = Utils.getNodesByTypes(graph, "UA", "U");
		List<String> U_UA_O_OA = Utils.getNodesByTypes(graph, "OA", "O", "U", "UA");

		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();

		List<String[]> inputArray = new ArrayList<String[]>();
		for (String ua_u : UA_U) {
			for(String oa_o:U_UA_O_OA) {
				inputArray.add(new String[] {ua_u, oa_o});
			}
		}
		
		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandATAllComb(inputArray);
		setFullTranslation(translatedGraph);
		saveDataToFile(translatedGraph, GlobalVariables.swapFile);

		CVC4Runner runner = new CVC4Runner();
		
		List<String> output = runner.runFromSMTLIB2SetsTheory(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		String result = "";
		for(String s : output) {
			result+=s;
			result+=System.lineSeparator();
		}
		setActualOutput(runner.getFullOutput());
		return result;
	}
	
	public String getActualOutput() {
		return actualOutput.replace(",", "");
	}
	private void setActualOutput(String actualOutput) {
		this.actualOutput = actualOutput;
	}
	private List<String> processOutput(List<String> output, Graph graph) throws PMException {
		int i = 1;
		actualOutput="";
	 listOfAssociations = new ArrayList<AssociationRelation>();

		PReviewDecider decider = new PReviewDecider(graph);
		//System.out.println("IMPORTANT ACCESS RIGHT CHECK!: "+decider.list("UA1", "", "UA1"));
		AssociationRelation ar = null;
		for (String s : output) {
			if (!s.contains("sat") && !s.contains("error")) {
			for (String st : s.split("\"")) {
				if (st.contains("(") || st.contains(")") || st.trim().isEmpty()) {
					continue;
				}
				if (i == 1) {
					ar = new AssociationRelation();
					ar.setUA(st);
				}
				if (i == 2)
					ar.addToOperationSet(st);
				if (i == 3) {
					ar.setAT(st);
				//	if(ar.getUA().equals(ar.getAT())) {						
					//	i=1; //||graph.getParents(ar.getUA()).contains(ar.getAT())||graph.getParents(ar.getAT()).contains(ar.getUA())
					//	continue;
				//	}
					updateAssociationRelations(ar);
					i = 1;
					continue;
				}
				i++;
			}}
			else {
				actualOutput += s;
				actualOutput += System.lineSeparator();
			}
		}
		// System.out.println(listOfAssociations);
		return convertAssociationsToString(listOfAssociations);
	}

	private List<String> convertAssociationsToString(List<AssociationRelation> listOfAssociations) {
		List<String> list = new ArrayList<String>();
		for (AssociationRelation ar : listOfAssociations) {
			list.add(ar.toStringOutput());
		}
		return list;
	}

	private void updateAssociationRelations(AssociationRelation associationRelation) {
		if (listOfAssociations.isEmpty()) {
			listOfAssociations.add(associationRelation);
			return;
		}
		for (int i = 0; i < listOfAssociations.size(); i++) {
			AssociationRelation ar = listOfAssociations.get(i);
			if (ar.getUA().equals(associationRelation.getUA()) && ar.getAT().equals(associationRelation.getAT())) {
				ar.addToOperationSet(associationRelation.getOperationSet());
				return;
			}
		}
		listOfAssociations.add(associationRelation);

	}

	public String printAccessRightsForUAandATPair(String UA, String AT) {

		return "";
	}

	public static void main(String[] args) throws Exception {

		String simpleGraphPath = "GPMSPolicies/simpleGraphToSMT.json";
//		 
//		String translatedGraphResultPath = "SMTLIBv2Files/SMTLIB2Input/tclosureTranslatedGraph.smt2";
		TranslatorMain applet = new TranslatorMain();
		Graph graph = Utils.readAnyGraph(simpleGraphPath);

		applet.getAllAccessRights(graph);
		// applet.graph = simpleTestGraph.readAnyGraph("Graphs/NGACExample1.json");
//		//applet.graph = simpleTestGraph.buildSimpleGraph();
//		applet.graph = simpleTestGraph.readAnyGraph(simpleGraphPath);
//		// saveDataToFile(GraphSerializer.toJson(applet.graph), simpleGraphPath);
//		// saveDataToFile(GraphSerializer.toJson(graph), "Graphs/NGACExample1.json");
//		CVC4Translator translator = new CVC4Translator(applet.graph);
//		translator.initTranslation();
//		String translatedGraph = translator.getTranslatedGraph();
//		translatedGraph+=System.lineSeparator();
//		
//		translatedGraph+=CVC4Translator.getAllAccessRightsBetweenUAandAT("U1", "O1");
//
//		//System.out.println(translatedGraph);
//		saveDataToFile(translatedGraph, "SMTLIBv2Files/SMTLIB2Input/tclosureSimpleGraph2.smt2");
//		
//		
//		CVC4Runner runner = new CVC4Runner();
//
//		runner.runFromSMTLIB2AllCombTest("SMTLIBv2Files/SMTLIB2Input/tclosureSimpleGraph2.smt2");
//
//		

//		saveDataToFile(translator.getTranslatedGraph() + "(declare-const access_to_check1 String)\r\n"
//				+ "(declare-const access_to_check2 String)\r\n" + "(declare-const access_to_check3 String)\r\n"
//				+ "(declare-const access_to_check4 String)\r\n" + "\r\n" + "(assert (= \"r\"  access_to_check1))\r\n"
//				+ "(assert (= \"x\"  access_to_check2))\r\n" + "(assert (= \"w\"  access_to_check3))\r\n"
//				+ "(assert (= \"add\"  access_to_check4))\r\n" + "\r\n" + "(declare-fun ar () String)\r\n" + "\r\n"
//				+ "(echo \"U1 O1\")\r\n" + "; Access Right r check\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "\r\n" + "\r\n"
//				+ "(echo \"U1 O2\")\r\n" + "; Access Right r check\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "\r\n" + "(echo \"U2 O2\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "\r\n" + "(echo \"U2 O1\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "\r\n" + "(echo \"U3 O1\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O1\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "(echo \"U3 O2\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U3\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"O2\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "(echo \"U1 U3\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U1\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "\r\n" + "(echo \"U2 U3\")\r\n"
//				+ "; Access Right r check\r\n" + "(push 1)\r\n" + "(assert (= access_to_check1 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check2 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check3 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)\r\n" + "(push 1)\r\n"
//				+ "(assert (= access_to_check4 ar))\r\n"
//				+ "(assert (exists ((relationAssociation association)) (and (= relationAssociation (choose setAssociation)) (member (mkTuple \"U2\" (UA relationAssociation)) Tclosure) (member ar (access_rights relationAssociation)) (member (mkTuple \"U3\" (AT relationAssociation)) Tclosure))))\r\n"
//				+ "(check-sat)\r\n" + "(get-value (ar))\r\n" + "(pop 1)", "SMTLIBv2Files/SMTLIB2Input/tclosureSimpleGraph.smt2");

//		Runtime rt = Runtime.getRuntime();
//		String[] commands = { "CVC4/cvc4.exe", "--incremental", "SMTLIB2Input/tclosureSimpleGraph.smt2" };
//		Process proc = rt.exec(commands);
//
//		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//
//		// Read the output from the command
//		System.out.println("Here is the standard output of the command:\n");
//		String s = null;
//		while ((s = stdInput.readLine()) != null) {
//			if (!s.contains("sat")) {
//				System.out.println(s);
//			}
//
//		}
//
//		// Read any errors from the attempted command
//		System.out.println("Here is the standard error of the command (if any):\n");
//		while ((s = stdError.readLine()) != null) {
//			System.out.println(s);
//		}
//
//		FileWriter writer = new FileWriter("SMTLIB2Input/input.smt2");
//		PrintWriter printWriter = new PrintWriter(writer);
//
//
//    	printWriter.println("(set-logic ALL_SUPPORTED)");
//    	printWriter.println("(set-option :produce-unsat-cores true)");
//    	printWriter.println("(set-option :produce-models true)");
//    	printWriter.println("(declare-fun UA_containment () (Set (Tuple String String)))");
//    	printWriter.println("(declare-fun Tclosure () (Set (Tuple String String)))");
//    	printWriter.println("(declare-fun OA_containment () (Set (Tuple String String)))");
//    	printWriter.println("(declare-fun Tclosure () (Set (Tuple String String)))");
//    	
//    	
//    	printWriter.println("(declare-datatypes ((association 0))\r\n" + 
//    			"   (((rec (UA String) (access_rights (Set String)) (AT String)))))\r\n" + 
//    			"");
//    	printWriter.println("(assert (= UA_containment (insert (mkTuple \"U1\" \"UA1\") (mkTuple \"UA1\" \"UA2\") (mkTuple \"U3\" \"UA3\") (mkTuple \"U2\" \"UA2\") (mkTuple \"UA3\" \"PC1\") (singleton (mkTuple \"UA2\" \"PC1\")))))\r\n" + 
//    			"");
//    	printWriter.println("(assert (= Tclosure (tclosure UA_containment)))");
//    	printWriter.println("(assert (= OA_containment (insert (mkTuple \"O1\" \"OA1\") (mkTuple \"OA1\" \"OA2\") (mkTuple \"O2\" \"OA2\") (mkTuple \"O2\" \"OA2\") (singleton (mkTuple \"OA2\" \"PC1\")))))");
//    	printWriter.println("(assert (= Tclosure (tclosure OA_containment)))");
//    	printWriter.println("(assert (not(member (mkTuple \"U1\" \"UA2\") Tclosure )))");
//    	printWriter.println("(check-sat)");
//    	printWriter.flush();
//    	

//    	Runtime rt = Runtime.getRuntime();
//    	String[] commands = {"CVC4/cvc4.exe", "--incremental", "SMTLIB2Input/input.smt2"};
//    	Process proc = rt.exec(commands);
//
//    	BufferedReader stdInput = new BufferedReader(new 
//    	     InputStreamReader(proc.getInputStream()));
//
//    	BufferedReader stdError = new BufferedReader(new 
//    	     InputStreamReader(proc.getErrorStream()));
//
//    	// Read the output from the command
//    	System.out.println("Here is the standard output of the command:\n");
//    	String s = null;
//    	while ((s = stdInput.readLine()) != null) {
//    		//if(!s.contains("sat")) {
//    			System.out.println(s);
//    		//}
//    		   	
//    	}
//    	
//    	// Read any errors from the attempted command
//    	System.out.println("Here is the standard error of the command (if any):\n");
//    	while ((s = stdError.readLine()) != null) {
//    	    System.out.println(s);
//    	}

	}

	private static void saveDataToFile(String data, String path) throws PMException, IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(data);
		myWriter.close();

	}

	public String getAccessRightsResults() {
		String output = "";
		for(String s : accessRightsResults) {
			output+=s;
			output+=System.lineSeparator();
		}
		return output;
	}

	public void setAccessRightsResults(List<String> accessRightsResults) {
		this.accessRightsResults = accessRightsResults;
	}

	public String getFullTranslation() {
		return fullTranslation;
	}

	public void setFullTranslation(String fullTranslation) {
		this.fullTranslation = fullTranslation;
	}

	public void setAccessRightsResults() {
		this.accessRightsResults = new ArrayList<String>();
	}
}
