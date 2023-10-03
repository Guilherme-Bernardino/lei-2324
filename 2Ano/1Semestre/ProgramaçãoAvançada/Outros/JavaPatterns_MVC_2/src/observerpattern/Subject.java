package observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brunomnsilva
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
