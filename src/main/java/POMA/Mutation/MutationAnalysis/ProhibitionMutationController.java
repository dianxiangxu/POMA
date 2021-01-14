package POMA.Mutation.MutationAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Exceptions.NoTypeProvidedException;
import POMA.Mutation.ProhibitionMutationOperators.*;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.*;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class ProhibitionMutationController {
	List<String> testMethods;
	int totalNumberOfMutants = 0;
	int totalNumberOfKilledMutants = 0;
	static int totalNumberOfMutantsForTest = 0;
	static int totalNumberOfKilledMutantsForTest = 0;
	List<String[]> data = new ArrayList<String[]>();
	String[] row;
	String CSVFilePath = "CSV/ProhibitionMutationResults.csv";
	static int rowCount = 30;
	static int colCount = 3;
	Graph graph;
	Prohibitions prohibitions;

	public static void main(String[] args) throws PMException, IOException, InstantiationException, IllegalAccessException, GraphDoesNotMatchTestSuitException, CloneNotSupportedException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		ProhibitionMutationController pmc = new ProhibitionMutationController();
		File CSV = new File(pmc.CSVFilePath);
		long startTime = System.currentTimeMillis();
		pmc.createHeaderForCSV();
//		String initialGraphConfig = "GPMSPolicies/simpleGraphToSMT.json";

		pmc.graph = ProhibitionMutation.readGraph("GPMSPolices/Demo/graph.json");
		pmc.prohibitions = ProhibitionMutation.readProhibition("src/main/java/POMA/Mutation/ProhibitionMutationOperators/prohibitions1.json");
		for (String testMethod : pmc.testMethods) {

			String[] row = new String[rowCount];
			pmc.totalNumberOfMutantsForTest = 0;
			pmc.totalNumberOfKilledMutantsForTest = 0;
			double resultAOC = pmc.testAOC(testMethod, pmc.graph, pmc.prohibitions);
			double resultAOO = pmc.testAOO(testMethod, pmc.graph, pmc.prohibitions);
			double resultCOC = pmc.testCOC(testMethod, pmc.graph, pmc.prohibitions);
			double resultCOO = pmc.testCOO(testMethod, pmc.graph, pmc.prohibitions);
			double resultCUS = pmc.testCUS(testMethod, pmc.graph, pmc.prohibitions);
			double resultRCT = pmc.testRCT(testMethod, pmc.graph, pmc.prohibitions);
			double resultRIS = pmc.testRIS(testMethod, pmc.graph, pmc.prohibitions);
			double resultROCT = pmc.testROCT(testMethod, pmc.graph, pmc.prohibitions);
			double resultROO = pmc.testROO(testMethod, pmc.graph, pmc.prohibitions);
			
			

			row[0] = testMethod;
			row[1] = Double.toString(resultAOC);
			row[2] = Double.toString(resultAOO);
			row[3] = Double.toString(resultCOC);
			row[4] = Double.toString(resultCOO);
			row[5] = Double.toString(resultCUS);
			row[6] = Double.toString(resultRCT);
			row[7] = Double.toString(resultRIS);
			row[8] = Double.toString(resultROCT);
			row[9] = Double.toString(resultROO);
			row[10] = Double.toString(
					(double) pmc.totalNumberOfKilledMutantsForTest / (double) pmc.totalNumberOfMutantsForTest * 100);
			pmc.data.add(row);
		}
		pmc.saveCSV(pmc.data, CSV);
		
		System.out.println("Number of mutations: " + totalNumberOfMutantsForTest);
		System.out.println("Number of killed mutants: " + totalNumberOfKilledMutantsForTest);

		System.out.println("Mutation Score: " + totalNumberOfKilledMutantsForTest*100/totalNumberOfMutantsForTest + "%");

		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minutes = (timeElapsed / 1000) / 60;
		int seconds = (int) ((timeElapsed / 1000) % 60);
		System.out.println("Execution time in milliseconds: " + timeElapsed);
		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);

	}

	public ProhibitionMutationController() {
		createTestMethods();
	}

public void createMutants(List<String> mutantNames, Graph graph, Prohibitions prohibitions, File folder) throws GraphDoesNotMatchTestSuitException, NoTypeProvidedException, InstantiationException {
		
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
		row11[0] = "totalMutationScore";

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
				if (mutantNames.get(i).equals("AOC")) {
					row2[0] = "AOC";
					row2[j] = Double.toString(testAOC(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("AOO")) {
					row3[0] = "AOO";
					row3[j] = Double.toString(testAOO(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("COC")) {
					row4[0] = "COC";
					row4[j] = Double.toString(testCOC(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("COO")) {
					row5[0] = "COO";
					row5[j] = Double.toString(testCOO(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("CUS")) {
					row6[0] = "CUS";
					row6[j] = Double.toString(testCUS(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("RCT")) {
					row7[0] = "RCT";
					row7[j] = Double.toString(testRCT(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("RIS")) {
					row8[0] = "RIS";
					row8[j] = Double.toString(testRIS(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("ROCT")) {
					row9[0] = "ROCT";
					row9[j] = Double.toString(testROCT(testMethod, graph, prohibitions));
				} else if (mutantNames.get(i).equals("ROO")) {
					row10[0] = "ROO";
					row10[j] = Double.toString(testROO(testMethod, graph, prohibitions));
				}
				
			}
			row11[j] = Double.toString(
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

	private double testAOC(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorAOC mutatorAOC = new MutatorAOC(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is AOC");

		try {
			mutatorAOC.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAOC.calculateMutationScore(mutatorAOC.getNumberOfMutants(),
				mutatorAOC.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAOC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAOC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAOC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAOC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAOO(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorAOO mutatorAOO = new MutatorAOO(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is AOO");

		try {
			mutatorAOO.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAOO.calculateMutationScore(mutatorAOO.getNumberOfMutants(),
				mutatorAOO.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAOO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAOO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAOO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAOO.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCOC(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorCOC mutatorCOC = new MutatorCOC(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is COC");

		try {
			mutatorCOC.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCOC.calculateMutationScore(mutatorCOC.getNumberOfMutants(),
				mutatorCOC.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCOC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCOC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCOC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCOC.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCOO(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorCOO mutatorCOO = new MutatorCOO(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is COO");

		try {
			mutatorCOO.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCOO.calculateMutationScore(mutatorCOO.getNumberOfMutants(),
				mutatorCOO.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCOO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCOO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCOO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCOO.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCUS(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorCUS mutatorCUS = new MutatorCUS(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is CUS");

		try {
			mutatorCUS.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCUS.calculateMutationScore(mutatorCUS.getNumberOfMutants(),
				mutatorCUS.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCUS.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCUS.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCUS.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCUS.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRCT(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorRCT mutatorRCT = new MutatorRCT(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is RCT");

		try {
			mutatorRCT.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRCT.calculateMutationScore(mutatorRCT.getNumberOfMutants(),
				mutatorRCT.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRCT.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRCT.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRCT.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRCT.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRIS(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorRIS mutatorRIS = new MutatorRIS(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is RIS");

		try {
			mutatorRIS.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRIS.calculateMutationScore(mutatorRIS.getNumberOfMutants(),
				mutatorRIS.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRIS.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRIS.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRIS.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRIS.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testROCT(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorROCT mutatorROCT = new MutatorROCT(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is ROCT");

		try {
			mutatorROCT.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorROCT.calculateMutationScore(mutatorROCT.getNumberOfMutants(),
				mutatorROCT.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorROCT.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorROCT.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorROCT.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorROCT.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testROO(String testMethod, Graph graph, Prohibitions prohibitions) throws GraphDoesNotMatchTestSuitException {
		MutatorROO mutatorROO = new MutatorROO(testMethod, graph, prohibitions);
		System.out.println("MutationMethod is ROO");

		try {
			mutatorROO.main(graph);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorROO.calculateMutationScore(mutatorROO.getNumberOfMutants(),
				mutatorROO.getNumberOfKilledMutants());
//		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorROO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorROO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorROO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorROO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private void createTestMethods() {
		testMethods = new ArrayList<String>();
//		testMethods.add("RS");
//		testMethods.add("R");
		testMethods.add("Pairwise");
//		testMethods.add("AllCombinations");

	}

	private void createHeaderForCSV() {
		String[] header = new String[rowCount];
		header[0] = "TestMethod";
		header[1] = "AOC";
		header[2] = "AOO";
		header[3] = "COC";
		header[4] = "COO";
		header[5] = "CUS";
		header[6] = "RCT";
		header[7] = "RIS";
		header[8] = "ROCT";
		header[9] = "ROO";
		
		header[10] = "totalMutationScore";
		data.add(header);
	}

	private void createHeaderForCSV(List<String> mutantNames) {
		String[] header = new String[rowCount];
		header[0] = "TestMethod";
		for(int i=0;i< mutantNames.size(); i++) {
			header[i+1] =  mutantNames.get(i);
		}
		header[mutantNames.size()+1] = "totalMutationScore";
		data.add(header);
	}

	public void saveCSV(List<String[]> data, File directoryForTestResults) throws PMException, IOException {

		if (directoryForTestResults.createNewFile()) {
			System.out.println("File has been created.");
		} else {
			System.out.println("File already exists.");
		}
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(directoryForTestResults));
		CSVWriter CSVwriter = new CSVWriter(writer);
		CSVwriter.writeAll(data);
		writer.flush();
		CSVwriter.close();

		if (writer != null)
			writer.close();
	}
	
	public static Obligation readGPMSObligation() throws PMException, IOException {
		File obligationFile = getFileFromResources("GPMSPolicies/GPMS/Obligations.yml");
		InputStream inputStream = new FileInputStream(obligationFile);
		Obligation obligation = EVRParser.parse(inputStream);
		return obligation;
	}
	
	public static File getFileFromResources(String fileName) {
		File resource = new File(fileName);
		return resource;
	}
}
