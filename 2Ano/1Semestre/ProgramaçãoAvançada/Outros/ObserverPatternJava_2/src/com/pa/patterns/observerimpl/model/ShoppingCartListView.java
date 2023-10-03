package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingCartListView implements Observer {

    @Override
    public void update(Object obj) {
        if(obj instanceof ShoppingCart) {
            ShoppingCart cart = (ShoppingCart)obj;

            System.out.println("List View - " + cart.getName());
            List<Product> myList = new ArrayList<>(cart.getProducts());
            Collections.sort(myList, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return (int)(p1.getCost() - p2.getCost());
                }
            });
            for (Product p : myList) {
                System.out.printf("%s - %.2f€ \n", p.getName(), p.getCost());
            }

        }
    }

}
