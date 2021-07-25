package project.POMA;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CaseStudies.LawUseCase.customEvents.AcceptEvent;
import CaseStudies.LawUseCase.customEvents.ApproveEvent;
import CaseStudies.LawUseCase.customEvents.CreateEvent;
import CaseStudies.LawUseCase.customEvents.DisapproveEvent;
import CaseStudies.LawUseCase.customEvents.RefuseEvent;
import CaseStudies.LawUseCase.customEvents.WithdrawEvent;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

public class testTest {
    Graph ngacGraph = new MemGraph();
    Obligation obligation = new Obligation();

    @Test
	public void randomTest() throws Exception {
		PReviewDecider decider = new PReviewDecider(ngacGraph);
        File obligationFile = new File("Policies/TEST/Obligations.yml");
        InputStream is = new FileInputStream(obligationFile);
        obligation = EVRParser.parse(is);
		PDP pdp = getPDP(ngacGraph, obligation);
        ngacGraph.createPolicyClass("pc", null);

        ngacGraph.createNode("c", UA, null, "pc");
        ngacGraph.createNode("b", UA, null, "c");
        ngacGraph.createNode("a", UA, null, "b");
        ngacGraph.assign("a", "c");

	//	assertFalse(ngacGraph.getChildren("Case3").contains("Case3Info"));
		//pdp.getEPP().processEvent(new AcceptEvent(ngacGraph.getNode("a")), "a", "initialCreate");
	//	assertTrue(ngacGraph.getChildren("Case3").contains("Case3Info"));
	//	assertFalse(decider.check("HR1", "", "HR", "hire", "fire"));
       System.out.println( GraphSerializer.toJson(ngacGraph));
	}

    static PDP getPDP(Graph graph, Obligation obligation) throws Exception {
        EPPOptions eppOptions = new EPPOptions();

        PDP pdp = new PDP(new PAP(graph, null, new MemObligations()), eppOptions);
        if (graph.exists("super_pc_rep")) {
            graph.deleteNode("super_pc_rep");
        }
        pdp.getPAP().getObligationsPAP().add(obligation, true);
        return pdp;
    }
}
