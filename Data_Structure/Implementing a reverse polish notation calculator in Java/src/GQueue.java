

/**
 * The GQueue interface for Data Structures Assignment #1:
 * 	Reverse Polish Notation calculator. 
 * 
 * @author simont
 * Jan, 2014. 
 * @param  The type of object stored in the GQueue. 
 */
public abstract class GQueue<T> implements GCollection<T> {
	
	protected GList<T> list;

	/**
	 * Inserts the given object into the GQueue if possible. 
	 * @param t
	 * @return True if the object was inserted. 
	 * 
	 * @throws NullPointerException if the given object is null.
	 */
	public abstract boolean offer(T t);
	
	/**
	 * Removes and returns the head of the GQueue. 
	 * @return The head of the GQueue. 
	 */
	public abstract T poll();
	
	/**
	 * Retrieves, but does not remove, the head of this GQueue. 
	 * If the GQueue is empty, returns null. 
	 * @return The head of the GQueue. 
	 */
	public abstract T peek();
	
	@Override
	public abstract String toString();
}
