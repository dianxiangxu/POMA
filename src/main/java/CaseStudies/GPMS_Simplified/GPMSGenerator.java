package CaseStudies.GPMS_Simplified;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;


	public class GPMSGenerator {

		public static void main(String[] argv) throws PMException, IOException {
			Graph editingPolicy = new MemGraph(); 	  
	  
			editingPolicy.createPolicyClass("EditingPolicy", null);

			editingPolicy.createNode("PI", UA, null, "EditingPolicy");
			editingPolicy.createNode("CoPI", UA, null, "EditingPolicy");
			editingPolicy.createNode("SP", UA, null, "EditingPolicy");
			editingPolicy.createNode("Chair", UA, null, "EditingPolicy");
			editingPolicy.createNode("BM", UA, null, "EditingPolicy");
			editingPolicy.createNode("Dean", UA, null, "EditingPolicy");
			editingPolicy.createNode("RA", UA, null, "EditingPolicy");
			editingPolicy.createNode("RD", UA, null, "EditingPolicy");

			editingPolicy.createNode("UChair", UA, null, "EditingPolicy");
			editingPolicy.createNode("UBM", UA, null, "EditingPolicy");
			editingPolicy.createNode("UDean", UA, null, "EditingPolicy");
			editingPolicy.createNode("URA", UA, null, "EditingPolicy");
			editingPolicy.createNode("URD", UA, null, "EditingPolicy");
			editingPolicy.createNode("PDSWhole", OA, null, "EditingPolicy");
			editingPolicy.createNode("PDSEditing", OA, null, "EditingPolicy");

			editingPolicy.createNode("Vlad", U, null, "PI");			
			editingPolicy.createNode("Nazmul", U, null, "CoPI");

			editingPolicy.associate("PI", "PDSWhole", new OperationSet("submit"));
			editingPolicy.associate("CoPI", "PDSWhole", new OperationSet("edit"));

			saveDataToFile(GraphSerializer.toJson(editingPolicy), "Policies/ForBMC/GPMSSimplified/EditingPolicy.json");	
		}

		
		private static void saveDataToFile(String code, String path) throws IOException {
			File file = new File(path);
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(code);
			myWriter.close();
		}
		
	}


