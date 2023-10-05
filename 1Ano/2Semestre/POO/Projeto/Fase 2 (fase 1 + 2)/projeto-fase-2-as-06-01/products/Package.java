package products;

/**
 * EN
 * The Package class is an abstract class referring to a generic denomination of a product packaging.
 * This and all derived classes implement the Transportable interface.
 * A package has an uniqueId, concretely implemented in each derived class to be an abbreviation of such package, plus its serial number.
 * The Packages should be able to pack a products or multiple.
 *
 * PT
 * A classe Package é uma classe abstrata referente a uma denominação genérica da embalagem de um produto.
 * Esta e todas as classes derivadas implementam a interface Transportable.
 * Um pacote tem um uniqueId, implementado concretamente em cada classe derivada para ser uma abreviação de tal pacote, mais o seu número de série.
 * As embalagens devem poder acondicionar um ou vários produtos.
 *
 * @author guilh
 */
public abstract class Package implements Transportable{

    protected String uniqueId;

    protected double totalWeight;

    protected boolean isReady;

    public Package(){
        this.isReady = false;
    }

    /**
     * Method for packaging products on a Package.
     *
     * @param product
     * @return true if operation successful, false otherwise
     */
    public abstract boolean packProducts(Product product);

    /**
     * Return uniqueId.
     *
     * @return uniqueId.
     */
    public String getUniqueId(){
        return uniqueId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean isReadyForShipping() {
        return isReady;
    }

    public void setIsReadyForShipping(){
        this.isReady = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof CardboardBox)) {
            return false;
        }
        Package other = (Package) obj;
        return uniqueId.equals(other.uniqueId);
    }

    @Override
    public int hashCode() {
        return 13 * uniqueId.hashCode();
    }
}
