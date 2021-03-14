package POMA.PolicyGeneration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.github.javafaker.Faker;

import POMA.Utils;
import POMA.Exceptions.NoTypeProvidedException;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;

public class GPMSGraphGenerator {

	final int numberOfColleges = 10;
	final int numberOfDepartments = 2;
	final int numberOfUsers = 4;

	List<String> existentCollegeList = new ArrayList<String>();

	List<String> collegeLettersList = new ArrayList<String>();
	List<String> collegeDepartmentList = new ArrayList<String>();;
	List<String> userNames = new ArrayList<String>();;

	Graph academicGeneratedGraph = new MemGraph();
	Graph academicUnitsPolicyClassGraph = new MemGraph();

	Graph eligibilityGeneratedGraph = new MemGraph();
	Graph eligibilityPolicyClassGraph = new MemGraph();

	String folderName = "Policies/GPMS" + numberOfColleges;

	public static void main(String[] args) throws Exception {
		final long startTime = System.currentTimeMillis();

		GPMSGraphGenerator gg = new GPMSGraphGenerator();

		String AcademicUnitsPolicyClass = "Policies/GPMS/AcademicUnitsPolicyClass.json";
		String EligibilityPolicyClass = "Policies/GPMS/EligibilityPolicyClass.json";

		gg.academicUnitsPolicyClassGraph = Utils.readAnyGraph(AcademicUnitsPolicyClass);

		gg.eligibilityPolicyClassGraph = Utils.readAnyGraph(EligibilityPolicyClass);
		GraphSerializer.fromJson(gg.eligibilityGeneratedGraph, GraphSerializer.toJson(gg.eligibilityPolicyClassGraph));

		// System.out.println(initialGraph.getChildren("AcademicUnitsPolicyClass"));
		gg.getExistentCollegeLetters(gg.academicUnitsPolicyClassGraph.getChildren("AcademicUnitsPolicyClass"));
		gg.generateRandomCollegeLetters();

		gg.generateNewGraph(gg.academicUnitsPolicyClassGraph);

		gg.checkOrCreateFolder();

		Utils.saveDataToFile(GraphSerializer.toJson(gg.academicGeneratedGraph),
				gg.folderName + "/AcademicUnitsPolicyClass.json");
		Utils.saveDataToFile(GraphSerializer.toJson(gg.eligibilityGeneratedGraph),
				gg.folderName + "/EligibilityPolicyClass.json");
		// System.out.println(gg.existentCollegeList);
		// System.out.println(gg.collegeLettersList);
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));

		gg.copyDefaultPolicies();
		Utils.saveDataToFile(gg.getCompleteInfo(), gg.folderName + "/info.txt");

	}

	private void copyDefaultPolicies() throws Exception {
		File originalEditing = new File("policies/GPMS/EditingPolicyClass.json");
		File originalAdministration = new File("policies/GPMS/AdministrationUnitsPolicyClass.json");
		File copyEditing = new File(folderName + "/EditingPolicyClass.json");
		File copyAdministration = new File(folderName + "/AdministrationUnitsPolicyClass.json");
		FileUtils.copyFile(originalEditing, copyEditing);
		FileUtils.copyFile(originalAdministration, copyAdministration);
	}

	private String getCompleteInfo() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("Eligibility Policy Class:");
		sb.append(System.lineSeparator());
		sb.append(getGraphInfo(eligibilityGeneratedGraph));
		sb.append("Academic Policy Class:");
		sb.append(System.lineSeparator());
		sb.append(getGraphInfo(academicGeneratedGraph));

		// default policies
		Graph editingGraph = Utils.readAnyGraph("Policies/GPMS/EditingPolicyClass.json");
		Graph administrationGraph = Utils.readAnyGraph("Policies/GPMS/AdministrationUnitsPolicyClass.json");

		sb.append("Editing Policy Class:");
		sb.append(System.lineSeparator());
		sb.append(getGraphInfo(editingGraph));
		sb.append("Administration Policy Class:");
		sb.append(System.lineSeparator());
		sb.append(getGraphInfo(administrationGraph));
		int totalSize = eligibilityGeneratedGraph.getNodes().size()
				+ academicGeneratedGraph.getNodes().size() + editingGraph.getNodes().size()
				+ administrationGraph.getNodes().size();
		sb.append("TOTAL NODES: " + totalSize);
		return sb.toString();
	}

	private String getGraphInfo(Graph graph) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("     UA : " + Integer.toString(Utils.getNodesByTypes(graph, "UA").size()));
		sb.append(System.lineSeparator());
		sb.append("     U: " + Integer.toString(Utils.getNodesByTypes(graph, "U").size()));
		sb.append(System.lineSeparator());
		sb.append("     OA: " + Integer.toString(Utils.getNodesByTypes(graph, "OA").size()));
		sb.append(System.lineSeparator());
		sb.append("     O: " + Integer.toString(Utils.getNodesByTypes(graph, "O").size()));
		sb.append(System.lineSeparator());
		sb.append("     PC: " + Integer.toString(Utils.getNodesByTypes(graph, "PC").size()));
		sb.append(System.lineSeparator());

		return sb.toString();
	}

	private void checkOrCreateFolder() {
		File theDir = new File(folderName);
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
	}

	private void getExistentCollegeLetters(Set<String> currentColleges) {
		for (String s : currentColleges) {
			String[] splitted = s.split(" ");
			existentCollegeList.add(splitted[1]);
		}
	}

	private void generateRandomCollegeLetters() throws PMException {
		Random r = new Random();
		char c0 = 'c';
		while (collegeLettersList.size() < numberOfColleges - existentCollegeList.size()) {
			char c1 = (char) (r.nextInt(26) + 'a');
			char c2 = (char) (r.nextInt(26) + 'a');
			char c3 = (char) (r.nextInt(26) + 'a');
			char[] chars = { c0, c1, c2, c3 };
			String str = new String(chars).toUpperCase();
			if (collegeLettersList.contains(str) || existentCollegeList.contains(str)) {
				continue;
			}
			collegeLettersList.add(str);
		}
	}

	private void generateRandomDepartmentLetters() throws PMException {
		collegeDepartmentList = new ArrayList<String>();
		Random r = new Random();
		char c0 = 'd';
		while (collegeDepartmentList.size() < numberOfDepartments) {
			char c1 = (char) (r.nextInt(26) + 'a');
			char c2 = (char) (r.nextInt(26) + 'a');
			char c3 = (char) (r.nextInt(26) + 'a');
			char[] chars = { c0, c1, c2, c3 };
			String str = new String(chars).toUpperCase();

			if (academicGeneratedGraph.exists("BM" + str)) {
				continue;
			}
			collegeDepartmentList.add(str);
		}
	}

	private void generateNewGraph(Graph initialGraph) throws PMException {
		GraphSerializer.fromJson(academicGeneratedGraph, GraphSerializer.toJson(initialGraph));
		generateRandomCollegeLetters();
		generateAllNodes();
	}

	private void generateAllNodes() throws PMException {
		for (String college : collegeLettersList) {
			academicGeneratedGraph.createNode("Dean " + college, UA, null, "AcademicUnitsPolicyClass");
			academicGeneratedGraph.createNode("Dean" + college + "User", U, null, "Dean " + college);
			generateRandomDepartmentLetters();
			for (String department : collegeDepartmentList) {
				academicGeneratedGraph.createNode("BM" + department, UA, null, "Dean " + college);
				academicGeneratedGraph.createNode("bm" + department + "User", U, null, "BM" + department);
				academicGeneratedGraph.createNode("Chair" + department, UA, null, "BM" + department);
				academicGeneratedGraph.createNode("Chair" + department + "User", U, null, "Chair" + department);
				academicGeneratedGraph.createNode("Dept" + department, UA, null, "Chair" + department);
				// GENERATE NAMES HERE
				generateNames();
				for (String name : userNames) {
					academicGeneratedGraph.createNode(name, U, null, "Dept" + department);
				}
				assignNewUsersToRandomElegibility();
			}
		}
	}

	private void generateNames() throws PMException {
		userNames = new ArrayList<String>();
		while (userNames.size() < numberOfUsers) {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			if (academicGeneratedGraph.exists(firstName) || eligibilityGeneratedGraph.exists(firstName)
					|| userNames.contains(firstName)) {
				continue;
			}
			userNames.add(firstName);
		}
		// String name = faker.name().fullName();
		// String firstName = faker.name().firstName();
		// String lastName = faker.name().lastName();
		// String streetAddress = faker.address().streetAddress();
	}

	private void assignNewUsersToRandomElegibility() throws PMException {
		String[] facultyTypes = { "TenureTrack Faculty", "Tenured Faculty", "Adjunct Faculty", "Research Faculty",
				"Clinic Faculty", "Teaching Faculty" };
		Random rand = new Random();
		for (String name : userNames) {
			int random_index = rand.nextInt(facultyTypes.length);
			eligibilityGeneratedGraph.createNode(name, U, null, facultyTypes[random_index]);
		}
	}
}
