import java.util.ArrayList;

public class Vehicles {
    private ArrayList<Products> products;
    private Locations delivery;
    private Locations pickUp;

    public Vehicles(ArrayList<Products> products, Locations delivery, Locations pickUp) {
        this.products = new ArrayList<>();
        this.delivery = delivery;
        this.pickUp = pickUp;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public Locations getDelivery() {
        return delivery;
    }

    public void setDelivery(Locations delivery) {
        this.delivery = delivery;
    }

    public Locations getPickUp() {
        return pickUp;
    }

    public void setPickUp(Locations pickUp) {
        this.pickUp = pickUp;
    }
}
