package pt.pa.model.memento;

/**
 * Originator interface for Memento pattern, objects implement this interface.
 * Able to create a memento, added to mementos and set memento to previous.
 *
 * @author Guilherme B. 202001870
 */
public interface Originator {

    /**
     * Method responsible for adding a new instance of a saved state, i.e. state of an object.
     *
     * @return new Memento created
     */
    public Memento createMemento();

    /**
     * Method responsible for setting a state of an object to saved state, i.e. saved memento.
     *
     * @param savedState
     */
    public void setMemento(Memento savedState);

}
