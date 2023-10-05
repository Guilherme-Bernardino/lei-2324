import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {

    private final ArrayList<CoffeeBox> coffeeBoxes;

    public ShoppingCart() {
        this.coffeeBoxes = new ArrayList<>();
    }

    public ShoppingCart(ArrayList<CoffeeBox> coffeeBoxes) {
        this.coffeeBoxes = coffeeBoxes;
    }

    public void addCoffeToCard(int numberOfBoxes, CoffeeType coffeType) {
        if(numberOfBoxes < 1) throw new CoffeeShopIllegalArgumentException(ErrorCode.NUMBER_OF_BOXES_MUST_BE_POSITIVE);

        for (int i = 0; i < numberOfBoxes; i++) {
            coffeeBoxes.add(new CoffeeBox(coffeType));
        }
    }

    public void cleanCart() {
        coffeeBoxes.clear();
    }

    public float getTotalPrice(float discount) {
        float total = 0;
        for (CoffeeBox coffeeBoxe : coffeeBoxes) {
            total += coffeeBoxe.getBoxPrice();
        }
        return ((total * (100 - discount)) / 100);
    }

    public int getCoffeeQuantity() {
        return coffeeBoxes.stream().map(coffeeBoxe -> CoffeeBox.getBoxQuantity()).reduce(0, Integer::sum);
    }

    public ArrayList<CoffeeBox> getCartContent() {
        return coffeeBoxes;
    }

}
