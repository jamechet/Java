
public class Node {
	
	public Node next = null;
	public Node previous = null;
	private Token obj = null;

	public Node(Node next_, Node prev_, Token token_) {
		this.next = next_;
		this.previous = prev_;
		this.obj = token_;
	}

	public Token getObject() {
		return obj;
	}
	
	public String toString() {
		
		if ( obj == null ) 
			return "Node has no token assigned.";
		else
			return "Node contains: " + obj.toString();	
	}
	
	@Override
	public boolean equals(Object other) {
	    if (this == other)
	        return true;
	    if (other == null)
	        return false;
	    if (!(other instanceof Node))
	        return false;
		
	    return obj.equals(((Node)other).getObject());
	}
	
	@Override
	public int hashCode() {
		if ( obj == null )
			return 0;
		return obj.hashCode();
	}
}
