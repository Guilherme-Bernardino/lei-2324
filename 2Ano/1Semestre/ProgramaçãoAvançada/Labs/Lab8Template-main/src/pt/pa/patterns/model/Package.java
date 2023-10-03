package pt.pa.patterns.model;

/**
 *
 * @author amfs
 */
public class Package {

    private String description;

    private double price;

    private int freeMonths;

    public Package(String description, double price, int freeMonths) {
        this.description = description;
        this.price = price;
        this.freeMonths = freeMonths;
    }

    protected Package(String description, int freeMonths) {
        this.description = description;
        this.freeMonths = freeMonths;
        this.price = 0;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getFreeMonths() {
        return freeMonths;
    }

    @Override
    public String toString() {
        return String.format("%s | %.2f€ | %d months",
                getDescription(), getPrice(), getFreeMonths());
    }

    public void applyDiscount(double percentage) {
        this.price *= (1 - percentage/100);
    }
}
