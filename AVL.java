import java.io.*;

public class AVL extends BST
{  public boolean serialized;

   protected Node insert(Node node, Node newnode)
   {  if (node == null) return newnode;
      // BST insertion
      if (newnode.key.compareTo(node.key) < 0)
         node.left = insert(node.left, newnode);
      else if (newnode.key.compareTo(node.key) > 0)
         node.right = insert(node.right, newnode);
      else
         return node;

      node.height = 1 + max(height(node.left), height(node.right));
      if (serialized == true) return node;
      int balance_no = 0;
      if (node != null)
         balance_no = height(node.left) - height(node.right);

      // left left
      if (balance_no > 1 && newnode.key.compareTo(node.left.key) < 0)
         return rightRotate(node);
      // right right
      if (balance_no < -1 && newnode.key.compareTo(node.right.key) > 0)
         return leftRotate(node);
      // left right
      if (balance_no > 1 && newnode.key.compareTo(node.left.key) > 0) {
         node.left = leftRotate(node.left);
         return rightRotate(node);
      }
      // right left
      if (balance_no < -1 && newnode.key.compareTo(node.right.key) < 0) {
         node.right = rightRotate(node.right);
         return leftRotate(node);
      }

      return node;
   }

   private Node rightRotate(Node y)
   {  Node x = y.left;
      Node T2 = x.right;

      // Perform rotation
      x.right = y;
      y.left = T2;

      // Update heights
      y.height = max(height(y.left), height(y.right)) + 1;
      x.height = max(height(x.left), height(x.right)) + 1;

      // Return new root
      return x;
   }

   private Node leftRotate(Node x)
   {  Node y = x.right;
      Node T2 = y.left;

      // Perform rotation
      y.left = x;
      x.right = T2;

      //  Update heights
      x.height = max(height(x.left), height(x.right)) + 1;
      y.height = max(height(y.left), height(y.right)) + 1;

      // Return new root
      return y;
   }

   public void readTextFile(String filename) throws IOException
   {  File infile = new File(filename);
      FileReader fr = new FileReader(infile);
      BufferedReader br = new BufferedReader(fr);
      deserialize(br);
      br.close();
      fr.close();
      System.out.println(" Text file read into AVL binary search tree.");
   }

   public void readBinaryFile(String filename) throws IOException
   {  File infile = new File(filename);
      FileInputStream fis = new FileInputStream(infile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      deserialize_(bis);
      bis.close();
      fis.close();
      System.out.println(" Binary file read into AVL binary search tree.");
   }
}
