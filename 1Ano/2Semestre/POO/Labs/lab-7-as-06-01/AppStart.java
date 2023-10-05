import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;

public class AppStart {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Tour tour = new GuidedTour("SAD", "ADS","AD", localDateTime);

        //System.out.println(tour);

        Tour tour1 = new AudioTour("asdad", "saddasd");

        //System.out.println(tour1);

        Listings listings = new Listings();

        Tour tour2 = new GuidedTour("DSA", "SADDA", "ASD", localDateTime);

        Tour tour3 = new AudioTour("dsad", "ada");

        listings.add(tour);
        listings.add(tour1);
        listings.add(tour2);
        listings.add(tour3);


        //listings.show();
        //System.out.println("Number of Listings: " + listings.getNumberOfListings());

        JoseAfonsoSquareConcert concert1 = new JoseAfonsoSquareConcert("Raul", localDateTime);

        JoseAfonsoSquareConcert concert2 = new JoseAfonsoSquareConcert("Toy", localDateTime);


        listings.add(concert1);
        listings.add(concert2);

        List<Listable> nuList1 = listings.search("101");
        List<Listable> nuList2 = listings.searchWhere("Setúbal");
        List<Listable> nuList3 = listings.searchFree();

        System.out.println("First Search" + nuList1);
        System.out.println("Second Search" + nuList2);
        System.out.println("Third Search" + nuList3);

        System.out.println(concert1);

        listings.show();
        System.out.println("Number of Listings: " + listings.getNumberOfListings());
    }
}
