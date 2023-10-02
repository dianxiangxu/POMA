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

import CaseStudies.LawUseCase.customEvents.*;
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
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;

public class Law_OC {
	Graph graph = new MemGraph();
	Prohibitions prohibitions = new MemProhibitions();
	Obligation obligation = new Obligation();
	Logger logger;
	TestAppender appender;
	List<String> obligationHistory;
	
	@Before
	public void runBeforeEach() throws Exception {
//		String pathGraph = "Policies/LawUseCase/Graph.json";
//		String pathProhibition = "Policies/LawUseCase/prohibitions.json";
//		String pathObligation = "Policies/LawUseCase/Obligations.yml";
//		
//		
//		File graphFile = getFileFromResources(pathGraph);
//		String graphJSON = new String(Files.readAllBytes(Paths.get(graphFile.getAbsolutePath())));
//		GraphSerializer.fromJson(graph, graphJSON);
//		
//		File prohibitionsfile = new File(pathProhibition);
//		String prohibitionsJSON = new String(Files.readAllBytes(Paths.get(prohibitionsfile.getAbsolutePath())));
//		ProhibitionsSerializer.fromJson(prohibitions, prohibitionsJSON);
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			int count;
			
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("Case3")), "LA1", "");
			
			obligationHistory.add("disapproveLA1");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 1);
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			int count = 0;
			
			pdp.getEPP().processEvent(new WithdrawEvent(graph.getNode("Case3")), "LA1", "");
			
			obligationHistory.add("withdraw");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 1);
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			int count = 0;
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "LA1", "");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "LA1", "");//this fires 2 obligations, 4 and 5
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "LA1", "finalAccept");//TODO: no detection
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("Case3Info")), "C1", "");
		
			obligationHistory.add("create");
			obligationHistory.add("accept0_LA1_");
			obligationHistory.add("accept1_LA1_");
			obligationHistory.add("accept0_LA1_finalAccept");
			obligationHistory.add("approveC1");
			
			count = checkObligationCoveredNumber(obligationHistory);
			assertTrue(count == 5);
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
	public static PDP getPdp(Graph graph, Prohibitions prohibitions, Obligation obligation) throws Exception {
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
		PDP pdp = new PDP(new PAP(graph, prohibitions, new MemObligations()), eppOptions);
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