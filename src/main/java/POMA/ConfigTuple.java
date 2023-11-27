package POMA;

import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class ConfigTuple {

	private MemGraph graph;
	private Prohibitions prohibitions;
	private Obligation obligations;

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

	public Obligation getObligations() {
		return obligations;
	}

	public void setObligations(Obligation obligations) {
		this.obligations = obligations;
	}

	public ConfigTuple(){	
		
	}
	
	
}
