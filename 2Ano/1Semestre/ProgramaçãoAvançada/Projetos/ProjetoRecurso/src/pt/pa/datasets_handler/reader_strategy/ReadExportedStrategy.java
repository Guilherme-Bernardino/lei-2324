package pt.pa.datasets_handler.reader_strategy;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.scene.image.Image;
import pt.pa.datasets_handler.DataList;
import pt.pa.datasets_handler.Path;
import pt.pa.model.BusModelException;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ReadExportedStrategy (Strategy pattern), implements the ReaderStrategy, is responsible for reading the dataset and images
 * of the Exported file.
 *
 * @author Guilherme B. 202001870
 */
public class ReadExportedStrategy implements ReaderStrategy{
    private static final Path THIS_PATH = Path.EXPORTED;
    private Gson gson;
    private DataList dataList;
    private List<Image> images;
    private List<String> thisPath;

    public ReadExportedStrategy(){
        this.gson = new Gson();
        this.dataList = new DataList();
        this.images = new ArrayList<>();
        this.thisPath = new ArrayList<>();
    }

    @Override
    public void readFileData(Graph<Stop, Route> graph) {
        File file = new File(THIS_PATH.getPath() + "data.json");

        if(!file.exists()){
            throw new BusModelException("Dataset doesn't exist!");
        }

        try {
            System.out.println("Reading JSON from a file");
            System.out.println("----------------------------");

            //Create new reader, read from file
            JsonReader reader = new JsonReader(new FileReader(file));
            dataList = gson.fromJson(reader, DataList.class);


            //Iterate through data list stops, create new stop and add vertex to graph.
            for (DataList.StopData stopData: dataList.getStops()) {
                Stop newStop = new Stop(stopData.getStopCode(),stopData.getStopName(),
                        Float.parseFloat(stopData.getLongitude()),Float.parseFloat(stopData.getLatitude()),
                        Integer.parseInt(stopData.getX()),Integer.parseInt(stopData.getY()));

                graph.insertVertex(newStop);
                System.out.println(newStop);
            }

            List<Vertex<Stop>> vertexList = new LinkedList<>(graph.vertices());

            //Iterate through data list routes, create new route and add edge to graph.
            for (DataList.RouteData routeData: dataList.getRoutes()) {
                Route newRoute = new Route(routeData.getStopCodeStart(), routeData.getStopCodeEnd(),
                        Integer.parseInt(routeData.getDistance()), Integer.parseInt(routeData.getDuration()));

                Vertex<Stop> orig = getStop(vertexList, routeData.getStopCodeStart());
                Vertex<Stop> dest = getStop(vertexList, routeData.getStopCodeEnd());

                graph.insertEdge(orig, dest, newRoute);
                System.out.println(newRoute);
            }
        }
        catch(Exception e){
            new BusModelException("Couldn't read the data file!");
        }
    }

    @Override
    public List<Image> readAllImages() {
        Image img1 = new Image("file:" + THIS_PATH.getPath() + "img/dark.png");
        Image img2 = new Image("file:" + THIS_PATH.getPath() + "img/map.png");
        Image img3 = new Image("file:" + THIS_PATH.getPath() + "img/satellite.png");
        Image img4 = new Image("file:" + THIS_PATH.getPath() + "img/terrain.png");

        images.add(img1);
        images.add(img2);
        images.add(img3);
        images.add(img4);

        thisPath.add(THIS_PATH.getPath() + "img/dark.png");
        thisPath.add(THIS_PATH.getPath() + "img/map.png");
        thisPath.add(THIS_PATH.getPath() + "img/satellite.png");
        thisPath.add(THIS_PATH.getPath() + "img/terrain.png");

        return images;
    }

    @Override
    public List<String> getPaths(){
        return thisPath;
    }

    /**
     * Method for returning a vertex that equals to stop code.
     * Returns null if none existent.
     *
     * @param stops
     * @param code
     * @return selected Vertex
     */
    private Vertex<Stop> getStop(List<Vertex<Stop>> stops, String code){
        for (Vertex<Stop> v : stops) {
            if(code.equals(v.element().getStopCode())){
                return v;
            }
        }
        return null;
    }
}
