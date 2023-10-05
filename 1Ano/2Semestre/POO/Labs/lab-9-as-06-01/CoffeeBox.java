import java.io.Serializable;

public class CoffeeBox implements Serializable {

    private static final int COFFEE_QUANTITY = 10;
    private final CoffeeType type;

    public CoffeeBox(CoffeeType type) {
        this.type = type;
    }

    public float getBoxPrice() {
        return COFFEE_QUANTITY * type.getPrice();
    }

    public static int getBoxQuantity() {
        return COFFEE_QUANTITY;
    }
    
    @Override
    public String toString() {
        return "Caixa de café " + type.toString() + " com um custo de " + getBoxPrice() + "€";
    }

}
