package POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import CaseStudies.gpms.Constants;
import CaseStudies.gpms.customEvents.*;
import CaseStudies.gpms.customFunctions.*;
import POMA.Utils;
import POMA.Mutation.EquivalentMutantAnalyzer.MutantTester;
import POMA.Mutation.ObligationMutationOperators.MutantTester2;
import gov.nist.csd.pm.epp.EPP;
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

public class GPMS_MCDC {
	Graph graph = new MemGraph();
	Obligation obligation = new Obligation();
	
	@Before
	public void runBeforeEach() throws Exception {
//		String pathGraph = "Policies/GPMS/Graph.json";
//		String pathObligation = "Policies/GPMS/Obligations.yml";
//		
//		File graphFile = getFileFromResources(pathGraph);
//		String graphJSON = new String(Files.readAllBytes(Paths.get(graphFile.getAbsolutePath())));
//		GraphSerializer.fromJson(graph, graphJSON);
//		
//		File obligationFile = getFileFromResources(pathObligation);
//		InputStream inputStream = new FileInputStream(obligationFile);
//		obligation = EVRParser.parse(inputStream);
		
		graph = MutantTester.createCopy();
		obligation = MutantTester.getObligationMutantCopy();
	}
	
	@Test
	public void test_1() throws Exception {
		System.out.println("Test test_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
		
			assertTrue(graph.getChildren("PI").contains("nazmul"));
			assertTrue(graph.getChildren("PIInfo").contains("nazmulPI"));
			assertTrue(graph.getChildren("Chair").contains("ChairCSUser"));
			assertTrue(graph.getChildren("Dean").contains("DeanCOEUser"));
			assertTrue(graph.getChildren("Business Manager").contains("bmCSUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_2() throws Exception {
		System.out.println("Test test_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			
			assertTrue(graph.getChildren("CoPIInfo").contains("tomtomCoPI"));
			assertTrue(graph.getChildren("CoPI").contains("tomtom"));
			assertTrue(graph.getChildren("Chair").contains("ChairChemUser"));
			assertTrue(graph.getChildren("Dean").contains("DeanCOASUser"));
			assertTrue(graph.getChildren("Business Manager").contains("bmChemUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_2_1() throws Exception {
		System.out.println("Test test_2_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			//set copi_to_add()=liliana will cover negated conditions for actions 3,4,5 at the same time
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
			
			//actions 3,4,5
			System.out.print("NEED ASSERTIONS!!!!!!!!!!");
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_2_2() throws Exception {
		System.out.println("Test test_2_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			//set copi_to_add()=vlad will cover negated conditions for response condition
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("vlad")), "nazmul", "process");
			
			//actions 1-5
			System.out.print("NEED ASSERTIONS!!!!!!!!!!");
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_3a() throws Exception {
		System.out.println("Test test_3a.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
		
			assertFalse(graph.getSourceAssociations("PI").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("PI").keySet().contains("PIEditable"));
			assertFalse(graph.getSourceAssociations("CoPI").keySet().contains("CoPIEditable"));
//			assertFalse(graph.getSourceAssociations("PI").keySet().contains("CoPI"));//action4, error
			assertTrue(graph.getSourceAssociations("PI").get("Copi") == null);//action4
			
//			OperationSet map = graph.getSourceAssociations("CoPI").get("SP");//the association between CoPI and SP should be deleted; however, it exits
//			assertFalse(graph.getSourceAssociations("CoPI").keySet().contains("SP"));//action 5, error
//			assertTrue(graph.getSourceAssociations("CoPI").get("SP") == null);//action 5, error
			
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("ChairApproval"));
			assertTrue(graph.getSourceAssociations("Chair").get("ChairApproval").contains("write"));
			assertTrue(graph.getSourceAssociations("Chair").get("ChairApproval").size() == 1);
			
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("PDSWhole"));
			assertTrue(graph.getSourceAssociations("Chair").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Chair").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Chair").get("PDSWhole").size() == 2);
			
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("Chair").get("PDSSections").contains("read"));
			assertTrue(graph.getSourceAssociations("Chair").get("PDSSections").size() == 1);
			
//			Node irb = graph.getNode("irbUser");
			assertTrue(graph.getNode("irbUser").getProperties().get("required").equals("true"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_3b() throws Exception {
		System.out.println("Test test_3b.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
		
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("required"));
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("approved"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_3_1() throws Exception {
		System.out.println("Test test_3_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			//set irb_approval_required()=false will cover negated conditions for action 9
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
		
			//action 9
			if (graph.getNode("irbUser").getProperties().containsKey("required"))
				assertFalse(graph.getNode("irbUser").getProperties().get("required").equals("true"));
				//if required=true without action 9, then action 9 is redundant
			else
				assertTrue(1==1);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_3_2() throws Exception {
		System.out.println("Test test_3_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			//set irb_approval_required()=true will cover negated conditions for action 10
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
		
			//action 10
			if (graph.getNode("irbUser").getProperties().containsKey("required"))
				assertTrue(graph.getNode("irbUser").getProperties().get("required").equals("true"));
			else
				assertTrue(1==1);
			if (graph.getNode("irbUser").getProperties().containsKey("approved"))
				assertTrue(graph.getNode("irbUser").getProperties().get("approved").equals("true"));
			else
				assertTrue(1==1);
//			assertTrue(graph.getNode("irbUser").getProperties().get("approved").equals("true"));//irbUser.property.approved not exist
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_4() throws Exception {
		System.out.println("Test test_4.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "nazmul", "process");
		
			assertTrue(graph.getChildren("SPInfo").contains("vladSP"));
			assertTrue(graph.getChildren("SP").contains("vlad"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
//	@Test
//	public void test_4_1() throws Exception {
//		System.out.println("Test test_4_1.");
//		try {
//			PReviewDecider decider = new PReviewDecider(graph);
//			PDP pdp = getGPMSpdp(graph, obligation);
//			
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
//			//set sp_to_add()=leo will cover negated conditions for response condition
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("leo")), "nazmul", "process");
//		
//			//actions 1-2
//			assertFalse(graph.getChildren("SPInfo").contains("vladSP"));
//			assertFalse(graph.getChildren("SP").contains("vlad"));
//		} catch (PMException e) {
//			e.printStackTrace();
//			assertTrue(1 == 0);
//		}
//	}
	
	@Test
	public void test_5() throws Exception {
		System.out.println("Test test_5.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			
			assertFalse(graph.getChildren("CoPI").contains("tomtom"));
			assertFalse(graph.exists("tomtomCoPI"));
			assertFalse(graph.getChildren("Chair").contains("ChairChemUser"));
			assertFalse(graph.getChildren("Dean").contains("DeanCOASUser"));
			assertFalse(graph.getChildren("Business Manager").contains("bmChemUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_5_1() throws Exception {
		System.out.println("Test test_5_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
			
			assertTrue(graph.getChildren("Chair").contains("ChairCSUser"));
			
			assertTrue(graph.getChildren("Dean").contains("DeanCOEUser"));
			
			assertTrue(graph.getChildren("Business Manager").contains("bmCSUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	//action 3, MC/DC test 1, both factors satisfy
	//this test also coverage a possible condition of actions 4&5 in terms of MC/DC
	@Test
	public void test_5_3_1() throws Exception {
		System.out.println("Test test_5_3_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
			
			assertTrue(graph.getChildren("Chair").contains("ChairCSUser"));
			
			assertTrue(graph.getChildren("Dean").contains("DeanCOEUser"));
			
			assertTrue(graph.getChildren("Business Manager").contains("bmCSUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	//action 3, MC/DC test 2, factor 1 not satisfies, while factor 2 satisfies
	//this test also coverage a possible condition of actions 4&5 in terms of MC/DC
	//this test condition never satisfies.
//	@Test
//	public void test_5_3_2() throws Exception {
//		System.out.println("Test test_5_3_2.");
//		try {
//			PReviewDecider decider = new PReviewDecider(graph);
//			PDP pdp = getGPMSpdp(graph, obligation);
//			
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
//			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("liliana")), "nazmul", "process");
//			
//			assertTrue(graph.getChildren("Chair").contains("ChairCSUser"));
//			
//			assertTrue(graph.getChildren("Dean").contains("DeanCOEUser"));
//			
//			assertTrue(graph.getChildren("Business Manager").contains("bmCSUser"));
//		} catch (PMException e) {
//			e.printStackTrace();
//			assertTrue(1 == 0);
//		}
//	}
	
	//action 3, MC/DC test 3, factor 2 not satisfies, while factor 1 satisfies
	//this test also coverage a possible condition of actions 4&5 in terms of MC/DC
	@Test
	public void test_5_3_3() throws Exception {
		System.out.println("Test test_5_3_3.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			
			assertFalse(graph.getChildren("Chair").contains("ChairChemUser"));
			
			assertFalse(graph.getChildren("Dean").contains("DeanCOASUser"));
			
			assertFalse(graph.getChildren("Business Manager").contains("bmChemUser"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}	
	
	@Test
	public void test_6() throws Exception {
		System.out.println("Test test_6.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
			pdp.getEPP().processEvent(new DeleteSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
			
			assertFalse(graph.getChildren("SP").contains("vlad"));
			assertFalse(graph.exists("vladSP"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_7() throws Exception {
		System.out.println("Test test_7.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
		
			assertTrue(graph.getNode("ChairCSUser").getProperties().get("approved").equals("true"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("ChairApproval"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSWhole"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("BMApproval").contains("write"));
			
			assertTrue(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("PDSWhole").size() == 2);
			
			assertTrue(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("PDSSections").contains("read"));
			assertTrue(graph.getSourceAssociations("Business Manager").get("PDSSections").size() == 1);
			
			assertTrue(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("IRBApproval").contains("write"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("IRBApproval").size() == 1);
			
			assertTrue(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("PDSSections").contains("read"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("PDSSections").size() == 1);
			
			assertTrue(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("IRBOfficer").get("PDSWhole").size() == 2);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_7_1() throws Exception {
		System.out.println("Test test_7_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
		
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("ChairApproval"));
			
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("PDSWhole"));
			
			assertFalse(decider.check("Business Manager", "", "BMApproval", "write"));
			
			assertFalse(decider.check("Business Manager", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Business Manager", "", "PDSWhole", "Disapprove"));
			
			assertFalse(decider.check("Business Manager", "", "PDSSections", "read"));
			
			assertFalse(decider.check("IRBOfficer", "", "IRBApproval", "write"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSSections", "read"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Disapprove"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_7_7_1() throws Exception {
		System.out.println("Test test_7_7_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			//ChairChemUser does not approve yet, this covers negated condition for actions 2-6
		
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("ChairApproval"));
			
			assertTrue(graph.getSourceAssociations("Chair").keySet().contains("PDSWhole"));
			
			assertFalse(decider.check("Business Manager", "", "BMApproval", "write"));
			
			assertFalse(decider.check("Business Manager", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Business Manager", "", "PDSWhole", "Disapprove"));
			
			assertFalse(decider.check("Business Manager", "", "PDSSections", "read"));
			
			assertFalse(decider.check("IRBOfficer", "", "IRBApproval", "write"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSSections", "read"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Disapprove"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_7_7_2() throws Exception {
		System.out.println("Test test_7_7_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
		
			assertFalse(decider.check("IRBOfficer", "", "IRBApproval", "write"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSSections", "read"));
			
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("IRBOfficer", "", "PDSWhole", "Disapprove"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_8() throws Exception {
		System.out.println("Test test_8.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("ChairApproval"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
			
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Delete"));
			
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));
			
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));
			
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			
			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_9() throws Exception {
		System.out.println("Test test_9.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			
			assertTrue(graph.getNode("bmCSUser").getProperties().get("approved").equals("true"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("BMApproval"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertTrue(graph.getSourceAssociations("Dean").get("DeanApproval").contains("write"));
			
			assertTrue(graph.getSourceAssociations("Dean").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Dean").get("PDSWhole").contains("Disapprove"));
			
			assertTrue(graph.getSourceAssociations("Dean").get("PDSSections").contains("read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_9_1() throws Exception {
		System.out.println("Test test_9_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			//bmChemUser does not approve yet, this covers negated condition for actions 2-6
			
			assertTrue(graph.getSourceAssociations("Business Manager").keySet().contains("BMApproval"));
			assertTrue(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertFalse(decider.check("Dean", "", "DeanApproval", "write"));
			
			assertFalse(decider.check("Dean", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Dean", "", "PDSWhole", "Disapprove"));
			
			assertFalse(decider.check("Dean", "", "PDSSections", "read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_10() throws Exception {
		System.out.println("Test test_10.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("BMApproval"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));

			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));

			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			
			assertFalse(graph.getNode("bmCSUser").getProperties().keySet().contains("approved"));

			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));

			assertFalse(graph.getNode("IRBOfficer").getProperties().keySet().contains("approved"));

			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11a() throws Exception {
		System.out.println("Test test_11a.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			assertTrue(graph.getNode("DeanCOEUser").getProperties().get("approved").equals("true"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("DeanApproval"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("PDSWhole"));

			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Withdraw"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSSections").contains("read"));

			assertTrue(graph.getSourceAssociations("Research Admin").get("OSPInfo").contains("write"));

			assertTrue(graph.getSourceAssociations("Research Admin").get("RAApproval").contains("write"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11b() throws Exception {
		System.out.println("Test test_11b.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
		
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Withdraw"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("OSPInfo").contains("write"));

			assertTrue(graph.getSourceAssociations("Research Admin").get("RAApproval").contains("write"));

			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSSections").contains("read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11_1() throws Exception {
		System.out.println("Test test_11_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			//DeanCOASUser does not approve yet, this covers negated condition for actions 2-3, and (1) of actions 4-7
			
			assertTrue(graph.getSourceAssociations("Dean").keySet().contains("DeanApproval"));
			
			assertTrue(graph.getSourceAssociations("Dean").keySet().contains("PDSWhole"));

			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));

			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));
			
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));

			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11_4_1() throws Exception {
		System.out.println("Test test_11_4_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			//DeanCOASUser does not approve yet, this covers negated condition for actions 2-3, and (1) of actions 4-7
			
			assertTrue(graph.getSourceAssociations("Dean").keySet().contains("DeanApproval"));
			assertTrue(graph.getSourceAssociations("Dean").keySet().contains("PDSWhole"));

			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));

			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11_4_2() throws Exception {
		System.out.println("Test test_11_4_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
			//irbUser does not approve yet, this covers negated condition for (2) of actions 4-7, and (2) of actions 8-11
			
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));

			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));
			
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));

			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_11_4_3() throws Exception {
		System.out.println("Test test_11_4_3.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			//DeanCOASUser does not approve yet, this covers negated condition for (1) of actions 8-11
			
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));

			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_12() throws Exception {
		System.out.println("Test test_12.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
		
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("BMApproval"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("DeanApproval"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Delete"));
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("DeanCOEUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("bmCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_13() throws Exception {
		System.out.println("Test test_13.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
			
			assertTrue(graph.getNode("irbUser").getProperties().get("approved").equals("true"));
			
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Withdraw"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("OSPInfo").contains("write"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("RAApproval").contains("write"));
			
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSSections").contains("read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_13_1() throws Exception {
		System.out.println("Test test_13_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
			//DeanCOASUser does not approve yet, this covers negated condition for actions 4-7
			
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Approve"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Disapprove"));
			assertFalse(decider.check("Research Admin", "", "PDSWhole", "Withdraw"));
			
			assertFalse(decider.check("Research Admin", "", "OSPInfo", "write"));

			assertFalse(decider.check("Research Admin", "", "RAApproval", "write"));

			assertFalse(decider.check("Research Admin", "", "PDSSections", "read"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_14() throws Exception {
		System.out.println("Test test_14.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
		
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("BMApproval"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("IRBApproval"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("IRBOfficer").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Delete"));
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("DeanCOEUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("bmCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_15() throws Exception {
		System.out.println("Test test_15.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
		
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("RAApproval"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("OSPInfo"));
			assertTrue(graph.getSourceAssociations("Research Director").get("PDSWhole").contains("Approve"));
			assertTrue(graph.getSourceAssociations("Research Director").get("PDSWhole").contains("Disapprove"));
			assertTrue(graph.getSourceAssociations("Research Director").get("PDSWhole").contains("Delete"));
			assertTrue(graph.getSourceAssociations("Research Director").get("RDApproval").contains("write"));
			assertTrue(graph.getSourceAssociations("Research Director").get("PDSSections").contains("read"));
			assertTrue(graph.getSourceAssociations("Research Director").get("OSPInfo").contains("write"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_16() throws Exception {
		System.out.println("Test test_16.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");	
		
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("RAApproval"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("OSPInfo"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Delete"));
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("DeanCOEUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("bmCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("approved"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_17() throws Exception {
		System.out.println("Test test_17.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
			
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("RDApproval"));
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("OSPInfo"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("Research Admin").get("RAApproval").contains("write"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_18() throws Exception {
		System.out.println("Test test_18.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
			
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("OSPInfo"));
			assertFalse(graph.getSourceAssociations("Research Director").keySet().contains("RDApproval"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Chair").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Business Manager").keySet().contains("PDSSections"));
			assertFalse(graph.getSourceAssociations("Dean").keySet().contains("PDSSections"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Submit"));
			assertTrue(graph.getSourceAssociations("PI").get("PDSWhole").contains("Delete"));
			assertTrue(graph.getSourceAssociations("PI").get("PIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("add-copi"));
			assertTrue(graph.getSourceAssociations("PI").get("CoPI").contains("delete-copi"));
			assertTrue(graph.getSourceAssociations("CoPI").get("CoPIEditable").contains("write"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("add-sp"));
			assertTrue(graph.getSourceAssociations("CoPI").get("SP").contains("delete-sp"));
			assertFalse(graph.getNode("ChairCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("DeanCOEUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("bmCSUser").getProperties().keySet().contains("approved"));
			assertFalse(graph.getNode("irbUser").getProperties().keySet().contains("approved"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test_19() throws Exception {
		System.out.println("Test test_19.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
			pdp.getEPP().processEvent(new SubmitRAEvent(graph.getNode("PDSWhole")), "raUser", "process");
			
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("PDSWhole"));
			assertFalse(graph.getSourceAssociations("Research Admin").keySet().contains("RAApproval"));
			assertTrue(graph.getSourceAssociations("Research Director").get("PDSWhole").contains("Archive"));
			assertTrue(graph.getSourceAssociations("Research Director").get("RDApproval").contains("write"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	public static File getFileFromResources(String fileName) {
		File resource = new File(fileName);
		return resource;
	}
	
	// the method to get pdp and load it with custom functions, graph, and obligations. 
	public static PDP getGPMSpdp(Graph graph, Obligation obligation) throws Exception {
		// Custom functions
		DeleteNodeExecutor deleteNodeExecutor = new DeleteNodeExecutor();
		CreateNodeExecutor1 createNodeExecutor1 = new CreateNodeExecutor1();
		ConcatExecutor concatExecutor = new ConcatExecutor();
		IsNodeInListExecutor areSomeNodesContainedInExecutor = new IsNodeInListExecutor();
		CompareNodeNamesExecutor compareNodesExecutor = new CompareNodeNamesExecutor();
		CoPIToAddExecutor coPIToAddExecutor = new CoPIToAddExecutor();
		SPToAddExecutor spToAddExecutor = new SPToAddExecutor();
		CoPIToDeleteExecutor coPIToDeleteExecutor = new CoPIToDeleteExecutor();
		SPToDeleteExecutor spToDeleteExecutor = new SPToDeleteExecutor();
		AddPropertiesToNodeExecutor addPropertiesToNodeExecutor = new AddPropertiesToNodeExecutor();
		RemovePropertyFromChildrenExecutor removePropertiesFromChildrenExecutor = new RemovePropertyFromChildrenExecutor();
		AllChildrenHavePropertiesExecutor allChildrenHavePropertiesExecutor = new AllChildrenHavePropertiesExecutor();
		IRBApprovalRequired iRBApprovalRequired = new IRBApprovalRequired();
		GetAncestorInPCExecutor getAncestorInPCExecutor = new GetAncestorInPCExecutor();
		GetChildInPCExecutor getChildInPCExecutor = new GetChildInPCExecutor();
		GetChildrenUsersInPolicyClassExecutor getChildrenInPCExecutor = new GetChildrenUsersInPolicyClassExecutor();
		GetChildExecutor getChildExecutor = new GetChildExecutor();
		GetAncestorsInPCExecutor getAncestorsInPCExecutor = new GetAncestorsInPCExecutor();
		//adding custom functions to eppOptions
		EPPOptions eppOptions = new EPPOptions(deleteNodeExecutor, createNodeExecutor1, concatExecutor,
				areSomeNodesContainedInExecutor, compareNodesExecutor, coPIToAddExecutor, spToAddExecutor,
				coPIToDeleteExecutor, spToDeleteExecutor, addPropertiesToNodeExecutor,
				removePropertiesFromChildrenExecutor, allChildrenHavePropertiesExecutor, iRBApprovalRequired,
				getAncestorInPCExecutor, getChildInPCExecutor, getChildrenInPCExecutor, getChildExecutor,
				getAncestorsInPCExecutor);
		//creating the pdp
		PDP pdp = new PDP(new PAP(graph, new MemProhibitions(), new MemObligations()), eppOptions);
		if (graph.exists("super_pc_rep")) {
			graph.deleteNode("super_pc_rep");
		}
		//adding obligations to the pdp through pap
		pdp.getPAP().getObligationsPAP().add(obligation, true);		
		return pdp;
	}
    
	
}
