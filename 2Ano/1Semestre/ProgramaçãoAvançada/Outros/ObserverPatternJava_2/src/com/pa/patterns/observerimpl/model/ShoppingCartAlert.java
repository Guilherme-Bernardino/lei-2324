package com.pa.patterns.observerimpl.model;

import com.pa.patterns.observerimpl.observer.Observer;

public class ShoppingCartAlert implements Observer {

    private double maxValue;

    public ShoppingCartAlert(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void update(Object obj) {
        if(obj instanceof ShoppingCart) {
            ShoppingCart cart = (ShoppingCart) obj;

            Product last = cart.lastProductAdded();
            if(last.getCost() > maxValue) {
                System.out.printf("ALERT!!! - The product %s has exceeded the maximum value %.2f€ \n",
                        last.getName(), maxValue);
            }
        }
    }
}
