package POMA.Verification.TranslationWithSets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Utils;
import POMA.Exceptions.NoTypeProvidedException;
import POMA.Exceptions.NodeIsNotContainedByPolicyClassException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class CVC4Translator {

	private Set<String> tuples = new HashSet<String>();
	private Set<String> tuples_Prohibitions = new HashSet<String>();

	private List<String> UAs;
	private Graph ngacGraph;
	private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;
	private String pathToGraph;
	private Set<String> nodesWithoutContainment = new HashSet<String>();

	public CVC4Translator(String pathToGraph) {
		this.pathToGraph = pathToGraph;
	}

	public CVC4Translator(Graph ngacGraph) {
		this.ngacGraph = ngacGraph;

	}

	public void initTranslation() {

		try {
			if (ngacGraph == null) {
				readAnyGraph(pathToGraph);
				checkGraph();
			} else {
				checkGraph();
			}
		} catch (PMException | IOException e) {
			e.printStackTrace();
			System.out.println("The graph was not loaded correctly");
		} catch (NodeIsNotContainedByPolicyClassException e) {
			System.out.println(e);
			//System.exit(0);
			;
		}

		try {
			findTClosuresForAllPCs();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Could not find tclosure correctly");
		}

		try {
			findUAsInGraph();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Problems with finding UAs in the graph");
		}
		try {
			findAssociationsInGraph();
		} catch (PMException e) {
			e.printStackTrace();
			System.out.println("Problems with finding associations in the graph");
		}

	}

	public String getTranslatedGraph() throws PMException, NoTypeProvidedException, IOException {
		StringBuilder sb = new StringBuilder();
		CVC4PrepTranslation prepTranslation = new CVC4PrepTranslation();
		sb.append(prepTranslation.toString());

		sb.append(translateAssociations());
		// sb.append(AssociationRelation.setCardSetAssociationSetCVC4Assertion(listOfAssociations.size()));
		sb.append(System.lineSeparator());
		sb.append(translateContainment());
		sb.append(System.lineSeparator());
		sb.append(translateContainment_Prohibitions());
		sb.append(System.lineSeparator());
		sb.append(prepTranslation.assertTClosures());
		sb.append(System.lineSeparator());
		// System.out.println(getTestSuits());
		return sb.toString();
	}

	public static String getAllAccessRightsCheckBetweenUAandAT(String UA, String AT)
			throws PMException, NoTypeProvidedException {
//		if (UA.equals(AT)) {
//			throw new IllegalArgumentException("The subject and target cannot match");
//		}
		String toReturn = "";
		toReturn += "(declare-fun SingletonToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SingletonToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= SingletonToCheckUA (singleton (mkTuple  \"" + UA + "\" \"" + UA + "\"))))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= SingletonToCheckAT (singleton (mkTuple \"" + AT + "\" \"" + AT + "\"))))";
		toReturn += System.lineSeparator();
		toReturn += "(assert(not(= SingletonToCheckUA SingletonToCheckAT)))";
		toReturn += System.lineSeparator();
		toReturn += "(assert (= UA_U_Reachability (join SingletonToCheckUA Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AT_Reachability (join SingletonToCheckAT Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
		toReturn += System.lineSeparator();
		toReturn += "(check-sat)";
		toReturn += System.lineSeparator();
		toReturn += "(get-value (FinalJoin))";
		toReturn += System.lineSeparator();
		return toReturn;
	}

	public static String getAllAccessRightsCheckInSetOfUAandAT(List<String[]> input)
			throws PMException, NoTypeProvidedException {
		String toReturn = "";
		toReturn += "(declare-fun SingletonToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SingletonToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		for (String[] array : input) {
//			if (array[0].equals(array[1])) {
//				continue;
//			}
			
			if(array.length<2) {
			continue;	
			}
			toReturn += "(push 1)";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= SingletonToCheckUA (singleton (mkTuple  \"" + array[0] + "\" \"" + array[0]
					+ "\"))))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= SingletonToCheckAT (singleton (mkTuple \"" + array[1] + "\" \"" + array[1]
					+ "\"))))";
			toReturn += System.lineSeparator();
			toReturn += "(assert (= UA_U_Reachability (join SingletonToCheckUA Tclosure )))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= AT_Reachability (join SingletonToCheckAT Tclosure )))";
			toReturn += System.lineSeparator();

			toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
			toReturn += System.lineSeparator();

			toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
			toReturn += System.lineSeparator();

			toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
			toReturn += System.lineSeparator();
			toReturn += "(check-sat)";
			toReturn += System.lineSeparator();
			toReturn += "(get-model)";
			toReturn += System.lineSeparator();
			toReturn += "(get-value (FinalJoin))";
			toReturn += System.lineSeparator();
			toReturn += "(pop 1)";
			toReturn += System.lineSeparator();
		}
		return toReturn;
	}

	public static String getAllAccessRightsCheckInSetOfUAandATAllComb(List<String[]> input)
			throws PMException, NoTypeProvidedException {
		String toReturn = "";
		toReturn += "(declare-fun SetToCheckUA () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun SetToCheckAT () (Set (Tuple String String)))";
		toReturn += System.lineSeparator();
		StringBuilder sbUA = new StringBuilder();
		StringBuilder sbAT = new StringBuilder();
		List<String> uas = new ArrayList<String>();
		List<String> ats = new ArrayList<String>();
		if(input.size()==1) {
			sbUA.append(System.lineSeparator()+"(assert (= SetToCheckUA ");
			sbAT.append(System.lineSeparator()+"(assert (= SetToCheckAT ");
		}
		else {
		sbUA.append(System.lineSeparator()+"(assert (= SetToCheckUA (insert ");
		sbAT.append(System.lineSeparator()+"(assert (= SetToCheckAT (insert ");
		}
		Iterator<String[]> iterator = input.iterator();
		while (iterator.hasNext()) {		
			String[] array = iterator.next();
			String tupleUA = "(mkTuple \"" + array[0] + "\" \"" + array[0] + "\")";
			String tupleAT = "(mkTuple \"" + array[1] + "\" \"" + array[1] + "\")";
			if (!iterator.hasNext()) {
				if(input.size()==1) {
				sbUA.append("(singleton " + tupleUA + ")))");
				sbAT.append("(singleton " + tupleAT + ")))");
				}
				else {
					sbUA.append("(singleton " + tupleUA + "))))");
					sbAT.append("(singleton " + tupleAT + "))))");
				}
			} else {
					if (!uas.contains(array[0])) {
						sbUA.append(tupleUA + " " + System.lineSeparator());
						uas.add(array[0]);
					}
					if (!ats.contains(array[1])) {
						sbAT.append(tupleAT + " " + System.lineSeparator());
						ats.add(array[1]);
					}
				}

			
		}

		toReturn += sbUA.toString();
		toReturn += System.lineSeparator();
		toReturn += sbAT.toString();
		toReturn += System.lineSeparator()+System.lineSeparator();
		toReturn += "(assert (= UA_U_Reachability (join SetToCheckUA Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AT_Reachability (join SetToCheckAT Tclosure )))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun AssociationsForUA () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))";
		toReturn += System.lineSeparator();

		toReturn += "(declare-fun FinalJoin () (Set (Tuple String String String)))";
		toReturn += System.lineSeparator();

		toReturn += "(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))";
		toReturn += System.lineSeparator();
		
		return toReturn;
	}
	
	
	
	public static String getFinalCheck(Prohibitions prohibitions) throws Exception {
		String allAccessRights = "FinalJoin";
		String toReturn="";
		if(prohibitions!=null)
		{
			allAccessRights = "result";			
			toReturn += handleProhibitions(prohibitions.getAll().get(0));
		}
		toReturn += "(check-sat)";
		toReturn += System.lineSeparator();
	    toReturn += "(get-model)";
		toReturn += System.lineSeparator();
		toReturn += "(get-value ("+allAccessRights+"))";
		return toReturn;
	}
	public static String handleProhibitions(Prohibition p) {
		
		if(p.getContainers().size()==1) {
			System.out.println(p.getContainers().size());
			return translateProhibitionSingleContainer(p, 1);
		}
		if(p.getContainers().size()==2) {
			return translateProhibitionTwoContainers(p, 1);
		}
		return "";
	}
	
	private static String translateProhibitionSingleContainer(Prohibition p, int prohibitionsCount) {
		Map.Entry<String, Boolean> container =  p.getContainers().entrySet().iterator().next();
		StringBuilder sb = new StringBuilder();
		String containerSMT = "Prohibition"+prohibitionsCount+"Container" + container.getKey();
		String containerSMTComplement = "ComplementProhibition"+prohibitionsCount+"Container" + container.getKey();
		String TPSSMT = "TPS"+containerSMT;
		String subject = "Prohibition"+prohibitionsCount+"Subject" + p.getSubject();
		
		String accessRight = p.getOperations().iterator().next();
				
		//System.out.println("(assert (= "+containerSMT+" "+container.getValue()+"))");
	
		sb.append("(declare-fun UA_U_Reachability_PROHIBITION"+prohibitionsCount+" () (Set (Tuple String String)))");		   //1	
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AT_Container_Reachability_PROHIBITION"+prohibitionsCount+" () (Set (Tuple String String)))"); //2
		sb.append(System.lineSeparator());
		sb.append("(declare-fun "+containerSMT+" ()  (Set (Tuple String String)))");           //3
		sb.append(System.lineSeparator());
		sb.append("(declare-fun " + subject+" () (Set (Tuple String String)))");                                                //4
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+containerSMT+" (singleton(mkTuple \""+container.getKey()+"\" \""+container.getKey()+"\")))) "); //5
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+subject+" (singleton(mkTuple \""+p.getSubject()+"\" \""+p.getSubject()+"\")))) "); //6
		sb.append(System.lineSeparator());
		sb.append("(declare-fun "+subject+"_Reachability () (Set (Tuple String String)))"); //7
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+subject+"_Reachability (join "+subject+" (transpose Tclosure))))"); //8
		sb.append(System.lineSeparator());
		sb.append("(assert (= UA_U_Reachability_PROHIBITION"+prohibitionsCount+" (join "+subject+" Tclosure)))"); //9
		sb.append(System.lineSeparator());
		sb.append("(assert (= AT_Container_Reachability_PROHIBITION"+prohibitionsCount+" (join "+containerSMT+" (transpose Tclosure_Prohibition))))"); //10
		sb.append(System.lineSeparator());

		sb.append("(declare-fun SubjectAndAR"+prohibitionsCount+" () (Set (Tuple String String)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AffectedARs"+prohibitionsCount+" () (Set (Tuple String String String)))"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= SubjectAndAR"+prohibitionsCount+" (singleton (mkTuple  \""+p.getSubject()+"\" \""+accessRight+"\"))))"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= AffectedARs"+prohibitionsCount+" (intersection (join SubjectAndAR"+prohibitionsCount+" AssociationsForProhibitions) FinalJoin)))"); //11
		sb.append(System.lineSeparator());

		
		sb.append("(declare-fun "+containerSMTComplement +"() Bool)"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+containerSMTComplement+" "+container.getValue()+"))"); //12
		sb.append(System.lineSeparator());
		sb.append("(declare-fun "+TPSSMT+"() (Set (Tuple String String)))"); //13
		sb.append(System.lineSeparator());
		sb.append("(assert\r\n"
				+ "	(ite\r\n"
				+ "	(= "+containerSMTComplement +" true)\r\n"
				+ "	(= "+TPSSMT+" (setminus (setminus SetToCheckAT \r\n"
				+ "						(intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container_Reachability_PROHIBITION"+prohibitionsCount+")  SetToCheckAT )) "+containerSMT+")\r\n"
				+ "	)\r\n"
				+ "	(= "+TPSSMT+" (intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container_Reachability_PROHIBITION"+prohibitionsCount+" )\r\n"
				+ "						SetToCheckAT))	\r\n"
				+ "))"); //14
								
		sb.append(System.lineSeparator());
		sb.append("(declare-fun Prohibitions"+prohibitionsCount+"_AT_Reachability () (Set (Tuple String String)))"); //15
		sb.append(System.lineSeparator());
		sb.append("(assert (= Prohibitions"+prohibitionsCount+"_AT_Reachability (join "+TPSSMT+" Tclosure )))"); //16
		sb.append(System.lineSeparator());
		sb.append("(declare-fun ProhibitionsForUA () (Set (Tuple String String String)))"); //17
		sb.append(System.lineSeparator());
		sb.append("(declare-fun Prohibitions () (Set (Tuple String String String)))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= Prohibitions (singleton (mkTuple \""+p.getSubject()+"\" \""+accessRight+"\" \""+container.getKey()+"\"))))");
		sb.append(System.lineSeparator());
		sb.append("(assert (= ProhibitionsForUA (join "+subject+"_Reachability AffectedARs"+prohibitionsCount+") ))"); //18
		sb.append(System.lineSeparator());
		sb.append("(declare-fun FinalProhibitions () (Set (Tuple String String String)))"); //19
		sb.append(System.lineSeparator());
		sb.append("(assert (= FinalProhibitions (join ProhibitionsForUA (transpose Prohibitions"+prohibitionsCount+"_AT_Reachability)) ))"); //20
		sb.append(System.lineSeparator());
		sb.append("(declare-fun result () (Set (Tuple String String String)))"); //21
		sb.append(System.lineSeparator());
		sb.append("(assert (= result (setminus FinalJoin FinalProhibitions)))"); //22
		sb.append(System.lineSeparator());

		return sb.toString();
	}
	
	
	private static String translateProhibitionTwoContainers(Prohibition p, int prohibitionsCount) {
		Iterator<Map.Entry<String,Boolean>> iterator =  p.getContainers().entrySet().iterator();
		Map.Entry<String, Boolean> container1 =  iterator.next();
		Map.Entry<String, Boolean> container2 =  iterator.next();
		StringBuilder sb = new StringBuilder();
							
		String subject = "Prohibition"+prohibitionsCount+"Subject" + p.getSubject();		
		String accessRight = p.getOperations().iterator().next();
						
		String container1SMT = "Prohibition"+prohibitionsCount+"Container1" + container1.getKey();
		String container1SMTComplement = "ComplementProhibition"+prohibitionsCount+"Container1" + container1.getKey();
		String TPS1SMT = "TPS"+container1SMT;
		sb.append("(declare-fun UA_U_Reachability_PROHIBITION"+prohibitionsCount+" () (Set (Tuple String String)))");		   //1	
		sb.append(System.lineSeparator());				
		sb.append("(declare-fun "+container1SMT+" ()  (Set (Tuple String String)))");           //3
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+container1SMT+" (singleton(mkTuple \""+container1.getKey()+"\" \""+container1.getKey()+"\")))) "); //5
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+" () (Set (Tuple String String)))"); //2
		sb.append(System.lineSeparator());
		sb.append("(assert (= AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+" (join "+container1SMT+" (transpose Tclosure_Prohibition))))"); //10
		sb.append(System.lineSeparator());
		
		
		
		String containerFinal = "Prohibition"+prohibitionsCount+"ContainerFinal";
		String TPSFinal = "TPS"+containerFinal;
		String container2SMT = "Prohibition"+prohibitionsCount+"Container2" + container2.getKey();
		String container2SMTComplement = "ComplementProhibition"+prohibitionsCount+"Container2" + container2.getKey();
		String TPS2SMT = "TPS"+container2SMT;
		sb.append("(declare-fun "+container2SMT+" ()  (Set (Tuple String String)))");           //3
		sb.append(System.lineSeparator());		
		sb.append("(assert (= "+container2SMT+" (singleton(mkTuple \""+container2.getKey()+"\" \""+container2.getKey()+"\")))) "); //5
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+" () (Set (Tuple String String)))"); //2
		sb.append(System.lineSeparator());
		sb.append("(assert (= AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+" (join "+container2SMT+" (transpose Tclosure_Prohibition))))"); //10
		sb.append(System.lineSeparator());				
		sb.append("(declare-fun "+TPSFinal+" ()  (Set (Tuple String String)))");           //3
		sb.append(System.lineSeparator());
		
										
		sb.append("(declare-fun " + subject+" () (Set (Tuple String String)))");                                                //4
		sb.append(System.lineSeparator());		
		sb.append("(assert (= "+subject+" (singleton(mkTuple \""+p.getSubject()+"\" \""+p.getSubject()+"\")))) "); //6
		sb.append(System.lineSeparator());
		sb.append("(declare-fun "+subject+"_Reachability () (Set (Tuple String String)))"); //7
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+subject+"_Reachability (join "+subject+" (transpose Tclosure))))"); //8
		sb.append(System.lineSeparator());		
		sb.append("(assert (= UA_U_Reachability_PROHIBITION"+prohibitionsCount+" (join "+subject+" Tclosure)))"); //9
		sb.append(System.lineSeparator());
		
				
		sb.append("(declare-fun Prohibition"+prohibitionsCount+"Intersection () Bool)"); //10
		sb.append(System.lineSeparator());
		sb.append("(assert (= Prohibition"+prohibitionsCount+"Intersection "+p.isIntersection()+"))"); //10
		sb.append(System.lineSeparator());

		
		
		sb.append("(declare-fun SubjectAndAR"+prohibitionsCount+" () (Set (Tuple String String)))");
		sb.append(System.lineSeparator());
		sb.append("(declare-fun AffectedARs"+prohibitionsCount+" () (Set (Tuple String String String)))"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= SubjectAndAR"+prohibitionsCount+" (singleton (mkTuple  \""+p.getSubject()+"\" \""+accessRight+"\"))))"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= AffectedARs"+prohibitionsCount+" (intersection (join SubjectAndAR"+prohibitionsCount+" AssociationsForProhibitions) FinalJoin)))"); //11
		sb.append(System.lineSeparator());

		
		sb.append("(declare-fun "+container1SMTComplement +"() Bool)"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+container1SMTComplement+" "+container1.getValue()+"))"); //12
		sb.append(System.lineSeparator());
		sb.append("(declare-fun "+TPS1SMT+"() (Set (Tuple String String)))"); //13
		sb.append(System.lineSeparator());		
		sb.append("(assert\r\n"
				+ "	(ite\r\n"
				+ "	(= "+container1SMTComplement +" true)\r\n"
				+ "	(= "+TPS1SMT+" (setminus (setminus SetToCheckAT \r\n"
				+ "						(intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+")  SetToCheckAT )) "+container1SMT+")\r\n"
				+ "	)\r\n"
				+ "	(= "+TPS1SMT+" (intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container1_Reachability_PROHIBITION"+prohibitionsCount+" )\r\n"
				+ "						SetToCheckAT))	\r\n"
				+ "))"); //14		
		sb.append(System.lineSeparator());
		
		
		sb.append("(declare-fun "+container2SMTComplement +"() Bool)"); //11
		sb.append(System.lineSeparator());
		sb.append("(assert (= "+container2SMTComplement+" "+container2.getValue()+"))"); //12
		sb.append(System.lineSeparator());		
		sb.append("(declare-fun "+TPS2SMT+"() (Set (Tuple String String)))"); //13
		sb.append(System.lineSeparator());
		sb.append("(assert\r\n"
				+ "	(ite\r\n"
				+ "	(= "+container2SMTComplement +" true)\r\n"
				+ "	(= "+TPS2SMT+" (setminus (setminus SetToCheckAT \r\n"
				+ "						(intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+")  SetToCheckAT )) "+container2SMT+")\r\n"
				+ "	)\r\n"
				+ "	(= "+TPS2SMT+" (intersection \r\n"
				+ "						(join \r\n"
				+ "						(transpose AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+")  \r\n"
				+ "						AT_Container2_Reachability_PROHIBITION"+prohibitionsCount+" )\r\n"
				+ "						SetToCheckAT))	\r\n"
				+ "))"); //14
		sb.append(System.lineSeparator());
		
		sb.append("(assert \r\n"
				+ "	(ite\r\n"
				+ "	(= Prohibition"+prohibitionsCount+"Intersection " + "true)\r\n"
				+ "	(= "+TPSFinal+" (intersection "+TPS1SMT+" "+TPS2SMT+")\r\n"
				+ "	)\r\n"
				+ "	(= "+TPSFinal+" (union "+TPS1SMT+" "+TPS2SMT+"))\r\n"
				+ "	\r\n"
				+ "))");
		
		sb.append(System.lineSeparator());
		sb.append("(declare-fun Prohibitions"+prohibitionsCount+"_AT_Reachability () (Set (Tuple String String)))"); //15
		sb.append(System.lineSeparator());
		sb.append("(assert (= Prohibitions"+prohibitionsCount+"_AT_Reachability (join "+TPSFinal+" Tclosure )))"); //16
		sb.append(System.lineSeparator());
		sb.append("(declare-fun ProhibitionsForUA () (Set (Tuple String String String)))"); //17
		sb.append(System.lineSeparator());
		sb.append("(assert (= ProhibitionsForUA (join "+subject+"_Reachability AffectedARs"+prohibitionsCount+") ))"); //18
		sb.append(System.lineSeparator());
		sb.append("(declare-fun FinalProhibitions () (Set (Tuple String String String)))"); //19
		sb.append(System.lineSeparator());
		sb.append("(assert (= FinalProhibitions (join ProhibitionsForUA (transpose Prohibitions"+prohibitionsCount+"_AT_Reachability)) ))"); //20
		sb.append(System.lineSeparator());
		sb.append("(declare-fun result () (Set (Tuple String String String)))"); //21
		sb.append(System.lineSeparator());
		sb.append("(assert (= result (setminus FinalJoin FinalProhibitions)))"); //22				
		sb.append(System.lineSeparator());		
		return sb.toString();
	}
	
	
	private String translateContainment() {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert (= Containment (insert ");
		for (Iterator<String> iterator = tuples.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))"+System.lineSeparator());
			} else {
				sb.append(tuple + " "+System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String translateContainment_Prohibitions() {
		StringBuilder sb = new StringBuilder();
		sb.append("(assert (= Containment_Prohibition (insert ");
		for (Iterator<String> iterator = tuples_Prohibitions.iterator(); iterator.hasNext();) {
			String tuple = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(singleton " + tuple + "))))"+System.lineSeparator());
			} else {
				sb.append(tuple + " "+System.lineSeparator());
			}
		}
		return sb.toString();
	}
	
	private String translateAssociations() {
		StringBuilder sb = new StringBuilder();
		if(listOfAssociations.size()!=1) {
			sb.append(System.lineSeparator()+"(assert (= Associations (insert");
		}
		else {
			sb.append(System.lineSeparator()+"(assert (= Associations ( ");
			for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
				AssociationRelation tuple = iterator.next();
				String ua = tuple.getUA();
				String at = tuple.getAT();
				Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
				while (iteratorAR.hasNext()) {
					String assoc = "(mkTuple \"" + ua + "\" \"" + iteratorAR.next() + "\" \"" + at + "\")"+System.lineSeparator();
					if (!iterator.hasNext() && !iteratorAR.hasNext()) {
						sb.append("singleton " + assoc + ")))"+System.lineSeparator());
					} else {
						sb.append(assoc + " ");
					}
				}			


			}
			return sb.toString();
		}
		for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
			AssociationRelation tuple = iterator.next();
		//	System.out.println("!!!!!!!!!!!!!!!!!" + tuple);
			String ua = tuple.getUA();
			String at = tuple.getAT();
			Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
			while (iteratorAR.hasNext()) {
				String assoc = "(mkTuple \"" + ua + "\" \"" + iteratorAR.next() + "\" \"" + at + "\")";
				if (!iterator.hasNext() && !iteratorAR.hasNext()) {
					sb.append("(singleton " + assoc + "))))"+System.lineSeparator());
				} else {
					sb.append(assoc + " "+System.lineSeparator());
				}
			}

		}
		return sb.toString();
	}
	public String translateAssociationsNoUA() {
		StringBuilder sb = new StringBuilder();
		sb.append("(declare-fun AssociationsForProhibitions () (Set (Tuple String String String)))");
		sb.append(System.lineSeparator());
		if(listOfAssociations.size()!=1) {
			sb.append(System.lineSeparator()+"(assert (= AssociationsForProhibitions (insert");
		}
		else {
			sb.append(System.lineSeparator()+"(assert (= AssociationsForProhibitions ( ");
			for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
				AssociationRelation tuple = iterator.next();
				String ua = tuple.getUA();
				String at = tuple.getAT();
				Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
				while (iteratorAR.hasNext()) {
					String ar = iteratorAR.next();
					String assoc = "(mkTuple \"" + ar + "\" \"" + ar + "\" \"" + at + "\")"+System.lineSeparator();
					if (!iterator.hasNext() && !iteratorAR.hasNext()) {
						sb.append("singleton " + assoc + ")))"+System.lineSeparator());
					} else {
						sb.append(assoc + " ");
					}
				}			


			}
			return sb.toString();
		}
		for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
			AssociationRelation tuple = iterator.next();
		//	System.out.println("!!!!!!!!!!!!!!!!!" + tuple);
			String ua = tuple.getUA();
			String at = tuple.getAT();
			Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
			while (iteratorAR.hasNext()) {
				String ar = iteratorAR.next();

				String assoc = "(mkTuple \"" + ar + "\" \"" + ar + "\" \"" + at + "\")";
				if (!iterator.hasNext() && !iteratorAR.hasNext()) {
					sb.append("(singleton " + assoc + "))))"+System.lineSeparator());
				} else {
					sb.append(assoc + " "+System.lineSeparator());
				}
			}

		}
		return sb.toString();
	}
	private void findAssociationsInGraph() throws PMException {
		for (String UA : UAs) {
			Map<String, OperationSet> association = ngacGraph.getSourceAssociations(UA);
			if (!association.isEmpty()) {
				for (Map.Entry<String, OperationSet> entry : association.entrySet()) {
					String AT = entry.getKey();
					OperationSet os = entry.getValue();
					AssociationRelation associationRelation = new AssociationRelation(UA, os, AT);
					listOfAssociations.add(associationRelation);
				}
			}

		}
	}

	
	
	private void findUAsInGraph() throws PMException {
		UAs = new ArrayList<String>();
		for (String policyClass : ngacGraph.getPolicyClasses()) {
			UAs.addAll(findUAsInPC(policyClass));
		}
	}

	private List<String> findUAsInPC(String PC) throws PMException {
		List<String> UAs = new ArrayList<String>();
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if (node.getType().toString().equals("UA")) {
				UAs.add(node.getName());
			}
		};
		dfs.traverse(ngacGraph.getNode(PC), (c, p) -> {
		}, visitor, Direction.CHILDREN);

		return UAs;
	}

	private void findTClosuresForAllPCs() throws PMException {
		for (String policyClass : ngacGraph.getPolicyClasses()) {
			findTClosureForGraph(policyClass);
			findTClosureForProhibitions(policyClass);
		}
	}

	public void readAnyGraph(String path) throws PMException, IOException {
		File file_eligibility_policy = new File(path);
		String eligibility_policy = new String(
				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));

		ngacGraph = new MemGraph();

		GraphSerializer.fromJson(ngacGraph, eligibility_policy);
	}

	private void findTClosureForGraph(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
					|| node.getType().toString().equals("O") || node.getType().toString().equals("OA"))
					) {
				if (node.getType().toString().equals("UA") || node.getType().toString().equals("OA")) {
					tuples.add(new AssignmentRelation(node.getName(), node.getName()).toString());
				}
				for (String parent : ngacGraph.getParents(node.getName())) {
					tuples.add(new AssignmentRelation(node.getName(), parent).toString());
				}
			}
		};
		dfs.traverse(ngacGraph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}

	private void findTClosureForProhibitions(String policyClass) throws PMException {
		DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
		Visitor visitor = node -> {
			if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
					|| node.getType().toString().equals("O") || node.getType().toString().equals("OA"))
					) {
				for (String parent : ngacGraph.getParents(node.getName())) {
					tuples_Prohibitions.add(new AssignmentRelation(node.getName(), parent).toString());
				}
			}
		};
		dfs.traverse(ngacGraph.getNode(policyClass), (c, p) -> {
		}, visitor, Direction.CHILDREN);
	}
	
	private void isContainedByAnyPC(String child) throws PMException {
		//System.out.println("CHILD: " + child);
		for (String pc : ngacGraph.getPolicyClasses()) {
			DepthFirstSearcher dfs = new DepthFirstSearcher(ngacGraph);
			Set<String> nodes = new HashSet<>();
			Visitor visitor = node -> {
				if (node.getName().equals(pc)) {
					nodes.add(node.getName());
				}
			};
			dfs.traverse(ngacGraph.getNode(child), (c, p) -> {
			}, visitor, Direction.PARENTS);
			if (!nodes.contains(pc)) {
				nodesWithoutContainment.add(child);
			} else if (nodes.contains(pc) && nodesWithoutContainment.contains(child)) {
				nodesWithoutContainment.remove(child);
				return;
			} else if (nodes.contains(pc)) {
				return;
			}
		}
	}

	private void checkGraph() throws PMException, NodeIsNotContainedByPolicyClassException {
		//System.out.println(GraphSerializer.toJson(ngacGraph));
		for (Node node : ngacGraph.getNodes()) {
			if (!node.getType().toString().equals("PC")) {
				isContainedByAnyPC(node.getName());
			}
		}
		if (!nodesWithoutContainment.isEmpty()) {
			throw new NodeIsNotContainedByPolicyClassException(
					"Every node has to be contained by a policy class. The following nodes are not contained by any policy class: "
							+ nodesWithoutContainment.toString());
		}
	}
}
