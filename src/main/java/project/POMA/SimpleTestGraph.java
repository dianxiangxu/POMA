package project.POMA;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.O;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
public class SimpleTestGraph {

	public MemGraph ngacGraph;

	public MemGraph buildSimpleGraph() throws PMException {
		ngacGraph = new MemGraph();
		
		ngacGraph.createPolicyClass("PC1", null);
		ngacGraph.createNode("UA2", UA, null, "PC1");
		ngacGraph.createNode("UA1", UA, null, "UA2");
		ngacGraph.createNode("UA3", UA, null, "PC1");
		ngacGraph.createNode("U3", U, null, "UA3");
		ngacGraph.createNode("U2", U, null, "UA2");
		ngacGraph.createNode("U1", U, null, "UA1");
		ngacGraph.createNode("OA2", OA, null, "PC1");
		ngacGraph.createNode("OA1", OA, null, "OA2");
		ngacGraph.createNode("O2", O, null, "OA2");
		ngacGraph.createNode("O1", O, null, "OA1");
		ngacGraph.associate("UA2", "OA2", new OperationSet("r","x"));
		ngacGraph.associate("UA1", "OA1", new OperationSet("w"));
		ngacGraph.associate("UA1", "UA3", new OperationSet("add"));
		ngacGraph.associate("UA1", "UA2", new OperationSet("test"));

		return ngacGraph;
	}
	
	public MemGraph buildGraphNGACExample1() throws PMException {
		ngacGraph = new MemGraph();
		
		ngacGraph.createPolicyClass("position-contraints", null);
		ngacGraph.createPolicyClass("branch-contraints", null);

		ngacGraph.createNode("teller", UA, null, "position-contraints");
		ngacGraph.createNode("branch1", UA, null, "branch-contraints");
		ngacGraph.createNode("u1", U, null, "teller", "branch1");
		ngacGraph.createNode("products", OA, null, "branch-contraints");
		ngacGraph.createNode("products1", OA, null, "products");
		ngacGraph.createNode("products2", OA, null, "products");
		ngacGraph.createNode("assets", OA, null, "position-contraints");
		ngacGraph.createNode("accounts", OA, null, "assets");
		ngacGraph.createNode("loans", OA, null, "assets");
		ngacGraph.createNode("loans2", OA, null, "loans", "products2");
		ngacGraph.createNode("accounts2", OA, null, "accounts", "products2");
		ngacGraph.createNode("a21", O, null, "accounts2");
		ngacGraph.createNode("accounts1", OA, null, "accounts", "products1");
		ngacGraph.createNode("a11", O, null, "accounts1");
		ngacGraph.createNode("loans1", OA, null, "loans", "products1");
		ngacGraph.createNode("l11", O, null, "loans1");
		ngacGraph.createNode("l12", O, null, "loans1");

		ngacGraph.associate("branch1", "products1", new OperationSet("r","w"));
		ngacGraph.associate("teller", "accounts", new OperationSet("r","w"));
		return ngacGraph;
	}
	
	public MemGraph readAnyGraph(String path) throws PMException, IOException {
		File file_eligibility_policy = new File(path);
		File file_superPolicy = new File("Graphs/super_config.json");
		File file_org = new File("Graphs/AcademicUnitsPolicyClass.json");
		File file_adm = new File("Graphs/AdministrationUnitsPolicyClass.json");

		File editingFile = new File("Graphs/EditingPolicyClass.json");

		String superPolicy = new String(
				Files.readAllBytes(Paths.get(file_superPolicy.getAbsolutePath())));
		String eligibility_policy = new String(
				Files.readAllBytes(Paths.get(file_eligibility_policy.getAbsolutePath())));
		
		String org_policy = new String(
				Files.readAllBytes(Paths.get(file_org.getAbsolutePath())));
		String adm_policy = new String(
				Files.readAllBytes(Paths.get(file_adm.getAbsolutePath())));
		String editing_policy = new String(
				Files.readAllBytes(Paths.get(editingFile.getAbsolutePath())));
		ngacGraph = new MemGraph();
		//GraphSerializer.fromJson(ngacGraph, superPolicy);

		GraphSerializer.fromJson(ngacGraph, eligibility_policy);
		
		//GraphSerializer.fromJson(ngacGraph, org_policy);
		//GraphSerializer.fromJson(ngacGraph, adm_policy);
		
		//GraphSerializer.fromJson(ngacGraph, editing_policy);

		
		
		return ngacGraph;
	}

}
