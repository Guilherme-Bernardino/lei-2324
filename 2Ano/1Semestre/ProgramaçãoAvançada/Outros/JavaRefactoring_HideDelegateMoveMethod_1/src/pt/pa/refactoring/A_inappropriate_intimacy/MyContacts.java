package pt.pa.refactoring.A_inappropriate_intimacy;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyContacts implements Iterable<Person> {
    private Set<Person> list;
    private Date creationDate;

    public MyContacts() {
        list = new HashSet<>();
        creationDate = new Date();
    }

    //hide delegate
    public void add(Person p) {
        this.list.add(p);
    }

    /**
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate.toString();
    }

    public int getTotal() {
        return list.size();
    }

    public String show() {
        StringBuilder sb = new StringBuilder();

        sb.append( String.format("%40s | %20s | %40s", "Name", "Phone", "Email"));
        for(Person p : list) {
            /* Check if person has phone number or email; if not say "None". */
            sb.append(String.format("%40s | %20s | %40s",
                    //TODO: encapsulate fullName and do 'extract method'
                    // for the text representation of the phone number and email
                    p.fullName, (p.phoneNumber != null ? p.phoneNumber : "None"),
                    (p.emailAddress != null ? p.emailAddress : "None")));
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public Iterator<Person> iterator() {
        return list.iterator();
    }
}
