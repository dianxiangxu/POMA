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
import POMA.TestSuitGeneration.Utils;
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
	static int rowCount = 20;
	Graph graph;
	
	public static void main(String[] args) throws PMException, IOException {
		MutationController mc = new MutationController();
		File CSV = new File(mc.CSVFilePath);
		
		
		//for (String testMethod : mc.testMethods) {
		long startTime = System.currentTimeMillis();

			String[] row=new String[rowCount];
			mc.totalNumberOfMutantsForTest = 0;
			mc.totalNumberOfKilledMutantsForTest = 0;
			double resultRAD = mc.testRAD("P");
			double resultCAD = mc.testCAD("P");
			double resultCAA = mc.testCAA("P");
			double resultRAGR = mc.testRAGR("P");
			double resultAAGR = mc.testAAGR("P");
			double resultCUAA = mc.testCUAA("P");
			double resultCOAA = mc.testCOAA("P");
			double resultRARA = mc.testRARA("P");
			double resultAARA = mc.testAARA("P");
			double resultRACR = mc.testRACR("P");
			double resultAACR = mc.testAACR("P");
			double resultRARAA = mc.testRARAA("P");

			row[0] = "P";
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
			row[13] = Double.toString((double)mc.totalNumberOfKilledMutantsForTest/(double)mc.totalNumberOfMutantsForTest*100);
			mc.data.add(row);
		//}
		mc.saveCSV(mc.data, CSV);
		
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		long minutes = (timeElapsed / 1000)  / 60;
		int seconds = (int)((timeElapsed / 1000) % 60);
		System.out.println("Execution time in milliseconds: " + timeElapsed);
		System.out.println("Execution time in min and sec: " + minutes + ":" + seconds);

	}

	public MutationController() {
		createTestMethods();
		createHeaderForCSV();
	}
	
	private double testRAD(String testMethod) {
		MutatorRAD mutatorRAD = new MutatorRAD(testMethod);
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

	private double testCAD(String testMethod) {
		MutatorCAD mutatorCAD = new MutatorCAD(testMethod);
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
	
	private double testCAA(String testMethod) {
		MutatorCAA mutatorCAA = new MutatorCAA(testMethod);
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
	
	private double testRAGR(String testMethod) {
		MutatorRAGR mutatorRAGR = new MutatorRAGR(testMethod);
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
	
	private double testAAGR(String testMethod) {
		MutatorAAGR mutatorAAGR = new MutatorAAGR(testMethod);
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
	
	private double testCUAA(String testMethod) {
		MutatorCUAA mutatorCUAA = new MutatorCUAA(testMethod);
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
	
	private double testCOAA(String testMethod) {
		MutatorCOAA mutatorCOAA = new MutatorCOAA(testMethod);
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

	private double testRARA(String testMethod) {
		MutatorRARA mutatorRARA = new MutatorRARA(testMethod);
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
	
	private double testAARA(String testMethod) {
		MutatorAARA mutatorAARA = new MutatorAARA(testMethod);
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

	private double testRACR(String testMethod) {
		MutatorRACR mutatorRACR = new MutatorRACR(testMethod);
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
	
	private double testAACR(String testMethod) {
		MutatorAACR mutatorAACR = new MutatorAACR(testMethod);
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
	
	private double testRARAA(String testMethod) {
		MutatorRARAA mutatorRARAA = new MutatorRARAA(testMethod);
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
		testMethods.add("P");
//		testMethods.add("PP");


	}

	private void createHeaderForCSV() {
		String[] header = new String[rowCount];
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
		writer = new BufferedWriter(new FileWriter(directoryForTestResults));
		CSVWriter CSVwriter = new CSVWriter(writer);
		CSVwriter.writeAll(data);
		writer.flush();
		CSVwriter.close();

		if (writer != null)
			writer.close();
	}
}
