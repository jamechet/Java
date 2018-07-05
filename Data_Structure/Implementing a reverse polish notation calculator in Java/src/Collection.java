
public interface Collection {
	
	
	/**
	 * Ensures that the collection contains the specified object. 
	 * @param obj The object to add. 
	 * @return True if the object has been added to the collection. 
	 * 
	 * @throws NullPointerException if the specified object is null
	 */
	public boolean add(Token obj);
	
	
	/**
	 * Returns true if this collection contains no elements. 
	 * @return True if the collection is empty. 
	 */
	public boolean isEmpty();
	
	
	/**
	 * Returns the number of elements in this collection. 
	 * @return The number of elements in this collection. 
	 */
	public int size();
	
	/**
	 * Returns a string containing the toString() 
	 * 	for each object in this collection. 
	 * @return The concatenated toString() for each element in this collection
	 */
	@Override
	public String toString();
	
	/**
	 * Compares this collection with the specified object for equality. 
	 * The equality comparison must be value-based rather than the default 
	 * 	(reference based). 
	 * 
	 * @param obj The object to compare against. 
	 * @return True if the specified object is value-comparatively equal to this collection
	 */
	@Override
	public boolean equals(Object obj);
	
	/**
	 * Returns the hashCode for this collection. 
	 * (This method must satisfy the constraint that if Collection c1.equals(Collection c2), 
	 * 	then c1.hashCode() == c2.hashCode() must also be true. 
	 * @return The hashCode of this collection. 
	 */
	@Override
	public int hashCode();
}
