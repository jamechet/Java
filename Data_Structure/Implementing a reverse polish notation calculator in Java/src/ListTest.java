import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author simont
 *
 */
public class ListTest extends DSUnitTesting {

    @Test
    public void testList_CopyConstructor() {
		
		/* The copy constructor implementation requires a deep copy. 
		 * That is, after copying the object changes to the original should not impact the copied object.
		 */
		DSLinkedList s = new DSLinkedList();
		s.add(new Token(0));
		s.add(new Token(1));
		
		DSLinkedList other = new DSLinkedList(s);
		
		assertEquals("Copy constructor should create a List of equal size to the copied List", 2, other.size());
		for ( int i = 1 ; i >= 0 ; --i ) {
			assertEquals("Copy constructor did not copy the List correctly", new Token(i), other.remove(i));
		}
		
		s.add(new Token(2));
		assertEquals("Changes to original list should not impact the second list", 0, other.size());
		
    }
    @Test
    public void testList_Node() {
    	DSLinkedList l = new DSLinkedList(new Node(null, null, new Token(3)));
    	
    	assertEquals("Constructor that accepts a Node should set the given Node to the head of the list", 1, l.size());
    	
    }

    @Test
    public void testRemoveIntBeginning() {
    	DSLinkedList l = new DSLinkedList();
    	l.add(new Token("+"));
    	l.add(new Token("-"));
    	l.add(new Token("*"));
    	
    	Token t = l.remove(0);
    	assertNotNull("List.remove(int) should not return null when elements exist at that index in the list", t);
		assertEquals("Remove did not remove from the list correctly", new Token("+"), t);
		assertEquals("Remove did not remove from the list correctly", new Token("-"), l.remove(0));
    }
    @Test
    public void testRemoveIntEnd() {
    	DSLinkedList l = new DSLinkedList();
    	l.add(new Token("+"));
    	l.add(new Token("-"));
    	l.add(new Token("*"));
    	

    	Token t = l.remove(2);
    	assertNotNull("List.remove(int) should not return null when elements exist at that index in the list", t);
		assertEquals("Remove did not remove from the list correctly", new Token("*"), t);
		assertEquals("Remove did not remove from the list correctly", new Token("-"), l.remove(1));
    }

	@Test
	public void testAddIntToken() {
		List list = new DSLinkedList();
		
		list.add(new Token(1));
		list.add(0, new Token(0));
		
		assertEquals("List.add(int, Token) should insert the specified token at the specified index.", new Token(0), list.get(0));
		
		list.add(2, new Token(2));
		assertEquals("List.add(int, Token) should insert the specified token at the specified index.", new Token(2), list.get(2));
		
		list.add(2, new Token(3));
		assertEquals("List.add(int, Token) should insert the specified token at the specified index.", new Token(3), list.get(2));
		
		assertEquals(4, list.size());
	}

	/**
	 *  method for {@link List#contains(java.lang.Object)}.
	 */
	@Test
	public void testContains() {

		List list = new DSLinkedList();
		for ( int i = 0 ; i < 3 ; ++i ) 
			list.add(new Token(i));
	
		for ( int i = 0 ; i < 3 ; ++i ) 
			assertTrue("List.contains(Token) should return true when the token is in the list.", list.contains(new Token(i)));

		
		list = new DSLinkedList();
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		
		assertFalse("List.contains() should return false when the token is not in the list", list.contains(new Token(4)));
		assertFalse("List.contains() should return false when the token is not in the list", list.contains(new Token(-1)));
	}

	/**
	 *  method for {@link List#get(int)}.
	 */
	@Test
	public void testGet() {
		
		List list = new DSLinkedList();
		list.add(new Token(0)); 
		list.add(new Token(1));
		list.add(new Token(2));
		
		assertNotNull("List.get() should not return null when the object should exist in the list", list.get(0));
		assertEquals("List.get() isn't returning the correct item.", new Token(0), list.get(0));
		assertEquals("List.get() isn't returning the correct item.", new Token(2), list.get(2));
		assertEquals("List.get() isn't returning the correct item.", new Token(1), list.get(1));
	}
	
	
	@Test
	public void testIndexOf() {
		List list = new DSLinkedList();
		
		list.add(new Token(0));
		list.add(new Token(1));
		
		assertEquals("List.indexOf() should return the correct index", 1, list.indexOf(new Token(1)));
		assertEquals("List.indexOf() should return the correct index", 0, list.indexOf(new Token(0)));
	}

	@Test
	public void testRemoveInt() {
		List list = new DSLinkedList();
		
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		list.add(new Token(3));
		
		assertEquals("List.add() should add tokens to the list. (List: [" + list.toString() + "])", 4, list.size());

		assertEquals("List.get() should return the token at the specified index. (List: [" + list.toString() + "])", 
				new Token(1), list.get(1));
		list.remove(1);
		assertEquals("List.remove(int) should decrement the size by one. (List: [" + list.toString() + "])", 3, list.size());
		
		assertEquals("List.get() should return the token at the specified index. (List: [" + list.toString() + "])", 
				new Token(2), list.get(1));
		
	}
	
	/**
	 *  method for {@link List#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveToken_Beginning() {
		
		List list = new DSLinkedList();
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		
		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Token(0)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("1.0 2.0"));
	}

	/**
	 *  method for {@link List#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveToken_End() {
		
		List list = new DSLinkedList();
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		
		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Token(2)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("0.0 1.0"));
	}
	
	/**
	 *  method for {@link List#remove(java.lang.Object)}.
	 */
	@Test
	public void testRemoveToken_Middle() {
		
		List list = new DSLinkedList();
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		
		assertTrue("List.remove() should return true when removing an existing object", list.remove(new Token(1)));
		assertEquals("List.size() should decrement after a remove().", 2, list.size());
		assertTrue("List.remove() should remove the given object", list.toString().equals("0.0 2.0"));
	}

	@Test
	public void testRemoveToken_Single() {
		
		List list = new DSLinkedList();
		list.add(new Token(0));
		list.add(new Token(1));
		list.add(new Token(2));
		list.add(new Token(1));
		
		assertTrue("List.remove(Token) should remove the first instance of the specified token.", list.remove(new Token(1)));
		assertTrue("List.remove(Token) should only remove one instance of the specified token!", list.toString().equals("0.0 2.0 1.0"));
	}
}
