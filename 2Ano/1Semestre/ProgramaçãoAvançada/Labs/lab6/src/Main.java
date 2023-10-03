import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Graph;
import pt.pa.adts.graph.GraphAdjacencyList;
import pt.pa.adts.graph.Vertex;

public class Main {
    public static void main(String[] args) {

        Graph<String, Integer> graph = new GraphAdjacencyList<>();

        Vertex<String> v = graph.insertVertex("v");;
        Vertex<String> u = graph.insertVertex("u");;
        Vertex<String> w = graph.insertVertex("w");;
        Vertex<String> z = graph.insertVertex("z");;
    
        Edge<Integer, String> e1 = graph.insertEdge(v, u, 120);;
        Edge<Integer, String> e2 = graph.insertEdge(w, u, 70);;
        Edge<Integer, String> e3 = graph.insertEdge(v, w, 150);;
        Edge<Integer, String> e4 = graph.insertEdge(z, w, 50);;

        System.out.println(graph.numVertices());
    }
}