package project.POMA;

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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;
public class App extends
JApplet{
	MemGraph graph;
	mxGraphComponent component;
	private static final Dimension DEFAULT_SIZE = new Dimension(1800, 1000);
	DirectedGraph<String,Relationship> graphToPlot;
    private JGraphXAdapter<String, Relationship> jgxAdapter;
	public static void main(String[] args) throws PMException, IOException {
		
		
		
		String simpleGraphPath = "Graphs/simpleGraph.json";
		String translatedGraphResultPath = "SMTLIB2Input/tclosureTranslatedGraph.smt2";
		SimpleTestGraph simpleTestGraph = new SimpleTestGraph();
		App applet = new App();
		//applet.graph = simpleTestGraph.readAnyGraph("Graphs/NGACExample1.json");
		applet.graph = simpleTestGraph.buildSimpleGraph();

		applet.graphToPlot = applet.graph.graph;
		
		
		
        applet.init();

        JFrame frame = new JFrame();
       // frame.setLayout( new GridBagLayout() );
        frame.getContentPane().add(applet);
        frame.setTitle("NGAC Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
       // frame.add(applet.component, new GridBagConstraints());
		
		
		
		
		
		
		//saveDataToFile(GraphSerializer.toJson(applet.graph), simpleGraphPath);
		//saveDataToFile(GraphSerializer.toJson(graph), "Graphs/NGACExample1.json");
		//CVC4Translator translator = new CVC4Translator(applet.graph);
		//translator.initTranslation();

//		saveDataToFile(translator.getTranslatedGraph()+"(declare-const access_to_check1 String)\r\n" + 
//				"(declare-const access_to_check2 String)\r\n" + 
//				"(declare-const access_to_check3 String)\r\n" + 
//				"(declare-const access_to_check4 String)\r\n" + 
//				"\r\n" + 
//				"(assert (= \"r\"  access_to_check1))\r\n" + 
//				"(assert (= \"x\"  access_to_check2))\r\n" + 
//				"(assert (= \"w\"  access_to_check3))\r\n" + 
//				"(assert (= \"add\"  access_to_check4))\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				";Access Rights U1 has on O1\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U1 has on O1--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				"\r\n" + 
//				";Access Rights U1 has on O2\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U1 has on O2--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				"\r\n" + 
//				";Access Rights U2 has on O1\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U2 has on O1--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				"\r\n" + 
//				";Access Rights U2 has on O2\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U2 has on O2--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				"\r\n" + 
//				";Access Rights U1 has on U3\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U1 has on UA3--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
//				"\r\n" + 
//				";Access Rights U2 has on U3\r\n" + 
//				"(push 1)\r\n" + 
//				"(declare-const output String)\r\n" + 
//				"(assert (= \"Access Rights U2 has on UA3--------------------------------------------------------\" output))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-value (output))\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right r check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named r))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right x check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named x))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"\r\n" + 
//				"; Access Right w check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named w))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"; Access Right add check\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)\r\n" + 
//				"\r\n" + 
//				"(push 1)\r\n" + 
//				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) Tclosure))) :named add))\r\n" + 
//				"(check-sat)\r\n" + 
//				"(get-unsat-core)\r\n" + 
//				"(pop 1)", "SMTLIB2Input/tclosureSimpleGraph.smt2");

		CVC4Runner runner = new CVC4Runner();
		
	//	runner.runFromSMTLIB2("SMTLIB2Input/tclosureSimpleGraph.smt2");
		
//		App app = new App();
//		File super_config = new File("Graphs/super_config.json");
//		File file_eligibility_policy = new File("Graphs/EligibilityPolicyClass.json");
//		String super_policy = new String(Files.readAllBytes(Paths.get(super_config.getAbsolutePath())));
//		String eligibility_policy = new String(
//				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));
//
//		Graph ngacGraph = new MemGraph();
//		
//
//		GraphSerializer.fromJson(ngacGraph, super_policy);
//		GraphSerializer.fromJson(ngacGraph, eligibility_policy);

//		ngacGraph.createPolicyClass("PC2", null);
//		
//		ngacGraph.createNode("OA1", OA, null, "PC2");
//		ngacGraph.createNode("O1", O, null, "OA1");
//		ngacGraph.associate("PIEligible", "OA1", new OperationSet("w"));

		// ngacGraph.deassign("OA1", "PC2");
		// ngacGraph.assign("OA1", "EligibilityPolicyClass");
		// System.out.println(ngacGraph.getNode("OA1").toString());

//		PReviewDecider decider = new PReviewDecider(ngacGraph);

//		
//		for(String s : decider.list("PIEligible", "", "O1")) {
//			System.out.println(s);
//		}
//		

		// ngacGraph.getSourceAssociations(source);

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
	@Override
    public void init()
    {
        // create a JGraphT graph
       // ListenableGraph<String, Relationship> g =
        //    new DefaultListenableGraph<String,Relationship>(new DirectedGraph<String,Relationship>(Relationship.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(this.graphToPlot);
        
        	 for (Map.Entry<Relationship,mxICell> entry : jgxAdapter.getEdgeToCellMap().entrySet()) { 
                 
                 if(!entry.getKey().toString().equals("")) {
                	 System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue().toString()); 
                     mxStylesheet stylesheet = jgxAdapter.getStylesheet();
                     List<mxICell> itemList = new ArrayList<mxICell>();
                     itemList.add(entry.getValue());
                	 Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
                //	 edgeStyle.put(mxConstants.STYLE_DASHED, true);
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "red",itemList.toArray());

                	 jgxAdapter.setCellStyles(mxConstants.STYLE_DASHED,"true" ,itemList.toArray());
                 }
        	 }
        	 List<mxICell> UList = new ArrayList<mxICell>();
    		 List<mxICell> UAList = new ArrayList<mxICell>();
    		 List<mxICell> OAList = new ArrayList<mxICell>();
    		 List<mxICell> OList = new ArrayList<mxICell>();
    		 List<mxICell> PCList = new ArrayList<mxICell>();

        	 for (Map.Entry<String,mxICell> entry : jgxAdapter.getVertexToCellMap().entrySet()) { 
        		 
                 if(!entry.getKey().toString().equals("")) {
                	 try {
						if(graph.getNode(entry.getKey().toString()).getType().toString().equals("U")) {
							UList.add(entry.getValue());
						 }
						if(graph.getNode(entry.getKey().toString()).getType().toString().equals("UA")) {
							UAList.add(entry.getValue());
						 }
						if(graph.getNode(entry.getKey().toString()).getType().toString().equals("OA")) {
							OAList.add(entry.getValue());
						 }
						if(graph.getNode(entry.getKey().toString()).getType().toString().equals("O")) {
							OList.add(entry.getValue());
						 }
						if(graph.getNode(entry.getKey().toString()).getType().toString().equals("PC")) {
							PCList.add(entry.getValue());
						 }
					} catch (PMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	 System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue().toString()); 
                     mxStylesheet stylesheet = jgxAdapter.getStylesheet();
                     List<mxICell> itemList = new ArrayList<mxICell>();
                     itemList.add(entry.getValue());
                	 Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
                	 //fillColor=green
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FILLCOLOR,"yellow" ,UList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_ELLIPSE ,UList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_ROUNDED,"true" ,UList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_GRADIENTCOLOR,"AFAFFF" ,UList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_OPACITY,"50" ,UList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "black",UList.toArray());
                	 
                	 
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FILLCOLOR,"yellow" ,UAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_RECTANGLE ,UAList.toArray());
                	 //jgxAdapter.setCellStyles(mxConstants.STYLE_ROUNDED,"true" ,UAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_GRADIENTCOLOR,"AFAFFF" ,UAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_OPACITY,"50" ,UAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "black",UAList.toArray());

                	 
                	 
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FILLCOLOR,"red" ,OList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_ELLIPSE ,OList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_ROUNDED,"true" ,OList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_GRADIENTCOLOR,"AFAFFF" ,OList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_OPACITY,"50" ,OList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "black",OList.toArray());

                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FILLCOLOR,"red" ,OAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_RECTANGLE ,OAList.toArray());
                	// jgxAdapter.setCellStyles(mxConstants.STYLE_ROUNDED,"true" ,OAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_GRADIENTCOLOR,"AFAFFF" ,OAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_OPACITY,"50" ,OAList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "black",OAList.toArray());
                	 
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FILLCOLOR,"green" ,PCList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_RECTANGLE ,PCList.toArray());
                	 //jgxAdapter.setCellStyles(mxConstants.STYLE_ROUNDED,"true" ,PCList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_GRADIENTCOLOR,"AFAFFF" ,PCList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_OPACITY,"50" ,PCList.toArray());
                	 jgxAdapter.setCellStyles(mxConstants.STYLE_FONTCOLOR, "black",PCList.toArray());
                 }
        	 }
        	   	 
       double  widthLayout = (double)(DEFAULT_SIZE.width / 2.0);
       double heightLayout = (double)(DEFAULT_SIZE.width / 2.0);;
        setPreferredSize(DEFAULT_SIZE);
        
        mxStylesheet stylesheet = jgxAdapter.getStylesheet();
        Map<String, Object> nodeStyle = stylesheet.getDefaultVertexStyle();
        nodeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        nodeStyle.put(mxConstants.STYLE_ROUNDED, true);
        nodeStyle.put(mxConstants.STYLE_OPACITY, 50);
        nodeStyle.put(mxConstants.STYLE_FILLCOLOR, "#EFEFFF");
        nodeStyle.put(mxConstants.STYLE_GRADIENTCOLOR, "#AFAFFF");
        nodeStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
       // 

        stylesheet.setDefaultVertexStyle(nodeStyle);
        Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
        edgeStyle.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ELBOW);
        edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2.0);
       // edgeStyle.put(mxConstants.STYLE_ROUNDED, true);
        stylesheet.setDefaultEdgeStyle(edgeStyle);
        Map<String, Object> startStyle = new HashMap<>();
        startStyle.put(mxConstants.STYLE_STROKEWIDTH, 3.0);
        startStyle.put(mxConstants.STYLE_STROKECOLOR, "#0000F0");
        stylesheet.putCellStyle("StartStyle", startStyle);
         component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        add(component, BorderLayout.CENTER);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);
       // component.zoomOut();
       // component.zoomOut();
        //component.zoomOut();
        //component.zoomOut();

        //set new geometry
        jgxAdapter.getModel().setGeometry(jgxAdapter.getDefaultParent(), 
                new mxGeometry((widthLayout)/2, (30),
                        widthLayout, heightLayout));
        // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        //mxParallelEdgeLayout layout = new mxParallelEdgeLayout(jgxAdapter);
        // center the circle
//        int radius = 400;
//        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
//        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
//        layout.setRadius(radius);
//        layout.setMoveCircle(true);

       //layout.execute(jgxAdapter.getDefaultParent());
        getContentPane().add(BorderLayout.CENTER, component);
        mxHierarchicalLayout layout1 = new mxHierarchicalLayout(jgxAdapter);
        add(component, BorderLayout.CENTER);
        component.setZoomPolicy(5);;
        layout1.setInterHierarchySpacing(100);
        layout1.setIntraCellSpacing(100);
        layout1.setInterRankCellSpacing(100);
        layout1.setParallelEdgeSpacing(25);;

       // layout.execute(jgxAdapter.getDefaultParent());
        layout1.execute(jgxAdapter.getDefaultParent());
        new mxParallelEdgeLayout(jgxAdapter, 20).execute(jgxAdapter.getDefaultParent());

    }
}
