import graph.*;

public class Main {
    public static void main(String[] args) {
        //Graph<String, String> graph = new GraphEdgeList<>();
        //Graph<String, String> graph = new GraphAdjacencyList<>();
        Graph<String, String> graph = new GraphAdjacencyMatrix<>();

        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");
        Vertex<String> f = graph.insertVertex("F");
        Vertex<String> g = graph.insertVertex("G");
        Vertex<String> h = graph.insertVertex("H");

        Edge<String, String> e1 = graph.insertEdge(a, d, "e1");
        Edge<String, String> e2 = graph.insertEdge(a, e, "e2");
        Edge<String, String> e3 = graph.insertEdge(a, b, "e3");
        Edge<String, String> e4 = graph.insertEdge(a, c, "e4");
        Edge<String, String> e5 = graph.insertEdge(c, g, "e5");
        Edge<String, String> e6 = graph.insertEdge(b, f, "e6");
        Edge<String, String> e7 = graph.insertEdge(g, b, "e7");
        Edge<String, String> e8 = graph.insertEdge(f, c, "e8");
        Edge<String, String> e9 = graph.insertEdge(e, h, "e9");
        Edge<String, String> e10 = graph.insertEdge(f, g, "e10");
        Edge<String, String> e11 = graph.insertEdge(h, f, "e11");
        Edge<String, String> e12 = graph.insertEdge(h, g, "e12");

        /* Check the output to visualize the current internal data structure */
        //System.out.println(graph);

        /* Answer the following questions through method calls and compare to the graph's illustration (graph.png):

            - What is the set of vertices?
            - What is the set of edges?
            - Is A adjacent to C ?
            - Is F adjacent to E ?
            - What are the incident edges of F ?
            - TODO: What is the opposite vertex of G via e7 ?
            - TODO: What is the opposite vertex of B via e9 ?

        */
        System.out.println("Vertice set = " + graph.vertices()); // expected = {A, ..., H}
        System.out.println("Edge set = " + graph.edges()); // expected = {e1, ..., e12}
        System.out.println("A adjacent to C ? " + graph.areAdjacent(a, c)); //expected = true
        System.out.println("F adjacent to E ? " + graph.areAdjacent(f, e)); //expected = false
        System.out.println("F incident edges set = " + graph.incidentEdges(f)); //expected = {e6, e8, e10, e11}
        System.out.println("Opposite of G via e7 = " + graph.opposite(g, e7)); //expected = B
        System.out.println("Opposite of B via e9 = " + graph.opposite(b, e9)); //expected = null

        /* TODO: - Remove F and repeat the above questions. */
        System.out.println("--- REMOVE VERTEX F and edge e2 ---");
        graph.removeVertex(f);
        graph.removeEdge(e2);

        //System.out.println(graph);
        System.out.println("Vertice set = " + graph.vertices()); // expected = {A, B, C, D, E, G, H}
        System.out.println("Edge set = " + graph.edges()); // expected = all others, except e6, e8, e10, e11 and e2
        System.out.println("A adjacent to C ? " + graph.areAdjacent(a, c)); //expected = true

        //Because we removed F, an exception will be thrown here
        try {
            System.out.println("F adjacent to E ? " + graph.areAdjacent(f, e)); //expected = exception
            System.out.println("F incident edges set = " + graph.incidentEdges(f)); //expected = exception
        } catch (InvalidVertexException ex) {
            System.out.println("EXCEPTION THROWN -> " + ex.getMessage());
        }
        System.out.println("Opposite of G via e7 = " + graph.opposite(g, e7)); //expected = B
        System.out.println("Opposite of B via e9 = " + graph.opposite(b, e9)); //expected = null


    }
}
