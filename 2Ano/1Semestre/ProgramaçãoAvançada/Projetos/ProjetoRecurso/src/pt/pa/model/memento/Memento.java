package pt.pa.model.memento;

/**
 * Memento interface for Memento pattern, memento objects implement this interface.
 *
 * @author Guilherme B. 202001870
 */
public interface Memento {

    /**
     * Returns a String of said saved object on this Memento.
     *
     * @return state object description
     */
    String getDescription();
}
