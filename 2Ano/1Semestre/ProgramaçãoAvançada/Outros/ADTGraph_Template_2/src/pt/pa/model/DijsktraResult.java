package pt.pa.model;

import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Vertex;

import java.util.List;

/**
 * Classe que agrega os resultados parciais de um caminho mais curto.
 *
 * @param <V> Tipo de dados dos vértices
 * @param <E> Tipo de dados das arestas
 */
public class DijsktraResult<V, E> {
    private int cost = Integer.MAX_VALUE;
    private List<Vertex<V>> path = null;
    private List<Edge<E,V>> pathEdges = null;

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
        if(!hasSolution())
            return String.format("No solution. Cannot reach target vertex.");

        //TODO: Podemos fazer o output "manualmente" e evitar "Vertex" e "Edge"
        //no output atual
        return String.format("Cost = %d | Path = %s | EdgePath = %s\n",
                getCost(),
                getPath(),
                getPathEdges());
    }
}
