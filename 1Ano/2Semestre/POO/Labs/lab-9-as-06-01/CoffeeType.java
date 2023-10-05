/**
 * Representa os tipos de café e seu preço.
 *
 * @author POO 2022/2023
 */
public enum CoffeeType {

    ESPRESSO("Expresso", 0.40f),
    DECAF("Descafeinado", 0.45f),
    LONG("Longo", 0.50f),
    STRONG("Forte", 0.65f);

    private final String description;
    private final float price;

    private CoffeeType(String description, float price) {
        this.description = description;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.description;
    }

}
