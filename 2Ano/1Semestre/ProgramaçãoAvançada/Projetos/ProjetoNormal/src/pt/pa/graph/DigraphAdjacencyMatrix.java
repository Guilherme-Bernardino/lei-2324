package pt.pa.graph;

import java.util.*;

/**
 * Implementation of a digraph that adheres to the {@link Digraph} interface.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 * <br>
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 * @author guilh
 */
public class DigraphAdjacencyMatrix<V, E> implements Digraph<V, E> {

    private Map< Vertex<V>, Map<Vertex<V>, Edge<E,V>>> adjacencyMap;

    public DigraphAdjacencyMatrix() {
        this.adjacencyMap = new HashMap<>();
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

        for (Map<Vertex<V>, Edge<E, V>> adjacents : adjacencyMap.values()) {
            edgeSet.addAll(adjacents.values());
        }

        return edgeSet;
        /*
        Collection<Edge<E, V>> edges = new ArrayList<>();
        for (Vertex<V> fromVertex : adjacencyMap.keySet()) {
            for (Edge<E, V> edge : adjacencyMap.get(fromVertex).values()) {
                edges.add(edge);
            }
        }
        return edges;*/
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> inbound) throws InvalidVertexException {
        checkVertex(inbound);

        Collection<Edge<E, V>> incidentEdges = new ArrayList<>();
        for (Map.Entry<Vertex<V>, Map<Vertex<V>, Edge<E, V>>> entry : adjacencyMap.entrySet()) {
            if (entry.getValue().containsKey(inbound)) {
                incidentEdges.add(entry.getValue().get(inbound));
            }
        }
        return incidentEdges;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        for (Map.Entry<Vertex<V>, Edge<E, V>> entry : adjacencyMap.get(v).entrySet()) {
            if (entry.getValue().equals(e)) {
                return entry.getKey();
            }
        }
        throw new InvalidEdgeException();
    }

    @Override
    public Collection<Edge<E, V>> outboundEdges(Vertex<V> outbound) throws InvalidVertexException {
        checkVertex(outbound);

        Map<Vertex<V>, Edge<E, V>> vertexAdjacencyMap = adjacencyMap.get(outbound);
        if (vertexAdjacencyMap == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(vertexAdjacencyMap.values());
    }

    @Override
    public boolean areAdjacent(Vertex<V> outbound, Vertex<V> inbound) throws InvalidVertexException {
        checkVertex(outbound);
        checkVertex(inbound);

        /* find and edge that goes outbound ---> inbound */
        return adjacencyMap.get(outbound).containsKey(inbound);
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
    public Edge<E, V> insertEdge(Vertex<V> outbound, Vertex<V> inbound, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(outbound);
        checkVertex(inbound);

        MyEdge edge = new MyEdge(edgeElement);
        adjacencyMap.get(outbound).put(inbound, edge);
        return edge;
    }

    @Override
    public Edge<E, V> insertEdge(V outboundElement, V inboundElement, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        Vertex<V> v1 = vertexOf(outboundElement);
        Vertex<V> v2 = vertexOf(inboundElement);

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

        for (Map<Vertex<V>, Edge<E, V>> vertexAdjacencyMap : adjacencyMap.values()) {
            vertexAdjacencyMap.remove(e.vertices()[0]);
            vertexAdjacencyMap.remove(e.vertices()[1]);
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

    @Override
    public List<Map.Entry<V, Integer>> getTuples() {
        return null;
    }

    @Override
    public List<Map.Entry<V, Integer>> getTop7Tuples() {
        return null;
    }

    @Override
    public int getNumberOfComponents() {
        return 0;
    }

    @Override
    public Collection<Edge<E, V>> getEdgesInList() {
        return null;
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
            for(Vertex<V> v : DigraphAdjacencyMatrix.this.adjacencyMap.keySet()) {
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
}

