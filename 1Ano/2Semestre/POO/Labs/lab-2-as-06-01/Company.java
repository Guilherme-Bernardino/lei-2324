
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Manage a company.
 *
 * @author POO
 * @version março/2023
 */
public class Company {

    // Clients registry
    private ArrayList<User> clients;
    // Sellers registry
    private ArrayList<User> sellers;
    // Properties registry
    private ArrayList<Property> properties;
    // Sells registry
    private ArrayList<Sell> sells;

    /**
     * Constructor of class Company
     */
    public Company() {
        this.clients = new ArrayList<>();
        this.sellers = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.sells = new ArrayList<>();
    }

    /**
     * Get the list of clients.
     *
     * @return This company clients.
     */
    public List<User> getClients() {
        return clients;         // dummy implementation
    }

    /**
     * Get the list of sellers.
     *
     * @return This company sellers.
     */
    public List<User> getSellers() {
        return sellers;         // dummy implementation
    }



    /**
     * Get the list of properties.
     *
     * @return This company's properties.
     */
    public List<Property> getProperties() {
        return properties;         // dummy implementation
    }

    /**
     * Get the list of sells.
     *
     * @return This company sells.
     */
    public List<Sell> getSells() {
        return sells;
    }

    /**
     * Register a client.
     *
     * @param client to register. Must not be null. Must not be registered.
     * @return true If the registration succeeds, false otherwise.
     */
    public boolean registerClient(User client) {
        if(client == null){
            return false;
        }

        for (User c: clients) {
            if(c.equals(client)){
                return false;
            }
        }

        return clients.add(client);
    }

    /**
     * Register a seller.
     *
     * @param seller to register. Must not be null. Must not be registered.
     * @return true If the registration succeeds, false otherwise.
     */
    public boolean registerSeller(User seller) {
        if(seller == null){
            return false;
        }

        for (User c: sellers) {
            if(c.equals(seller)){
                return false;
            }
        }

        return sellers.add(seller);
    }

    /**
     * Register a property.
     *
     * @param property to register. Must not be null. Must not be registered.
     * @return true If the registration succeeds, false otherwise.
     */
    public boolean registerProperty(Property property) {
        if(property == null){
            return false;
        }

        for (Property c: properties) {
            if(c.equals(property)){
                return false;
            }
        }

        return properties.add(property);
    }

    /**
     * Register a sell.
     *
     * @param sell to register. Must not be null. Must not be registered.
     * @return true If the registration succeeds, false otherwise.
     */
    public boolean registerSell(Sell sell) {
        return sells.add(sell);
    }

    /**
     * Generate a new sell and register that sell.
     *
     * @param client Must be already registered.
     * @param seller Must be already registered.
     * @param property Must be already registered.
     * @return true If the request succeeds, false otherwise.
     */
    public boolean createSell(User client, User seller, Property property) {
        if(registerClient(client)){
            return false;
        }
        if(registerSeller(seller)){
            return false;
        }
        if(registerProperty(property)){
            return false;
        }

        Sell sell = new Sell(client, seller, property);
        return registerSell(sell);
    }

    /**
     * Calculates the total number of sells within the provided year.
     *
     * @param year
     * @return The total number of sells in the year.
     */
    public int calculateSellsOfTheYear(int year) {
        int count = 0;
        for (Sell s : sells) {
            if(s.getDate().getYear() == year){
                count++;
            }
        }
        return count;
    }

    /**
     * Find the seller with more sells within the provided year.
     *
     * @param year
     * @return The name of the seller of the year.
     */
    public String findSellerOfTheYear(int year) {
        List<User> sellersList = new ArrayList<>();
        User topSeller = null;
        int topValue = 0;

        for (Sell s : sells) {
            if(s.getDate().getYear() == year){
                sellersList.add(s.getSeller());
            }
        }
        for (User u : sellersList) {
            int value = Collections.frequency(sellersList,u);
            if(value > topValue){
                topValue = value;
                topSeller = u;
            }
        }

        return topSeller.getName();
    }

}
