import java.util.ArrayList;
import java.util.HashSet;

public class ShippingCompany extends HashSet<Transport> {

    private String name;
    private ArrayList<Transport> inService;

    public ShippingCompany(String name){
        this.name = name;
        this.inService = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Transport> getInService() {
        return inService;
    }

    public void setInService(ArrayList<Transport> inService) {
        this.inService = inService;
    }

    public void makeTransportation(String id, String origin, String destination, double price){

        Transport t = getTransport(id);

        t.setOrigin(origin);
        t.setDestination(destination);
        t.setPrice(price);
        t.setAvailable(false);

        if(t != null && t.isAvailable()){
            inService.add(t);
            this.remove(t);
        }
    }

    public void finalizeTransportation(String id){
        Transport t = getTransport(id);

        if(t != null){
            inService.remove(t);

            t.setOrigin("");
            t.setDestination("");
            t.setPrice(0.0);
            t.setAvailable(true);

            this.add(t);
        }
    }

    private Transport getTransport(String id){
        for (Transport t: this) {
            if(t.getId().equalsIgnoreCase(id)){
                return t;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Nome %s\n", this.name));
        sb.append(super.toString()+ "\n");
        /*for (Transport acc : inService) {
            sb.append("--" +  acc.getId() + "--");
            sb.append(acc.toString());
        }*/

        return sb.toString();
    }
}
