package POMA.Mutation.EquivalentMutantAnalyzer;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;

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
import CaseStudies.LawUseCase.customEvents.ApproveEvent;
import CaseStudies.LawUseCase.customEvents.CreateEvent;
import CaseStudies.LawUseCase.customEvents.DisapproveEvent;
import CaseStudies.LawUseCase.customEvents.WithdrawEvent;

//import CaseStudies.gpms.Constants;
//import CaseStudies.gpms.customEvents.AddCoPIEvent;
//import CaseStudies.gpms.customEvents.AddSPEvent;
//import CaseStudies.gpms.customEvents.ApproveEvent;
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
import gov.nist.csd.pm.epp.EPPOptions;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pap.PAP;
import gov.nist.csd.pm.pdp.PDP;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.obligations.MemObligations;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction.CreateNode;
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
	
	public static List<String> getUsInGraph(Graph graph) throws PMException {
		List<String> Us = new ArrayList<String>();

		Node[] nodes = graph.getNodes().toArray(new Node[graph.getNodes().size()]);
		for (Node node : nodes) {
			if (node.getType() == U) {
				Us.add(node.getName());
			}
		}
		return Us;
	}
	
	public static void addToARList(AccessRequest q) {
		for (AccessRequest ar : arList) {
			if (ar.getSA().equals(q.getSA()) && ar.getAR().equals(q.getAR()) && ar.getTA().equals(q.getTA()))
				return;
		}
		arList.add(q);
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
	
	public static void setObligationMutant(Obligation obligation){
		obligationMutant.setEnabled(obligation.isEnabled());
		obligationMutant.setLabel(obligation.getLabel());
		obligationMutant.setRules(obligation.getRules());
		obligationMutant.setSource(obligation.getSource());
	}
	
	public static String getUserName(int i, List<String> Us) {
		//0 can be replaced by a random number from 0 to length(Us)
		String userName = Us.get(i);
//		System.out.println("changeToUser:" + userName);
		return userName;
	}
	
	//this function returns the list of attributes whose privilege sets might be changed (propagation)
	static public void getAffectedAttributes (Set<String> attributeList, String ruleLabel) throws FileNotFoundException, PMException {
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
		switch(ruleLabel) {
		case "withdraw_case_info":
			if (ar != null) {
				if (ar.getSA() != null) disapproveUser = ar.getSA();
				if (ar.getTA() != null) disapproveNode = ar.getTA();
//				disapproveProcess = "";
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode(disapproveNode)), disapproveUser, disapproveProcess);
			break;
		case "withdraw_case":
			if (ar != null) {
				if (ar.getSA() != null) withdrawUser = ar.getSA();
				if (ar.getTA() != null) withdrawNode = ar.getTA();
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new WithdrawEvent(graph.getNode(withdrawNode)), withdrawUser, withdrawProcess);
			break;
		case "create_case_info":
			if (ar != null) {
				if (ar.getSA() != null) createUser = ar.getSA();
				if (ar.getTA() != null) createNode = ar.getTA();
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			break;
		case "accept_case_LA":
			if (ar != null) {
				if (ar.getSA() != null) laAcceptUser = ar.getSA();
				if (ar.getTA() != null) laAcceptNode = ar.getTA();
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(laAcceptNode)), laAcceptUser, laAcceptProcess);
			break;
		case "accept_refuse_case_A":
			if (ar != null) {
				if (ar.getSA() != null) acceptUser = ar.getSA();
				if (ar.getTA() != null) acceptNode = ar.getTA();
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
			break;
		case "accept_case_Final":
			//FIXME: mutation on process not implemented
			if (ar != null) {
				if (ar.getSA() != null) finalAcceptProcess = ar.getSA();
				if (ar.getTA() != null) finalAcceptNode = ar.getTA();
				finalAcceptUser = "";
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
			//final accept
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(finalAcceptNode)), finalAcceptUser, finalAcceptProcess);
			break;
		case "approve_case":
			if (ar != null) {
				if (ar.getSA() != null) approveUser = ar.getSA();
				if (ar.getTA() != null) approveNode = ar.getTA();
			}
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode(createNode)), createUser, createProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(acceptNode)), acceptUser, acceptProcess);
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode(laAcceptNode)), laAcceptUser, laAcceptProcess);
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode(approveNode)), approveUser, approveProcess);
			break;
			
		default:
			break;
		}
	}
	
	//Function getAccessRequestNoTrigger() should return an access request which cannot trigger obligation in initial policy
	//and it can trigger obligation in mutant
	//the returned access request should be according to mutant
	static public void reachObligationNoTrigger(Graph graph, Prohibitions prohibitions, Obligation obligation, EventPattern eventPattern, String ruleLabel) throws Exception {
		PDP pdp = getPdpLawFirm(graph, prohibitions, obligation);
		switch(ruleLabel) {
		case "withdraw_case_info":
			//FIXME
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new DisapproveEvent(graph.getNode("Apple")), "LeadAttorneys", "");
			break;
		case "withdraw_case":
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new WithdrawEvent(graph.getNode("Apple")), "LeadAttorneys", "Withdraw");
			break;
		case "create_case_info":
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("GeneralInfo")), "Attorneys", "initialCreate");
			break;
		case "accept_case_LA":
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "LeadAttorneys", "finalAccept");
			break;
		case "accept_refuse_case_A":
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "Attorneys", "initialAccept");
			break;
		case "accept_case_Final":
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Apple")), "LeadAttorneys", "finalAccept");
			break;
		case "approve_case":
			pdp.getEPP().processEvent(new CreateEvent(graph.getNode("Case3")), "Attorneys", "initialCreate");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "Attorneys", "initialAccept");
			pdp.getEPP().processEvent(new AcceptEvent(graph.getNode("Case3Info")), "LeadAttorneys", "finalAccept");
			getAccessRequestNoTrigger();
			pdp.getEPP().processEvent(new ApproveEvent(graph.getNode("Apple")), "C-Suit", "approve");
			break;
			
		default:
			break;
		}
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
	
	//If mutation happens in eventPattern, ar could help build:
	//1) the request can trigger obligation in initial policy and cannot trigger obligation in mutant; or 
	//2) the request cannot trigger obligation in initial policy and can trigger obligation in mutant.
	//ar is null if there is no mutation within event pattern
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
			reachObligation(graph, prohibitions, mutant, ar,ruleLabel);
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
	
}
