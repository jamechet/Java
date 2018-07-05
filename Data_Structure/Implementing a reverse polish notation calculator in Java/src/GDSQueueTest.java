


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author simont
 *
 */
public class GDSQueueTest extends DSUnitTesting {

	GDSQueue<String> queue;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	
		queue = new GDSQueue<String>();
	}

	@Test(expected=NullPointerException.class)
	public void testOffer_Null () {
		queue.offer(null);
	}
	
	/**
	 * Test method for {@link GDSQueue#offer(java.lang.Object)}.
	 */
	@Test
	public void testOffer() {
		
		assertEquals(0, queue.size());
		assertTrue(queue.offer("Foo"));
		assertEquals(1, queue.size());
		
	}

	/**
	 * Test method for {@link GDSQueue#poll()}.
	 */
	@Test
	public void testPoll() {
		queue.offer("foo");
		queue.offer("bar");
		queue.offer("baz");
		
		assertEquals(3, queue.size());
		assertEquals("foo", queue.poll());
		assertEquals("bar", queue.poll());
		
		assertEquals(1, queue.size());
	}

	/**
	 * Test method for {@link GDSQueue#peek()}.
	 */
	@Test
	public void testPeek() {
		assertNull(queue.peek());
		queue.offer("foo");
		queue.offer("bar");
		queue.offer("baz");
		
		assertEquals("foo", queue.peek());	
		
		fail("Testing the marking system.");
	}

}
