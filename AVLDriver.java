import java.io.*;

public class AVLDriver
{  public static void main(String args[]) throws IOException
   {  InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader input = new BufferedReader(reader);
      AVL tree = new AVL();
      if (args.length > 0)
      {  tree.serialized = false;
         tree.readBinaryFile(args[0]);
      }
      else
      {  tree.serialized = true;
         tree.binaryDeserialize();
      }
      System.out.println(" h = " + tree.height());
      printmenu();
      char choice = input.readLine().charAt(0);
      while (choice != '0')
      {  switch (choice)
         {  case '1':
               while (true)
               {  BST.Node newnode = enterNode(input);
                  if (newnode == null) break;
                  tree.insert(newnode);
               }
               break;
            case '2':
               System.out.print(" key: ");
               String key = input.readLine();
               AVL.Node target = tree.retrieve(key);
               if (target != null) System.out.println(" data: " + target.data);
               else System.out.println(" target not found");
               break;
            case '3':
               System.out.print(" key: ");
               key = input.readLine();
               target = tree.retrieve(key);
               if (target != null)
               {  System.out.println(" data: " + target.data);
                  System.out.print(" key: ");
                  key = input.readLine();
                  System.out.print(" data: ");
                  String data = input.readLine();
                  tree.update(target, key, data);
               }
               else
                  System.out.println(" target not found");
               break;
            case '4':
               System.out.print(" key: ");
               key = input.readLine();
               tree.remove(key);
               break;
            case '5':
               tree.preorder();
               break;
            case '6':
               tree.inorder();
               break;
            case '7':
               tree.postorder();
               break;
            case '8':
               tree.textSerialize();
					tree.binarySerialize();
               break;
            default:
               System.out.println(" error - please try again");
         }
         printmenu();
         choice = input.readLine().charAt(0);
      }
      input.close();
		tree.textSerialize();
      tree.binarySerialize();
		tree.defoliate();
      System.out.println(" copyright (c) 2018 Josh Roybal");
   }

   public static void printmenu()
   {  System.out.println(" AVL balanced binary search tree");
      System.out.println(" 0 - terminate processing");
      System.out.println(" 1 - create records");
      System.out.println(" 2 - retrieve records");
      System.out.println(" 3 - update records");
      System.out.println(" 4 - delete records");
      System.out.println(" 5 - pre-order traversal");
      System.out.println(" 6 - in-order traversal");
      System.out.println(" 7 - post-order traversal");
      System.out.println(" 8 - serialize binary search tree");
      System.out.print(" -> ");
   }

   public static AVL.Node enterNode(BufferedReader br) throws IOException
   {  System.out.print(" key: ");
      String key = br.readLine();
      if (key.length() == 0) return null;
      System.out.print(" data: ");
      String data = br.readLine();
      return new AVL.Node(key, data);
   }
}
