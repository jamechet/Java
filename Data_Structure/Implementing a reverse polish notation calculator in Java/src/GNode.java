

public class GNode<T> {
	
	public GNode<T> next = null;
	public GNode<T> previous = null;
	private T obj = null;

	public GNode(GNode<T> next_, GNode<T> prev_, T object_) {
		this.next = next_;
		this.previous = prev_;
		this.obj = object_;
	}

	public T getObject() {
		return obj;
	}
	
	public String toString() {
		if ( obj == null ) 
			return "Node has no object assigned.";
		else
			return "Node contains: " + obj.toString();	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GNode))
			return false;
		GNode other = (GNode) obj;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}
}
