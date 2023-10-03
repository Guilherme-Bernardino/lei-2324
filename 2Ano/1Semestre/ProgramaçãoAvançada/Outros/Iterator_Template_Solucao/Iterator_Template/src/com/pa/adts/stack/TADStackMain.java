/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.adts.stack;

import java.util.Iterator;

/**
 *
 * @author patricia.macedo
 */
public class TADStackMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Stack<Integer> stack1 = new StackArray<>();
        Stack<Integer> stack1 = new StackLinked<>();
        for(int i=0; i<10;i++)
            stack1.push(i);

        for(int e : stack1) { //pq Stack implements Iterable!!!
            System.out.println(e);
        }

        System.out.println("Pop: " + stack1.pop());

        Iterator<Integer> it = stack1.iteratorReversed();
        while(it.hasNext()) {
            int e = it.next();
            System.out.println(e);
        }


    }

}
