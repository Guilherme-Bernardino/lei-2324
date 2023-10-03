package pt.pa.refactoring.B_message_chain;

public class Truck {
    private final String make;
    private final String model;
    private final LicencePlate licencePlate;

    public Truck(String make, String model, LicencePlate licencePlate) {
        this.make = make;
        this.model = model;
        this.licencePlate = licencePlate;
    }

    public String getMakeModel() {
        return make + " - " + model;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public LicencePlate getLicencePlate() {
        return licencePlate;
    }
}
