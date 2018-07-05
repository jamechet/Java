
public class DSLinkedList implements List {
	
	public Node head;
	int size;
	public DSLinkedList() {
		head = null;
		size=0;
	}
	public DSLinkedList(Node head_) {
		head = head_;
	}
	// Copy constructor.
	public DSLinkedList(DSLinkedList other) {
		for(int i=0; i<other.size(); i++){
			Token s = other.get(i);
			add(s);
		}
	}

	// Remove element with specify index
	public Token remove(int index)throws  IndexOutOfBoundsException {
		if(index > size() || index<0){
			throw new IndexOutOfBoundsException();
		}
		Node currentIndex = head;
		int reversCall = size();
		reversCall = reversCall-index;
		for(int i=0; i<reversCall-1; i++){
			currentIndex = currentIndex.next;
		}
		Token removed =  currentIndex.getObject();
		
		Node currentPrevious = currentIndex.previous;
		Node currentNext = currentIndex.next;
		
		// remove only if one index left
		if(currentPrevious ==null && currentNext== null){
			currentIndex =null;
			head = currentIndex;
			size = size-1;
			return removed;
		}
		// remove first index
		if(currentPrevious == null){
			head= currentNext;
			head.previous =null;
			size = size-1;
			return removed;
		}
		else
		// remove the last index;
			if(currentNext ==null){
				currentPrevious.next= null;
				size = size-1;
				return removed;
			}
		// Remove between
			else 
				currentNext.previous = currentPrevious;
				currentPrevious.next= currentNext;
								
				size = size-1;							
		return removed;
	}
	/*using get method to loop and return the object
	 * if object in the list is equal the object input 
	 * return current position terminate the loop
	 */
	public int indexOf(Token obj)throws NullPointerException {
		if(obj== null){
			throw new NullPointerException();
		}
		int positionIndex =-1;
		for(int i=0; i<size(); i++){
			Token copy = get(i);
			if(copy.equals(obj)){
				positionIndex = i;
				i=size();
			}
		}
		return positionIndex;
	}
	/*first element in the list is at last position
	 * Get size of the list - index -1
	 * return the vaule as it call
	 */
	public Token get(int index)throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		int reversCall = size();
		reversCall = reversCall-index;
		for(int i=0; i<reversCall-1 && node !=null; i++){
			node = node.next;	
		}
		
		return node.getObject() ;
	}

	public boolean isEmpty() {
		
		return head==null;
	}

	public int size() {
		int count =0;
		Node n=head;
		while(n!=null){
			count++;
			n=n.next;
		}
		return count;	
	}
	
	@Override
	public String toString() {
		int number =0;
		StringBuilder result = new StringBuilder();
		while(number<size()){
			result.append(get(number));
			number++;
			if(number<size()){
				result.append(" ");
			}
		}
		return result.toString();
	}

	public boolean add(Token obj)throws NullPointerException {
		//if node is empty create one
		if( obj ==null){
			throw new NullPointerException();
		}
		if(head==null){
			head = new Node(null, null, obj);
			size++;
			return true;
		}
		else{
			//store current node
			Node currentNode = head;
			// create a new node
			head = new Node(head, null, obj);
			// link last previous node to the node after
			currentNode.previous = head;
			size++;
			return true;
		}

		
	}
	/* if the object does exist in the list return true
	 * if not add object into the index and shift the old index to the next
	 *   
	 * 
	 */
	public boolean add(int index, Token obj) throws NullPointerException,  IndexOutOfBoundsException{
		
		if(obj==null){
			throw new NullPointerException();
		}	
		if(index<0 || index >size()){
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty() == true){
			add(obj);
		}
		Node currentIndex = head;
		int reversCall = size();
		reversCall = reversCall-index;
		for(int i=0; i<reversCall-1; i++){
			currentIndex = currentIndex.next;
		}
		Token removed = currentIndex.getObject();
		
		Node currentPrevious = currentIndex.previous;
		Node currentNext = currentIndex.next;
	//	Node store = new Node(null, null, obj);
		
		// remove only if one index left
		
		if(index == size()-1){
			if(currentIndex.getObject().equals(obj)){
				return true;
			}
			add(obj);
			Node current = head;
			int re = size();
			re = re-index-1;
			for(int i=0; i<re-1; i++){
				current = current.next;
			}
			Token aa = current.getObject();
			Token bb = current.next.getObject();
			current.next = new Node(current.next.next, current.next.previous, aa);
			head = new Node(current.next, null, bb);
			return true;
/*			add(obj);
			Token t = head.next.getObject();
			Token c = head.getObject();
			head.setObj(t);
			head.next.setObj(c);
*/
		}
		else{
			if(currentPrevious == null && index>size()-1){
				add(obj);
			
			}
		
			
		}

			return true;
	}
	/*	Use indexOf to return object position
	 * 	if object doesn't exist will return -1
	 *  contains return false
	 *  else if it found return true;
	 */
	public boolean contains(Token obj)throws NullPointerException {
		if(obj == null){
			throw new NullPointerException();
		}
		int number;
		number = indexOf(obj);
		if(number == -1){
			return false;
		}
		else
			
			return true;
	}
	/*Use indexOf method to get obj position
	 * use position number put into remove method 
	 */
	public boolean remove(Token obj)throws NullPointerException {
		if(obj==null){
			throw new NullPointerException();
		}
		int num = indexOf(obj);
		Token target = remove(num);
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + size;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DSLinkedList))
			return false;
		DSLinkedList other = (DSLinkedList) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
}
