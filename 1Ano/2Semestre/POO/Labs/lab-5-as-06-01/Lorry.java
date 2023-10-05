public class Lorry extends GroundTransportation{

    private int numberOfPallets;
    private int trailers;
    public Lorry(String licensePlate, int numberOfPallets, int trailers) {
        super(licensePlate);
        this.numberOfPallets = numberOfPallets;
        this.trailers = trailers;
    }

    public int getNumberOfPallets() {
        return numberOfPallets;
    }

    public void setNumberOfPallets(int numberOfPallets) {
        this.numberOfPallets = numberOfPallets;
    }

    public int getTrailers() {
        return trailers;
    }

    public void setTrailers(int trailers) {
        this.trailers = trailers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append(String.format("N of Pallets: %s\n", this.numberOfPallets));
        sb.append(String.format("N of Trailers: %s\n", this.trailers));

        return sb.toString();
    }
}
