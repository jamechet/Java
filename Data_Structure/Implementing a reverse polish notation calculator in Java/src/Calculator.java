
public class Calculator {
	
	public Queue infixToPostfix(Queue infix) {
		Queue copyStack = new DSQueue();
		Queue copyStacks = new DSQueue(copyStack);
		Stack symbol = new DSStack();
		Queue getSize = new DSQueue(infix);
		int infixSize = getSize.size();
		Queue inputQueue = new DSQueue();
		Queue outputQueue= new DSQueue();
		Stack inputStack = new DSStack();
		// read infix queue
		while (infix.isEmpty() !=true){
			// if the top of the queue is symbol insert into a stack and remove it
			int math = infix.peek().getPrecedence();
			if(math == 0 || math == 1|| math== 2|| math== 3){
				// if the inputStack is empty 
				if (inputStack.isEmpty()){			
							inputStack.push(infix.poll());
					
				}
				else{
					if(math == 3 && infix.peek().toString().equals("(")){
			
						inputStack.push(infix.poll());
					}
					else if(infix.peek().toString().equals(")")){
						infix.poll();
						while(inputStack.isEmpty()!=true){
							if(inputStack.peek().toString().equals("(")){
								inputStack.pop();
							}
							else
								
								inputQueue.offer(inputStack.pop());
						}
					}
	
					// if the coming in precedence larger than current precedence input into stack 
					else if(inputStack.peek().toString().equals("+") && infix.peek().toString().equals("*")){
					
						//	inputQueue.offer(inputStack.pop());
							inputStack.push(infix.poll());
					}
					else if(inputStack.peek().toString().equals("-") && infix.peek().toString().equals("/")){
					
						inputStack.push(infix.poll());
					}
					else if(inputStack.peek().toString().equals("/") && infix.peek().toString().equals("-")){
							inputQueue.offer(inputStack.pop());
							inputStack.push(infix.poll());
						}
					else if(inputStack.peek().toString().equals("*") && infix.peek().toString().equals("-")){
						while(inputStack.isEmpty()!=true){
						inputQueue.offer(inputStack.pop());
						}
						inputStack.push(infix.poll());
						
					}
					else if(inputStack.peek().toString().equals("/") && infix.peek().toString().equals("+")){//> infix.peek().getPrecedence()){
						while(inputStack.isEmpty()!=true){
							inputQueue.offer(inputStack.pop());
						}
						inputStack.push(infix.poll());
					
					}
					else if(inputStack.peek().toString().equals("+") && infix.peek().toString().equals("/")){
						inputStack.push(infix.poll());
						
					}
					// if not insert into queue
					else{
						// devide come first 
						if (inputStack.peek().toString().equals("/") && infix.peek().toString().equals("*")){
							
							Token devide = inputStack.pop();
							inputStack.push(infix.poll());
							inputQueue.offer(devide);
						}
						else if (inputStack.peek().toString().equals("*") && infix.peek().toString().equals("/")){
							Token devide = inputStack.pop();
							inputStack.push(infix.poll());
							inputQueue.offer(devide);
						}
						else if(inputStack.peek().toString().equals("(")){
							inputStack.push(infix.poll());
						}
						else
							inputQueue.offer(infix.poll());
					}
				}	
			}
			// if it number return head of the queue and remove it
			else{
				inputQueue.offer(infix.poll());
			}
		}
		while(inputQueue.isEmpty()!= true){
			outputQueue.offer(inputQueue.poll());
		}
		while(inputStack.isEmpty()!=true){
			outputQueue.offer((inputStack.pop()));
		}
		return outputQueue ;
	}
	public float evaluate(Queue input) {
		Stack revers = new DSStack();
		float number = 0;
		Stack inputQueue = new DSStack();
		Stack inputStack = new DSStack();
		while(input.isEmpty()!=true){
			int math =input.peek() .getPrecedence();
			if(math== 0 || math == 1 || math == 2 || math == 3){
		//		inputStack.push(input.poll());
				String symbol = input.poll().toString();
				float queue1 = inputQueue.pop().getOperand();
				float queue2 = inputQueue.pop().getOperand();
				if(symbol.equals("+")){
					number = queue2 + queue1;
					inputQueue.push(new Token(number));
				}
				else if(symbol.equals("-")){
					number = queue2 - queue1;
					inputQueue.push(new Token(number));
				
				}
				else if(symbol.equals("*")){
					number = queue2 * queue1;
					inputQueue.push(new Token(number));
				}
				else if(symbol.equals("/")){
					number = queue2 / queue1;
					inputQueue.push(new Token(number));
				}
			}
			else
					inputQueue.push(input.poll());
		}
		return inputQueue.pop().getOperand() ;
	}
}
