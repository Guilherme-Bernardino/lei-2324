package products;
import java.util.ArrayList;
import java.util.List;

/**
 * EN
 * The CardboardBox is a Package derived class, used for storing a multiple product in or a single big product.
 * This package can pack multiple small products, up to ten, or uniquely a big product.
 *
 * PT
 * A CardboardBox é uma classe derivada de Package, usada para armazenar vários produtos ou um único produto grande.
 * Este pacote pode embalar vários produtos pequenos, até dez, ou apenas um produto grande.
 *
 * @author guilh
 */
public class CardboardBox extends Package{

    List<Product> products;
    private boolean isClosed;

    private static int number = 0;

    public CardboardBox() {
        this.uniqueId = "CBB-" + ++number;
        this.isClosed = false;
        this.products = new ArrayList<>(10);
    }

    @Override
    public boolean packProducts(Product prod) {
        if(prod == null){
            return false;
        }

        if(isClosed){
            return false;
        }

        if(products.size() == 10){
            return false;
        }

        if(checkBigProduct(prod)){
            this.isClosed = true;
        }

        if(products.size() > 1  && checkBigProduct(prod)){
            return false;
        }

        totalWeight += prod.getWeight();
        return products.add(prod);
    }

    /**
     * Auxiliary method used for checking if parameter products is of the types
     * big toy or big electronic device.
     *
     * @param prod
     * @return true if it is, false otherwise
     */
    private boolean checkBigProduct(Product prod){
        return prod.getType() == ProductType.BRINQUEDO_GRANDE | prod.getType() == ProductType.ELETRONICO_GRANDE;
    }

    /**
     * Returns the list of products.
     *
     * @return list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public double getTotalWeight(){
        return totalWeight;
    }

    @Override
    public String toString() {
        return "CardboardBox{" +
                "products=" + products +
                ", isClosed=" + isClosed +
                ", uniqueId='" + uniqueId  +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
