
import java.io.*;

/*
   The SynonymNode class defines the nodes in the synonyms single linked list. 
   You may add methods, fields etc to this class but please DO NOT modify the
   method signatures and fields definitions provided.
*/
public class SynonymNode implements Serializable
{
    public String synonym;
    public SynonymNode next;
    
    public SynonymNode(){
    	
    }
    public SynonymNode(String input){
    	synonym = input;
    	next = null;
    }
    public SynonymNode(String input, SynonymNode nodeRef){
    	synonym = input;
    	next = nodeRef;
    }
    public String getSynonym() {
    	return synonym;
    }

    public void setSynonym(String synonym) {
    	this.synonym = synonym;
        
    }

    public SynonymNode getNext() {
        return new SynonymNode();
    }

    public void setNext(SynonymNode next) {
    	this.next = next;
    }
  
    
    //================== Please DO NOT modify the code below ======================
    public boolean equals(SynonymNode node)
    {
        boolean retval = false;

        if ((node != null) && (synonym != null))
        {
            retval = synonym.equals(node.synonym);
        }

        return retval;
    }
}
