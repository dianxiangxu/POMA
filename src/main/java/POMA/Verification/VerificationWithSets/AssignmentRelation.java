package POMA.Verification.VerificationWithSets;


public class AssignmentRelation
{
	String child;
	String parent;
	
	
	public AssignmentRelation(String child,String parent) {
		this.child = child;
		this.parent = parent;
		
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
	          
	        return child.equals(tuple.child)
	                &&parent.equals(tuple.parent);
	 }
	 
	 @Override
	    public String toString() { 
		 
		 return "(mkTuple \"" + child + "\" \"" +parent+"\")";
	 }
}