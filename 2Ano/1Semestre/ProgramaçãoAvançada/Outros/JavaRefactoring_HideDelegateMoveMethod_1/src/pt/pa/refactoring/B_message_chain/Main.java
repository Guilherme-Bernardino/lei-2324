package pt.pa.refactoring.B_message_chain;

public class Main {
    public static void main(String[] args) {

        Workforce workforce = new Workforce();

        Truck iveco = new Truck("Iveco", "PowerStar 420 E5", new LicencePlate("42-31-OS", 1999, 5));
        Truck mercedes = new Truck("Mercedes-Benz", "Actros", new LicencePlate("52-MM-31", 2011, 2));
        Truck man = new Truck("MAN", "TGX", new LicencePlate("AA-15-BB", 2020, 8));

        Driver bevis = new Driver("Bevis Watson", iveco);
        Driver brent = new Driver("Brent Keller", mercedes);
        Driver conan = new Driver("Conan Mcfarland", man);

        workforce.addDriver(bevis);
        workforce.addDriver(brent);
        workforce.addDriver(conan);

        System.out.println("All drivers: ");
        System.out.println( workforce.showAllDrivers() );

        System.out.println("\n");
        System.out.println("Only drivers starting with letter B: ");
        System.out.println( workforce.showDriversNameStartingWithLetter('B') );

        System.out.println("\n");
        System.out.println("Searching driver of licence plate \"52-MM-31\": ");
        System.out.println( workforce.searchTruckDriverOf("52-MM-31").getFullName() );
    }

}
