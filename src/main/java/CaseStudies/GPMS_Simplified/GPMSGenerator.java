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

	public static void main(String[] argv) throws Exception {
		// gpms37Generator();
		// gpms125Generator();
		// gpms265Generator();
		// gpms500Generator();
		 gpms750Generator();
	}

	private static void gpms37Generator() throws Exception {
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

		saveDataToFile(GraphSerializer.toJson(editingPolicy), "Policies/ForBMC/GPMSSimplified/EditingPolicy37.json");
	}

	private static void gpms125Generator() throws Exception {
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
		editingPolicy.createNode("RD2", UA, null, "RD");
		editingPolicy.createNode("RD3", UA, null, "RD2");
		editingPolicy.createNode("RD4", UA, null, "RD3");
		editingPolicy.createNode("RD5", UA, null, "RD4");
		editingPolicy.createNode("RD6", UA, null, "RD5");
		editingPolicy.createNode("RD7", UA, null, "RD6");
		editingPolicy.createNode("RD8", UA, null, "RD7");
		editingPolicy.createNode("RD9", UA, null, "RD8");
		editingPolicy.createNode("RD10", UA, null, "RD9");
		editingPolicy.createNode("RD11", UA, null, "RD10");
		editingPolicy.createNode("RD12", UA, null, "RD11");

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

		saveDataToFile(GraphSerializer.toJson(editingPolicy), "Policies/ForBMC/GPMSSimplified/EditingPolicy125.json");
	}

	private static void gpms500Generator() throws Exception {
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
		editingPolicy.createNode("RD2", UA, null, "RD");
		editingPolicy.createNode("RD3", UA, null, "RD2");
		editingPolicy.createNode("RD4", UA, null, "RD3");
		editingPolicy.createNode("RD5", UA, null, "RD4");
		editingPolicy.createNode("RD6", UA, null, "RD5");
		editingPolicy.createNode("RD7", UA, null, "RD6");
		editingPolicy.createNode("RD8", UA, null, "RD7");
		editingPolicy.createNode("RD9", UA, null, "RD8");
		editingPolicy.createNode("RD10", UA, null, "RD9");
		editingPolicy.createNode("RD11", UA, null, "RD10");
		editingPolicy.createNode("RD12", UA, null, "RD11");
		editingPolicy.createNode("RD13", UA, null, "RD12");
		editingPolicy.createNode("RD14", UA, null, "RD13");
		editingPolicy.createNode("RD15", UA, null, "RD14");
		editingPolicy.createNode("RD16", UA, null, "RD15");
		editingPolicy.createNode("RD17", UA, null, "RD16");
		editingPolicy.createNode("RD18", UA, null, "RD17");
		editingPolicy.createNode("RD19", UA, null, "RD18");
		editingPolicy.createNode("RD20", UA, null, "RD19");

		editingPolicy.createNode("RD21", UA, null, "RD20");
		editingPolicy.createNode("RD22", UA, null, "RD21");
		editingPolicy.createNode("RD23", UA, null, "RD22");
		editingPolicy.createNode("RD24", UA, null, "RD23");
		editingPolicy.createNode("RD25", UA, null, "RD24");
		editingPolicy.createNode("RD26", UA, null, "RD25");
		editingPolicy.createNode("RD27", UA, null, "RD26");
		editingPolicy.createNode("RD28", UA, null, "RD27");
		editingPolicy.createNode("RD29", UA, null, "RD28");

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

		saveDataToFile(GraphSerializer.toJson(editingPolicy), "Policies/ForBMC/GPMSSimplified/EditingPolicy500.json");
	}

	private static void gpms750Generator() throws Exception {
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
		editingPolicy.createNode("RD2", UA, null, "RD");
		editingPolicy.createNode("RD3", UA, null, "RD2");
		editingPolicy.createNode("RD4", UA, null, "RD3");
		editingPolicy.createNode("RD5", UA, null, "RD4");
		editingPolicy.createNode("RD6", UA, null, "RD5");
		editingPolicy.createNode("RD7", UA, null, "RD6");
		editingPolicy.createNode("RD8", UA, null, "RD7");
		editingPolicy.createNode("RD9", UA, null, "RD8");
		editingPolicy.createNode("RD10", UA, null, "RD9");
		editingPolicy.createNode("RD11", UA, null, "RD10");
		editingPolicy.createNode("RD12", UA, null, "RD11");
		editingPolicy.createNode("RD13", UA, null, "RD12");
		editingPolicy.createNode("RD14", UA, null, "RD13");
		editingPolicy.createNode("RD15", UA, null, "RD14");
		editingPolicy.createNode("RD16", UA, null, "RD15");
		editingPolicy.createNode("RD17", UA, null, "RD16");
		editingPolicy.createNode("RD18", UA, null, "RD17");
		editingPolicy.createNode("RD19", UA, null, "RD18");
		editingPolicy.createNode("RD20", UA, null, "RD19");

		editingPolicy.createNode("RD21", UA, null, "RD20");
		editingPolicy.createNode("RD22", UA, null, "RD21");
		editingPolicy.createNode("RD23", UA, null, "RD22");
		editingPolicy.createNode("RD24", UA, null, "RD23");
		editingPolicy.createNode("RD25", UA, null, "RD24");
		editingPolicy.createNode("RD26", UA, null, "RD25");
		editingPolicy.createNode("RD27", UA, null, "RD26");
		editingPolicy.createNode("RD28", UA, null, "RD27");
		editingPolicy.createNode("RD29", UA, null, "RD28");
		editingPolicy.createNode("RD30", UA, null, "RD29");
		editingPolicy.createNode("RD31", UA, null, "RD30");
		editingPolicy.createNode("RD32", UA, null, "RD31");
		editingPolicy.createNode("RD33", UA, null, "RD32");
		editingPolicy.createNode("RD34", UA, null, "RD33");
		editingPolicy.createNode("RD35", UA, null, "RD34");
		editingPolicy.createNode("RD36", UA, null, "RD35");
		//editingPolicy.createNode("RD37", UA, null, "RD36");

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

		saveDataToFile(GraphSerializer.toJson(editingPolicy), "Policies/ForBMC/GPMSSimplified/EditingPolicy750.json");
	}

	private static void saveDataToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

}
