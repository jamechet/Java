
public class DSQueue extends Queue {

	public DSQueue(Queue input) {
		DSLinkedList a = new DSLinkedList();
		a = (DSLinkedList) input.list;
		DSLinkedList b = new DSLinkedList(a);
		list = (List)b;
	}
	public DSQueue() {
		DSLinkedList t = new DSLinkedList();
		list = (List)t;
	}
	public boolean offer(Token t) {
		return list.add(t);
	}
	public Token poll() {
		return list.remove(list.size()-list.size());
	}
	public Token peek() {
		return list.get(list.size()-list.size());
	}
	public boolean add(Token obj) {
		return list.add(obj);
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public int size() {
		return list.size();//size();
	}
	@Override
	public String toString() {
		return list.toString();
	}
	@Override
	public boolean equals(Object o) {
		Queue store = (Queue) o;
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
