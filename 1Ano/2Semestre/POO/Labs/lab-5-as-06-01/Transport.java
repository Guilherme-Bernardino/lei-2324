public class Transport {

    private static int AUTO_NUMBER = 0;

    private String id;
    private String origin;
    private String destination;
    private double price;
    private double fees;
    private boolean available;

    public Transport(){
        this.id = "T-00" + ++AUTO_NUMBER;
        this.origin = "";
        this.destination = "";
        this.price = 0;
        this.fees = 0;
        this.available = true;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPriceWithFees(){
        return price + (price * fees);
    }

    public String getTransportType(){
        return "N/A";
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("\n\n"));
        sb.append(String.format("Transporte: \n\n"));
        sb.append(String.format("TransportType: %s\n", getTransportType()));
        sb.append(String.format("Id: %s\n", this.id));
        sb.append(String.format("Origin: %s\n", this.origin.isEmpty() ? "N/A" : this.origin));
        sb.append(String.format("Destination: %s\n", this.destination.isEmpty() ? "N/A": this.destination));
        sb.append(String.format("Price: %s\n", this.price + ""));
        sb.append(String.format("Fees %s\n", this.fees + ""));
        sb.append(String.format("Available?: %s\n", this.available == true ? "Yes" : "No"));
        sb.append(String.format("Price with fees: %s\n", getPriceWithFees()));
        return sb.toString();
    }

}
