package POMA.Mutation.EquivalentMutantAnalyzer;

public class AccessRequest {
	private String sa;
	private String ar;
	private String ta;
	
	public AccessRequest (String sa, String ar, String ta) {
		this.sa = sa;
		this.ar = ar;
		this.ta = ta;
	}
	
//	public AccessRequest (String ar, String oa) {
//		this.ar = ar;
//		this.oa = oa;
//	}
	
	public String getSA () {
		return this.sa;
	}
	
	public String getAR () {
		return this.ar;
	}
	
	public String getTA () {
		return this.ta;
	}
}
