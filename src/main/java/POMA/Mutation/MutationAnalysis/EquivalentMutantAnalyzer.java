package POMA.Mutation.MutationAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import POMA.Mutation.EquivalentMutantAnalyzer.AAG;
import POMA.Mutation.EquivalentMutantAnalyzer.RAG;
import POMA.Mutation.EquivalentMutantAnalyzer.RAD;
import POMA.Mutation.EquivalentMutantAnalyzer.CAD;
import POMA.Mutation.EquivalentMutantAnalyzer.CAA;
import POMA.Mutation.EquivalentMutantAnalyzer.CUAA;
import POMA.Mutation.EquivalentMutantAnalyzer.COAA;
import POMA.Mutation.EquivalentMutantAnalyzer.RARA;
import POMA.Mutation.EquivalentMutantAnalyzer.AARA;
import POMA.Mutation.EquivalentMutantAnalyzer.RAC;
import POMA.Mutation.EquivalentMutantAnalyzer.AAC;
import POMA.Mutation.EquivalentMutantAnalyzer.RARAA;

import POMA.Mutation.EquivalentMutantAnalyzer.CSS;
import POMA.Mutation.EquivalentMutantAnalyzer.AOAR;
import POMA.Mutation.EquivalentMutantAnalyzer.COAR;
import POMA.Mutation.EquivalentMutantAnalyzer.ROAR;
import POMA.Mutation.EquivalentMutantAnalyzer.RIS;
import POMA.Mutation.EquivalentMutantAnalyzer.AOC;
import POMA.Mutation.EquivalentMutantAnalyzer.COC;
import POMA.Mutation.EquivalentMutantAnalyzer.ROCT;
import POMA.Mutation.EquivalentMutantAnalyzer.RCT;
import POMA.Mutation.EquivalentMutantAnalyzer.ROP;

import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.ROB;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.CEU;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.REU;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.CEO;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.AEO;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.REO;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.CEPE;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.REPE;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.ROC;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.NCD;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.ROF;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.NOF;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.ROA;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.COA;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.ICA;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.IGA;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.IAA;
import POMA.Mutation.EquivalentMutantAnalyzer.Obligation.INA;

import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.ROB_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CEU_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.REU_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CEO_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.AEO_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CET_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.RET_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.ROA_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CDA_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CASA_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.RDA_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CTG_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CSG_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.CARG_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.AARG_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.RARG_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.ROC_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.NCD_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.ROF_Solver;
import POMA.Mutation.EquivalentMutantAnalyzer.ObligationConstraintSolver.NOF_Solver;

import POMA.Mutation.EquivalentMutantAnalyzer.AccessRequest;
import POMA.Utils;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Mutation.MutationOperators.*;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.*;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class EquivalentMutantAnalyzer {
	List<String> testMethods;
	int totalNumberOfMutants = 0;
	int totalNumberOfKilledMutants = 0;
	int totalNumberOfMutantsForTest = 0;
	int totalNumberOfKilledMutantsForTest = 0;
	List<String[]> data = new ArrayList<String[]>();
	String[] row;
	String CSVFilePath = "CSV/OverallMutationResults.csv";
	static int colCount = 25;
	Graph graph;
	public Prohibitions prohibitions;
	static List<AccessRequest> arList;

	
	private void createHeaderForCSV(List<String> mutantNames) {
		String[] header = new String[colCount];
		header[0]= "TestMethod";
		for(int i=0;i< mutantNames.size(); i++) {
			header[i+1] =  mutantNames.get(i);
		}
		header[mutantNames.size()+1] = "totalMutationScore";
		data.add(header);
	}
	
	public static void main(String[] args) throws Exception {
		EquivalentMutantAnalyzer mc = new EquivalentMutantAnalyzer();
		List<String> mutantNames = new ArrayList<String>();
		String initialGraphConfig = "";
		String initialProhibitionConfig = "";
		String obligationPath = "";
		arList = new ArrayList<AccessRequest>();
		
//        initialGraphConfig = "Policies/GPMS/Graph.json";
//		obligationPath = "Policies/GPMS/Obligations.yml";
		
//		initialGraphConfig = "Policies/LawUseCase/Graph.json";
//		initialProhibitionConfig = "Policies/LawUseCase/prohibitions.json";
//		obligationPath = "Policies/LawUseCase/Obligations.yml";
		
//        initialGraphConfig = "Policies/BankPolicy/Complex/bank_policy_config.json";
		
//        initialGraphConfig = "Policies/ProhibitionExample/ProhibitionsMedicalExampleOA/graph.json";
//        initialProhibitionConfig = "Policies/ProhibitionExample/ProhibitionsMedicalExampleOA/prohibitionsx1.json";
		
		//BMC: LawFirm
		//FIXME: prohibition not working yet
//		initialGraphConfig = "Policies/SolverVerification/LawFirm/Graph.json";
//		obligationPath = "Policies/SolverVerification/LawFirm/Obligations.yml";
//		obligationPath = "Policies/SolverVerification/LawFirm/ObligationsWithCondition.yml";
////      initialProhibitionConfig = "Policies/ForBMC/LawFirmSimplified/prohibitions.json";
		
		//BMC: GPMS
//		initialGraphConfig = "Policies/SolverVerification/GPMS/Graph.json";
//		obligationPath = "Policies/SolverVerification/GPMS/Obligations.yml";
		
//		//Coverage-Based test generation: GPMS
//		initialGraphConfig = "Policies/GPMS/Graph.json";
//		obligationPath = "Policies/GPMS/Obligations.yml";
//		
//		//Coverage-Based test generation: Law Firm
//		initialGraphConfig = "Policies/LawUseCase/Graph.json";
//		obligationPath = "Policies/LawUseCase/Obligations.yml";
//		initialProhibitionConfig = "Policies/LawUseCase/prohibitions.json";
		
		//Coverage-Based test generation: GPMS
		initialGraphConfig = "Policies/BankPolicy/OnePolicyClass/Graph.json";
		obligationPath = "Policies/BankPolicy/OnePolicyClass/Obligations.yml";

		
		File folder = new File(initialGraphConfig).getParentFile();
//		mutantNames.add("AAG");
//		mutantNames.add("RAG");
//		mutantNames.add("RAD");
//		mutantNames.add("CAD");
//		mutantNames.add("CAA");
//		mutantNames.add("CUAA");
//		mutantNames.add("COAA");
//		mutantNames.add("RARA");
//		mutantNames.add("AARA");
//		mutantNames.add("RAC");
//		mutantNames.add("AAC");
//		mutantNames.add("RARAA");
//		
//		if (!initialProhibitionConfig.equals("")) {
//			mutantNames.add("CSS");
//			mutantNames.add("AOAR");
//			mutantNames.add("COAR");
//			mutantNames.add("ROAR");
//			mutantNames.add("RIS");
//			mutantNames.add("AOC");
//			mutantNames.add("COC");
//			mutantNames.add("ROCT");
//			mutantNames.add("RCT");
//			mutantNames.add("ROP");
//		}
		
		if (!obligationPath.equals("")) {
//			mutantNames.add("ROB");
//			mutantNames.add("CEU");
//			mutantNames.add("REU");
//			mutantNames.add("CEO");
//			mutantNames.add("AEO");
//			mutantNames.add("REO");
//			mutantNames.add("CEPE");
//			mutantNames.add("REPE");
//			mutantNames.add("ROC");
//			mutantNames.add("NCD");
//			mutantNames.add("ROF");
//			mutantNames.add("NOF");
//			mutantNames.add("ROA");
//			mutantNames.add("COA");
//			mutantNames.add("ICA");
//			mutantNames.add("IGA");
//			mutantNames.add("IAA");
//			mutantNames.add("INA");
			
			//FIXME: reserved for solver
//			mutantNames.add("ROB_Solver");
//			mutantNames.add("CEU_Solver");//GPMS:NO
//			mutantNames.add("REU_Solver");//GPMS:NO
//			mutantNames.add("CEO_Solver");
//			mutantNames.add("AEO_Solver");
//			mutantNames.add("REO_Solver");//Not implemented
//			mutantNames.add("CET_Solver");
//			mutantNames.add("RET_Solver");
//			mutantNames.add("ROC_Solver");
//			mutantNames.add("NCD_Solver");
//			mutantNames.add("ROF_Solver");
			mutantNames.add("NOF_Solver");
//			mutantNames.add("ROA_Solver");
//			mutantNames.add("COA_Solver");//Not implemented
//			mutantNames.add("CDC_Solver");//Not implemented
//			mutantNames.add("CTG_Solver");
//			mutantNames.add("CSG_Solver");
//			mutantNames.add("CARG_Solver");
//			mutantNames.add("AARG_Solver");
//			mutantNames.add("RARG_Solver");			
//			mutantNames.add("CDA_Solver");
//			mutantNames.add("CASA_Solver");//GPMS:NO
//			mutantNames.add("RDA_Solver");//BUG: when running GPMS
//			mutantNames.add("CTD_Solver");//Not implemented
		}
		
		mc.graph = Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
//		initialProhibitionConfig = "";
		if (!initialProhibitionConfig.equals("")) {
		    mc.prohibitions = Utils.readProhibitions(initialProhibitionConfig);
		} else {
			mc.prohibitions = new MemProhibitions();
		}
		mc.createMutants( mutantNames,  mc.graph, mc.prohibitions, obligationPath, folder);
	}

	public EquivalentMutantAnalyzer() {
		createTestMethods();
	}

	public void createMutants(List<String> mutantNames, Graph graph, Prohibitions prohibitions, String obligationPath, File folder) throws Exception {
		
		//createHeaderForCSV(mutantNames);
		List<String[]> outputList = new ArrayList<String[]>();
		
		
		String[] row1 = new String[]{"TestMethod", "Pairwise", "AllCombination"};
		data.add(row1);
		String[] row2 = new String[colCount];
		String[] row3 = new String[colCount];
		String[] row4 = new String[colCount];
		String[] row5 = new String[colCount];
		String[] row6 = new String[colCount];
		String[] row7 = new String[colCount];
		String[] row8 = new String[colCount];
		String[] row9 = new String[colCount];
		String[] row10 = new String[colCount];
		String[] row11 = new String[colCount];
		String[] row12 = new String[colCount];
		String[] row13 = new String[colCount];
		String[] row14 = new String[colCount];
		String[] row15 = new String[colCount];
		String[] row16 = new String[colCount];
		String[] row17 = new String[colCount];
		String[] row18 = new String[colCount];
		String[] row19 = new String[colCount];
		String[] row20 = new String[colCount];
		String[] row21 = new String[colCount];
		String[] row22 = new String[colCount];
		String[] row23 = new String[colCount];
		String[] row24 = new String[colCount];
		String[] row25 = new String[colCount];
		String[] row26 = new String[colCount];
		String[] row27 = new String[colCount];
		String[] row28 = new String[colCount];
		String[] row29 = new String[colCount];
		String[] row30 = new String[colCount];
		String[] row31 = new String[colCount];
		String[] row32 = new String[colCount];
		String[] row33 = new String[colCount];
		String[] row34 = new String[colCount];
		String[] row35 = new String[colCount];
		String[] row36 = new String[colCount];
		String[] row37 = new String[colCount];
		String[] row38 = new String[colCount];
		String[] row39 = new String[colCount];
		String[] row40 = new String[colCount];
		String[] row41 = new String[colCount];
		String[] row42 = new String[colCount];
		String[] row43 = new String[colCount];
		String[] row44 = new String[colCount];
		String[] row45 = new String[colCount];
		String[] row46 = new String[colCount];
		String[] row47 = new String[colCount];
		String[] row48 = new String[colCount];
		String[] row49 = new String[colCount];
		String[] row50 = new String[colCount];
		row50[0] = "totalMutationScore";

		long startTime = System.currentTimeMillis();
		int j = 0;

		for (String testMethod : testMethods) {
			j++;
			String[] row = new String[colCount];
			totalNumberOfMutantsForTest = 0;
			totalNumberOfKilledMutantsForTest = 0;
			row[0] = testMethod;
			


			for (int i = 0; i < mutantNames.size(); i++) {
				System.out.println(mutantNames.toString());
				System.out.println("Total size of tests: " + arList.size());
				double result = 0;
				if (mutantNames.get(i).equals("AAG")) {
					row2[0] = "AAG";
					row2[j] = Double.toString(testAAG(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RAG")) {
					row3[0] = "RAG";
					row3[j] = Double.toString(testRAG(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RAD")) {
					row4[0] = "RAD";
					row4[j] = Double.toString(testRAD(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CAD")) {
					row5[0] = "CAD";
					row5[j] = Double.toString(testCAD(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CAA")) {
					row6[0] = "CAA";
					row6[j] = Double.toString(testCAA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CUAA")) {
					row7[0] = "CUAA";
					row7[j] = Double.toString(testCUAA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("COAA")) {
					row8[0] = "COAA";
					row8[j] = Double.toString(testCOAA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RARA")) {
					row9[0] = "RARA";
					row9[j] = Double.toString(testRARA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AARA")) {
					row10[0] = "AARA";
					row10[j] = Double.toString(testAARA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RAC")) {
					row11[0] = "RAC";
					row11[j] = Double.toString(testRAC(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AAC")) {
					row12[0] = "AAC";
					row12[j] = Double.toString(testAAC(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RARAA")) {
					row13[0] = "RARAA";
					row13[j] = Double.toString(testRARAA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CSS")) {
					row14[0] = "CSS";
					row14[j] = Double.toString(testCSS(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AOAR")) {
					row15[0] = "AOAR";
					row15[j] = Double.toString(testAOAR(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("COAR")) {
					row16[0] = "COAR";
					row16[j] = Double.toString(testCOAR(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROAR")) {
					row17[0] = "ROAR";
					row17[j] = Double.toString(testROAR(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RIS")) {
					row18[0] = "RIS";
					row18[j] = Double.toString(testRIS(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AOC")) {
					row19[0] = "AOC";
					row19[j] = Double.toString(testAOC(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("COC")) {
					row20[0] = "COC";
					row20[j] = Double.toString(testCOC(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROCT")) {
					row21[0] = "ROCT";
					row21[j] = Double.toString(testROCT(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RCT")) {
					row22[0] = "RCT";
					row22[j] = Double.toString(testRCT(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROP")) {
					row23[0] = "ROP";
					row23[j] = Double.toString(testROP(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROB")) {
					row24[0] = "ROB";
					row24[j] = Double.toString(testROB(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CEU")) {
					row25[0] = "CEU";
					row25[j] = Double.toString(testCEU(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("REU")) {
					row26[0] = "REU";
					row26[j] = Double.toString(testREU(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CEO")) {
					row27[0] = "CEO";
					row27[j] = Double.toString(testCEO(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AEO")) {
					row28[0] = "AEO";
					row28[j] = Double.toString(testAEO(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("REO")) {
					row29[0] = "REO";
					row29[j] = Double.toString(testREO(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CEPE")) {
					row30[0] = "CEPE";
					row30[j] = Double.toString(testCEPE(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("REPE")) {
					row31[0] = "REPE";
					row31[j] = Double.toString(testREPE(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROC")) {
					row32[0] = "ROC";
					row32[j] = Double.toString(testROC(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("NCD")) {
					row33[0] = "NCD";
					row33[j] = Double.toString(testNCD(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROF")) {
					row34[0] = "ROF";
					row34[j] = Double.toString(testROF(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("NOF")) {
					row35[0] = "NOF";
					row35[j] = Double.toString(testNOF(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROA")) {
					row36[0] = "ROA";
					row36[j] = Double.toString(testROA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("COA")) {
					row37[0] = "COA";
					row37[j] = Double.toString(testCOA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ICA")) {
					row38[0] = "ICA";
					row38[j] = Double.toString(testICA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("IGA")) {
					row39[0] = "IGA";
					row39[j] = Double.toString(testIGA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("IAA")) {
					row40[0] = "IAA";
					row40[j] = Double.toString(testIAA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("INA")) {
					row41[0] = "INA";
					row41[j] = Double.toString(testINA(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROB_Solver")) {
					row24[0] = "ROB_Solver";
					row24[j] = Double.toString(testROB_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CEU_Solver")) {
					row25[0] = "CEU_Solver";
					row25[j] = Double.toString(testCEU_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("REU_Solver")) {
					row26[0] = "REU_Solver";
					row26[j] = Double.toString(testREU_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CEO_Solver")) {
					row27[0] = "CEO_Solver";
					row27[j] = Double.toString(testCEO_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AEO_Solver")) {
					row28[0] = "AEO_Solver";
					row28[j] = Double.toString(testAEO_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CET_Solver")) {
					row30[0] = "CET_Solver";
					row30[j] = Double.toString(testCET_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RET_Solver")) {
					row31[0] = "RET_Solver";
					row31[j] = Double.toString(testRET_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROA_Solver")) {
					row36[0] = "ROA_Solver";
					row36[j] = Double.toString(testROA_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CTG_Solver")) {
					row39[0] = "CTG_Solver";
					row39[j] = Double.toString(testCTG_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CDA_Solver")) {
					row40[0] = "CDA_Solver";
					row40[j] = Double.toString(testCDA_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CASA_Solver")) {
					row41[0] = "CASA_Solver";
					row41[j] = Double.toString(testCASA_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RDA_Solver")) {
					row42[0] = "RDA_Solver";
					row42[j] = Double.toString(testRDA_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CSG_Solver")) {
					row43[0] = "CSG_Solver";
					row43[j] = Double.toString(testCSG_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("CARG_Solver")) {
					row44[0] = "CARG_Solver";
					row44[j] = Double.toString(testCARG_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("AARG_Solver")) {
					row45[0] = "AARG_Solver";
					row45[j] = Double.toString(testAARG_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("RARG_Solver")) {
					row46[0] = "RARG_Solver";
					row46[j] = Double.toString(testRARG_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROC_Solver")) {
					row47[0] = "ROC_Solver";
					row47[j] = Double.toString(testROC_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("NCD_Solver")) {
					row48[0] = "NCD_Solver";
					row48[j] = Double.toString(testNCD_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("ROF_Solver")) {
					row49[0] = "ROF_Solver";
					row49[j] = Double.toString(testROF_Solver(testMethod, graph, prohibitions, obligationPath));
				} else if (mutantNames.get(i).equals("NOF_Solver")) {
					row50[0] = "NOF_Solver";
					row50[j] = Double.toString(testNOF_Solver(testMethod, graph, prohibitions, obligationPath));
				}
			}
			row50[j] = Double.toString(
					(double) totalNumberOfKilledMutantsForTest / (double) totalNumberOfMutantsForTest * 100);
		
		}
		data.add(row2);
		data.add(row3);
		data.add(row4);
		data.add(row5);
		data.add(row6);
		data.add(row7);
		data.add(row8);
		data.add(row9);
		data.add(row10);
		data.add(row11);
		data.add(row12);
		data.add(row13);
		data.add(row14);
		data.add(row15);
		data.add(row16);
		data.add(row17);
		data.add(row18);
		data.add(row19);
		data.add(row20);
		data.add(row21);
		data.add(row22);
		data.add(row23);
		data.add(row24);
		data.add(row25);
		data.add(row26);
		data.add(row27);
		data.add(row28);
		data.add(row29);
		data.add(row30);
		data.add(row31);
		data.add(row32);
		data.add(row33);
		data.add(row34);
		data.add(row35);
		data.add(row36);
		data.add(row37);
		data.add(row38);
		data.add(row39);
		data.add(row40);
		data.add(row41);
		data.add(row42);

		//DEBUG
		System.out.println("Total size of tests: " + arList.size());
		for (AccessRequest q : arList) {
			System.out.println("("+q.getSA()+","+q.getAR()+","+q.getTA()+")");
		}
//		System.out.print(arList);
		System.out.print(totalNumberOfMutantsForTest);
		System.out.print(totalNumberOfKilledMutantsForTest);
		
		
		try {
			saveCSV(data, folder);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minutes = (timeElapsed / 1000) / 60;
		int seconds = (int) ((timeElapsed / 1000) % 60);
		System.out.println("Execution time in milliseconds: " + timeElapsed);
		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);
	}

	
	private double testAAG(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		AAG AAG = new AAG(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AAG");

		try {
			AAG.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		arList.clear();
		arList.addAll(AAG.getARList());
		
		double mutationScore = AAG.calculateMutationScore(AAG.getNumberOfMutants(),
				AAG.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AAG.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AAG.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AAG.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AAG.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRAG(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RAG RAG = new RAG(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RAG");

		try {
			RAG.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(RAG.getARList());
		
		double mutationScore = RAG.calculateMutationScore(RAG.getNumberOfMutants(),
				RAG.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RAG.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RAG.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RAG.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RAG.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRAD(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RAD RAD = new RAD(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RAD");

		try {
			RAD.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(RAD.getARList());
		

		double mutationScore = RAD.calculateMutationScore(RAD.getNumberOfMutants(),
				RAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RAD.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCAD(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		CAD CAD = new CAD(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CAD");

		try {
			CAD.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(CAD.getARList());
		

		double mutationScore = CAD.calculateMutationScore(CAD.getNumberOfMutants(),
				CAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CAD.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		CAA CAA = new CAA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CAA");

		try {
			CAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(CAA.getARList());
		

		double mutationScore = CAA.calculateMutationScore(CAA.getNumberOfMutants(),
				CAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCUAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		CUAA CUAA = new CUAA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CUAA");

		try {
			CUAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(CUAA.getARList());
		

		double mutationScore = CUAA.calculateMutationScore(CUAA.getNumberOfMutants(),
				CUAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CUAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CUAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CUAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CUAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCOAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		COAA COAA = new COAA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is COAA");

		try {
			COAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(COAA.getARList());
		

		double mutationScore = COAA.calculateMutationScore(COAA.getNumberOfMutants(),
				COAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + COAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + COAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += COAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += COAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRARA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RARA RARA = new RARA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RARA");

		try {
			RARA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(RARA.getARList());
		

		double mutationScore = RARA.calculateMutationScore(RARA.getNumberOfMutants(),
				RARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RARA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAARA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		AARA AARA = new AARA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AARA");

		try {
			AARA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(AARA.getARList());
		

		double mutationScore = AARA.calculateMutationScore(AARA.getNumberOfMutants(),
				AARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AARA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRAC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RAC RAC = new RAC(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RAC");

		try {
			RAC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(RAC.getARList());
		

		double mutationScore = RAC.calculateMutationScore(RAC.getNumberOfMutants(),
				RAC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RAC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RAC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RAC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RAC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAAC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		AAC AAC = new AAC(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AAC");

		try {
			AAC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		arList.clear();
		arList.addAll(AAC.getARList());

		double mutationScore = AAC.calculateMutationScore(AAC.getNumberOfMutants(),
				AAC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AAC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AAC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AAC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AAC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRARAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RARAA RARAA = new RARAA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RARAA");

		try {
			RARAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList.clear();
		arList.addAll(RARAA.getARList());
		

		double mutationScore = RARAA.calculateMutationScore(RARAA.getNumberOfMutants(),
				RARAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RARAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RARAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RARAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RARAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCSS(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		CSS CSS = new CSS(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CSS");

		try {
			CSS.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CSS.getARList();
		
		double mutationScore = CSS.calculateMutationScore(CSS.getNumberOfMutants(),
				CSS.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CSS.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CSS.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CSS.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CSS.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAOAR(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		AOAR AOAR = new AOAR(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AOAR");

		try {
			AOAR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = AOAR.getARList();
		
		double mutationScore = AOAR.calculateMutationScore(AOAR.getNumberOfMutants(),
				AOAR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AOAR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AOAR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AOAR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AOAR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCOAR(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		COAR COAR = new COAR(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is COAR");

		try {
			COAR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = COAR.getARList();
		
		double mutationScore = COAR.calculateMutationScore(COAR.getNumberOfMutants(),
				COAR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + COAR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + COAR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += COAR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += COAR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROAR(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		ROAR ROAR = new ROAR(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROAR");

		try {
			ROAR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROAR.getARList();
		
		double mutationScore = ROAR.calculateMutationScore(ROAR.getNumberOfMutants(),
				ROAR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROAR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROAR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROAR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROAR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRIS(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RIS RIS = new RIS(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RIS");

		try {
			RIS.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = RIS.getARList();
		
		double mutationScore = RIS.calculateMutationScore(RIS.getNumberOfMutants(),
				RIS.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RIS.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RIS.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RIS.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RIS.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAOC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		AOC AOC = new AOC(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AOC");

		try {
			AOC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = AOC.getARList();
		
		double mutationScore = AOC.calculateMutationScore(AOC.getNumberOfMutants(),
				AOC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AOC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AOC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AOC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AOC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCOC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		COC COC = new COC(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is COC");

		try {
			COC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = COC.getARList();
		
		double mutationScore = COC.calculateMutationScore(COC.getNumberOfMutants(),
				COC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + COC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + COC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += COC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += COC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROCT(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		ROCT ROCT = new ROCT(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROCT");

		try {
			ROCT.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROCT.getARList();
		
		double mutationScore = ROCT.calculateMutationScore(ROCT.getNumberOfMutants(),
				ROCT.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROCT.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROCT.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROCT.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROCT.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRCT(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		RCT RCT = new RCT(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RCT");

		try {
			RCT.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = RCT.getARList();
		
		double mutationScore = RCT.calculateMutationScore(RCT.getNumberOfMutants(),
				RCT.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RCT.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RCT.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RCT.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RCT.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROP(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws GraphDoesNotMatchTestSuitException {
		ROP ROP = new ROP(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROP");

		try {
			ROP.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROP.getARList();
		
		double mutationScore = ROP.calculateMutationScore(ROP.getNumberOfMutants(),
				ROP.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROP.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROP.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROP.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROP.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROB(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROB ROB = new ROB(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROB");

		try {
			ROB.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROB.getARList();
		
		double mutationScore = ROB.calculateMutationScore(ROB.getNumberOfMutants(),
				ROB.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROB.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROB.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROB.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROB.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEU(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CEU CEU = new CEU(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CEU");

		try {
			CEU.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CEU.getARList();
		
		double mutationScore = CEU.calculateMutationScore(CEU.getNumberOfMutants(),
				CEU.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CEU.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CEU.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CEU.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CEU.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREU(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		REU REU = new REU(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is REU");

		try {
			REU.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = REU.getARList();
		
		double mutationScore = REU.calculateMutationScore(REU.getNumberOfMutants(),
				REU.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + REU.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + REU.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += REU.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += REU.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEO(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CEO CEO = new CEO(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CEO");

		try {
			CEO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CEO.getARList();
		
		double mutationScore = CEO.calculateMutationScore(CEO.getNumberOfMutants(),
				CEO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CEO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CEO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CEO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CEO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAEO(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		AEO AEO = new AEO(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AEO");

		try {
			AEO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = AEO.getARList();
		
		double mutationScore = AEO.calculateMutationScore(AEO.getNumberOfMutants(),
				AEO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AEO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AEO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AEO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AEO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREO(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		REO REO = new REO(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is REO");

		try {
			REO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = REO.getARList();
		
		double mutationScore = REO.calculateMutationScore(REO.getNumberOfMutants(),
				REO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + REO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + REO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += REO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += REO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEPE(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CEPE CEPE = new CEPE(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CEPE");

		try {
			CEPE.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CEPE.getARList();
		
		double mutationScore = CEPE.calculateMutationScore(CEPE.getNumberOfMutants(),
				CEPE.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CEPE.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CEPE.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CEPE.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CEPE.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREPE(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		REPE REPE = new REPE(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is REPE");

		try {
			REPE.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = REPE.getARList();
		
		double mutationScore = REPE.calculateMutationScore(REPE.getNumberOfMutants(),
				REPE.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + REPE.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + REPE.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += REPE.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += REPE.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROC(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROC ROC = new ROC(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROC");

		try {
			ROC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROC.getARList();
		
		double mutationScore = ROC.calculateMutationScore(ROC.getNumberOfMutants(),
				ROC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testNCD(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		NCD NCD = new NCD(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is NCD");

		try {
			NCD.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = NCD.getARList();
		
		double mutationScore = NCD.calculateMutationScore(NCD.getNumberOfMutants(),
				NCD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + NCD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + NCD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += NCD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += NCD.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROF(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROF ROF = new ROF(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROF");

		try {
			ROF.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROF.getARList();
		
		double mutationScore = ROF.calculateMutationScore(ROF.getNumberOfMutants(),
				ROF.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROF.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROF.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROF.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROF.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testNOF(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		NOF NOF = new NOF(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is NOF");

		try {
			NOF.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = NOF.getARList();
		
		double mutationScore = NOF.calculateMutationScore(NOF.getNumberOfMutants(),
				NOF.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + NOF.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + NOF.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += NOF.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += NOF.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROA ROA = new ROA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROA");

		try {
			ROA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROA.getARList();
		
		double mutationScore = ROA.calculateMutationScore(ROA.getNumberOfMutants(),
				ROA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCOA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		COA COA = new COA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is COA");

		try {
			COA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = COA.getARList();
		
		double mutationScore = COA.calculateMutationScore(COA.getNumberOfMutants(),
				COA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + COA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + COA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += COA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += COA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testICA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ICA ICA = new ICA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ICA");

		try {
			ICA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ICA.getARList();
		
		double mutationScore = ICA.calculateMutationScore(ICA.getNumberOfMutants(),
				ICA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ICA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ICA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ICA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ICA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testIGA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		IGA IGA = new IGA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is IGA");

		try {
			IGA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = IGA.getARList();
		
		double mutationScore = IGA.calculateMutationScore(IGA.getNumberOfMutants(),
				IGA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + IGA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + IGA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += IGA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += IGA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testIAA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		IAA IAA = new IAA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is IAA");

		try {
			IAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = IAA.getARList();
		
		double mutationScore = IAA.calculateMutationScore(IAA.getNumberOfMutants(),
				IAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + IAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + IAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += IAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += IAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testINA(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		INA INA = new INA(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is INA");

		try {
			INA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = INA.getARList();
		
		double mutationScore = INA.calculateMutationScore(INA.getNumberOfMutants(),
				INA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + INA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + INA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += INA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += INA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROB_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROB_Solver ROB_Solver = new ROB_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROB_Solver");

		try {
			ROB_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROB_Solver.getARList();
		
		double mutationScore = ROB_Solver.calculateMutationScore(ROB_Solver.getNumberOfMutants(),
				ROB_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROB_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROB_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROB_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROB_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEU_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CEU_Solver CEU_Solver = new CEU_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CEU_Solver");

		try {
			CEU_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CEU_Solver.getARList();
		
		double mutationScore = CEU_Solver.calculateMutationScore(CEU_Solver.getNumberOfMutants(),
				CEU_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CEU_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CEU_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CEU_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CEU_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREU_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		REU_Solver REU_Solver = new REU_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is REU_Solver");

		try {
			REU_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = REU_Solver.getARList();
		
		double mutationScore = REU_Solver.calculateMutationScore(REU_Solver.getNumberOfMutants(),
				REU_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + REU_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + REU_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += REU_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += REU_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEO_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CEO_Solver CEO_Solver = new CEO_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CEO_Solver");

		try {
			CEO_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CEO_Solver.getARList();
		
		double mutationScore = CEO_Solver.calculateMutationScore(CEO_Solver.getNumberOfMutants(),
				CEO_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CEO_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CEO_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CEO_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CEO_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAEO_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		AEO_Solver AEO_Solver = new AEO_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AEO_Solver");

		try {
			AEO_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = AEO_Solver.getARList();
		
		double mutationScore = AEO_Solver.calculateMutationScore(AEO_Solver.getNumberOfMutants(),
				AEO_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AEO_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AEO_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AEO_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AEO_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCET_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CET_Solver CET_Solver = new CET_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CET_Solver");

		try {
			CET_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CET_Solver.getARList();
		
		double mutationScore = CET_Solver.calculateMutationScore(CET_Solver.getNumberOfMutants(),
				CET_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CET_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CET_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CET_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CET_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRET_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		RET_Solver RET_Solver = new RET_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RET_Solver");

		try {
			RET_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = RET_Solver.getARList();
		
		double mutationScore = RET_Solver.calculateMutationScore(RET_Solver.getNumberOfMutants(),
				RET_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RET_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RET_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RET_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RET_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROA_Solver ROA_Solver = new ROA_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROA_Solver");

		try {
			ROA_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROA_Solver.getARList();
		
		double mutationScore = ROA_Solver.calculateMutationScore(ROA_Solver.getNumberOfMutants(),
				ROA_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROA_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROA_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROA_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROA_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCTG_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CTG_Solver CTG_Solver = new CTG_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CTG_Solver");

		try {
			CTG_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CTG_Solver.getARList();
		
		double mutationScore = CTG_Solver.calculateMutationScore(CTG_Solver.getNumberOfMutants(),
				CTG_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CTG_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CTG_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CTG_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CTG_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCSG_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CSG_Solver CSG_Solver = new CSG_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CSG_Solver");

		try {
			CSG_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CSG_Solver.getARList();
		
		double mutationScore = CSG_Solver.calculateMutationScore(CSG_Solver.getNumberOfMutants(),
				CSG_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CSG_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CSG_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CSG_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CSG_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCARG_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CARG_Solver CARG_Solver = new CARG_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CARG_Solver");

		try {
			CARG_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CARG_Solver.getARList();
		
		double mutationScore = CARG_Solver.calculateMutationScore(CARG_Solver.getNumberOfMutants(),
				CARG_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CARG_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CARG_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CARG_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CARG_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAARG_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		AARG_Solver AARG_Solver = new AARG_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is AARG_Solver");

		try {
			AARG_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = AARG_Solver.getARList();
		
		double mutationScore = AARG_Solver.calculateMutationScore(AARG_Solver.getNumberOfMutants(),
				AARG_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AARG_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AARG_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AARG_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AARG_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRARG_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		RARG_Solver RARG_Solver = new RARG_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RARG_Solver");

		try {
			RARG_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = RARG_Solver.getARList();
		
		double mutationScore = RARG_Solver.calculateMutationScore(RARG_Solver.getNumberOfMutants(),
				RARG_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RARG_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RARG_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RARG_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RARG_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCDA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CDA_Solver CDA_Solver = new CDA_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CDA_Solver");

		try {
			CDA_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CDA_Solver.getARList();
		
		double mutationScore = CDA_Solver.calculateMutationScore(CDA_Solver.getNumberOfMutants(),
				CDA_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CDA_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CDA_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CDA_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CDA_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCASA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		CASA_Solver CASA_Solver = new CASA_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is CASA_Solver");

		try {
			CASA_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = CASA_Solver.getARList();
		
		double mutationScore = CASA_Solver.calculateMutationScore(CASA_Solver.getNumberOfMutants(),
				CASA_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + CASA_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + CASA_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += CASA_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += CASA_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRDA_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		RDA_Solver RDA_Solver = new RDA_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is RDA_Solver");

		try {
			RDA_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = RDA_Solver.getARList();
		
		double mutationScore = RDA_Solver.calculateMutationScore(RDA_Solver.getNumberOfMutants(),
				RDA_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + RDA_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + RDA_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += RDA_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += RDA_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROC_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROC_Solver ROC_Solver = new ROC_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROC_Solver");

		try {
			ROC_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROC_Solver.getARList();
		
		double mutationScore = ROC_Solver.calculateMutationScore(ROC_Solver.getNumberOfMutants(),
				ROC_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROC_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROC_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROC_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROC_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testNCD_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		NCD_Solver NCD_Solver = new NCD_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is NCD_Solver");

		try {
			NCD_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = NCD_Solver.getARList();
		
		double mutationScore = NCD_Solver.calculateMutationScore(NCD_Solver.getNumberOfMutants(),
				NCD_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + NCD_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + NCD_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += NCD_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += NCD_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROF_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		ROF_Solver ROF_Solver = new ROF_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is ROF_Solver");

		try {
			ROF_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = ROF_Solver.getARList();
		
		double mutationScore = ROF_Solver.calculateMutationScore(ROF_Solver.getNumberOfMutants(),
				ROF_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + ROF_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + ROF_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += ROF_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += ROF_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testNOF_Solver(String testMethod, Graph graph, Prohibitions prohibitions, String obligationPath) throws Exception {
		NOF_Solver NOF_Solver = new NOF_Solver(testMethod, graph, prohibitions, obligationPath, arList);
		System.out.println("MutationMethod is NOF_Solver");

		try {
			NOF_Solver.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		arList = NOF_Solver.getARList();
		
		double mutationScore = NOF_Solver.calculateMutationScore(NOF_Solver.getNumberOfMutants(),
				NOF_Solver.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + NOF_Solver.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + NOF_Solver.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += NOF_Solver.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += NOF_Solver.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private void createTestMethods() {
		testMethods = new ArrayList<String>();
//		testMethods.add("RS");
//		testMethods.add("R");
//		testMethods.add("Pairwise");
		testMethods.add("AllCombinations");

	}

	private void createHeaderForCSV() {
		String[] header = new String[colCount];


		header[0] = "TestMethod";
		header[1] = "AAG";
		header[2] = "RAG";
		header[3] = "RAD";
		header[4] = "CAD";
		header[5] = "CAA";
		header[6] = "CUAA";
		header[7] = "COAA";
		header[8] = "RARA";
		header[9] = "AARA";
		header[10] = "RAC";
		header[11] = "AAC";
		header[12] = "RARAA";
		header[13] = "CSS";
		header[14] = "AOAR";
		header[15] = "COAR";
		header[16] = "ROAR";
		header[17] = "RIS";
		header[18] = "AOC";
		header[19] = "COC";
		header[20] = "ROCT";
		header[21] = "RCT";
		header[22] = "ROP";
		header[23] = "ROB";
		header[24] = "CEU";
		header[25] = "REU";
		header[26] = "CEO";
		header[27] = "AEO";
		header[28] = "REO";
		header[29] = "CEPE";
		header[30] = "REPE";
		header[31] = "ROC";
		header[32] = "NCD";
		header[33] = "ROF";
		header[34] = "NOF";
		header[35] = "ROA";
		header[36] = "COA";
		header[37] = "ICA";
		header[38] = "IGA";
		header[39] = "IAA";
		header[40] = "INA";
		header[41] = "totalMutationScore";
		data.add(header);
	}

	

	public void saveCSV(List<String[]> data, File directoryForTestResults) throws PMException, IOException {

		if (directoryForTestResults.createNewFile()) {
			System.out.println("File has been created.");
		} else {
			System.out.println("File already exists.");
		}
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(directoryForTestResults+"/CSV/OverallMutationResults.csv"));
		CSVWriter CSVwriter = new CSVWriter(writer);
		CSVwriter.writeAll(data);
		writer.flush();
		CSVwriter.close();

		if (writer != null)
			writer.close();
	}
}
