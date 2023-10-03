package pt.pa.observerpattern;

/**
 * An observer on the Observer pattern is the interface waiting for a an update on the observable object.
 * Observer changes information whenever something is changed on the model.
 *
 * @author Guilherme B. 202001870
 */
public interface Observer {

    void update(Observable subject, Object arg);

}
