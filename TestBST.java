import java.io.*;

public class TestBST
{  public static void main(String args[]) throws IOException
   {  long t1 = System.currentTimeMillis();
      BST tree = new BST();
      tree.deserialize_();
      System.out.println(" h = " + tree.height()); 
      tree.serialize_();
      long t2 = System.currentTimeMillis();
      float elapsed = (t2-t1)/1000.0F;
      System.out.println(" elapsed time = " + elapsed + " seconds");
   }
}
