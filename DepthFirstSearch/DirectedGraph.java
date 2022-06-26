//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: DirectedGraph.java
//Date: 12/14/2020
//Description: Directed Graph extending the Graph class to add edges to the lists

import java.util.ArrayList;
import java.util.Map;

public class DirectedGraph extends Graph<Vertex> {
	//adds edge to the graph
	public void addEdge(String input, String ver) {
		// checks if edges are already connected
		ArrayList<Vertex> vertexList = adjacencyList.get(getVertex(input));
		// if already not in the Adjacency list
		// makes the vertexlist an array list
		if (vertexList == null) {
			vertexList = new ArrayList<>();
		}
		// add a edge to source to destination
		vertexList.add(getVertex(ver));
		adjacencyList.put(getVertex(input), vertexList);

	}
	//checks if the node is already in the vertex and if it is not it will be mapped
	public Vertex getVertex(String input) {
		if (!vertices.containsKey(input)) {
			vertices.put(input, new Vertex(input));
		}
		return vertices.get(input);
	}

}






