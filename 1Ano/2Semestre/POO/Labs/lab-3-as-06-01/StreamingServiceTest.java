import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
public class StreamingServiceTest {

    StreamingService stream;
    Account account1, account2, account3, account4, account5;

    @BeforeEach
    public void startUp(){
        stream = new StreamingService();
        account1 = new Account("José Manuel", true);
        account2 = new Account("Francisco Silva", false);
        account3 = new Account("Maria Clara", false);
        account4 = new Account("Clotilde Matias", true);
        account5 = new Account("Fernanda Costa", false);
        stream.addAccount(account1);
        stream.addAccount(account2);
        stream.addAccount(account3);
        stream.addAccount(account4);
        stream.addAccount(account5);


    }
    @Test
    public void testConstructor(){

        assertNotNull(stream.getActiveAccounts());
    }

    @Test
    public void testAddAccount(){

        assertTrue(stream.getActiveAccounts().contains(account1));
    }

    @Test
    public void testRemoveAccount(){
        stream.removeAccount(account1);
        assertFalse(stream.getActiveAccounts().contains(account1));
    }

    @Test
    public void testRemoveUnpaidAccounts(){
        stream.removeUnpaidAccounts();
        for (Account acc: stream.getActiveAccounts()) {
            System.out.println("acc"+ acc);
        }
        assertEquals(2, stream.getActiveAccounts().size());
    }

    @Test
    public void testSetAllAccountsAsUnpaid(){
        stream.setAllAccountsAsUnpaid();

        List<Account> accList = stream.getActiveAccounts().stream().filter(account -> !account.isPaid()).collect(Collectors.toList());
        for (Account a: accList) {
            System.out.println(a + "");
        }

        assertEquals(accList, stream.getActiveAccounts());
    }

    @Test
    public void testRemoveUnpaidAccountsRemoveIf(){
        stream.removeUnpaidAccountsRemoveIf();

        assertEquals(2, stream.getActiveAccounts().size());
    }

    @Test
    public void testGetListOfUnpaidAccounts(){
        assertEquals(3, stream.getListOfUnpaidAccounts().size());
    }

    @Test
    public void testCountUnpaidAccounts(){
        assertEquals(3, stream.countUnpaidAccounts());
    }

    @Test
    public void testToString(){
        assertNotNull(stream.toString());
        System.out.println(stream.toString());
    }
}
