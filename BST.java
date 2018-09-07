import java.io.*;
import java.util.Stack;

// implementations are recursive
class BST
{  static class Node
   {  protected String key;
      protected Node left;
      protected Node right;

      // constructor definition
      Node(String key)
      {  this.key = key;
         this.left = null;
         this.right = null;
      }

      public String toString()
      {  return key;
      }
   }

   protected Node root;  // root of tree

   // constructor definition
   BST()
   {  root = null;
   }

   // whence ye came thither shall ye return
   public void defoliate()
   {  root = null;
   }

   public boolean isEmpty()
   {  return root == null ? true : false;
   }

   public void insert(String data)
   {  root = insert(root, data);
   }

   private Node insert(Node root, String data)
   {  if (root == null)
         return new Node(data);
      if (data.compareTo(root.key) < 0)
         root.left = insert(root.left, data);
      else if (data.compareTo(root.key) > 0)
         root.right = insert(root.right, data);
      else
         ;
      return root;
   }

   public String retrieve(String key)
   {  Node target = retrieve(root, key);
      if (target != null)
         return target.key;
      else
         return "target node not found";
   }

   protected Node retrieve(Node current, String key)
   {  if (current == null)
         return null;
      else if (key.compareTo(current.key) == 0)
         return current;
      else if (key.compareTo(current.key) < 0)
         return retrieve(current.left, key);
      else
         return retrieve(current.right, key);
   }

   public void update(String old_key, String new_key)
   {  if (old_key.compareTo(new_key) == 0) return;
      delete(old_key);
      insert(new_key);
   }

   public void delete(String key)
   {  root = delete(root, key);
   }

   protected Node delete(Node root, String key)
   {  if (root == null) return root;
      if (key.compareTo(root.key) < 0)
         root.left = delete(root.left, key);
      else if (key.compareTo(root.key) > 0)
         root.right = delete(root.right, key);
      else
      {  // node with only one child or no child
         if (root.left == null)
            return root.right;
         else if (root.right == null)
            return root.left;
         // node with two children: Get the inorder successor (smallest
         // in the right subtree)
         root.key = minKey(root.right);
         // Delete the inorder successor
         root.right = delete(root.right, root.key);
      }
      return root;
   }

   protected String minKey(Node node)
   {  if (node.left == null) return node.key;
      // return node.left.key;
      return minKey(node.left);
   }

   public void preorder()
   {  preorder(root);
   }

   protected void preorder(Node current)
   {  if (current != null)
      {  System.out.println(current);
         preorder(current.left);
         preorder(current.right);
      }
   }

   public void inorder()
   {  inorder(root);
   }

   protected void inorder(Node current)
   {  if (current != null)
      {  inorder(current.left);
         System.out.println(current);
         inorder(current.right);
      }
   }

   public void postorder()
   {  postorder(root);
   }

   protected void postorder(Node current)
   {  if (current != null)
      {  postorder(current.left);
         postorder(current.right);
         System.out.println(current);
      }
   }

   public void serialize() throws IOException
   {  File outfile = new File("tree.txt");
      FileWriter fw = new FileWriter(outfile);
      BufferedWriter bw = new BufferedWriter(fw);
      serialize(bw, root);
      bw.close();
      fw.close();
      System.out.println("binary search tree serialized");
   }

   // iterative serialization
   protected void serialize(BufferedWriter bw, Node root)
      throws IOException
   {  if (root == null) return;
      Stack<Node> nodeStack = new Stack<Node>();
      nodeStack.push(root);

      while (!nodeStack.empty())
      {  Node current = nodeStack.peek();
         bw.write(current.toString());
         bw.newLine();
         nodeStack.pop();
         if (current.right != null)
            nodeStack.push(current.right);
         if (current.left != null)
            nodeStack.push(current.left);
      }
   }

   public void deserialize() throws IOException
   {  File infile = new File("tree.txt");
      FileReader fr = new FileReader(infile);
      BufferedReader br = new BufferedReader(fr);
      deserialize(br);
      br.close();
      fr.close();
      System.out.println("binary search tree deserialized");
   }

   // iterative deserialization
   protected void deserialize(BufferedReader br) throws IOException
   {  String data;
      while ((data = br.readLine()) != null)
         insert (data);
   }

   // read non-serialized files
   public void readTextFile(String filename) throws IOException
   {  File infile = new File(filename);
      if (infile.exists())
      {  FileReader fr = new FileReader(infile);
         BufferedReader br = new BufferedReader(fr);
         readTextFile(br);
         br.close();
         fr.close();
         System.out.println(filename + " read into binary search tree");
      }
      else
      {  System.err.println("error - file does not exist");
         System.exit(1);
      }
   }

   protected void readTextFile(BufferedReader br)
   throws IOException
   {  String data;
      while ((data = br.readLine()) != null)
         insert(data);
   }
}
