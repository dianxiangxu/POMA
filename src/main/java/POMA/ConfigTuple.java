package POMA;

import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class ConfigTuple {

	private MemGraph graph;
	private Prohibitions prohibitions;

	public Prohibitions getProhibitions() {
		return prohibitions;
	}

	public void setProhibitions(Prohibitions prohibitions) {
		this.prohibitions = prohibitions;
	}

	public MemGraph getGraph() {
		return graph;
	}

	public void setGraph(MemGraph graph) {
		this.graph = graph;
	}

	
	public ConfigTuple(){	
		
	}
	
	
}
