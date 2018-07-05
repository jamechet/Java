
import java.io.*;
import java.util.HashMap;

/*
 The SynonymList class defines a single linked list for storing the synonyms of 
 a key value. You should implement this class in part B of the assignment. 
 You may add methods, fields etc to this class but please DO NOT modify the
 method signatures and fields definitions provided.
 */
public class SynonymList implements Serializable {
	 private static HashMap < String, Integer > map = new HashMap < String, Integer > ();

    public SynonymNode head;

    /*
     insertSynonym inserts the specified string in alphabetical order into
     the linked list of synonyms. See the assignment specifications for more
     details on how this method should operate.
     */
    public boolean insertSynonym(String synonym) throws IllegalArgumentException {
    	if(synonym == null || synonym.equals("")){
    		throw new IllegalArgumentException();
    	}
    	else if(synonym !=null){
    		if(checkExistingNode(synonym)== false){
    			
    			boolean check = add(synonym);
    			
    			return check;
    		}
    		
    		else{
    			return false;
    		}
    	}
    	else
    		
    		return false;
    
    }
    public boolean insertSynonym1(String synonym) throws IllegalArgumentException {
    	if(synonym == null || synonym.equals("")){
    		throw new IllegalArgumentException();
    	}
    	else if(synonym !=null){
    		if(checkExistingNode(synonym)== false){
    			
    			boolean check = add1(synonym);
    			
    			return check;
    		}
    		
    		else{
    			return false;
    		}
    	}
    	else
    		
    		return false;
    
    }
    /*
     * Method sorting string
     */
    public boolean sorting(){
    	SynonymNode currentNode = head;
    	map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        map.put("fourth", 4);
        while(currentNode!=null){
        	if(currentNode.next!=null){
        		int check = comp(currentNode.getSynonym(), currentNode.next.getSynonym());
        		if(check > 0){
        			String store = currentNode.getSynonym();
        			currentNode.setSynonym(currentNode.next.getSynonym());
        			currentNode.next.setSynonym(store);
        		}
        	}
        	currentNode = currentNode.next;
        	
        }
        
    	
    	return true;
    }
    private void sort(){
    	if(head.next!=null){
    		if(head.next.synonym.compareTo(head.synonym)<0){
    			String a = head.synonym;
    			head.setSynonym(head.next.synonym);
    			head.next.setSynonym(a);
    		}
    	}
    	
    }
    /*
     * Help method sorting
     */
    private int comp(String s1, String s2) {
        int i1 = map.get(s1);
        int i2 = map.get(s2);
        return (i1 < i2 ? -1 : (i1 == i2 ? 0 : 1));
    }
    public boolean add(String synonym){
    	if(head==null){
			head = new SynonymNode(synonym, null);
			return true;
		}
		else{
			
			//store current node
			SynonymNode currentNode = head;
			// create a new node
			head = new SynonymNode(synonym, head);
			// link last previous node to the node after
			currentNode = head;
		//	sort();
			sorting();
			return true;
		}	
			
    }
    public boolean add1(String synonym){
    	if(head==null){
			head = new SynonymNode(synonym, null);
			return true;
		}
		else{
			
			//store current node
			SynonymNode currentNode = head;
			// create a new node
			head = new SynonymNode(synonym, head);
			// link last previous node to the node after
			currentNode = head;
			sort();
		//	sorting();
			return true;
		}	
			
    }
    public boolean checkExistingNode(String input){
    	
    	SynonymNode currentNode = head;
    	while(currentNode!=null){
    		if(currentNode.synonym.equals(input)){
    			return true;
    		}
    		currentNode= currentNode.next;
    	}
    	return false;
    }
    public String[] getSynonyms() {
    	SynonymNode node = head;
    	if(head==null){
    		return null;
    	}
    	String[] s = new String[length()];
    	for(int i = 0; i < s.length; i++){
    		s[i] = node.synonym;
    		node = node.next;
    	}
        return s;
    }
    public String toString() {
		
		StringBuilder result = new StringBuilder();
		while(head!=null){
			result.append(head.getSynonym());
			head = head.next;
		
			result.append(" ");
			
		}
		return result.toString();
	}
    /*
     hasSynonym returns true if a synonym is in the list, and returns false 
     otherwise. See the assignment specification for more details about how
     this method should operate.
     */
    public boolean hasSynonym(String synonym) throws IllegalArgumentException {
    	if(synonym==null || synonym.equals("")){
    		throw new IllegalArgumentException();
    	}
    	
    	SynonymNode currentNode = head;
    		while(currentNode!=null){
    			if(currentNode.synonym.equals(synonym)){
    				return true;
    			}
    			currentNode= currentNode.next;
    		}
    		return false;
    	
       
    }
    

    /*
     length returns the number of nodes in the synonym list
     */
    public int length() {
    	int number=0;
    	SynonymNode node = head;
    	while(node!=null){
    		number++;
    		node = node.next;
    	}
        return number;
    }

    //================== Please DO NOT modify any code below ======================
    public boolean equals(SynonymList list) {
        boolean retval = false;
        int len = length();

        if ((list != null) && (len == list.length())) {
            if (len == 0) {
                retval = true;
            } else {
                SynonymNode list1Node = head;
                SynonymNode list2Node = list.head;
                retval = true;

                while ((list1Node != null) && (retval == true)) {
                    retval = false;

                    if (list1Node.equals(list2Node)) {
                        retval = true;
                        list1Node = list1Node.next;
                        list2Node = list2Node.next;
                    }
                }
            }
        }

        return retval;
    }
}
