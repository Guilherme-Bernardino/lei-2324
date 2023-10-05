package products;

/**
 * EN
 * The Sack is a Package derived class, used for storing a single product in.
 * This package can pack small toys, small electronics, acessories and clothes.
 *
 * PT
 * O Sack é uma classe derivada do Package, usada para armazenar um único produto.
 * Este pacote pode embalar pequenos brinquedos, pequenos eletrônicos, acessórios e roupas.
 *
 * @author guilh
 */
public class Sack extends Package{

    protected Product product;
    private static int number = 0;

    public Sack() {
        this.uniqueId = "S-" + ++number;
    }

    @Override
    public boolean packProducts(Product prod) {
        if(prod == null){
            return false;
        }
        if(product != null){
            return false;
        }
        if(!(prod.getType() == ProductType.ACESSORIO | prod.getType() == ProductType.BRINQUEDO_PEQUENO
           | prod.getType() == ProductType.ELETRONICO_PEQUENO | prod.getType() == ProductType.ROUPA)){
            return false;
        }
        this.product = prod;
        this.totalWeight = prod.getWeight();
        return true;
    }

    /**
     * Returns the product.
     *
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    @Override
    public double getTotalWeight(){
        return product.getWeight();
    }

    @Override
    public String toString() {
        return "Sack{" +
                "product=" + product +
                ", uniqueId='" + uniqueId +
                ", totalWeight=" + totalWeight +
                ", isReady=" + isReady +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Sack)) {
            return false;
        }
        Sack other = (Sack) obj;
        return uniqueId.equals(other.uniqueId);
    }

    @Override
    public int hashCode() {
        return 13 * uniqueId.hashCode();
    }
}
