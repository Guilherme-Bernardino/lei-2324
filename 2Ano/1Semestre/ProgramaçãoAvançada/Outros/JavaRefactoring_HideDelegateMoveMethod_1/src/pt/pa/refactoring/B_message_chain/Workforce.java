package pt.pa.refactoring.B_message_chain;

import java.util.ArrayList;
import java.util.List;

public class Workforce {
    private final List<Driver> driverList;

    public Workforce() {
        driverList = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        if(!driverList.contains(driver)) {
            driverList.add(driver);
        }
    }

    /**
     * Returns all drivers in text format.
     * @return drivers in text format.
     */
    public String showAllDrivers() {
        return showDriversNameStartingWithLetter('*');
    }

    /**
     * Returns selected drivers in text format.
     * @param firstLetter first letter of driver name or '*' for all.
     * @return drivers in text format.
     */
    public String showDriversNameStartingWithLetter(char firstLetter) {
        StringBuilder sb = new StringBuilder();

        for(Driver driver : driverList) {
            if(firstLetter == '*' || driver.firstNameStartsWith(firstLetter)) {
                sb.append(String.format("%s is driving the truck %s (Licence Plate: %s)",
                        driver.getFullName(),
                        driver.getAssignedTruckMakeModel(),
                        driver.getAssignedTruckLicensePlateId()));
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    public Driver searchTruckDriverOf(String licenceId) {
        for(Driver driver : driverList) {
            if( driver.getAssignedTruckLicensePlateId().equalsIgnoreCase(licenceId)) {
                return driver;
            }
        }
        return null; /* not found */
    }
}
