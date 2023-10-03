package pt.pa.model;

import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Graph;
import pt.pa.adts.graph.GraphEdgeList;
import pt.pa.adts.graph.Vertex;

import java.util.*;

public class MainDijsktra {

    public static void main(String[] args) {
        Graph<Local, Bridge> graph = new GraphEdgeList<>();

        Vertex<Local> vA = graph.insertVertex(new Local("A"));
        Vertex<Local> vB = graph.insertVertex(new Local("B"));
        Vertex<Local> vC = graph.insertVertex(new Local("C"));
        Vertex<Local> vD = graph.insertVertex(new Local("D"));
        Vertex<Local> vE = graph.insertVertex(new Local("E"));
        Vertex<Local> vF = graph.insertVertex(new Local("F"));


        graph.insertEdge(vA, vB, new Bridge("c", 3));
        graph.insertEdge(vA, vE, new Bridge("b", 15));
        graph.insertEdge(vE, vB, new Bridge("a", 4));
        graph.insertEdge(vA, vD, new Bridge("d", 7));
        graph.insertEdge(vD, vC, new Bridge("e", 3));
        graph.insertEdge(vD, vC, new Bridge("f", 2));

        Vertex<Local> orig = vE;
        Vertex<Local> dest = vC; //vF

        DijsktraResult<Local, Bridge> result = minimumCostPath(graph, orig, dest);

        System.out.printf("From %s to %s:\n", orig.element(), dest.element());
        System.out.println(result);
    }

    public static DijsktraResult<Local, Bridge> minimumCostPath(Graph<Local, Bridge> graph,
                                                 Vertex<Local> start,
                                                 Vertex<Local> destination) {

        Map<Vertex<Local>, Integer> distances = new HashMap<>();
        Map<Vertex<Local>, Vertex<Local>> predecessors = new HashMap<>();
        Map<Vertex<Local>, Edge<Bridge, Local>> edgePredecessors = new HashMap<>();

        dijkstra(graph, start, distances, predecessors, edgePredecessors);

        //consigo consultar distances e predecessors aqui!

        //qual o custo entre 'start' e 'destination'?
        int cost = distances.get(destination);

        //se distancia for "infinita", é porque não existe caminho até
        //o vértice 'destination'
        if(cost == Integer.MAX_VALUE)
            return new DijsktraResult<>(Integer.MAX_VALUE, null);

        //qual o caminho entre 'start' e 'destination'?
        List<Vertex<Local>> path = new ArrayList<>();
        List<Edge<Bridge, Local>> edgePath = new ArrayList<>();
        Vertex<Local> current = destination;
        while (current != start) {
            path.add(0, current);
            edgePath.add(0, edgePredecessors.get(current));

            current = predecessors.get(current);
        }
        path.add(0, start);

        return new DijsktraResult<>(cost, path, edgePath);
    }

    public static void dijkstra(Graph<Local, Bridge> graph, Vertex<Local> start,
                                Map<Vertex<Local>, Integer> distances,
                                Map<Vertex<Local>, Vertex<Local>> predecessors,
                                Map<Vertex<Local>, Edge<Bridge, Local>> edgePredecessors) {

        List<Vertex<Local>> unvisited = new ArrayList<>();

        for (Vertex<Local> v : graph.vertices()) {
            unvisited.add(v);
            distances.put(v, Integer.MAX_VALUE);
            predecessors.put(v, null);
            edgePredecessors.put(v, null);
        }
        distances.put(start, 0);

        while(!unvisited.isEmpty()) {
            Vertex<Local> current = findMinCostVertex(distances, unvisited);

            if(current == null || distances.get(current) == Integer.MAX_VALUE)
                break; //escusado continuar, só restam vértices não atingíveis a partir de 'start'

            for (Edge<Bridge, Local> e : graph.incidentEdges(current)) {
                Vertex<Local> neighbor = graph.opposite(current, e);

                if(!unvisited.contains(neighbor)) continue;

                int pathCost = distances.get( current ) + e.element().getCost();

                if(pathCost < distances.get(neighbor)) {
                    distances.put(neighbor, pathCost);
                    predecessors.put(neighbor, current);
                    edgePredecessors.put(neighbor, e);
                }
            }
            unvisited.remove(current);
        }
    }

    private static Vertex<Local> findMinCostVertex(Map<Vertex<Local>, Integer> distances,
                                                   List<Vertex<Local>> unvisited) {
        if(unvisited.isEmpty())
            return null; //embora nao esperado -> prog. defensiva.

        Vertex<Local> minVertex = unvisited.get(0);
        int minCost = distances.get(minVertex);

        for(int i=1; i < unvisited.size(); i++) {
            Vertex<Local> current = unvisited.get(i);
            int currentCost = distances.get(current);

            if(currentCost < minCost) {
                minVertex = current;
                minCost = currentCost;
            }
        }
        return minVertex;
    }

}
