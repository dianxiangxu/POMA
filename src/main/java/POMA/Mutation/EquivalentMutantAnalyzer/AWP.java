package POMA.Mutation.EquivalentMutantAnalyzer;

import java.util.List;
import java.util.Set;

//Association With Policy class
public class AWP {
	private String sourceAttribute;
	private Set<String> operationSet;
	private String targetAttribute;
	private List<String> policyClass;
	
	public AWP (String sa, Set<String> op, String ta, List<String> pc) {
		this.sourceAttribute = sa;
		this.operationSet = op;
		this.targetAttribute = ta;
		this.policyClass = pc;
	}
	
//	public AccessRequest (String ar, String oa) {
//		this.ar = ar;
//		this.oa = oa;
//	}
	
	public void setSA (String sa) {
		this.sourceAttribute = sa;
	}
	
	public void setOP (Set<String> ars) {
		this.operationSet = ars;
	}
	
	public void setPC (List<String> pcs) {
		this.policyClass = pcs;
	}
	
	public void setTA (String ta) {
		this.targetAttribute = ta;
	}
	
	public String getSA () {
		return this.sourceAttribute;
	}
	
	public Set<String> getOP () {
		return this.operationSet;
	}
	
	public String getTA () {
		return this.targetAttribute;
	}
	
	public List<String> getPC () {
		return this.policyClass;
	}

}
