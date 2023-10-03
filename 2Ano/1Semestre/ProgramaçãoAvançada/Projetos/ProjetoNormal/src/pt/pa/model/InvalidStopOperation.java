package pt.pa.model;

public class InvalidStopOperation extends RuntimeException{
    public InvalidStopOperation() {
        super("Hub: Invalid Operation!!!");
    }

    public InvalidStopOperation(String message) {
        super(message);
    }
}
