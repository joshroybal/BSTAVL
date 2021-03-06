import java.io.*;

public class AVL extends BST
{  static class Node extends BST.Node
   {  private int height;
      private int balance;
      private Node left;
      private Node right;

      // constructor definition
      Node(String key)
      {  super(key);
         this.height = 1;
         this.left = null;
         this.right = null;
      }

      void setSuper()
      {  if (this == null) return;
         super.right = this.right;
         super.left = this.left;
      }
   }

   private Node root;

   public boolean isEmpty()
   {  return root == null ? true : false;
   }

   public void insert(String data)
   {  root = insert(root, data);
   }

   private Node insert(Node current, String data)
   {  if (current == null)
         return new Node(data);

      if (data.compareTo(current.key) < 0)
         current.left = insert(current.left, data);
      else if (data.compareTo(current.key) > 0)
         current.right = insert(current.right, data);
      else
         ;  // ignore duplicates

      current.height = 1 + max(height(current.left), height(current.right));
      int balance = height(current.left) - height(current.right);

      // left left
      if (balance > 1 && data.compareTo(current.left.key) < 0)
         return rightRotate(current);
      // right right
      if (balance < -1 && data.compareTo(current.right.key) > 0)
         return leftRotate(current);
      // left right
      if (balance > 1 && data.compareTo(current.left.key) > 0)
      {  current.left = leftRotate(current.left);
         return rightRotate(current);
      }
      // right left
      if (balance < -1 && data.compareTo(current.right.key) < 0)
      {   current.right = rightRotate(current.right);
         return leftRotate(current);
      }

      current.setSuper();
      return current;
   }

   private Node rightRotate(Node p)
   {  Node q = p.left;
      Node t = q.right;

      // Perform rotation
      q.right = p;
      p.left = t;

      // Update heights
      p.height = max(height(p.left), height(p.right)) + 1;
      q.height = max(height(q.left), height(q.right)) + 1;

      // set BST nodes
      p.setSuper();
      q.setSuper();

      // Return new root
      return q;
   }

   private Node leftRotate(Node p)
   {  Node q = p.right;
      Node t = q.left;

      // Perform rotation
      q.left = p;
      p.right = t;

      //  Update heights
      p.height = max(height(p.left), height(p.right)) + 1;
      q.height = max(height(q.left), height(q.right)) + 1;

      // set BST nodes
      p.setSuper();
      q.setSuper();

      // Return new root
      return q;
   }

   public String retrieve(String data)
   {  Node target = (Node)retrieve(root, data);
      if (target != null)
         return target.key;
      else
         return "target node not found";
   }

   public void delete(String key)
   {  root = (Node)delete(root, key);
   }

   public void preorder()
   {  preorder(root);
   }

   public void inorder()
   {  inorder(root);
   }

   public void postorder()
   {  postorder(root);
   }

   public int height()
   {  return height(root);
   }

   private static int height(Node node)
   {  return node == null ? 0 : node.height;
   }

   private static int max(int m, int n)
   {  return m >= n ? m : n;
   }

   private static int min(int m, int n)
   {  return m <= n ? m : n;
   }

   // read non-serialized files with AVL insertion override
   protected void readTextFile(BufferedReader br)
   throws IOException
   {  String line;
      while ((line = br.readLine()) != null)
         insert(line);
   }

   // serialization override
   public void serialize() throws IOException
   {  File outfile = new File("tree.txt");
      FileWriter fw = new FileWriter(outfile);
      BufferedWriter bw = new BufferedWriter(fw);
      serialize(bw, root);
      bw.close();
      fw.close();
      System.out.println("binary search tree serialized");
   }
}
