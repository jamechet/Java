import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public abstract class CollectionsTest<T extends Collection> extends DSUnitTesting {
	
	private T collection;
	private T other;
	
	protected abstract T createInstance();
	
	@Before
	public void setup() {
		collection = createInstance();
		other = createInstance();
	}
	
	@Test(expected=NullPointerException.class)
	public final void testAddNullPointer() {
		collection.add(null);
	}
	
	@Test
	public final void testIsEmpty() {
		assertTrue(collection.getClass().getName() + ".isEmpty() should return true when collection contains no elements", collection.isEmpty());
		
		collection.add(new Token(0));
		assertFalse(collection.getClass().getName() + ".isEmpty() should return false when collection contains elements", collection.isEmpty());
	}
	
	@Test
	public final void testSize() {
		assertEquals(collection.getClass().getName() + ".size() should be 0 for an empty collection.", 0, collection.size());
		
		collection.add(new Token(0));
		assertEquals(collection.getClass().getName() + ".size() should be 1 for a collection with one element.", 1, collection.size());		
	}
	
	@Test
	public final void testToStringTest() {
		assertTrue(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
			"with no trailing whitespace. Expected [], got: [" + collection.toString() + "]", collection.toString().equals(""));
		collection.add(new Token(0));
		assertTrue(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace. Expected [0.0], got: [" + collection.toString() + "]", 
				collection.toString().equals("0.0"));
		
		collection.add(new Token(0));
		assertTrue(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace. Expected [0.0 0.0], got: [" + collection.toString() + "]", 
				collection.toString().equals("0.0 0.0"));
	}
	
	@Test
	public final void testEquality() {
		
		// Empty collections
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
	
		// Collections with the same objects. 
		collection.add(new Token(0));
		other.add(new Token(0));
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
		collection.add(new Token(0));
		other.add(new Token(0));
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
		
		
		// Collections with different objects. 
		collection.add(new Token(1));
		assertFalse(collection.getClass().getName() + "s should not be equal", collection.equals(other));
		
	}
	
	@Test 
	public final void testHashCodeEquality() {
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());
		
		collection.add(new Token(0));
		other.add(new Token(0));
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());
		
		collection.add(new Token(0));
		other.add(new Token(0));		
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());	
	}
	
	@Test
	public void testAdd() {
		
		assertTrue(collection.getClass().getName() + "returned false when trying to add an element.", collection.add(new Token(0)));
		assertTrue(collection.getClass().getName() + " didn't insert the specified token. " 
				+ collection.getClass().getName() + ".add(Token), or " + collection.getClass().getName() + ".toString() is broken.", 
				"0.0".equals(collection.toString()));
		
		// Add a second time: adding to an empty list is different than adding to a non-empty list. 
		collection.add(new Token(1));
		assertTrue(collection.getClass().getName() + " didn't insert the specified token. " 
				+ collection.getClass().getName() + ".add(Token), or " + collection.getClass().getName() + ".toString() is broken.", 
				"0.0 1.0".equals(collection.toString()));

	}
}
