public class GroundTransportation extends Transport{

    private String licensePlate;

    public GroundTransportation(String licensePlate){
        super();
        this.licensePlate = licensePlate;
        this.setFees(0.03);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getTransportType(){
        return "Ground";
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("License Plate: %s\n", this.licensePlate));
        sb.replace(0, super.toString().length() -1, super.toString());

        return sb.toString();
    }
}
