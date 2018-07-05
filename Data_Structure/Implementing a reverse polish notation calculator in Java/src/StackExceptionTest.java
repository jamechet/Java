import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;


public class StackExceptionTest extends DSUnitTesting{
	
	private Stack myStack;
	@Before
	public void setup() {
		myStack = new DSStack();
	}
	
	@Test(expected=EmptyStackException.class)
	public final void popOnEmptyStack() {
		myStack.pop();
	}
	
	@Test(expected=EmptyStackException.class)
	public final void peekOnEmptyStack() {
		myStack.peek();
	}

}
