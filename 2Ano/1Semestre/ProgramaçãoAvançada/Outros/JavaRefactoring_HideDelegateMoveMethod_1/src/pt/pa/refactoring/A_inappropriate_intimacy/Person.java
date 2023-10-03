package pt.pa.refactoring.A_inappropriate_intimacy;

import java.util.Objects;

public class Person {
    final String fullName;
    final String phoneNumber;
    final String emailAddress;

    public Person(String fullName, String phoneNumber, String emailAddress) {
        if(fullName == null || fullName.indexOf(" ") == -1)
            throw new IllegalArgumentException("Must have full name.");
        if(phoneNumber != null && phoneNumber.length() < 9)
            throw new IllegalArgumentException("Phone number too short.");
        if(emailAddress != null && emailAddress.indexOf("@") == -1)
            throw new IllegalArgumentException("Must have valid email.");

        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return fullName.split(" ", 2)[0];
    }

    public String getEmailDomain() {
        return emailAddress.substring(emailAddress.lastIndexOf("@") + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return fullName.equals(person.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

}
