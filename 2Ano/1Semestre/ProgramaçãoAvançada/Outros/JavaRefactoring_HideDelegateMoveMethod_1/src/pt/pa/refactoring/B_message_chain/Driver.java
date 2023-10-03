package pt.pa.refactoring.B_message_chain;

import java.util.Objects;

public class Driver {
    private final String fullName;
    private Truck assignedTruck;

    public Driver(String fullName, Truck assignedTruck) {
        if(fullName == null) throw new IllegalArgumentException("Name cannot be null.");

        this.fullName = fullName;
        this.assignedTruck = assignedTruck;
    }

    public String getFirstName() {
        return fullName.split(" ", 2)[0];
    }

    public String getFullName() {
        return fullName;
    }

    //necessary now?
    //public Truck getAssignedTruck() {
    //    return assignedTruck;
    //}

    public void setAssignedTruck(Truck assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return fullName.equals(driver.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    public String getAssignedTruckLicensePlateId() {
        return assignedTruck.getLicencePlate().getId();
    }

    public String getAssignedTruckMakeModel() {
        return assignedTruck.getMakeModel();
    }

    public boolean firstNameStartsWith(char firstLetter) {
        return fullName.charAt(0) == firstLetter;
    }
}
