package CaseStudies.LawUseCase;


	import gov.nist.csd.pm.exceptions.PMException;
	import gov.nist.csd.pm.operations.OperationSet;
	import gov.nist.csd.pm.pdp.decider.PReviewDecider;
	import gov.nist.csd.pm.pip.graph.Graph;
	import gov.nist.csd.pm.pip.graph.GraphSerializer;
	import gov.nist.csd.pm.pip.graph.MemGraph;
	import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
	import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
	import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
	import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

	import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.*;
import static org.junit.Assert.assertTrue;

import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Map;

	public class SimplifiedLawUseCaseGenerator {

		public static void main(String[] argv) throws PMException, IOException {


			Graph casePolicy = new MemGraph(); 	  
			  
			casePolicy.createPolicyClass("CasePolicy", null);

			casePolicy.createNode("AttorneysMain", UA, null, "CasePolicy");
			casePolicy.createNode("Attorneys", UA, null, "AttorneysMain");
			casePolicy.createNode("Case3", OA, null, "CasePolicy");

			casePolicy.createNode("Case3Info", OA, null, "Case3");
			casePolicy.createNode("LeadAttorneys", UA, null, "CasePolicy", "Attorneys");
			casePolicy.createNode("Attorneys1", UA, null, "CasePolicy");
			casePolicy.createNode("Attorneys2", UA, null, "CasePolicy");
			casePolicy.createNode("Attorneys3", UA, null, "CasePolicy");
			casePolicy.createNode("LeadAttorneysU", UA, null, "LeadAttorneys");


			casePolicy.associate("Attorneys", "Case3", new OperationSet("accept", "refuse"));
			casePolicy.associate("LeadAttorneys", "Case3", new OperationSet("disapprove", "withdraw"));
			casePolicy.associate("AttorneysMain", "Case3", new OperationSet("accept"));

			saveDataToFile(GraphSerializer.toJson(casePolicy), "Policies/ForBMC/LawFirmSimplified/CasePolicy.json");					
		}

		
		private static void saveDataToFile(String code, String path) throws IOException {
			File file = new File(path);
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(code);
			myWriter.close();
		}
		
	}


