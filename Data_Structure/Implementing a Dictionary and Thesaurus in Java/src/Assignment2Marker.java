import java.io.*;
import java.util.Random;

public class Assignment2Marker
{
    //constants for reading and writing binary trees to file
    private static final int NULL_POINTER = -1;
    private static final String NUM_SYNONYMS_PLACEHOLDER = "$";
    private static final int KEY_WORD_OFFSET = 0;
    private static final int KEY_MEANING_OFFSET = KEY_WORD_OFFSET + 1;
    private static final int NUM_SYNONYMS_OFFSET = KEY_MEANING_OFFSET + 1;

    //constants specifying the marks for each test case
    private static final float FIND_NODE_TC_1_MARK = 0.2f;
    private static final float FIND_NODE_TC_2_MARK = 0.2f;
    private static final float FIND_NODE_TC_3_MARK = 0.5f;
    private static final float FIND_NODE_TC_4_MARK = 3;
    private static final float MAX_FIND_NODE_MARK = 
        FIND_NODE_TC_1_MARK + FIND_NODE_TC_2_MARK + 
        FIND_NODE_TC_3_MARK + FIND_NODE_TC_4_MARK;

    private static final float INSERT_WORD_TC_1_MARK = 0.2f;
    private static final float INSERT_WORD_TC_2_MARK = 0.2f;
    private static final float INSERT_WORD_TC_3_MARK = 0.2f;
    private static final float INSERT_WORD_TC_4_MARK = 3;
    private static final float INSERT_WORD_TC_5_MARK = 4;
    private static final float INSERT_WORD_TC_6_MARK = 2;
    private static final float MAX_INSERT_WORD_MARK = 
        INSERT_WORD_TC_1_MARK + INSERT_WORD_TC_2_MARK + 
        INSERT_WORD_TC_3_MARK + INSERT_WORD_TC_4_MARK + 
        INSERT_WORD_TC_5_MARK + INSERT_WORD_TC_6_MARK;

    private static final float GET_MEANING_TC_1_MARK = 0.2f;
    private static final float GET_MEANING_TC_2_MARK = 0.2f;
    private static final float GET_MEANING_TC_3_MARK = 2;
    private static final float GET_MEANING_TC_4_MARK = 0.2f;
    private static final float MAX_GET_MEANING_MARK = 
        GET_MEANING_TC_1_MARK + GET_MEANING_TC_2_MARK + 
        GET_MEANING_TC_3_MARK + GET_MEANING_TC_4_MARK;

    private static final float COUNT_GREATER_TC_1_MARK = 0.2f;
    private static final float COUNT_GREATER_TC_2_MARK = 2;
    private static final float COUNT_GREATER_TC_3_MARK = 2;
    private static final float COUNT_GREATER_TC_4_MARK = 2;
    private static final float COUNT_GREATER_TC_5_MARK = 0.2f;
    private static final float MAX_COUNT_GREATER_MARK = 
        COUNT_GREATER_TC_1_MARK + COUNT_GREATER_TC_2_MARK + 
        COUNT_GREATER_TC_3_MARK + COUNT_GREATER_TC_4_MARK + 
        COUNT_GREATER_TC_5_MARK;

    private static final float COUNT_LESS_TC_1_MARK = 0.2f;
    private static final float COUNT_LESS_TC_2_MARK = 0.5f;
    private static final float COUNT_LESS_TC_3_MARK = 2;
    private static final float COUNT_LESS_TC_4_MARK = 2;
    private static final float COUNT_LESS_TC_5_MARK = 0.5f;
    private static final float MAX_COUNT_LESS_MARK = 
        COUNT_LESS_TC_1_MARK + COUNT_LESS_TC_2_MARK + 
        COUNT_LESS_TC_3_MARK + COUNT_LESS_TC_4_MARK + 
        COUNT_LESS_TC_5_MARK;

    private static final float REMOVE_WORD_TC_1_MARK = 0.2f;
    private static final float REMOVE_WORD_TC_2_MARK = 2;
    private static final float REMOVE_WORD_TC_3_MARK = 3;
    private static final float REMOVE_WORD_TC_4_MARK = 3;
    private static final float REMOVE_WORD_TC_5_MARK = 2;
    private static final float REMOVE_WORD_TC_6_MARK = 2;
    private static final float REMOVE_WORD_TC_7_MARK = 3;
    private static final float REMOVE_WORD_TC_8_MARK = 3;
    private static final float MAX_REMOVE_WORD_MARK = 
        REMOVE_WORD_TC_1_MARK + REMOVE_WORD_TC_2_MARK + 
        REMOVE_WORD_TC_3_MARK + REMOVE_WORD_TC_4_MARK + 
        REMOVE_WORD_TC_5_MARK + REMOVE_WORD_TC_6_MARK + 
        REMOVE_WORD_TC_7_MARK + REMOVE_WORD_TC_8_MARK;

    private static final float INSERT_SYNONYM_TC_1_MARK = 0.2f;
    private static final float INSERT_SYNONYM_TC_2_MARK = 0.2f;
    private static final float INSERT_SYNONYM_TC_3_MARK = 2;
    private static final float INSERT_SYNONYM_TC_4_MARK = 3;
    private static final float INSERT_SYNONYM_TC_5_MARK = 3;
    private static final float INSERT_SYNONYM_TC_6_MARK = 3;
    private static final float MAX_INSERT_SYNONYM_MARK = 
        INSERT_SYNONYM_TC_1_MARK + INSERT_SYNONYM_TC_2_MARK + 
        INSERT_SYNONYM_TC_3_MARK + INSERT_SYNONYM_TC_4_MARK + 
        INSERT_SYNONYM_TC_5_MARK + INSERT_SYNONYM_TC_6_MARK;

    private static final float ADD_SYNONYM_TC_1_MARK = 0.2f;
    private static final float ADD_SYNONYM_TC_2_MARK = 0.2f;
    private static final float ADD_SYNONYM_TC_3_MARK = 0.2f;
    private static final float ADD_SYNONYM_TC_4_MARK = 0.2f;
    private static final float ADD_SYNONYM_TC_5_MARK = 0.2f;
    private static final float ADD_SYNONYM_TC_6_MARK = 2;
    private static final float ADD_SYNONYM_TC_7_MARK = 2;
    private static final float MAX_ADD_SYNONYM_MARK = 
        ADD_SYNONYM_TC_1_MARK + ADD_SYNONYM_TC_2_MARK + 
        ADD_SYNONYM_TC_3_MARK + ADD_SYNONYM_TC_4_MARK + 
        ADD_SYNONYM_TC_5_MARK + ADD_SYNONYM_TC_6_MARK + 
        ADD_SYNONYM_TC_7_MARK;

    private static final float GET_SYNONYMS_TC_1_MARK = 0.2f;
    private static final float GET_SYNONYMS_TC_2_MARK = 0.2f;
    private static final float GET_SYNONYMS_TC_3_MARK = 3;
    private static final float GET_SYNONYMS_TC_4_MARK = 3;
    private static final float MAX_GET_SYNONYMS_MARK = 
        GET_SYNONYMS_TC_1_MARK + GET_SYNONYMS_TC_2_MARK + 
        GET_SYNONYMS_TC_3_MARK + GET_SYNONYMS_TC_4_MARK;

    private static final float FIND_RELATED_TC_1_MARK = 0.2f;
    private static final float FIND_RELATED_TC_2_MARK = 3.1f;
    private static final float FIND_RELATED_TC_3_MARK = 7;
    private static final float MAX_FIND_RELATED_MARK = 
        FIND_RELATED_TC_1_MARK + FIND_RELATED_TC_2_MARK + 
        FIND_RELATED_TC_3_MARK;

    private static final float FIND_WORD_OF_CATEGORY_TC_1_MARK = 2;
    private static final float FIND_WORD_OF_CATEGORY_TC_2_MARK = 3.f;
    private static final float FIND_WORD_OF_CATEGORY_TC_3_MARK = 4.f;
    private static final float FIND_WORD_OF_CATEGORY_TC_4_MARK = 4.f;
    private static final float FIND_WORD_OF_CATEGORY_TC_5_MARK = 4.f;
    private static final float FIND_WORD_OF_CATEGORY_MARK = 
        FIND_WORD_OF_CATEGORY_TC_1_MARK + FIND_WORD_OF_CATEGORY_TC_2_MARK + 
        FIND_WORD_OF_CATEGORY_TC_3_MARK + FIND_WORD_OF_CATEGORY_TC_4_MARK + 
        FIND_WORD_OF_CATEGORY_TC_5_MARK ;
    
    private static final float MAX_TOTAL_MARK = 
        MAX_FIND_NODE_MARK + MAX_INSERT_WORD_MARK + MAX_GET_MEANING_MARK + 
        MAX_COUNT_GREATER_MARK + MAX_COUNT_LESS_MARK + MAX_REMOVE_WORD_MARK + 
        MAX_INSERT_SYNONYM_MARK + MAX_ADD_SYNONYM_MARK + MAX_GET_SYNONYMS_MARK + 
        MAX_FIND_RELATED_MARK + FIND_WORD_OF_CATEGORY_MARK;

    //constants for each test case
    private static final String FIND_NODE_TC_3_FILENAME = 
        "findNodeTC3.txt";
    private static final String FIND_NODE_TC_3_SYNONYMS_FILENAME = 
        "findNodeTC3Synonyms.txt";
    private static final String FIND_NODE_TC_2_KEY = "non-existent key";
    private static final String FIND_NODE_TC_3_KEY = FIND_NODE_TC_2_KEY;
    private static final String FIND_NODE_TC_4_KEY = "car";
    private static final String FIND_NODE_TC_4_MEANING = 
        "a road vehicle powered by an internal combustion engine";

    private static final String INSERT_WORD_TC_4_FILENAME = 
        "insertWordTC4.txt";
    private static final String INSERT_WORD_TC_5_FILENAME = 
        "insertWordTC5.txt";
    private static final String INSERT_WORD_TC_6_FILENAME = 
        "insertWordTC6.txt";
    private static final String INSERT_WORD_TC_2_KEY = "mini";
    private static final String INSERT_WORD_TC_4_KEY = "sausage";
    private static final String INSERT_WORD_TC_1_MEANING = 
        "this is a valid string";
    private static final String INSERT_WORD_TC_3_MEANING = 
        INSERT_WORD_TC_1_MEANING;
    private static final String INSERT_WORD_TC_4_MEANING = 
        "a short cylindrical tube of minced meat";
    private static final String INSERT_WORD_TC_5_KEY = "watch";
    private static final String INSERT_WORD_TC_5_MEANING = 
        "timepiece worn on the wrist";
    private static final String INSERT_WORD_TC_6_KEY =
        INSERT_WORD_TC_5_KEY;
    private static final String INSERT_WORD_TC_6_MEANING = 
        INSERT_WORD_TC_5_MEANING;

    private static final String GET_MEANING_TC_3_FILENAME =
        "getMeaningTC3.txt";
    private static final String GET_MEANING_TC_2_KEY = 
        "sausage";
    private static final String GET_MEANING_TC_3_KEY = "sausage";
    private static final String GET_MEANING_TC_3_MEANING = 
        "a short cylindrical tube of minced meat";
    private static final String GET_MEANING_TC_4_KEY = 
        "non-existent key";

    private static final String COUNT_GREATER_TC_3_FILENAME = 
        "countGreaterTC3.txt";
    private static final String COUNT_GREATER_TC_2_KEY = 
        "non-existent key";
    private static final String COUNT_GREATER_TC_3_KEY = "sausage";
    private static final String COUNT_GREATER_TC_4_KEY = "football";
    private static final String COUNT_GREATER_TC_5_KEY = "football";
    private static final int COUNT_GREATER_TC_3_EXP_COUNT = 0;
    private static final int COUNT_GREATER_TC_4_EXP_COUNT = 2;

    private static final String COUNT_LESS_TC_3_FILENAME = 
        "countLessTC3.txt";
    private static final String COUNT_LESS_TC_2_KEY = 
        "non-existent key";
    private static final String COUNT_LESS_TC_3_KEY = "car";
    private static final String COUNT_LESS_TC_4_KEY = "football";
    private static final String COUNT_LESS_TC_5_KEY = "football";
    private static final int COUNT_LESS_TC_3_EXP_COUNT = 0;
    private static final int COUNT_LESS_TC_4_EXP_COUNT = 1;

    private static final String REMOVE_WORD_TC_3_FILENAME = 
        "removeWordTC3.txt";
    private static final String REMOVE_WORD_TC_4_FILENAME = 
        "removeWordTC4.txt";
    private static final String REMOVE_WORD_TC_5_FILENAME = 
        "removeWordTC5.txt";
    private static final String REMOVE_WORD_TC_6_FILENAME = 
        "removeWordTC6.txt";
    private static final String REMOVE_WORD_TC_7_FILENAME = 
        "removeWordTC7.txt";
    private static final String REMOVE_WORD_TC_8_FILENAME = 
        "removeWordTC8.txt";
    private static final String REMOVE_WORD_TC_4_EXP_TREE_FILENAME = 
        "removeWordTC4ExpTree.txt";
    private static final String REMOVE_WORD_TC_5_EXP_TREE_FILENAME = 
        "removeWordTC5ExpTree.txt";
    private static final String REMOVE_WORD_TC_6_EXP_TREE_FILENAME = 
        "removeWordTC6ExpTree.txt";
    private static final String REMOVE_WORD_TC_7_EXP_TREE_FILENAME = 
        "removeWordTC7ExpTree.txt";
    private static final String REMOVE_WORD_TC_8_EXP_TREE_FILENAME = 
        "removeWordTC8ExpTree.txt";
    private static final String REMOVE_WORD_TC_2_KEY = "motorcycle";
    private static final String REMOVE_WORD_TC_3_KEY = "motorcycle";
    private static final String REMOVE_WORD_TC_4_KEY = "car";
    private static final String REMOVE_WORD_TC_5_KEY = "football";
    private static final String REMOVE_WORD_TC_6_KEY = "inspection";
    private static final String REMOVE_WORD_TC_7_KEY = "inspection";
    private static final String REMOVE_WORD_TC_8_KEY = "hello";

    private static final String INSERT_SYNONYM_TC_3_FILENAME =
        "insertSynonymTC3.txt";
    private static final String INSERT_SYNONYM_TC_3_SYNONYM = "second";
    private static final String INSERT_SYNONYM_TC_5_FILENAME =
        "insertSynonymTC5.txt";
    private static final String INSERT_SYNONYM_TC_5_SYNONYM = "first";
    private static final String INSERT_SYNONYM_TC_6_FILENAME = 
        "insertSynonymTC6.txt";
    private static final String INSERT_SYNONYM_TC_6_SYNONYM = "third";

    private static final String ADD_SYNONYM_TC_1_SYNONYM = "a valid synonym";
    private static final String ADD_SYNONYMS_TC_2_KEY = "a valid key";
    private static final String ADD_SYNONYM_TC_3_SYNONYM = ADD_SYNONYM_TC_1_SYNONYM;
    private static final String ADD_SYNONYM_TC_4_KEY = ADD_SYNONYMS_TC_2_KEY;
    private static final String ADD_SYNONYM_TC_5_FILENAME = "addSynonymTC5.txt";
    private static final String ADD_SYNONYM_TC_5_EXP_TREE_FILENAME = 
        "addSynonymTC5ExpTree.txt";
    private static final String ADD_SYNONYM_TC_5_KEY = "car";
    private static final String ADD_SYNONYM_TC_5_SYNONYM = "motor vehicle";
    private static final String ADD_SYNONYM_TC_6_KEY = "car";
    private static final String ADD_SYNONYM_TC_6_SYNONYM = "motor vehicle";
    private static final String ADD_SYNONYM_TC_7_FILENAME = "addSynonymTC7.txt";
    private static final String ADD_SYNONYM_TC_7_KEY = "truck";
    private static final String ADD_SYNONYM_TC_7_SYNONYM = "vehicle";

    private static final String GET_SYNONYMS_TC_2_KEY = "non-existent key";
    private static final String GET_SYNONYMS_TC_3_KEY = "car";
    private static final String GET_SYNONYMS_TC_3_EXP_RETVAL[] = 
    {"motor vehicle","vehicle"};
    private static final String GET_SYNONYMS_TC_3_FILENAME = "getSynonymsTC3.txt";
    private static final String GET_SYNONYMS_TC_4_KEY = "sausage";

    private static final String FIND_RELATED_TC_2_SYNONYM = "broom-broom";
    private static final String FIND_RELATED_TC_3_EXP_RETVAL[] = 
    {"inspection","test"};
    private static final String FIND_RELATED_TC_3_SYNONYM = "examination";
    private static final String FIND_RELATED_TC_2_FILENAME = 
        "findRelatedTC2.txt";
    private static final String FIND_RELATED_TC_3_FILENAME = 
        "findRelatedTC3.txt";

    //marks for each method
    private float findNodeMark;
    private float insertWordMark;
    private float getMeaningMark;
    private float countGreaterMark;
    private float countLessMark;
    private float removeWordMark;
    private float insertSynonymMark;
    private float addSynonymMark;
    private float getSynonymsMark;
    private float findRelatedMark;


    /*
        Preconditions: filename not null
    */
    private BinaryTree readBinaryTree(String filename)
    {
        assert(filename != null);
        BinaryTree retval = null;
        BinaryTree tree = null;
        String line = null;
        int numLines = 0;
        String buff[] = null;
        String tokens[] = null;
        TreeNode wrkspce[] = null;
        SynonymNode synonymNode = null;
        SynonymNode tail = null;
        int numSynonyms = 0;
        int leftChildIndx = 0;
        int rightChildIndx = 0;
        int tokenOffset = 0;

        try
        {
            BufferedReader reader = 
                new BufferedReader(new FileReader(filename));
            line = reader.readLine();

            if (line != null)
            {
                numLines = Integer.parseInt(line);

                if (numLines > 0)
                {
                    buff = new String[numLines];
                    wrkspce = new TreeNode[numLines];

                    for (int offset = 0; offset < numLines; offset++)
                    {
                        buff[offset] = reader.readLine();
                    }

                    for (int offset = buff.length-1; offset >= 0; offset--)
                    {
                        tokens = buff[offset].split("\\|");
                        wrkspce[offset] = new TreeNode();
                        wrkspce[offset].key = tokens[KEY_WORD_OFFSET];
                        wrkspce[offset].meaning = tokens[KEY_MEANING_OFFSET];
                        numSynonyms = Integer.parseInt(tokens[NUM_SYNONYMS_OFFSET]);
                        tokenOffset = NUM_SYNONYMS_OFFSET + 1;
                        tail = null;

                        if (numSynonyms > 0)
                        {
                            //create synonyms list
                            for (; 
                                    tokenOffset < NUM_SYNONYMS_OFFSET + numSynonyms + 1; 
                                    tokenOffset++)
                            {
                                synonymNode = new SynonymNode();
                                synonymNode.synonym = tokens[tokenOffset];

                                if (wrkspce[offset].synonyms.head == null)
                                {
                                    wrkspce[offset].synonyms.head = synonymNode;
                                }
                                else
                                {
                                    tail.next = synonymNode;
                                }

                                tail = synonymNode;
                            }
                        }

                        leftChildIndx = Integer.parseInt(tokens[tokenOffset++]);

                        if (leftChildIndx >= 0)
                        {
                            wrkspce[offset].left = wrkspce[leftChildIndx];
                            wrkspce[leftChildIndx].parent = wrkspce[offset];
                        }

                        rightChildIndx = Integer.parseInt(tokens[tokenOffset]);

                        if (rightChildIndx >= 0)
                        {
                            wrkspce[offset].right = wrkspce[rightChildIndx];
                            wrkspce[rightChildIndx].parent = wrkspce[offset];
                        }
                    }
                }

                tree = new BinaryTree();
                tree.root = wrkspce[0];
                retval = tree;
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return retval;
    }


    /*
        Preconditions: filename not null. The len arg is optional - but if provided then
                       it must contain at least one element
    */
    private SynonymList readSynonymList(String filename, int len[])
    {
        assert((filename != null) && ((len == null) || (len.length > 0)));
        SynonymList retval = null;
        SynonymList synonyms = new SynonymList();
        SynonymNode node = null;
        SynonymNode tail = null;

        try 
        {
            BufferedReader reader = 
                new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            reader.close();

            if (line != null)
            {
                String tokens[] = line.split("\\|");
                int tokenOffset = 0;
                int numSynonyms = Integer.parseInt(tokens[tokenOffset++]);

                if (len != null)
                {
                    len[0] = numSynonyms;
                }

                if (numSynonyms > 0)
                {
                    //create synonyms list
                    for (; tokenOffset < numSynonyms + 1; tokenOffset++)
                    {
                        node = new SynonymNode();
                        node.synonym = tokens[tokenOffset];

                        if (synonyms.head == null)
                        {
                            synonyms.head = node;
                        }
                        else
                        {
                            tail.next = node;
                        }

                        tail = node;
                    }

                    retval = synonyms;
                }
            }

            reader.close();

        }
        catch (Exception e)
        {
            System.out.println("Error reading synonym list from file: ");
            e.printStackTrace();
        }

        return retval;
    }


    /*
        toArray reads a synonym list from the specified file and returns the 
        synonyms in an array.

        Preconditions: filename not null
    */
    private String[] toArray(String filename)
    {
        assert(filename != null);
        String retval[] = null;
        int numSynonyms[] = {0};

        SynonymList synonyms = readSynonymList(filename,numSynonyms);

        if ((synonyms != null) && (numSynonyms[0] > 0))
        {
            retval = new String[numSynonyms[0]];
            SynonymNode current = synonyms.head;
            int offset = 0;

            while (current != null)
            {
                retval[offset++] = current.synonym;
                current = current.next;
            }
        }

        return retval;
    }


    private int getMaxDepth(TreeNode node, int depth)
    {
        int maxDepth = depth;

        if (node != null)
        {
            int leftMaxDepth = getMaxDepth(node.left,depth+1);

            if (leftMaxDepth > maxDepth)
            {
                maxDepth = leftMaxDepth;
            }

            int rightMaxDepth = getMaxDepth(node.right,depth+1);

            if (rightMaxDepth > maxDepth)
            {
                maxDepth = rightMaxDepth;
            }
        }

        return maxDepth;
    }


    private boolean equals(String buff1[], String buff2[])
    {
        boolean retval = false;

        if ((buff1 == null) && (buff2 == null))
        {
            retval = true;
        }
        else if ((buff1 != null) && (buff2 != null))
        {
            if (buff1.length == buff2.length)
            {
                int offset = 0;
                boolean valid = true;

                while (valid && (offset < buff1.length))
                {
                    valid = false;

                    if (((buff1[offset] == null) && (buff2[offset] == null)) || 
                            ((buff1[offset] != null) && 
                             (buff1[offset].equals(buff2[offset]))))
                    {
                        valid = true;
                    }

                    offset++;
                }

                if (valid)
                {
                    retval = true;
                }
            }
        }

        return retval;
    }



    public void markFindNodeMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking findNode method of BinaryTree");

        findNodeMark = 0;
        BinaryTree tree = new BinaryTree();
        SynonymList synonyms = null;
        TreeNode retval = null;
        TreeNode exp_retval = null;

        System.out.println("running findNode test case #1 - testing that findNode " + 
                "throws IllegalArgumentException when null argument passed");
        try
        {
            tree.findNode(null);
            System.out.println("your findNode method did not throw an exception");
            System.out.println("findNode test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("findNode test case #1 passed");
            findNodeMark += FIND_NODE_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your findNode method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("findNode test case #1 failed");
        }

        System.out.println("running findNode test case #2 - testing that findNode " + 
                "returns null and does not throw an exception when search for a non " + 
                "existent key value in an empty tree");
        try
        {
            retval = tree.findNode(FIND_NODE_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.println("findNode test case #2 passed");
                findNodeMark += FIND_NODE_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected null retval, but got: %s instead\n",
                        retval);
                System.out.println("findNode test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your findNode method threw the exception:");
            e.printStackTrace();
            System.out.println("findNode test case #2 failed");
        }


        if (((tree = readBinaryTree(FIND_NODE_TC_3_FILENAME)) != null) && 
            ((synonyms = readSynonymList(FIND_NODE_TC_3_SYNONYMS_FILENAME,null)) != null))
        {
            System.out.println("running findNode test case #3 - testing that findNode " + 
                    "returns null and does not throw an exception when search for a non " + 
                    "existent key value in a non-empty tree");
            try
            {
                retval = tree.findNode(FIND_NODE_TC_3_KEY);

                if (retval == exp_retval)
                {
                    System.out.println("findNode test case #3 passed");
                    findNodeMark += FIND_NODE_TC_3_MARK;
                }
                else
                {
                    System.out.printf("expected null retval, but got: %s instead\n",
                            retval);
                    System.out.println("findNode test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your findNode method threw the exception:");
                e.printStackTrace();
                System.out.println("findNode test case #3 failed");
            }

            System.out.println("running findNode test case #4 - testing that findNode " + 
                    "finds the node in a non-empty tree");
            try
            {
                exp_retval = new TreeNode();
                exp_retval.key = FIND_NODE_TC_4_KEY;
                exp_retval.meaning = FIND_NODE_TC_4_MEANING;
                exp_retval.synonyms = synonyms;
                retval = tree.findNode(FIND_NODE_TC_4_KEY);

                if (exp_retval.equals(retval))
                {
                    System.out.println("findNode test case #4 passed");
                    findNodeMark += FIND_NODE_TC_4_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %s, but got: %s instead\n",
                            exp_retval, retval);
                    System.out.println("findNode test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your findNode method threw the exception:");
                e.printStackTrace();
                System.out.println("findNode test case #4 failed");
            }
        }
        else
        {
            System.out.printf("findNode test case #3 and #4 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", FIND_NODE_TC_3_FILENAME,
                    FIND_NODE_TC_3_SYNONYMS_FILENAME);
        }

        System.out.println("findNode method marking completed");
        System.out.println("====================================================");
    }


    public void markInsertWordMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking insertWord method of BinaryTree");

        System.out.println("running insertWord test case #1 - testing that insertWord " + 
                "throws IllegalArgumentException when null argument passed");
        BinaryTree tree = new BinaryTree();
        insertWordMark = 0;

        try
        {
            tree.insertWord(null,INSERT_WORD_TC_1_MEANING);
            System.out.println("your insertWord method did not throw an exception");
            System.out.println("insertWord test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("insertWord test case #1 passed");
            insertWordMark += INSERT_WORD_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your insertWord method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("insertWord test case #1 failed");
        }

        System.out.println("running insertWord test case #2 - testing that insertWord " + 
                "throws IllegalArgumentException when null argument passed");
        try
        {
            tree.insertWord(INSERT_WORD_TC_2_KEY,null);
            System.out.println("your insertWord method did not throw an exception");
            System.out.println("insertWord test case #2 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("insertWord test case #2 passed");
            insertWordMark += INSERT_WORD_TC_2_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your insertWord method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("insertWord test case #2 failed");
        }

        System.out.println("running insertWord test case #3 - testing that insertWord " + 
                "throws IllegalArgumentException when \"\" argument passed as key value");
        try
        {
            tree.insertWord("",INSERT_WORD_TC_3_MEANING);
            System.out.println("your insertWord method did not throw an exception");
            System.out.println("insertWord test case #3 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("insertWord test case #3 passed");
            insertWordMark += INSERT_WORD_TC_3_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your insertWord method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("insertWord test case #3 failed");
        }

        boolean exp_retval = false;
        boolean retval;
        tree = null;
        BinaryTree exp_tree;

        if ((tree = readBinaryTree(INSERT_WORD_TC_4_FILENAME)) != null)
        {
            System.out.println("running insertWord test case #4 - testing that insertWord " + 
                    "does not add a word already in the tree");
            try 
            {
                retval = 
                    tree.insertWord(INSERT_WORD_TC_4_KEY,INSERT_WORD_TC_4_MEANING);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);
                    exp_tree = readBinaryTree(INSERT_WORD_TC_4_FILENAME);

                    if (tree.equals(exp_tree))
                    {
                        System.out.println("insertWord test case #4 passed");
                        insertWordMark += INSERT_WORD_TC_4_MARK;
                    }
                    else
                    {
                        System.out.println("your insertWord method returned false" + 
                                " but it appears to have modified the tree in some way");
                        System.out.println("insertWord test case #4 failed");
                    }

                    System.out.println("running insertWord test case #5 - testing that insertWord " + 
                            "correctly adds a word not already in a non-empty tree");
                    exp_retval = true;

                    try 
                    {
                        retval = 
                            tree.insertWord(INSERT_WORD_TC_5_KEY,
                                    INSERT_WORD_TC_5_MEANING);

                        if (retval == exp_retval)
                        {
                            System.out.printf("expected retval: %b and got: %b\n",
                                    exp_retval,retval);
                            exp_tree = readBinaryTree(INSERT_WORD_TC_5_FILENAME);

                            if (tree.equals(exp_tree))
                            {
                                System.out.println("insertWord test case #5 passed");
                                insertWordMark += INSERT_WORD_TC_5_MARK;
                            }
                            else
                            {
                                System.out.println("your insertWord method did not add the new" + 
                                        " word correctly");
                                System.out.println("insertWord test case #5 failed");
                            }
                        }
                        else
                        {
                            System.out.printf("expected retval: %b but got: %b instead\n",
                                    exp_retval,retval);
                            System.out.println("insertWord test case #5 failed");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("your insertWord method threw the following " + 
                                "exception: ");
                        e.printStackTrace();
                        System.out.println("insertWord test case #5 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("insertWord test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your insertWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("insertWord test case #4 failed");
            }
        }
        else
        {
            System.out.printf("insertWord test case #4 and #5 blocked - " +
                    "cannot read the file: %s\n", INSERT_WORD_TC_4_FILENAME);
        }

        System.out.println("running insertWord test case #6 - testing that insertWord " + 
                "correctly adds a word in an empty tree");
        exp_retval = true;
        tree = new BinaryTree();

        if ((exp_tree = readBinaryTree(INSERT_WORD_TC_6_FILENAME)) != null)
        {
            try 
            {
                retval = 
                    tree.insertWord(INSERT_WORD_TC_6_KEY,INSERT_WORD_TC_6_MEANING);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("insertWord test case #6 passed");
                        insertWordMark += INSERT_WORD_TC_6_MARK;
                    }
                    else
                    {
                        System.out.printf("your insertWord method returned %b " + 
                                "but did not add the new word correctly\n", 
                                exp_retval);
                        System.out.println("insertWord test case #6 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("insertWord test case #6 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your insertWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("insertWord test case #6 failed");
            }
        }
        else
        {
            System.out.printf("insertWord test case #6 blocked - " +
                    "cannot read the file: %s\n", INSERT_WORD_TC_6_FILENAME);
        }

        System.out.println("insertWord marking completed");
        System.out.println("====================================================");
    }


    public void markGetMeaningMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking getMeaning method of BinaryTree");

        System.out.println("running getMeaning test case #1 - testing that getMeaning " + 
                "throws IllegalArgumentException when null argument passed");
        BinaryTree tree = new BinaryTree();
        getMeaningMark = 0; 

        try
        {
            tree.getMeaning(null);
            System.out.println("your getMeaning method did not throw an exception");
            System.out.println("getMeaning test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("getMeaning test case #1 passed");
            getMeaningMark += GET_MEANING_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your getMeaning method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("getMeaning test case #1 failed");
        }

        System.out.println("running getMeaning test case #2 - testing that getMeaning " + 
                "returns null when the key value does not exist in empty tree");
        String retval;
        String exp_retval  = null;

        try
        {
            retval = tree.getMeaning(GET_MEANING_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.println("getMeaning test case #2 passed");
                getMeaningMark += GET_MEANING_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected retval: %s but got: %s instead\n",
                        exp_retval,retval);
                System.out.println("getMeaning test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your getMeaning method threw the following " + 
                    "exception: ");
            e.printStackTrace();
            System.out.println("getMeaning test case #2 failed");
        }

        if ((tree = readBinaryTree(GET_MEANING_TC_3_FILENAME)) != null)
        {
            System.out.println("running getMeaning test case #3 - testing that getMeaning " + 
                    "returns the correct meaning for a key value in the tree");
            exp_retval = GET_MEANING_TC_3_MEANING;
            try 
            {
                retval = tree.getMeaning(GET_MEANING_TC_3_KEY);

                if (exp_retval.equals(retval))
                {
                    System.out.printf("expected retval: %s and got: %s\n",
                            exp_retval,retval);
                    System.out.println("getMeaning test case #3 passed");
                    getMeaningMark += GET_MEANING_TC_3_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %s but got: %s instead\n",
                            exp_retval,retval);
                    System.out.println("getMeaning test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your getMeaning method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("getMeaning test case #3 failed");
            }

            System.out.println("running getMeaning test case #4 - testing that " + 
                    "getMeaning returns null when key value does not exist in " + 
                    "non-empty tree");
            exp_retval = null;

            try 
            {
                retval = tree.getMeaning(GET_MEANING_TC_4_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %s and got: %s\n",
                            exp_retval,retval);
                    System.out.println("getMeaning test case #4 passed");
                    getMeaningMark += GET_MEANING_TC_4_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %s but got: %s instead\n",
                            exp_retval,retval);
                    System.out.println("getMeaning test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your getMeaning method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("getMeaning test case #4 failed");
            }
        }
        else
        {
            System.out.printf("getMeaning test case #3 and #4 blocked - " +
                    "cannot read the file: %s\n", GET_MEANING_TC_3_FILENAME);
        }

        System.out.println("getMeaning marking completed");
        System.out.println("====================================================");
    }


    public void markCountWordsGreaterMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking countWordsGreater method of BinaryTree");

        System.out.println("running countWordsGreater test case #1 - testing that " + 
                "countWordsGreater throws IllegalArgumentException when null argument passed");
        BinaryTree tree = new BinaryTree();
        countGreaterMark = 0; 

        try
        {
            tree.countWordsGreater(null);
            System.out.println("your countWordsGreater method did not throw an exception");
            System.out.println("countWordsGreater test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("countWordsGreater test case #1 passed");
            countGreaterMark += COUNT_GREATER_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your countWordsGreater method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("countWordsGreater test case #1 failed");
        }

        int exp_retval = BinaryTree.KEY_VALUE_NOT_FOUND;
        int retval = 0;

        System.out.println("running countWordsGreater test case #2 - testing that " + 
                "countWordsGreater returns -1 when the key word is not in the tree");
        try
        {
            retval = tree.countWordsGreater(COUNT_GREATER_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %d, got: %d\n",
                        exp_retval, retval);
                System.out.println("countWordsGreater test case #2 passed");
                countGreaterMark += COUNT_GREATER_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected retval: %d, but got: %d instead\n",
                        exp_retval, retval);
                System.out.println("countWordsGreater test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your countWordsGreater method threw the " + 
                    "following exception: ");
            e.printStackTrace();
            System.out.println("countWordsGreater test case #2 failed");
        }

        if ((tree = readBinaryTree(COUNT_GREATER_TC_3_FILENAME)) != null)
        {
            System.out.println("running countWordsGreater test case #3 - testing that " + 
                    "countWordsGreater returns 0 for the largest key value in the tree");
            exp_retval = COUNT_GREATER_TC_3_EXP_COUNT;

            try 
            {
                retval = tree.countWordsGreater(COUNT_GREATER_TC_3_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %d and got: %d\n",
                            exp_retval,retval);
                    System.out.println("countWordsGreater test case #3 passed");
                    countGreaterMark += COUNT_GREATER_TC_3_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %d but got: %d instead\n",
                            exp_retval,retval);
                    System.out.println("countWordsGreater test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your countWordsGreater method threw the " + 
                        "following exception: ");
                e.printStackTrace();
                System.out.println("countWordsGreater test case #3 failed");
            }


            System.out.println("running countWordsGreater test case #4 - testing that " + 
                    "countWordsGreater returns the correct number of words in the tree " +
                    "that are greater than the key value");
            exp_retval = COUNT_GREATER_TC_4_EXP_COUNT;

            try 
            {
                retval = tree.countWordsGreater(COUNT_GREATER_TC_4_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %d and got: %d\n",
                            exp_retval,retval);
                    System.out.println("countWordsGreater test case #4 passed");
                    countGreaterMark += COUNT_GREATER_TC_4_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %d but got: %d instead\n",
                            exp_retval,retval);
                    System.out.println("countWordsGreater test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your countWordsGreater method threw the " + 
                        "following exception: ");
                e.printStackTrace();
                System.out.println("countWordsGreater test case #4 failed");
            }
        }
        else
        {
            System.out.printf("countWordsGreater test case #3 and #4 blocked - " +
                    "cannot read the file: %s\n", COUNT_GREATER_TC_3_FILENAME);
        }

        System.out.println("running countWordsGreater test case #5 - testing that " + 
                "countWordsGreater returns -1 when the tree is empty");
        tree = new BinaryTree();
        exp_retval = BinaryTree.KEY_VALUE_NOT_FOUND;

        try
        {
            retval = tree.countWordsGreater(COUNT_GREATER_TC_5_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %d, got: %d\n",
                        exp_retval, retval);
                System.out.println("countWordsGreater test case #5 passed");
                countGreaterMark += COUNT_GREATER_TC_5_MARK;
            }
            else
            {
                System.out.printf("expected retval: %d, but got: %d instead\n",
                        exp_retval, retval);
                System.out.println("countWordsGreater test case #5 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your countWordsGreater method threw the " + 
                    "following exception: ");
            e.printStackTrace();
            System.out.println("countWordsGreater test case #5 failed");
        }

        System.out.println("countWordsGreater marking completed");
        System.out.println("====================================================");
    }


    public void markCountWordsLessMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking countWordsLess method of BinaryTree");

        System.out.println("running countWordsLess test case #1 - testing that " + 
                "countWordsLess throws IllegalArgumentException when null argument passed");
        BinaryTree tree = new BinaryTree();
        countLessMark = 0; 

        try
        {
            tree.countWordsLess(null);
            System.out.println("your countWordsLess method did not throw an exception");
            System.out.println("countWordsLess test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("countWordsLess test case #1 passed");
            countLessMark += COUNT_LESS_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your countWordsLess method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("countWordsLess test case #1 failed");
        }

        int exp_retval = BinaryTree.KEY_VALUE_NOT_FOUND;
        int retval = 0;

        System.out.println("running countWordsLess test case #2 - testing that " + 
                "countWordsLess returns -1 when the key word is not in the tree");
        try
        {
            retval = tree.countWordsLess(COUNT_LESS_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %d, got: %d\n",
                        exp_retval, retval);
                System.out.println("countWordsLess test case #2 passed");
                countLessMark += COUNT_LESS_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected retval: %d, but got: %d instead\n",
                        exp_retval, retval);
                System.out.println("countWordsLess test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your countWordsLess method threw the " + 
                    "following exception: ");
            e.printStackTrace();
            System.out.println("countWordsLess test case #2 failed");
        }

        if ((tree = readBinaryTree(COUNT_LESS_TC_3_FILENAME)) != null)
        {
            System.out.println("running countWordsLess test case #3 - testing that " + 
                    "countWordsLess returns 0 for the smallest key in the tree");
            exp_retval = COUNT_LESS_TC_3_EXP_COUNT;

            try 
            {
                retval = tree.countWordsLess(COUNT_LESS_TC_3_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %d and got: %d\n",
                            exp_retval,retval);
                    System.out.println("countWordsLess test case #3 passed");
                    countLessMark += COUNT_LESS_TC_3_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %d but got: %d instead\n",
                            exp_retval,retval);
                    System.out.println("countWordsLess test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your countWordsLess method threw the " + 
                        "following exception: ");
                e.printStackTrace();
                System.out.println("countWordsLess test case #3 failed");
            }


            System.out.println("running countWordsLess test case #4 - testing that " + 
                    "countWordsLess returns the correct number of words in the tree " +
                    "that are greater than the key value");
            exp_retval = COUNT_LESS_TC_4_EXP_COUNT;

            try 
            {
                retval = tree.countWordsLess(COUNT_LESS_TC_4_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %d and got: %d\n",
                            exp_retval,retval);
                    System.out.println("countWordsLess test case #4 passed");
                    countLessMark += COUNT_LESS_TC_4_MARK;
                }
                else
                {
                    System.out.printf("expected retval: %d but got: %d instead\n",
                            exp_retval,retval);
                    System.out.println("countWordsLess test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your countWordsLess method threw the " + 
                        "following exception: ");
                e.printStackTrace();
                System.out.println("countWordsLess test case #4 failed");
            }
        }
        else
        {
            System.out.printf("countWordsLess test case #3 and #4 blocked - " +
                    "cannot read the file: %s\n", COUNT_LESS_TC_3_FILENAME);
        }

        System.out.println("running countWordsLess test case #5 - testing that " + 
                "countWordsLess returns -1 when the tree is empty");
        tree = new BinaryTree();
        exp_retval = BinaryTree.KEY_VALUE_NOT_FOUND;

        try
        {
            retval = tree.countWordsLess(COUNT_LESS_TC_5_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %d, got: %d\n",
                        exp_retval, retval);
                System.out.println("countWordsLess test case #5 passed");
                countLessMark += COUNT_LESS_TC_5_MARK;
            }
            else
            {
                System.out.printf("expected retval: %d, but got: %d instead\n",
                        exp_retval, retval);
                System.out.println("countWordsLess test case #5 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your countWordsLess method threw the " + 
                    "following exception: ");
            e.printStackTrace();
            System.out.println("countWordsLess test case #5 failed");
        }

        System.out.println("countWordsLess marking completed");
        System.out.println("====================================================");
    }


    public void markRemoveWordMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking removeWord method of BinaryTree");
        removeWordMark = 0;
        boolean retval;
        boolean exp_retval = false;
        BinaryTree tree = new BinaryTree();
        BinaryTree exp_tree = null;

        System.out.println("running removeWord test case #1 - testing that removeWord " + 
                "throws an exception when argument is null");
        try
        {
            tree.removeWord(null);
            System.out.println("your removeWord method did not throw an exception");
            System.out.println("removeWord test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("removeWord test case #1 passed");
            removeWordMark += REMOVE_WORD_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your removeWord method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("removeWord test case #1 failed");
        }

        System.out.println("running removeWord test case #2 - testing that removeWord " + 
                "does not throw an exception when removing a key from an empty tree");
        try 
        {
            tree = new BinaryTree();
            retval = tree.removeWord(REMOVE_WORD_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %b and got: %b\n",
                        exp_retval,retval);
                System.out.println("removeWord test case #2 passed");
                removeWordMark += REMOVE_WORD_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected retval: %b but got: %b instead\n",
                        exp_retval,retval);
                System.out.println("removeWord test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your removeWord method threw the following " + 
                    "exception: ");
            e.printStackTrace();
            System.out.println("removeWord test case #2 failed");
        }

        if ((tree = readBinaryTree(REMOVE_WORD_TC_3_FILENAME)) != null)
        {
            System.out.println("running removeWord test case #3 - testing that removeWord " + 
                    "returns false when the key value is not in the tree");
            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_3_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);
                    exp_tree = readBinaryTree(REMOVE_WORD_TC_3_FILENAME);

                    if (tree.equals(exp_tree))
                    {
                        System.out.println("removeWord test case #3 passed");
                        removeWordMark += REMOVE_WORD_TC_3_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned false" + 
                                " but it appears to have modified the tree in some way");
                        System.out.println("removeWord test case #3 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #3 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #3 blocked - " +
                    "cannot read the file: %s\n", REMOVE_WORD_TC_3_FILENAME);
        }

        if (((tree = readBinaryTree(REMOVE_WORD_TC_4_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(REMOVE_WORD_TC_4_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running removeWord test case #4 - testing that removeWord " + 
                    "successfully deletes a key with no children from the binary tree");
            exp_retval = true;

            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_4_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("removeWord test case #4 passed");
                        removeWordMark += REMOVE_WORD_TC_4_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("removeWord test case #4 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #4 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #4 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", REMOVE_WORD_TC_4_FILENAME,
                    REMOVE_WORD_TC_4_EXP_TREE_FILENAME);
        }

        if (((tree = readBinaryTree(REMOVE_WORD_TC_5_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(REMOVE_WORD_TC_5_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running removeWord test case #5 - testing that removeWord " + 
                    "successfully deletes a key with only a left child from the binary tree");
            exp_retval = true;

            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_5_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("removeWord test case #5 passed");
                        removeWordMark += REMOVE_WORD_TC_5_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("removeWord test case #5 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #5 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #5 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #5 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", REMOVE_WORD_TC_5_FILENAME,
                    REMOVE_WORD_TC_5_EXP_TREE_FILENAME);
        }

        if (((tree = readBinaryTree(REMOVE_WORD_TC_6_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(REMOVE_WORD_TC_6_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running removeWord test case #6 - testing that removeWord " + 
                    "successfully deletes a key with only a right child from the binary tree");
            exp_retval = true;

            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_6_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("removeWord test case #6 passed");
                        removeWordMark += REMOVE_WORD_TC_6_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("removeWord test case #6 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #6 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #6 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #6 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", REMOVE_WORD_TC_6_FILENAME,
                    REMOVE_WORD_TC_6_EXP_TREE_FILENAME);
        }

        if (((tree = readBinaryTree(REMOVE_WORD_TC_7_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(REMOVE_WORD_TC_7_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running removeWord test case #7 - testing that removeWord " + 
                    "successfully deletes a key with two children from the binary tree");
            exp_retval = true;

            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_7_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("removeWord test case #7 passed");
                        removeWordMark += REMOVE_WORD_TC_7_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("removeWord test case #7 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #7 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #7 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #7 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", REMOVE_WORD_TC_7_FILENAME,
                    REMOVE_WORD_TC_7_EXP_TREE_FILENAME);
        }

        if (((tree = readBinaryTree(REMOVE_WORD_TC_8_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(REMOVE_WORD_TC_8_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running removeWord test case #8 - testing that removeWord " + 
                    "successfully deletes the root node with two children");
            exp_retval = true;

            try 
            {
                retval = tree.removeWord(REMOVE_WORD_TC_8_KEY);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("removeWord test case #8 passed");
                        removeWordMark += REMOVE_WORD_TC_8_MARK;
                    }
                    else
                    {
                        System.out.println("your removeWord method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("removeWord test case #8 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("removeWord test case #8 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your removeWord method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("removeWord test case #8 failed");
            }
        }
        else
        {
            System.out.printf("removeWord test case #8 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", REMOVE_WORD_TC_8_FILENAME,
                    REMOVE_WORD_TC_8_EXP_TREE_FILENAME);
        }

        System.out.println("removeWord marking completed");
        System.out.println("====================================================");
    }


    public void markInsertSynonymMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking insertSynonym method of SynonymsList");
        insertSynonymMark = 0;
        boolean exp_retval;
        boolean retval;
        SynonymList list = new SynonymList();
        SynonymList exp_list = null;

        System.out.println("running insertSynonym test case #1 - testing that insertSynonym"+
                " throws an exception when argument is null");
        try
        {
            list.insertSynonym(null);
            System.out.println("your insertSynonym method did not throw an exception");
            System.out.println("insertSynonym test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("insertSynonym test case #1 passed");
            insertSynonymMark += INSERT_SYNONYM_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your insertSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("insertSynonym test case #1 failed");
        }

        System.out.println("running insertSynonym test case #2 - testing that insertSynonym"+
                " throws an exception when argument is \"\"");
        try
        {
            list.insertSynonym("");
            System.out.println("your insertSynonym method did not throw an exception");
            System.out.println("insertSynonym test case #2 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("insertSynonym test case #2 passed");
            insertSynonymMark += INSERT_SYNONYM_TC_2_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your insertSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("insertSynonym test case #2 failed");
        }

        if ((exp_list = readSynonymList(INSERT_SYNONYM_TC_3_FILENAME,null)) != null)
        {
            System.out.println("running insertSynonym test case #3 - testing that " + 
                    "insertSynonym successfully adds a synonym to an empty list");
            exp_retval = true;

            try 
            {
                retval = list.insertSynonym(INSERT_SYNONYM_TC_3_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_list.equals(list))
                    {
                        System.out.println("insertSynonym test case #3 passed");
                        insertSynonymMark += INSERT_SYNONYM_TC_3_MARK;

                        exp_retval = false;
                        System.out.println("running insertSynonym test case #4 - " + 
                            "testing that insertSynonym does not add the same synonym " + 
                            "to a list");

                        try
                        {
                            retval = list.insertSynonym(INSERT_SYNONYM_TC_3_SYNONYM);

                            if (retval == exp_retval)
                            {
                                System.out.printf("expected retval: %b and got: %b\n",
                                        exp_retval,retval);

                                if (exp_list.equals(list))
                                {
                                    System.out.println("insertSynonym test case #4 passed");
                                    insertSynonymMark += INSERT_SYNONYM_TC_4_MARK;
                                }
                                else
                                {
                                    System.out.println("your insertSynonms method returned false"+
                                            " but the resultant list is not correct");
                                    System.out.println("insertSynonym test case #4 failed");
                                }
                            }
                            else
                            {
                                System.out.printf("expected retval: %b but got: %b instead\n",
                                        exp_retval,retval);
                                System.out.println("insertSynonym test case #4 failed");
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("your insertSynonym method threw the following " + 
                                    "exception: ");
                            e.printStackTrace();
                            System.out.println("insertSynonym test case #4 failed");
                        }
                    }
                    else
                    {
                        System.out.println("your insertSynonms method returned true" + 
                                " but the resultant list is not correct");
                        System.out.println("insertSynonym test case #3 failed");
                        System.out.println("skipping test case #4");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("insertSynonym test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your insertSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("insertSynonym test case #3 failed");
            }
        }
        else
        {
            System.out.printf("insertSynonym test cases #3 and #4 blocked - " +
                    "cannot read the file(s): %s\n", INSERT_SYNONYM_TC_3_FILENAME);
        }

        if ((exp_list = readSynonymList(INSERT_SYNONYM_TC_5_FILENAME,null)) != null)
        {
            System.out.println("running insertSynonym test case #5 - testing that " + 
                    "insertSynonym successfully adds a synonym in alphabetical order" + 
                    " at the start of the list");
            exp_retval = true;

            try 
            {
                retval = list.insertSynonym(INSERT_SYNONYM_TC_5_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_list.equals(list))
                    {
                        System.out.println("insertSynonym test case #5 passed");
                        insertSynonymMark += INSERT_SYNONYM_TC_5_MARK;
                    }
                    else
                    {
                        System.out.println("your insertSynonms method returned true" + 
                                " but the resultant list is not correct");
                        System.out.println("insertSynonym test case #5 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("insertSynonym test case #5 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your insertSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("insertSynonym test case #5 failed");
            }
        }
        else
        {
            System.out.printf("insertSynonym test cases #5 blocked - " +
                    "cannot read the file(s): %s\n", INSERT_SYNONYM_TC_5_FILENAME);
        }

        if ((exp_list = readSynonymList(INSERT_SYNONYM_TC_6_FILENAME,null)) != null)
        {
            System.out.println("running insertSynonym test case #6 - testing that " + 
                    "insertSynonym successfully adds a synonym in alphabetical order" + 
                    " at the end of the list");
            exp_retval = true;

            try 
            {
                retval = list.insertSynonym(INSERT_SYNONYM_TC_6_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_list.equals(list))
                    {
                        System.out.println("insertSynonym test case #6 passed");
                        insertSynonymMark += INSERT_SYNONYM_TC_6_MARK;
                    }
                    else
                    {
                        System.out.println("your insertSynonms method returned true" + 
                                " but the resultant list is not correct");
                        System.out.println("insertSynonym test case #6 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("insertSynonym test case #6 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your insertSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("insertSynonym test case #6 failed");
            }
        }
        else
        {
            System.out.printf("insertSynonym test cases #6 blocked - " +
                    "cannot read the file(s): %s\n", INSERT_SYNONYM_TC_6_FILENAME);
        }
        System.out.println("insertSynonym marking completed");
        System.out.println("====================================================");
    }


    public void markAddSynonymMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking addSynonym method of BinaryTree");
        addSynonymMark = 0;
        boolean retval;
        boolean exp_retval;
        BinaryTree exp_tree;
        BinaryTree tree = new BinaryTree();

        System.out.println("running addSynonym test case #1 - testing that addSynonym"+
                " throws an exception when argument is null");
        try
        {
            tree.addSynonym(null,ADD_SYNONYM_TC_1_SYNONYM);
            System.out.println("your addSynonym method did not throw an exception");
            System.out.println("addSynonym test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("addSynonym test case #1 passed");
            addSynonymMark += ADD_SYNONYM_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your addSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("addSynonym test case #1 failed");
        }

        System.out.println("running addSynonym test case #2 - testing that addSynonym " + 
                "throws IllegalArgumentException when null argument passed");
        try
        {
            tree.addSynonym(ADD_SYNONYMS_TC_2_KEY,null);
            System.out.println("your addSynonym method did not throw an exception");
            System.out.println("addSynonym test case #2 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("addSynonym test case #2 passed");
            addSynonymMark += ADD_SYNONYM_TC_2_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your addSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("addSynonym test case #2 failed");
        }

        System.out.println("running addSynonym test case #3 - testing that addSynonym " + 
                "throws IllegalArgumentException when \"\" argument passed as key value");
        try
        {
            tree.addSynonym("",ADD_SYNONYM_TC_3_SYNONYM);
            System.out.println("your addSynonym method did not throw an exception");
            System.out.println("addSynonym test case #3 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("addSynonym test case #3 passed");
            addSynonymMark += ADD_SYNONYM_TC_3_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your addSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("addSynonym test case #3 failed");
        }

        System.out.println("running addSynonym test case #4 - testing that addSynonym " + 
                "throws IllegalArgumentException when \"\" argument passed as synonym");
        try
        {
            tree.addSynonym(ADD_SYNONYM_TC_4_KEY,"");
            System.out.println("your addSynonym method did not throw an exception");
            System.out.println("addSynonym test case #4 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("addSynonym test case #4 passed");
            addSynonymMark += ADD_SYNONYM_TC_4_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your addSynonym method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("addSynonym test case #4 failed");
        }

        if (((tree = readBinaryTree(ADD_SYNONYM_TC_5_FILENAME)) != null) &&
            ((exp_tree = readBinaryTree(ADD_SYNONYM_TC_5_EXP_TREE_FILENAME)) != null))
        {
            System.out.println("running addSynonym test case #5 - testing that " + 
                    "addSynonym successfully adds a synonym for a key");
            exp_retval = true;

            try 
            {
                retval = tree.addSynonym(ADD_SYNONYM_TC_5_KEY,ADD_SYNONYM_TC_5_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("addSynonym test case #5 passed");
                        addSynonymMark += ADD_SYNONYM_TC_5_MARK;
                    }
                    else
                    {
                        System.out.println("your addSynonym method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("addSynonym test case #5 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("addSynonym test case #5 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your addSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("addSynonym test case #5 failed");
            }

            System.out.println("running addSynonym test case #6 - testing that " + 
                    "addSynonym does not add existing synonym");
            exp_retval = false;

            try 
            {
                retval = tree.addSynonym(ADD_SYNONYM_TC_6_KEY,ADD_SYNONYM_TC_6_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("addSynonym test case #6 passed");
                        addSynonymMark += ADD_SYNONYM_TC_6_MARK;
                    }
                    else
                    {
                        System.out.println("your addSynonym method returned false" + 
                                " but the resultant tree is not correct");
                        System.out.println("addSynonym test case #6 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("addSynonym test case #6 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your addSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("addSynonym test case #6 failed");
            }
        }
        else
        {
            System.out.printf("addSynonym test case #5 and #6 blocked - " +
                    "cannot read the file(s): %s and/or %s\n", ADD_SYNONYM_TC_5_FILENAME,
                    ADD_SYNONYM_TC_5_EXP_TREE_FILENAME);
        }

        if ((tree = readBinaryTree(ADD_SYNONYM_TC_7_FILENAME)) != null)
        {
            exp_tree = tree;
            System.out.println("running addSynonym test case #7 - testing that " + 
                    "addSynonym does not add a synonym for a non-existent key");
            exp_retval = false;

            try 
            {
                retval = tree.addSynonym(ADD_SYNONYM_TC_7_KEY,ADD_SYNONYM_TC_7_SYNONYM);

                if (retval == exp_retval)
                {
                    System.out.printf("expected retval: %b and got: %b\n",
                            exp_retval,retval);

                    if (exp_tree.equals(tree))
                    {
                        System.out.println("addSynonym test case #7 passed");
                        addSynonymMark += ADD_SYNONYM_TC_7_MARK;
                    }
                    else
                    {
                        System.out.println("your addSynonym method returned true" + 
                                " but the resultant tree is not correct");
                        System.out.println("addSynonym test case #5 failed");
                    }
                }
                else
                {
                    System.out.printf("expected retval: %b but got: %b instead\n",
                            exp_retval,retval);
                    System.out.println("addSynonym test case #5 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your addSynonym method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("addSynonym test case #5 failed");
            }
        }
        else
        {
            System.out.printf("addSynonym test case #7 blocked - " +
                    "cannot read the file: %s\n", ADD_SYNONYM_TC_7_FILENAME);
        }

        System.out.println("addSynonym marking completed");
        System.out.println("====================================================");
    }


    public void markGetSynonymsMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking getSynonyms method of BinaryTree");
        getSynonymsMark = 0;
        String exp_retval[] = null;
        String retval[] = null;
        BinaryTree tree = new BinaryTree();

        System.out.println("running getSynonyms test case #1 - testing that getSynonyms"+
                " throws an exception when argument is null");
        try
        {
            tree.getSynonyms(null);
            System.out.println("your getSynonyms method did not throw an exception");
            System.out.println("getSynonyms test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("getSynonyms test case #1 passed");
            getSynonymsMark += GET_SYNONYMS_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your getSynonyms method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("getSynonyms test case #1 failed");
        }

        System.out.println("running getSynonyms test case #2 - testing that getSynonyms"+
                " returns null when key value not in tree");
        try
        {
            retval = tree.getSynonyms(GET_SYNONYMS_TC_2_KEY);

            if (retval == exp_retval)
            {
                System.out.printf("expected retval: %s and got: %s\n",exp_retval,retval);
                System.out.println("getSynonyms test case #2 passed");
                getSynonymsMark += GET_SYNONYMS_TC_2_MARK;
            }
            else
            {
                System.out.printf("expected retval: %s but got: %s instead\n",
                        exp_retval,retval);
                System.out.println("getSynonyms test case #2 failed");
            }
        }
        catch (Exception e)
        {
            System.out.println("your getSynonyms method threw the following " + 
                    "exception: ");
            e.printStackTrace();
            System.out.println("getSynonyms test case #2 failed");
        }

        System.out.println("running getSynonyms test case #3 - testing that getSynonyms"+
                " returns an array of synonyms for a key value in the tree");
        exp_retval = GET_SYNONYMS_TC_3_EXP_RETVAL;

        if ((tree = readBinaryTree(GET_SYNONYMS_TC_3_FILENAME)) != null)
        {
            try
            {
                retval = tree.getSynonyms(GET_SYNONYMS_TC_3_KEY);

                if (equals(retval,exp_retval))
                {
                    System.out.println("got correct synonyms");
                    System.out.println("getSynonyms test case #3 passed");
                    getSynonymsMark += GET_SYNONYMS_TC_3_MARK;
                }
                else
                {
                    System.out.println("got incorrect synonyms");
                    System.out.println("getSynonyms test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your getSynonyms method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("getSynonyms test case #3 failed");
            }

            try
            {
                System.out.println("running getSynonyms test case #4 - testing that getSynonyms" +
                        " returns null when a valid key value has no synonyms");
                exp_retval = null;
                retval = tree.getSynonyms(GET_SYNONYMS_TC_4_KEY);

                if (equals(retval,exp_retval))
                {
                    System.out.println("got correct synonyms");
                    System.out.println("getSynonyms test case #4 passed");
                    getSynonymsMark += GET_SYNONYMS_TC_4_MARK;
                }
                else
                {
                    System.out.println("got incorrect synonyms");
                    System.out.println("getSynonyms test case #4 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your getSynonyms method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("getSynonyms test case #4 failed");
            }
        }
        else
        {
            System.out.printf("getSynonyms test case #3 and #4 blocked - " +
                    "cannot read the file: %s\n", GET_SYNONYMS_TC_3_FILENAME);
        }

        System.out.println("getSynonyms marking completed");
        System.out.println("====================================================");
    }


    public void markFindRelatedMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking findRelatedWords method of BinaryTree");
        findRelatedMark = 0;
        String exp_retval[] = null;
        String retval[] = null;
        BinaryTree tree = new BinaryTree();

        System.out.println("running findRelatedWords test case #1 - testing that "+
                " findRelatedWords throws an exception when argument is null");
        try
        {
            tree.findRelatedWords(null);
            System.out.println("your findRelatedWords method did not throw an exception");
            System.out.println("findRelatedWords test case #1 failed");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("findRelatedWords test case #1 passed");
            findRelatedMark += FIND_RELATED_TC_1_MARK;
        }
        catch (Exception e)
        {
            System.out.println("your findRelatedWords method threw the following " + 
                    "incorrect exception: ");
            e.printStackTrace();
            System.out.println("findRelatedWords test case #1 failed");
        }

        if ((tree = readBinaryTree(FIND_RELATED_TC_2_FILENAME)) != null)
        {
            System.out.println("running findRelatedWords test case #2 - testing that "+
                    " findRelatedWords returns null when a synonym is not in the tree");
            exp_retval = null;

            try
            {
                retval = tree.findRelatedWords(FIND_RELATED_TC_2_SYNONYM);

                if (exp_retval == retval)
                {
                    System.out.println("findRelatedWords returned null");
                    System.out.println("findRelatedWords test case #2 passed");
                    findRelatedMark += FIND_RELATED_TC_2_MARK;
                }
                else
                {
                    System.out.println("findRelatedWords did not return null");
                    System.out.println("findRelatedWords test case #2 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your findRelatedWords method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("findRelatedWords test case #2 failed");
            }
        }
        else
        {
            System.out.printf("findRelatedWords test case #2 blocked - " +
                    "cannot read the file: %s\n", FIND_RELATED_TC_2_FILENAME);
        }

        System.out.println("running findRelatedWords test case #3 - testing that findRelatedWords"+
                " returns an array of keys that have the specified synonym");
        exp_retval = FIND_RELATED_TC_3_EXP_RETVAL;

        if ((tree = readBinaryTree(FIND_RELATED_TC_3_FILENAME)) != null)
        {
            try
            {
                retval = tree.findRelatedWords(FIND_RELATED_TC_3_SYNONYM);

                if (equals(retval,exp_retval))
                {
                    System.out.println("findRelatedWords returned the correct " + 
                            "key values");
                    findRelatedMark += FIND_RELATED_TC_3_MARK;
                }
                else
                {
                    System.out.println("findRelatedWords did not return the " + 
                            "correct key values");
                    System.out.println("findRelatedWords test case #3 failed");
                }
            }
            catch (Exception e)
            {
                System.out.println("your findRelatedWords method threw the following " + 
                        "exception: ");
                e.printStackTrace();
                System.out.println("findRelatedWords test case #3 failed");
            }
        }
        else
        {
            System.out.printf("findRelatedWords test case #3 blocked - " +
                    "cannot read the file: %s\n", FIND_RELATED_TC_3_FILENAME);
        }

        System.out.println("findRelatedWords marking completed");
        System.out.println("====================================================");
    }


    public void printMark()
    {
        System.out.println();
        System.out.println("===================== MARKS ========================");
        System.out.printf("findNode method mark: %2.1f out of %2.1f\n",
                findNodeMark,MAX_FIND_NODE_MARK);
        System.out.printf("insertWord method mark: %2.1f out of %2.1f\n",
                insertWordMark,MAX_INSERT_WORD_MARK);
        System.out.printf("getMeaning method mark: %2.1f out of %2.1f\n",
                getMeaningMark,MAX_GET_MEANING_MARK);
        System.out.printf("countWordsGreater method mark: %2.1f out of %2.1f\n",
                countGreaterMark,MAX_COUNT_GREATER_MARK);
        System.out.printf("countWordsLess method mark: %2.1f out of %2.1f\n",
                countLessMark,MAX_COUNT_LESS_MARK);
        System.out.printf("removeWord method mark: %2.1f out of %2.1f\n",
                removeWordMark,MAX_REMOVE_WORD_MARK);
        System.out.printf("insertSynonym method mark: %2.1f out of %2.1f\n",
                insertSynonymMark,MAX_INSERT_SYNONYM_MARK);
        System.out.printf("addSynonym method mark: %2.1f out of %2.1f\n",
                addSynonymMark,MAX_ADD_SYNONYM_MARK);
        System.out.printf("getSynonyms method mark: %2.1f out of %2.1f\n",
                getSynonymsMark,MAX_GET_SYNONYMS_MARK);
        System.out.printf("findRelatedWords method mark: %2.1f out of %2.1f\n",
                findRelatedMark,MAX_FIND_RELATED_MARK);
        System.out.printf("findInstanceOfWordType method mark: %2.1f out of %2.1f\n",
                findWordOfCategoryTypeMark,FIND_WORD_OF_CATEGORY_MARK);
        System.out.printf("\nTotal Marks: %2.1f out of %2.1f\n",
                (findNodeMark + insertWordMark + getMeaningMark + countGreaterMark + 
                 countLessMark + removeWordMark + insertSynonymMark + 
                 addSynonymMark + getSynonymsMark + findRelatedMark + findWordOfCategoryTypeMark), MAX_TOTAL_MARK);
        System.out.println("====================================================");
        System.out.println();
        System.out.println("Marking report complete");
    }


    public static void main(String args[])
    {
        Assignment2Marker marker = new Assignment2Marker();

        try
        {
            marker.markFindNodeMethod();
            marker.markInsertWordMethod();
            marker.markGetMeaningMethod();
            marker.markCountWordsGreaterMethod();
            marker.markCountWordsLessMethod();
            marker.markRemoveWordMethod();
            marker.markInsertSynonymMethod();
            marker.markAddSynonymMethod();
            marker.markGetSynonymsMethod();
            marker.markFindRelatedMethod();
            marker.markFindInstanceOfWordTypeMethod();
            marker.printMark();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Congratulations, your code threw an error that I didn't " + 
                    "think was possible!!! Sorry but no extra marks for such an amazing " + 
                    "achievement - just more work for you to fix the problem :P");
        }
    }
    
    
    
    
    
    
    
    


	//TODO these all need to be distributed with existing entries

    private static final String FIND_WORD_OF_CATEGORY_TC_1_TYPES = "findInstanceOfWordTypePairs.txt";
    private static final String FIND_WORD_OF_CATEGORY_SENTENCE = "the fat cat sat on the wooly hat near the blue mat";
    
    private float findWordOfCategoryTypeMark;
    
    
    private boolean readAndAddCategoryList(String filename, BinaryTree treeToAddTo)
    {
        assert((filename != null) && (treeToAddTo != null));

        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            reader.close();

            if (line != null)
            {
                String tokens[] = line.split("\\|");
                int tokenOffset = 0;
                int numSynonyms = Integer.parseInt(tokens[tokenOffset++]);

                if (numSynonyms > 0)
                {
                    //load all the pair word/type pairs from the file
                    for (; tokenOffset < numSynonyms + 1; tokenOffset++)
                    {
                        int pairedOffset = tokenOffset * 2;
                        treeToAddTo.setCategory(tokens[pairedOffset], BinaryTree.LexicalCategory.valueOf(tokens[pairedOffset + 1]));
                    }
                }
            }

            reader.close();
            
            return true;

        }
        catch (Exception e)
        {
            System.out.println("Error reading synonym list from file: ");
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    public void markFindInstanceOfWordTypeMethod()
    {
        System.out.println();
        System.out.println("====================================================");
        System.out.println("marking FindInstanceOfWordType method of BinaryTree");
        findWordOfCategoryTypeMark = 0;
        BinaryTree tree = new BinaryTree(); 


        //generate a tree from our test sentence
        String[] words = FIND_WORD_OF_CATEGORY_SENTENCE.split(" ");
        for (int i = 0; i < words.length; i++)
        {
            tree.insertWord(words[i], "don't care about the meaning :)");
        }
        
        
        
        //pick two random words and check if they are in the tree, if not then it hasn't been constructed correctly
        //really basic check that the insertNode function is working
        String testWord1 = words[new Random().nextInt(words.length)];
        String testWord2 = words[new Random().nextInt(words.length)];
        try
        {
			if (tree.findNode(testWord1).key.equals(testWord1) && 
					tree.findNode(testWord2).key.equals(testWord2)) 
			{
            
				System.out.println("running findInstanceOfWordType test case #1 - check");
				System.out.println("that lexical categories can be added and retrieved to words");

				try
				{
            
					//TEST HERE
					if (tree.setCategory("mat", BinaryTree.LexicalCategory.NOUN) && 
							tree.findNode("mat").category == BinaryTree.LexicalCategory.NOUN && 
							tree.getCategory("mat") == BinaryTree.LexicalCategory.NOUN)
					{
						findWordOfCategoryTypeMark += FIND_WORD_OF_CATEGORY_TC_1_MARK;
						System.out.println("findInstanceOfWordType test case #1 passed");
					}
					else
					{
						System.out.println("findInstanceOfWordType test case #1 failed");
					}
				}
				catch (Exception e)
				{
					System.out.println("your findInstanceOfWordType method threw the following " + 
							"exception: ");
					e.printStackTrace();
					System.out.println("findInstanceOfWordType test case #1 failed");
				}
            
            
            
            
            
            
				System.out.println("running findInstanceOfWordType test case #2 - check");
				System.out.println("findInstanceOfWordType can find first instance of a noun");

				try
				{
					//load the lexical categories from the file, should always work if the user has working tree logic
					readAndAddCategoryList(FIND_WORD_OF_CATEGORY_TC_1_TYPES, tree);
                
					//TEST HERE
					if (tree.findInstanceOfWordType(FIND_WORD_OF_CATEGORY_SENTENCE, BinaryTree.LexicalCategory.NOUN, 1) == tree.findNode("cat") && tree.findNode("cat").key.equals("cat"))
					{
						findWordOfCategoryTypeMark += FIND_WORD_OF_CATEGORY_TC_2_MARK;
						System.out.println("findInstanceOfWordType test case #2 passed");
					}
					else
					{
						System.out.println("findInstanceOfWordType test case #2 failed");
					}
				}
				catch (Exception e)
				{
					System.out.println("your findInstanceOfWordType method threw the following " + 
							"exception: ");
					e.printStackTrace();
					System.out.println("findInstanceOfWordType test case #2 failed");
				}
            
            
            
            
            
            
				System.out.println("running findInstanceOfWordType test case #3 - check");
				System.out.println("findInstanceOfWordType can find first instance of an adjective");

				try
				{
					//load the lexical categories from the file, should always work if the user has working tree logic
					readAndAddCategoryList(FIND_WORD_OF_CATEGORY_TC_1_TYPES, tree);
                
					//TEST HERE
					if (tree.findInstanceOfWordType(FIND_WORD_OF_CATEGORY_SENTENCE, BinaryTree.LexicalCategory.ADJECTIVE, 1) == tree.findNode("fat") && tree.findNode("fat").key.equals("fat"))
					{
						findWordOfCategoryTypeMark += FIND_WORD_OF_CATEGORY_TC_3_MARK;
						System.out.println("findInstanceOfWordType test case #3 passed");
					}
					else
					{
						System.out.println("findInstanceOfWordType test case #3 failed");
					}
				}
				catch (Exception e)
				{
					System.out.println("your findInstanceOfWordType method threw the following " + 
							"exception: ");
					e.printStackTrace();
					System.out.println("findInstanceOfWordType test case #3 failed");
				}
            
            
            
            
            
				System.out.println("running findInstanceOfWordType test case #4 - check");
				System.out.println("findInstanceOfWordType can find third instance of a noun");

				try
				{
					//load the lexical categories from the file, should always work if the user has working tree logic
					readAndAddCategoryList(FIND_WORD_OF_CATEGORY_TC_1_TYPES, tree);
                
					//TEST HERE
					if (tree.findInstanceOfWordType(FIND_WORD_OF_CATEGORY_SENTENCE, BinaryTree.LexicalCategory.NOUN, 3) == tree.findNode("mat") && tree.findNode("mat").key.equals("mat"))
					{
						findWordOfCategoryTypeMark += FIND_WORD_OF_CATEGORY_TC_4_MARK;
						System.out.println("findInstanceOfWordType test case #4 passed");
					}
					else
					{
						System.out.println("findInstanceOfWordType test case #4 failed");
					}
				}
				catch (Exception e)
				{
					System.out.println("your findInstanceOfWordType method threw the following " + 
							"exception: ");
					e.printStackTrace();
					System.out.println("findInstanceOfWordType test case #4 failed");
				}
            
				String[] randomNounTest = { "cat", "hat", "mat" };
				String[] randomAdjectiveTest = { "fat", "wooly", "blue" };
            
				Random generator = new Random();
            
				int randomNounIndex = generator.nextInt(randomNounTest.length);
				int randomAdjectiveIndex = generator.nextInt(randomAdjectiveTest.length);
            
				System.out.println("running findInstanceOfWordType test case #5 - check");
				System.out.println("findInstanceOfWordType can find random instance of a random type");

				try
				{
					//load the lexical categories from the file, should always work if the user has working tree logic
					readAndAddCategoryList(FIND_WORD_OF_CATEGORY_TC_1_TYPES, tree);
                
					//TEST HERE
					if (tree.findInstanceOfWordType(FIND_WORD_OF_CATEGORY_SENTENCE, BinaryTree.LexicalCategory.NOUN, randomNounIndex + 1) == tree.findNode(randomNounTest[randomNounIndex]) && 
							tree.findInstanceOfWordType(FIND_WORD_OF_CATEGORY_SENTENCE, BinaryTree.LexicalCategory.ADJECTIVE, randomAdjectiveIndex + 1) == tree.findNode(randomAdjectiveTest[randomAdjectiveIndex]))
					{
						findWordOfCategoryTypeMark += FIND_WORD_OF_CATEGORY_TC_5_MARK;
						System.out.println("findInstanceOfWordType test case #5 passed");
					}
					else
					{
						System.out.println("findInstanceOfWordType test case #5 failed");
					}
				}
				catch (Exception e)
				{
					System.out.println("your findInstanceOfWordType method threw the following " + 
							"exception: ");
					e.printStackTrace();
					System.out.println("findInstanceOfWordType test case #5 failed");
				}
            
            
			}
			else
			{
				System.out.printf("findInstanceOfWordType test case #1 blocked - " +
						"cannot generate a valid tree\n");
			}
		}
		catch (Exception e)
		{
			System.out.printf("findInstanceOfWordType test case #1 blocked - " +
					"cannot generate a valid tree\n");
		}

        System.out.println("findInstanceOfWordType marking completed");
        System.out.println("====================================================");
    }
}




