package tests;

import coords.Dimension;
import coords.Position;
import locals.PickUpLocal;
import locals.StorageLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import products.Package;
import products.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLocals {

    private StorageLocal storageLocal;
    private PickUpLocal pickUpLocal;

    @BeforeEach
    public void setUp(){
        Position storagePosition = new Position(0, 0);
        Dimension storageDimension = new Dimension(4, 2);
        storageLocal = new StorageLocal(storagePosition, storageDimension);

        Position pickUpPosition = new Position(0, 0);
        Dimension pickUpDimension = new Dimension(4, 3);
        pickUpLocal = new PickUpLocal(pickUpPosition, pickUpDimension);
    }

    @Test
    public void testAdd_SinglePackage_ReturnsTrue(){
        Package pack = new Box();
        assertTrue(storageLocal.add(pack));
        assertEquals(1, storageLocal.getPackageList().size());
        assertTrue(storageLocal.getPackageList().contains(pack));
    }

    @Test
    public void testAddPackages_MultiplePackages_ReturnsTrue(){
        Package pack1 = new Box();
        Package pack2 = new Sack();
        Package pack3 = new CardboardBox();
        List<Transportable> packages = new ArrayList<>();
        packages.add(pack1);
        packages.add(pack2);
        packages.add(pack3);

        assertTrue(storageLocal.addPackages(packages));
        assertEquals(3, storageLocal.getPackageList().size());
        System.out.println(storageLocal.getPackageList());
    }

    @Test
    public void testAddPackages_EmptyList_ReturnsFalse(){
        List<Transportable> packages = new ArrayList<>();

        assertFalse(storageLocal.addPackages(packages));
        assertEquals(0, storageLocal.getPackageList().size());
    }

    @Test
    public void testGetPosition_ValidPosition_ReturnsPosition(){
        Position position = new Position(2, 3);
        storageLocal.setPosition(position);

        assertEquals(position, storageLocal.getPosition());
    }

    @Test
    public void testSetPosition_NewPosition_SetsPosition(){
        Position position = new Position(2, 3);
        storageLocal.setPosition(position);

        assertEquals(position, storageLocal.getPosition());
    }

    @Test
    public void testGetDimension_ValidDimension_ReturnsDimension(){
        Dimension dimension = new Dimension(4, 2);
        storageLocal.setDimension(dimension);

        assertEquals(dimension, storageLocal.getDimension());
    }

    @Test
    public void testSetDimension_NewDimension_SetsDimension(){
        Dimension dimension = new Dimension(4, 2);
        storageLocal.setDimension(dimension);

        assertEquals(dimension, storageLocal.getDimension());
    }
}