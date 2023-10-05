package products;

/**
 * EN
 * The Product class identifies a product type, used in storage and transportation.
 * It uses indentifiers such as a name, a weight, a type and an unique ID.
 * This class implements the Transportable interface, meaning it can be transported in vehicles.
 *
 * PT
 * A classe Produto identifica um tipo de produto, utilizado no armazenamento e transporte.
 * Ele usa identificadores como um nome, um peso, um tipo e um ID exclusivo.
 * Essa classe implementa a interface Transportable, ou seja, pode ser transportada em veículos.
 *
 * @author guilh
 */
public class Product implements Transportable{

    private String name;
    private double weight;
    private static int number = 0;
    private ProductType type;
    private final int id;

    public Product(String name, double weight, ProductType type) {
        this.id = ++number;
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    /**
     * Returns this object's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns this object's weight.
     *
     * @return weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets a new weight.
     *
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Returns this object's type.
     *
     * @return type
     */
    public ProductType getType() {
        return type;
    }

    /**
     * Sets a new type.
     *
     * @param type
     */
    public void setType(ProductType type) {
        this.type = type;
    }

    /**
     * Returns this object's unique ID.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + getId()+ "\n");
        sb.append("Name: " + getName()+ "\n");
        sb.append("Type: " + getType()+ "\n");
        sb.append("Weight: "+ getWeight());
        return sb.toString();
    }

    @Override
    public double getTotalWeight() {
        return weight;
    }
}
