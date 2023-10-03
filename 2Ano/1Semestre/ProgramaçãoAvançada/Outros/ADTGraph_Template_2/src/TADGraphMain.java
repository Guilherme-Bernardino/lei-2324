import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Graph;
import pt.pa.adts.graph.GraphEdgeList;
import pt.pa.adts.graph.Vertex;
import pt.pa.model.Bridge;
import pt.pa.model.Local;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;


public class TADGraphMain {
    public static void main(String[ ] args){
        Graph<Local, Bridge> graph = new GraphEdgeList<>();

        //NOTE: This graph is slightly different from the slides
        //However, the requested code is the same
        Vertex<Local> vA = graph.insertVertex(new Local("A"));
        Vertex<Local> vB = graph.insertVertex(new Local("B"));
        Vertex<Local> vC = graph.insertVertex(new Local("C"));
        Vertex<Local> vD = graph.insertVertex(new Local("D"));
        Vertex<Local> vE = graph.insertVertex(new Local("E"));

        graph.insertEdge(vA, vB, new Bridge("c"));
        graph.insertEdge(vA, vE, new Bridge("b"));
        graph.insertEdge(vE, vB, new Bridge("a"));
        graph.insertEdge(vA, vD, new Bridge("d"));
        graph.insertEdge(vD, vC, new Bridge("e"));
        graph.insertEdge(vD, vC, new Bridge("f"));

        System.out.println(graph);

        //PONTES QUE SAIEM DE 'A'
        System.out.println("Bridges leaving A:");
        Collection<Edge<Bridge, Local>> bridgesFromA = graph.incidentEdges(vA);
        System.out.println(bridgesFromA);

        //LOCAIS ADJACENTES A 'C'
        System.out.println("Adjacent places to 'D'");
        Collection<Edge<Bridge, Local>> bridgesFromD = graph.incidentEdges(vD);
        for(Edge<Bridge, Local> bridge : bridgesFromD) {
            Vertex<Local> op = graph.opposite(vD, bridge);
            System.out.println(op.element());
        }

        //BRIDGE count LeAVING 'C'
        System.out.println("Bridge count leaving 'C'");
        System.out.println(graph.incidentEdges(vC).size());

        //ADJACENCY
        System.out.println("Are A and B adjacent? " + graph.areAdjacent(vA, vB));
        System.out.println("Are D and B adjacent? " + graph.areAdjacent(vD, vB));

        //DFS traversal
        System.out.println("DFS traversal:");
        dfs(graph, vD);

        //TODO: Challenges: implement BFS

    }

    public static void dfs(Graph<Local, Bridge> graph, Vertex<Local> start) {
        List<Vertex<Local>> visited = new ArrayList<>();
        Stack<Vertex<Local>> stack = new Stack<>();

        visited.add(start); //#1
        stack.push(start);

        while(!stack.isEmpty()) {
            Vertex<Local> v = stack.pop();
            System.out.println(v.element()); //process
            Collection<Edge<Bridge, Local>> edges = graph.incidentEdges(v);
            for (Edge<Bridge, Local> e : edges) {
                Vertex<Local> w = graph.opposite(v, e);
                if(!visited.contains(w)) {
                    visited.add(w);
                    stack.push(w);
                }
            }
        }
    }

}
