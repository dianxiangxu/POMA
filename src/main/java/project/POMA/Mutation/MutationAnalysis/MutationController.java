package project.POMA.Mutation.MutationAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import gov.nist.csd.pm.exceptions.PMException;
import project.POMA.Mutation.MutationOperators.MutatorRAD;
import project.POMA.Mutation.MutationOperators.MutatorCAD;
import project.POMA.Mutation.MutationOperators.MutatorCAA;
import project.POMA.Mutation.MutationOperators.MutatorRAGR;
import project.POMA.Mutation.MutationOperators.MutatorAAGR;
import project.POMA.Mutation.MutationOperators.MutatorCUAA;
import project.POMA.Mutation.MutationOperators.MutatorCOAA;
import project.POMA.Mutation.MutationOperators.MutatorRARA;
import project.POMA.Mutation.MutationOperators.MutatorAARA;
import project.POMA.Mutation.MutationOperators.MutatorRACR;
import project.POMA.Mutation.MutationOperators.MutatorAACR;
import project.POMA.Mutation.MutationOperators.MutatorRARAA;

public class MutationController {
	List<String> testMethods;
	int totalNumberOfMutants = 0;
	int totalNumberOfKilledMutants = 0;
	int totalNumberOfMutantsForTest = 0;
	int totalNumberOfKilledMutantsForTest = 0;
	List<String[]> data = new ArrayList<String[]>();
	String[] row;
	String CSVFilePath = "CSV/OverallMutationResults2.csv";
	static int rowCount = 20;

	public static void main(String[] args) throws PMException, IOException {
		MutationController mc = new MutationController();
		File CSV = new File(mc.CSVFilePath);

		mc.createTestMethods();
		mc.createHeaderForCSV();
		for (String testMethod : mc.testMethods) {
			String[] row=new String[rowCount];
			mc.totalNumberOfMutantsForTest = 0;
			mc.totalNumberOfKilledMutantsForTest = 0;
			double resultRAD = mc.testRAD(testMethod);
			double resultCAD = mc.testCAD(testMethod);
			double resultCAA = mc.testCAA(testMethod);
			double resultRAGR = mc.testRAGR(testMethod);
			double resultAAGR = mc.testAAGR(testMethod);
			double resultCUAA = mc.testCUAA(testMethod);
			double resultCOAA = mc.testCOAA(testMethod);
			double resultRARA = mc.testRARA(testMethod);
			double resultAARA = mc.testAARA(testMethod);
			double resultRACR = mc.testRACR(testMethod);
			double resultAACR = mc.testAACR(testMethod);
			double resultRARAA = mc.testRARAA(testMethod);

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
			row[13] = Double.toString((double)mc.totalNumberOfKilledMutantsForTest/(double)mc.totalNumberOfMutantsForTest*100);
			mc.data.add(row);
		}
		mc.saveCSV(mc.data, CSV);
	}

	private double testRAD(String testMethod) {
		MutatorRAD mutatorRAD = new MutatorRAD();
		try {
			mutatorRAD.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRAD.calculateMutationScore(mutatorRAD.getNumberOfMutants(),
				mutatorRAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorRAD.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorRAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRAD.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testCAD(String testMethod) {
		MutatorCAD mutatorCAD = new MutatorCAD();
		try {
			mutatorCAD.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCAD.calculateMutationScore(mutatorCAD.getNumberOfMutants(),
				mutatorCAD.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorCAD.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorCAD.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCAD.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCAD.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCAD.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCAA(String testMethod) {
		MutatorCAA mutatorCAA = new MutatorCAA();
		try {
			mutatorCAA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCAA.calculateMutationScore(mutatorCAA.getNumberOfMutants(),
				mutatorCAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorCAA.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorCAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRAGR(String testMethod) {
		MutatorRAGR mutatorRAGR = new MutatorRAGR();
		try {
			mutatorRAGR.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRAGR.calculateMutationScore(mutatorRAGR.getNumberOfMutants(),
				mutatorRAGR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorRAGR.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorRAGR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRAGR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRAGR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRAGR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAAGR(String testMethod) {
		MutatorAAGR mutatorAAGR = new MutatorAAGR();
		try {
			mutatorAAGR.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAAGR.calculateMutationScore(mutatorAAGR.getNumberOfMutants(),
				mutatorAAGR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorAAGR.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorAAGR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAAGR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAAGR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAAGR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCUAA(String testMethod) {
		MutatorCUAA mutatorCUAA = new MutatorCUAA();
		try {
			mutatorCUAA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCUAA.calculateMutationScore(mutatorCUAA.getNumberOfMutants(),
				mutatorCUAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorCUAA.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorCUAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCUAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCUAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCUAA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testCOAA(String testMethod) {
		MutatorCOAA mutatorCOAA = new MutatorCOAA();
		try {
			mutatorCOAA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorCOAA.calculateMutationScore(mutatorCOAA.getNumberOfMutants(),
				mutatorCOAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorCOAA.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorCOAA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorCOAA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorCOAA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorCOAA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRARA(String testMethod) {
		MutatorRARA mutatorRARA = new MutatorRARA();
		try {
			mutatorRARA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRARA.calculateMutationScore(mutatorRARA.getNumberOfMutants(),
				mutatorRARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorRARA.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorRARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRARA.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAARA(String testMethod) {
		MutatorAARA mutatorAARA = new MutatorAARA();
		try {
			mutatorAARA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAARA.calculateMutationScore(mutatorAARA.getNumberOfMutants(),
				mutatorAARA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorAARA.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorAARA.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAARA.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAARA.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAARA.getNumberOfKilledMutants();
		return mutationScore;
	}

	private double testRACR(String testMethod) {
		MutatorRACR mutatorRACR = new MutatorRACR();
		try {
			mutatorRACR.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRACR.calculateMutationScore(mutatorRACR.getNumberOfMutants(),
				mutatorRACR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorRACR.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorRACR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorRACR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorRACR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorRACR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testAACR(String testMethod) {
		MutatorAACR mutatorAACR = new MutatorAACR();
		try {
			mutatorAACR.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorAACR.calculateMutationScore(mutatorAACR.getNumberOfMutants(),
				mutatorAACR.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorAACR.getMutationMethod());
		System.out.println("Number of mutations: " + mutatorAACR.getNumberOfMutants());
		System.out.println("Number of killed mutants: " + mutatorAACR.getNumberOfKilledMutants());

		System.out.println("Mutation Score: " + mutationScore + "%");
		System.out.println();
		totalNumberOfMutantsForTest += mutatorAACR.getNumberOfMutants();
		totalNumberOfKilledMutantsForTest += mutatorAACR.getNumberOfKilledMutants();
		return mutationScore;
	}
	
	private double testRARAA(String testMethod) {
		MutatorRARAA mutatorRARAA = new MutatorRARAA();
		try {
			mutatorRARAA.init(testMethod);
		} catch (PMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		double mutationScore = mutatorRARAA.calculateMutationScore(mutatorRARAA.getNumberOfMutants(),
				mutatorRARAA.getNumberOfKilledMutants());
		System.out.println("TestMethod is " + testMethod);
		System.out.println("MutationMethod is " + mutatorRARAA.getMutationMethod());
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
		testMethods.add("PNP");
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
