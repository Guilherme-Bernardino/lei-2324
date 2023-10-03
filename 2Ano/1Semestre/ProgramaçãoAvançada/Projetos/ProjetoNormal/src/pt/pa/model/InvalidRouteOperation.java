package pt.pa.model;

public class InvalidRouteOperation extends RuntimeException{
    public InvalidRouteOperation() {
        super("Route: Invalid Operation!!!");
    }

    public InvalidRouteOperation(String message) {
        super(message);
    }
}
