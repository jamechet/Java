import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PrecedenceTest extends DSUnitTesting {

	public Token a, b;
	public int expectedResult;
	public Calculator calc;
	
	public PrecedenceTest(Object[] objs) {
		
		this.a = (Token)objs[0];
		this.b = (Token)objs[1];
		this.expectedResult = (Integer)objs[2];
	}
	
	@Before
	public void setup() {
		calc = new Calculator();
	}
	/*
	 * Helper function: return an object array of tokens + integer. 
	 */
	public static Object[] pair(String args) {
		String[] parts = args.split(" ");
		
		return new Object[]{ new Token(parts[0]), 
							 new Token(parts[1]), 
							 Integer.parseInt(parts[2]) };
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> precedencePairs() {
		Object[][] objs = new Object[][] { 
				/*
				 *     First           Second      isHigher? (lower, same, higher: -1, 0, 1)
				 */
				{ pair("+ + 0") },
				{ pair("- - 0") },
				{ pair("- + 0") },
				{ pair("+ - 0") },
				
				{ pair("+ / -1")},
				{ pair("/ - 1")},
				
				{ pair("/ / 0")},
				{ pair("/ * 0")},
				{ pair("* / 0")},
				{ pair("* * 0")},
				
				{ pair("* + 1")},
				{ pair("- * -1")},
				
				{ pair("( ( 0")},
				{ pair("( ) 0")},
				{ pair("( + 1")},
				{ pair(") - 1")},
				{ pair(") / 1")}
				
		};

		return Arrays.asList(objs);
	}

}
