


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author simont
 *
 */
public class GNodeTest {

	GNode<String> n1, n2, n3, n4;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		n1 = new GNode<String>(null, null, "Foo");
		n2 = new GNode<String>(null, null, "Foo");
		n3 = new GNode<String>(null, new GNode<String>(null, null, null), "Foo");
		n4 = new GNode<String>(null, null, "Bar");
		
	}

	/**
	 * Test method for {@link GNode#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		
		assertEquals("Checking hashCode() returns the same value for nodes containing equal objects", n1.hashCode(), n2.hashCode());
		assertEquals("Checking hashCode() returns the same value for two nodes with different pointers, but equal objects", 
				n2.hashCode(), n3.hashCode());
	}

	/**
	 * Test method for {@link GNode#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		assertTrue("Checking equals() returns true for nodes containing equal objects", n1.equals(n2));
		assertTrue("Checking equals() returns true for two nodes with different pointers, but equal objects", n2.equals(n3));	
		assertFalse("Checking equals() returns false for nodes with non-equal objects", n1.equals(n4));
	}
}
