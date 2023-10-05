package products;

/**
 * EN
 * Transportable interface used in transportable objects such as products and packages.
 * The classes that implement this interface can be accessed by total weight.
 *
 * PT
 * Interface transportável usada em objetos transportáveis, como produtos e pacotes.
 * As classes que implementam esta interface podem ser acessadas por peso total.
 *
 * @author guilh
 */
public interface Transportable {

    /**
     * Returns the total weight measured for the object that implements.
     *
     * @return total weight
     */
    double getTotalWeight();


    /**
     * Checks if transportable object is ready for shipping.
     *
     * @return isReady
     */
    boolean isReadyForShipping();
}
