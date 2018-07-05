
import java.io.*;

/*
   The TreeNode class defines the nodes that make up the binary tree of dictionary 
   meanings. You may add methods, fields etc to this class but please DO NOT modify 
   the method signatures and fields definitions provided.
*/
public class TreeNode implements Serializable
{
    public String key;
    public String meaning;
    public SynonymList synonyms;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;
    public BinaryTree.LexicalCategory category;

    public TreeNode(TreeNode t){
    	this.key = t.key;
    	this.meaning = t.meaning;
    	this.synonyms = t.synonyms;
    	this.parent = t.parent;
    	this.left = t.left;
    	this.right = t.right;
    	this.category = t.category;
    }
    public TreeNode()
    {
        synonyms = new SynonymList();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public SynonymList getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(SynonymList synonyms) {
        this.synonyms = synonyms;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
    
    
    void insertSynonym(String synonym) {
        this.synonyms.insertSynonym(synonym);
    }

    public String[] getSynonymsArray() {
       return this.synonyms.getSynonyms();
    }
    
    

    //================== Please DO NOT modify the code below ======================
    public boolean equals(TreeNode node)
    {
        boolean retval = false;

        if (node != null) 
        {
            //check that both keys are null or they are both not null and equal
            if ((key == null) && (node.key == null))
            {
                retval = true;
            }
            else if ((key != null) && key.equals(node.key))
            {
                retval = true;
            }

            //check that both meanings are null or they are both not null and equal
            if (retval == true)
            {
                retval = false;

                if ((meaning == null) && (node.meaning == null))
                {
                    retval = true;
                }
                else if ((meaning != null) && meaning.equals(node.meaning))
                {
                    retval = true;
                }
            }

            //check that both synonym lists are null or they are both not null and equal
            if (retval == true)
            {
                retval = false;

                if ((synonyms == null) && (node.synonyms == null))
                {
                    retval = true;
                }
                else if ((synonyms != null) && synonyms.equals(node.synonyms))
                {
                    retval = true;
                }
            }
        }

        return retval;
    }



}
