import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class InfixToPostfixTest extends DSUnitTesting {

	Calculator calc;
	Queue input;
	Queue expectedOutput;
	
	public InfixToPostfixTest(Queue input, Queue expectedOutput) {
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	@Before
	public final void setup() {
		calc = new Calculator();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> tokenStreams() {
		return Arrays.asList(new Object[][] { 
				// Simple operators. 
				{ THelper.listCreator("1 + 2"), THelper.listCreator("1 2 +") },
				{ THelper.listCreator("2 - 4"), THelper.listCreator("2 4 -") },
				{ THelper.listCreator("4 * 9"), THelper.listCreator("4 9 *") },
				
				// Multiple operators, single expression
				{ THelper.listCreator("1 + 2 + 3"), THelper.listCreator("1 2 + 3 +") },
				{ THelper.listCreator("2 / 3 * 4"), THelper.listCreator("2 3 / 4 *") },
				
				// Different precedence values
				{ THelper.listCreator("1 + 2 * 3"), THelper.listCreator("1 2 3 * +") },
				{ THelper.listCreator("1 / 2 + 4 * 5 - 3"), THelper.listCreator("1 2 / 4 5 * + 3 -") },
				{ THelper.listCreator("2 * 3 - 4 / 2 + 1 / 2"), THelper.listCreator("2 3 * 4 2 / - 1 2 / +")},
				{ THelper.listCreator("7 / 1 + 3 * 2 * 1 / 4 / 3 + 2 * 1"), THelper.listCreator("7 1 / 3 2 * 1 * 4 / 3 / + 2 1 * +") },
				
				// Parens. 
				{ THelper.listCreator("( 1 + 2 ) * 3"), THelper.listCreator("1 2 + 3 *") }, 
				{ THelper.listCreator("2 / ( 3 + 1 ) * ( 4 * 2 )"), THelper.listCreator("2 3 1 + / 4 2 * *") }
				
		});
	}
	
	@Test
	public final void infixToPostfix() {
	
		DSQueue givenInfix = new DSQueue((DSQueue)input);
		
		DSQueue result = (DSQueue)calc.infixToPostfix(input);
		
		if ( expectedOutput.size() != result.size() )
			fail("Infix to Postfix conversion resulted in the wrong number of tokens: expected: "  
				+ expectedOutput + ", recieved: " + result);
		
		if ( !expectedOutput.toString().equals(result.toString()) ) {
			fail("Converting: [" + givenInfix + "] resulted in an incorrect RPN expression. Expected: " 
					+ expectedOutput + ", got: " + result);			
		}
	}

}
