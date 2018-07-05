/**
 * 
 */


/**
 * @author simont
 *
 */
public class GDSQueue<T> extends GQueue<T> {
	
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
		return a.size();//
	}
	/* (non-Javadoc)
	 * @see Generics.GQueue#offer(java.lang.Object)
	 */
	@Override
	public boolean offer(T t) {
		// TODO Auto-generated method stub
		return a.add(t);
	}
	/* (non-Javadoc)
	 * @see Generics.GQueue#poll()
	 */
	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return a.remove(a.size()-a.size());
	}
	/* (non-Javadoc)
	 * @see Generics.GQueue#peek()
	 */
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return a.get(a.size()-a.size());
	}
	/* (non-Javadoc)
	 * @see Generics.GQueue#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return a.toString();
	}
	public boolean equals(Object other) {
		return a.hashCode() == other.hashCode();
	}
	@Override
	public int hashCode() {
		return a.hashCode();
	}
	

}
