public class AirTransportation extends Transport{

    private String name;
    private int numberOfContainers;

    public AirTransportation(String name, int numberOfContainers){
        super();
        this.name = name;
        this.numberOfContainers = numberOfContainers;
        this.setFees(0.04);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    @Override
    public String getTransportType(){
        return "Air";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Name: %s\n", this.name));
        sb.append(String.format("Number Of Containers: %s\n", this.numberOfContainers + ""));
        sb.replace(0, super.toString().length() -1, super.toString());

        return sb.toString();
    }
}
