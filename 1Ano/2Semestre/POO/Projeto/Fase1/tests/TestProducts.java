package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductType;
import products.Transportable;

public class TestProducts {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("1", 10.0, ProductType.ELETRONICO_GRANDE);
    }

    @Test
    public void testGetName() {
        String expectedName = "1";
        String actualName = product.getName();
        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        String newName = "2";
        product.setName(newName);
        String updatedName = product.getName();
        Assertions.assertEquals(newName, updatedName);
    }

    @Test
    public void testGetWeight() {
        double expectedWeight = 10.0;
        double actualWeight = product.getWeight();
        Assertions.assertEquals(expectedWeight, actualWeight);
    }

    @Test
    public void testSetWeight() {
        double newWeight = 15.0;
        product.setWeight(newWeight);
        double updatedWeight = product.getWeight();
        Assertions.assertEquals(newWeight, updatedWeight);
    }

    @Test
    public void testGetType() {
        ProductType expectedType = ProductType.ELETRONICO_GRANDE;
        ProductType actualType = product.getType();
        Assertions.assertEquals(expectedType, actualType);
    }

    @Test
    public void testSetType() {
        ProductType newType = ProductType.LIVRO;
        product.setType(newType);
        ProductType updatedType = product.getType();
        Assertions.assertEquals(newType, updatedType);
    }

    @Test
    public void testGetId() {
        int expectedId = 1;
        int actualId = product.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetTotalWeight() {
        double expectedTotalWeight = 10.0;
        double actualTotalWeight = product.getTotalWeight();
        Assertions.assertEquals(expectedTotalWeight, actualTotalWeight);
    }
}
