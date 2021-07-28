package POMA.Mutation.EquivalentMutantAnalyzer;

import java.util.List;
import java.util.Set;

//Association With Policy class
public class AssociationList {
	private List<AWP> dal;
	private List<AWP> ndal;
	
	public AssociationList (List<AWP> dal, List<AWP> ndal) {
		this.dal = dal;
		this.ndal = ndal;
	}
	
//	public AccessRequest (String ar, String oa) {
//		this.ar = ar;
//		this.oa = oa;
//	}
	
	public void setDal (List<AWP> dal) {
		this.dal = dal;
	}
	
	public void setNdal (List<AWP> ndal) {
		this.ndal = ndal;
	}

	public List<AWP> getDal () {
		return this.dal;
	}
	
	public List<AWP> getNdal () {
		return this.ndal;
	}
}
