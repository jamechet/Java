
public class Token {
	public enum Type { OPERATOR, OPERAND, PAREN };
	public Type type;
	
	private String operator=" ";
	private float operand=-1;
	private int precedence= -1;
	
	public Token(float op) {
		this.type = Type.OPERAND;
		operand = op;
		
	}

	public Token(int op){
	
		this.type = Type.OPERAND;
		operand = (float)op;
	}
	
	public Token(String op) {
		if ((op.equals(")"))||(op.equals("(")))
			this.type = Type.PAREN;
		else
			
			this.type = Type.OPERATOR;
		
	
		operator = op;
		if((operator.equals("+"))|| (operator.equals("-"))){
			precedence = 0;
		}
		else if(operator.equals("/")||(operator.equals("*"))){
			precedence = 1;
		}
		else if(operator.equals("^")){
			precedence = 2;
		}
		else if((operator.equals(")"))||(operator.equals("("))){
			precedence = 3;
		}
			
		
	}
	
	public Token(Token other) {
		operator = other.operator;
		operand = other.operand;
		if((operator.equals("+"))|| (operator.equals("-"))){
			precedence = 0;
		}
		else if(operator.equals("*")||(operator.equals("/"))){
			precedence = 1;
		}
		else if(operator.equals("^")){
			precedence = 2;
		}
		else if((operator.equals(")"))||(operator.equals("("))){
			precedence = 3;
		}
	}
	
	public String getOperator() {
		
		return new String (operator);
	}
	public float getOperand() {
		return new Float(operand);
	}
	public int getPrecedence() {
		return new Integer(precedence);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (Float.floatToIntBits(operand) != Float
				.floatToIntBits(other.operand))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (precedence != other.precedence)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(operand);
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + precedence;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}	
	public String toString() {
	//	
		if (!(operator.equals(" "))){
			return operator;
		}
		if(operand >=0 ){
			
			String floatToString = String.valueOf(operand);
			return new String (floatToString);
		}
		else if(precedence>-1){
			String intToString = String.valueOf(precedence);
			return new String(intToString);
		}
		else	
			return null;
	}
}



