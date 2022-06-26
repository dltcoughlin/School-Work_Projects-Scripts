import java.util.ArrayList;
import java.util.Map;
import java.util.*;

public class DirectedGraph extends Graph<Vertex> {
	public void addEdge(String u, String v) {
	/**
	* Task: create a directed edge and add it to the graph
	* @param u Node have a edge fom (source node)
	* @param v Node have a edge to (destination node)
	*/

	// Check if th source node already has some connected edges
		ArrayList<Vertex> vertexList = adjacencyList.get(getVertex(u));


	// if already not in the Adjacency list
	// Map it to a new Vertex and initialize
		if (vertexList == null) {
			vertexList = new ArrayList<>();
		}

	// add a edge to source to destination
		vertexList.add(getVertex(v));

	// update the adjacency list
		adjacencyList.put(getVertex(u), vertexList);

	}

	public Vertex getVertex(String u) {
		/**
		* Task: check if a node is already mapped to a vertex, if not map it
		* @param u node(String) to be mapped
		* @return Mapped correspond vertex of the node
		*/

		// if this node(String) showed up for the first time
		// map it to a correspond vertex
		if (!vertices.containsKey(u)) {
			vertices.put(u, new Vertex(u));
		}
		return vertices.get(u);
	}

}






