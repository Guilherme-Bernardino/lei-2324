package pt.pa.model.memento;

/**
 * Memento Exception, fails if no memento is set or exists.
 *
 * @author Guilherme B. 202001870
 */
public class NoMementoException extends RuntimeException {

    public NoMementoException() {
        super("There is no Memento");
    }

    public NoMementoException(String s) {
        super(s);
    }

}
