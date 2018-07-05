/**
 * The Stack interface for Data Structures Assignment #1: 
 * 	Reverse Polish Notation calculator. 
 * 
 * @author simont
 * Jan, 2014
 * @param  The type of object stored in this Stack. 
 */
public abstract class Stack implements Collection{

	protected List list;
	
	/**
	 * Tests if the Stack is empty. 
	 * @return True if the Stack is empty. 
	 */
	public abstract boolean empty();
	
	/**
	 * Returns, without removing, the object at the top of the Stack. 
	 * @return the object at the top of the Stack. 
	 * 
	 * @throws EmptyStackException if the stack is empty. 
	 */
	public abstract Token peek();
	/**
	 * Returns and removes the object at the top of the Stack. 
	 * @return the object at the top of the Stack. 
	 * 
	 * @throws EmptyStackException if the stack is empty.
	 */
	public abstract Token pop();
	/**
	 * Adds the given object to the top of the Stack. 
	 * @return The given object. 
	 */
	public abstract Token push(Token obj);
	
	public abstract String toString();
}
