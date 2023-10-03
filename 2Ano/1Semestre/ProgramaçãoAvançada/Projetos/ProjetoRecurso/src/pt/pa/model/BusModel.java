package pt.pa.model;

import com.brunomnsilva.smartgraph.graph.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.image.Image;
import pt.pa.datasets_handler.*;
import pt.pa.datasets_handler.reader_strategy.ReadDemoStrategy;
import pt.pa.datasets_handler.reader_strategy.ReaderStrategy;
import pt.pa.dijsktra.DijkstraStrategy;
import pt.pa.dijsktra.DijsktraResult;
import pt.pa.dijsktra.RouteDistanceDijkstraStrategy;
import pt.pa.dijsktra.RouteDurationDijkstraStrategy;
import pt.pa.factorymethod.TicketFactory;
import pt.pa.factorymethod.Ticket;
import pt.pa.model.memento.Caretaker;
import pt.pa.model.memento.Memento;
import pt.pa.model.memento.Originator;
import pt.pa.observerpattern.Subject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * Bus Model class, belongs to MVC pattern, responsible for creating new graphs and data for both controller and view to access.
 *
 * @author Guilherme B. 202001870
 */
public class BusModel extends Subject implements Originator {

    private static final ReaderStrategy DEFAULT_DATASET = new ReadDemoStrategy();
    private static final int TOP10 = 10;
    private static final int PDF_FONTSIZE = 8;
    private Caretaker caretaker;
    private Graph<Stop, Route> network;
    private Route replaceRoute;
    private Reader reader;
    private List<Image> images;

    public BusModel() {
        setReader(DEFAULT_DATASET);
        this.caretaker = new Caretaker(this);
    }

    public BusModel(BusModel m) {
        setReader(new ReadDemoStrategy());
        this.images = new ArrayList<>();
    }

    /**
     * Changes Background Image, enters with a number for selection of an image on the array of dataset images.
     *
     * @param image
     * @return image for changing
     */
    public Image changeBackgroundImage(int image){
        if(image < 0 && image > 3) throw new RuntimeException("Invalid Image!");
        return images.get(image);
    }

    public Graph<Stop, Route> getMap() { return network; }

    /**
     * Returns all image paths on this graph.
     *
     * @return list of paths
     */
    public List<String> getPaths(){
        return reader.getPaths();
    }

    /**
     * Returns number of vertices on the graph.
     *
     * @return total number of vertices
     */
    public int stopNumber() {
        return network.numVertices();
    }

    /**
     * Returns number of routes on the graph.
     *
     * @return total number of routes
     */
    public int routesNumber() {
        return network.numEdges();}

    /**
     * Instantiates a new writer object and creates a new file with exported graph dataset and images.
     *
     * @param pathList
     * @throws IOException
     */
    public void exportGraph(List<String> pathList) throws IOException {
        Writer writer1 = new Writer(network,pathList);
        writer1.createFile();
    }

    /**
     * Adds stop vertex to graph through new stop data.
     * Updates the view.
     *
     * @param stop
     * @throws InvalidVertexException
     */
    public void addStop(Stop stop) throws InvalidVertexException {
        if (findStop(stop.getStopCode()) != null) {
            throw new BusModelException("Already exists a vertex with that code!");
        }

        try {
            network.insertVertex(stop);
            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new BusModelException();
        }
    }

    /**
     * Adds route edge to graph through new route data.
     * Updates the view.
     *
     * @param startCode
     * @param stopCode
     * @param distance
     * @param duration
     * @throws InvalidEdgeException
     */
    public void addRoute(String startCode, String stopCode, int distance, int duration) throws InvalidEdgeException{
        try {
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            Route route = new Route(startCode, stopCode, distance, duration);

            network.insertEdge(p1, p2, route);

            notifyObservers(null);

        } catch (InvalidEdgeException e) {
            throw new BusModelException();
        }
    }

    /**
     * Removes stop through stop code.
     * Updates the view.
     *
     * @param stopCode
     * @throws InvalidVertexException
     */
    public void removeStop(String stopCode) throws InvalidVertexException{
        try {
            Vertex<Stop> stopVertex = findStop(stopCode);

            if(stopVertex == null){
                throw new BusModelException("Stop " + stopCode + " does not exist!");
            }

            network.removeVertex(stopVertex);

            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new BusModelException();
        }
    }

    /**
     * Removes route through stop start code and stop code.
     * Updates the view.
     *
     * @param startCode
     * @param stopCode
     * @throws InvalidEdgeException
     */
    public void removeRoute(String startCode, String stopCode) throws InvalidEdgeException{
        try {
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            network.removeEdge(findRoute(p1,p2));

            notifyObservers(null);

        } catch (InvalidVertexException e) {
            throw new BusModelException();
        }
    }

    /**
     * Replaces route and edge element through stop start code, stop code, distance and duration.
     * Updates the view.
     *
     * @param startCode
     * @param stopCode
     * @param distance
     * @param duration
     * @throws InvalidEdgeException
     */
    public void replaceRoute(String startCode, String stopCode, int distance, int duration) throws InvalidEdgeException{
        try{
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            Route newRoute = new Route(startCode, stopCode, distance, duration);

            replaceRoute = network.replace(findRoute(p1,p2), newRoute);

            caretaker.saveState();

            notifyObservers(null);

            System.out.println(caretaker.getMemento().getDescription());
            System.out.println(network);

        } catch (InvalidVertexException e){
            throw new BusModelException();
        }
    }

    /**
     * Sets a new reader path, reads all image paths a creates a new graph.
     * Updates the view.
     *
     * @param readerStrategy
     */
    public void setReader(ReaderStrategy readerStrategy){
        try{
            this.network = new DigraphEdgeList<>();
            reader = new Reader(readerStrategy);

            reader.readFileData(network, readerStrategy);
            images = reader.readAllImages(readerStrategy);

            if(reader.getPaths().isEmpty()){
                throw new BusModelException("No dataset available!");
            }

            notifyObservers(null);
        } catch (IOException e){
            throw new BusModelException("No dataset exists!");
        }
    }

    /**
     * Iterates through all stops and checks its number of adjacent vertices.
     *
     * @return all vertices in a list
     */
    public List<Map.Entry<Stop, Integer>> getTuples() {
        Map<Stop, Integer> list = new HashMap<>();

        for (Vertex<Stop> vertex : network.vertices()) {
            list.put(vertex.element(), network.incidentEdges(vertex).size());
        }

        List<Map.Entry<Stop, Integer>> nList = new ArrayList<>(list.entrySet());
        nList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return nList;
    }

    /**
     * Iterates through the tuples list and returns a list with the top 10 most central stops.
     *
     * @return top 10 list
     */
    public List<Map.Entry<Stop, Integer>> getTop10Tuples(){
        List<Map.Entry<Stop, Integer>> list = new ArrayList<>();
        for(int i = 0; i < TOP10; i++){
            list.add(getTuples().get(i));
        }

        return list;
    }

    /**
     * Method for finding a specific vertex in the graph.
     *
     * @param code
     * @return the found vertex
     */
    private Vertex<Stop> findStop(String code) {
        for (Vertex<Stop> v : network.vertices()) {
            if (v.element().getStopCode().equals(code)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Method for finding a specific edge in the graph.
     *
     * @param p1
     * @param p2
     * @return
     */
    private Edge<Route,Stop> findRoute(Vertex<Stop> p1 , Vertex<Stop> p2) {
        for (Edge<Route, Stop> edge : network.incidentEdges(p2)) {
            if (network.opposite(p1, edge) == p2) {
                return edge;
            }
        }
        return null;
    }

    /**
     * Returns a DijkstraResult for the shortest path between two vertices, using route distance as a metric.
     *
     * @param startCode
     * @param stopCode
     * @return result of Dijkstra algorithm
     */
    public DijsktraResult<Stop,Route> getShortestDistancePath(String startCode, String stopCode){
        try {
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            DijkstraStrategy distanceStrategy = new RouteDistanceDijkstraStrategy();

            DijsktraResult<Stop, Route> result = distanceStrategy.minimumCostPath(network, p1, p2);

            return result;

        } catch (InvalidEdgeException e){
            throw new BusModelException();
        }
    }

    /**
     * Returns a DijkstraResult for the shortest path between two vertices, using route duration as a metric.
     *
     * @param startCode
     * @param stopCode
     * @return result of Dijkstra algorithm
     */
    public DijsktraResult<Stop,Route> getShortestDurationPath(String startCode, String stopCode){
        try {
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            DijkstraStrategy durationStrategy = new RouteDurationDijkstraStrategy();

            DijsktraResult<Stop,Route> result = durationStrategy.minimumCostPath(network, p1, p2);

            return result;

        } catch (InvalidEdgeException e){
            throw new BusModelException();
        }
    }

    /**
     * Generates a pdf file for the Full Ticket format.
     * This ticket includes information between two stops.
     *
     * @param startCode
     * @param stopCode
     * @param factory
     * @param type
     */
    public void generateTicket(String startCode, String stopCode, TicketFactory factory, String type) {
        try {
            Vertex<Stop> p1 = findStop(startCode);
            Vertex<Stop> p2 = findStop(stopCode);

            if (p1 == null) {
                throw new BusModelException(" Stop " + startCode+ " does not exist!");
            }
            if (p2 == null) {
                throw new BusModelException(" Stop " + stopCode + " does not exist!");
            }

            DijsktraResult<Stop, Route> dijsktraResultDistance = getShortestDistancePath(startCode, stopCode);

            List<String> stopNames = new ArrayList<>();
            for (Vertex<Stop> v: dijsktraResultDistance.getPath()) {
                stopNames.add(v.element().getStopName());
            }

            int total = 0;

            for (Edge<Route, Stop> e: dijsktraResultDistance.getPathEdges()) {
                total += e.element().getDuration();
            }

            stopNames.remove(0);
            stopNames.remove(stopNames.size() - 1);

            Ticket ticket = factory.create(type,p1.element().getStopName(),p2.element().getStopName(),stopNames,dijsktraResultDistance.getCost(),
                    total, dijsktraResultDistance.getPath().size() - 2);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(
                    "./datasets/" + ticket.getTitle() + "_" + p1.element().getStopName() + p2.element().getStopName() + ".pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, PDF_FONTSIZE, BaseColor.BLACK);
            Paragraph chunk = new Paragraph(String.valueOf(ticket.toStringFormatted()), font);

            document.add(chunk);
            document.close();


        } catch (InvalidEdgeException e){
            throw new BusModelException();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Memento createMemento() {
        return new BusModelMemento(replaceRoute);
    }

    @Override
    public void setMemento(Memento savedState) {
        if (savedState instanceof BusModelMemento) {
            BusModelMemento state = (BusModelMemento) savedState;

            Vertex<Stop> p1 = findStop(state.state.getStopCodeStart());
            Vertex<Stop> p2 = findStop(state.state.getStopCodeEnd());

            network.replace(findRoute(p1,p2), state.state);

            notifyObservers(null);
        }
    }

    /**
     * Restores this model to the previous state.
     */
    public void undo() {
        caretaker.restoreState();
    }

    /**
     * Bus Model Memento holds data for the model's mementos.
     */
    private class BusModelMemento implements Memento {
        private Route state;

        public BusModelMemento(Route stateToSave) {
            state = new Route(stateToSave.getStopCodeStart(),stateToSave.getStopCodeEnd(), stateToSave.getDistance(), stateToSave.getDuration());
            System.out.println(state.getStopCodeStart());
            System.out.println(state.getStopCodeEnd());
        }

        @Override
        public String getDescription() {
            return state.toString();
        }
    }
}
