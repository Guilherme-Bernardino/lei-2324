package pt.pa.graph;

import pt.pa.model.*;
import pt.pa.model.memento.Memento;
import pt.pa.model.memento.Originator;


import java.util.*;

/**
 * ADT Graph implementation that stores a collection of vertices and
 * where each vertex contains a list of incident edges.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 */
public class GraphAdjacencyList<V, E> implements Graph<V, E>, Originator {

    private Map<V, Vertex<V>> vertices;


    public GraphAdjacencyList() {
        this.vertices = new HashMap<>();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return edges().size();
    }

    @Override
    public Collection<Vertex<V>> vertices() {
        return vertices.values();
    }

    private boolean existsVertexWith(V element) {
        return vertices.containsKey(element);
    }

    private boolean existsEdgeWithElement(E element) {
        Collection<Edge<E, V>> e = edges();

        for (Edge<E, V> edge : e) {
            if (edge.element().equals(element) == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {

        if (vElement == null) {
            throw new InvalidVertexException("Cannot add Null vertex.");
        } else if (existsVertexWith(vElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }

        Vertex<V> newVertex = new MyVertex(vElement);

        vertices.put(vElement, newVertex);
        return newVertex;
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
            MyVertex veU = checkVertex(u);
        MyVertex veV = checkVertex(v);

        if (veU.equals(veV))
        {
            return false;
        }

        Set<Edge<E, V>> intersection = new HashSet<>(veU.incidentEdges);
        intersection.retainAll(veV.incidentEdges);

        return !intersection.isEmpty();
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        MyVertex myVertex = checkVertex(v);
        return myVertex.getIncidentEdges();
    }

    @Override
    public synchronized Collection<Edge<E, V>> edges() {
        Set<Edge<E, V>> set = new HashSet<>();

        vertices.values().forEach((vertex) -> {
            MyVertex myV = (MyVertex) vertex;
            set.addAll(myV.incidentEdges);
        });

        return set;
    }

    @Override
    public List<Edge<E,V>> getEdgesInList(){
        List<Edge<E,V>> edgesList = new ArrayList<>(edges());
        return edgesList;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement)
            throws InvalidVertexException, InvalidEdgeException {

        MyVertex v1 = checkVertex(u);
        MyVertex v2 = checkVertex(v);

        if (edgeElement == null) {
            throw new InvalidEdgeException("Cannot add Null edge.");
        } else if (existsEdgeWithElement(edgeElement) == true) {
            throw new InvalidEdgeException("There is already an edge with this element.");
        }

        MyEdge newEdge = new MyEdge(edgeElement);
        v1.incidentEdges.add(newEdge);
        v2.incidentEdges.add(newEdge);
        return newEdge;
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement)
            throws InvalidVertexException, InvalidEdgeException {

        Vertex<V> v1 = vertices.get(vElement1);
        Vertex<V> v2 = vertices.get(vElement2);

        return insertEdge(v1, v2, edgeElement);
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {

        checkVertex(v);
        checkEdge(e);

        MyEdge myEdge = (MyEdge) e;

        if (myEdge.vertices()[0] == v) {
            return myEdge.vertices()[1];
        } else {
            return myEdge.vertices()[0];
        }
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {

        checkVertex(v);

        MyVertex myVertex = (MyVertex) v;

        List<Edge<E, V>> l = myVertex.getIncidentEdges();
        while (l.size() > 0) {
            removeEdge(l.remove(0));
        }

        vertices().remove(v);

        return v.element();
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {

        checkEdge(e);

        MyEdge myEdge = (MyEdge)e;

        MyVertex v1 = (MyVertex)myEdge.vertices()[0];
        MyVertex v2 = (MyVertex)myEdge.vertices()[1];

        v1.getIncidentEdges().remove(e);
        v2.getIncidentEdges().remove(e);

        return e.element();
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        checkVertex(v);

        MyVertex myVertex = (MyVertex)v;
        myVertex.element = newElement;

        return v.element();
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        checkEdge(e);

        MyEdge myEdge = (MyEdge)e;
        myEdge.element = newElement;

        return e.element();
    }

    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {
        if (v == null)
            throw new InvalidVertexException("Null vertex.");

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
        if (e == null)
            throw new InvalidEdgeException("Null edge.");

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
    public List<Map.Entry<V, Integer>> getTuples(){

        Map<V, Integer> list = new HashMap<>();

        for (Vertex<V> vertex : this.vertices()) {
            MyVertex myVertex = (MyVertex) vertex;
            list.put(myVertex.element(), myVertex.getIncidentEdges().size());
        }

        List<Map.Entry<V, Integer>> nList = new ArrayList<>(list.entrySet());
        nList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        return nList;
    }

    @Override
    public List<Map.Entry<V, Integer>> getTop7Tuples(){
        List<Map.Entry<V, Integer>> list = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            list.add(getTuples().get(i));
        }

        return list;
    }

    List<Vertex<V>> visitedAll = new ArrayList<>();

    private List<Vertex<V>> dfs(Graph<V, E> graph, Vertex<V> start) {
        List<Vertex<V>> visited = new ArrayList<>();
        Stack<Vertex<V>> stack = new Stack<>();
        visited.add(start); //#1
        stack.push(start);

        while(!stack.isEmpty()) {
            Vertex<V> v = stack.pop();
            //System.out.println(v.element()); //process
            Collection<Edge<E, V>> edges = graph.incidentEdges(v);

            for (Edge<E, V> e : edges) {
                Vertex<V> w = graph.opposite(v, e);
                if(!visited.contains(w)) {
                    visited.add(w);
                    stack.push(w);
                }
            }
        }

        return visited;
    }

    @Override
    public int getNumberOfComponents(){
        int count = 0;

        for (Vertex<V> vertex : vertices()) {
            List<Vertex<V>> visited = dfs(this, vertex);
            //System.out.println(visited.size());

            if(!visitedAll.containsAll(visited)){
                visitedAll.addAll(visited);
                //System.out.println(visitedAll.size());
                count++;
            }
        }

        return count;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph | Adjacency List : \n");

        for (Vertex<V> v : vertices.values()) {
            sb.append(String.format("%s", v));
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
    Memento Classes
     */
    Edge<E, V> removedEdge;
    MyEdge removedMyEdge;
    public void setRemovedEdgeVertices(Edge<E,V> edge){
        this.removedEdge = edge;
        removedMyEdge = (MyEdge) edge;
        removedMyEdge.setVertexList(edge.vertices()[0],edge.vertices()[1], edge.element());
    }





    @Override
    public Memento createMemento() {
        return null;
    }

    @Override
    public void setMemento(Memento savedState) {

    }


    /**
     * Class MyVertex - Armazena o vértice (V) e a lista de arestas incidentes
     */
    private class MyVertex implements Vertex<V> {
        private V element;
        private List<Edge<E, V>> incidentEdges;

        public MyVertex(V element) {
            this.element = element;
            this.incidentEdges = new ArrayList<>();
        }

        @Override
        public V element() {
            return element;
        }

        public List<Edge<E, V>> getIncidentEdges() {
            return incidentEdges;
        }

        @Override
        public String toString() {
            return "Vertex{" + element + '}' + " --> " + incidentEdges.toString();
        }
    }

    /**
     * Class MyEdge - encapsula o element (E)
     */
    private class MyEdge implements Edge<E, V> {
        private E element;

        public List<Vertex<V>> vertexList = new LinkedList<>();
        public E myElement;
        public void setVertexList(Vertex<V> v1, Vertex<V> v2, E myElement){
            vertexList.add(v1);
            vertexList.add(v2);
            this.myElement = myElement;
        }

        public MyEdge(E element) {
            this.element = element;
        }

        @Override
        public E element() {
            return element;
        }

        @Override
        public Vertex<V>[] vertices() {
            // if the edge exists, then two existing vertices have the edge
            // in their incidentEdges lists
            List<Vertex<V>> adjacentVertices = new ArrayList<>();

            for (Vertex<V> v : GraphAdjacencyList.this.vertices.values()) {
                MyVertex myV = (MyVertex) v;

                if (myV.incidentEdges.contains(this)) {
                    adjacentVertices.add(v);
                }
            }

            if (adjacentVertices.isEmpty()) {
                return new Vertex[] { null, null }; // edge was removed meanwhile
            } else {
                return new Vertex[] { adjacentVertices.get(0), adjacentVertices.get(1) };
            }
        }

        @Override
        public String toString() {
            return "Edge{" + element + "}";
        }
    }
}