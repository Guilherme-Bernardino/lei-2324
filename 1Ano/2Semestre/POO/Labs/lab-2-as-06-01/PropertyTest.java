import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class PropertyTest {

    Property property1, property2;

    Company company1;

    User client1,client2,seller1,seller2;

    Sell sell1, sell2;

    @BeforeEach
    public void setUp(){
        company1 = new Company();
        client1 = new User("José Manuel", "911111111", "zemanel@yahoo.com");
        client2 = new User("António Francisco", "922222222", "tochico@hotmail.com");
        seller1 = new User("Fernando Fernandes", "966777101", "fefe@remax.pt");
        seller2 = new User("Rodrigo Rodrigues", "966777152", "roro@remax.pt");
        property1 = new Property("T3 Monte Belo", 150000.0);
        property2 = new Property("T2 Monte Belo", 100000.0);

        company1.registerClient(client1);
        company1.registerClient(client2);
        company1.registerProperty(property1);
        company1.registerProperty(property2);
        company1.registerSeller(seller1);
        company1.registerSeller(seller2);
    }

    @Test
    public void testConstructor(){
        assertTrue(property1.getModel() == "T3 Monte Belo");
        assertTrue(property1.getPrice() == 150000.0); ;
    }

    @Test
    public void testToString(){
        System.out.println(property1.toString());
        assertTrue(property1.toString().contains("T3 Monte Belo"));
    }

    //Nivel 5
    @Test
    public void testCreateSell(){
        assertTrue(company1.createSell(client1,seller1,property1));
        assertTrue(company1.createSell(client2,seller2,property2));
    }

    @Test
    public void testCalculateSellsOfTheYear(){
        company1.createSell(client1,seller1,property1);
        company1.createSell(client2,seller2,property2);

        assertEquals(2, company1.calculateSellsOfTheYear(2023));
    }

    @Test
    public void testFindSellerOfTheYear(){
        company1.createSell(client1,seller1,property1);
        company1.createSell(client2,seller2,property2);
        company1.createSell(client1,seller1,property2);

        assertEquals(seller1.getName(), company1.findSellerOfTheYear(2023));
    }
}
