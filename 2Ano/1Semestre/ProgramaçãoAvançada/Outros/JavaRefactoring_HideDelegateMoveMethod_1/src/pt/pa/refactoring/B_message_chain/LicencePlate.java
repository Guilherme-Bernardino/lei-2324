package pt.pa.refactoring.B_message_chain;

import java.util.Date;

public class LicencePlate {
    private final String id;
    private final int year;
    private final int month;

    public LicencePlate(String id, int year, int month) {
        this.id = id;
        this.year = year;
        this.month = month;
    }

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int age() {
        Date today = new Date();
        return year - today.getYear();
    }
}
