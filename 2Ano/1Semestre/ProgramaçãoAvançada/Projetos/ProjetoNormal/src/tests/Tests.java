package tests;

import org.junit.Before;
import org.junit.Test;
import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.GraphAdjacencyList;
import pt.pa.graph.Vertex;
import pt.pa.model.Route;
import pt.pa.model.Stop;
import pt.pa.datasets_handler.Path;
import pt.pa.datasets_handler.Reader;

import static org.junit.Assert.*;

public class Tests {

    Graph<Stop, Route> graph;
    Stop stop, stop2, stop3, stop4;
    Vertex<Stop> vStop, vStop2, vStop3;
    Route route, route2, route3;
    Edge<Route,Stop> vRoute, vRoute2;

    @Before
    public void setUp(){
        graph = new GraphAdjacencyList<>();

        stop = new Stop("CDM", "CasalDoMarco", 0,0);
        vStop = graph.insertVertex(stop);
        stop2 = new Stop("FOG","Fogueteiro",0,0);
        vStop2 = graph.insertVertex(stop2);
        stop3 = new Stop("AMO","Amora",0,0);
        vStop3 = graph.insertVertex(stop3);
        stop4 = new Stop("SXL", "Seixal", 0,0);

        Reader reader = new Reader();

        reader.readStops(graph, Path.DEMO);
        reader.readRoutes(graph, Path.DEMO);
        reader.readXY(graph, Path.DEMO);

        route = new Route("CDM","FOG",0, 0);
        vRoute = graph.insertEdge(stop, stop2, route);
        route2 = new Route("CDM", "AMO",0, 0);
        vRoute2 = graph.insertEdge(stop,stop3,route2);
        route3 = new Route("FOG", "AMO",0, 0);

    }

    @Test
    public void test_checkIfGraphIsEmptyAtCreation() {
        Graph<Stop,Route> newGraph = new GraphAdjacencyList();
        
        assertTrue(newGraph.vertices().isEmpty());
        assertTrue(newGraph.edges().isEmpty());
    }

    @Test
    public void test_ifGraphIsNotEmptyAfterSetUp() {
        assertFalse(graph.vertices().isEmpty());
        assertFalse(graph.edges().isEmpty());
    }

    @Test
    public void test_containsVertices() {
        assertFalse(graph.vertices().isEmpty());
    }

    @Test
    public void test_containsEdges() {
        assertFalse(graph.edges().isEmpty());
    }

    @Test
    public void test_ifInsertVertexIsCorrect() {
        for (Vertex<Stop> s : graph.vertices()) {
            System.out.println(s.element());
        }

        assertTrue(graph.vertices().contains(vStop));
    }

    @Test
    public void test_ifRemoveVertexIsCorrect() {
        for (Vertex<Stop> s : graph.vertices()) {
            System.out.println(s.element());
        }

        System.out.println("--------------------------------------");

        graph.removeVertex(vStop);

        for (Vertex<Stop> s : graph.vertices()) {
            System.out.println(s.element());
        }

        assertFalse(graph.vertices().contains(stop));
    }

    @Test
    public void test_ifGraphHasAllStops() {
        //for the DEMO file, the graph should have 16 Stops (The 13 in the file +3 inserted manually before tests).

        assertTrue(graph.numVertices()==16);
    }

    @Test
    public void test_ifInsertEdgeIsCorrect() {
        assertTrue(graph.edges().contains(vRoute));
    }

    @Test
    public void test_ifGraphHasAllRoutes() {
        //for the DEMO file, the graph should have 14 default Routes +2 added at setUp. Total 16
        assertTrue(graph.numEdges()==16);
    }

    @Test
    public void test_ifVertexAreAdjacent() {
        //verifies the existence of and edge that connects the 2 Stops. One was created in Setup.
        assertTrue(graph.areAdjacent(vStop,vStop2));
    }

    @Test
    public void test_ifEdgeHasIncidentEdges() {
        //The Stop should have 2 incident edges manually created in setUp
        assertEquals(2,graph.incidentEdges(vStop).size());
    }

    @Test
    public void test_ifVertexOpposite() {
        assertTrue(graph.opposite(vStop,vRoute)==vStop2);
    }


    @Test
    public void test_ifRemoveEdgeIsCorrect() {
        graph.removeEdge(vRoute);

        assertFalse(graph.edges().contains(route));
    }

    @Test
    public void test_ifReplaceVertexIsCorrect() {
        for (Vertex<Stop> v: graph.vertices()) {
            System.out.println(v);
        }
        System.out.println("-------------------------------");
        graph.replace(vStop3, stop4);

        for (Vertex<Stop> v: graph.vertices()) {
            System.out.println(v);
        }

        assertTrue(vStop3.element()==stop4);
    }

    @Test
    public void test_ifReplaceEdgeIsCorrect() {
        for (Edge<Route, Stop> e: graph.edges()) {
            System.out.println(e);
        }
        System.out.println("-------------------------------");
        graph.replace(vRoute2, route3);

        for (Edge<Route, Stop> e: graph.edges()) {
            System.out.println(e);
        }

        assertTrue(vRoute2.element()==route3);
    }
}
