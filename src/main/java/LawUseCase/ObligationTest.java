package LawUseCase;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import LawUseCase.customEvents.AcceptEvent;
import LawUseCase.customEvents.ApproveEvent;

import LawUseCase.customFunctions.EqualsExecutor;
import POMA.TestSuitGeneration.Utils;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gpms.policy.Constants;
import gpms.policy.customEvents.*;
import gpms.policy.customFunctions.*;

public class ObligationTest {
	Obligation obligation = new Obligation();

	// Graph
	Graph ngacGraph = new MemGraph();
	Prohibitions prohibitions = new MemProhibitions();

	@Before
	public void setUp() throws Exception {
		File lawfirmpolicyfile = new File("GPMSPolicies/LawUseCase/LawFirmPolicy.json");
		File casepolicyfile = new File("GPMSPolicies/LawUseCase/CasePolicy.json");
		File valuetypepolicyfile = new File("GPMSPolicies/LawUseCase/ValueTypePolicy.json");
		File prohibitionsfile = new File("GPMSPolicies/LawUseCase/prohibitions.json");

		String lawfirmpolicy = new String(Files.readAllBytes(Paths.get(lawfirmpolicyfile.getAbsolutePath())));
		String casepolicy = new String(Files.readAllBytes(Paths.get(casepolicyfile.getAbsolutePath())));
		String valuetypepolicy = new String(Files.readAllBytes(Paths.get(valuetypepolicyfile.getAbsolutePath())));
		String prohibitionsJSON = new String(Files.readAllBytes(Paths.get(prohibitionsfile.getAbsolutePath())));

		GraphSerializer.fromJson(ngacGraph, lawfirmpolicy);
		GraphSerializer.fromJson(ngacGraph, casepolicy);
		GraphSerializer.fromJson(ngacGraph, valuetypepolicy);
		ProhibitionsSerializer.fromJson(prohibitions, prohibitionsJSON);
		File obligationFile = new File("GPMSPolicies/LawUseCase/Obligations.yml");
		InputStream is = new FileInputStream(obligationFile);
		obligation = EVRParser.parse(is);

	}

	
	@Test
	public void prohibitionsTest() throws Exception {
		//PReviewDecider decider = new PReviewDecider(ngacGraph, prohibitions);
		PReviewDecider decider = new PReviewDecider(ngacGraph);
		//System.out.println(decider.getCapabilityList("C-Suit",""));
		//System.out.println(GraphSerializer.toJson(ngacGraph));

		assertTrue(decider.check("HR", "", "HR", "fire"));
		assertTrue(decider.check("C-Suit", "", "C-Suit", "fire"));
		assertTrue(decider.check("LeadAttorneys", "", "LeadAttorneys", "fire"));
		assertTrue(decider.check("LA1", "", "MainOffice", "hire"));
		assertTrue(decider.check("C-Suit", "", "MainOffice", "hire"));
		assertTrue(decider.check("LeadAttorneys", "", "MainOffice", "hire"));

	}
	
	@Test
	public void caseApprovalAFirst() throws Exception {
		PReviewDecider decider = new PReviewDecider(ngacGraph, prohibitions);
		PDP pdp = getPDP(ngacGraph, prohibitions, obligation);
		pdp.getEPP().processEvent(new AcceptEvent(ngacGraph.getNode("Case3Info")), "A1", "initialAccept");
		assertFalse(decider.check("Attorneys", "", "Case3", "accept"));
		assertFalse(decider.check("Attorneys", "", "Case3", "refuse"));
		assertTrue(decider.check("LA1", "", "Case3Info", "accept"));
		assertFalse(decider.check("C-Suit", "", "Case3", "approve"));
		pdp.getEPP().processEvent(new AcceptEvent(ngacGraph.getNode("Case3Info")), "LA1", "finalAccept");
		assertFalse(decider.check("Attorneys", "", "Case3", "accept"));
		assertFalse(decider.check("Attorneys", "", "Case3", "refuse"));
		assertTrue(decider.check("C-Suit", "", "Case3", "approve"));
		pdp.getEPP().processEvent(new ApproveEvent(ngacGraph.getNode("Case3Info")), "C1", "approve");
		assertTrue(ngacGraph.getChildren("GeneralInfo").contains("Case3"));
		assertFalse(decider.check("C-Suit", "", "Case3", "approve"));
	}
	@Test
	public void caseApprovalLAFirst() throws Exception {
		PReviewDecider decider = new PReviewDecider(ngacGraph, prohibitions);
		PDP pdp = getPDP(ngacGraph, prohibitions, obligation);
		assertTrue(decider.check("LA1", "", "Case3Info", "accept"));
		assertTrue(ngacGraph.getParents("LA1").contains("LeadAttorneys"));
		pdp.getEPP().processEvent(new AcceptEvent(ngacGraph.getNode("Case3Info")), "LA1", "initialAccept");		
		assertFalse(decider.check("LA1", "", "Case3Info", "accept"));
		assertFalse(decider.check("C-Suit", "", "Case3", "approve"));
		pdp.getEPP().processEvent(new AcceptEvent(ngacGraph.getNode("Case3Info")), "A1", "finalAccept");
		assertFalse(decider.check("Attorneys", "", "Case3", "accept"));
		assertFalse(decider.check("Attorneys", "", "Case3", "refuse"));
		assertTrue(decider.check("C-Suit", "", "Case3", "approve"));
		pdp.getEPP().processEvent(new ApproveEvent(ngacGraph.getNode("Case3Info")), "C1", "approve");
		assertTrue(ngacGraph.getChildren("GeneralInfo").contains("Case3"));
		assertFalse(decider.check("C-Suit", "", "Case3", "approve"));

	}


	public static PDP getPDP(Graph graph, Prohibitions prohibitions, Obligation obligation) throws Exception {
		EqualsExecutor equalsExecutor = new EqualsExecutor();
		EPPOptions eppOptions = new EPPOptions(equalsExecutor);
		
		PDP pdp = new PDP(new PAP(graph, prohibitions, new MemObligations()), eppOptions);
		if (graph.exists("super_pc_rep")) {
			graph.deleteNode("super_pc_rep");
		}
		pdp.getPAP().getObligationsPAP().add(obligation, true);
		return pdp;
	}
}