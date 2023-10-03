import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;
import pt.pa.controller.GraphController;
import pt.pa.dijsktra.DijkstraStrategy;
import pt.pa.dijsktra.DijsktraResult;
import pt.pa.graph.*;
import pt.pa.model.*;
import pt.pa.datasets_handler.Path;
import pt.pa.datasets_handler.Reader;
import com.brunomnsilva.smartgraph.containers.SmartGraphDemoContainer;
import com.brunomnsilva.smartgraph.graphview.*;
import pt.pa.datasets_handler.Writer;


import java.io.IOException;
import java.util.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        Reader reader = new Reader();

        Graph<Stop, Route>  graph = new DigraphAdjacencyMatrix<>();
        //GraphController controller = new GraphController((GraphAdjacencyList) graph);
        reader.readStops(graph, Path.DEMO);
        reader.readRoutes(graph, Path.DEMO);
        reader.readXY(graph, Path.DEMO);
        //Image img = reader.readImage(Path.DEMO);
        List<Image> images = reader.readAllImages(Path.DEMO);

        System.out.println(graph.getEdgesInList());
        System.out.println(graph.numEdges());
        Route newRoute = new Route("DA", "SA", 2, 2 );
        //controller.addRoute((Vertex<Stop>) graph.vertices().toArray()[0],(Vertex<Stop>) graph.vertices().toArray()[1],newRoute);
        //controller.removeRoute((Edge<Route, Stop>) graph.getEdgesInList().toArray()[1]);
        System.out.println("Get Edges in List: ");
        System.out.println(graph.getEdgesInList());
        System.out.println("Num Edges: ");
        System.out.println(graph.numEdges());
        busModelInterface newbus = new busModelInterface();
        newbus.setNetwork((GraphAdjacencyList<Stop, Route>) graph);
        Route route1, route2;
        route1 = new Route("SMC", "CBS", 200, 20);
        newbus.addRoute("Salamanca", "Castelo Branco", route1);


        //controller.undo();
        System.out.println("Graph Edges: ");
        System.out.println(graph.edges());
        System.out.println("Graph num Edges: ");
        System.out.println(graph.numEdges());

        Writer writer = new Writer(graph,reader.getPaths());
        try{
            writer.createFile();
        } catch (IOException e){
            try {
                throw new IOException("Couldn't export!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


        String customProp = "edge.label = true" + "\n" + "edge.arrow = false";
        SmartGraphProperties properties = new SmartGraphProperties(customProp);
        //SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        //SmartGraphPanel<String, String> graphView = new SmartGraphPanel<>(graph, strategy);
        SmartGraphPanel<Stop, Route> graphView = new SmartGraphPanel<>(graph, properties);

        Scene scene = new Scene(new SmartGraphDemoContainer(graphView), 1024, 768);
        //Scene scene = new Scene(new BorderPane(), 1200, 768);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Projeto PA 2223 - Bus Network");
        stage.setMinHeight(768);
        stage.setMinWidth(1200);
        stage.setScene(scene);
        stage.show();
        

        BackgroundImage bImg = new BackgroundImage(images.get(0),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);

        graphView.init();

        for (Vertex<Stop> vertex : graph.vertices()) {
            graphView.setVertexPosition(vertex, vertex.element().getX(), vertex.element().getY());
        }
        
        graphView.backgroundProperty().set(bGround);

        //System.out.println(graph.numVertices());
        //System.out.println(graph.numEdges());


        System.out.println("Stops Centralization : ");
        for(int i = 0; i < graph.getTop7Tuples().size(); i++){
            System.out.println(graph.getTop7Tuples().get(i));
        }


        System.out.println("Get network");
        System.out.println(newbus.getNetwork());
        System.out.println("Get Stop list");
        System.out.println(newbus.getStopList());
        System.out.println("Get route number");
        System.out.println(newbus.routeNumber());
        //System.out.println(newbus.stopsNAwayFromStop("Madrid", 1));
        System.out.println("Connected stops");
        System.out.println(newbus.connectedStops("Salamanca"));
        //System.out.println(newbus.findFarthestStopPair());
        Stop stop1;

        stop1 = new Stop("SSBR", "Sesimbra", 45, -4, 500, 350);
        newbus.addStop(stop1);
        System.out.println("Before adding route: ");
        System.out.println(newbus.routeNumber());
        route2 = new Route("VLD", "OVD", 520, 320);
        newbus.addRoute("Valladolid", "Oviedo", route2);
        System.out.println("After adding route: ");
        System.out.println(newbus.routeNumber());
        System.out.println("Find route: ");
        System.out.println(newbus.findRoute("Salamanca", "Castelo Branco"));
        System.out.println("Stops after adding stop: ");
        System.out.println(newbus.findStop("Sesimbra"));
        System.out.println("Get Stop list: ");
        System.out.println(newbus.getStopList());
        System.out.println("Is Madrid isolated ?");
        System.out.println(newbus.isStopIsolated("Madrid"));
        System.out.println("Is Badajoz isolated ?");
        System.out.println(newbus.isStopIsolated("Badajoz"));
        System.out.println("Does Route exist between Madrid and Salamanca ?");
        System.out.println( newbus.existRoute("Madrid", "Salamanca"));
        System.out.println("Does Route exist between Madrid and Bilbao ?");
        System.out.println(newbus.existRoute("Madrid", "Bilbao"));

        //System.out.println(graph.getNumberOfComponents((Vertex<Stop>) graph.vertices().toArray()[0]));
        System.out.println("Graph get number of components: ");
        System.out.println(graph.getNumberOfComponents());

        DijkstraStrategy dijkstra = new RouteDurationDijkstraStrategy();

        DijkstraStrategy duration = new RouteDistanceDijkstraStrategy();
        DijsktraResult<Stop, Route> resultado = dijkstra.minimumCostPath(graph, (Vertex<Stop>) graph.vertices().toArray()[1], (Vertex<Stop>) graph.vertices().toArray()[3]);
        DijsktraResult<Stop,Route> result = dijkstra.minimumCostPath(graph,(Vertex<Stop>) graph.vertices().toArray()[1], (Vertex<Stop>) graph.vertices().toArray()[3]);
        //DijsktraResult<Stop,Route> result2 = dijkstra.maximumCostPath(graph,(Vertex<Stop>) graph.vertices().toArray()[0], (Vertex<Stop>) graph.vertices().toArray()[1]);

        System.out.println();
        System.out.println("Dijkstra: ");
        System.out.println(result);
        //System.out.println(result2);
        System.out.println(resultado);

    }
}
