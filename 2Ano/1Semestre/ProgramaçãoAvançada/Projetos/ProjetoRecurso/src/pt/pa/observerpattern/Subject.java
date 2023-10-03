package pt.pa.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class for extension of other class, i.e. Observable objects.
 *
 * @author Guilherme B. 202001870
 */
public class Subject implements Observable {

    private List<Observer> observers;

    public Subject() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object arg) {
        for(Observer o : observers) {
            o.update(this, arg);
        }
    }
}
