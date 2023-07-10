package POMA.Verification.TranslationWithSets;

public class AssignmentRelation {
	String child;
	String parent;
	int childInt;
	int parentInt;
	public AssignmentRelation(String child, String parent) {
		this.child = child;
		this.parent = parent;

	}

	public AssignmentRelation(int child, int parent) {
		this.childInt = child;
		this.parentInt = parent;

	}
	public String getChild() {
		return child;
	}

	public String getParent() {
		return parent;
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

		AssignmentRelation tuple = (AssignmentRelation) o;

		return child.equals(tuple.child) && parent.equals(tuple.parent);
	}

	@Override
	public String toString() {

		return "(mkTuple \"" + child + "\" \"" + parent + "\")";
	}

	public String toStringNoQuotes() {

		return "(mkTuple " + child + " " + parent + ")";
	}
	
	public String toStringCVC5() {

		return "(tuple " + child + " " + parent + ")";
	}
}