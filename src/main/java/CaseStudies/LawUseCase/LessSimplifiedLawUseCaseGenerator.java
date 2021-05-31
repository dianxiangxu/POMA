package CaseStudies.LawUseCase;


	import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.O;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;

	public class LessSimplifiedLawUseCaseGenerator {

		public static void main(String[] argv) throws PMException, IOException {


			Graph casePolicy = new MemGraph(); 	  
			  
			casePolicy.createPolicyClass("CasePolicy", null);

			casePolicy.createNode("Attorneys", UA, null, "CasePolicy");
			casePolicy.createNode("LeadAttorneys", UA, null, "Attorneys");
			casePolicy.createNode("C-Suit", UA, null, "LeadAttorneys");
			casePolicy.createNode("LA1", U, null, "LeadAttorneys");
			casePolicy.createNode("A1", U, null, "Attorneys");
			casePolicy.createNode("C1", U, null, "C-Suit");

			casePolicy.createNode("Case3", OA, null, "CasePolicy");
			casePolicy.createNode("Apple", O, null, "Case3");
			casePolicy.createNode("Google", O, null, "Case3");
			casePolicy.createNode("Alice", O, null, "Case3");

			casePolicy.associate("Attorneys", "Case3", new OperationSet("accept", "refuse"));
			casePolicy.associate("LeadAttorneys", "Case3", new OperationSet("disapprove", "withdraw"));

			saveDataToFile(GraphSerializer.toJson(casePolicy), "Policies/ForBMC/LawFirmSimplified/CasePolicyLess.json");					
		}

		
		private static void saveDataToFile(String code, String path) throws IOException {
			File file = new File(path);
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(code);
			myWriter.close();
		}
		
	}


