package project.POMA;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.MemGraph;

import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.U;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.O;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.OA;
public class SimpleTestGraph {

	public Graph ngacGraph;

	public Graph buildSimpleGraph() throws PMException {
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

		return ngacGraph;
	}
	
	
	
	
}
