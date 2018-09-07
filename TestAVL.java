import java.io.*;

class TestAVL
{  public static void main(String args[]) throws IOException
   {  long t1 = System.currentTimeMillis();
      if (args.length == 0)
      {  System.err.println("Usage: java TestBST filename");
         System.exit(1);
      }
      AVL tree = new AVL();
      tree.readTextFile(args[0]);
      if (tree.isEmpty() == false)
      {  System.out.println("h = " + tree.height());
         tree.serialize();
         tree.defoliate();
         tree.deserialize();
         tree.defoliate();
      }
      long t2 = System.currentTimeMillis();
      float elapsed = (t2-t1)/1000.0F;
      System.out.println("elapsed time = " + elapsed + " seconds");
   }
}
