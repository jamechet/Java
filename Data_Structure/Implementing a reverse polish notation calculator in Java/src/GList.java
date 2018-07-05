

/**
 * @author simont
 *
 * @param <T>
 */
public interface GList<T> extends GCollection<T> {

	/**
	 * Inserts the specified element at the specified position in this list. 
	 *  Shifts the element currently at that position (if any) and any subsequent 
	 *  elements to the right (adds one to their indices).
	 * @param index Index at which to add
	 * @param obj The object to add
	 * @return True if insertion was successful
	 * 
	 * @throws NullPointerException if the given object is null
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	public boolean add(int index, T obj);
	
	/**
	 * Returns true iff the given object is contained in the list. 
	 * 
	 * @param obj The object whose presence is to be tested
	 * @return True if the list contains the given object
	 * 
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean contains(T obj);
	
	/**
	 * Remove the first instance of the given object from the list, if it exists
	 * @param obj The object to remove
	 * @return True if the object was removed 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean remove(T obj);
	
	/**
	 * Remove the object at the specified index from the list, if it exists. 
	 * @param index The index to remove
	 * @return The object previously at the specified index
	 *	
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
	public T remove(int index);
	
	/**
	 * Get the object at the specified index, if it exists. 
	 * @param index The index to retrieve 
	 * @return The object at the specified index, if it exists. 
	 * 
	 * @throws IndexOutOfBoundsException if the specified index is out of bounds
	 */
	public T get(int index);

	
	/**
	 * Returns the first index of the specified object, or -1 if the object does not exist
	 * in the list. 
	 * @param object
	 * @return The index of the specified object, or -1 if it is not contained in the list. 
	 */
	public int indexOf(T object);
}
