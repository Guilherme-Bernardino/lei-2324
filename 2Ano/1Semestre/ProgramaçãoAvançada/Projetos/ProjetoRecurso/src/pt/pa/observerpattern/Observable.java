package pt.pa.observerpattern;

/**
 * Observable on the Observer pattern is the object being changed and delegating information the observer class.
 *
 * @author Guilherme B. 202001870
 */
public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object arg);

}
