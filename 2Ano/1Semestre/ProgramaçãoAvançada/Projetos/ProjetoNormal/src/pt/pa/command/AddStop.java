package pt.pa.command;


import pt.pa.model.Stop;
import pt.pa.model.busModelInterface;

public class AddStop implements Command {
    private busModelInterface model;
    private Stop stop;

    public AddStop(busModelInterface map, Stop stop) {
        this.model = map;
        this.stop = stop;
    }

    @Override
    public void execute() {
        System.out.println("EXECUTE ADDING STOP");
        model.addStop(stop);
    }

}
