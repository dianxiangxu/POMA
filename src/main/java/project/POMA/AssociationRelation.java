package project.POMA;

import java.util.Iterator;

import gov.nist.csd.pm.operations.OperationSet;

public class AssociationRelation {
	String UA;
	String AT;
	OperationSet os;
	String name;

	public AssociationRelation(String UA, OperationSet os, String AT) {
		this.UA = UA;
		this.AT = AT;
		this.os = os;
		name = (UA + "_" + AT).toLowerCase();
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

	private String getOSCVC4AssertionsMembership() {
		StringBuilder sb = new StringBuilder();
		for (Iterator<String> iterator = os.iterator(); iterator.hasNext();) {
			String ar = iterator.next();
			if (!iterator.hasNext()) {
				sb.append("(assert (member \"" + ar + "\" access_for_" + name + "))");
			}
			else {
				sb.append("(assert (member \"" + ar + "\" access_for_" + name + "))" + System.lineSeparator());
			}
		}
		return sb.toString();
	}

	private String getOSCVC4AssertionCardinality() {
		return "(assert (= size_access_for_" + name + " (card access_for_" + name + ")))";
	}

	private String getAssociationCVC4Def() {
		return "(declare-const " + name + " association)";
	}

	private String getAssociationCVC4Assertion() {
		return "(assert (and (= (UA " + name + ") \"" + UA + "\") (= (access_rights " + name + ") access_for_" + name
				+ ") (= (AT " + name + ") \"" + AT + "\")))";
	}

	@Override
	public String toString() {

		return getOSCVC4Def() + System.lineSeparator() + getOSSizeCVC4Def() + System.lineSeparator()
				+ getOSSizeCVC4Assertion() + System.lineSeparator() + getOSCVC4AssertionsMembership()
				+ System.lineSeparator() + getOSCVC4AssertionCardinality() + System.lineSeparator()
				+ getAssociationCVC4Def() + System.lineSeparator() + getAssociationCVC4Assertion()
				+ System.lineSeparator();
	}
}