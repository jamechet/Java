


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author simont
 *
 */
public class GListTest extends DSUnitTesting  {

	GDSLinkedList<String> list; 
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new GDSLinkedList<String>();
	}

	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddIntT_IndexTooHigh() {
		list.add(5, "foo");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddIntT_IndexTooLow() {
		list.add(-1, "foo");
	}

	@Test(expected=NullPointerException.class)
	public void testAddIntT_Null() {
		list.add(0, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddIntT_Null_ListNonEmpty() {
		list.add(0, "foo");
		list.add(1, "bar");
		list.add(2, "baz");
		list.add(1, null);
	}
	
	/**
	 * Test method for {@link GList#add(int, java.lang.Object)}.
	 */
	@Test
	public void testAddIntT() {
		assertEquals("New list should be empty", 0, list.size());
		
		// Add to an empty list
		for ( int i = 0 ; i < 5 ; ++i )
			assertTrue("list.add(" + i + ", " + Integer.toString(i) + 
					" should return true", list.add(i, Integer.toString(i)));
		
		// Add a new string at position 1
		assertTrue("list.add(1, one) should return true", list.add(1, "one"));
		
		assertEquals("list.get() didn't retrieve the expected string", "0", list.get(0));
		assertEquals("list.get() didn't retrieve the expected string", "one", list.get(1));

		for ( int i = 1 ; i < 3 ; ++i ) {
			assertEquals("list.get() didn't retrieve the expected string", 
					Integer.toString(i + 1), list.get(i+2));
		}
	}

	
	/**
	 * Test method for {@link GList#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {
		list.add("foo");
		list.add("bar");
		list.add("baz");
		
		assertTrue("list.contains(foo) should be true when foo is in the list", 
				list.contains("foo"));
		assertTrue("list.contains(bar) should be true when foo is in the list", 
				list.contains("bar"));
		assertTrue("list.contains(baz) should be true when foo is in the list", 
				list.contains("baz"));

		assertFalse("list.contains(far) should be false if far is not in the list", 
				list.contains("far"));
	}

	@Test(expected=NullPointerException.class)
	public void testContainsNull() {
		list.contains(null);
	}
	
	/**
	 * Test method for {@link GList#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveT() {
		
		list.add("foo");
		list.add("bar");
		list.add("baz");
		
		assertEquals(3, list.size());
		assertTrue("list.remove(T) should return true when element removed", list.remove("bar"));
		assertEquals(2, list.size());
		
		assertFalse("list.remove(T) should be false when T is not in the list", list.remove("bar"));
	}

	@Test(expected=NullPointerException.class)
	public void testRemoveTNull() {
		list.remove(null);
	}
	
	/**
	 * Test method for {@link GList#remove(int)}.
	 */
	@Test
	public void testRemoveInt() {
		list.add("foo");
		list.add("bar");
		list.add("baz");
		
		String obj = list.remove(1);
		assertEquals(2, list.size());
		assertEquals("list.remove(int) didn't return the expected element", "bar", obj);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveInt_IndexHigh() {
		list.remove(5);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveInt_IndexLow() {
		list.remove(-5);
	}
	
	/**
	 * Test method for {@link GList#get(int)}.
	 */
	@Test
	public void testGet() {
		list.add("foo");
		list.add("bar");
		list.add("baz");
		
		assertEquals("foo", list.get(0));
		assertEquals("bar", list.get(1));
		assertEquals("baz", list.get(2));
	}

	/**
	 * Test method for {@link GList#indexOf(java.lang.Object)}.
	 */
	@Test
	public void testIndexOf() {
		list.add("foo");
		list.add("bar");
		list.add("baz");
		
		assertEquals("indexOf didn't return the expected index", 1, list.indexOf("bar"));
		assertEquals("indexOf didn't return the expected index", 2, list.indexOf("baz"));
		
		assertEquals("indexOf should return -1 when item not in list", -1, list.indexOf("not in list"));
	}
	
	@Test
	public void testBackwardsTraverse() {
		fail("This test is not given to students.\nMake sure you can traverse backwards using the 'previous' references in your list.");
	}
	
	@Test
	public void testAddIntToken_InsertAtBeginning() {
		
		assertEquals("Empty list should have size 0", 0, list.size());
		list.add(0, "foo");
		list.add(0, "bar");
		list.add(0, "baz");
		
		assertEquals("Non-empty list should have size 3", 3, list.size());
		assertEquals("First item in list should be", "baz", list.get(0));
		
	}

	@Test
	public void testIndexOf_invalidIndex() {
		list.add("bar");
		list.add("baz");
		assertEquals("IndexOf should return -1 when the object doesn't exist in the list", -1, list.indexOf("foo"));
	}

}
