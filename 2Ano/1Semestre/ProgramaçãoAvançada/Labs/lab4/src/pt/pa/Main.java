/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.pa;


import pt.pa.adts.IntegerBST;
import pt.pa.adts.IntegerBinarySearchTree;

/**
 *
 * @author brunomnsilva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntegerBinarySearchTree bst = new IntegerBST();
        
        bst.insert(4);
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);        
        bst.insert(8);
        bst.insert(6);        
        bst.insert(5);
        bst.insert(10);
        bst.insert(14);
        bst.insert(9);
        
                
        Iterable<Integer> elems = bst.breadthOrder();
        for (Integer integer : elems) {
            System.out.println(integer);
        }
        System.out.println("BST:");
        System.out.println(bst);
/* 
        System.out.println("BST:");
        System.out.println(bst);
        
        System.out.println("Is empty? " + bst.isEmpty());
        System.out.println("Element 9 exists? " + bst.exists(9));
        
        System.out.println("Size of tree? " + bst.size());
        System.out.println("height of tree? " + bst.height());
        System.out.println("Minimum element? " + bst.minimum());
        System.out.println("Maximum element? " + bst.maximum());
        
        System.out.println("Sum of odd elements? " + bst.sumOdd());
        
        System.out.println("Sum of internal elements? " + bst.sumInternals());
        
        int threshold = 1;
        System.out.printf("Values greater than %d: \n", threshold);
        System.out.println("Count: " + bst.countGreaterThan(threshold));
        System.out.print("Elements: ");
        for(int v : bst.greaterThan(threshold)) {
            System.out.print(v + " ");
        }
        System.out.println();
        
        int elementToRemove = 4;
        //int elementToRemove = 8;
        //int elementToRemove = 3;
        //int elementToRemove = 10;
        
        System.out.println("Removing " + elementToRemove + " ...");
        bst.remove(elementToRemove);
        
        bst.insert(15);
        System.out.println("BST:");
        System.out.println(bst);
        
        //System.out.println("Size of tree? " + bst.size());
        //System.out.println("height of tree? " + bst.height());
        //System.out.println("Minimum element? " + bst.minimum());
        //System.out.println("Maximum element? " + bst.maximum());
        
        System.out.println("Sum of internal elements? " + bst.sumInternals());
        
        bst.remove(5);
        
        System.out.println("BST:");
        System.out.println(bst);
 */
}
    
}
