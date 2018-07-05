
import java.io.*;
import java.util.ArrayList;

/*
 The BinaryTree class is the data structure that the dictionary uses to store 
 words (key values) and their meanings. You have to implement the functions in 
 this class. The assignment specification describes in detail how each of the 
 methods in this class operate. You may add methods, fields etc to this class 
 but please DO NOT modify the method signatures and fields definitions provided.
 */
public class BinaryTree implements Serializable {

    public static final int KEY_VALUE_NOT_FOUND = -1;
    public TreeNode root ;
    private TreeNode findRoot = new TreeNode();
    private int positionNumber=0;
    
    
    //see http://en.wikipedia.org/wiki/Lexical_category for more information
    public enum LexicalCategory { NOUN, PRONOUN, ADJECTIVE, VERB, ADVERB, PREPOSITION, CONJUNCTION, INTERJECTION };

    public boolean setCategory(String key, LexicalCategory category) throws IllegalArgumentException
    {
    	if(key==null || key.equals("") || category== null){
    		throw new IllegalArgumentException();
    	}
    	TreeNode node = findNode(key);
    	if(node!=null){
    		node.category= category;
    		return true;
    	}
        return false;
    }
    
    public LexicalCategory getCategory(String key) throws IllegalArgumentException
    {
    	if(key==null || key.equals("")){
    		throw new IllegalArgumentException();
    	}
        return findNode(key).category;
    }
    
    /**
     *  Find the Nth instance of a type of a word in a string. All words will be space-separated
     *  e.g. finding the 2nd noun (instance = 2, category = NOUN) in the sentence "the cat jumped on the hat" should return the "hat" TreeNode
     *  if a an instance of a given type of word is not found, it should return null
     * 
     *  @param sentence Sentence containing the collection of space-separated words to search
     *  @param category LexicalCategory of the word to find
     *  @param instance Which occurrence of the word to return, i.e. the 1st, 2nd, etc. (the first index of a word is instance 1, NOT 0)
     *  @return TreeNode for the given word
     */
    public TreeNode findInstanceOfWordType(String sentence, LexicalCategory category, int instance)
    {
    	TreeNode node = new TreeNode();
    	int number =1;
    	String [] store = sentence.split(" ");
    	for(int i=0; i<store.length; i++){
    		if(findNode(store[i])!=null){
    			
    			if(findNode(store[i]).category== category && number==instance){
        			return findNode(store[i]);
        		}
    			if(findNode(store[i]).category== category){
    				number++;
    			}
    		}
    	}
        return null;
    }
  
	public BinaryTree(){
		findRoot = root;
	}
   
    public boolean add(String key){
    	if(root == null){
    		root= new TreeNode();
    		root.setKey(key);
    		findRoot= root;
    		return true;
    	}
    	else if(root!=null){
    		if(root.key.compareTo(key)== 0){
    			return true;
    		}
    		else if(root.key.compareTo(key)>0){
    			
    			root.left = new TreeNode();
    			root.left.key = key;
    			findRoot = root;
    			return true;
    		}
    		else {
    			root.right = new TreeNode();
    			root.right.key =key;
    			findRoot = root;
    			return true;
    		}
    	}
    	
    	return false;
    }
    public void preOrderTravers(TreeNode e, int depth, StringBuilder sb){
    	
    	for(int i=0; i< depth; i++){
    		sb.append(" ");
    	}
    	if(e == null){
    		sb.append("null\n");
    		
    	}
    	else{
    		sb.append(e.meaning);
    		
    		sb.append("\n");
    		preOrderTravers(e.left, depth +1, sb);
    		preOrderTravers(e.right, depth + 1, sb);
    			
    	}
    }
    public String toString(){
    	StringBuilder sb = new StringBuilder();
    	preOrderTravers(root, 1, sb);
    	return sb.toString();
    }
    
    
    public TreeNode findNode(String key) throws IllegalArgumentException {
    	findRoot = root;
    	TreeNode c = findNodeCopy(key);
    	return c;
    	
    }
 public TreeNode findNodeCopy(String key) throws IllegalArgumentException {
    	
    	if( key ==null){
    		throw new IllegalArgumentException();
    	}
    	else if(findRoot==null){
    		
    		return null;
    	}
    	else{
    		if(key.compareTo(findRoot.key)==0){
    			
    			return findRoot;
    		}
    		else if(key.compareTo(findRoot.key)<0){
    			findRoot = findRoot.left;
    			positionNumber= positionNumber+1;
    			return findNodeCopy(key);
    		}
    		else{
    			findRoot = findRoot.right;
    			
    			return findNodeCopy(key);
    		}		
    	}
    }
    

    public boolean insertWord(String key, String meaning) throws IllegalArgumentException {
    	TreeNode newNode = new TreeNode();
		newNode.setKey(key);
		newNode.setMeaning(meaning);
    	if( key == null || meaning== null || key.equals("")){
    		throw new IllegalArgumentException();
    	}
    	if(root == null){
    		root = newNode;
    		return true;
    		
   	}
    	else if( findNode(key)!=null){
    		return false;
    	}
    	else{
    		TreeNode focusNode = root;
    		TreeNode parent;
    		boolean check = false;
    		while(check ==false){
    			parent = focusNode;
    			if(key.compareTo(focusNode.key)<0){
    				focusNode = focusNode.left;
    				if(focusNode == null){
    					parent.left = newNode;
    					check = true;
    					return true;
    				}
    			}else {
    				focusNode = focusNode.right;
    			
    				if(focusNode == null){
    	    			parent.right = newNode;
    	    			check = true;
    	    			return true;
    	    		}
    				
    			}
    		}
    		return false;
    	} 
    	
    }
    

    public String getMeaning(String key) throws IllegalArgumentException {
    	if(key == null){
    		throw new IllegalArgumentException();
    	}
    	else if(findNode(key)==null){
    		return null;
    	}
    	else if(findNode(key)!=null){
    		return findNode(key).getMeaning();
    	}
    	return "emptyString";
    }
    

    public int countWordsGreater(String key) throws IllegalArgumentException {
    	if(key == null){
    		throw new IllegalArgumentException();
    	}
    	if(findNode(key)==null){
    		return -1;
    	}
    	if(findNode(key)!=null){
    		TreeNode focusNode = root;
    		TreeNode parent;
    		TreeNode newNode = new TreeNode();
    		boolean check = false;
    		while(check ==false){
    			focusNode = focusNode.right;
    			if(focusNode!= null){
    				newNode = focusNode;
    				
    			}
    			else
    				check = true;
    		}
    		if(key.compareTo(newNode.key)==0){
    			return 0;
    		}
    		
    		
    		
    		
    	}
    	return 2;
    }

    
    public int countWordsLess(String key) throws IllegalArgumentException {
    	if(key == null){
    		throw new IllegalArgumentException();
    	}
    	if(findNode(key)==null){
    		return -1;
    	}
    	else if(findNode(key)!=null){
    		TreeNode focusNode = root;
    		TreeNode parent;
    		TreeNode newNode = new TreeNode();
    		boolean check = false;
    		while(check ==false){
    			focusNode = focusNode.left;
    			if(focusNode!= null){
    				newNode = focusNode;
    				
    			}
    			else
    				check = true;
    		}
    		if(key.compareTo(newNode.key)==0){
    			return 0;
    		}
    	}
    	return 1;
    }

    public boolean helpRemove(TreeNode node, TreeNode parent, String key){
    	if( node.key.compareTo(key)!=0){
    		parent = node;
    		if(key.compareTo(node.key)<0){
    			
    		}
    	}
    	return true;
    }
    public boolean removeWord(String key) throws IllegalArgumentException {
    	
    	if(key==null || key.equals("")){
    		throw new IllegalArgumentException();
    	}
    	else if(findNode(key)==null){
    		return false;
    	}
    	
  /*
    	else{
    		TreeNode focusNode = root;
        	TreeNode parent = root;
        	boolean leftChild = true;
        	while(focusNode.key.compareTo(key)!=0){
        		parent = focusNode;
        		if(key.compareTo(focusNode.key)<0){
        			leftChild = true;
        			focusNode = focusNode.left;
        		}
        		else{
        			leftChild = false;
        			focusNode = focusNode.right;
        		}
        		if(focusNode == null){
        			return false;
        		}
        		
        		
        	}
        	if(focusNode.left == null && focusNode.right==null){
        		if(focusNode.equals(root)){
        			root = null;
        		}else if(leftChild){
        			parent.left = null;
        		}else{
        			parent.right = null;
        		}
        	}
        	else if( focusNode.right == null){
        		if(focusNode.equals(root)){
        			root = focusNode.left;
        		}
        		else if(leftChild){
        			parent.left = focusNode.left;
        		}
        		else{
        			parent.right = focusNode.left;
        		}
        	}
        	else if(focusNode.left == null){
        		if(focusNode.equals(root)){
        			root = focusNode.right;
        		}
        		else if (leftChild){
        			parent.left = focusNode.right;
        		}
        		else{
        			parent.right = focusNode.left;
        		}
        		
        	}
        	else{
        		TreeNode replacement = getReplacementNode(focusNode);
        		if(focusNode.equals(root)){
        			root = replacement;
        		}
        		else if(leftChild){
        			parent.left = replacement;
        		}
        		else{
        			parent.right = replacement;
        			replacement.left = focusNode.left;
        		}
        		
        	}
     */
        	return removeWordHelper(key,root, root.parent);
    	
    	
    }
    public TreeNode getReplacementNode( TreeNode replaceNode ){
    	TreeNode replacementParent = replaceNode;
    	TreeNode replacement = replaceNode;
    	TreeNode focusNode = replaceNode.right;
    	while(focusNode!=null){
    		replacementParent = replacement;
    		replacement = focusNode;
    		focusNode = focusNode.left;
    	}
    	if(!(replacement.equals(replaceNode.right))){
    		replacementParent.left = replacement.right;
    		replacement.right = replaceNode.right;
    	}
    	return replacement;
    }

    public boolean addSynonym(String key, String synonym) throws IllegalArgumentException {
    	if(key == null || key.equals("") || synonym == null || synonym.equals("")){
    		throw new IllegalArgumentException();
    	}
    	
    	else if (findNode(key)!=null){
    			TreeNode focusNode = root;
        		TreeNode parent;
        		boolean check = false;
        		while(check ==false){
        			parent = focusNode;
        			if(key.compareTo(focusNode.key)<0){
        				focusNode = focusNode.left;
        				if(focusNode.getKey().compareTo(key)==0){
        					if(focusNode.getSynonyms().hasSynonym(synonym)== true){
        						return false;
        					}
        					else{
        						focusNode.synonyms.insertSynonym1(synonym);
        						check = true;
        						return true;
        					}
        				}
        			}else {
        				focusNode = focusNode.right;
        			
        				if(focusNode == null){
        	    			focusNode.synonyms.insertSynonym1(synonym);
        	    			check = true;
        	    			return true;
        	    		}
        				
        			}
        		}
        		
    		return true;
    		}
    	else
    		return false;
		
    		
    	
       
    }

    
    public String[] getSynonyms(String key) throws IllegalArgumentException {
    
    	
    	if(key==null || key.equals("")){
    		throw new IllegalArgumentException();
    	}
        TreeNode node = findNode(key);
        if(node==null){
        	return null;
        }
        else
        
        	return node.synonyms.getSynonyms();
    	
    }
    

    public BinaryTree balanceTree() {
    	BinaryTree newTree = new BinaryTree();
    	ArrayList<TreeNode> array = new ArrayList<TreeNode>();
    	inOrderTraversal(root, array);
    	balanceTreeHelper(newTree, array);
        return newTree;      //return tree
    }

    
    public String[] findRelatedWords(String synonym) throws IllegalArgumentException {
    	TreeNode node = root;
    	ArrayList<String>  array= new ArrayList<String>();
    	if(synonym==null || synonym.equals("")){
    		throw new IllegalArgumentException();
    	}
    	 checkSynonym(node, synonym,array);
    	 if(array.isEmpty()){
    		 return null;
    	 }
    	 String[] a = array.toArray(new String[array.size()]);
    	return a;
    
    	
    }
    private void checkSynonym(TreeNode node, String synonym, ArrayList<String>  array){
    	if(node == null){
    		return;
    	}
    	else if(node.synonyms.hasSynonym(synonym)){
    		array.add(node.key);
    	}
    	checkSynonym(node.left, synonym, array);
    	checkSynonym(node.right, synonym, array);
    }
    //================== Please DO NOT modify the code below ======================
    public boolean equals(BinaryTree tree) {
        boolean retval = false;

        if (tree != null) {
            retval = compareTrees(root, tree.root);
        }

        return retval;
    }

    private boolean compareTrees(TreeNode node1, TreeNode node2) {
        boolean retval = false;

        if ((node1 == null) && (node2 == null)) {
            retval = true;
        } else if ((node1 != null) && (node2 != null)) {
            if (node1.equals(node2)) {
                if (compareTrees(node1.left, node2.left)
                        && compareTrees(node1.right, node2.right)) {
                    retval = true;
                }
            }
        }

        return retval;
    }

    
    private boolean InsertWordHelper(TreeNode current, TreeNode newnode) {

        //check for valid current node
        if (current == null) {
            return false;
        }

        //if key is already present
        if (current.getKey().equals(newnode.getKey())) {
            return false;
        } else if (current.getKey().compareTo(newnode.getKey()) > 0) {  //move to left
            if (current.getLeft() == null) {        //if no node at left
                current.setLeft(newnode);           //add new node at left
                return true;
            } else {
                return InsertWordHelper(current.getLeft(), newnode);    //recursive call
            }
        } else {
            //if no node at right
            if (current.getRight() == null) {
                current.setRight(newnode);      //add at right
                return true;
            } else {
                return InsertWordHelper(current.getRight(), newnode);   //recursive call
            }
        }
    }

    
    private String getMeaningHelper(TreeNode current, String key) {

        //stopping condition
        if (current == null) {
            return null;
        }

        if (current.getKey().equals(key)) { //key found
            return current.getMeaning();
        } else if (current.getKey().compareTo(key) > 0) {   //should be on left
            return getMeaningHelper(current.getLeft(), key);
        } else {
            return getMeaningHelper(current.getRight(), key);       //check on right
        }
    }

    
    private int CountWordsGreaterHelper(TreeNode current, String key) {

        if (current == null) {
            return 0;
        }

        int found = 0;

        //key found
        if (current.getKey().compareTo(key) > 0) {
            found = 1;
        }

        //recursive call on left and right 
        return CountWordsGreaterHelper(current.getLeft(), key) + found
                + CountWordsGreaterHelper(current.getRight(), key);
    }

    
    private int CountWordsLessHelper(TreeNode current, String key) {

        if (current == null) {
            return 0;
        }

        int found = 0;

        //key is found
        if (current.getKey().compareTo(key) < 0) {
            found = 1;
        }

        //recursive call on left and right
        return CountWordsLessHelper(current.getLeft(), key) + found
                + CountWordsLessHelper(current.getRight(), key);
    }

    
    private void findRelatedWordsHelper(String synonym, TreeNode current, ArrayList<String> arrlist) {

        if (current == null) {
            return;
        }

        //if synonym is found
        if (current.getSynonyms().hasSynonym(synonym)) {
            arrlist.add(current.getKey());      //add current to list
        }

        //check on left
        findRelatedWordsHelper(synonym, current.getLeft(), arrlist);
        findRelatedWordsHelper(synonym, current.getRight(), arrlist);   //check on right
    }

    
    private void inOrderTraversal(TreeNode current, ArrayList<TreeNode> list) {

        if (current != null) {  //stopping condition
            inOrderTraversal(current.getLeft(), list);  //check left
            list.add(current);                          //add current to list
            inOrderTraversal(current.getRight(), list); //check right
        }
    }
    

    private void balanceTreeHelper(BinaryTree retval, ArrayList<TreeNode> list) {

        if (list.isEmpty()) {       //stopping condition
            return;
        }

        int median = list.size() / 2;       //find median node

        //set left and right pointers to null
        list.get(median).setLeft(null);
        list.get(median).setRight(null);

        //if root is null, add at root
        if (retval.root == null) {
            retval.root = list.get(median);
        } else {
            retval.InsertWordHelper(retval.root, list.get(median)); //insert median node to tree
        }

        if (list.size() == 1) { //if only 1 node, stop
            return;
        }

        //create left and right sublists
        ArrayList<TreeNode> sublist1 = new ArrayList<TreeNode>();
        ArrayList<TreeNode> sublist2 = new ArrayList<TreeNode>();

        //add left sublist values
        for (int i = 0; i < median; ++i) {
            sublist1.add(list.get(i));
        }

        //add right sublist values
        for (int i = median + 1; i < list.size(); ++i) {
            sublist2.add(list.get(i));
        }

        //recursive call on left and right sublists
        balanceTreeHelper(retval, sublist1);
        balanceTreeHelper(retval, sublist2);
    }

    
    private boolean removeWordHelper(String key, TreeNode current, TreeNode parent) {

        //check on left
        if (current.getKey().compareTo(key) > 0) {

            if (current.getLeft() == null) {        //if no node at left
                return false;       //not found
            } else {
                return removeWordHelper(key, current.getLeft(), current);//call on left subtree
            }
        } else if (current.getKey().compareTo(key) < 0) {

            //if no node at right
            if (current.getRight() == null) {
                return false;       //not found
            } else {
                return removeWordHelper(key, current.getRight(), current); //call on right subtree
            }
        } else {

            //if node found has both left and right children
            if (current.getRight() != null && current.getLeft() != null) {

                //find minimum node from right subtree
                TreeNode minNode = getMinNode(current.getRight());

                //copy values to current node
                current.setKey(minNode.getKey());
                current.setMeaning(minNode.getMeaning());
                current.setSynonyms(minNode.getSynonyms());

                //remove min node from right subtree
                removeWordHelper(minNode.getKey(), current.getRight(), current);

            } else if (parent.getLeft() == current) {  //if current node is left child of parent 

                if (current.getLeft() == null) {        //if current has no left child
                    parent.setLeft(current.getRight()); //add current right child to parent left
                } else {
                    parent.setLeft(current.getLeft());
                }
            } else {

                if (current.getLeft() == null) {
                    parent.setRight(current.getRight());
                } else {
                    parent.setRight(current.getLeft());
                }
            }
            return true;
        }
    }

    private TreeNode getMinNode(TreeNode current) {

        if (current == null) {  //check for validity
            return null;
        }

        if (current.getLeft() == null) {    //if no node at left
            return current;                 //current is minimum node
        } else {
            return getMinNode(current.getLeft());   //recursive call
        }
    }
}
