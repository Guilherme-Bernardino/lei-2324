package pt.pa.model;

public class Bridge {
    private String name;
    private int cost;

    public Bridge(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Bridge(String name) {
        this(name,1);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Bridge{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
