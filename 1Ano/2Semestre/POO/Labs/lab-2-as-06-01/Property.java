

/**
 * A property to sell.
 *
 * @author POO
 * @version março/2023
 */
public class Property {
    private static int numberOfProperties = 0;
    private final String id = "dinis";
    private String model;
    private double price;

    /**
     * Constructor of class Property
     *
     * @param model The property description.
     * @param price The property price.
     */
    public Property(String model, double price) {
        this.model = model;
        this.price = price;
    }

    /**
     * Id selector.
     */
    public String getId() {
        return id;
    }

    /**
     * Description selector.
     */
    public String getModel() {
        return model;
    }

    /**
     * Description modifier.
     * 
     * @param model The new description. Must not be null.
     */
    public void setDescription(String model) {
        this.model = model;
    }

    /**
     * Price selector.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Price modifier.
     * 
     * @param price The new price. Must not be negative.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {

        String string = "Propriedade: \n" +
                "Modelo       : " + model + "\n" +
                "Preço        : " + price;

        return string;

    }
}
