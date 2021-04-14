package POMA.Verification.TranslationWithSets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.GlobalVariables;
import POMA.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pdp.decider.PReviewDecider;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class Translator {
	private ArrayList<AssociationRelation> listOfPriveleges = new ArrayList<AssociationRelation>();
	private String actualOutput = "";
	private ArrayList<String> accessRightsResults;
	private String fullTranslation = "";

	public String translateGraphOnly(Graph graph) throws Exception {
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();
		return translatedGraph;
	}

	public String queryAccessRightsEach(Graph graph, String queries, Prohibitions prohibitions) throws Exception {
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();
		String[] lines = queries.split("\\r?\\n");
		List<String[]> inputArray = new ArrayList<String[]>();
		for (String line : lines) {
			inputArray.add(line.split("\\s*,\\s*"));
		}

		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandAT(inputArray);
		setFullTranslation(translatedGraph);

		saveDataToFile(translatedGraph, GlobalVariables.swapFile);
		CVC4Runner runner = new CVC4Runner();
		List<String> output = runner.runFromSMTLIB2SetsTheoryIncremental(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		setActualOutput(runner.getFullOutput());
		return runner.getFullOutput();
	}

	public String queryAccessRights(Graph graph, String queries, Prohibitions prohibitions) throws Exception {
		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();

		String[] lines = queries.split("\\r?\\n");
		List<String[]> inputArray = new ArrayList<String[]>();
		for (String line : lines) {
			inputArray.add(line.split("\\s*,\\s*"));
		}

		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandATAllComb(inputArray);

		translatedGraph += System.lineSeparator();
		translatedGraph += translator.translateAssociationsNoUA();
		translatedGraph += System.lineSeparator();

		translatedGraph += System.lineSeparator();
		translatedGraph += CVC4Translator.getFinalCheck(prohibitions);

		setFullTranslation(translatedGraph);
		
		saveDataToFile(translatedGraph, GlobalVariables.swapFile);

		CVC4Runner runner = new CVC4Runner();

		List<String> output = runner.runFromSMTLIB2SetsTheory(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		setActualOutput(runner.getFullOutput());

		return output.toString();
	}

	public String getAllAccessRights(Graph graph, Prohibitions prohibitions) throws Exception {
		List<String> UA_U = Utils.getNodesByTypes(graph, "UA", "U");
		List<String> U_UA_O_OA = Utils.getNodesByTypes(graph, "OA", "O", "U", "UA");

		CVC4Translator translator = new CVC4Translator(graph);
		translator.initTranslation();
		String translatedGraph = translator.getTranslatedGraph();
		translatedGraph += System.lineSeparator();

		List<String[]> inputArray = new ArrayList<String[]>();
		for (String ua_u : UA_U) {
			for (String oa_o : U_UA_O_OA) {
				inputArray.add(new String[] { ua_u, oa_o });
			}
		}

		translatedGraph += CVC4Translator.getAllAccessRightsCheckInSetOfUAandATAllComb(inputArray);
		translatedGraph += System.lineSeparator();
		translatedGraph += translator.translateAssociationsNoUA();
		translatedGraph += System.lineSeparator();

		translatedGraph += System.lineSeparator();
		translatedGraph += CVC4Translator.getFinalCheck(prohibitions);

		setFullTranslation(translatedGraph);
		saveDataToFile(translatedGraph, GlobalVariables.swapFile);

		CVC4Runner runner = new CVC4Runner();

		List<String> output = runner.runFromSMTLIB2SetsTheory(GlobalVariables.swapFile);
		setAccessRightsResults(processOutput(output, graph));
		AccessRightsVerifier.testAccessRights(listOfPriveleges);
		String result = "";
		for (String s : output) {
			result += s;
			result += System.lineSeparator();
		}
		setActualOutput(runner.getFullOutput());
		return result;
	}

	public String getActualOutput() {
		return actualOutput.replace(",", "");
	}

	private void setActualOutput(String actualOutput) {
		this.actualOutput = actualOutput;
	}

	private ArrayList<String> processOutput(List<String> output, Graph graph) throws PMException {
		int i = 1;
		actualOutput = "";
		listOfPriveleges = new ArrayList<AssociationRelation>();

		PReviewDecider decider = new PReviewDecider(graph);
		// System.out.println("IMPORTANT ACCESS RIGHT CHECK!: "+decider.list("UA1", "",
		// "UA1"));
		AssociationRelation ar = null;
		for (String s : output) {
			if (!s.contains("sat") && !s.contains("error")) {
				for (String st : s.split("\"")) {
					if (st.contains("(") || st.contains(")") || st.trim().isEmpty()) {
						continue;
					}
					if (i == 1) {
						ar = new AssociationRelation();
						ar.setUA(st);
					}
					if (i == 2)
						ar.addToOperationSet(st);
					if (i == 3) {
						ar.setAT(st);
						// if(ar.getUA().equals(ar.getAT())) {
						// i=1;
						// //||graph.getParents(ar.getUA()).contains(ar.getAT())||graph.getParents(ar.getAT()).contains(ar.getUA())
						// continue;
						// }
						updateAssociationRelations(ar);
						i = 1;
						continue;
					}
					i++;
				}
			} else {
				actualOutput += s;
				actualOutput += System.lineSeparator();
			}
		}
		// System.out.println(listOfAssociations);
		return convertAssociationsToString(listOfPriveleges);
	}

	private ArrayList<String> convertAssociationsToString(ArrayList<AssociationRelation> listOfAssociations) {
		ArrayList<String> list = new ArrayList<String>();
		for (AssociationRelation ar : listOfAssociations) {
			list.add(ar.toStringOutput());
		}
		return list;
	}

	private void updateAssociationRelations(AssociationRelation associationRelation) {
		if (listOfPriveleges.isEmpty()) {
			listOfPriveleges.add(associationRelation);
			return;
		}
		for (int i = 0; i < listOfPriveleges.size(); i++) {
			AssociationRelation ar = listOfPriveleges.get(i);
			if (ar.getUA().equals(associationRelation.getUA()) && ar.getAT().equals(associationRelation.getAT())) {
				ar.addToOperationSet(associationRelation.getOperationSet());
				return;
			}
		}
		listOfPriveleges.add(associationRelation);

	}

	public String printAccessRightsForUAandATPair(String UA, String AT) {

		return "";
	}

	public static void main(String[] args) throws Exception {

//		String simpleGraphPath = "GPMSPolicies/simpleGraphToSMT.json";		 
//		Translator applet = new Translator();
//		Graph graph = Utils.readAnyGraph(simpleGraphPath);

		
	}

	private static void saveDataToFile(String data, String path) throws PMException, IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(data);
		myWriter.close();

	}

	public String getAccessRightsResults() {
		String output = "";
		for (String s : accessRightsResults) {
			output += s;
			output += System.lineSeparator();
		}
		return output;
	}

	public void setAccessRightsResults(ArrayList<String> accessRightsResults) {
		this.accessRightsResults = accessRightsResults;
	}

	public String getFullTranslation() {
		return fullTranslation;
	}

	public void setFullTranslation(String fullTranslation) {
		this.fullTranslation = fullTranslation;
	}

	public void setAccessRightsResults() {
		this.accessRightsResults = new ArrayList<String>();
	}
}
