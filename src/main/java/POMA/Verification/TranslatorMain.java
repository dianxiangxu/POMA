package POMA.Verification;

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

import java.util.*;
import java.util.List;

import org.jgrapht.graph.DefaultDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

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
	MemGraph graph;

	public static void main(String[] args) throws PMException, IOException, NoTypeProvidedException {

		// String simpleGraphPath = "GPMSPolicies/simpleGraphToSMT.json";
		 String simpleGraphPath = "GPMSPolicies/simpleGraphToSMT.json";
		 
		String translatedGraphResultPath = "SMTLIBv2Files/SMTLIB2Input/tclosureTranslatedGraph.smt2";
		SimpleTestGraph simpleTestGraph = new SimpleTestGraph();
		TranslatorMain applet = new TranslatorMain();
		// applet.graph = simpleTestGraph.readAnyGraph("Graphs/NGACExample1.json");
		//applet.graph = simpleTestGraph.buildSimpleGraph();
		applet.graph = simpleTestGraph.readAnyGraph(simpleGraphPath);
		// saveDataToFile(GraphSerializer.toJson(applet.graph), simpleGraphPath);
		// saveDataToFile(GraphSerializer.toJson(graph), "Graphs/NGACExample1.json");
		CVC4Translator translator = new CVC4Translator(applet.graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		//System.out.println(translatedGraph);
		saveDataToFile(translatedGraph, "SMTLIBv2Files/SMTLIB2Input/tclosureSimpleGraph.smt2");
		
		
		CVC4Runner runner = new CVC4Runner();

		runner.runFromSMTLIB2("SMTLIBv2Files/SMTLIB2Input/tclosureSimpleGraph.smt2");

		
		
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
}
