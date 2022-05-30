package POMA.Mutation.EquivalentMutantAnalyzer;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.CSVReader;

import CaseStudies.LawUseCase.customEvents.AcceptEvent;
import CaseStudies.gpms.customFunctions.IsNodeInListExecutor;

//import CaseStudies.gpms.Constants;
//import CaseStudies.gpms.customEvents.AddCoPIEvent;
//import CaseStudies.gpms.customEvents.AddSPEvent;
//import CaseStudies.gpms.customEvents.ApproveEvent;
//import CaseStudies.gpms.customEvents.ArchiveEvent;
//import CaseStudies.gpms.customEvents.CreateEvent;
//import CaseStudies.gpms.customEvents.DeleteCoPIEvent;
//import CaseStudies.gpms.customEvents.DeleteSPEvent;
//import CaseStudies.gpms.customEvents.DisapproveEvent;
//import CaseStudies.gpms.customEvents.SubmitEvent;
//import CaseStudies.gpms.customEvents.SubmitRAEvent;
//import CaseStudies.gpms.customFunctions.AddPropertiesToNodeExecutor;
//import CaseStudies.gpms.customFunctions.AllChildrenHavePropertiesExecutor;
//import CaseStudies.gpms.customFunctions.CoPIToAddExecutor;
//import CaseStudies.gpms.customFunctions.CoPIToDeleteExecutor;
//import CaseStudies.gpms.customFunctions.CompareNodeNamesExecutor;
//import CaseStudies.gpms.customFunctions.ConcatExecutor;
//import CaseStudies.gpms.customFunctions.CreateNodeExecutor1;
//import CaseStudies.gpms.customFunctions.DeleteNodeExecutor;
//import CaseStudies.gpms.customFunctions.GetAncestorInPCExecutor;
//import CaseStudies.gpms.customFunctions.GetAncestorsInPCExecutor;
//import CaseStudies.gpms.customFunctions.GetChildExecutor;
//import CaseStudies.gpms.customFunctions.GetChildInPCExecutor;
//import CaseStudies.gpms.customFunctions.GetChildrenUsersInPolicyClassExecutor;
//import CaseStudies.gpms.customFunctions.IRBApprovalRequired;
//import CaseStudies.gpms.customFunctions.IsNodeInListExecutor;
//import CaseStudies.gpms.customFunctions.RemovePropertyFromChildrenExecutor;
//import CaseStudies.gpms.customFunctions.SPToAddExecutor;
//import CaseStudies.gpms.customFunctions.SPToDeleteExecutor;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Verification.ReachabilityAnalysis.ObligationChecker;
import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
import POMA.Verification.ReachabilityAnalysis.model.Solution;
import POMA.Verification.ReachabilityAnalysis.model.Variable;
import POMA.Verification.ReachabilityAnalysis.model.Variables;
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Condition;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.obligations.model.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction.Target.Container;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DenyAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class Utils extends MutantTester {
	static Graph g;
	
	public Utils(String testSuit, Graph graph, Prohibitions prohibitions, String obligationPath, List<AccessRequest> arList) throws GraphDoesNotMatchTestSuitException {
		super(testSuit, graph, prohibitions, obligationPath, arList);
	}

	// decide whether a is contained by b or not
	public static boolean isContained(Node nodeA, Node nodeB, Graph graph) throws PMException {
		if (nodeA.getName().equals(nodeB.getName()))
			return true;
		for (String parent : graph.getParents(nodeA.getName())) {
			if (parent.equals(nodeB.getName())) {
				return true;
			} else {
				if (isContained(graph.getNode(parent), nodeB, graph)) {
					return true;
				}
			}
		}
		return false;
	}


	public static boolean isContained(String nodeA, String nodeB, Graph graph) throws PMException {
		if (nodeA.equals(nodeB))
			return true;
		for (String parent : graph.getParents(nodeA)) {
			if (parent.equals(nodeB)) {
				return true;
			} else {
				if (isContained(graph.getNode(parent).getName(), nodeB, graph)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public static AccessRequest compareTwoLists (Map<String, Set<String>> listA, Map<String, Set<String>> listB, String type) {
		if (type.equals("UA")) {
			if (listA == null) {
				if (listB == null) {
					return null;
				} else {
					//nodeA gains permissions in mutant while no permissions in initial configuration
					for (Map.Entry<String, Set<String>> capability : listA.entrySet()) {
						String target = capability.getKey();
						Set<String> permissions = capability.getValue();
						Iterator iter = permissions.iterator();
						String ar = (String) iter.next();
						return new AccessRequest(null, ar, target);
					}
				}
			}
			
			for (Map.Entry<String, Set<String>> capability : listA.entrySet()) {
				String target = capability.getKey();
				Set<String> permissions = capability.getValue();
				
				if (listB == null || !listB.containsKey(target) || (listB.get(target).isEmpty())) {
					if (permissions.isEmpty()) {
						continue;
					}
					//permissions of nodeA are all lost in mutant
					Iterator iter = permissions.iterator();
					String ar = (String) iter.next();
					return new AccessRequest(null, ar, target);
				}
				
				Set<String> permissionsM = listB.get(target);
				
				Set<String> permissionsMTmp = new HashSet<String>();
				for (String pM : permissionsM) {
					permissionsMTmp.add(pM);
				}
				
				for (String p : permissions) {
//					if (permissionsM.isEmpty()) {
//						return new AccessRequest(null, p, target);
//					}
					boolean findAr = false;
					for (String pM : permissionsM) {
						if (p.equals(pM)) {
							permissionsMTmp.remove(pM);
							findAr = true;
						}
					}
					if (findAr == false) {
						//ar is lost(not permitted) in mutant while is permitted in initial configuration
						return new AccessRequest(null, p, target);
					}
				}
				if (!permissionsMTmp.isEmpty()) {
					//nodeA gains new access right ar in mutant
					Iterator iter = permissionsMTmp.iterator();
					String ar = (String) iter.next();
					return new AccessRequest(null, ar, target);
				}		
			}
			
			return null;
		} else if (type.equals("OA")) {
			if (listA == null) {
				if (listB == null) {
					return null;
				} else {
					//nodeA gains permissions in mutant while no permissions in initial configuration
					for (Map.Entry<String, Set<String>> acM : listB.entrySet()) {
						String subject = acM.getKey();
						Set<String> permissions = acM.getValue();
						Iterator iter = permissions.iterator();
						String ar = (String) iter.next();
						return new AccessRequest(subject, ar, "");
					}
				}
			}
			for (Map.Entry<String, Set<String>> ac : listA.entrySet()) {
				String subject = ac.getKey();
				Set<String> permissions = ac.getValue();
				
				if (listB == null || !listB.containsKey(subject) || (listB.get(subject).isEmpty())) {
					if (permissions.isEmpty()) {
						continue;
					}
					//permissions of nodeA are all lost in mutant
					Iterator iter = permissions.iterator();
					String ar = (String) iter.next();
					return new AccessRequest(subject, ar, null);
				}
				Set<String> permissionsM = listB.get(subject);

				Set<String> permissionsMTmp = new HashSet<String>();
				for (String pM : permissionsM) {
					permissionsMTmp.add(pM);
				}
				
				for (String p : permissions) {
					boolean findAr = false;
					
					for (String pM : permissionsM) {
						if (p.equals(pM)) {
							permissionsMTmp.remove(pM);
							findAr = true;
						}
					}
					if (findAr == false) {
						//ar is lost(not permitted) in mutant while is permitted in initial configuration
						return new AccessRequest(subject, p, null);
					}
				}
				if (!permissionsMTmp.isEmpty()) {
					//nodeA gains new access right ar in mutant
					Iterator iter = permissionsMTmp.iterator();
					String ar = (String) iter.next();
					return new AccessRequest(subject, ar, null);
				}
			}
		}
		return null;
	}
	
	
	public static void getAllAscendant (String node, Set<String> ascendantList, Graph g) throws PMException {
//		ascendantList.add(node);
		if (!g.exists(node)) {
			ascendantList.add(node);
			return;
		}
		Set<String> children = g.getChildren(node);
		ascendantList.addAll(children);
		for (String child : children) {
			getAllAscendant(child, ascendantList, g);
		}
	}
	
	public static OperationSet getAllAccessRights() throws PMException, IOException {
		OperationSet ARSet = new OperationSet();
		for (Node SourceNode : UAs) {
			if (graph.getSourceAssociations(SourceNode.getName()) == null) {
				continue;
			}
			Map<String, OperationSet> sources = graph.getSourceAssociations(SourceNode.getName());
			List<String> targetNodes = new ArrayList<String>(sources.keySet());
			for (String targetNode : targetNodes) {
				Set<String> operateSet = sources.get(targetNode);
				OperationSet accessRights = new OperationSet(operateSet);
				ARSet.addAll(accessRights);
			}
		}
		// System.out.println("allAccessRightSet is :" + ARSet);
		return ARSet;
	}

	public static List<Prohibition> getProhibitionList () throws PMException {
		return prohibitions.getAll();
	}
	
	public static Prohibitions createProhibitionsCopy() throws PMException {
		Prohibitions mutant = new MemProhibitions();
		String json = ProhibitionsSerializer.toJson(prohibitions);

		ProhibitionsSerializer.fromJson(mutant, json);
		return mutant;
	}
	
	public static Node[] getNodesInGraph() throws PMException {
		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		return nodes;
	}
	
	public static List<Node> getUsInGraph(Graph graph) throws PMException {
		List<Node> Us = new ArrayList<Node>();

		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		for (Node node : nodes) {
			if (node.getType() == U) {
				Us.add(node);
			}
		}
		return Us;
	}
	
	public static List<Node> getUAsInGraph(Graph graph) throws PMException {
		List<Node> UAs = new ArrayList<Node>();

		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		for (Node node : nodes) {
			if (node.getType() == UA) {
				UAs.add(node);
			}
		}
		return UAs;
	}
	
	public static List<Node> getOAsInGraph(Graph graph) throws PMException {
		List<Node> OAs = new ArrayList<Node>();

		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		for (Node node : nodes) {
			if (node.getType() == OA) {
				OAs.add(node);
			}
		}
		return OAs;
	}
	
	public static void addToARList(AccessRequest q) {
		for (AccessRequest ar : arList) {
			if (ar.getSA().equals(q.getSA()) && ar.getAR().equals(q.getAR()) && ar.getTA().equals(q.getTA()))
				return;
		}
		arList.add(q);
	}
	
	public static Obligation readObligation(String path) throws FileNotFoundException, EVRException {
		File obligationFile = new File(path);
		InputStream inputStream = new FileInputStream(obligationFile);
		Obligation obligation = EVRParser.parse(inputStream);

		return obligation;
	}
	
	public static Obligation readObligation() throws FileNotFoundException, EVRException {
		File obligationFile = new File(obligationFilePath);
		InputStream inputStream = new FileInputStream(obligationFile);
		Obligation obligation = EVRParser.parse(inputStream);

		return obligation;
	}
	
	public static Obligation createObligationCopy() throws FileNotFoundException, EVRException{
		return readObligation();
	}
	
	public static Obligation createObligationWithCondtionCopy() throws FileNotFoundException, EVRException {
		File obligationFile = new File(obligationWithConditionFilePath);
		InputStream inputStream = new FileInputStream(obligationFile);
		Obligation obligation = EVRParser.parse(inputStream);

		return obligation;
	}
	
	public static void setObligationMutant(Obligation obligation){
		obligationMutant.setEnabled(obligation.isEnabled());
		obligationMutant.setLabel(obligation.getLabel());
		obligationMutant.setRules(obligation.getRules());
		obligationMutant.setSource(obligation.getSource());
	}
	
	public static String getUserName(int i, List<Node> Us) {
		//0 can be replaced by a random number from 0 to length(Us)
		String userName = Us.get(i).getName();
//		System.out.println("changeToUser:" + userName);
		return userName;
	}
	
	public static List<String> getPcList(Node node, Graph graph) throws PMException {
		Node nodePc;
		List<String> pcList = new ArrayList<String>();

		for (String pc : graph.getPolicyClasses()) {
			nodePc = graph.getNode(pc);
			if (isContained(node.getName(), nodePc.getName(), graph)) {
				pcList.add(pc);
			}
		}
		return pcList;
	}
	
	//this function returns the list of attributes whose privilege sets might be changed (propagation)
	static public void getAffectedAttributes (Set<String> attributeList, String ruleLabel) throws FileNotFoundException, PMException {
		attributeList.clear();
		List<Rule> rules = createObligationCopy().getRules();
		for (Rule rule : rules) {
			if (rule.getLabel().equals(ruleLabel)) {
				ResponsePattern responsePattern = rule.getResponsePattern();
				List<Action> actions = responsePattern.getActions();
				for (Action action : actions) {
					if (action instanceof CreateAction) {
						for (CreateNode createNode: ((CreateAction) action).getCreateNodesList()) {
							attributeList.add(createNode.getWhat().getName());
							attributeList.add(createNode.getWhere().getName());
						}
					} else if (action instanceof AssignAction) {
						for (Assignment assignment : ((AssignAction) action).getAssignments() ) {
							attributeList.add(assignment.getWhat().getName());
							attributeList.add(assignment.getWhere().getName());
						}
					} else if (action instanceof GrantAction) {
						attributeList.add(((GrantAction) action).getSubject().getName());
						attributeList.add(((GrantAction) action).getTarget().getName());
						
					} else if (action instanceof DenyAction) {
						attributeList.add(((DenyAction) action).getSubject().getName());
					} else if (action instanceof DeleteAction) {
						if (((DeleteAction) action).getNodes() != null) {
							for (EvrNode evrNnode : ((DeleteAction) action).getNodes()) {
								String name = evrNnode.getName();
								Map<String, OperationSet> map = graph.getSourceAssociations(name);
								attributeList.addAll(map.keySet());
								map = graph.getTargetAssociations(name);
								attributeList.addAll(map.keySet());
							}
						}
						if (((DeleteAction) action).getAssignments() != null) {
							AssignAction assignAction = ((DeleteAction) action).getAssignments();
							for (Assignment assignment : assignAction.getAssignments()) {
								attributeList.add(assignment.getWhat().getName());
								attributeList.add(assignment.getWhere().getName());
							}
						}
						if (((DeleteAction) action).getAssociations() != null) {
							List<GrantAction> grantList = ((DeleteAction) action).getAssociations();
							for (GrantAction gAction : grantList) {
								attributeList.add(gAction.getSubject().getName());
								attributeList.add(gAction.getTarget().getName());
							}
						}
						//FIXME: below not handled
						//prohibitions
						if (((DeleteAction) action).getProhibitions() != null) {
							List<String> prohibitionList = ((DeleteAction) action).getProhibitions();
							for (String name : prohibitionList) {
								for (Prohibition p : Utils.getProhibitionList()) {
									if (p.getName().equals(name)) {
										attributeList.add(p.getSubject());
									}
								}
							}
						}
						//obligations
						if (((DeleteAction) action).getRules() != null) {
							List<String> ruleList = ((DeleteAction) action).getRules();
							for (String name : ruleList) {
								getAffectedAttributes(attributeList, name);
							}
						}
					}
				}
			}
		}
	}
	
	public static PDP getPdpLawFirm(Graph graph, Prohibitions prohibitions, Obligation obligation) throws Exception {
		EPPOptions eppOptions = new EPPOptions();

		PDP pdp = new PDP(new PAP(graph, prohibitions, new MemObligations()), eppOptions);
		if (graph.exists("super_pc_rep")) {
			graph.deleteNode("super_pc_rep");
		}
		pdp.getPAP().getObligationsPAP().add(obligation, true);
		return pdp;
	}
	
	//FIXME: this function is supposed to be implemented
	public static void getAccessRequestNoTrigger() {
		return;
	}
	
	//this function will try to trigger obligation by its label
	//currently, every step is handwriting
	//in future, steps could come from external libraries
	//IMPORTANT: obligation cannot be changed within this function
	//ar is a possible request which shows different obligation triggering decision between initial policy and mutant
	//Example: Law Firm
	static public void reachObligation(Graph graph, Prohibitions prohibitions, Obligation obligation, AccessRequest ar, String ruleLabel) throws Exception {
		//FIXME: These strings should come from reachability analysis function
		String createNode = "Case3", createUser = "Attorneys", createProcess = "initialCreate";
		String acceptNode = "Case3Info", acceptUser = "Attorneys", acceptProcess = "initialAccept";
		String laAcceptNode = "Case3Info", laAcceptUser = "LeadAttorneys", laAcceptProcess = "Accept";
		String finalAcceptNode = "Case3Info", finalAcceptUser = "Attorneys", finalAcceptProcess = "finalAccept";
		String disapproveNode = "Case3", disapproveUser = "LeadAttorneys", disapproveProcess = "";
		String withdrawNode = "Case3", withdrawUser = "LeadAttorneys", withdrawProcess = "Withdraw";
		String approveNode = "Case3Info", approveUser = "C-Suit", approveProcess = "approve";
		
		PDP pdp = getPdpLawFirm(graph, prohibitions, obligation);
//		switch(ruleLabel) {
//		case "withdraw_case_info":
//			if (ar != null) {
//				if (ar.getSA() != null) disapproveUser = ar.getSA();
//				if (ar.getTA() != null) disapproveNode = ar.getTA();
////				disapproveProcess = "";
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(disapproveNode)), disapproveUser, disapproveProcess);
//			break;
//		case "withdraw_case":
//			if (ar != null) {
//				if (ar.getSA() != null) withdrawUser = ar.getSA();
//				if (ar.getTA() != null) withdrawNode = ar.getTA();
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new WithdrawEvent(graph.getNode(withdrawNode)), withdrawUser, withdrawProcess);
//			break;
//		case "create_case_info":
//			if (ar != null) {
//				if (ar.getSA() != null) createUser = ar.getSA();
//				if (ar.getTA() != null) createNode = ar.getTA();
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			break;
//		case "accept_case_LA":
//			if (ar != null) {
//				if (ar.getSA() != null) laAcceptUser = ar.getSA();
//				if (ar.getTA() != null) laAcceptNode = ar.getTA();
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(laAcceptNode)), laAcceptUser, laAcceptProcess);
//			break;
//		case "accept_refuse_case_A":
//			if (ar != null) {
//				if (ar.getSA() != null) acceptUser = ar.getSA();
//				if (ar.getTA() != null) acceptNode = ar.getTA();
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
//			break;
//		case "accept_case_Final":
//			//FIXME: mutation on process not implemented
//			if (ar != null) {
//				if (ar.getSA() != null) finalAcceptProcess = ar.getSA();
//				if (ar.getTA() != null) finalAcceptNode = ar.getTA();
//				finalAcceptUser = "";
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
//			//final accept
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(finalAcceptNode)), finalAcceptUser, finalAcceptProcess);
//			break;
//		case "approve_case":
//			if (ar != null) {
//				if (ar.getSA() != null) approveUser = ar.getSA();
//				if (ar.getTA() != null) approveNode = ar.getTA();
//			}
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(laAcceptNode)), laAcceptUser, laAcceptProcess);
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(approveNode)), approveUser, approveProcess);
//			break;
//			
//		default:
//			break;
//		}
	}
	
	//Function getAccessRequestNoTrigger() should return an access request which cannot trigger obligation in initial policy
	//and it can trigger obligation in mutant
	//the returned access request should be according to mutant
	static public void reachObligationNoTrigger(Graph graph, Prohibitions prohibitions, Obligation obligation, EventPattern eventPattern, String ruleLabel) throws Exception {
		PDP pdp = getPdpLawFirm(graph, prohibitions, obligation);
//		switch(ruleLabel) {
//		case "withdraw_case_info":
//			//FIXME
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("Apple")), "LeadAttorneys", "");
//			break;
//		case "withdraw_case":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new WithdrawEvent(graph.getNode("Apple")), "LeadAttorneys", "Withdraw");
//			break;
//		case "create_case_info":
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("GeneralInfo")), "Attorneys", "initialCreate");
//			break;
//		case "accept_case_LA":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "LeadAttorneys", "finalAccept");
//			break;
//		case "accept_refuse_case_A":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "Attorneys", "initialAccept");
//			break;
//		case "accept_case_Final":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "LeadAttorneys", "finalAccept");
//			break;
//		case "approve_case":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
//			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "LeadAttorneys", "finalAccept");
//			getAccessRequestNoTrigger();
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("Apple")), "C-Suit", "approve");
//			break;
//			
//		default:
//			break;
//		}
	}

	//Example: GPMS
	public static PDP getPdpGPMS(Graph graph, Prohibitions prohibitions, Obligation obligation) throws Exception {
		return null;
		
//		// Custom functions
//		DeleteNodeExecutor deleteNodeExecutor = new DeleteNodeExecutor();
//		CreateNodeExecutor1 createNodeExecutor1 = new CreateNodeExecutor1();
//		ConcatExecutor concatExecutor = new ConcatExecutor();
//		IsNodeInListExecutor areSomeNodesContainedInExecutor = new IsNodeInListExecutor();
//		CompareNodeNamesExecutor compareNodesExecutor = new CompareNodeNamesExecutor();
//		CoPIToAddExecutor coPIToAddExecutor = new CoPIToAddExecutor();
//		SPToAddExecutor spToAddExecutor = new SPToAddExecutor();
//		CoPIToDeleteExecutor coPIToDeleteExecutor = new CoPIToDeleteExecutor();
//		SPToDeleteExecutor spToDeleteExecutor = new SPToDeleteExecutor();
//		AddPropertiesToNodeExecutor addPropertiesToNodeExecutor = new AddPropertiesToNodeExecutor();
//		RemovePropertyFromChildrenExecutor removePropertiesFromChildrenExecutor = new RemovePropertyFromChildrenExecutor();
//		AllChildrenHavePropertiesExecutor allChildrenHavePropertiesExecutor = new AllChildrenHavePropertiesExecutor();
//		IRBApprovalRequired iRBApprovalRequired = new IRBApprovalRequired();
//		GetAncestorInPCExecutor getAncestorInPCExecutor = new GetAncestorInPCExecutor();
//		GetChildInPCExecutor getChildInPCExecutor = new GetChildInPCExecutor();
//		GetChildrenUsersInPolicyClassExecutor getChildrenInPCExecutor = new GetChildrenUsersInPolicyClassExecutor();
//		GetChildExecutor getChildExecutor = new GetChildExecutor();
//		GetAncestorsInPCExecutor getAncestorsInPCExecutor = new GetAncestorsInPCExecutor();
//		//adding custom functions to eppOptions
//		EPPOptions eppOptions = new EPPOptions(deleteNodeExecutor, createNodeExecutor1, concatExecutor,
//				areSomeNodesContainedInExecutor, compareNodesExecutor, coPIToAddExecutor, spToAddExecutor,
//				coPIToDeleteExecutor, spToDeleteExecutor, addPropertiesToNodeExecutor,
//				removePropertiesFromChildrenExecutor, allChildrenHavePropertiesExecutor, iRBApprovalRequired,
//				getAncestorInPCExecutor, getChildInPCExecutor, getChildrenInPCExecutor, getChildExecutor,
//				getAncestorsInPCExecutor);
//		
//		//creating the pdp
//
//		PDP pdp = new PDP(new PAP(graph, prohibitions, new MemObligations()), eppOptions);
//		if (graph.exists("super_pc_rep")) {
//			graph.deleteNode("super_pc_rep");
//		}
//		pdp.getPAP().getObligationsPAP().add(obligation, true);
//		return pdp;
	}
	
	
	static public void reachObligationGPMS(Graph graph, Prohibitions prohibitions, Obligation obligation, String ruleLabel) throws Exception {
//		PDP pdp = getPdpGPMS(graph, prohibitions, obligation);
//		switch(ruleLabel) {
//		case "create_proposal":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			break;
//		case "add_copi":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			break;
//		case "submit_proposal":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			break;
//		case "add_sp":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			break;
//		case "delete_copi":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new DeleteCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			break;
//		case "delete_sp":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new DeleteSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			break;
//		case "chair_approve":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			break;
//		case "chair_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			break;
//		case "bm_approve":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			break;
//		case "bm_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			break;
//		case "dean_approve":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			break;
//		case "dean_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			break;
//		case "irb_approve"://there are two possible paths to trigger irb_approve obligation; before or after dean_approve
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			break;
//		case "irb_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.IRB_APPROVAL)), "irbUser", "process");
//			break;
//		case "ra_approve":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
//			break;
//		case "ra_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
//			break;
//		case "rd_approve":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
//			break;
//		case "rd_disapprove":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
//			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
//			break;
//		case "ra_submit":
//			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("PDSWhole")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddCoPIEvent(graph.getNode("CoPI"), graph.getNode("tomtom")), "nazmul", "process");
//			pdp.getEPP().processEvent(new AddSPEvent(graph.getNode("SP"), graph.getNode("vlad")), "tomtom", "process");
//			pdp.getEPP().processEvent(new SubmitEvent(graph.getNode("PDSWhole"), true),"nazmul", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.CHAIR_APPROVAL)), "ChairChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmCSUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.BM_APPROVAL)), "bmChemUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOEUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.DEAN_APPROVAL)), "DeanCOASUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.IRB_APPROVAL)),"irbUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RA_APPROVAL)), "raUser", "process");
//			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(Constants.RD_APPROVAL)), "rdUser", "process");
//			pdp.getEPP().processEvent(new SubmitRAEvent(graph.getNode("PDSWhole")), "raUser", "process");
//			break;
//		default:
//			break;
//		}
	}	
	
	public static AccessRequest decideEquivalentMutant (Obligation obligation, Obligation mutant, AccessRequest ar, String ruleLabel) throws Exception {
		Set<String> attributeList = new HashSet<String>();
		Set<String> tmpList = new HashSet<String>();
		Set<String> allList = new HashSet<String>();
		AccessRequest q;
		Map<String, Set<String>> CapabilityList = null;
		Map<String, Set<String>> CapabilityListMutant = null;
		Map<String, Set<String>> ACL = null;
		Map<String, Set<String>> ACLM = null;
		
		getAffectedAttributes(attributeList, ruleLabel);
		for (String attribute : attributeList) {
			tmpList.clear();
			getAllAscendant(attribute, tmpList, graph);
			allList.addAll(tmpList);
		}
		for (String attribute : attributeList) {
			//attribute serves as source attribute
			Graph graph = createCopy();
			Prohibitions prohibitions = createProhibitionsCopy();
			reachObligation(graph, prohibitions, obligation, ar, ruleLabel);
			PReviewDecider decider = new PReviewDecider(graph, prohibitions);
			if (graph.exists(attribute)) {
				CapabilityList = decider.getCapabilityList(attribute, null);
			}
//			Map<String, Set<String>> CapabilityList = decider.getCapabilityList(attribute, null);
			
			graph = createCopy();
			prohibitions = createProhibitionsCopy();
//			System.out.println(graph.getNodes());
			reachObligation(graph, prohibitions, mutant, ar, ruleLabel);
//			System.out.println(graph.getNodes());
			decider = new PReviewDecider(graph, prohibitions);
			if (graph.exists(attribute)) {
				CapabilityListMutant = decider.getCapabilityList(attribute, null);
			}
//			Map<String, Set<String>> CapabilityListMutant = decider.getCapabilityList(attribute, null);
		
			if (CapabilityList == null) {
				q = compareTwoLists(CapabilityListMutant, CapabilityList, "UA");
			} else if (CapabilityListMutant == null) {
				q = compareTwoLists(CapabilityList, CapabilityListMutant, "UA");
			} else {
				q = CapabilityList.size() >= CapabilityListMutant.size() ? 
					compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
					compareTwoLists(CapabilityListMutant, CapabilityList, "UA");
			}
			if (q != null)
				return new AccessRequest(attribute, q.getAR(), q.getTA());
			
			//attribute serves as target attribute
			graph = createCopy();
			prohibitions = createProhibitionsCopy();
			reachObligation(graph, prohibitions, obligation, ar, ruleLabel);
			decider = new PReviewDecider(graph, prohibitions);
			if (graph.exists(attribute)) {
				ACL = decider.generateACL(attribute, null);
			}
//			Map<String, Set<String>> ACL = decider.generateACL(attribute, null);
			
			graph = createCopy();
			prohibitions = createProhibitionsCopy();
			reachObligation(graph, prohibitions, mutant, ar, ruleLabel);
			decider = new PReviewDecider(graph, prohibitions);
			if (graph.exists(attribute)) {
				ACLM = decider.generateACL(attribute, null);
			}
//			Map<String, Set<String>> ACLM = decider.generateACL(attribute, null);
			
			if (ACL == null) {
				q = compareTwoLists(ACLM, ACL, "OA");
			} else if (ACLM == null) {
				q = compareTwoLists(ACL, ACLM, "OA");
			} else {
				q = ACL.size() >= ACLM.size() ?
					compareTwoLists(ACL, ACLM, "OA") :
					compareTwoLists(ACLM, ACL, "OA");
			}
			if (q != null)
				return new AccessRequest(q.getSA(), q.getAR(), attribute);
		}
		return null;
	}
	
	//check event pattern of obligation
	//return true if it is reachable
	public static boolean obligationIsReachable (Obligation mutant, String ruleLabel) {
		//FIXME
		return true;
	}
	
	//check response pattern of obligation
	//return true if any privilege set of any attribute would be changed
	public static boolean privilegeIsChanged (Obligation mutant, String ruleLabel) {
		//FIXME
		return true;
	}
	
	public static List<String> getSubject (Subject s) {
		if (s == null)
			return null;
		List<String> tmpList = new ArrayList<String>();
		if (s.getUser() != null)
			tmpList.add(s.getUser());
		if (s.getAnyUser() != null)
			tmpList.addAll(s.getAnyUser());
		if (s.getProcess() != null)
			tmpList.add(s.getProcess().getValue());
		return tmpList;
	}
	
	public static List<String> getTarget (Target t) {
		if (t == null)
			return null;
		List<String> tmpList = new ArrayList<String>();
		if (t.getPolicyElements() != null) {
			for (EvrNode en : t.getPolicyElements()) {
				tmpList.add(en.getName());
			}
		}
		if (t.getContainers() != null) {
			for (EvrNode en : t.getContainers()) {
				tmpList.add(en.getName());
			}
		}
		return tmpList;
	}
	
//	public static String getFireObligationConstraint (EventPattern event, String label) throws PMException {
//		String constraint;
//		List<String> subjects = getSubject(event.getSubject());
//		if (subjects == null) {
//			subjects = POMA.Utils.getUAsInGraph(graph);
//			subjects.addAll(POMA.Utils.getUsInGraph(graph));
//		}
//		List<String> targets = getTarget(event.getTarget());
//		if (targets == null) {
//			targets = POMA.Utils.getUAsInGraph(graph);
//			targets.addAll(POMA.Utils.getUsInGraph(graph));
//			targets.addAll(POMA.Utils.getOAsInGraph(graph));
//			targets.addAll(POMA.Utils.getOsInGraph(graph));
//		}
//		for (String s : subjects) {
//			for (String ar : event.getOperations()) {
//				for (String at : targets) {
//					//return first available fire-request
//					constraint = "OBLIGATIONLABEL(" + label + ","+ s + "," + ar + "," + at + ")";
//					return constraint;
//				}
//			}
//		}
//		return null;
//	}
	
	//this constraint 
	public static String getPostConstraint (Action action, String type) throws PMException {
		switch (type) {
		case "remove":
			String postConstraint = "";
			if (action instanceof CreateAction) {
//				int i = 0;
//				for (CreateNode createNode: ((CreateAction) action).getCreateNodesList()) {
//					String what = createNode.getWhat().getName();
//					String where = createNode.getWhere().getName();
//					if (i == 0) {
//						postConstraint = andP("NODEEXISTS(" + what + ")", "EXPLICITASSIGN(" + what + "," + where + ")");
//					} else
//						andP(postConstraint, andP("NODEEXISTS(" + what + ")", "EXPLICITASSIGN(" + what + "," + where + ")"));
//					i++;
//				}
			} else if (action instanceof AssignAction) {
				int i = 0;
				for (Assignment assignment : ((AssignAction) action).getAssignments() ) {
					String what = assignment.getWhat().getName();
					String where = assignment.getWhere().getName();
					if (i == 0) {
						postConstraint = "EXPLICITASSIGN(" + what + "," + where + ")";
					} else 
						andP(postConstraint, "EXPLICITASSIGN(" + what + "," + where + ")");
					i++;
				}
			} else if (action instanceof GrantAction) {
				String subject = ((GrantAction) action).getSubject().getName();
				List<String> operations = ((GrantAction) action).getOperations();
				String target = ((GrantAction) action).getTarget().getName();
				if (target == null)
					target = "?t";
				int i = 0;
				for (String op : operations) {
					if (i == 0) {
						postConstraint = "ASSOCIATE(" + subject + "," + op + "," + target + ")";
					} else 
						postConstraint = andP(postConstraint, "ASSOCIATE(" + subject + "," + op + "," + target + ")");
					i++;
				}
			
			} else if (action instanceof DenyAction) {
//				String subject = ((DenyAction) action).getSubject().getName();
//				List<String> operations = ((DenyAction) action).getOperations();
//				List<Container> containers = ((DenyAction) action).getTarget().getContainers();
//				for (String op : operations) {
//					for (Container c : containers) {
//						tmpConstraints.add("PERMIT(" + subject + "," + op + "," + c.getName() + ")");
//					}
//				}
			
				//FIXME: prohibitions hot handled
				return null;
			} else if (action instanceof DeleteAction) {
				if (((DeleteAction) action).getNodes() != null) {
					int i = 0;
					for (EvrNode evrNnode : ((DeleteAction) action).getNodes()) {
						String name = evrNnode.getName();
						if (i == 0) {
							postConstraint = "NOT(NODEEXISTS(" + name + "))";
						} else 
							andP(postConstraint, "NOT(NODEEXISTS(" + name + "))");
						i++;
					}
				}
				//FIXME: this only applies to 1 descendant conditon!!!!
				if (((DeleteAction) action).getAssignments() != null) {
					AssignAction assignAction = ((DeleteAction) action).getAssignments();
					int i = 0;
					for (Assignment assignment : assignAction.getAssignments()) {
						String what = assignment.getWhat().getName();
						String where = assignment.getWhere().getName();
						if (i == 0) {
							postConstraint = "NOT(EXPLICITASSIGN(" + what + "," + where + "))";
						} else 
							andP(postConstraint, "NOT(EXPLICITASSIGN(" + what + "," + where + "))");
						i++;
					}
				}
				if (((DeleteAction) action).getAssociations() != null) {
					List<GrantAction> grantList = ((DeleteAction) action).getAssociations();
					int i = 0;
					postConstraint = null;
					for (GrantAction gAction : grantList) {
						String subject = ((GrantAction) gAction).getSubject().getName();
						List<String> operations = ((GrantAction) gAction).getOperations();//operation cannot be empty
						String target = ((GrantAction) gAction).getTarget().getName();
						if (target == null)
							target = "?t";
//						String operation = "{";
//						String firstOp = operations.get(0);
//						if(operations.size() != 0)
//							operation += firstOp;
//						for (String op : operations) {
//							if (firstOp.equals(op))
//								continue;
//							operation += "," + op; 
//						}
//						operation += "}";
//					
//						if (i == 0) {
//							postConstraint = "NOT(ASSOCIATE(" + subject + "," + operation + "," + target + "))";
//						} else {
//							andP(postConstraint, "NOT(ASSOCIATE(" + subject + "," + operation + "," + target + "))");
//						}
						String tmpS = null;
						for (String op : operations) {
							if (tmpS == null)
								tmpS = "NOT(ASSOCIATE(" + subject + "," + op + "," + target + "))";
							else
								tmpS = orP(tmpS, "NOT(ASSOCIATE(" + subject + "," + op + "," + target + "))");
						}
						if (i == 0) {
							postConstraint = tmpS;
						} else {
							postConstraint = andP(postConstraint, tmpS);
						}
						i++;
					}
				}
			}
			return postConstraint;
		}
		return null;
	}
	
	public static List<String> getAllSubject (EventPattern event) throws PMException {
		List<String> subjects = getSubject(event.getSubject());
		if (subjects == null) {
			return POMA.Utils.getUsInGraph(graph);
		} else {
			return subjects;
		}
	}
	
	public static List<String> getAllTarget (EventPattern event) throws PMException {
		List<String> target = getTarget(event.getTarget());
		if (target == null) {
			return POMA.Utils.getOAsInGraph(graph);
		} else {
			return target;
		}
	}
	
	public static List<String> getReachabilityConstraint (EventPattern event) throws PMException {
		List<String> tmpConstraints = new ArrayList<String>();
		List<String> allUs = new ArrayList<String>();
		List<String> subjects = getSubject(event.getSubject());
		if (subjects == null) {
			allUs.addAll(POMA.Utils.getUsInGraph(graph));
		} else {
			for (String user : POMA.Utils.getUsInGraph(graph)) {
				for (String subject : subjects) {
					if (Utils.isContained(user, subject, graph)) {
						allUs.add(user);
						break;
					}
				}
			}
		}
		List<String> targets = getTarget(event.getTarget());
		if (targets == null) {
			targets = POMA.Utils.getUAsInGraph(graph);
			targets.addAll(POMA.Utils.getUsInGraph(graph));
			targets.addAll(POMA.Utils.getOAsInGraph(graph));
			targets.addAll(POMA.Utils.getOsInGraph(graph));
		}
		for (String user : allUs) {
			for (String ar : event.getOperations()) {
				for (String at : targets) {
					tmpConstraints.add("PERMIT(" + user + "," + ar + "," + at +")");
				}
			}
		}
		return tmpConstraints;
	}
	
	//FIXME: function unhandled
	public static List<String> getNecessityConstraint (List<Function> fList, Boolean satisfy) {
		List<String> tmpConstraints = new ArrayList<String>();
		return null;
	}
	
	//constraintP: action should change permit or deny
	public static List<String> getPropagationConstraintRemove (Action action) {
		List<String> tmpConstraints = new ArrayList<String>();
		if (action instanceof CreateAction) {
//			for (CreateNode createNode: ((CreateAction) action).getCreateNodesList()) {
//				String what = createNode.getWhat().getName();
//				String where = createNode.getWhere().getName();
//				tmpConstraints.add("NOT(NODEEXISTS("+what+")) AND NODEEXISTS(" +where+ ") AND PERMIT(" + where + ",?ar,?at)");
//				tmpConstraints.add("NOT(NODEEXISTS("+what+")) AND NODEEXISTS(" +where+ ") AND PERMIT(?s,?ar," + where + ")");
//			}
//			return null;
		} else if (action instanceof AssignAction) {
			for (Assignment assignment : ((AssignAction) action).getAssignments() ) {
				String what = assignment.getWhat().getName();
				String where = assignment.getWhere().getName();
				String tmp = "PERMIT(" + where + ",?ar,?at)";
				tmp = andP(tmp, "DENY(" + what + ",?ar,?at)");
				tmp = andP(tmp, "NOT(HIERARCHY(" + what + "," + where + "))");
//				tmp = andP(tmp, "NODEEXISTS(" + what + ")");
//				tmp = andP(tmp, "NODEEXISTS(" + where  + ")");
				tmpConstraints.add(tmp);

				tmp = "PERMIT(?s,?ar," + where + ")";
				tmp = andP(tmp, "DENY(?s,?at," + what + ")");
				tmp = andP(tmp, "NOT(HIERARCHY(" + what + "," + where + "))");
//				tmp = andP(tmp, "NODEEXISTS(" + what + ")");
//				tmp = andP(tmp, "NODEEXISTS(" + where  + ")");
				tmpConstraints.add(tmp);
			}
		} else if (action instanceof GrantAction) {
			String subject = ((GrantAction) action).getSubject().getName();
			List<String> operations = ((GrantAction) action).getOperations();
			String target = ((GrantAction) action).getTarget().getName();
			for (String op : operations) {
				String tmp = "DENY(" + subject + "," + op + "," + target + ")";
//				tmp = andP(tmp, "NODEEXISTS(" + subject + ")");
//				tmp = andP(tmp, "NODEEXISTS(" + target + ")");		
				tmpConstraints.add(tmp);
			}
			
		} else if (action instanceof DenyAction) {
//			String subject = ((DenyAction) action).getSubject().getName();
//			List<String> operations = ((DenyAction) action).getOperations();
//			List<Container> containers = ((DenyAction) action).getTarget().getContainers();
//			for (String op : operations) {
//				for (Container c : containers) {
//					tmpConstraints.add("PERMIT(" + subject + "," + op + "," + c.getName() + ")");
//				}
//			}
			
			//FIXME: prohibitions hot handled
			return null;
		} else if (action instanceof DeleteAction) {
			if (((DeleteAction) action).getNodes() != null) {
//				for (EvrNode evrNnode : ((DeleteAction) action).getNodes()) {
//					String name = evrNnode.getName();
//					tmpConstraints.add("PERMIT(" + name + ",?ar,?at) AND NODEEXISTS(" + name + ")");
//					tmpConstraints.add("PERMIT(?s,?ar," + name + ") AND NODEEXISTS(" + name + ")");
//				}
			}
			//FIXME: this only applies to 1 descendant conditon!!!!
			if (((DeleteAction) action).getAssignments() != null) {
				AssignAction assignAction = ((DeleteAction) action).getAssignments();
				for (Assignment assignment : assignAction.getAssignments()) {
					String what = assignment.getWhat().getName();
					String where = assignment.getWhere().getName();
					//FIXME: a special case:PS(where) is subset Association(what)
					//in this situation, removing assignment not affects PS(what)
					//this special case not handled in solver?
					String tmp = "EXPLICITASSIGN(" + what + "," + where + ")";
					tmp = andP(tmp, "NOT(IMPLICITASSIGN(" + what + "," + where + "))");
					tmp = andP(tmp, "PERMIT(" + what + ",?ar,?at)");
					tmp = andP(tmp, "NOT(ASSOCIATE(" + what + ",?ar,?at))");
					tmpConstraints.add(tmp);
					
					tmp = "EXPLICITASSIGN(" + what + "," + where + ")";
					tmp = andP(tmp, "NOT(IMPLICITASSIGN(" + what + "," + where + "))");
					tmp = andP(tmp, "PERMIT(?s,?ar," + what + ")");
					tmp = andP(tmp, "NOT(ASSOCIATE(?s,?ar," + what + "))");
					tmpConstraints.add(tmp);
				}
			}
			if (((DeleteAction) action).getAssociations() != null) {
				List<GrantAction> grantList = ((DeleteAction) action).getAssociations();
				for (GrantAction gAction : grantList) {
					String subject = ((GrantAction) gAction).getSubject().getName();
					List<String> operations = ((GrantAction) gAction).getOperations();//operation cannot be empty
					String target = ((GrantAction) gAction).getTarget().getName();
					if (target == null)
						target = "?t";
					String operation = "{";
					String firstOp = operations.get(0);
					if(operations.size() != 0)
						operation += firstOp;
					for (String op : operations) {
						if (firstOp.equals(op))
							continue;
						operation += "," + op; 
					}
					operation += "}";
					
					String tmp;
					String tmpS;
					for (String op : operations) {
						tmp = "ASSOCIATE(" + subject + "," + op + "," + target + ")";
						tmpS = "ASSOCIATE(?s," + op + ",?at)";
						tmpS = andP(tmpS, "IMPLICITASSIGN(" + subject + ",?s)");
						tmpS = andP(tmpS, "IMPLICITASSIGN(" + target + ",?at)");
						tmpS = "NOT(" + tmpS + ")";
						tmp = andP(tmp, tmpS);
						
						tmpConstraints.add(tmp);
					}
					
				}
			}
//			//FIXME: below not handled
//			//prohibitions
//			if (((DeleteAction) action).getProhibitions() != null) {
//				List<String> prohibitionList = ((DeleteAction) action).getProhibitions();
//				for (String name : prohibitionList) {
//					for (Prohibition p : Utils.getProhibitionList()) {
//						if (p.getName().equals(name)) {
//							attributeList.add(p.getSubject());
//						}
//					}
//				}
//			}
//			//obligations
//			if (((DeleteAction) action).getRules() != null) {
//				List<String> ruleList = ((DeleteAction) action).getRules();
//				for (String name : ruleList) {
//					getAffectedAttributes(attributeList, name);
//				}
//			}
		}
		return tmpConstraints;
	}
	
	//a:old what, b:old where, c:new where
	public static String getPropagationConstraintCDA (String a, String b, String c) {
		String tmpConstraints1;
		String tmpConstraints2;
		
		String tmp1 = "PERMIT(" + c + ",?ar,?at)";
		tmp1 = andP(tmp1,"DENY(" + a + ",?at,?at)");
		String tmp2 = "PERMIT(?s,?ar," + c + ")";
		tmp2 = andP(tmp2,"DENY(?s,?ar," + a + ")");
		tmpConstraints1 =  orP(tmp1,tmp2);
		
		tmp1 = "PERMIT(" + a + ",?ar,?at1)";
		tmp1 = andP(tmp1,"DENY(" + c + ",?ar,?at1)");
		tmp1 = andP(tmp1,"NOT(ASSOCIATE(" + a + ",?ar,?at2))");
		tmp1 = andP(tmp1,"IMPLICITASSIGN(?at1,?at2)");
		tmp2 = "PERMIT(?s1,?ar,"+ a + ")";
		tmp2 = andP(tmp2,"DENY(?s1,?ar," + c + ")");
		tmp2 = andP(tmp2,"NOT(ASSOCIATE(?s2,?ar," + a + "))");
		tmp2 = andP(tmp2,"IMPLICITASSIGN(?s1,?s2)");
		tmpConstraints2 = orP(tmp1,tmp2);
		
		return orP(tmpConstraints1,tmpConstraints2);
	}
	
	//a:old what, b:old where, c:new where
		public static String getPropagationConstraintCTG (String a, String b, String c, List<String> operations) {
			String tmpConstraint = null;
			
			for (String op : operations) {
				String tmp1 = "PERMIT("+ a +"," + op + "," + b + ")";
				tmp1 = andP(tmp1,"DENY("+ a +"," + op + "," + c + ")");
				String tmp2 = "DENY(" + a + "," + op + "," + b + ")";
				tmp2 = andP(tmp2,"NOT(IMPLICITASSIGN(" + b + "," + c + "))");
				if (tmpConstraint == null)
					tmpConstraint = orP(tmp1,tmp2);
				else
					tmpConstraint = orP(tmpConstraint, orP(tmp1,tmp2));
			}
			return tmpConstraint;
		}
	
	public static List<AccessRequest> sendToSolver (Graph g, Prohibitions p, Obligation ob, String preConstraints, String postConstraint) throws Exception {
		//FIXME:ob here is the version W/O condition, the original ob ignored/ only in LAWFIRM example
		//once below line works, it blocks solver finding solution with mutant obligation
//		ob = readObligation("Policies/SolverVerification/LawFirm/ObligationsNoCondition.yml");
		
		List<AccessRequest> eventList= new ArrayList<AccessRequest>();
		ObligationChecker checker = new ObligationChecker(g, ob);
		checker.setBound(7);
//		checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
		//constraint example"(PERMIT(Attorneys2U, accept, Case3Info) OR PERMIT(Attorneys2U, accept, Case3Info));"
		Solution solution = checker.solveConstraint(preConstraints, postConstraint);
		if (solution == null)
			return null;
		System.out.println(solution);
		//parse received string into list of struct AccessRequest
		for (ObligationFiring event : solution.getObligationFirings()) {
			AccessRequest q = new AccessRequest(event.getSubject(),event.getEvent(),event.getObject());
			eventList.add(q);
		}
		
//		Variables vs = solution.getVariables();
//		for (Variable v : vs.getVariables()) {
//			attributeList.add(v.getAssignment());
//		}

		return eventList;
	}
	
	static public AccessRequest verifyEventList(PReviewDecider deciderI, PReviewDecider deciderM, Set<String> attributeList) throws Exception {
		//FIXME: return null currently; wait sample ready to test
//		if (true)
//			return null;
		Map<String, Set<String>> CapabilityList = null;
		Map<String, Set<String>> CapabilityListMutant = null;
		Map<String, Set<String>> ACL = null;
		Map<String, Set<String>> ACLM = null;
		AccessRequest q;
		
		
		for (String attribute : attributeList) {
			CapabilityList = deciderI.getCapabilityList(attribute, null);
			CapabilityListMutant = deciderM.getCapabilityList(attribute, null);
		
			if (CapabilityList == null) {
				q = compareTwoLists(CapabilityListMutant, CapabilityList, "UA");
			} else if (CapabilityListMutant == null) {
				q = compareTwoLists(CapabilityList, CapabilityListMutant, "UA");
			} else {
				q = CapabilityList.size() >= CapabilityListMutant.size() ? 
					compareTwoLists(CapabilityList, CapabilityListMutant, "UA") :
					compareTwoLists(CapabilityListMutant, CapabilityList, "UA");
			}
			if (q != null) {
				System.out.println("Original:" + CapabilityList.toString());
				System.out.println("Mutant:" + CapabilityListMutant.toString());
				return new AccessRequest(attribute, q.getAR(), q.getTA());
			}
				
			
			ACL = deciderI.generateACL(attribute, null);
			ACLM = deciderM.generateACL(attribute, null);
			
			if (ACL == null) {
				q = compareTwoLists(ACLM, ACL, "OA");
			} else if (ACLM == null) {
				q = compareTwoLists(ACL, ACLM, "OA");
			} else {
				q = ACL.size() >= ACLM.size() ?
					compareTwoLists(ACL, ACLM, "OA") :
					compareTwoLists(ACLM, ACL, "OA");
			}
			if (q != null) {
				System.out.println("Original:" + ACL.toString());
				System.out.println("Mutant:" + ACLM.toString());
				return new AccessRequest(q.getSA(), q.getAR(), attribute);
			}
				
		}
		
		//return true if assert results are different between initial obligation and mutant obligation
		return null;
	}
	
	static public AccessRequest runPolicyMachine (Graph graphI, Prohibitions prohibitionsI, Obligation obligation, Graph graphM, Prohibitions prohibitionsM, Obligation mutant, List<AccessRequest> eventList, List<AccessRequest> actualEventList, Set<String> attributeList) throws Exception {
		PDP pdpI = getPdpLawFirm(graphI, prohibitionsI, obligation);
		PDP pdpM = getPdpLawFirm(graphM, prohibitionsM, mutant);
		PReviewDecider deciderI = new PReviewDecider(graphI, prohibitionsI);
		PReviewDecider deciderM = new PReviewDecider(graphM, prohibitionsM);
		AccessRequest decideq;
		
//		System.out.println(GraphSerializer.toJson(graphI));
//		System.out.println(GraphSerializer.toJson(graphM));
		
		for (AccessRequest q : eventList) {
			if (obligation.getLabel().equals("LawUseCase Obligations")) {
				switch (q.getAR()) {
				case "accept":
					pdpI.getEPP().processEvent(new AcceptEvent(graphI.getNode(q.getTA())), q.getSA(), "Accept");
					pdpM.getEPP().processEvent(new AcceptEvent(graphM.getNode(q.getTA())), q.getSA(), "Accept");
					System.out.println(q.getSA() + "||" + q.getTA());
					break;
//				case "create":
//					pdp.getEPP().processEvent(new CreateEvent(graphI.getNode(q.getTA())), q.getSA(), "Create");
//					break;
//				case "approve":
//					pdpI.getEPP().processEvent(new ApproveEvent(graphI.getNode(q.getTA())), q.getSA(), "Approve");
//					pdpM.getEPP().processEvent(new ApproveEvent(graphM.getNode(q.getTA())), q.getSA(), "Approve");
//					break;
//				case "disapprove":
//					pdpI.getEPP().processEvent(new DisapproveEvent(graphI.getNode(q.getTA())), q.getSA(), "Disapprove");
//					pdpM.getEPP().processEvent(new DisapproveEvent(graphM.getNode(q.getTA())), q.getSA(), "Disapprove");
//					break;
//				case "withdraw":
//					pdpI.getEPP().processEvent(new WithdrawEvent(graphI.getNode(q.getTA())), q.getSA(), "Withdraw");
//					pdpM.getEPP().processEvent(new WithdrawEvent(graphM.getNode(q.getTA())), q.getSA(), "Withdraw");
//					break;
//				case "refuse":
//					pdpI.getEPP().processEvent(new RefuseEvent(graphI.getNode(q.getTA())), q.getSA(), "Refuse");
//					pdpM.getEPP().processEvent(new RefuseEvent(graphM.getNode(q.getTA())), q.getSA(), "Refuse");
//					break;
//				case "finalAccept":
//					break;
				default:
					break;
				}
			} else if (obligation.getLabel().equals("GPMS Obligations")) {
//				switch (q.getAR()) {
//				case "submit":
//					pdpI.getEPP().processEvent(new SubmitEvent(graphI.getNode(q.getTA()), true),q.getSA(), "Submit");
//					pdpM.getEPP().processEvent(new SubmitEvent(graphM.getNode(q.getTA()), true),q.getSA(), "Submit");
//					break;
//				case "approve":
//					pdpI.getEPP().processEvent(new ApproveEvent(graphI.getNode(q.getTA())), q.getSA(), "Approve");
//					pdpM.getEPP().processEvent(new ApproveEvent(graphM.getNode(q.getTA())), q.getSA(), "Approve");
//					break;
//				case "archive":
//					pdpI.getEPP().processEvent(new ArchiveEvent(graphI.getNode(q.getTA())), q.getSA(), "Archive");
//					pdpM.getEPP().processEvent(new ArchiveEvent(graphM.getNode(q.getTA())), q.getSA(), "Archive");
//					break;
//				case "create":
//					break;
//				case "add-copi":
//					break;
//				case "add-sp":
//					break;
//				case "delete-copi":
//					break;
//				case "delete-sp":
//					break;
//				case "disapprove":
//					break;
//				case "RAsubmit":
//					break;
//				default:
//					break;
//				}
			}
//			System.out.println(GraphSerializer.toJson(graphI));
//			System.out.println(GraphSerializer.toJson(graphM));
			actualEventList.add(q);
			decideq = verifyEventList(deciderI, deciderM, attributeList);
			if(decideq != null)
				return decideq;
		}
		return null;
	}
	
	static public String andP (String a, String b) {
		return "(" + a + " AND " + b + ")";
	}
	
	static public String orP (String a, String b) {
		return "(" + a + " OR " + b + ")";
	}
	
	static public String generatePConstraint(ResponsePattern responsePattern) {
		String PConstraint = null;
		List<Action> actions = responsePattern.getActions();
		for (Action action : actions) {
			List<String> constraintP = getPropagationConstraintRemove(action);
			if (constraintP == null)
				continue;
			for (String s : constraintP) {
				if (PConstraint == null) {
					PConstraint = s;
					continue;
				}
				PConstraint = Utils.orP(PConstraint, s);
			}
		}
		return PConstraint;
	}
	
	static public String generatePConstraintOneAction(Action action) {
		String PConstraint = null;
		List<String> constraintP = getPropagationConstraintRemove(action);
		if (constraintP == null)
			return null;
		for (String s : constraintP) {
			if (PConstraint == null) {
				PConstraint = s;
				continue;
			}
			PConstraint = Utils.orP(PConstraint, s);
		}
		return PConstraint;
	}
	
	static public String generatePostConstraint(ResponsePattern responsePattern) throws PMException {
		String postConstraint = null;
		List<Action> actions = responsePattern.getActions();
		for (Action action : actions) {
			String tmpPostConstraint = getPostConstraint(action, "remove");
			if (tmpPostConstraint == null) {
				continue;
			}
			if (postConstraint == null)
				postConstraint = tmpPostConstraint;
			else
				postConstraint = Utils.orP(postConstraint, tmpPostConstraint);
		}
//		return postConstraint + ";";
		return postConstraint;
	}
	
	//FIXME: obM is the mutant with condtion
	public static boolean killMutantT (Obligation mutant, String ruleLabel, String preConstraint, String postConstraint, Obligation obM) throws FileNotFoundException, EVRException, Exception {
//		if (true)
//		return true;
		List<AccessRequest> actualEventList = new ArrayList<AccessRequest>();
		AccessRequest q;
		//send to solver
		try {
			List<AccessRequest> eventList = sendToSolver(createCopy(), createProhibitionsCopy(), createObligationCopy(), preConstraint, postConstraint);
			if (eventList == null) {
				eventList = sendToSolver(createCopy(), createProhibitionsCopy(), mutant, preConstraint, postConstraint);
			}
			if (eventList == null) {
				//equivalent mutant
				System.out.println("Mutant not killed! No solution found!");
				return false;
			}
			
			//get potentially affected attributes
			Set<String> attributeList = new HashSet<String>();
			Set<String> potentialAttributeList = new HashSet<String>();
			getAffectedAttributes(potentialAttributeList, ruleLabel);
			filterNotU(potentialAttributeList, attributeList);
					
			//run policy machine
			Graph graphI = createCopy();
			Graph graphM = createCopy();
			Prohibitions prohibitionsI = createProhibitionsCopy();
			Prohibitions prohibitionsM = createProhibitionsCopy();
//			System.out.print(GraphSerializer.toJson(graphI));
//			System.out.print(GraphSerializer.toJson(graphM));
			
			//FIXME:for running obligation, load obligtion with condition to avoid PM error
			Obligation ob;
			if (mutant.getLabel().equals("LawUseCase Obligations")) {
				ob = readObligation("Policies/SolverVerification/LawFirm/ObligationsWithCondition.yml");
				q = runPolicyMachine(graphI, prohibitionsI, ob, graphM, prohibitionsM, obM, eventList, actualEventList, attributeList);
//				q = verifyEventList(ob, obM, eventList, ruleLabel, attributeList);
			} else {
				ob = createObligationCopy();
				q = runPolicyMachine(graphI, prohibitionsI, ob, graphM, prohibitionsM, obM, eventList, actualEventList, attributeList);
//				q = verifyEventList(ob, mutant, eventList, ruleLabel, attributeList);
			}
			
			
			
			if (q == null) {
			//equivalent mutant
				System.out.println("Mutant not killed!");
				return false;
			} else {
				System.out.println("Mutant Killed! Event sequence:");
				for (AccessRequest event : actualEventList) {
					System.out.println("(" + event.getSA() + "," + event.getAR() + "," + event.getTA() + ")");
				}
				System.out.println("Assert request:(" + q.getSA() + "," + q.getAR() + "," + q.getTA() + ").");
				//FIXME: should save actualEventList+assert request, q, into test suite
//				Utils.addToARList(actualEventList);
//				Utils.addToARList(q);
				return true;
			}
		} catch (PMException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	//FIXME: obM is the mutant with condtion
	public static boolean killMutant (Obligation mutant, String ruleLabel, String preConstraint, String postConstraint) throws FileNotFoundException, EVRException, Exception {
		List<AccessRequest> actualEventList = new ArrayList<AccessRequest>();
		AccessRequest q;
		//send to solver
		List<AccessRequest> eventList = sendToSolver(createCopy(), createProhibitionsCopy(), createObligationCopy(), preConstraint, postConstraint);
		if (eventList == null) {
			eventList = sendToSolver(createCopy(), createProhibitionsCopy(), mutant, preConstraint, postConstraint);
		}
		if (eventList == null) {
			//equivalent mutant
			System.out.println("Mutant not killed! No solution found!");
			return false;
		}
		
		//get potentially affected attributes
		Set<String> attributeList = new HashSet<String>();
		Set<String> potentialAttributeList = new HashSet<String>();
		getAffectedAttributes(potentialAttributeList, ruleLabel);
		filterNotU(potentialAttributeList, attributeList);
				
		//run policy machine
		Graph graphI = createCopy();
		Graph graphM = createCopy();
		Prohibitions prohibitionsI = createProhibitionsCopy();
		Prohibitions prohibitionsM = createProhibitionsCopy();
		
		
		//run policy machine
		try {
			//FIXME:for running obligation, load obligtion with condition to avoid PM error
			//CTG not available in LawFirm policies
			q = runPolicyMachine(graphI, prohibitionsI, createObligationCopy(), graphM, prohibitionsM, mutant, eventList, actualEventList, attributeList);
			
			if (q == null) {
			//equivalent mutant
				System.out.println("Mutant not killed!");
				return false;
			} else {
				System.out.println("Mutant Killed! Event sequence:");
				for (AccessRequest event : actualEventList) {
					System.out.println("(" + event.getSA() + "," + event.getAR() + "," + event.getTA() + ")");
				}
				System.out.println("Assert request:(" + q.getSA() + "," + q.getAR() + "," + q.getTA() + ").");
				//FIXME: should save actualEventList+assert request, q, into test suite
//				Utils.addToARList(actualEventList);
//				Utils.addToARList(q);
				return true;
			}
		} catch (PMException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	static void filterNotU (Set<String> potentialAttributeList, Set<String> userList) throws PMException {
		List<Node> nodeList = getUsInGraph(graph);
		for (Node node : nodeList) {
			for (String s : potentialAttributeList) {
				if (isContained(node.getName(), s, graph)) {
					userList.add(node.getName());
					break;
				}
			}
		}
	}
	
	static AccessRequest pretestArlist (PReviewDecider deciderO, PReviewDecider deciderM, List<AccessRequest> arList) throws PMException {
		for (AccessRequest q : arList) {
			if (deciderO.check(q.getSA(),"",q.getTA(),q.getAR()) != deciderM.check(q.getSA(),"",q.getTA(),q.getAR())) {
				System.out.println("Pre-Tested");
				return q;
			}
		}
		return null;
	}
	
	//below is test function for sample GPMS
//	public static int tryKillMutants () throws Exception {
//		Graph graphI = createCopy();
//		Graph graphM = createCopy();
//		boolean res1 = false;
//		boolean res2 = false;
//		Obligation obligation = EVRParser.parse(new FileInputStream(new File("Policies/SolverVerification/sample/GPMS/Obligations.yml")));
//		Obligation mutant = EVRParser.parse(new FileInputStream(new File("Policies/SolverVerification/sample/GPMS/Mutant.yml")));
////		Obligation mutant = EVRParser.parse(new FileInputStream(new File("Policies/SolverVerification/sample/GPMS/Obligations.yml")));
//		
//		PDP pdpI = getPdpLawFirm(graphI, null, obligation);
//		PDP pdpM = getPdpLawFirm(graphM, null, mutant);
//		PReviewDecider deciderI = new PReviewDecider(graphI);
//		PReviewDecider deciderM = new PReviewDecider(graphM);
//		List<AccessRequest> eventList = new ArrayList<AccessRequest>();
//		
////		Set<String> attributeList = new HashSet<String>();
////		Set<String> potentialAttributeList = new HashSet<String>();
////		getAffectedAttributes(potentialAttributeList, "obligation3");
////		filterNotU(potentialAttributeList, attributeList);
//		
//		eventList.add(new AccessRequest("Vlad","submit","PDSWhole"));//fault 6//1
//		eventList.add(new AccessRequest("UChair","approve","PDSWhole"));
//		eventList.add(new AccessRequest("UBM","approve","PDSWhole"));//fault 4,5//2
//		eventList.add(new AccessRequest("UDean","approve","PDSWhole"));//fault 3//3
//		eventList.add(new AccessRequest("URA","submit","PDSWhole"));//fault 2,8//4
//		eventList.add(new AccessRequest("URD","archive","PDSWhole"));//fault 1,7//5
//		
//		for (AccessRequest q : eventList) {
//			switch (q.getAR()) {
//				case "submit":
//					pdpI.getEPP().processEvent(new SubmitEvent(graphI.getNode(q.getTA()), true),q.getSA(), "Submit");
//					pdpM.getEPP().processEvent(new SubmitEvent(graphM.getNode(q.getTA()), true),q.getSA(), "Submit");
//					break;
//				case "approve":
//					pdpI.getEPP().processEvent(new ApproveEvent(graphI.getNode(q.getTA())), q.getSA(), "Approve");
//					pdpM.getEPP().processEvent(new ApproveEvent(graphM.getNode(q.getTA())), q.getSA(), "Approve");
//					break;
//				case "archive":
//					pdpI.getEPP().processEvent(new ArchiveEvent(graphI.getNode(q.getTA())), q.getSA(), "Archive");
//					pdpM.getEPP().processEvent(new ArchiveEvent(graphM.getNode(q.getTA())), q.getSA(), "Archive");
//					break;
//				default:
//					break;
//			}
////			decideq = verifyEventList(deciderI, deciderM, attributeList);
////			if(decideq != null)
////				return decideq;
//			
//		}
//		
//		//detect fault6
//		res1 = deciderI.check("Vlad", "", "PDSWhole", "submit");
//		res2 = deciderM.check("Vlad", "", "PDSWhole", "submit");
//		System.out.println(res1 + "......" + res2);
//		
////		//detect fault4,5
//		res1 = deciderI.check("UBM", "", "PDSWhole", "approve");
//		res2 = deciderM.check("UBM", "", "PDSWhole", "approve");
//		System.out.println(res1 + "......" + res2);
//		
////		//detect faul3
//		res1 = deciderI.check("UDean", "", "PDSWhole", "approve");
//		res2 = deciderM.check("UDean", "", "PDSWhole", "approve");
//		System.out.println(res1 + "......" + res2);
//		
////		//detect fault2,8
//		res1 = deciderI.check("URA", "", "PDSWhole", "submit");
//		res2 = deciderM.check("URA", "", "PDSWhole", "submit");
//		System.out.println(res1 + "......" + res2);
////				
//		//detect fault1,7
//		res1 = deciderI.check("UChair", "", "PDSWhole", "approve");
//		res2 = deciderM.check("UChair", "", "PDSWhole", "approve");
//		System.out.println(res1 + "......" + res2);
//		
//		return 0;
//	}
	
	//below is test function for sample LawFirm
	public static int tryKillMutants () throws Exception {
		Graph graphI = createCopy();
		Graph graphM = createCopy();
		boolean res1 = false;
		boolean res2 = false;
		Obligation obligation = EVRParser.parse(new FileInputStream(new File("Policies/SolverVerification/sample/LawFirm/Obligations.yml")));
		Obligation mutant = EVRParser.parse(new FileInputStream(new File("Policies/SolverVerification/sample/LawFirm/Mutant.yml")));
		
		PDP pdpI = getPdpLawFirm(graphI, null, obligation);
		PDP pdpM = getPdpLawFirm(graphM, null, mutant);
		PReviewDecider deciderI = new PReviewDecider(graphI);
		PReviewDecider deciderM = new PReviewDecider(graphM);
		List<AccessRequest> eventList = new ArrayList<AccessRequest>();
		
		eventList.add(new AccessRequest("LeadAttorneysU","accept","Case3Info"));//fault 1,2//output 1,2
		
//		eventList.add(new AccessRequest("Attorneys1U","accept","Case3Info2"));//fault 3//output 3
//		
//		eventList.add(new AccessRequest("LeadAttorneysU","accept","Case3Info"));//fault 4//output 3
//		
//		eventList.add(new AccessRequest("Attorneys1U","accept","Case3Info"));
//		eventList.add(new AccessRequest("LeadAttorneysU","accept","Case3Info"));//fault 5//output 4
//		
//		eventList.add(new AccessRequest("Attorneys1U","accept","Case3Info"));
//		eventList.add(new AccessRequest("Attorneys2U","accept","Case3Info"));//fault 6//output 5
//	
		eventList.add(new AccessRequest("Attorneys1U","accept","Case3Info"));
		eventList.add(new AccessRequest("Attorneys2U","refuse","Case3Info"));//fault 7//output 4
		for (AccessRequest q : eventList) {
			switch (q.getAR()) {
			case "accept":
			pdpI.getEPP().processEvent(new AcceptEvent(graphI.getNode(q.getTA())), q.getSA(), "Accept");
			pdpM.getEPP().processEvent(new AcceptEvent(graphM.getNode(q.getTA())), q.getSA(), "Accept");
			System.out.println(q.getSA() + "||" + q.getTA());
			break;
				default:
					break;
			}
//			decideq = verifyEventList(deciderI, deciderM, attributeList);
//			if(decideq != null)
//				return decideq;
			
		}
		
		//detect fault 1
		res1 = deciderI.check("Attorneys1U", "", "Case3Info2", "approve");
		res2 = deciderM.check("Attorneys1U", "", "Case3Info2", "approve");
		System.out.println(res1 + "......" + res2);
		
		//detect fault 2
		res1 = deciderI.check("Attorneys1U", "", "Case3Info2", "refuse");
		res2 = deciderM.check("Attorneys1U", "", "Case3Info2", "refuse");
		System.out.println(res1 + "......" + res2);
		
//		//detect fault 3,4
		res1 = deciderI.check("Attorneys2U", "", "Case3Info2", "refuse");
		res2 = deciderM.check("Attorneys2U", "", "Case3Info2", "refuse");
		System.out.println(res1 + "......" + res2);
		
////		//detect faul 5,7
		res1 = deciderI.check("Attorneys3U", "", "Case3Info", "refuse");
		res2 = deciderM.check("Attorneys3U", "", "Case3Info", "refuse");
		System.out.println(res1 + "......" + res2);
//		
//		//detect faul 6
		res1 = deciderI.check("Attorneys3U", "", "Case3Info2", "refuse");
		res2 = deciderM.check("Attorneys3U", "", "Case3Info2", "refuse");
		System.out.println(res1 + "......" + res2);
		
		return 0;
	}
	
	//parse received string into list of struct AccessRequest
//	static public void parseIntoEventList (String receivedData, List<AccessRequest> eventList) {
//		
//	}
}
