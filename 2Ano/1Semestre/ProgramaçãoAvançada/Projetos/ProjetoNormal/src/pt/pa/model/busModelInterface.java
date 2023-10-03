package pt.pa.model;

import pt.pa.graph.*;
import pt.pa.model.memento.Memento;
import pt.pa.observerpattern.Subject;

import java.util.*;
import java.util.stream.Collectors;


public class busModelInterface extends Subject{

    private String networkName;
    private Graph<Stop, Route> network;
    public busModelInterface() {network = new DigraphAdjacencyMatrix<>(); }


    public busModelInterface(busModelInterface m) {
        network = new GraphAdjacencyList<>();
        network.vertices().addAll(m.network.vertices());
    }

    public Graph<Stop, Route> getMap() { return network; }

    public int stopNumber() {return network.numVertices();}

    public int routeNumber(){
        return network.numEdges();
    }


    private int countStops(Stop s) { return connectedStops(s.getStopName()).size(); }

    public Collection<Vertex<Stop>> getStopList() {
        return network.vertices();
    }

    public Vertex<Stop> findStop(String stopName) {
        for (Vertex<Stop> stop : network.vertices()) {
            if (stop.element().getStopName().trim().equals(stopName.trim())) {
                return stop;
            }
        }
        return null;
    }

    public void addStop(Stop stop) throws InvalidVertexException {
        try {
            network.insertVertex(stop);
        } catch (InvalidVertexException e) {
            throw new InvalidVertexException("Error: Invalid Stop");
        }
    }


    public Vertex<Stop> removeStop(String name) throws InvalidVertexException {
        if (!existStop(name)) throw new InvalidStopOperation("Hub does not exist!");
        Vertex<Stop> h = findStop(name);
        network.removeVertex(h);
        notifyObservers(null);
        return h;
    }


    public void addRoute(String stop1, String stop2, Route route) throws InvalidVertexException, InvalidEdgeException {
        if (findStop(stop1) == null || findStop(stop2) == null)
            throw new InvalidVertexException("Error: Invalid Stop");
        try{
            network.insertEdge(findStop(stop1),findStop(stop2),route);
        }catch (InvalidEdgeException e){
            throw new InvalidEdgeException("Error: Invalid Route");
        }
    }


    public boolean existStop(String stop) {
        return findStop(stop) != null;
    }


    public Edge<Route, Stop> findRoute(String sourceStop, String destinationStop) throws InvalidVertexException {
        if (!existStop(sourceStop) || !existStop(destinationStop))
            throw new InvalidVertexException("Error: Invalid Stop");
        Vertex<Stop> stop1 = findStop(sourceStop);
        Vertex<Stop> stop2 = findStop(destinationStop);

        for(Edge<Route, Stop> edge: network.edges())
            if (edge.vertices()[0].equals(stop1) && edge.vertices()[1].equals(stop2)){
                return edge;
            }
        return null;
    }

    public boolean existRoute(String sourceStop, String destinationStop) {
        return findRoute(sourceStop, destinationStop)!=null;
    }


    public Collection<Stop> connectedStops(String stop) throws InvalidVertexException {
        if (!existStop(stop))
            throw new InvalidVertexException("Stop " + stop + " does not exist");

        Vertex<Stop> stopFind = findStop(stop);

        Set<Stop> set = new HashSet<>();
        for (Edge<Route, Stop> edge: network.edges())
            if (edge.vertices()[0].equals(stopFind))
                set.add(edge.vertices()[1].element());
            else
            if(edge.vertices()[1].equals(stopFind) )
                set.add(edge.vertices()[0].element());
        return set;
    }


    public Edge<Route, Stop> removeRoute(String sourceStop, String destinationStop) throws InvalidRouteOperation, InvalidStopOperation {
        if (!existStop(sourceStop)) throw new InvalidStopOperation("Hub does not exist!");
        if (!existStop(destinationStop)) throw new InvalidStopOperation("Hub does not exist!");
        if (!existRoute(sourceStop, destinationStop))
            throw new InvalidEdgeException("Route does not exists!");
        Edge<Route, Stop> e = findRoute(sourceStop, destinationStop);
        network.removeEdge(e);
        notifyObservers(null);
        return e;
    }


    public boolean isStopIsolated(String stop) throws InvalidVertexException {
        if (!existStop(stop))
            throw new InvalidVertexException("Stop does not exist!");
        return connectedStops(stop).size()==0;
    }


    public Graph<Stop, Route> getNetwork() {
        return network;
    }

    public Collection<Vertex<Stop>> getVertices(){
        return network.vertices();
    }


    public void setNetwork(GraphAdjacencyList<Stop, Route> network) {
        this.network = network;
    }


    public String getNetworkName() {
        return networkName;
    }


    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }




    public Memento createMemento() {
        return  new NetworkMemento(this.network);
    }



    public void setMemento(Memento savedState) {
        this.network = ((NetworkMemento)savedState).state;
    }


    private class NetworkMemento implements Memento {
        private Graph<Stop, Route> state;

        /**
         * Creates a new memento from a graph to be saved
         * @param stateToSave graph to be saved
         */
        public NetworkMemento (Graph<Stop, Route> stateToSave) {
            this.state = new GraphAdjacencyList<>();
            for(Vertex<Stop> v : stateToSave.vertices()){
                state.insertVertex(v.element());
            }
            for(Edge<Route,Stop> e : stateToSave.edges()){
                state.insertEdge(e.vertices()[0].element(),e.vertices()[1].element(),e.element());
            }
        }

        /**
         * Returns a description from the memento
         * @return Returns a description from the memento
         */
        @Override
        public String getDescription() {
            return state.toString();
        }

    }


    public double findMinimumCostPath(String orig, String dest, List<Vertex<Stop>> localsPath, Graph<Stop, Route> graph) {
        Vertex<Stop> vOrig = this.findStop(orig);
        Vertex<Stop> vDest = this.findStop(dest);
        double cost = 0;
        Map<Vertex<Stop>, Double> costs = new HashMap<>();
        Map<Vertex<Stop>, Vertex<Stop>> predecessors = new HashMap<>();
        Vertex<Stop> vertex = vDest;

        dijkstra(vOrig, costs, predecessors, graph);

        if(predecessors.get(vDest) != null) {
            while (!vertex.element().getStopName().equals(vOrig.element().getStopName())) {
                localsPath.add(vertex);
                vertex = predecessors.get(vertex);
            }

            localsPath.add(vertex);
            cost = costs.get(vDest);
        }

        return cost;
    }



    private static void dijkstra(Vertex<Stop> orig, Map<Vertex<Stop>, Double> costs, Map<Vertex<Stop>, Vertex<Stop>> predecessors, Graph<Stop, Route> graph) {
        double cost = 0.0;

        for (Vertex<Stop> v:graph.vertices()) {
            costs.put(v, Double.MAX_VALUE);
            predecessors.put(v, null);
        }

        costs.replace(orig, 0.0);

        List<Vertex<Stop>> unvisited = (List<Vertex<Stop>>) graph.vertices();

        while (!unvisited.isEmpty()) {
            Vertex<Stop> vertex = findLowerVertex(costs, unvisited);

            try {
                if (costs.get(vertex) == Double.MAX_VALUE)
                    return;
            }
            catch (Exception e) {
                unvisited.clear();
            }

            unvisited.remove(vertex);

            List<Vertex<Stop>> adjacents = new ArrayList<>();

            for (Vertex<Stop> v:unvisited) {
                if (graph.areAdjacent(vertex, v))
                    adjacents.add(v);
            }

            for (Vertex<Stop> vertexAdjacent:adjacents) {
                cost = 0.0;
                cost = costs.get(vertex) + cost_between(vertex, vertexAdjacent, graph);

                if(cost < costs.get(vertexAdjacent)) {
                    costs.replace(vertexAdjacent, cost);
                    predecessors.replace(vertexAdjacent, vertex);
                }
            }
        }
    }


    private static Double cost_between(Vertex<Stop> vertex, Vertex<Stop> vertexAdjacent, Graph<Stop, Route> graph) {
        Double cost = Double.MAX_VALUE;

        for (Edge<Route, Stop> edge:graph.incidentEdges(vertex)) {
            if ((edge.vertices()[0] == vertexAdjacent || edge.vertices()[1] == vertexAdjacent) && cost > edge.element().getDistance())
                cost = Double.valueOf(edge.element().getDistance());
        }

        return cost;
    }


    private static Vertex<Stop> findLowerVertex(Map<Vertex<Stop>, Double> costs, List<Vertex<Stop>> unvisited) {
        Vertex<Stop> vertex = null;
        Double cost = Double.MAX_VALUE;

        for (Vertex<Stop> v:unvisited) {
            if(costs.get(v) < cost) {
                vertex = v;
                cost = costs.get(v);
            }
        }

        return vertex;
    }



    public List<Stop> findFarthestStopPair(){
        double cost = 0.0, currentCost = 0.0;
        List<Vertex<Stop>> path = new ArrayList<>();
        List<Stop> stops = new ArrayList<>();
        Stop orig = null;
        Stop dest = null;

        for (Vertex<Stop> vOrig : network.vertices()) {
            for (Vertex<Stop> vDest : network.vertices()) {
                if(!vOrig.element().getStopName().equals(vDest.element().getStopName())) {
                    currentCost = findMinimumCostPath(vOrig.element().getStopName(), vDest.element().getStopName(), path, network);
                    if(currentCost > cost) {
                        cost = currentCost;
                        orig = vOrig.element();
                        dest = vDest.element();
                    }

                    path.clear();
                }
            }
        }

        stops.add(orig);
        stops.add(dest);

        return stops;
    }



    public List<Stop> stopsNAwayFromStop(String stop, int n){
        List<Stop> stops = new ArrayList<>();
        List<Vertex<Stop>> visited = new ArrayList<>();
        List<Vertex<Stop>> unvisited = new ArrayList<>();
        List<Vertex<Stop>> vertices = (List<Vertex<Stop>>) network.vertices();
        Vertex<Stop> stopVertex = findStop(stop);

        for (Vertex<Stop> vertex:vertices) {
            if(!stopVertex.element().getStopName().equals(vertex.element().getStopName())) {
                if (network.areAdjacent(stopVertex, vertex)) {
                    visited.add(vertex);
                    stops.add(vertex.element());
                }
            }
        }

        for (int i=1;i<n;i++) {
            for (Vertex<Stop> vertex:visited) {
                for (Vertex<Stop> v:vertices) {
                    if (!vertex.element().getStopName().equals(v.element().getStopName())) {
                        if (network.areAdjacent(vertex, v)) {
                            if(!stops.contains(v.element()) && !v.element().getStopName().equals(stop)) {
                                unvisited.add(v);

                                stops.add(v.element());
                            }
                        }
                    }
                }
            }

            visited.clear();
            visited = new ArrayList<>(unvisited);
            unvisited.clear();
        }

        return stops;
    }



    public int countComponents() {
        List<Vertex<Stop>> visited = new ArrayList<>();
        int count = 0;
        for(Vertex<Stop> h : network.vertices()){
            if(!visited.contains(h)){
                dfs(h, visited);
                count ++;
            }
        }
        return count;
    }


    public void dfs(Vertex<Stop> stopVertex,  List<Vertex<Stop>> visited) {
        visited.add(stopVertex);
        for (int i = 0; i < network.vertices().size(); i++) {
            Collection<Stop> list = new ArrayList<>(connectedStops(stopVertex.element().getStopName()));
            for(Stop s : list){
                if (!visited.contains(findStop(s.getStopName())))
                    dfs(findStop(s.getStopName()), visited);
            }
        }
    }

    public Collection<Stop> neighbors(String hub) throws InvalidStopOperation {
        if (!existStop(hub)) throw new InvalidStopOperation("Hub does not exist!");
        List<Stop> neighbors = new ArrayList<>();
        Vertex<Stop> h = findStop(hub);
        for (Edge<Route, Stop> e : network.incidentEdges(h)) {
            Vertex<Stop> opposite = network.opposite(h, e);
            neighbors.add(opposite.element());
        }
        return neighbors;
    }

    public Map<String, Integer> mostPopularStops() {
        Map<String, Integer> stopNeighbors = new HashMap<>();
        for (Vertex<Stop> h : network.vertices()) {
            Collection<Stop> neighbors = neighbors(h.element().getStopName());
            stopNeighbors.put(h.element().getStopName(), neighbors.size());
        }
        Map<String, Integer> sortedDesc = stopNeighbors.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortedDesc;
    }

}
