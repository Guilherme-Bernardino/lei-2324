package pt.pa.command;

import pt.pa.model.InvalidRouteOperation;
import pt.pa.model.InvalidStopOperation;
import pt.pa.model.Route;
import pt.pa.model.busModelInterface;

public class AddRoute implements Command {
    private busModelInterface network;
    private String sourceStop;
    private String destinationStop;
    private Route route;

    private String stopCodeEnd;
    public AddRoute(busModelInterface network, String sourceStop, String destinationStop, Route route) {
        this.network = network;
        this.sourceStop = sourceStop;
        this.destinationStop = destinationStop;
        this.route = route;
    }

    @Override
    public void execute() throws InvalidStopOperation, InvalidRouteOperation {
        System.out.println("EXECUTE ADD ROUTE");
        network.addRoute(sourceStop, destinationStop, route);
    }

}