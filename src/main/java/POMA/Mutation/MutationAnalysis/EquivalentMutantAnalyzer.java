package POMA.Mutation.MutationAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import POMA.Mutation.EquivalentMutantAnalyzer.EMAAG;
import POMA.Mutation.EquivalentMutantAnalyzer.AAGAnalyze;
import POMA.Mutation.EquivalentMutantAnalyzer.EMRAG;

import POMA.Mutation.MutationOperators.MutatorAAC;
import POMA.Mutation.MutationOperators.MutatorAAG;
import POMA.Mutation.MutationOperators.MutatorAARA;
import POMA.Mutation.MutationOperators.MutatorCAA;
import POMA.Mutation.MutationOperators.MutatorCAD;
import POMA.Mutation.MutationOperators.MutatorCOAA;
import POMA.Mutation.MutationOperators.MutatorCUAA;
import POMA.Mutation.MutationOperators.MutatorRAC;
import POMA.Mutation.MutationOperators.MutatorRAD;
import POMA.Mutation.MutationOperators.MutatorRAG;
import POMA.Mutation.MutationOperators.MutatorRARA;
import POMA.Mutation.MutationOperators.MutatorRARAA;
import POMA.Utils;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Mutation.MutationOperators.*;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.*;
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
	static int colCount = 15;
	Graph graph;
	public Prohibitions prohibitions;

	
	private void createHeaderForCSV(List<String> mutantNames) {
		String[] header = new String[colCount];
		header[0]= "TestMethod";
		for(int i=0;i< mutantNames.size(); i++) {
			header[i+1] =  mutantNames.get(i);
		}
		header[mutantNames.size()+1] = "totalMutationScore";
		data.add(header);
	}
	
	public static void main(String[] args) throws PMException, IOException, GraphDoesNotMatchTestSuitException {
		EquivalentMutantAnalyzer mc = new EquivalentMutantAnalyzer();
		List<String> mutantNames = new ArrayList<String>();
		String initialGraphConfig = "";
		String initialProhibitionConfig = "";
		
//        initialGraphConfig = "Policies/GPMS/Graph.json";
        initialGraphConfig = "Policies/LawUseCase/Graph.json";
//        initialProhibitionConfig = "Policies/LawUseCase/prohibitions.json";
//        initialGraphConfig = "Policies/BankPolicy/Complex/bank_policy_config.json";
//        initialGraphConfig = "Policies/ProhibitionExample/ProhibitionsMedicalExampleOA/graph.json";
//        initialProhibitionConfig = "Policies/ProhibitionExample/ProhibitionsMedicalExampleOA/prohibitionsx1.json";

        
        
		File folder = new File(initialGraphConfig).getParentFile();
//		mutantNames.add("RAD");
//		mutantNames.add("CAD");
//		mutantNames.add("CAA");
//		mutantNames.add("RAG");
//		mutantNames.add("EMRAG");
//		mutantNames.add("AAG");
//		mutantNames.add("EMAAG");
		mutantNames.add("AAGAnalyze");
//		mutantNames.add("CUAA");
//		mutantNames.add("COAA");
//		mutantNames.add("RARA");
//		mutantNames.add("AARA");
//		mutantNames.add("RAC");
//		mutantNames.add("AAC");
//		mutantNames.add("RARAA");
		mc.graph = Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
//		initialProhibitionConfig = "";
		if (initialProhibitionConfig != "")
		    mc.prohibitions = Utils.readProhibitions(initialProhibitionConfig);
		mc.createMutants( mutantNames,  mc.graph, mc.prohibitions, folder);
	}

	public EquivalentMutantAnalyzer() {
		createTestMethods();
	}

	public void createMutants(List<String> mutantNames, Graph graph, Prohibitions prohibitions, File folder) throws GraphDoesNotMatchTestSuitException {
		
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
		row14[0] = "totalMutationScore";

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
				double result = 0;
				if (mutantNames.get(i).equals("EMAAG")) {
					row2[0] = "EMAAG";
					row2[j] = Double.toString(testEMAAG(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("AAGAnalyze")) {
					row3[0] = "AAGAnalyze";
					row3[j] = Double.toString(testAAGAnalyze(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("EMRAG")) {
					row4[0] = "EMRAG";
					row4[j] = Double.toString(testEMRAG(testMethod, graph, prohibitions));
				}
			}
			row14[j] = Double.toString(
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

	private double testEMAAG(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		EMAAG EMAAG = new EMAAG(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is EMAAG");

		try {
			EMAAG.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = EMAAG.calculateMutationScore(EMAAG.getNumberOfMutants(),
				EMAAG.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + EMAAG.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + EMAAG.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += EMAAG.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += EMAAG.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAAGAnalyze(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		AAGAnalyze AAGAnalyze = new AAGAnalyze(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is AAGAnalyze");

		try {
			AAGAnalyze.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = AAGAnalyze.calculateMutationScore(AAGAnalyze.getNumberOfMutants(),
				AAGAnalyze.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + AAGAnalyze.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + AAGAnalyze.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += AAGAnalyze.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += AAGAnalyze.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testEMRAG(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		EMRAG EMRAG = new EMRAG(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is EMRAG");

		try {
			EMRAG.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = EMRAG.calculateMutationScore(EMRAG.getNumberOfMutants(),
				EMRAG.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + EMRAG.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + EMRAG.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += EMRAG.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += EMRAG.getNumberOfKilledMutants();
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
		header[1] = "RAD";
		header[2] = "CAD";
		header[3] = "CAA";
		header[4] = "RAG";
		header[5] = "AAG";
		header[6] = "CUAA";
		header[7] = "COAA";
		header[8] = "RARA";
		header[9] = "AARA";
		header[10] = "RAC";
		header[11] = "AAC";
		header[12] = "RARAA";
		header[13] = "totalMutationScore";
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
