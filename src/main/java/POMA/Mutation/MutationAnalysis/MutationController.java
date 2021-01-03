package POMA.Mutation.MutationAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import POMA.Mutation.MutationOperators.MutatorAACR;
import POMA.Mutation.MutationOperators.MutatorAAGR;
import POMA.Mutation.MutationOperators.MutatorAARA;
import POMA.Mutation.MutationOperators.MutatorCAA;
import POMA.Mutation.MutationOperators.MutatorCAD;
import POMA.Mutation.MutationOperators.MutatorCOAA;
import POMA.Mutation.MutationOperators.MutatorCUAA;
import POMA.Mutation.MutationOperators.MutatorRACR;
import POMA.Mutation.MutationOperators.MutatorRAD;
import POMA.Mutation.MutationOperators.MutatorRAGR;
import POMA.Mutation.MutationOperators.MutatorRARA;
import POMA.Mutation.MutationOperators.MutatorRARAA;
import POMA.Utils;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Mutation.MutationOperators.*;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.*;

public class MutationController {
	List<String> testMethods;
	int totalNumberOfMutants = 0;
	int totalNumberOfKilledMutants = 0;
	int totalNumberOfMutantsForTest = 0;
	int totalNumberOfKilledMutantsForTest = 0;
	List<String[]> data = new ArrayList<String[]>();
	String[] row;
	String CSVFilePath = "CSV/OverallMutationResults.csv";
	static int colCount = 3;
	Graph graph;

	
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
		MutationController mc = new MutationController();
		File CSV = new File(mc.CSVFilePath);
		long startTime = System.currentTimeMillis();
		mc.createHeaderForCSV();
		 String initialGraphConfig = "GPMSPolicies/simpleGraphToSMT.json";

		mc.graph = Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
		for (String testMethod : mc.testMethods) {

			String[] row = new String[colCount];
			mc.totalNumberOfMutantsForTest = 0;
			mc.totalNumberOfKilledMutantsForTest = 0;
			double resultRAD = mc.testRAD(testMethod, mc.graph);
			double resultCAD = mc.testCAD(testMethod, mc.graph);
			double resultCAA = mc.testCAA(testMethod, mc.graph);
			double resultRAGR = mc.testRAGR(testMethod, mc.graph);
			double resultAAGR = mc.testAAGR(testMethod, mc.graph);
			double resultCUAA = mc.testCUAA(testMethod, mc.graph);
			double resultCOAA = mc.testCOAA(testMethod, mc.graph);
			double resultRARA = mc.testRARA(testMethod, mc.graph);
			double resultAARA = mc.testAARA(testMethod, mc.graph);
			double resultRACR = mc.testRACR(testMethod, mc.graph);
			double resultAACR = mc.testAACR(testMethod, mc.graph);
			double resultRARAA = mc.testRARAA(testMethod, mc.graph);

			row[0] = testMethod;
			row[1] = Double.toString(resultRAD);
			row[2] = Double.toString(resultCAD);
			row[3] = Double.toString(resultCAA);
			row[4] = Double.toString(resultRAGR);
			row[5] = Double.toString(resultAAGR);
			row[6] = Double.toString(resultCUAA);
			row[7] = Double.toString(resultCOAA);
			row[8] = Double.toString(resultRARA);
			row[9] = Double.toString(resultAARA);
			row[10] = Double.toString(resultRACR);
			row[11] = Double.toString(resultAACR);
			row[12] = Double.toString(resultRARAA);
			row[13] = Double.toString(
					(double) mc.totalNumberOfKilledMutantsForTest / (double) mc.totalNumberOfMutantsForTest * 100);
			mc.data.add(row);
		}
		mc.saveCSV(mc.data, CSV);

		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minutes = (timeElapsed / 1000) / 60;
		int seconds = (int) ((timeElapsed / 1000) % 60);
		System.out.println("Execution time in milliseconds: " + timeElapsed);
		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);

	}

	public MutationController() {
		createTestMethods();
	}

	public void createMutants(List<String> mutantNames, Graph graph, File folder) throws GraphDoesNotMatchTestSuitException {
		
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
				if (mutantNames.get(i).equals("RAD")) {
					row2[0] = "RAD";
					row2[j] = Double.toString(testRAD(testMethod, graph));
				} else if (mutantNames.get(i).equals("CAD")) {
					row3[0] = "CAD";
					row3[j] = Double.toString(testCAD(testMethod, graph));
				} else if (mutantNames.get(i).equals("CAA")) {
					row4[0] = "CAA";
					row4[j] = Double.toString(testCAA(testMethod, graph));
				} else if (mutantNames.get(i).equals("RAGR")) {
					row5[0] = "RAGR";
					row5[j] = Double.toString(testRAGR(testMethod, graph));
				} else if (mutantNames.get(i).equals("AAGR")) {
					row6[0] = "AAGR";
					row6[j] = Double.toString(testAAGR(testMethod, graph));
					System.out.println("HELLO");
				} else if (mutantNames.get(i).equals("CUAA")) {
					row7[0] = "CUAA";
					row7[j] = Double.toString(testCUAA(testMethod, graph));
				} else if (mutantNames.get(i).equals("COAA")) {
					row8[0] = "COAA";
					row8[j] = Double.toString(testCOAA(testMethod, graph));
				} else if (mutantNames.get(i).equals("RARA")) {
					row9[0] = "RARA";
					row9[j] = Double.toString(testRARA(testMethod, graph));
				} else if (mutantNames.get(i).equals("AARA")) {
					row10[0] = "AARA";
					row10[j] = Double.toString(testAARA(testMethod, graph));
				} else if (mutantNames.get(i).equals("RACR")) {
					row11[0] = "RACR";
					row11[j] = Double.toString(testRACR(testMethod, graph));
				} else if (mutantNames.get(i).equals("AACR")) {
					row12[j] = Double.toString(testAACR(testMethod, graph));
					row12[0] = "AACR";
				} else if (mutantNames.get(i).equals("RARAA")) {
					row13[0] = "RARAA";
					row13[j] = Double.toString(testRARAA(testMethod, graph));
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

	private double testRAD(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorRAD mutatorRAD = new MutatorRAD(testMethod, graph);
		System.out.println("MutationMethod is RAD");

		try {
			mutatorRAD.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRAD.calculateMutationScore(mutatorRAD.getNumberOfMutants(),
				mutatorRAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRAD.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCAD(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorCAD mutatorCAD = new MutatorCAD(testMethod, graph);
		System.out.println("MutationMethod is CAD");

		try {
			mutatorCAD.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCAD.calculateMutationScore(mutatorCAD.getNumberOfMutants(),
				mutatorCAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCAD.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCAA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorCAA mutatorCAA = new MutatorCAA(testMethod, graph);
		System.out.println("MutationMethod is CAA");

		try {
			mutatorCAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCAA.calculateMutationScore(mutatorCAA.getNumberOfMutants(),
				mutatorCAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCAA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRAGR(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorRAGR mutatorRAGR = new MutatorRAGR(testMethod, graph);
		System.out.println("MutationMethod is RAGR");

		try {
			mutatorRAGR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRAGR.calculateMutationScore(mutatorRAGR.getNumberOfMutants(),
				mutatorRAGR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRAGR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRAGR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRAGR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRAGR.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testAAGR(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorAAGR mutatorAAGR = new MutatorAAGR(testMethod, graph);
		System.out.println("MutationMethod is AAGR");

		try {
			mutatorAAGR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAAGR.calculateMutationScore(mutatorAAGR.getNumberOfMutants(),
				mutatorAAGR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAAGR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAAGR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAAGR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAAGR.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCUAA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorCUAA mutatorCUAA = new MutatorCUAA(testMethod, graph);
		System.out.println("MutationMethod is CUAA");

		try {
			mutatorCUAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCUAA.calculateMutationScore(mutatorCUAA.getNumberOfMutants(),
				mutatorCUAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCUAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCUAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCUAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCUAA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCOAA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorCOAA mutatorCOAA = new MutatorCOAA(testMethod, graph);
		System.out.println("MutationMethod is COAA");

		try {
			mutatorCOAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCOAA.calculateMutationScore(mutatorCOAA.getNumberOfMutants(),
				mutatorCOAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCOAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCOAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCOAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCOAA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRARA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorRARA mutatorRARA = new MutatorRARA(testMethod, graph);
		System.out.println("MutationMethod is RARA" + mutatorRARA.getMutationMethod());

		try {
			mutatorRARA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRARA.calculateMutationScore(mutatorRARA.getNumberOfMutants(),
				mutatorRARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRARA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testAARA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorAARA mutatorAARA = new MutatorAARA(testMethod, graph);
		System.out.println("MutationMethod is AARA");

		try {
			mutatorAARA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAARA.calculateMutationScore(mutatorAARA.getNumberOfMutants(),
				mutatorAARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAARA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRACR(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorRACR mutatorRACR = new MutatorRACR(testMethod, graph);
		System.out.println("MutationMethod is RACR");

		try {
			mutatorRACR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRACR.calculateMutationScore(mutatorRACR.getNumberOfMutants(),
				mutatorRACR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRACR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRACR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRACR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRACR.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testAACR(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorAACR mutatorAACR = new MutatorAACR(testMethod, graph);
		System.out.println("MutationMethod is AACR");

		try {
			mutatorAACR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAACR.calculateMutationScore(mutatorAACR.getNumberOfMutants(),
				mutatorAACR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAACR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAACR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAACR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAACR.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRARAA(String testMethod, Graph graph) throws GraphDoesNotMatchTestSuitException {
		MutatorRARAA mutatorRARAA = new MutatorRARAA(testMethod, graph);
		System.out.println("MutationMethod is RARAA");

		try {
			mutatorRARAA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRARAA.calculateMutationScore(mutatorRARAA.getNumberOfMutants(),
				mutatorRARAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRARAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRARAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRARAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRARAA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private void createTestMethods() {
		testMethods = new ArrayList<String>();
//		testMethods.add("RS");
//		testMethods.add("R");
		testMethods.add("Pairwise");
		testMethods.add("AllCombinations");

	}

	private void createHeaderForCSV() {
		String[] header = new String[colCount];


		header[0] = "TestMethod";
		header[1] = "RAD";
		header[2] = "CAD";
		header[3] = "CAA";
		header[4] = "RAGR";
		header[5] = "AAGR";
		header[6] = "CUAA";
		header[7] = "COAA";
		header[8] = "RARA";
		header[9] = "AARA";
		header[10] = "RACR";
		header[11] = "AACR";
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
