package pt.pa.model.memento;

public class NoMementoException extends RuntimeException {

    public NoMementoException() {
        super("There is no Memento");
    }

    public NoMementoException(String s) {
        super(s);
    }

}
