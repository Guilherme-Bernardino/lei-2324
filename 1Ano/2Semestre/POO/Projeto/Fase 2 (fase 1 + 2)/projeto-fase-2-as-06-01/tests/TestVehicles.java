package tests;

import coords.Dimension;
import coords.Position;
import locals.DropOffLocal;
import locals.PickUpLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import products.Package;
import products.*;
import vehicles.AGC;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestVehicles {

    private AGC agc;
    private PickUpLocal pickUpLocal;
    private DropOffLocal dropOffLocal;

    private Position position;

    @BeforeEach
    public void setUp() {
        agc = new AGC();
        pickUpLocal = new PickUpLocal(new Position(10, 10), new Dimension(5, 5));
        dropOffLocal = new DropOffLocal(new Position(20, 20), new Dimension(5, 5));
        agc.setPickUpLocal(pickUpLocal);
        agc.setDropOfLocal(dropOffLocal);

        position = new Position(0,0);

        agc.setPosition(position);
    }

    @Test
    public void testAddPackage_ValidPackage_ReturnsTrue() {
        Package pack = new Box();
        boolean result = agc.addPackage(pack);
        assertTrue(result);
        assertTrue(agc.getPackages().contains(pack));
    }

    @Test
    public void testAddPackage_NullPackage_ReturnsFalse() {
        boolean result = agc.addPackage(null);
        assertFalse(result);
    }

    @Test
    public void testAddPackages_NullList_ReturnsFalse() {
        boolean result = agc.addPackages(null);
        assertFalse(result);
    }

    @Test
    public void testAddPackages_EmptyList_ReturnsFalse() {
        boolean result = agc.addPackages(new ArrayList<>());
        assertFalse(result);
    }

    @Test
    public void testAddPackages_ValidList_ReturnsTrue() {
        Package pack1 = new Box();
        pack1.packProducts(new Product("1", 10.0, ProductType.ACESSORIO));
        Package pack2 = new Box();
        pack2.packProducts(new Product("2", 10.0, ProductType.ACESSORIO));
        List<Transportable> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);

        boolean result = agc.addPackages(packages);
        assertTrue(result);
        assertTrue(agc.getPackages().containsAll(packages));
    }

    @Test
    public void testUnload_PickUpLocalNotSet_ReturnsFalse() {
        agc.setPickUpLocal(null);
        boolean result = agc.unload();
        assertFalse(result);
    }

    @Test
    public void testLoad_DropOffLocalNotSet_ReturnsFalse() {
        agc.setDropOfLocal(null);
        boolean result = agc.load(new ArrayList<>());
        assertFalse(result);
    }

    @Test
    public void testLoad_ValidDropOffLocal_ReturnsTrue() {
        Package pack1 = new Box();
        pack1.packProducts(new Product("1", 10.0, ProductType.ACESSORIO));
        Package pack2 = new Box();
        pack2.packProducts(new Product("2", 10.0, ProductType.ACESSORIO));
        List<Transportable> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);

        boolean result = agc.load(packages);
        assertTrue(result);
        assertTrue(agc.getPackages().containsAll(packages));
    }

    @Test
    public void testRetrieveFromShelf_NullPackage_ReturnsFalse() {
        boolean result = agc.retrieveFromShelf(null);
        assertFalse(result);
    }

    @Test
    public void testRetrieveFromShelf_ValidPackage_ReturnsTrue() {
        Package pack = new Box();
        boolean result = agc.retrieveFromShelf(pack);
        assertTrue(result);
        assertTrue(agc.getPackages().contains(pack));
    }

    @Test
    public void testGetPosition_InitialPosition_ReturnsCorrectPosition() {
        Position position = agc.getPosition();
        assertEquals(0, position.getX());
        assertEquals(0, position.getY());
    }

    @Test
    public void testSetPosition_NewPosition_SetsCorrectPosition() {
        Position newPosition = new Position(5, 5);
        agc.setPosition(newPosition);
        Position position = agc.getPosition();
        assertEquals(newPosition, position);
    }

}
