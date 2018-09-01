import java.io.*;
// implementations are recursive
class BST
{  static class Node
   {  // Objects represents nodes of linked list
      protected String key;
      protected String data;
      protected int height;
      protected Node left;
      protected Node right;

      // constructor definition
      Node(String key, String data)
      {  this.key = key;
         this.data = data;
         this.height = 1;
         this.left = null;
         this.right = null;
      }

      public String toString()
      {  return key + "\t" + data;
      }

      public byte[] toRecord()
      {  return String.format("%-40s%-40s", key, data).getBytes();
      }
   }
 
   static protected Node root;  // root of tree
   public boolean serialized; // input file serialized flag
   // constructor definition
   BST()
   {  root = null;
   }

	public boolean isEmpty()
	{	return root == null ? true : false;
	}
	
   public void insert(Node newnode)
   {  root = insert(root, newnode);
   }

   protected Node insert(Node root, Node newnode)
   {  if (root == null) return newnode;

      if (newnode.key.compareTo(root.key) <= 0)
         root.left = insert(root.left, newnode);
      else if (newnode.key.compareTo(root.key) > 0)
         root.right = insert(root.right, newnode);
      else
         return root;

      root.height = 1 + max(height(root.left), height(root.right));

      return root;
   }

   public Node retrieve(String key)
   {  return retrieve(root, key);
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

   public void update(Node target, String key, String data)
   {  if (target.key.compareTo(key) == 0)
      {  target.data = data;
         return;
      }
      remove(target.key);
      target = null;
      Node newnode = new Node(key, data);
      insert(newnode);
   }

   public void remove(String key)
   {  root = remove(root, key);
   }

   protected Node remove(Node root, String key)
   {  if (root == null) return root;
      if (key.compareTo(root.key) < 0)
         root.left = remove(root.left, key);
      else if (key.compareTo(root.key) > 0)
         root.right = remove(root.right, key);
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
         root.right = remove(root.right, root.key);
      }
      return root;
   }

   protected String minKey(Node node)
   {  if (node.left == null) return node.key;
      return node.left.key;
   }

   public void preorder()
   {  preorder(root);
   }

   protected void preorder(Node current)
   {  if (current != null)
      {  System.out.println(current.height + " " + current);
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
         System.out.println(current.height + " " + current);
      }
   }

   public void textSerialize() throws IOException
   {  File outfile = new File("tree.txt");
      FileWriter fw = new FileWriter(outfile);
      BufferedWriter bw = new BufferedWriter(fw);
      textSerialize(bw, root);
      bw.close();
      fw.close();
      System.out.println(" binary search tree serialized from text file");
   }

   protected void textSerialize(BufferedWriter bw, Node current) throws IOException
   {  if (current != null)
      {  bw.write(current.toString());
         bw.newLine();
         textSerialize(bw, current.left);
         textSerialize(bw, current.right);
      }
   }

   public void textDeserialize() throws IOException
   {  File infile = new File("tree.txt");
      if (!infile.exists()) return;
      FileReader fr = new FileReader(infile);
      BufferedReader br = new BufferedReader(fr);
      textDeserialize(br, root);
      br.close();
      fr.close();
      System.out.println(" binary search tree deserialized to text file");
   }

   protected void textDeserialize(BufferedReader br, Node node) throws IOException
   {  String line;
      if ((line = br.readLine()) != null)
      {  int tabidx = line.indexOf('\t');
         String key = line.substring(0, tabidx);
         String data = line.substring(tabidx + 1);
         node = new Node(key, data);
         insert(node);
         textDeserialize(br, node.left);
         textDeserialize(br, node.right);
      }
   }

   public void binarySerialize() throws IOException
   {  File outfile = new File("tree.dat");
      FileOutputStream fos = new FileOutputStream(outfile);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      binarySerialize(bos, root);
      bos.close();
      fos.close();
      System.out.println(" binary search tree serialized to binary file");
   }

   protected void binarySerialize(BufferedOutputStream bos, Node current)
      throws IOException
   {  if (current != null)
      {  bos.write(current.toRecord());
         binarySerialize(bos, current.left);
         binarySerialize(bos, current.right);
      }
   }

   public void binaryDeserialize() throws IOException
   {  File infile = new File("tree.dat");
		if (!infile.exists()) return;
      FileInputStream fis = new FileInputStream(infile);
		BufferedInputStream bis = new BufferedInputStream(fis);
		binaryDeserialize(bis, root);
		bis.close();
		fis.close();
		System.out.println(" binary search tree serialized from binary file");
   }

   // read one record at a time from file and insert into binary search tree
   protected void binaryDeserialize(BufferedInputStream bis, Node node)
      throws IOException
   {  final int RECSIZ = 80, FLDSIZ = 40;
      byte[] buffer = new byte[RECSIZ];
      if (bis.read(buffer) != -1 )
      {  String key = new String(buffer, 0, FLDSIZ);
         String data = new String(buffer, FLDSIZ - 1, FLDSIZ);
         node = new Node(key.trim(), data.trim());
         insert(node);
			binaryDeserialize(bis, node.left);
			binaryDeserialize(bis, node.right);
		}
   }

   // read non-serialized files
   public void readTextFile(String filename) throws IOException
   {  File infile = new File(filename);
      if (!infile.exists())
      {  System.out.println(" error - file does not exist");
         System.exit(1);
      }
      FileReader fr = new FileReader(infile);
      BufferedReader br = new BufferedReader(fr);
      readTextFile(br, root);
      br.close();
      fr.close();
      System.out.println(" text file read into AVL binary search tree");
   }

   protected void readTextFile(BufferedReader br, Node node)
   throws IOException
   {  String line;
      while ((line = br.readLine()) != null)
      {  int tabidx = line.indexOf('\t');
         String key = line.substring(0, tabidx);
         String data = line.substring(tabidx + 1);
         node = new Node(key, data);
         insert(node);
      }
   }

   public void readBinaryFile(String filename) throws IOException
   {  File infile = new File(filename);
      if (!infile.exists())
      {  System.out.println(" error - file does not exist");
         System.exit(1);
      }
      FileInputStream fis = new FileInputStream(infile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      readBinaryFile(bis, root);
      bis.close();
      fis.close();
      System.out.println(" binary file read into binary search tree");
   }

   protected void readBinaryFile(BufferedInputStream bis, Node node)
      throws IOException
   {  final int RECSIZ = 80, FLDSIZ = 40;
      byte[] buffer = new byte[RECSIZ];
      while (bis.read(buffer) != -1 )
      {  String key = new String(buffer, 0, FLDSIZ);
         String data = new String(buffer, FLDSIZ - 1, FLDSIZ);
         node = new Node(key.trim(), data.trim());
         insert(node);
      }
   }

   public void defoliate()
   {  root = null;
   }

   // utility methods
   public int height()
   {  return height(root);
   }

   protected static int height(Node root)
   {  return root == null ? 0 : root.height;
   }

   protected static int max(int m, int n)
   {  return m >= n ? m : n;
   }

   protected static int min(int m, int n)
   {  return m <= n ? m : n;
   }
}
