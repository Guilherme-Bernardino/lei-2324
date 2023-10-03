package pt.pa.dijsktra;

import pt.pa.graph.Edge;
import pt.pa.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the result path from the Dijkstra algorithm.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 * @see Edge
 * @see Vertex
 */
public class DijsktraResult<V, E> {
    private int cost = Integer.MAX_VALUE;
    private List<Vertex<V>> path = null;
    private List<Edge<E, V>> pathEdges = null;

    public DijsktraResult(int cost, List<Vertex<V>> path) {
        this.cost = cost;
        this.path = path;
    }

    public DijsktraResult(int cost, List<Vertex<V>> path, List<Edge<E, V>> pathEdges) {
        this.cost = cost;
        this.path = path;
        this.pathEdges = pathEdges;
    }

    public int getCost() {
        return cost;
    }

    public List<Vertex<V>> getPath() {
        return path;
    }

    public boolean hasSolution() {
        return cost != Integer.MAX_VALUE;
    }

    public List<Edge<E, V>> getPathEdges() {
        return pathEdges;
    }


    @Override
    public String toString() {
        if (!hasSolution())
            return String.format("No solution. Cannot reach target vertex.");

        //TODO: Podemos fazer o output "manualmente" e evitar "Vertex" e "Edge"
        //no output atual


        return String.format("Cost = %d\\r\\n | Path = %s\\r\\n | EdgePath = %s\\r\\n",
                getCost(),
                getPath(),
                getPathEdges());
    }
}