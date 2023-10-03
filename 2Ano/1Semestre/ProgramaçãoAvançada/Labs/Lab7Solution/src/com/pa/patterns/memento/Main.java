/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.patterns.memento;

import com.pa.patterns.memento.model.NoMementoException;
import com.pa.patterns.memento.model.ShoppingCartController;

public class Main {
    public static void main(String[] args) throws NoMementoException {
        /*
        ShoppingCartController cart = new ShoppingCartController();
        cart.addProduct("Chewing Gum", 0.1);
        cart.addProduct("Powerbank", 30);
        cart.addProduct("PS4", 350);

        System.out.println(cart.showAll());
        cart.removeProduct("PS4");
        System.out.println(cart.showAll());
        
        for(int i=0; i<4;i++) {
            cart.undo();
            System.out.println("UNDO - " + (i+1) + " \n" + cart.showAll() );
        }*/
    }
}
