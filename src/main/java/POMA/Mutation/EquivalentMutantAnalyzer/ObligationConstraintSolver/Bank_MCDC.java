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

import CaseStudies.Bank.Constants;
import CaseStudies.Bank.customEvents.*;
import CaseStudies.Bank.customFunctions.*;
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

public class Bank_MCDC {
	Graph graph = new MemGraph();
	Prohibitions prohibitions = new MemProhibitions();
	Obligation obligation = new Obligation();
	Logger logger;
	TestAppender appender;
	List<String> obligationHistory;
	
	@Before
	public void runBeforeEach() throws Exception {
//		String pathGraph = "Policies/BankPolicy/OnePolicyClass/Graph.json";
//		String pathObligation = "Policies/BankPolicy/OnePolicyClass/Obligations.yml";
//		
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
	public void test1() throws Exception {
		System.out.println("Test test1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			
			assertTrue(graph.getChildren("accounts").contains("a1"));
			
			assertTrue(graph.getNode("a1").getProperties().get("loan").equals("false"));
			
			assertTrue(graph.getNode("a1").getProperties().get("active").equals("none"));
			
			assertTrue(graph.getNode("a1").getProperties().get("loan-process").equals("none"));
			
			assertTrue(graph.getSourceAssociations("teller").get("accounts").contains("review"));
			assertTrue(graph.getSourceAssociations("teller").get("accounts").contains("apply-loan"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2a() throws Exception {
		System.out.println("Test test2a.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			
			assertTrue(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2b() throws Exception {
		System.out.println("Test test2b.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			
			assertTrue(graph.getSourceAssociations("loan-officer").get("loans").contains("review"));
			assertTrue(graph.getSourceAssociations("loan-officer").get("loans").contains("approve"));
			assertTrue(graph.getSourceAssociations("loan-officer").get("loans").contains("disapprove"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2_1() throws Exception {
		System.out.println("Test test2_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			
			assertFalse(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2_1_1() throws Exception {
		System.out.println("Test test2_1_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			
			assertFalse(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test2_1_2() throws Exception {
		System.out.println("Test test2_1_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			
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
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("loan").equals("true"));
			
			assertTrue(graph.getSourceAssociations("teller").get("accounts").contains("pay-loan"));
			
//			assertFalse(decider.check("teller-supervisor", "", "a1", "delete"));//FIXME: prohibitions not work for action 3
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test3_1() throws Exception {
		System.out.println("Test test3_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			
			assertFalse(graph.getNode("a1").getProperties().get("loan").equals("true"));
			
			assertFalse(graph.getSourceAssociations("teller").get("accounts").contains("pay-loan"));
			
//			assertFalse(decider.check("teller-supervisor", "", "a1", "delete"));//FIXME: prohibitions not work for action 3
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test3_1_1() throws Exception {
		System.out.println("Test test3_1_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test3_1_2() throws Exception {
		System.out.println("Test test3_1_2.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			
			assertFalse(graph.getNode("a1").getProperties().get("loan").equals("true"));
			
			assertFalse(graph.getSourceAssociations("teller").get("accounts").contains("pay-loan"));
			
//			assertFalse(decider.check("teller-supervisor", "", "a1", "delete"));//FIXME: prohibitions not work for action 3
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("loans")), "lo1", "");
			
			assertFalse(graph.getChildren("loans").contains("a1"));
			
			assertFalse(graph.getSourceAssociations("loan-officer").keySet().contains("loans"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test4_1() throws Exception {
		System.out.println("Test test4_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("loans")), "lo1", "");
			
			assertTrue(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test4_1_1() throws Exception {
		System.out.println("Test test4_1_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("loans")), "lo1", "");
			
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
//			assertFalse(decider.check("teller-supervisor", "", "a1", "delete"));//FIXME: prohibitions not work
			pdp.getEPP().processEvent(new PayLoanEvent(graph.getNode("accounts")), "teller1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("loan").equals("false"));
			
			assertFalse(graph.getChildren("loans").contains("a1"));
			
			assertFalse(graph.getSourceAssociations("loan-officer").keySet().contains("loans"));
			
//			assertTrue(decider.check("teller-supervisor", "", "a1", "delete"));//FIXME: this is a test of prohibition
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test6a() throws Exception {
		System.out.println("Test test6a.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
			
			assertTrue(graph.getSourceAssociations("teller").get("accounts").contains("create"));
			
			assertFalse(graph.getChildren("accounts").contains("a1"));
			
			assertFalse(graph.getSourceAssociations("loan-officer").keySet().contains("loans"));
			
			assertFalse(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test6b() throws Exception {
		System.out.println("Test test6b.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("loan").equals("false"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test6_0() throws Exception {
		System.out.println("Test test6_0.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("loans")), "lo1", "");
			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
			
			assertFalse(graph.getSourceAssociations("teller").get("accounts").contains("create"));
			
			assertTrue(graph.getChildren("accounts").contains("a1"));
			
			assertTrue(graph.getSourceAssociations("loan-officer").keySet().contains("loans"));
			
			assertTrue(graph.getChildren("loans").contains("a1"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
//	@Test
//	public void test6_1() throws Exception {
//		System.out.println("Test test6_1.");
//		try {
//			PReviewDecider decider = new PReviewDecider(graph);
//			PDP pdp = getPdp(graph, prohibitions, obligation);
//			
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
//			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
//			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
//			//FIXME: it makes to sense, where trigger an obligation twice and assert something
//			
//			assertFalse(graph.getSourceAssociations("teller").get("accounts").contains("create"));
//		} catch (PMException e) {
//			e.printStackTrace();
//			assertTrue(1 == 0);
//		}
//	}
	
	@Test
	public void test6_3() throws Exception {
		System.out.println("Test test6_3.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new DeleteEvent(graph.getNode("accounts")), "ts1", "");
			
			assertFalse(graph.getChildren("accounts").contains("a1"));
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("loans")), "ds1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("loan-process").equals("true"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test7_1() throws Exception {
		System.out.println("Test test7_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			
			assertFalse(graph.getNode("a1").getProperties().keySet().contains("loan-process"));
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			pdp.getEPP().processEvent(new ApplyLoanEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("loans")), "ds1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("loan-process").equals("false"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test8_1() throws Exception {
		System.out.println("Test test8_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("loans")), "ds1", "");
			
			assertFalse(graph.getNode("a1").getProperties().keySet().contains("loan-process"));
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("active").equals("true"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test9_1() throws Exception {
		System.out.println("Test test9_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new OnlineEvent(graph.getNode("accounts")), "ds1", "");
			
			assertFalse(graph.getNode("a1").getProperties().keySet().contains("active"));
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
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("accounts")), "teller1", "");
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("accounts")), "ds1", "");
			
			assertTrue(graph.getNode("a1").getProperties().get("active").equals("false"));
		} catch (PMException e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}
	
	@Test
	public void test10_1() throws Exception {
		System.out.println("Test test10_1.");
		try {
			PReviewDecider decider = new PReviewDecider(graph);
			PDP pdp = getPdp(graph, prohibitions, obligation);
			
			pdp.getEPP().processEvent(new OfflineEvent(graph.getNode("accounts")), "ds1", "");
			
			assertFalse(graph.getNode("a1").getProperties().keySet().contains("active"));
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
		IsNodeInListExecutor areSomeNodesContainedInExecutor = new IsNodeInListExecutor();
		AddPropertiesToNodeExecutor addPropertiesToNodeExecutor = new AddPropertiesToNodeExecutor();
		RemovePropertyFromChildrenExecutor removePropertiesFromChildrenExecutor = new RemovePropertyFromChildrenExecutor();
//		AllChildrenHavePropertiesExecutor allChildrenHavePropertiesExecutor = new AllChildrenHavePropertiesExecutor();
		HasPropertiesExecutor HasPropertiesExecutor = new HasPropertiesExecutor();
		//adding custom functions to eppOptions
		EPPOptions eppOptions = new EPPOptions(deleteNodeExecutor, createNodeExecutor1, areSomeNodesContainedInExecutor, addPropertiesToNodeExecutor,
				removePropertiesFromChildrenExecutor, HasPropertiesExecutor);
		//creating the pdp
		PDP pdp = new PDP(new PAP(graph, prohibitions, new MemObligations()), eppOptions);
		if (graph.exists("super_pc_rep")) {
			graph.deleteNode("super_pc_rep");
		}
		//adding obligations to the pdp through pap
		pdp.getPAP().getObligationsPAP().add(obligation, true);		
		return pdp;
	}
}