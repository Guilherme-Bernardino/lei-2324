package pt.pa.adts;

public class InvalidSizeException extends IllegalArgumentException {
    public InvalidSizeException() {
        super("Invalid size exception");
    }

    public InvalidSizeException(String s) {
        super(s);
    }
}
