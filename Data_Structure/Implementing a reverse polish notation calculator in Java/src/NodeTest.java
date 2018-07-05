import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest extends DSUnitTesting {


	@Test
	public final void testToString() {
		
		Node myNode = new Node(null, null, null);
		
		assertEquals("(Checking Node.toString())", 
				"Node has no token assigned.", myNode.toString());
	
	}
	
	@Test
	public void testHashCode() {
	
		Node n1 = new Node(null, null, new Token(0));
		Node n2 = new Node(null, null, new Token(0));
		Node n3 = new Node(null, new Node(null, null, null), new Token(0));
		Node n4 = new Node(null, null, new Token(1));
		
		assertEquals("Checking hashCode() returns the same value for nodes containing equal objects", n1.hashCode(), n2.hashCode());
		assertEquals("Checking hashCode() returns the same value for two nodes with different pointers, but equal objects", 
				n2.hashCode(), n3.hashCode());
	}

	@Test
	public void testEqualsObject() {
		Node n1 = new Node(null, null, new Token(0));
		Node n2 = new Node(null, null, new Token(0));
		Node n3 = new Node(null, new Node(null, null, null), new Token(0));
		Node n4 = new Node(null, null, new Token(1));
		
		assertTrue("Checking equals() returns true for nodes containing equal objects", n1.equals(n2));
		assertTrue("Checking equals() returns true for two nodes with different pointers, but equal objects", n2.equals(n3));
		
		assertFalse("Checking equals() returns false for nodes with non-equal objects", n1.equals(n4));
	}

}
