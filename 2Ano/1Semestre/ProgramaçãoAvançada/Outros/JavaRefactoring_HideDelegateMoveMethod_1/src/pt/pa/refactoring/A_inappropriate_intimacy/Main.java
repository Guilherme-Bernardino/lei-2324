package pt.pa.refactoring.A_inappropriate_intimacy;

public class Main {

    public static void main(String[] args) {
	    MyContacts contacts = new MyContacts();

        // serious inappropriate intimacy detected previously
        // contacts.list.add or even contacts.getList().add
        // protect the information and only allow permitted operations

        contacts.add(new Person("Magee Garrett","260-929-568","est.Nunc@lectus.org"));
        contacts.add(new Person("Nerea Adams","227-520-290","nibh.vulputate@lorem.org"));
        contacts.add(new Person("Sydney Lara","249-448-640","mi.Aliquam@sollicitudin.edu"));
        contacts.add(new Person("Duncan House",null,"nec@feugiatplaceratvelit.edu"));
        contacts.add(new Person("Conan Mcfarland","214-866-672","enim@sempereratin.ca"));
        contacts.add(new Person("Philip Howell","284-576-609","at.pede@justofaucibus.edu"));
        contacts.add(new Person("Kiona Whitaker","297-723-280","at.pede@Nullamfeugiat.edu"));
        contacts.add(new Person("Quemby Green","203-675-275","cursus.et.magna@inconsequatenim.co.uk"));
        contacts.add(new Person("Hyacinth Reese",null,null));
        contacts.add(new Person("Sierra Shannon","212-829-582",null));
        contacts.add(new Person("Bevis Watson","251-167-074","quis@adipiscing.org Lilah Knight,247-359-792,eu@eutelluseu.ca"));
        contacts.add(new Person("Brent Keller","200-454-266","montes.nascetur.ridiculus@purusgravidasagittis.org"));
        contacts.add(new Person("Bruce Patterson","202-892-442",null));

        System.out.println(String.format("Contact list created at %s", contacts.getCreationDate()));

        //moving this functionality to MyContacts class would be an example
        //of the 'Extract Method' technique.
        int missingEmail = 0;
        for(Person p : contacts) { //inappropriate intimacy solved with Iterator in this case
            //TODO: create getters in Person and make attributes private - another simple examples
            // of inappropriate intimacy
            if(p.emailAddress == null) missingEmail++;
        }

        System.out.println(String.format("Contains %d contacts, of which %d don't have email address.", contacts.getTotal(), missingEmail));
    }
}
