package pt.pa.dijsktra;

import pt.pa.dijsktra.DijsktraResult;
import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.Vertex;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import java.util.List;
import java.util.Map;

public interface DijkstraStrategy{

    /**
     * Minimum cost between two vertices, takes the graph and the start and destination vertices as params and finds the associated path
     * @param graph
     * @param start
     * @param destination
     * @return DijsktraResult, keeps total cost, vertices and edges in path
     */
    DijsktraResult<Stop, Route> minimumCostPath(Graph<Stop,Route> graph, Vertex<Stop> start, Vertex<Stop> destination);

    /**
     * Dijkstra Algorithm to return minimum cost path
     * @param graph
     * @param start
     * @param costs
     * @param predecessors
     * @param edgePredecessors
     */
    void findMinPathThroughDijkstra(Graph<Stop,Route> graph, Vertex<Stop> start,
                          Map<Vertex<Stop>, Integer> costs,
                          Map<Vertex<Stop>, Vertex<Stop>> predecessors,
                          Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors);

    /**
     * Algorithm, finds the minimum cost vertex in graph
     * @param costs
     * @param unvisited
     * @return Minimum cost Stop in graph
     */
    Vertex<Stop> findMinCostVertex(Map<Vertex<Stop>, Integer> costs, List<Vertex<Stop>> unvisited);

    /**
     * Maximum cost between two vertices, takes the graph and the start and destination vertices as params and finds the associated path
     * @param graph
     * @param start
     * @param destination
     * @return DijsktraResult, keeps total cost, vertices and edges in path
     */
    DijsktraResult<Stop, Route> maximumCostPath(Graph<Stop,Route> graph,Vertex<Stop> start, Vertex<Stop> destination);

    /**
     * Dijkstra Algorithm to return maximum cost path
     * @param graph
     * @param start
     * @param costs
     * @param predecessors
     * @param edgePredecessors
     */
    void findMaxPathThroughDijkstra(Graph<Stop,Route> graph, Vertex<Stop> start,
                             Map<Vertex<Stop>, Integer> costs,
                             Map<Vertex<Stop>, Vertex<Stop>> predecessors,
                             Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors);

    /**
     * Algorithm, finds the maximum cost vertex in graph
     * @param costs
     * @param unvisited
     * @return Maximum cost Stop in graph
     */
    Vertex<Stop> findMaxCostVertex(Map<Vertex<Stop>, Integer> costs,
                                          List<Vertex<Stop>> unvisited);
}
