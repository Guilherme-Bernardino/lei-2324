/* 
 * The MIT License
 *
 * Copyright 2019 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package graph;

import java.util.*;

/**
 * ADT Graph implementation that stores a collection of edges (and vertices) and
 * where each edge contains the references for the vertices it connects.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 *
 * @author brunomnsilva
 */
public class GraphEdgeList<V, E> implements Graph<V, E> {

    private Map<V, Vertex<V>> vertices;
    private Map<E, Edge<E, V>> edges;

    public GraphEdgeList() {
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        checkVertex(u);
        checkVertex(v);

        /* Is there an edge between v e w ?*/
        for(Edge<E, V> e : edges.values()) {
            MyEdge edge = (MyEdge)e;
            if(edge.v1 == v && edge.v2 == u || edge.v1 == u && edge.v2 == v) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int numVertices() {
        return this.vertices.size();
    }

    @Override
    public int numEdges() {
        return this.edges.size();
    }

    @Override
    public Collection<Vertex<V>> vertices() {
        return this.vertices.values();
    }

    @Override
    public Collection<Edge<E, V>> edges() {
        return this.edges.values();
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        List<Edge<E, V>> incident = new ArrayList<>();

        for(Edge<E, V> e : edges.values()) {
            MyEdge edge = (MyEdge)e;
            if(edge.v1 == v || edge.v2 == v) {
                incident.add(edge);
            }
        }

        return incident;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(v);
        checkEdge(e);

        //we could traverse only incidentEdges(v), but internally it will also scan
        //every edge.
        for (Edge<E, V> edge : edges.values()) {
            if(edge == e) {
                MyEdge myEdge = (MyEdge)edge;
                if(myEdge.v1 == v) return myEdge.v2;
                else if(myEdge.v2 == v) return myEdge.v1;
            }
        }

        return null; //'e' is not incident to 'v'
    }

    @Override
    public Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        if(vertices.containsKey(vElement))
            throw new InvalidVertexException(String.format("Element %s already exists.", vElement));

        MyVertex v = new MyVertex(vElement);

        vertices.put(vElement, v);

        return v;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(u);
        checkVertex(v);

        MyEdge e = new MyEdge(edgeElement, u, v);

        edges.put(edgeElement, e);

        return e;
    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException, InvalidEdgeException {
        Vertex<V> v1 = vertices.get(vElement1);
        Vertex<V> v2 = vertices.get(vElement2);
        return insertEdge(v1, v2, edgeElement);
    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        //NOTE: WE CAN NOT ITERATE OVER 'edges' collection while removing elements
        //-> Concurrent modification exception

        /*
        List<Edge<E,V>> toRemove = new ArrayList<>();
        for (Edge<E, V> e : edges.values()) {
            MyEdge edge = (MyEdge)e;
            if(edge.v1 == v || edge.v2 == v) {
                toRemove.add(e);
            }
        }
        */

        //incidentEdges(v) does the same work as above, so use it.
        Collection<Edge<E, V>> toRemove = this.incidentEdges(v);

        for (Edge<E, V> e : toRemove) {
            this.edges.remove(e.element());
        }

        vertices.remove(v.element());

        return v.element();
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        checkEdge(e);

        edges.remove(e.element());

        return e.element();
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        checkVertex(v);

        MyVertex vertex = (MyVertex)v;

        V oldElement = vertex.element;
        vertex.element = newElement;

        //Update mapping of collection
        vertices.remove(oldElement);
        vertices.put(newElement, vertex);

        return oldElement;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        checkEdge(e);

        MyEdge edge = (MyEdge)e;
        E oldElement = edge.element;
        edge.element = newElement;

        //Update mapping of collection
        edges.remove(oldElement);
        edges.put(newElement, e);

        return oldElement;
    }

    class MyVertex implements Vertex<V> {
        V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return this.element;
        }

        @Override
        public String toString() {
            return "Vertex{" + element + '}';
        }
    }

    class MyEdge implements Edge<E, V> {

        E element;
        Vertex<V> v1;
        Vertex<V> v2;

        public MyEdge(E element, Vertex<V> v, Vertex<V> w) {
            this.element = element;
            this.v1 = v;
            this.v2 = w;
        }

        @Override
        public E element() {
            return this.element;
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices = new Vertex[2];
            vertices[0] = v1;
            vertices[1] = v2;

            return vertices;
        }

        @Override
        public String toString() {
            return String.format("Edge{%s, v1 = %s, v2 = %s}", element, v1, v2);
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

        if (!vertices.containsKey(vertex.element)) {
            throw new InvalidVertexException(String.format("Vertex %s does not belong to this graph.", vertex.element));
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

        if (!edges.containsKey(edge.element)) {
            throw new InvalidEdgeException(String.format("Edge %s does not belong to this graph.", edge.element));
        }

        return edge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph | Edge List : \n");

        sb.append(String.format("Vertices (%d): \n", numVertices()));
        for (Vertex<V> v : vertices.values()) {
            sb.append("\t").append(v.toString()).append("\n");
        }
        sb.append("\n");
        sb.append(String.format("Edges (%d): \n", numEdges()));
        for (Edge<E, V> e : edges.values()) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
