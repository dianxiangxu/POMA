package project.POMA;

import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.MemGraph;
import static gov.nist.csd.pm.pip.graph.model.nodes.NodeType.UA;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws PMException
    {
        Graph graph = new MemGraph();
        graph.createPolicyClass("PC1", null);
        graph.createNode("UA1", UA, null, "PC1");
        
        for(String child :  graph.getChildren("PC1"))
        System.out.println(child);
    }
}
