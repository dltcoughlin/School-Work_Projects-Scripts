//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: DirectedGraph.java
//Date: 12/14/2020
//Description: Graph class to run the depth first search methods for the inputted graph sets, also checks if there are any unreachable classes
import java.util.*;
import java.util.ArrayList;
import java.util.Map;

public class Graph<V> {
	//starting node of Graph
	public V startingNode = null;
	Map<String, V> vertices = new HashMap<>();
	Map<V, ArrayList<V>> adjacencyList = new HashMap<>();
	//hashset to add any nodes that have been visisted
	Set<V> visitedNode = new HashSet<>();
	//creating Plist and Hlist for nodes to be added too
	ParenthesizedList pList = new ParenthesizedList();
	Hierarchy hList = new Hierarchy();
	//hold cycle true or false
	boolean cycle;
	//hashset for discovred nodes
	Set<V> discoveredNode = new HashSet<>();
	//method to call dfs method
	public void depthFirstSearch() {
		cycle = false;
		//runs dfs from the starting node
		dfs(startingNode);
	}
	//checks the list for depth first order
	private void dfs(V node) {
		//checks for cyles within the lists
		if (discoveredNode.contains(node)) {
			cycle = true;
			// Perform DFS Actions Cycle Detected operation
			pList.cycleDetected();
			hList.cycleDetected();
			return;
		}
		//adds the nodes to the lists through the process vertex method
		pList.processVertex((Vertex) node);
		hList.processVertex((Vertex) node);

		//descends the vertex through the descend vertex method
		pList.descendVertex((Vertex) node);
		hList.descendVertex((Vertex) node);
		///adds the node to the discovred nodes
		discoveredNode.add(node);
		//adds the node to the visisted nodes
		visitedNode.add(node);

		// gets the child of the lists
		ArrayList<V> list = adjacencyList.get(node);
		if (list != null) {
			for (V item : list)
				dfs(item);
			}
			//adds vertex to the lists
			pList.ascendVertex((Vertex) node);
			hList.ascendVertex((Vertex) node);
			discoveredNode.remove(node);
		}


	private V getVertex(String input) {
		return vertices.get(input);
	}
	//checks if any nodes are not visisted
	public void displayUnreachableClasses(){
		//goes through the list 
		for (Map.Entry<V, ArrayList<V>> item : adjacencyList.entrySet()) {
			//check if there are any unvisisted nodes throughout the list
			if(item.getValue().size()>0){
				//outputs the node if unvisisted
				if(!visitedNode.contains(item.getKey())){
					System.out.println(item.getKey() + " is unreachable");
					visitedNode.add(item.getKey());
				}
				//checks any ajacent nodes in case they are unreachable
				for (V vertex : item.getValue()){
					if(!visitedNode.contains(vertex)){
						System.out.println(vertex + " is unreachable");
						visitedNode.add(vertex);
					}
				}
			}
		}
	}


}