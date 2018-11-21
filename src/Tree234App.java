/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree234;

import java.util.Scanner;

/**
 *
 * @author intel
 */

public class Tree234App{
   
    public static Scanner user = new Scanner(System.in);
    
    public static void main (String args[]){

        int choice, num, count =0;

        Tree234<Integer> tree = new Tree234<>();


        while (true){
            System.out.println("");
            System.out.println("*************************************");
            System.out.println("Press 1 to enter data\n"+ 
                                  "Press 2 to delete data\n"+
                                 "Press 3 to display tree\n"+ 
                                  "Press 4 to find data\n"+
                                 "Press 5 for BFS traversals\n"+ 
                                  "Press 6 to Dfs traversals\n"+
                                 "Press 7 to exit\n");
            System.out.println("****************************************");
    System.out.print("-->Enter your choice: ");
    choice = user.nextInt();
   
    switch(choice){
    
        case 1:
        
            System.out.print("Enter element to insert: ");
            num = user.nextInt();
            if(tree.insert(num)){
                System.out.println("Successfully Inserted item " + num);
            }
            else
                System.out.println("Duplicate cannot be inserted");
                System.out.println("");
            break;
     
        case 2:
           
            System.out.print("Enter element to delete: ");
            num = user.nextInt();
            
            if(tree.remove(tree.getRoot(), num))
            System.out.print("Successfully delete item " + num);
            
            else
                System.out.print("Cannot delete item " + num);
            break;
      
        case 3:
         
            System.out.println("Resultant tree is");
            tree.displayTree();
            break;
      
        case 4:
          
            System.out.print("Enter element to find: ");
            num = user.nextInt();
            if(tree.find(num))
                System.out.print("Successfully found item " + num);
           else
              System.out.print("Cannot find the item " + num);
             break;
       
        case 5:
            System.out.println("Bfs traversl of a tree is");
             tree.BfsTraversals(tree.getRoot());
              break;
       
        case 6:
            
            System.out.print("Press 1 for inOrder traversal\n" +
                             "Press 2 for preOrder traversal\n" +
                             "Press 3 for postOrder traversal\n");
                       num = user.nextInt();
                       tree.DfsTraversal(num);
                       break;
        case 7:
            count =1;
            break;
        default:
              System.out.println("Invalid entry");

    
        }

        if (count == 1)
            break;
        
        }
    }
}
