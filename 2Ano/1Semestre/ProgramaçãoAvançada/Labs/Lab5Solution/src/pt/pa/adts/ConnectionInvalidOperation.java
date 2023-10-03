package pt.pa.adts;

public class ConnectionInvalidOperation extends RuntimeException {
    public ConnectionInvalidOperation() {
        super("Edge: Invalid Operation!!!");
    }

    public ConnectionInvalidOperation(String message) {
        super(message);
    }
}
