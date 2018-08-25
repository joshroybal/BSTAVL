import java.io.*;

public class TestAVL
{  public static void main(String args[]) throws IOException
   {  long t1 = System.currentTimeMillis();
      AVL tree = new AVL();
      tree.readBinaryFile(args[0]);
      System.out.println(" h = " + tree.height());
      tree.serialize_();
      long t2 = System.currentTimeMillis();
      float elapsed = (t2-t1)/1000.0F;
      System.out.println(" elapsed time = " + elapsed + " seconds");
   }
}
