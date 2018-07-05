


import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;

/**
 * @author simont
 *
 */
public class GDSStackTest extends DSUnitTesting  {

	GDSStack<String> stack;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stack = new GDSStack<String>();
	}
	
	@Test(expected=EmptyStackException.class)
	public void testPeek_Empty() {
		stack.peek();
	}

	@Test(expected=EmptyStackException.class)
	public void testPop_Empty() {
		stack.pop();
	}
	/**
	 * Test method for {@link GDSStack#peek()}.
	 */
	@Test
	public void testPeek() {
		
		stack.push("Foo");
		assertEquals(1, stack.size());
		assertEquals("Foo", stack.peek());
		assertEquals("Stack size shouldn't be changed by peek()", 1, stack.size());
		
		stack.push("Bar");
		assertEquals(2, stack.size());
		assertEquals("Stack.peek() didn't return the expected result", "Bar", stack.peek());

	}

	@Test
	public void testPop() {
		
		stack.push("Foo");
		stack.push("Bar");
		assertEquals(2, stack.size());
		assertEquals("Bar", stack.pop());
		assertEquals(1, stack.size());
		assertEquals("Stack.pop() didn't return the expected result", "Foo", stack.peek());

	}

	/**
	 * Test method for {@link GDSStack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		
		assertEquals(0, stack.size());
		
		assertEquals("Foo", stack.push("Foo"));
		assertEquals(1, stack.size());
		assertEquals("Bar", stack.push("Bar"));
		assertEquals(2, stack.size());
	}

}
