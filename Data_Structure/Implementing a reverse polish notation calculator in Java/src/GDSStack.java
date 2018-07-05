/**
 * 
 */


/**
 * @author simont
 *
 */
public class GDSStack<T> extends GStack<T> {
	GDSLinkedList<T> a= new GDSLinkedList<T>();	
	/* (non-Javadoc)
	 * @see Generics.GCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T obj) {
		// TODO Auto-generated method stub
		return a.add(obj);
	}

	/* (non-Javadoc)
	 * @see Generics.GCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return a.isEmpty();
	}

	/* (non-Javadoc)
	 * @see Generics.GCollection#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return a.size();
	}

	/* (non-Javadoc)
	 * @see Generics.GStack#empty()
	 */
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return a.isEmpty();
	}

	/* (non-Javadoc)
	 * @see Generics.GStack#peek()
	 */
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return a.get(size());
	}

	/* (non-Javadoc)
	 * @see Generics.GStack#pop()
	 */
	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return a.remove(a.size());
	}

	/* (non-Javadoc)
	 * @see Generics.GStack#push(java.lang.Object)
	 */
	@Override
	public T push(T obj) {
		if(obj !=null){
			a.add(obj);
			return obj;
		}
		else
				
			return null;
	}

	/* (non-Javadoc)
	 * @see Generics.GStack#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a.toString();
	}
	public boolean equals(Object other) {
		
		return other.hashCode()==a.hashCode();
	}

	@Override
	public int hashCode() {
		return a.hashCode();
	}

}
