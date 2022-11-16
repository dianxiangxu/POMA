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

public class GPMS_AC {
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
	public void test11a() throws Exception {
		System.out.println("Test test11a.");
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
	public void test11b() throws Exception {
		System.out.println("Test test11b.");
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
	public void test12() throws Exception {
		System.out.println("Test test12.");
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
	public void test13() throws Exception {
		System.out.println("Test test13.");
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
	public void test14() throws Exception {
		System.out.println("Test test14.");
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
	public void test15() throws Exception {
		System.out.println("Test test15.");
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
	public void test16() throws Exception {
		System.out.println("Test test16.");
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
	public void test17() throws Exception {
		System.out.println("Test test17.");
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
	public void test18() throws Exception {
		System.out.println("Test test18.");
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
	public void test19() throws Exception {
		System.out.println("Test test19.");
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
