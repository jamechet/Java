

/**
 * The GStack interface for Data Structures Assignment #1: 
 * 	Reverse Polish Notation calculator. 
 * 
 * @author simont
 * Jan, 2014
 * @param  The type of object stored in this GStack. 
 */
public abstract class GStack<T> implements GCollection<T>{

	protected GList<T> list;
	
	/**
	 * Tests if the GStack is empty. 
	 * @return True if the GStack is empty. 
	 */
	public abstract boolean empty();
	
	/**
	 * Returns, without removing, the object at the top of the GStack. 
	 * @return the object at the top of the GStack. 
	 * 
	 * @throws EmptyGStackException if the stack is empty. 
	 */
	public abstract T peek();
	/**
	 * Returns and removes the object at the top of the GStack. 
	 * @return the object at the top of the GStack. 
	 * 
	 * @throws EmptyGStackException if the stack is empty.
	 */
	public abstract T pop();
	/**
	 * Adds the given object to the top of the GStack. 
	 * @return The given object. 
	 */
	public abstract T push(T obj);
	
	public abstract String toString();
}
