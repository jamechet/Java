import org.junit.Before;
import org.junit.Test;


public class ListExceptionTest extends DSUnitTesting {
	
	private List myList;
	@Before
	public void setup() {
		myList = new DSLinkedList();
	}
	
	@Test(expected=NullPointerException.class)
	public final void addNullPointer() {
		myList.add(0, null);
	}
	
	@Test(expected=NullPointerException.class)
	public final void containsNullPointer() {
		myList.contains(null);
	}
	
	@Test(expected=NullPointerException.class)
	public final void removeNullPointer() {
		myList.remove(null);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public final void addIndexOutOfBoundsHigh() {
		myList.add(3, new Token(0));
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public final void addIndexOutOfBoundsLow() {
		myList.add(-1, new Token(0));
	}	
	@Test(expected=IndexOutOfBoundsException.class)
	public final void removeIndexOutOfBoundsHigh() {
		myList.remove(3);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public final void removeIndexOutOfBoundsLow() {
		myList.remove(-2);
	}
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public final void getIndexOutOfBoundsHigh() {
		myList.get(7);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public final void getIndexOutOfBoundsLow() {
		myList.get(-1);
	}
}
