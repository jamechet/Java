

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author simont
 * @param <E>
 * @param <E>
 *
 */
public abstract class GCollectionTest<T> extends DSUnitTesting {
	
	private GCollection<T> collection;
	private GCollection<T> other;
	
	protected abstract GCollection<T> createInstance();
	
	protected abstract T createObject(String s);
	
	public static class GStackCollectionInterface<E> extends GCollectionTest<String> {

		protected GDSStack<String> createInstance() {
			return new GDSStack<String>();
		}

		@Override
		protected String createObject(String s) {	
			return new String(s);
		}	
	}

	public static class GQueueCollectionInterface<E> extends GCollectionTest<String> {

		@Override
		protected GCollection<String> createInstance() {
			// TODO Auto-generated method stub
			return new GDSQueue<String>();
		}
		@Override
		protected String createObject(String s) {
			// TODO Auto-generated method stub
			return new String(s);
		}
	}

	public static class GListCollectionInterface<E> extends GCollectionTest<String> {
	
		@Override
		protected GCollection<String> createInstance() {
			return new GDSLinkedList<String>();
		}

		@Override
		protected String createObject(String s) {
			// TODO Auto-generated method stub
			return new String(s);
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setup() throws Exception {
		collection = createInstance();
		other = createInstance();
	}

	/**
	 * Test method for {@link GCollection#add(java.lang.Object)}.
	 */
	@Test
	public void testAdd() {
		assertTrue(collection.getClass().getName() + "returned false when trying to add an element.", collection.add(createObject("foo")));
		assertTrue(collection.getClass().getName() + " didn't insert the specified token. " 
				+ collection.getClass().getName() + ".add(Token), or " + collection.getClass().getName() + ".toString() is broken.", 
				"foo".equals(collection.toString()));
		
		// Add a second time: adding to an empty list is different than adding to a non-empty list. 
		collection.add(createObject("Bar"));
		assertTrue(collection.getClass().getName() + " didn't insert the specified token. " 
				+ collection.getClass().getName() + ".add(Token), or " + collection.getClass().getName() + ".toString() is broken.", 
				"foo Bar".equals(collection.toString()));

	}

	/**
	 * Test method for {@link GCollection#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(collection.getClass().getName() + ".isEmpty() should return true when collection contains no elements", collection.isEmpty());
		
		collection.add(createObject("Foo"));
		assertFalse(collection.getClass().getName() + ".isEmpty() should return false when collection contains elements", collection.isEmpty());

	}

	/**
	 * Test method for {@link GCollection#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(collection.getClass().getName() + ".size() should be 0 for an empty collection.", 0, collection.size());
		
		collection.add(createObject("Foo"));
		assertEquals(collection.getClass().getName() + ".size() should be 1 for a collection with one element.", 1, collection.size());		

	}

	/**
	 * Test method for {@link GCollection#toString()}.
	 */
	@Test
	public void testToString() {
			assertEquals(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
				"with no trailing whitespace", "", collection.toString());
			
			collection.add(createObject("Foo"));
			assertEquals(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
					"with no trailing whitespace", "Foo", collection.toString());
			
			collection.add(createObject("Foo"));
			assertEquals(collection.getClass().getName() + ".toString() should return a concatenated string with a single space between the elements, " +
					"with no trailing whitespace", "Foo Foo", collection.toString());
	}

	/**
	 * Test method for {@link GCollection#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
		
		collection.add(createObject("Foo"));
		other.add(createObject("Foo"));
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
		
		collection.add(createObject("Foo"));
		other.add(createObject("Foo"));
		assertTrue(collection.getClass().getName() + "s should be equal", collection.equals(other));
		
		// Collections with different objects. 
		collection.add(createObject("Baz"));
		assertFalse(collection.getClass().getName() + "s should not be equal", collection.equals(other));

	}

	/**
	 * Test method for {@link GCollection#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());
		
		collection.add(createObject("Foo"));
		other.add(createObject("Foo"));
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());
		
		collection.add(createObject("Foo"));
		other.add(createObject("Foo"));		
		assertTrue(collection.getClass().getName() + "hashCodes for [" + collection.toString() + "] and [" +
				other.toString() + "] should be equal", collection.hashCode() == other.hashCode());	
	}

	@Test(expected=NullPointerException.class)
	public final void testAddNullPointer() {
		collection.add(null);
	}

	/* (non-Javadoc)
	 * @see GCollectionTest#createInstance()
	 */

}
