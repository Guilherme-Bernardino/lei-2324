package pt.pa.command;

import pt.pa.model.InvalidRouteOperation;
import pt.pa.model.InvalidStopOperation;
import pt.pa.model.Route;
import pt.pa.model.busModelInterface;

public class RemoveRoute implements Command{
    private busModelInterface network;
    private String sourceStop;
    private String destinationStop;
    private Route route;

    public RemoveRoute(busModelInterface network, String sourceStop, String destinationStop) {
        this.network = network;
        this.sourceStop = sourceStop;
        this.destinationStop = destinationStop;
    }
    @Override
    public void execute() throws InvalidStopOperation, InvalidRouteOperation {
        System.out.println("EXECUTE REMOVE ROUTE");
        route = network.removeRoute(sourceStop, destinationStop).element();
    }
}
