package POMA;

import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class ConfigTuple {

	private MemGraph graph;
	private Prohibitions prohibitions;
	private Obligation obligations;
	private String customFunctionSpecificationPath = "";
	private String preproperty = "";
	private String postproperty = "";
	private String obligationPath = "";

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
	
	public String getCustomFunctionSpecificationPath() {
		return customFunctionSpecificationPath;
	}

	public void setCustomFunctionSpecificationPath(String customFunctionSpecificationPath) {
		this.customFunctionSpecificationPath = customFunctionSpecificationPath;
	}
	
	public String getPreproperty() {
		return preproperty;
	}

	public void setPreproperty(String preproperty) {
		this.preproperty = preproperty;
	}

	public String getPostproperty() {
		return postproperty;
	}

	public void setPostproperty(String postproperty) {
		this.postproperty = postproperty;
	}
	
	public String getObligationPath() {
		return obligationPath;
	}

	public void setObligationPath(String obligationPath) {
		this.obligationPath = obligationPath;
	}

	public ConfigTuple(){	
		
	}
	
	
}
