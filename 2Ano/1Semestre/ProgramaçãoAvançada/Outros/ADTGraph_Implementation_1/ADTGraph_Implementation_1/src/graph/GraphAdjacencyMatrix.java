package graph;

import java.util.*;

public class GraphAdjacencyMatrix<V,E> implements Graph<V,E> {

    private Map< Vertex<V>, Map<Vertex<V>, Edge<E,V>>> adjacencyMap;

    public GraphAdjacencyMatrix() {
        this.adjacencyMap = new HashMap<>();
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        checkVertex(u);
        checkVertex(v);

        return adjacencyMap.get(u).containsKey(v);
    }

    @Override
    public int numVertices() {
        return adjacencyMap.size();
    }

    @Override
    public int numEdges() {
        return edges().size();
    }

    @Override
    public Collection<Vertex<V>> vertices() {
        return adjacencyMap.keySet();
    }

    @Override
    public Collection<Edge<E, V>> edges() {
        Set<Edge<E,V>> edgeSet = new HashSet<>();

        for (Map<Vertex<V>, Edge<E, V>> adjacencies : adjacencyMap.values()) {
            edgeSet.addAll( adjacencies.values() );
        }

        return edgeSet;
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        return adjacencyMap.get(v).values();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(v);

        Map<Vertex<V>, Edge<E, V>> vAdjacencies = adjacencyMap.get(v);
        for (Map.Entry<Vertex<V>, Edge<E, V>> tuple : vAdjacencies.entrySet()) {
            if(tuple.getValue() == e) return tuple.getKey();
        }

        return null; //'e' is not incident to 'v'
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        if(vertexOf(vElement) != null) {
            throw new InvalidVertexException(String.format("Element %s already exists.", vElement));
        }

        MyVertex v = new MyVertex(vElement);

        adjacencyMap.put(v, new HashMap<>());

        return v;
    }

    private Vertex<V> vertexOf(V element) {
        for (Vertex<V> v : adjacencyMap.keySet()) {
            if(v.element() == element) { //is it the same instance?
                return v;
            }
        }
        return null; //no vertex contains 'vElement'
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(u);
        checkVertex(v);

        MyEdge e = new MyEdge(edgeElement);

        adjacencyMap.get(u).put(v, e);
        adjacencyMap.get(v).put(u, e);

        return e;
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        Vertex<V> v1 = vertexOf(vElement1);
        Vertex<V> v2 = vertexOf(vElement2);

        if(v1 == null) throw new InvalidVertexException(String.format("No vertex contains %s.", vElement1));
        if(v2 == null) throw new InvalidVertexException(String.format("No vertex contains %s.", vElement2));

        return insertEdge(v1, v2, edgeElement);
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        for (Map.Entry<Vertex<V>, Map<Vertex<V>, Edge<E, V>>> map : adjacencyMap.entrySet()) {
            if(map.getKey() == v) continue;

            Map<Vertex<V>, Edge<E, V>> adjacencies = map.getValue();
            adjacencies.remove(v);

        }

        adjacencyMap.remove(v);

        return v.element();
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        checkEdge(e);

        //find 'e' and remove it. It will exist in two adjacency mappings.
        for (Map<Vertex<V>, Edge<E, V>> vertexAdjacencyMap : adjacencyMap.values()) {
            //remove key whose value is equal to 'e'
            //using the Iterator pattern (lectured later in class), this can be done:
            Iterator<Map.Entry<Vertex<V>, Edge<E, V>>> it = vertexAdjacencyMap.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<Vertex<V>, Edge<E, V>> tuple = it.next();
                if(tuple.getValue() == e) {
                    it.remove();
                }
            }
            //otherwise a two-step solution is required:
            //1. find which vertex 'v' is adjacent via 'e', if any
            //2. remove it afterwards, via vertexAdjacencyMap.remove(v)
        }

        return e.element();
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        checkVertex(v);

        MyVertex vertex = (MyVertex)v;

        V oldElement = vertex.element;
        vertex.element = newElement;

        return oldElement;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        checkEdge(e);

        MyEdge edge = (MyEdge)e;
        E oldElement = edge.element;
        edge.element = newElement;

        return oldElement;
    }

    private class MyVertex implements Vertex<V> {
        private V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return element;
        }

        @Override
        public String toString() {
            return String.format("Vertex{%s}", element);
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
            for(Vertex<V> v : GraphAdjacencyMatrix.this.adjacencyMap.keySet()) {

                for (Map.Entry<Vertex<V>, Edge<E, V>> entry : adjacencyMap.get(v).entrySet()) {
                    if( entry.getValue().equals( this )) {
                        return new Vertex[]{v, entry.getKey()};
                    }
                }
            }

            return new Vertex[]{null, null}; //edge was removed from the graph
        }

        @Override
        public String toString() {
            return String.format("Edge{%s}", element);
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

        if (!adjacencyMap.containsKey(v)) {
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
        StringBuilder sb = new StringBuilder("Graph | Adjacency Matrix : \n");

        for(Vertex<V> v : adjacencyMap.keySet()) {

            sb.append( String.format("%10s | ", v) );

            for (Map.Entry<Vertex<V>, Edge<E, V>> entry : adjacencyMap.get(v).entrySet()) {
                sb.append( String.format("%10s ---> %-10s + ", entry.getValue(), entry.getKey()) );
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
