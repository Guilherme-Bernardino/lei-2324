
/**
 *
 * @author POO
 * @version April '23
 */
public class Station
{
    private double frequency;
    
    private String name;
    
    public Station(double frequency, String name) {
        this.frequency = frequency;
        this.name = name;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return this.frequency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
 