//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: Hierarchy.java
//Date: 12/14/2020
//Description: Hierarchy for building the output for the graphset inputs
import java.util.LinkedList;
import java.util.Queue;
public class Hierarchy implements DFSActions<Vertex> {
	Queue<String> vertexList = new LinkedList<>();
	//method to process vertex to vertexList 
	public void processVertex(Vertex vertex) {
		vertexList.add(vertex.toString());
	}
	//descends the vertexlist 
	public void descendVertex(Vertex vertex) {
		vertexList.add("(");
	}
	//ascends the vertexList
	public void ascendVertex(Vertex vertex) {
		vertexList.add(")");
	}
	//checks if cycle is detected and adds to it
	public void cycleDetected() {
		vertexList.add("*");
	}
	//converts graphset to string 
	@Override
	public String toString() {
		int size = 0;
		String graphString = "";
		//loops through the vertex list and builds string of a Hierachy
		while (vertexList.size() > 0) {
			String check = vertexList.peek();
			vertexList.remove();
			if (check == "(") {
				if (vertexList.peek() == ")") {
					vertexList.remove();
					continue;
				} 
				else if (vertexList.peek() == "*") {
					graphString += vertexList.peek() + " ";
					vertexList.remove();
					vertexList.remove();
					continue;
				}
			}
			if(check == "("){
				size++;
			}
			else if(check == ")"){
				--size;
			}
			if(check == "(" ||check == ")"){
				continue;
			}
			//adds cycle if applicable 
			if(check != "*"){
				graphString += "\n";
			}
			for (int i = 0; i < size; i++) {
				graphString += "\t";
			}
			graphString += check + " " ;
		}
		graphString += "\n";
		return graphString;
	}

}
