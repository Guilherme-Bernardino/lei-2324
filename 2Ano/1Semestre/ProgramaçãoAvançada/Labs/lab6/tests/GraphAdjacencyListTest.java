package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import pt.pa.adts.graph.Edge;
import pt.pa.adts.graph.Graph;
import pt.pa.adts.graph.GraphAdjacencyList;
import pt.pa.adts.graph.InvalidEdgeException;
import pt.pa.adts.graph.InvalidVertexException;
import pt.pa.adts.graph.Vertex;

public class GraphAdjacencyListTest {
	
	Graph<String, Integer> graph;

	Vertex<String> v;
	Vertex<String> u;
	Vertex<String> w;
	Vertex<String> z;

	Edge<Integer, String> e1;
	Edge<Integer, String> e2;
	Edge<Integer, String> e3;
	Edge<Integer, String> e4;

	@Before
	public void beforeTest() {
		graph = new GraphAdjacencyList<>();

		v = graph.insertVertex("v");
		u = graph.insertVertex("u");
		w = graph.insertVertex("w");
		z = graph.insertVertex("z");

		e1 = graph.insertEdge(v, u, 120);
		e2 = graph.insertEdge(w, u, 70);
		e3 = graph.insertEdge(v, w, 150);
		e4 = graph.insertEdge(z, w, 50);
	}

	@Test
	public void insertVertex_throwsException_ifVertexExists() {
		assertThrows(InvalidVertexException.class, () -> {
			graph.insertVertex("v");
		});
	}

	@Test
	public void insertEdge_throws_invalidEdgeException_ifEdge_isNullOrExists() {
		assertThrows(InvalidEdgeException.class, () -> {
			graph.insertEdge(v, u, null);
		});
		
		assertThrows(InvalidEdgeException.class, () -> {
			graph.insertEdge(v, u, 120);
		});
		
		assertThrows(InvalidEdgeException.class, () -> {
			graph.insertEdge(u, v, 120);
		});
	}

	@Test
	public void opposite_isOk() {
		assertEquals(u, graph.opposite(v, e1));
		assertEquals(v, graph.opposite(u, e1));
	}

}
