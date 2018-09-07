import java.io.*;

class AVLDriver
{  public static void main(String args[]) throws IOException
   {  AVL tree = new AVL();
      if (args.length > 0)
         tree.readTextFile(args[0]);

      InputStreamReader reader = new InputStreamReader(System.in);
      BufferedReader input = new BufferedReader(reader);

      printmenu();
      char choice = input.readLine().charAt(0);
      while (choice != '0')
      {  if (choice == '1')
            tree.preorder();
         else if (choice == '2')
            tree.inorder();
         else if (choice == '3')
            tree.postorder();
         else if (choice == '4')
         {  System.out.print("key: ");
            String key = input.readLine();
            while (key.length() > 0)
            {  String data = tree.retrieve(key);
               System.out.println("data: " + data);
               System.out.print("key: ");
               key = input.readLine();
            }
         }
         else if (choice == '5')
         {  System.out.print("key: ");
            String key = input.readLine();
            while (key.length() > 0)
            {  tree.insert(key);
               System.out.print("key: ");
               key = input.readLine();
            }
         }
         else if (choice == '6')
         {  System.out.print("key: ");
            String key = input.readLine();
            while (key.length() > 0)
            {  tree.delete(key);
               System.out.print("key: ");
               key = input.readLine();
            }
         }
         else if (choice == '7')
         {  System.out.print("key: ");
            String old_key = input.readLine();
            while (old_key.length() > 0)
            {  System.out.print("modified key: ");
               String new_key = input.readLine();
               tree.update(old_key, new_key);
               System.out.print("key: ");
               old_key = input.readLine();
            }
         }
         else
            System.out.println("error - please try again");

         printmenu();
         choice = input.readLine().charAt(0);
      }

      input.close();
      reader.close();

      tree.serialize();
      tree.defoliate();

      System.out.println("copyright (c) 2018 Josh Roybal");
   }

   public static void printmenu()
   {  System.out.println("binary search tree");
      System.out.println("0 - terminate processing");
      System.out.println("1 - preorder traversal");
      System.out.println("2 - inorder traversal");
      System.out.println("3 - postorder traversal");
      System.out.println("4 - retrieve nodes");
      System.out.println("5 - insert nodes");
      System.out.println("6 - delete nodes");
      System.out.println("7 - modify nodes");
      System.out.print(" -> ");
   }
}
