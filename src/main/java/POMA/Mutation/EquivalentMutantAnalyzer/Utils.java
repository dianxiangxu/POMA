package POMA.Mutation.EquivalentMutantAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opencsv.CSVReader;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class Utils extends MutantTester {
	static Graph g;
	
	public Utils(String testSuit, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		super(testSuit, graph, prohibitions);
	}

	// decide whether a is contained by b or not
	public static boolean isContained(Node nodeA, Node nodeB) throws PMException {
		if (nodeA.getName().equals(nodeB.getName()))
			return true;
		for (String parent : graph.getParents(nodeA.getName())) {
			if (parent.equals(nodeB.getName())) {
				return true;
			} else {
				if (isContained(graph.getNode(parent), nodeB)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Node getPcOf(Node node) throws PMException {
		Node nodePc;

		for (String pc : graph.getPolicyClasses()) {
			nodePc = graph.getNode(pc);
			if (isContained(node, nodePc)) {
				return nodePc;
			}
		}
		return node;
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
	
	public static List<String> getPcList(Node node, Graph graph) throws PMException {
		Node nodePc;
		List<String> pcList = new ArrayList<String>();

		for (String pc : graph.getPolicyClasses()) {
			nodePc = graph.getNode(pc);
			if (isContained(node.getName(), nodePc.getName())) {
				pcList.add(pc);
			}
		}
		return pcList;
	}

	public static boolean isContained(String nodeA, String nodeB) throws PMException {
		if (nodeA.equals(nodeB))
			return true;
		for (String parent : graph.getParents(nodeA)) {
			if (parent.equals(nodeB)) {
				return true;
			} else {
				if (isContained(graph.getNode(parent).getName(), nodeB)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static AccessRequest decideEquivalentMutantForAddingAssignment (Node nodeA, Node nodeB, Graph graph, Prohibitions prohibitions, Map<String, Map> mapSA, Map<String, AssociationList> mapTA) throws PMException {
		g = graph;
		List<Prohibition> prohibitionList = new ArrayList<Prohibition>();
		
		if (prohibitions != null)
			prohibitionList = prohibitions.getAll();

		if (nodeA.getType().toString().equals("UA")) {
			Map<String, OperationSet> associationsA = new HashMap<>();
			Map<String, OperationSet> associationsB = new HashMap<>();
			
			if (!mapSA.containsKey(nodeA.getName())) {
				AssociationsSA(nodeA.getName(), associationsA);
				mapSA.put(nodeA.getName(), associationsA);
			} else {
				associationsA = mapSA.get(nodeA.getName());
			}
		
			if (!mapSA.containsKey(nodeB.getName())) {
				AssociationsSA(nodeB.getName(), associationsB);
				mapSA.put(nodeB.getName(), associationsB);
			} else {
				associationsB = mapSA.get(nodeB.getName());
			}
			for (Map.Entry<String, OperationSet> entryB : associationsB.entrySet()) {
				Set<String> tars = new HashSet<String>();
				for (Map.Entry<String, OperationSet> entryA : associationsA.entrySet()) {
					if (isContained(entryB.getKey(), entryA.getKey())) {
						Set<String> aOp = (Set<String>) entryA.getValue();
						//tars includes all access rights A has formerly had on entryB.getKey()/entryA.getKey()
						//make sure these access rights are not prohibited by prohibitions introduced by B
						for (String ar : aOp) {
							for (Prohibition p : prohibitionList) {
								if (isContained(nodeB.getName(), p.getSubject()) && !isContained(nodeA.getName(), p.getSubject()) 
										&& p.getOperations().contains(ar)) {
									Set<String> ascendantList = new HashSet<String>();
									//if no prohibition, all elements in ascendantList is available to build up q to kill the mutant
									getAllAscendant(entryA.getKey(), ascendantList);
									for (String ascendant : ascendantList) {
										if (isInTPS(ascendant, p)) {
											//this privilege is prohibited by prohibitions introduced by B
											return new AccessRequest(nodeA.getName(), ar, ascendant);
										}
									}
								}
							}
						}
						tars.addAll(aOp);
					}
				}
				
				Set<String> bOp = (Set<String>) entryB.getValue();
				for (String ar : bOp) {
					if (!tars.contains(ar)) {
//						return new AccessRequest(nodeA.getName(), ar, entryB.getKey());
						//assume privilege:(nodeA, ar, entryB) can kill the mutant
						//examine whether new added privilege is prohibited
						//this work has been done ahead
//						for (Prohibition p : prohibitionList) {
//							if (isContained(nodeB.getName(), p.getSubject()) && p.getOperations().contains(ar)) {
//								Set<String> ascendantList = new HashSet<String>();
//								//if no prohibition, all elements in ascendantList is available to build up q to kill the mutant
//								getAllAscendant(entryB.getKey(), ascendantList);
//								for (String ascendant : ascendantList) {
//									if (!isInTPS(ascendant, p)) {
//										return new AccessRequest(nodeA.getName(), ar, ascendant);
//									}
//								}
//								return null;
//							}
//						}
						return new AccessRequest(nodeA.getName(), ar, entryB.getKey());
					}
				}
			}
		}
		
		AssociationList associationsA;
		AssociationList associationsB;
		
		

		//FIXME save dal + ndal correctly
		if (true) {
//		if (!mapTA.containsKey(nodeA.getName())) {
			Map<String, Boolean> searchMap = new HashMap<>();
			List<AWP> dalA = new ArrayList<AWP>();
			List<AWP> ndalA = new ArrayList<AWP>();
			AssociationsTA(nodeA.getName(), getPcList(nodeA, g), dalA, ndalA, searchMap);
			comb(dalA, ndalA, getPcList(nodeA, g));
			associationsA = new AssociationList(dalA, ndalA);
			mapTA.put(nodeA.getName(), associationsA);
		} else {
			associationsA = mapTA.get(nodeA.getName());
		}
		
		//FIXME save dal + ndal correctly
		if (true) {
//		if (!mapTA.containsKey(nodeB.getName())) {
			Map<String, Boolean> searchMap = new HashMap<>();
			List<AWP> dalB = new ArrayList<AWP>();
			List<AWP> ndalB = new ArrayList<AWP>();
			//getPcList(nodeA) is correct, dal/ndal is concept about nodeA
			AssociationsTA(nodeB.getName(), getPcList(nodeA, g), dalB, ndalB, searchMap);
			comb(dalB, ndalB, getPcList(nodeA, g));
			associationsB = new AssociationList(dalB, ndalB);
			mapTA.put(nodeB.getName(), associationsB);
		} else {
			associationsB = mapTA.get(nodeB.getName());
		}
//		if (nodeA.getName().equals("Cases") && nodeB.getName().equals("Case3"))
//		System.out.println("debug here");	
		if (!subSet(nodeA, nodeB)) {
			List<String> totalPcList = getPcList(nodeA, g);
			totalPcList.addAll(getPcList(nodeB, g));
			
			//handle scenario any association-detached node(M: contained by a) has changed to non-association-detached node
			//an association (x,ar,a) in ndalA cannot impose privilege (x,ar,M) on M before mutation
			Set<String> ascendantList = new HashSet<String>();
			getAllAscendant(nodeA.getName(), ascendantList);
			for (String ascendant : ascendantList) {
				if (!subSet(nodeA, graph.getNode(ascendant))) {
					List<String> tmpPcList = getPcList(g.getNode(ascendant), g);
					tmpPcList.removeAll(totalPcList);
					if (tmpPcList.isEmpty()) {
						//if nodeA has no Ndal, this would be skipped
						for (AWP a : associationsA.getNdal()) {
							for (String ar : a.getOP()) { 
								//(a.getSA(), ar, ascendant) is to kill the mutant, then make sure it is not prohibited
								boolean prohibited = false;
								for (Prohibition p : prohibitionList) {
									if (isContained(a.getSA(), p.getSubject()) && p.getOperations().contains(ar)
											&& (isInTPS(ascendant, p) || isInTPS(nodeB.getName(), p))) {
										prohibited = true;
										break;
									}
								}
								if (prohibited)
									continue;
								return new AccessRequest(a.getSA(), ar, ascendant);
							}
						}
					}
				}
			}
			
			//handle scenario any status of association has changed
			AccessRequest q = detectStatusChange(associationsA, associationsB, totalPcList, nodeA, prohibitionList);
			if (q != null) {
				return q;
			}

		}
		
//		if (nodeA.getName().equals("GeneralInfo"))
//			System.out.println("debug here");
		
		for (AWP entryB : associationsB.getNdal()) {
			Set<String> tars = new HashSet<String>();
			for (AWP entryA : associationsA.getNdal()) {
				if (isContained(entryB.getSA(), entryA.getSA())) {
					Set<String> aOp = (Set<String>) entryA.getOP();
					//tars includes all access rights entryB.getSA() has formerly had on A
					//make sure these access rights are not prohibited by prohibitions introduced by B
					for (String ar : aOp) {
						for (Prohibition p : prohibitionList) {
							if (isContained(entryB.getSA(), p.getSubject()) && p.getOperations().contains(ar)) {
//								Set<String> ascendantList = new HashSet<String>();
								if (!isInTPS(nodeA.getName(), p) && isInTPS(nodeB.getName(), p)) {
									//this privilege is prohibited by prohibitions introduced by B
									return new AccessRequest(entryB.getSA(), ar, nodeA.getName());
								}
							}
						}
					}
					tars.addAll(aOp);
				}
			}
					
			Set<String> bOp = (Set<String>) entryB.getOP();
			for (String ar : bOp) {
				if (!tars.contains(ar)) {
//					return new AccessRequest(entryB.getSA(), ar, nodeA.getName());
//					
					boolean prohibited = false;
					for (Prohibition p : prohibitionList) {
						if (isContained(entryB.getSA(), p.getSubject()) && p.getOperations().contains(ar)) {
							prohibited = true;
							Set<String> ascendantList = new HashSet<String>();
							//if no prohibition, all elements in ascendantList is available to build up q to kill the mutant
							getAllAscendant(nodeA.getName(), ascendantList);
							for (String ascendant : ascendantList) {
								if (!isInTPS(ascendant, p))
									return new AccessRequest(entryB.getSA(), ar, ascendant);
							}
//							return null;
						}
					}
					if (prohibited == false)
						return new AccessRequest(entryB.getSA(), ar, nodeA.getName());
				}
			}
		}
		return null;
	}
	
	public static void AssociationsSA (String node, Map<String, OperationSet> associations) throws PMException {
		Map<String, OperationSet> map = (Map<String, OperationSet>) g.getSourceAssociations(node);
		for (Map.Entry<String, OperationSet> entry : map.entrySet()) {
			String target = entry.getKey();
			Set<String> op = (Set<String>) entry.getValue();
			
			Set<String> oldOp = (Set<String>) associations.get(target);
			if (oldOp != null)
				op.addAll(oldOp);
			associations.put(target, (OperationSet) op);
			
//			Set<String> children = graph.getChildren(node);
//			for (String child : children) {
//				AssociationsSA(child, associations);
//			}
			
			
		}
		Set<String> parents = g.getParents(node);
		for (String parent : parents) {
			AssociationsSA(parent, associations);
		}
	}
	
	public static void AssociationsTA (String nodeName, List<String> pcs, List<AWP> DAL, List<AWP> NDAL, Map<String, Boolean> searchMap) throws PMException {
		Map<String, OperationSet> map = (Map<String, OperationSet>) g.getTargetAssociations(nodeName);
		Boolean detached;
		
		if (searchMap.get(nodeName) == null) {
			searchMap.put(nodeName, true);
		} else {
			return;
		}
		
		for (Map.Entry<String, OperationSet> entry : map.entrySet()) {
			String source = entry.getKey();
			Set<String> op = (Set<String>) entry.getValue();
			
			//FIXME update DAL table
			detached = false;
			List<String> pcList = getPcList(g.getNode(nodeName), g);
			for (String pc : pcs) {
				if (!pcList.contains(pc)) {
					DAL.add(new AWP(source, op, nodeName, pcList));
					detached = true;
					break;
				}
			}
			if (!detached)
				NDAL.add(new AWP(source, op, nodeName, pcs));
		}
		Set<String> parents = g.getParents(nodeName);
		for (String parent : parents) {
			AssociationsTA(parent, pcs, DAL, NDAL, searchMap);
		}
	}
	
	public static void getAllAscendant (String node, Set<String> ascendantList) throws PMException {
		Set<String> children = g.getChildren(node);
		ascendantList.addAll(children);
		for (String child : children) {
			getAllAscendant(child, ascendantList);
		}
	}
	
	public static boolean isInTPS (String node, Prohibition p) throws PMException {
		if (p.isIntersection()) {
			//if intersection in set true, we have to find an ascendant at least not in one container
			Map<String, Boolean> containers = p.getContainers();
			for (Map.Entry<String, Boolean> container : containers.entrySet()) {
				if ((!isContained(node, container.getKey()) && !container.getValue())
					|| (isContained(node, container.getKey()) && container.getValue())) {
					return false;
				}
			}
			
		} else {
			//if intersection is false, we have to find an ascendant not in all containers
			Map<String, Boolean> containers = p.getContainers();
			boolean find = false;
			for (Map.Entry<String, Boolean> container : containers.entrySet()) {
				find = find || (isContained(node, container.getKey()) && !container.getValue());
				find = find || (!isContained(node, container.getKey()) && container.getValue());
				if (find)
					break;
			}
			return find;
		}
		return true;
	}
	
	static void comb (List<AWP> dal, List<AWP> ndal, List<String> pcs) throws PMException {
		boolean change = false;
		
		for (AWP da : dal) {
//			DAL.remove(da);
			
			for (String ar : da.getOP()) {
				change = false;
				List<String> pcsT = new ArrayList<String>();
				pcsT.addAll(pcs);
				for (AWP nda : ndal) {
					if (isContained(da.getSA(), nda.getSA()) && nda.getOP().contains(ar)) {
						//add dal to ndal
						Set<String> ars = new HashSet<String>();
						ars.add(ar);
						ndal.add(new AWP(da.getSA(), ars, da.getTA(), da.getPC()));
						ars = da.getOP();
						ars.remove(ar);
						da.setOP(ars);
						change = true;
						break;
					}
				}
				if (change)
					continue;
			
				for (String pc : da.getPC()) {
					if (pcsT.contains(pc))
						pcsT.remove(pc);
				}
			
//				List<AWP> tmpL = new ArrayList<AWP>();
//				tmpL.add(da);
				for (AWP daT : dal) {
					if (isContained(da.getSA(), daT.getSA()) && daT.getOP().contains(ar)) {
						for (String pcT : daT.getPC()) {
							if (pcsT.contains(pcT))
								pcsT.remove(pcT);
						}
						if (pcsT.isEmpty()) {
							//add dal to ndal
							Set<String> ars = new HashSet<String>();
							ars.add(ar);
							ndal.add(new AWP(da.getSA(), ars, da.getTA(), da.getPC()));
							ars = da.getOP();
							ars.remove(ar);
							da.setOP(ars);
							change = true;
							break;
						}
					}
				}
			}
			if (da.getOP().size() == 0)
				dal.remove(da);
		}
	}
	
	//return true, if PC(B) is subset of PC(A)
	public static boolean subSet (Node nodeA, Node nodeB) throws PMException {
		List<String> pcListA = getPcList(nodeA, g);
		List<String> pcListB = getPcList(nodeB, g);
		
		for (String pc : pcListB) {
			if (!pcListA.toString().contains(pc))
				return false;
		}
		
		return true;
	}
	
	public static AccessRequest detectStatusChange (AssociationList associationsA, AssociationList associationsB, List<String> pcs, Node node, List<Prohibition> prohibitionList) throws PMException {
		List<AWP> dalA = associationsA.getDal();
		List<AWP> ndalA = associationsA.getNdal();
		List<AWP> dalB = associationsB.getDal();
		List<AWP> ndalB = associationsB.getNdal();
//		List<Prohibition> prohibitionList = new ArrayList<Prohibition>();
//		
//		if (this.prohibitions != null)
//			prohibitionList = this.prohibitions.getAll();
		
//		List<AWP> alA = null;
		List<AWP> alB = new ArrayList<AWP>();
//		alA.addAll(dalA);
//		alA.addAll(ndalA);
		alB.addAll(dalB);
		alB.addAll(ndalB);
		
		//dalA change?
		for (AWP a : dalA) {
			for (String ar : a.getOP()) {
				List<String> pcsT = new ArrayList<String>();
				pcsT.addAll(pcs);
				for (String pc : a.getPC()) {
					if (pcsT.contains(pc))
						pcsT.remove(pc);
				}
				for (AWP b : alB) {
					if (isContained(a.getSA(), b.getSA()) && b.getOP().contains(ar)) {
						for (String pc : b.getPC()) {
							if (pcsT.contains(pc))
								pcsT.remove(pc);
						}
						if (pcsT.isEmpty()) {
							//need to examine q:(a.getSA(), ar, node.getName())
							boolean prohibited = false;
							for (Prohibition p : prohibitionList) {
								if (isContained(a.getSA(), p.getSubject()) && p.getOperations().contains(ar)) {
									prohibited = true;
									Set<String> ascendantList = new HashSet<String>();
									//if no prohibition, all elements in ascendantList is available to build up q to kill the mutant
									getAllAscendant(node.getName(), ascendantList);
									for (String ascendant : ascendantList) {
										if (!isInTPS(ascendant, p))
											return new AccessRequest(a.getSA(), ar, ascendant);
									}
//									return null;
								}
							}
							//this privilege is (not permitted) (detached) before mutation, but is permitted after mutation
							if (prohibited == false)
								return (new AccessRequest(a.getSA(), ar, node.getName()));
						}
					}
				}
			}
		}
		
		//ndalA change?
		for (AWP a : ndalA) {
			//the target attribute is node itself, status of association would not change
			if (a.getTA().equals(node.getName()))
				continue;
			for (String ar : a.getOP()) {
				for (Prohibition p : prohibitionList) {
					if (isContained(a.getSA(), p.getSubject()) && p.getOperations().contains(ar) && isInTPS(a.getTA(), p)) {
						continue;
					}
				}
				List<String> pcsT = new ArrayList<String>();
				pcsT.addAll(pcs);
				for (String pc : a.getPC()) {
					if (pcsT.contains(pc))
						pcsT.remove(pc);
				}
				for (AWP b : alB) {
					if (isContained(a.getSA(), b.getSA()) && b.getOP().contains(ar)) {
						for (String pc : b.getPC()) {
							if (pcsT.contains(pc))
								pcsT.remove(pc);
						}
					}
				}
				if (pcsT.isEmpty()) {
					continue;
				} else {
					//need to examine q:(a.getSA(), ar, node.getName())
					//make sure q is not prohibited before mutation
					boolean prohibited = false;
					for (Prohibition p : prohibitionList) {
						if (isContained(a.getSA(), p.getSubject()) && p.getOperations().contains(ar)) {
							prohibited = true;
							Set<String> ascendantList = new HashSet<String>();
							//if no prohibition, all elements in ascendantList is available to build up q to kill the mutant
							getAllAscendant(node.getName(), ascendantList);
							for (String ascendant : ascendantList) {
								if (!isInTPS(ascendant, p))
									return new AccessRequest(a.getSA(), ar, ascendant);
							}
//							return null;
						}
					}
					//this privilege is permitted (detached) before mutation and is broken after mutation
					if (prohibited == false)
						return (new AccessRequest(a.getSA(), ar, node.getName()));
				}
			}
		}
		
		return null;
	}
}