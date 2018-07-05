/**
 * 
 */


/**
 * @author simont
 *
 */
public class GDSLinkedList<T> implements GList<T> {
	public GNode head= null;
	int size= 0;
	/* (non-Javadoc)
	 * @see Generics.GCollection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T obj)throws NullPointerException {
		//if node is empty create one
				if( obj ==null){
					throw new NullPointerException();
				}
				if(head==null){
					head = new GNode(null, null, obj);
					size++;
					return true;
				}
				else{
					//store current node
					GNode currentNode = head;
					// create a new node
					head = new GNode(head, null, obj);
					// link last previous node to the node after
					currentNode.previous = head;
					size++;
					return true;
				}
	}

	/* (non-Javadoc)
	 * @see Generics.GCollection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return head==null;
	}
	/* (non-Javadoc)
	 * @see Generics.GCollection#size()
	 */
	@Override
	public int size() {
		int count =0;
		GNode n=head;
		while(n!=null){
			count++;
			n=n.next;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see Generics.GList#add(int, java.lang.Object)
	 */
	@Override
	public boolean add(int index, T obj)throws NullPointerException,  IndexOutOfBoundsException {
		if(obj==null){
			throw new NullPointerException();
		}
		
		if(index<0 || index >size()){
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty()==true){
			add(obj);
		}
		else if(isEmpty()== false && index ==0){
			add(obj);
		}
			GNode currentIndex = head;
			int reversCall1 = size();
			reversCall1 = reversCall1-index;
		
			for(int i=0; i<reversCall1-1; i++){
				currentIndex = currentIndex.next;
			}
			T removed = (T)currentIndex.getObject();
		
			GNode currentPrevious = currentIndex.previous;
			GNode currentNext = currentIndex.next;
			boolean check;
			if(index == size()-1){
				check = true;
			}
			else 
				check = false;
			
			if(check == true){
			
				if(currentIndex.getObject().equals(obj)){
					return true;
				}
				add(obj);
				GNode current = head;
				int re = size();
				re = re-index-1;
				for(int i=0; i<re-1; i++){
					current = current.next;
				}
				T aa = (T)current.getObject();
				T bb = (T)current.next.getObject();
				current.next = new GNode(current.next.next, current.next.previous, aa);
				head = new GNode(current.next, null, bb);
				return true;
			}
			else if(currentPrevious == null && index>size()-1){
					add(obj);
					return true;
			
			}
			if(check == false){
				currentIndex = currentIndex.next;		
				// remove only if one index left
				if(removed.hashCode()!= obj.hashCode() && currentNext!=null){
					GNode newNode = new GNode(null, null, obj);
					currentIndex.previous.next = newNode;
					newNode.previous = currentIndex.previous;
					newNode.next = currentIndex;
					currentPrevious = newNode;
					return true;
				}
			}
			return true;
	}
	/* (non-Javadoc)
	 * @see Generics.GList#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(T obj)throws NullPointerException  {
		if(obj ==null){
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

	/* (non-Javadoc)
	 * @see Generics.GList#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(T obj)throws NullPointerException {
		if(obj==null){
			throw new NullPointerException();
		}
		int num = indexOf(obj);
		T target;
		if(num<0)
			return false;
		else
			
			target = remove(num);
			return true;
	}
	/* (non-Javadoc)
	 * @see Generics.GList#remove(int)
	 */
	@Override
	public T remove(int index)throws  IndexOutOfBoundsException {
		if(index > size() || index<0){
			throw new IndexOutOfBoundsException();
		}
		GNode currentIndex = head;
		int reversCall = size();
		reversCall = reversCall-index;
		for(int i=0; i<reversCall-1; i++){
			currentIndex = currentIndex.next;
		}
		T removed = (T) currentIndex.getObject();
		
		GNode currentPrevious = currentIndex.previous;
		GNode currentNext = currentIndex.next;
		
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

	/* (non-Javadoc)
	 * @see Generics.GList#get(int)
	 */
	@Override
	public T get(int index)throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		GNode node = head;
		int reversCall = size();
		reversCall = reversCall-index;
		for(int i=0; i<reversCall-1 && node !=null; i++){
			node = node.next;	
		}
		
		return (T)node.getObject() ;
	}
	/* (non-Javadoc)
	 * @see Generics.GList#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(T object)throws NullPointerException {
		if(object== null){
			throw new NullPointerException();
		}
		int positionIndex =-1;
		for(int i=0; i<size(); i++){
			T copy = get(i);
			if(copy.equals(object)){
				positionIndex = i;
				i=size();
			}
		}
		return positionIndex;
	}
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
		if (getClass() != obj.getClass())
			return false;
		GDSLinkedList other = (GDSLinkedList) obj;
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
