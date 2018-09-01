import java.io.*;

public class TestBST
{  public static void main(String args[]) throws IOException
   {  long t1 = System.currentTimeMillis();
      if (args.length < 2)
      {  System.err.println(" Usage: java TestBST text/binary filename");
         System.exit(0);
      }
      String flag = args[0];
      String infile = args[1];
      if (flag.compareTo("text") != 0 && flag.compareTo("binary") != 0)
      {  System.err.println(" Usage: java TestAVL text/binary filename");
         System.exit(0);
      }
      BST tree = new BST();
      if (flag.compareTo("text") == 0) tree.readTextFile(infile);
      if (flag.compareTo("binary") == 0) tree.readBinaryFile(infile);
      System.out.println(" h = " + tree.height());
      if (flag.compareTo("text") == 0) tree.textSerialize();
      if (flag.compareTo("binary") == 0) tree.binarySerialize();
		tree.defoliate();
      long t2 = System.currentTimeMillis();
      float elapsed = (t2-t1)/1000.0F;
      System.out.println(" elapsed time = " + elapsed + " seconds");
   }
}
