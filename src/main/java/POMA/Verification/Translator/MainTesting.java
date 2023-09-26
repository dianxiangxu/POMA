package POMA.Verification.Translator;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import POMA.ConfigTuple;
import POMA.Utils;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class MainTesting {
	public static void main(String[] argv) throws Exception {
		String pathToFolder = "Policies/ProhibitionExample/ProhibitionsMedicalExampleUA/Test7/";
		//CVC4Translator_Prohibitions tp = new CVC4Translator_Prohibitions(pathToGraph);
	//	tp.initTranslation();
		
		
		Translator tp = new Translator();
		ConfigTuple ct = Utils.readAllFilesInFolderToConfig(new File(pathToFolder));
		tp.getAllAccessRights(ct.getGraph(), ct.getProhibitions());
		//System.out.println(ct.getProhibitions());
		
		CVC4Translator translator = new CVC4Translator(ct.getGraph());
		translator.initTranslation();
		String associationNoUA = translator.translateAssociationsNoUA();
		String fulltranslation = tp.getFullTranslation() + associationNoUA;
		List<Prohibition> prohibitionList = ct.getProhibitions().getAll();
		
		Prohibition p = prohibitionList.get(0);
		
		MainTesting mt = new MainTesting();
		
		if(p.getContainers().entrySet().size()==2) {
			mt.translateProhibitionTwoContainers(p, 1, fulltranslation);
		}


	}
	
	private void translateProhibitionTwoContainers(Prohibition p, int prohibitionsCount, String fullTranslation) {
		Iterator<Map.Entry<String,Boolean>> iterator =  p.getContainers().entrySet().iterator();
		Map.Entry<String, Boolean> container1 =  iterator.next();
		Map.Entry<String, Boolean> container2 =  iterator.next();
		StringBuilder sb = new StringBuilder();
		
		
		
		

		
		
		
		String subject = "Prohibition"+prohibitionsCount+"Subject" + p.getSubject();		
		String accessRight = p.getOperations().iterator().next();
		
		sb.append(fullTranslation);
		
	
		
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
		sb.append("(check-sat)"); //22
		sb.append(System.lineSeparator());

		sb.append("(get-value (FinalProhibitions))");


		System.out.println(sb.toString());

		
	}
	
	
	
	
}
