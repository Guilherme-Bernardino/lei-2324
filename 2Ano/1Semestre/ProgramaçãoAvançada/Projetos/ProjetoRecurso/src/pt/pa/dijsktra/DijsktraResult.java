package pt.pa.dijsktra;

import com.brunomnsilva.smartgraph.graph.Edge;
import com.brunomnsilva.smartgraph.graph.Vertex;
import pt.pa.model.Stop;

import java.util.List;

/**
 * Class that represents the result path from the Dijkstra algorithm.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 * @see Edge
 * @see Vertex
 *
 */
public class DijsktraResult<V, E>{
    private int cost;
    private List<Vertex<V>> path;
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

    public String getPathInString(){
        String text = "";
        for (Vertex<V> vertex : path) {
            if(vertex.element() instanceof Stop){
                text += ((Stop) vertex.element()).getStopCode() + " -> ";
            }
        }

        String strNew = text.substring(0, text.length()-3);

        return strNew;
    }

    @Override
    public String toString() {
        if (!hasSolution())
            return String.format("No solution. Cannot reach target vertex.");

        return String.format("Total = %d\r\n%s",
                getCost(),
                getPathInString());
    }
}