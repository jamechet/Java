import java.util.EmptyStackException;

/**
 * @author simont
 *
 */
public class DSStack extends Stack {
	public DSStack() {
		DSLinkedList t = new DSLinkedList();
		list = t;
	}
	public DSStack(Stack other) {
		Queue i = new DSQueue();
		while(other.isEmpty()!=true){
			i.offer(other.pop());
		}
		while(i.isEmpty()!=true){
			other.push(i.poll());
		}
		DSLinkedList a = new DSLinkedList();
		a = (DSLinkedList) other.list;
		DSLinkedList b = new DSLinkedList(a);
		list = (List)b;
	}
	public Token push(Token obj) {
		if(obj !=null){
			list.add(obj);
			return obj;
		}
		else		
			return null;
	}
	public Token peek() {
		return list.get(size());
	}
	public Token pop() {
		return list.remove(list.size());
	}
	public boolean empty() {
		if(list.isEmpty()== true){
			return true;
		}
		else	
			return false;
	}
	public boolean add(Token obj) {
		return list.add(obj);
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public int size() {
		return list.size();
	}
	@Override
	public String toString() {
		return list.toString();
	}
	@Override
	public boolean equals(Object other) {
		Stack store = (Stack) other;
		DSLinkedList a = new DSLinkedList();
		a = (DSLinkedList) store.list;
		if (list.equals(a)){
			return true;
		}	
		else 
			return false;
	}
	@Override
	public int hashCode() {
		return list.hashCode();
	}

}
