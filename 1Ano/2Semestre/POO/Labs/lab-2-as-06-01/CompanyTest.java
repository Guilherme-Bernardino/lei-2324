

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CompanyTest {
    Company company1;
    User client1,client2,seller1,seller2;

    Property property1, property2;

    @BeforeEach
    public void setUp(){
        company1 = new Company();
        client1 = new User("José Manuel", "911111111", "zemanel@yahoo.com");
        client2 = new User("António Francisco", "922222222", "tochico@hotmail.com");
        seller1 = new User("Fernando Fernandes", "966777101", "fefe@remax.pt");
        seller2 = new User("Rodrigo Rodrigues", "966777152", "roro@remax.pt");
        property1 = new Property("T3 Monte Belo", 150000.0);
        property2 = new Property("T2 Monte Belo", 100000.0);

    }

    @Test
    public void testConstructor(){
        assertNotNull(company1.getSells());
        assertNotNull(company1.getClients());
        assertNotNull(company1.getSellers());
        assertNotNull(company1.getProperties());
    }

    /**
     * Tests for registering clients.
     */
    @Test
    public void testRegisterClient(){
        company1.registerClient(client1);
        assertTrue("Client exists!", company1.getClients().contains(client1));
    }

    @Test
    public void testRegisterClients(){
        company1.registerClient(client1);
        company1.registerClient(client2);

        assertTrue("Client 1 exists!",company1.getClients().contains(client1));
        assertTrue("Client 2 exists!",company1.getClients().contains(client2));
    }

    @Test
    public void testRegisterClientDuplicate(){
        company1.registerClient(client1);
        assertFalse(company1.registerClient(client1));
    }

    @Test
    public void testRegisterClientNull(){
        assertFalse(company1.registerClient(null));
    }

    /**
     * Tests for registering sellers.
     */
    @Test
    public void testRegisterSeller(){
        assertTrue(company1.registerSeller(seller1));
    }

    @Test
    public void testRegisterSellers(){
        assertTrue(company1.registerSeller(seller1));
        assertTrue(company1.registerSeller(seller2));
    }

    @Test
    public void testRegisterSellerDuplicate(){
        company1.registerSeller(seller1);
        assertFalse(company1.registerSeller(seller1));
    }

    @Test
    public void testRegisterSellerNull(){
        assertFalse(company1.registerSeller(null));
    }

    /**
     * Tests for resgistering properties.
     */

    @Test
    public void testRegisterProperty(){
        assertTrue(company1.registerProperty(property1));
    }

    @Test
    public void testRegisterProperties(){
        assertTrue(company1.registerProperty(property1));
        assertTrue(company1.registerProperty(property2));
    }

    @Test
    public void testRegisterPropertyDuplicate(){
        company1.registerProperty(property1);
        assertFalse(company1.registerProperty(property1));
    }

    @Test
    public void testRegisterPropertyNull(){
        assertFalse(company1.registerProperty(null));
    }
}
