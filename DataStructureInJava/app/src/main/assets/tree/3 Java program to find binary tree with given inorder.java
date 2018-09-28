// Java program to find binary tree with given inorder traversal 
import java.util.Vector; 

/*
1) Initialize list of Binary Trees as empty.  
2) For every element in[i] where i varies from 0 to n-1,
    do following
......a)  Create a new node with key as 'arr[i]', 
          let this node be 'node'
......b)  Recursively construct list of all left subtrees.
......c)  Recursively construct list of all right subtrees.
3) Iterate for all left subtrees
   a) For current leftsubtree, iterate for all right subtrees
        Add current left and right subtrees to 'node' and add
        'node' to list.
*/

/* Class containing left and right child of current  
 node and key value*/
class Node { 
    int data; 
    Node left, right; 
  
    public Node(int item) { 
        data = item; 
        left = null; 
        right = null; 
    } 
} 
  
/* Class to print Level Order Traversal */
class BinaryTree { 
  
    Node root; 
  
    // A utility function to do preorder traversal of BST 
    void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.data + " "    ); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    } 
  
    // Function for constructing all possible trees with 
    // given inorder traversal stored in an array from 
    // arr[start] to arr[end]. This function returns a 
    // vector of trees. 
    Vector<Node> getTrees(int arr[], int start, int end) { 
  
        // List to store all possible trees 
        Vector<Node> trees= new Vector<Node>(); 
  
        /* if start > end then subtree will be empty so 
         returning NULL in the list */
        if (start > end) { 
            trees.add(null); 
            return trees; 
        } 
  
        /* Iterating through all values from start to end 
         for constructing left and right subtree 
         recursively */
        for (int i = start; i <= end; i++) { 
            /* Constructing left subtree */
            Vector<Node> ltrees = getTrees(arr, start, i - 1); 
              
            /* Constructing right subtree */
            Vector<Node> rtrees = getTrees(arr, i + 1, end); 
  
            /* Now looping through all left and right subtrees 
             and connecting them to ith root below */
            for (int j = 0; j < ltrees.size(); j++) { 
                for (int k = 0; k < rtrees.size(); k++) { 
  
                    // Making arr[i] as root 
                    Node node = new Node(arr[i]); 
  
                    // Connecting left subtree 
                    node.left = ltrees.get(j); 
  
                    // Connecting right subtree 
                    node.right = rtrees.get(k); 
  
                    // Adding this tree to list 
                    trees.add(node); 
                } 
            } 
        } 
        return trees; 
    } 
  
    public static void main(String args[]) { 
        int in[] = {4, 5, 7}; 
        int n = in.length; 
        BinaryTree tree = new BinaryTree(); 
        Vector<Node> trees = tree.getTrees(in, 0, n - 1); 
        System.out.println("Preorder traversal of different "+ 
                           " binary trees are:"); 
        for (int i = 0; i < trees.size(); i++) { 
            tree.preOrder(trees.get(i)); 
            System.out.println(""); 
        } 
    } 
} 