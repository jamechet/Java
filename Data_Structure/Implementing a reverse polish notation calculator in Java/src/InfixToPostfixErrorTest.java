import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixErrorTest {
	
	Calculator calc;

	@Before
	public void setup() {
		calc = new Calculator();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void mismatchedLeftParen() {
		calc.infixToPostfix( THelper.listCreator("1 + 2 * ( 3 - 4") );
	}

	@Test(expected=IllegalArgumentException.class) 
	public final void mismatchedRightParen() {
		calc.infixToPostfix( THelper.listCreator("4 - 3 * 2 )") );
	}
}
