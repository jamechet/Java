import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class PostfixEvaluation extends DSUnitTesting {

	Queue input;
	float value;
	Calculator calc;
	
	public PostfixEvaluation(Queue input, float value) {
		this.input = input;
		this.value = value;
	}
	
	@Before
	public void setup() {
		calc = new Calculator();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> tokenStreams() {
		return Arrays.asList(new Object[][] { 
				// Simple operators. 
				{ THelper.listCreator("1 2 +"), 3 },
				{ THelper.listCreator("2 4 -"), -2 },
				{ THelper.listCreator("4 9 *"), 36 },
				
				// Multiple operators, single expression
				{ THelper.listCreator("1 2 + 3 +"), 6 },
				{ THelper.listCreator("2 3 / 4 *"), 8.0f/3.0f},
				
				// Different precedence values
				{ THelper.listCreator("1 2 3 * +"), 7 },
				{ THelper.listCreator("1 2 / 4 5 * + 3 -"), 17.5f },
				{ THelper.listCreator("2 3 * 4 2 / - 1 2 / +"), 4.5f},
				{ THelper.listCreator("7 1 / 3 2 * 1 * 4 / 3 / + 2 1 * +"), 9.5f },
				
				// Parens. 
				{ THelper.listCreator("1 2 + 3 *"), 9}, 
				{ THelper.listCreator("2 3 1 + / 4 2 * *"), 4 }
				
		});
	}
	
	@Test
	public final void postfixEvaluation() {
		float result = calc.evaluate(new DSQueue((DSQueue)input));
		if ( result != value )
			fail("Evaluating [" + input + "] failed (got [" + result + "], expected: [" + value +"])");
	}

}
