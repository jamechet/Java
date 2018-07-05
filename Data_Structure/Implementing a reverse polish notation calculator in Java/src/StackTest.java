import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author simont
 *
 */
public class StackTest extends DSUnitTesting {

	Stack myStack;
	
	@Before
	public void setup() {
		myStack = new DSStack();
	}

	@Test
	public void testEmpty() {
		Stack s = new DSStack();
		assertTrue("Newly created Stack should be empty", s.isEmpty());
		s.push(new Token(0));
		assertFalse("Stack containing an element shouldn't be empty", s.isEmpty());
	}
	
	@Test
	public void testStack_CopyConstructor() {
		
		/* The copy constructor implementation requires a deep copy. 
		 * That is, after copying the object changes to the original should not impact the copied object.
		 */
		Stack s = new DSStack();
		s.add(new Token(0));
		s.add(new Token(1));
		
		Stack other = new DSStack(s);
		
		assertEquals("Copy constructor should create a Stack of equal size to the copied Stack", 2, other.size());
		for ( int i = 0 ; i < 2 ; ++i ) {
			assertEquals("Copy constructor did not copy the Stack correctly", new Token(i), other.pop());
		}

		
		s.push(new Token(2));
		assertEquals("Changes to original stack should not impact the second stack", 0, other.size());
		
	}


	/**
	 * Test method for {@link Stack#peek()}.
	 */
	@Test
	public void testPeek() {
		
		myStack.add(new Token(0));
		assertNotNull("Stack.peek() should not return null when an object should exist at the top of the stack", myStack.peek());
		assertEquals("Stack.peek() should return the object at the top of the stack.", new Token(0), myStack.peek());
		assertFalse("Stack.peek() should not remove the object from the stack.", myStack.isEmpty());
	}

	/**
	 * Test method for {@link Stack#pop()}.
	 */
	@Test
	public void testPop() {
		myStack.add(new Token(0));
		assertEquals("Stack.pop() should return the object at the top of the stack.", new Token(0), myStack.pop());
		assertTrue("Stack.pop() should remove the object from the stack.", myStack.isEmpty());
	}

	/**
	 * Test method for {@link Stack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		myStack.push(new Token(0));
		myStack.push(new Token(1));
		assertEquals("Stack.push() should add to the top of the stack", new Token(1), myStack.peek());
		assertEquals("Stack.push() didn't result in the correct size stack", 2, myStack.size());
	}

}
