package pt.pa.controller;

import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import pt.pa.command.*;
import pt.pa.datasets_handler.Path;
import pt.pa.datasets_handler.Reader;
import pt.pa.graph.Vertex;
import pt.pa.model.InvalidRouteOperation;
import pt.pa.model.InvalidStopOperation;
import pt.pa.model.Route;
import pt.pa.model.Stop;
import pt.pa.model.busModelInterface;
import pt.pa.model.memento.Caretaker;
import pt.pa.model.memento.NoMementoException;
import pt.pa.view.busView;

import java.util.List;
import java.util.Map;


public class GraphController{

    private CommandManager commandmanager;

    private busModelInterface network;

    private busModelInterface model;

    private busView view;

    private Caretaker caretaker;


    public GraphController (busModelInterface model, busView view){
        this.model = model;
        this.view = view;
        this.view.setTriggers(this);
        this.model.addObserver(this.view);
        this.commandmanager = new CommandManager();


    }

    public void doAddStop(){
        String name = view.getNameStopAdd();
        String codeStop = view.getCodeStopAdd();
        String pop = view.getPopStopAdd();
        String latitude = view.getLatitudeStopAdd();
        String longitude = view.getLongitudeStopAdd();
        String x = view.getXStopAdd();
        String y = view.getYStopAdd();

        int iX = Integer.parseInt(x);
        int iY = Integer.parseInt(y);
        float fLat = Float.parseFloat(latitude);
        float fLong = Float.parseFloat(longitude);

        AddStop a = new AddStop(model, new Stop(codeStop,name,fLong,fLat,iX,iY));

        commandmanager.executeCommand(a);

        view.clearControls();
        view.clearError();


    }

    public void doRemoveStop() {
        try {
            String name = view.getNameStopAdd();
            if (name == null) {
                view.displayError("You must provide a name.");
                return;
            }
            RemoveStop c1 = new RemoveStop(model, name);
            commandmanager.executeCommand(c1);
            view.clearControls();
            view.clearError();
        } catch (InvalidStopOperation eStop) {
            view.displayError(eStop.getMessage());
        }
    }

    public void doAddRoute() {
        try {
            String sourceStop = view.getRouteSourceStop();
            String destStop = view.getRouteDestStop();
            if (sourceStop == null || destStop == null) {
                view.displayError("You must both the source and the destination.");
                return;
            }
            String distance = view.getDistanceAdd();
            if (distance == null || distance.isEmpty()) {
                view.displayError("You must enter a distance.");
                return;
            }
            AddRoute c = new AddRoute(model, sourceStop, destStop, new Route("das","",2,2));
            commandmanager.executeCommand(c);
            view.clearControls();
            view.clearError();
        } catch (InvalidRouteOperation | InvalidStopOperation e) {
            view.displayError(e.getMessage());
        }
    }

    public void doRemoveRoute() {
        try {
            String sourceStop = view.getRouteSourceStop();
            String destStop = view.getRouteDestStop();
            if (sourceStop == null || destStop == null) {
                view.displayError("You must both the source and the destination.");
                return;
            }
            RemoveRoute c = new RemoveRoute(model, sourceStop, destStop);
            commandmanager.executeCommand(c);
            view.clearControls();
            view.clearError();
        } catch (InvalidRouteOperation | InvalidStopOperation e) {
            view.displayError(e.getMessage());
        }
    }

    public XYChart.Series getTop7Data() {
        XYChart.Series dataStops = new XYChart.Series();
        dataStops.setName("Hubs");
        Map<String, Integer> sorted = model.mostPopularStops();
        System.out.println(sorted);
        int counter = 0;
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            if (counter >= 5) return dataStops;
            System.out.println(entry.getKey() + " " + entry.getValue());
            dataStops.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            counter++;
        }
        return dataStops;
    }

    public ListView<String> getCentralStops() {
        ListView<String> listCentralStops = new ListView();
        Map<String, Integer> sortedStops = model.mostPopularStops();
        System.out.println("S" + sortedStops);

        for (Map.Entry<String, Integer> entry : sortedStops.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            listCentralStops.getItems().add(String.format("%s %d", entry.getKey(), entry.getValue()));
        }

        return listCentralStops;
    }




    public void undo() throws NoMementoException {
        caretaker.restoreState();
    }


    public busModelInterface getNetworkFromController() {
        return network;
    }


    public void setNetworkFromController(busModelInterface network)
    {
        this.network = network;
    }



    public void mapChange() {
        Reader reader = new Reader();
        List<Image> images = reader.readAllImages(Path.DEMO);

        BackgroundImage bImg1 = new BackgroundImage(images.get(0),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg1);

    }

    public Caretaker getCaretaker(){
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }




}
