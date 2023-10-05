package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import products.Box;
import products.CardboardBox;
import products.Product;
import products.ProductType;

import java.util.List;

import static org.junit.Assert.*;

public class TestPackages {

    private Box box;
    private Product validProduct;
    private Product invalidProduct;

    Product smallProduct1,smallProduct2;

    private CardboardBox cardboardBox;

    @BeforeEach
    public void setUp() {
        box = new Box();
        cardboardBox = new CardboardBox();
        validProduct = new Product("Valid Product", 5.0, ProductType.BRINQUEDO_PEQUENO);
        invalidProduct = new Product("Invalid Product", 8.0, ProductType.ELETRONICO_GRANDE);

        smallProduct1 = new Product("Small Product 1", 1.0, ProductType.BRINQUEDO_PEQUENO);
        smallProduct2 = new Product("Small Product 2", 2.0, ProductType.LIVRO);
    }

    @Test
    public void testPackProducts_ValidProduct_Success() {
        boolean result = box.packProducts(validProduct);
        Assertions.assertTrue(result);
        Product packedProduct = box.getProduct();
        Assertions.assertEquals(validProduct, packedProduct);
    }

    @Test
    public void testPackProducts_NullProduct_Failure() {
        boolean result = box.packProducts(null);
        Assertions.assertFalse(result);
        Product packedProduct = box.getProduct();
        Assertions.assertNull(packedProduct);
    }

    @Test
    public void testPackProducts_AlreadyPackedProduct_Failure() {
        box.packProducts(validProduct);
        boolean result = box.packProducts(validProduct);
        Assertions.assertFalse(result);
        Product packedProduct = box.getProduct();
        Assertions.assertEquals(validProduct, packedProduct);
    }

    @Test
    public void testPackProducts_InvalidProductType_Failure() {
        boolean result = box.packProducts(invalidProduct);
        Assertions.assertFalse(result);
        Product packedProduct = box.getProduct();
        Assertions.assertNull(packedProduct);
    }

    @Test
    public void testGetProduct_PackedProduct_ReturnsProduct() {
        box.packProducts(validProduct);
        Product packedProduct = box.getProduct();
        Assertions.assertEquals(validProduct, packedProduct);
    }

    @Test
    public void testGetProduct_EmptyBox_ReturnsNull() {
        Product packedProduct = box.getProduct();
        Assertions.assertNull(packedProduct);
    }

    @Test
    public void testGetTotalWeight_PackedProduct_ReturnsWeight() {
        box.packProducts(validProduct);
        double totalWeight = box.getTotalWeight();
        Assertions.assertEquals(validProduct.getWeight(), totalWeight);
    }

    @Test
    public void testPackProducts_ValidSmallProducts_Success() {


        boolean result1 = cardboardBox.packProducts(smallProduct1);
        boolean result2 = cardboardBox.packProducts(smallProduct2);

        assertTrue(result1);
        assertTrue(result2);

        List<Product> packedProducts = cardboardBox.getProducts();
        assertEquals(2, packedProducts.size());
        assertTrue(packedProducts.contains(smallProduct1));
        assertTrue(packedProducts.contains(smallProduct2));
    }

    @Test
    public void testPackProducts_ValidBigProduct_Success() {
        Product bigProduct = new Product("Big Product", 10.0, ProductType.BRINQUEDO_GRANDE);

        boolean result = cardboardBox.packProducts(bigProduct);

        assertTrue(result);

        List<Product> packedProducts = cardboardBox.getProducts();
        assertEquals(1, packedProducts.size());
        assertEquals(bigProduct, packedProducts.get(0));
    }

    @Test
    public void testPackProducts_MaximumCapacity_Failure() {
        for (int i = 1; i <= 10; i++) {
            Product smallProduct = new Product("Small Product " + i, 1.0, ProductType.BRINQUEDO_PEQUENO);
            boolean result = cardboardBox.packProducts(smallProduct);
            assertTrue(result);
        }

        Product additionalProduct = new Product("Additional Product", 1.0, ProductType.LIVRO);
        boolean result = cardboardBox.packProducts(additionalProduct);

        assertFalse(result);

        List<Product> packedProducts = cardboardBox.getProducts();
        assertEquals(10, packedProducts.size());
        assertFalse(packedProducts.contains(additionalProduct));
    }

}