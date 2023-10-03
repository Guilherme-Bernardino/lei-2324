package pt.pa.model;

import pt.pa.graph.Edge;
import pt.pa.graph.Graph;
import pt.pa.graph.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.pa.dijsktra.DijsktraResult;


public class modelInterface {


    // Distance dijsktra

    public DijsktraResult<Stop, Route> minimumCostPath(Graph<Stop, Route> graph, Vertex<Stop> start, Vertex<Stop> destination) {
        Map<Vertex<Stop>, Integer> distances = new HashMap<>();
        Map<Vertex<Stop>, Vertex<Stop>> predecessors = new HashMap<>();
        Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors = new HashMap<>();

        findMinPathThroughDijkstra(graph, start, distances, predecessors, edgePredecessors);

        int cost = distances.get(destination);

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


    public void findMinPathThroughDijkstra(Graph<Stop, Route> graph, Vertex<Stop> start, Map<Vertex<Stop>, Integer> distances, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors) {
        List<Vertex<Stop>> unvisited = new ArrayList<>();

        for (Vertex<Stop> v : graph.vertices()) {
            unvisited.add(v);
            distances.put(v, Integer.MAX_VALUE);
            predecessors.put(v, null);
            edgePredecessors.put(v, null);
        }
        distances.put(start, 0);

        while(!unvisited.isEmpty()) {
            Vertex<Stop> currentMinCost = findMinCostVertex(distances, unvisited);


            if(currentMinCost == null || distances.get(currentMinCost) == Integer.MAX_VALUE)
                break;

            for (Edge<Route, Stop> e : graph.incidentEdges(currentMinCost)) {
                Vertex<Stop> neighbor = graph.opposite(currentMinCost, e);

                if(!unvisited.contains(neighbor)) continue;

                int pathCost = distances.get( currentMinCost ) +  e.element().getDistance();

                if(pathCost < distances.get(neighbor)) {
                    distances.put(neighbor, pathCost);
                    predecessors.put(neighbor, currentMinCost);
                    edgePredecessors.put(neighbor, e);
                }
            }
            unvisited.remove(currentMinCost);
        }
    }


    public Vertex<Stop> findMinCostVertex(Map<Vertex<Stop>, Integer> distances, List<Vertex<Stop>> unvisited) {
        if(unvisited.isEmpty())
            return null;

        Vertex<Stop> minVertex = unvisited.get(0);
        int minCost = distances.get(minVertex);

        for(int i=1; i < unvisited.size(); i++) {
            Vertex<Stop> current = unvisited.get(i);
            int currentCost = distances.get(current);

            if(currentCost < minCost) {
                minVertex = current;
                minCost = currentCost;
            }
        }
        return minVertex;
    }

    public DijsktraResult<Stop, Route> maximumCostPath(Graph<Stop, Route> graph, Vertex<Stop> start, Vertex<Stop> destination) {
        return null;
    }

    public void findMaxPathThroughDijkstra(Graph<Stop, Route> graph, Vertex<Stop> start, Map<Vertex<Stop>, Integer> distances, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors) {

    }

    public Vertex<Stop> findMaxCostVertex(Map<Vertex<Stop>, Integer> distances, List<Vertex<Stop>> unvisited) {
        return null;
    }


    class Result<Stop, Route> {
        private int cost = Integer.MAX_VALUE;
        private List<Vertex<Stop>> path = null;
        private List<Edge<Route, Stop>> pathEdges = null;

        public Result(int cost, List<Vertex<Stop>> path) {
            this.cost = cost;
            this.path = path;
        }

        public Result(int cost, List<Vertex<Stop>> path, List<Edge<Route, Stop>> pathEdges) {
            this.cost = cost;
            this.path = path;
            this.pathEdges = pathEdges;
        }

        public int getCost() {
            return cost;
        }

        public List<Vertex<Stop>> getPath() {
            return path;
        }

        public boolean hasSolution() {
            return cost != Integer.MAX_VALUE;
        }

        public List<Edge<Route, Stop>> getPathEdges() {
            return pathEdges;
        }
    }


    //Duration dijkstra

    public DijsktraResult<Stop, Route> minimumCostPathDuration(Graph<Stop, Route> graph, Vertex<Stop> start, Vertex<Stop> destination) {
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



    public void findMinPathThroughDijkstraDuration(Graph<Stop, Route> graph, Vertex<Stop> start, Map<Vertex<Stop>, Integer> durations, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors) {
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


    public Vertex<Stop> findMinCostVertexDuration(Map<Vertex<Stop>, Integer> durations, List<Vertex<Stop>> unvisited) {
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



    public DijsktraResult<Stop, Route> maximumCostPathDuration(Graph<Stop, Route> graph, Vertex<Stop> start, Vertex<Stop> destination) {
        return null;
    }

    public void findMaxPathThroughDijkstraDuration(Graph<Stop, Route> graph, Vertex<Stop> start, Map<Vertex<Stop>, Integer> durations, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Map<Vertex<Stop>, Edge<Route, Stop>> edgePredecessors) {

    }

    public Vertex<Stop> findMaxCostVertexDuration(Map<Vertex<Stop>, Integer> durations, List<Vertex<Stop>> unvisited) {
        return null;
    }

    class ResultDuration<Stop, Route> {
        private int cost = Integer.MAX_VALUE;
        private List<Vertex<Stop>> path = null;
        private List<Edge<Route, Stop>> pathEdges = null;

        public ResultDuration(int cost, List<Vertex<Stop>> path) {
            this.cost = cost;
            this.path = path;
        }

        public ResultDuration(int cost, List<Vertex<Stop>> path, List<Edge<Route, Stop>> pathEdges) {
            this.cost = cost;
            this.path = path;
            this.pathEdges = pathEdges;
        }

        public int getCost() {
            return cost;
        }

        public List<Vertex<Stop>> getPath() {
            return path;
        }

        public boolean hasSolution() {
            return cost != Integer.MAX_VALUE;
        }

        public List<Edge<Route, Stop>> getPathEdges() {
            return pathEdges;
        }
    }







}
