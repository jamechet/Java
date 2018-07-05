import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest extends DSUnitTesting{

	@Test
	public final void testEquals() {
		Token t1 = new Token(0);
		Token t2 = new Token(1);
		
		assertFalse("(Checking two different tokens are not equal: operand)", t1.equals(t2));
		assertFalse("(Checking two different tokens are not equal: operand)", t2.equals(t1));
		
		assertTrue("(Checking two equal tokens are equal: operand)", t1.equals(new Token(0)));
		assertTrue("(Checking two equal tokens are equal: operand)", t2.equals(new Token(1)));
	
		Token t3 = new Token("+");
		Token t4 = new Token("-");
		assertFalse("(Checking two different tokens are not equal: operator)", t3.equals(t4));
		assertFalse("(Checking two different tokens are not equal: operator)", t4.equals(t3));		
	
		assertTrue("(Checking two equal tokens are equal: operator)", t3.equals(new Token("+")));
		assertTrue("(Checking two equal tokens are equal: operator)", t4.equals(new Token("-")));
	}
	
	@Test
	public final void testToString() {
		Token t1 = new Token(0);
		assertTrue("(Checking Token.toString on operand)", t1.toString().equals("0.0"));
		Token t2 = new Token("/");
		assertTrue("(Checking Token.toString on operator)", t2.toString().equals("/"));
	}
	
	@Test
	public final void testTokenFloat() {
		
		Token t1 = new Token(0);
		assertTrue("Checking Token(float) sets type correctly", 
				t1.type == Token.Type.OPERAND);
		assertEquals("Checking Token(int) sets operand correctly", 0, t1.getOperand(), 0.0001);
	}
	
	@Test
	public final void testTokenString() {
		Token t1 = new Token("+");
		assertTrue("Checking Token(String) sets type correctly", 
				t1.type == Token.Type.OPERATOR);
		assertEquals("Checking Token(String) sets operand correctly", "+", t1.getOperator());
	}
	
	
	@Test
	public final void testHashCode() {
		
		Token t1 = new Token("+");
		Token t2 = new Token("+");
		
		Token t3 = new Token(0);
		Token t4 = new Token(0);
	
		assertEquals("Checking hashCode() returns the same value for two equal tokens", t1.hashCode(), t2.hashCode());
		assertEquals("Checking hashCode() returns the same value for two equal tokens", t3.hashCode(), t4.hashCode());
	}

	/**
	 * Test method for {@link Token#getOperator()}.
	 */
	@Test
	public void testGetOperator() {

		Token t1 = new Token("-");
		assertTrue("Checking getOperator returns the expected operator", t1.getOperator().equals("-"));

	}

	/**
	 * Test method for {@link Token#getOperand()}.
	 */
	@Test
	public void testGetOperand() {
		Token t1 = new Token(0);
		assertEquals("Checking getOperator returns the expected operator. Expected 0, got: " + t1.getOperand(), 
				t1.getOperand(), 0, 0);
		Token t2 = new Token(1);
		assertEquals("Checking getOperator returns the expected operator. Expected 1, got: " + t2.getOperand(), 
				t2.getOperand(), 1, 0);
	}

	/**
	 * Test method for {@link Token#getPrecedence()}.
	 */
	@Test
	public void testGetPrecedence() {
		Token t1 = new Token("+");
		Token t2 = new Token("-");
		Token t3 = new Token("*");
		Token t4 = new Token("/");
		Token t5 = new Token("^");
		Token t6 = new Token("(");
		Token t7 = new Token(")");
	
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t1.getOperator() + ", expected precedence: 0, "
               + "got: " + t1.getPrecedence(), 0, t1.getPrecedence());
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t2.getOperator() + ", expected precedence: 0, "
               + "got: " + t2.getPrecedence(), 0, t2.getPrecedence()); 
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t3.getOperator() + ", expected precedence: 1, "
               + "got: " + t3.getPrecedence(), 1, t3.getPrecedence()); 
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t4.getOperator() + ", expected precedence: 1, "
               + "got: " + t4.getPrecedence(), 1, t4.getPrecedence());

       assertEquals("Checking getPrecedence() returns correctly (operator: " + t5.getOperator() + ", expected precedence: 2, "
               + "got: " + t5.getPrecedence(), 2, t5.getPrecedence());
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t6.getOperator() + ", expected precedence: 3, "
               + "got: " + t6.getPrecedence(), 3, t6.getPrecedence()); 
       assertEquals("Checking getPrecedence() returns correctly (operator: " + t7.getOperator() + ", expected precedence: 3, "
               + "got: " + t7.getPrecedence(), 3, t7.getPrecedence()); 
	}
}
