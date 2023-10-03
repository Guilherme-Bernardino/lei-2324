package pt.pa.command;

import pt.pa.graph.Vertex;
import pt.pa.model.Stop;
import pt.pa.model.busModelInterface;

public class RemoveStop implements Command{

    private busModelInterface network;
    private String name;
    private Vertex<Stop> vStop;

    public RemoveStop(busModelInterface network, String name) {
        this.network = network;
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("EXECUTE REMOVE");
        vStop = network.removeStop(name);

    }
}
