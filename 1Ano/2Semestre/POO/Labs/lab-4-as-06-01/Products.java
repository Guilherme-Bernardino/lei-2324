public class Products{
    private String name;
    private double weight;
    private static int number;
    private String type;
    private final int id;

    public Products(String name, double weight, String type) {
        id = ++number;
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Products.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
