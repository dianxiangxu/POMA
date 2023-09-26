package POMA.Verification.Translator;

import POMA.Verification.ReachabilityAnalysis.AssignmentRelation;
import gov.nist.csd.pm.operations.OperationSet;

public class AssociationRelation {
	String UA;
	String AT;
	OperationSet os = new OperationSet();
	String name;

	public AssociationRelation(String UA, OperationSet os, String AT) {
		this.UA = UA;
		this.AT = AT;
		this.os = os;
		name = (UA + "_" + AT).toLowerCase();
	}
	public AssociationRelation() {

	}
	public void setUA(String UA) {
		this.UA = UA;
	}
	public void setAT(String AT) {
		this.AT = AT;
	}
	public String getUA() {
		return UA;
	}

	public String getAT() {
		return AT;
	}

	public OperationSet getOperationSet() {
		return os;
	}
	public void addToOperationSet(String accessRight) {
		os.add(accessRight);
	}
	public void addToOperationSet(OperationSet accessRights) {
		os.addAll(accessRights);
	}
	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		if (!(o instanceof AssignmentRelation)) {
			return false;
		}

		AssociationRelation tuple = (AssociationRelation) o;

		return UA.equals(tuple.UA) && AT.equals(tuple.AT);
	}

	private String getOSCVC4Def() {
		return "(declare-fun access_for_" + name + " () (Set String))";
	}

	private String getOSSizeCVC4Def() {
		return "(declare-fun size_access_for_" + name + " () Int)";
	}

	private String getOSSizeCVC4Assertion() {
		return "(assert (= size_access_for_" + name + " " + os.size() + "))";
	}





	public String toStringOutput() {
		return UA + " " + os + " " + AT;
	}




	

	@Override
	public String toString() {
		String toReturn = "";
		for(String ar : os) {
			toReturn += "(mkTuple \"" + UA + "\" \"" +ar+"\" \""+AT+"\")";
		}
		return toReturn;
	}
}