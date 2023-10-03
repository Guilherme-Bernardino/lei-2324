package pt.pa.dijsktra;

import com.brunomnsilva.smartgraph.graph.*;
import pt.pa.model.Route;
import pt.pa.model.Stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RouteDistanceDijkstraStrategy implements DijkstraStrategy in the Strategy pattern.
 * Class responsible using the Dijkstra algorithm with Routes duration as a metric.
 *
 * @author Guilherme B. 202001870
 */
public class RouteDurationDijkstraStrategy implements DijkstraStrategy {
    @Override
    public DijsktraResult<Stop, Route> minimumCostPath(Graph<Stop, Route> graph, Vertex<Stop> start, Vertex<Stop> destination) {
        Map<Vertex<Stop>, Integer> durations = new HashMap<>();
        Map<Vertex<Stop>, Vertex<Stop>> predecessors = new HashMap<>();
        Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors = new HashMap<>();

        findMinPathThroughDijkstra(graph, start, durations, predecessors, edgePredecessors);

        int cost = durations.get(destination);

        if(cost == Integer.MAX_VALUE)
            return new DijsktraResult<>(Integer.MAX_VALUE, null);

        List<Vertex<Stop>> path = new ArrayList<>();
        List<Edge<Route, Stop>> edgePath = new ArrayList<>();
        Vertex<Stop> current = destination;
        while (current != start) {
            path.add(0, current);
            edgePath.add(0, edgePredecessors.get(current));

            current = predecessors.get(current);
        }
        path.add(0, start);

        return new DijsktraResult<>(cost, path, edgePath);
    }

    @Override
    public void findMinPathThroughDijkstra(Graph<Stop, Route> graph, Vertex<Stop> start, Map<Vertex<Stop>, Integer> durations, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors) {
        List<Vertex<Stop>> unvisited = new ArrayList<>();

        for (Vertex<Stop> v : graph.vertices()) {
            unvisited.add(v);
            durations.put(v, Integer.MAX_VALUE);
            predecessors.put(v, null);
            edgePredecessors.put(v, null);
        }
        durations.put(start, 0);

        while(!unvisited.isEmpty()) {
            Vertex<Stop> currentMinCost = findMinCostVertex(durations, unvisited);


            if(currentMinCost == null || durations.get(currentMinCost) == Integer.MAX_VALUE)
                break;

            for (Edge<Route, Stop> e : graph.incidentEdges(currentMinCost)) {
                Vertex<Stop> neighbor = graph.opposite(currentMinCost, e);

                if(!unvisited.contains(neighbor)) continue;

                int pathCost = durations.get( currentMinCost ) +  e.element().getDuration();

                if(pathCost < durations.get(neighbor)) {
                    durations.put(neighbor, pathCost);
                    predecessors.put(neighbor, currentMinCost);
                    edgePredecessors.put(neighbor, e);
                }
            }
            unvisited.remove(currentMinCost);
        }
    }

    @Override
    public Vertex<Stop> findMinCostVertex(Map<Vertex<Stop>, Integer> durations, List<Vertex<Stop>> unvisited) {
        if(unvisited.isEmpty())
            return null;

        Vertex<Stop> minVertex = unvisited.get(0);
        int minCost = durations.get(minVertex);

        for(int i=1; i < unvisited.size(); i++) {
            Vertex<Stop> current = unvisited.get(i);
            int currentCost = durations.get(current);

            if(currentCost < minCost) {
                minVertex = current;
                minCost = currentCost;
            }
        }
        return minVertex;
    }

}
