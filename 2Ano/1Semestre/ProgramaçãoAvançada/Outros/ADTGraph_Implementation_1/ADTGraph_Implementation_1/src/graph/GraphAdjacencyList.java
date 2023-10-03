package graph;

import java.util.*;

/**
 * THE SOLUTION IS NOT PROVIDED, GIVEN THAT IT IS A REQUIREMENT OF THE COURSE PROJECT.
 *
 * @param <V>
 * @param <E>
 */
public class GraphAdjacencyList<V,E> implements Graph<V, E> {

    private Map<V, Vertex<V>> vertices;

    public GraphAdjacencyList() {
        this.vertices = new HashMap<>();
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        MyVertex myU = checkVertex(u);
        MyVertex myV = checkVertex(v);

        //is there a common edge between myU.incidentEdges and myV.incidentEdges ?

        Set<Edge<E,V>> intersection = new HashSet<>(myU.incidentEdges);
        intersection.retainAll(myV.incidentEdges);

        return !intersection.isEmpty();
    }

    @Override
    public int numVertices() {
        return 0;
    }

    @Override
    public int numEdges() {
        return 0;
    }

    @Override
    public Collection<Vertex<V>> vertices() {
        return null;
    }

    @Override
    public Collection<Edge<E, V>> edges() {
        return null;
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        MyVertex mV = (MyVertex)v;

        return new ArrayList<>( mV.incidentEdges );
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        return null;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        return null;
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        return null;
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        return null;
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        return null;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        return null;
    }

    private class MyVertex implements Vertex<V> {
        private V element;
        private List<Edge<E,V>> incidentEdges;

        public MyVertex(V element) {
            this.element = element;
            this.incidentEdges = new ArrayList<>();
        }

        @Override
        public V element() {
            return element;
        }

        @Override
        public String toString() {
            return "Vertex{" + element + '}' + " --> " + incidentEdges.toString();
        }
    }

    private class MyEdge implements Edge<E, V> {
        private E element;

        public MyEdge(E element) {
            this.element = element;
        }

        @Override
        public E element() {
            return element;
        }

        @Override
        public Vertex<V>[] vertices() {
            //if the edge exists, then two existing vertices have the edge
            //in their incidentEdges lists
            List<Vertex<V>> adjacentVertices = new ArrayList<>();

            for(Vertex<V> v : GraphAdjacencyList.this.vertices.values()) {
                MyVertex myV = (MyVertex) v;

                if( myV.incidentEdges.contains(this)) {
                    adjacentVertices.add(v);
                }
            }

            if(adjacentVertices.isEmpty()) {
                return new Vertex[]{null, null}; //edge was removed meanwhile
            } else {
                return new Vertex[]{adjacentVertices.get(0), adjacentVertices.get(1)};
            }
        }

        @Override
        public String toString() {
            return "Edge{" + element + "}";
        }
    }

    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {
        if(v == null) throw new InvalidVertexException("Null vertex.");

        MyVertex vertex;
        try {
            vertex = (MyVertex) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Not a vertex.");
        }

        if (!vertices.containsValue(v)) {
            throw new InvalidVertexException("Vertex does not belong to this graph.");
        }

        return vertex;
    }

    private MyEdge checkEdge(Edge<E, V> e) throws InvalidEdgeException {
        if(e == null) throw new InvalidEdgeException("Null edge.");

        MyEdge edge;
        try {
            edge = (MyEdge) e;
        } catch (ClassCastException ex) {
            throw new InvalidVertexException("Not an edge.");
        }

        if (!edges().contains(edge)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }

        return edge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph | Adjacency List : \n");

        for(Vertex<V> v : vertices.values()) {
            sb.append( String.format("%s", v) );
            sb.append("\n");
        }

        return sb.toString();
    }
}
