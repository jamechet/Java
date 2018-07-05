import org.junit.Before;
import org.junit.Test;

public class QueueExceptionTest extends DSUnitTesting {
	
	private Queue myQueue;
	@Before
	public void setup() {
		myQueue = new DSQueue();
	}
	
	@Test(expected=NullPointerException.class)
	public final void offerThrowsNull() {
		myQueue.offer(null);
	}

}
