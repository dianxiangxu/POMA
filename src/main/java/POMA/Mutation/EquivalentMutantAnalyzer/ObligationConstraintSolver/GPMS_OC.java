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

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
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

public class GPMS_OC {
	Graph graph = new MemGraph();
	Obligation obligation = new Obligation();
	Logger logger;
	TestAppender appender;
	List<String> obligationHistory;
	
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
//		
		logger = Logger.getRootLogger();
		appender = new TestAppender();
		logger.addAppender(appender);
		obligationHistory = new ArrayList<>();
	}
	
	@After
	public void runAfterEach() {
		logger.removeAppender(appender);
		obligationHistory = new ArrayList<>();
	}
	
	@Test
	public void test1() throws Exception {
		System.out.println("Test test1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count;
			
//			logger = Logger.getRootLogger();
//			appender = new TestAppender();
//			logger.addAppender(appender);
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
		
			
			obligationHistory.add("create");
			obligationHistory.add("add-copi");
			obligationHistory.add("delete-copi");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 3);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2() throws Exception {
		System.out.println("Test test2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "nazmul", "process");
			pdp.getEPP().processEvent(new DeleteSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "nazmul", "process");
			
			obligationHistory.add("create");
			obligationHistory.add("add-sp");
			obligationHistory.add("delete-sp");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 3);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test3() throws Exception {
		System.out.println("Test test3.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("disapproveChairCSUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 3);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test4() throws Exception {
		System.out.println("Test test4.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("disapprovebmCSUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 4);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test5() throws Exception {
		System.out.println("Test test5.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("approvebmCSUser");
			obligationHistory.add("disapproveDeanCOEUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 5);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test6() throws Exception {
		System.out.println("Test test6.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("approveirbUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 4);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test7() throws Exception {
		System.out.println("Test test7.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.IRB_APPROVAL)), "irbUser", "process");
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("disapproveirbUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 4);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test8() throws Exception {
		System.out.println("Test test8.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");	
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("approvebmCSUser");
			obligationHistory.add("approveDeanCOEUser");
			obligationHistory.add("disapproveraUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 6);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test9() throws Exception {
		System.out.println("Test test9.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");	
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("approvebmCSUser");
			obligationHistory.add("approveDeanCOEUser");
			obligationHistory.add("approveraUser");
			obligationHistory.add("disapproverdUser");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 7);
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test10() throws Exception {
		System.out.println("Test test10.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getGPMSpdp(graph, obligation);
			int count = 0;

			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(Constants.PDS_ORIGINATING_OA)), "nazmul", "process");
			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), false),"nazmul", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
			pdp.getEPP().processEvent(new SubmitRAEvent(graph.getNode("PDSWhole")), "raUser", "process");	
		
			obligationHistory.add("create");
			obligationHistory.add("submit");
			obligationHistory.add("approveChairCSUser");
			obligationHistory.add("approvebmCSUser");
			obligationHistory.add("approveDeanCOEUser");
			obligationHistory.add("approveraUser");
			obligationHistory.add("approverdUser");
			obligationHistory.add("RAsubmit");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 8);
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
 
	private int checkObligationCoveredNumber (List<String> obligationHistory) {
		int count = 0;
		for (String s : obligationHistory) {
			for (LoggingEvent e : appender.getLog()) {
				if (e.getMessage().toString().equals(s)) {
					count++;
					break;
				}
			}
		}
		return count;
	}
	
}

//class TestAppender extends AppenderSkeleton {
//    private final List<LoggingEvent> log = new ArrayList<LoggingEvent>();
//
//    @Override
//    public boolean requiresLayout() {
//        return false;
//    }
//
//    @Override
//    protected void append(final LoggingEvent loggingEvent) {
//        log.add(loggingEvent);
//    }
//
//    @Override
//    public void close() {
//    }
//
//    public List<LoggingEvent> getLog() {
//        return new ArrayList<LoggingEvent>(log);
//    }
//}