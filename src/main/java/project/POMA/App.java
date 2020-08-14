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

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.O;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;

import java.util.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

	
	
	
	public static void main(String[] args) throws PMException, IOException {
		String simpleGraphPath = "Graphs/simpleGraph.json";
		String translatedGraphResultPath = "SMTLIB2Input/tclosureTranslatedGraph.smt2";
		SimpleTestGraph simpleTestGraph = new SimpleTestGraph();
		Graph graph = simpleTestGraph.buildSimpleGraph();		
		graph.createNode("TEST", UA, null, "PC1");
		graph.deassign("TEST", "PC1");
		saveDataToFile(GraphSerializer.toJson(graph), simpleGraphPath);
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();

		saveDataToFile(translator.getTranslatedGraph()+"(declare-const access_to_check1 String)\r\n" + 
				"(declare-const access_to_check2 String)\r\n" + 
				"(declare-const access_to_check3 String)\r\n" + 
				"(declare-const access_to_check4 String)\r\n" + 
				"\r\n" + 
				"(assert (= \"r\"  access_to_check1))\r\n" + 
				"(assert (= \"x\"  access_to_check2))\r\n" + 
				"(assert (= \"w\"  access_to_check3))\r\n" + 
				"(assert (= \"add\"  access_to_check4))\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				";Access Rights U1 has on O1\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U1 has on O1--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				";Access Rights U1 has on O2\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U1 has on O2--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				";Access Rights U2 has on O1\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U2 has on O1--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O1\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O1\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O1\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				";Access Rights U2 has on O2\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U2 has on O2--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"O2\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"O2\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"O2\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				";Access Rights U1 has on U3\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U1 has on UA3--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U1\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				";----------------------------------------------------------------------------------------------------------------------------------------------------------------\r\n" + 
				"\r\n" + 
				";Access Rights U2 has on U3\r\n" + 
				"(push 1)\r\n" + 
				"(declare-const output String)\r\n" + 
				"(assert (= \"Access Rights U2 has on UA3--------------------------------------------------------\" output))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-value (output))\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right r check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named r))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right x check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named x))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"; Access Right w check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named w))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"; Access Right add check\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple \"U3\" (AT ua2_oa2)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple \"U3\" (AT ua1_oa1)) OA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)\r\n" + 
				"\r\n" + 
				"(push 1)\r\n" + 
				"(assert (!(not(and (member (mkTuple \"U2\" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple \"U3\" (AT ua1_ua3)) UA_tclosure))) :named add))\r\n" + 
				"(check-sat)\r\n" + 
				"(get-unsat-core)\r\n" + 
				"(pop 1)", "SMTLIB2Input/tclosureSimpleGraph.smt2");

		CVC4Runner runner = new CVC4Runner();
		
		runner.runFromSMTLIB2("SMTLIB2Input/tclosureSimpleGraph.smt2");
		
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
//    	printWriter.println("(declare-fun UA_tclosure () (Set (Tuple String String)))");
//    	printWriter.println("(declare-fun OA_containment () (Set (Tuple String String)))");
//    	printWriter.println("(declare-fun OA_tclosure () (Set (Tuple String String)))");
//    	
//    	
//    	printWriter.println("(declare-datatypes ((association 0))\r\n" + 
//    			"   (((rec (UA String) (access_rights (Set String)) (AT String)))))\r\n" + 
//    			"");
//    	printWriter.println("(assert (= UA_containment (insert (mkTuple \"U1\" \"UA1\") (mkTuple \"UA1\" \"UA2\") (mkTuple \"U3\" \"UA3\") (mkTuple \"U2\" \"UA2\") (mkTuple \"UA3\" \"PC1\") (singleton (mkTuple \"UA2\" \"PC1\")))))\r\n" + 
//    			"");
//    	printWriter.println("(assert (= UA_tclosure (tclosure UA_containment)))");
//    	printWriter.println("(assert (= OA_containment (insert (mkTuple \"O1\" \"OA1\") (mkTuple \"OA1\" \"OA2\") (mkTuple \"O2\" \"OA2\") (mkTuple \"O2\" \"OA2\") (singleton (mkTuple \"OA2\" \"PC1\")))))");
//    	printWriter.println("(assert (= OA_tclosure (tclosure OA_containment)))");
//    	printWriter.println("(assert (not(member (mkTuple \"U1\" \"UA2\") UA_tclosure )))");
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
