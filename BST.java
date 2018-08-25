import java.io.*;
// implementations are recursive
class BST
{  protected Node root;  // root of tree
   // constructor definition
   BST()
   {  root = null;
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

   protected String minKey(Node root)
   {  String min_key = root.key;
      while (root.left != null)
      {  min_key = root.left.key;
         root = root.left;
      }
      return min_key;
   }

   public void preorder()
   {  preorder(root);
   }

   protected void preorder(Node current)
   {  if (current == null) return;
      System.out.println(current);
      preorder(current.left);
      preorder(current.right);
   }

   public void inorder()
   {  inorder(root);
   }

   protected void inorder(Node current)
   {  if (current == null) return;
      inorder(current.left);
      System.out.println(current);
      inorder(current.right);
   }

   public void postorder()
   {  postorder(root);
   }

   protected void postorder(Node current)
   {  if (current == null) return;
      postorder(current.left);
      postorder(current.right);
      System.out.println(current);
   }

   public void serialize() throws IOException
   {  File outfile = new File("tree.txt");
      FileWriter fw = new FileWriter(outfile);
      BufferedWriter bw = new BufferedWriter(fw);
      serialize(bw, root);
      bw.close();
      fw.close();
      System.out.println(" binary search tree serialized");
   }

   protected void serialize(BufferedWriter bw, Node current) throws IOException
   {  if (current == null) return;
      else
      {  bw.write(current.toString());
         bw.newLine();
      }
      serialize(bw, current.left);
      serialize(bw, current.right);
   }

   public void deserialize() throws IOException
   {  File infile = new File("tree.txt");
      FileReader fr = new FileReader(infile);
      BufferedReader br = new BufferedReader(fr);
      deserialize(br);
      br.close();
      fr.close();
      System.out.println(" binary search tree deserialized");
   }

   protected void deserialize(BufferedReader br) throws IOException
   {  String line;
      while ((line = br.readLine()) != null) {
         int tabidx = line.indexOf('\t');
         String key = line.substring(0, tabidx);
         String data = line.substring(tabidx + 1);
         Node newnode = new Node(key, data);
         insert(newnode);
      }
   }

   public void serialize_() throws IOException
   {  File outfile = new File("tree.dat");
      FileOutputStream fos = new FileOutputStream(outfile);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      serialize_(bos, root);
      bos.close();
      fos.close();
      System.out.println(" binary search tree serialized to binary file");
   }

   protected void serialize_(BufferedOutputStream bos, Node current)
      throws IOException
   {  if (current != null)
      {  bos.write(current.toRecord().getBytes());
         serialize_(bos, current.left);
         serialize_(bos, current.right);
      }
   }

   public void deserialize_() throws IOException
   {  File infile = new File("tree.dat");
      FileInputStream fis = new FileInputStream(infile);
      BufferedInputStream bis = new BufferedInputStream(fis);
      deserialize_(bis);
      bis.close();
      fis.close();
      System.out.println(" binary search tree serialized from binary file");
   }

   // read one record at a time from file and insert into binary search tree
   protected void deserialize_(BufferedInputStream bis)
      throws IOException
   {  final int RECSIZ = 80, FLDSIZ = 40;
      byte[] buffer = new byte[RECSIZ];
      while ( bis.read(buffer) != -1 )
      {  String key = new String(buffer, 0, FLDSIZ);
         String data = new String(buffer, FLDSIZ - 1, FLDSIZ);
         Node record = new Node(key.trim(), data.trim());
         insert(record);
      }
   }

   public int height()
   {  return height(root);
   }

   protected static int height(Node root)
   {  return root == null ? 0 : root.height;
   }

   protected static int max(int m, int n)
   {  if (m >= n) return m;
      else return n;
   }

   protected static int min(int m, int n)
   {  if (m <= n) return m;
      else return n;
   }
}
