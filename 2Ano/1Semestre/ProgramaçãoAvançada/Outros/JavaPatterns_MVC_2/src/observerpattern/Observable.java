package observerpattern;

/**
 * @author brunomnsilva
 */
public interface Observable {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Object arg);

}
