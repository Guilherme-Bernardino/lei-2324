package pt.pa.adts;

public class CityInvalidOperation extends RuntimeException {
    public CityInvalidOperation() {
        super("City: Invalid Operation!!!");
    }

    public CityInvalidOperation(String message) {
        super(message);
    }
}
