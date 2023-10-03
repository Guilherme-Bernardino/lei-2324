package com.pa.patterns.memento.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ShoppingCart implements Originator {
    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void reset() {
        products.clear();
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getCost();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.valueOf(products);
    }

    @Override
    public Memento createMemento() {
        return new ShoppingCartMemento(products);
    }

    @Override
    public void setMemento(Memento savedState) {
        if (savedState instanceof ShoppingCartMemento) {
            reset();
            getProducts().addAll(((ShoppingCartMemento) savedState).state);
        }
    }

    private class ShoppingCartMemento implements Memento {
        private List state;

        public ShoppingCartMemento(List<Product> stateToSave) {
            this.state = new ArrayList<>(stateToSave); //copy of list
        }

        @Override
        public String getDescription() {
            return state.toString();
        }
    }
}
