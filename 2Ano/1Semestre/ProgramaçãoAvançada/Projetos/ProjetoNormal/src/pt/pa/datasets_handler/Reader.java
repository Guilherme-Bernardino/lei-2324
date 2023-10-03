package pt.pa.datasets_handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.Vertex;
import pt.pa.model.Stop;
import pt.pa.model.Route;

public class Reader {

    public void readStops(Graph<Stop, Route> graph, Path path){

        File file = new File(path.getPath() + "stops.txt");

        try{
            String buffer;
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for(int i=0; i < 4; i++){
                reader.readLine();
            }

            while((buffer = reader.readLine()) != null){
                String[] values = buffer.split("\t");
                Stop stop = new Stop(values[0],values[1], Float.parseFloat(values[2]), Float.parseFloat(values[3]));
                graph.insertVertex(stop);
            }

            reader.close();

        }catch(Exception e){
            System.out.println("Couldn't read the stops file!");
        }
    }


    public void readRoutes(Graph<Stop, Route> graph, Path path){

        File fileDistance = new File(path.getPath() + "routes-distance.txt");
        File fileDuration = new File(path.getPath() + "routes-duration.txt");

        List<Vertex<Stop>> stops = new LinkedList<>(graph.vertices());


        try{
            String buffer;
            BufferedReader reader1 = new BufferedReader(new FileReader(fileDistance));

            //Routes Distance
            for(int i=0; i < 4; i++){
                reader1.readLine();
            }

            while((buffer = reader1.readLine()) != null){
                String[] values = buffer.split("\t");
                Route routeDistance = new Route(values[0],values[1],Integer.parseInt(values[2]), 0);

                Vertex<Stop> orig = getStop(stops, values[0]);
                Vertex<Stop> dest = getStop(stops, values[1]);
                graph.insertEdge(orig, dest, routeDistance);
            }

            reader1.close();

            BufferedReader reader2 = new BufferedReader(new FileReader(fileDuration));

            List<Edge<Route,Stop>> routes = new LinkedList<>(graph.edges());

            //Routes Duration
            for(int i=0; i < 4; i++){
                reader2.readLine();
            }

            while((buffer = reader2.readLine()) != null){
                String[] values = buffer.split("\t");

                Edge<Route,Stop> edge = getRoute(routes, values[0], values[1]);
                //Vertex<Stop> dest = getRoute(routes values[0]);

                edge.element().setDuration(Integer.parseInt(values[2]));
            }

            reader2.close();

        }catch(Exception e){
            System.out.println("Couldn't read the file!");
        }
    }

    public void readXY(Graph<Stop, Route> graph, Path path){

        File fileXY = new File(path.getPath() + "xy.txt");
        List<Vertex<Stop>> stops = new LinkedList<>(graph.vertices());

        try{
            String buffer;
            BufferedReader reader = new BufferedReader(new FileReader(fileXY));

            //Routes Duration
            for(int i=0; i < 4; i++){
                reader.readLine();
            }

            while((buffer = reader.readLine()) != null){
                String[] values = buffer.split("\t");

                Vertex<Stop> stop = getStop(stops, values[0]);

                stop.element().setX(Integer.parseInt(values[1]));
                stop.element().setY(Integer.parseInt(values[2]));
            }

            reader.close();

        }catch(Exception e){
            System.out.println("Couldn't read the coordinates file!");
        }
    }

    /*
    public Image readImage(Path path){
        Image img = new Image("file:" + path.getPath() + "img/dark.png");
        setPath(path.getPath() + "img/dark.png");
        return img;
    }*/

    private List<Image> images = new ArrayList<>();
    private List<String> thisPath = new ArrayList<>();

    public List<Image> readAllImages(Path path){
        Image img1 = new Image("file:" + path.getPath() + "img/dark.png");
        Image img2 = new Image("file:" + path.getPath() + "img/map.png");
        Image img3 = new Image("file:" + path.getPath() + "img/satellite.png");
        Image img4 = new Image("file:" + path.getPath() + "img/terrain.png");

        images.add(img1);
        images.add(img2);
        images.add(img3);
        images.add(img4);

        thisPath.add(path.getPath() + "img/dark.png");
        thisPath.add(path.getPath() + "img/map.png");
        thisPath.add(path.getPath() + "img/satellite.png");
        thisPath.add(path.getPath() + "img/terrain.png");

        return images;
    }

    public List<String> getPaths(){
        return thisPath;
    }


    private Vertex<Stop> getStop(List<Vertex<Stop>> stops, String code){
        for (Vertex<Stop> v : stops) {
            if(code.equals(v.element().getStopCode())){
                return v;
            }
        }
        return null;
    }

    private Edge<Route,Stop> getRoute(List<Edge<Route,Stop>> routes, String codeStart, String codeEnd){
        for (Edge<Route,Stop>  e : routes) {
            if(codeStart.equals(e.element().getStopCodeStart()) && codeEnd.equals(e.element().getStopCodeEnd())){
                return e;
            }
        }
        return null;
    }
}
