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

import POMA.TestSuitGeneration.Utils;
import POMA.Exceptions.GraphDoesNotMatchTestSuitException;
import POMA.Exceptions.NoTypeProvidedException;
import POMA.Mutation.ObligationMutationOperators.*;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.*;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;

public class ObligationMutationController {
	List<String> testMethods;
	int totalNumberOfMutants = 0;
	int totalNumberOfKilledMutants = 0;
	int totalNumberOfMutantsForTest = 0;
	int totalNumberOfKilledMutantsForTest = 0;
	List<String[]> data = new ArrayList<String[]>();
	String[] row;
	String CSVFilePath = "CSV/OverallMutationResults.csv";
	static int rowCount = 25;
	Graph graph;
	Obligation obligation;

	public static void main(String[] args) throws PMException, IOException, InstantiationException, IllegalAccessException, GraphDoesNotMatchTestSuitException, CloneNotSupportedException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		ObligationMutationController omc = new ObligationMutationController();
		File CSV = new File(omc.CSVFilePath);
		long startTime = System.currentTimeMillis();
		omc.createHeaderForCSV();
		String initialGraphConfig = "GPMSPolicies/simpleGraphToSMT.json";

//		omc.graph = Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
		omc.graph = Utils.readGPMSGraph();
		omc.obligation = readGPMSObligation();
		for (String testMethod : omc.testMethods) {

			String[] row = new String[rowCount];
			omc.totalNumberOfMutantsForTest = 0;
			omc.totalNumberOfKilledMutantsForTest = 0;
//			double resultROR = omc.testROR(testMethod, omc.graph, omc.obligation);
//			double resultCEU = omc.testCEU(testMethod, omc.graph, omc.obligation);
//			double resultREU = omc.testREU(testMethod, omc.graph, omc.obligation);
//			double resultCEPC = omc.testCEPC(testMethod, omc.graph, omc.obligation);
//			double resultREPC = omc.testREPC(testMethod, omc.graph, omc.obligation);
//			double resultCEO = omc.testCEO(testMethod, omc.graph, omc.obligation);
//			double resultAEO = omc.testAEO(testMethod, omc.graph, omc.obligation);
//			double resultREO = omc.testREO(testMethod, omc.graph, omc.obligation);
//			double resultCEPE = omc.testCEPE(testMethod, omc.graph, omc.obligation);
//			double resultREPE = omc.testREPE(testMethod, omc.graph, omc.obligation);
			//double resultRCA = omc.testRCA(testMethod, omc.graph, omc.obligation);//not implemented
			double resultROA = omc.testROA(testMethod, omc.graph, omc.obligation);
			

			row[0] = testMethod;
//			row[1] = Double.toString(resultROR);
//			row[2] = Double.toString(resultCEU);
//			row[3] = Double.toString(resultREU);
//			row[4] = Double.toString(resultCEPC);
//			row[5] = Double.toString(resultREPC);
//			row[6] = Double.toString(resultCEO);
//			row[7] = Double.toString(resultAEO);
//			row[8] = Double.toString(resultREO);
//			row[9] = Double.toString(resultCEPE);
//			row[10] = Double.toString(resultREPE);
			//row[11] = Double.toString(resultRCA);
			row[12] = Double.toString(resultROA);
			row[20] = Double.toString(
					(double) omc.totalNumberOfKilledMutantsForTest / (double) omc.totalNumberOfMutantsForTest * 100);
			omc.data.add(row);
		}
		omc.saveCSV(omc.data, CSV);

		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minutes = (timeElapsed / 1000) / 60;
		int seconds = (int) ((timeElapsed / 1000) % 60);
		System.out.println("Execution time in milliseconds: " + timeElapsed);
		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);

	}

	public ObligationMutationController() {
		createTestMethods();
	}

	public void createMutants(List<String> mutantNames, Graph graph) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException {
		createHeaderForCSV(mutantNames);
//
//		File CSV = new File(CSVFilePath);
//		long startTime = System.currentTimeMillis();
//
//		for (String testMethod : testMethods) {
//			String[] row = new String[rowCount];
//			totalNumberOfMutantsForTest = 0;
//			totalNumberOfKilledMutantsForTest = 0;
//			row[0] = testMethod;
//
//			for (int i = 0; i < mutantNames.size(); i++) {
//				System.out.println(mutantNames.toString());
//				double result = 0;
//				if (mutantNames.get(i).equals("ROR")) {
//					result = testROR(testMethod, graph, obligation);
////				} else if (mutantNames.get(i).equals("CAD")) {
////					result = testCAD(testMethod, graph);
////				} else if (mutantNames.get(i).equals("CAA")) {
////					result = testCAA(testMethod, graph);
////				} else if (mutantNames.get(i).equals("RAGR")) {
////					result = testRAGR(testMethod, graph);
////				} else if (mutantNames.get(i).equals("AAGR")) {
////					result = testAAGR(testMethod, graph);
////					System.out.println("HELLO");
////				} else if (mutantNames.get(i).equals("CUAA")) {
////					result = testCUAA(testMethod, graph);
////				} else if (mutantNames.get(i).equals("COAA")) {
////					result = testCOAA(testMethod, graph);
////				} else if (mutantNames.get(i).equals("RARA")) {
////					result = testRARA(testMethod, graph);
////				} else if (mutantNames.get(i).equals("AARA")) {
////					result = testAARA(testMethod, graph);
////				} else if (mutantNames.get(i).equals("RACR")) {
////					result = testRACR(testMethod, graph);
////				} else if (mutantNames.get(i).equals("AACR")) {
////					result = testAACR(testMethod, graph);
////				} else if (mutantNames.get(i).equals("RARAA")) {
////					result = testRARAA(testMethod, graph);
//				}
//
//				row[i+1] = Double.toString(result);
//				
//			}
//			row[mutantNames.size()+1] = Double.toString(
//					(double) totalNumberOfKilledMutantsForTest / (double) totalNumberOfMutantsForTest * 100);
//			data.add(row);
//		}
//		try {
//			saveCSV(data, CSV);
//		} catch (PMException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		long endTime = System.currentTimeMillis();
//		long timeElapsed = endTime - startTime;
//		long minutes = (timeElapsed / 1000) / 60;
//		int seconds = (int) ((timeElapsed / 1000) % 60);
//		System.out.println("Execution time in milliseconds: " + timeElapsed);
//		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);
	}

	private double testROR(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException {
		MutatorROR mutatorROR = new MutatorROR(testMethod, graph, obligation);
		System.out.println("MutationMethod is ROR");

		try {
			mutatorROR.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorROR.calculateMutationScore(mutatorROR.getNumberOfMutants(),
				mutatorROR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorROR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorROR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorROR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorROR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEU(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MutatorCEU mutatorCEU = new MutatorCEU(testMethod, graph, obligation);
		System.out.println("MutationMethod is CEU");

		try {
			mutatorCEU.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCEU.calculateMutationScore(mutatorCEU.getNumberOfMutants(),
				mutatorCEU.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCEU.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCEU.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCEU.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCEU.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREU(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MutatorREU mutatorREU = new MutatorREU(testMethod, graph, obligation);
		System.out.println("MutationMethod is REU");

		try {
			mutatorREU.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorREU.calculateMutationScore(mutatorREU.getNumberOfMutants(),
				mutatorREU.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorREU.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorREU.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorREU.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorREU.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEPC(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MutatorCEPC mutatorCEPC = new MutatorCEPC(testMethod, graph, obligation);
		System.out.println("MutationMethod is CEPC");

		try {
			mutatorCEPC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCEPC.calculateMutationScore(mutatorCEPC.getNumberOfMutants(),
				mutatorCEPC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCEPC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCEPC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCEPC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCEPC.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREPC(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		MutatorREPC mutatorREPC = new MutatorREPC(testMethod, graph, obligation);
		System.out.println("MutationMethod is REPC");

		try {
			mutatorREPC.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorREPC.calculateMutationScore(mutatorREPC.getNumberOfMutants(),
				mutatorREPC.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorREPC.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorREPC.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorREPC.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorREPC.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCEO(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorCEO mutatorCEO = new MutatorCEO(testMethod, graph, obligation);
		System.out.println("MutationMethod is CEO");

		try {
			mutatorCEO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCEO.calculateMutationScore(mutatorCEO.getNumberOfMutants(),
				mutatorCEO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCEO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCEO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCEO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCEO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAEO(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorAEO mutatorAEO = new MutatorAEO(testMethod, graph, obligation);
		System.out.println("MutationMethod is AEO");

		try {
			mutatorAEO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAEO.calculateMutationScore(mutatorAEO.getNumberOfMutants(),
				mutatorAEO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorAEO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAEO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAEO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAEO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREO(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorREO mutatorREO = new MutatorREO(testMethod, graph, obligation);
		System.out.println("MutationMethod is REO");

		try {
			mutatorREO.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorREO.calculateMutationScore(mutatorREO.getNumberOfMutants(),
				mutatorREO.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorREO.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorREO.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorREO.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorREO.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCEPE(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorCEPE mutatorCEPE = new MutatorCEPE(testMethod, graph, obligation);
		System.out.println("MutationMethod is CEPE");

		try {
			mutatorCEPE.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCEPE.calculateMutationScore(mutatorCEPE.getNumberOfMutants(),
				mutatorCEPE.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorCEPE.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCEPE.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCEPE.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCEPE.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testREPE(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorREPE mutatorREPE = new MutatorREPE(testMethod, graph, obligation);
		System.out.println("MutationMethod is REPE");

		try {
			mutatorREPE.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorREPE.calculateMutationScore(mutatorREPE.getNumberOfMutants(),
				mutatorREPE.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorREPE.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorREPE.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorREPE.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorREPE.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRCA(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorRCA mutatorRCA = new MutatorRCA(testMethod, graph, obligation);
		System.out.println("MutationMethod is RCA");

		try {
			mutatorRCA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRCA.calculateMutationScore(mutatorRCA.getNumberOfMutants(),
				mutatorRCA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorRCA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRCA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRCA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRCA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testROA(String testMethod, Graph graph, Obligation obligation) throws GraphDoesNotMatchTestSuitException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoTypeProvidedException {
		MutatorROA mutatorROA = new MutatorROA(testMethod, graph, obligation);
		System.out.println("MutationMethod is ROA");

		try {
			mutatorROA.init();
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorROA.calculateMutationScore(mutatorROA.getNumberOfMutants(),
				mutatorROA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("Number of mutations: " + mutatorROA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorROA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorROA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorROA.getNumberOfKilledMutants();
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
		header[1] = "ROR";
		header[2] = "CEU";
		header[3] = "REU";
		header[4] = "CEPC";
		header[5] = "REPC";
		header[6] = "CEO";
		header[7] = "AEO";
		header[8] = "REO";
		header[9] = "CEPE";
		header[10] = "REPE";
		header[11] = "RCA";
		header[12] = "ROA";
		header[20] = "totalMutationScore";
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
