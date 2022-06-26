//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: ParenthesizedList.java
//Date: 12/14/2020
//Description: ParthensizedList for building the output for the graphset inputs
import java.util.LinkedList;
import java.util.Queue;

public class ParenthesizedList implements DFSActions<Vertex> {
	Queue<String> vertexList = new LinkedList<>();
	//processes the vertex to a string
	public void processVertex(Vertex vertex) {
		vertexList.add(vertex.toString());
	}
	//descends the vertexList
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
	//outputs the list to parenthesized list string
	@Override
	public String toString() {
		String graphString = "";
		graphString += "( ";
		while (vertexList.size() > 0) {
			String check = vertexList.peek();
			vertexList.remove();

			if (check == "(") {
				if (vertexList.peek() == ")") {
					vertexList.remove();
					continue;
				} 
				//adds cycle if applicable 
				else if (vertexList.peek() == "*") {
					graphString += vertexList.peek() + " ";
					vertexList.remove();
					vertexList.remove();
					continue;
				}
			}
			graphString += check + " ";
		}
		graphString += ")\n";
		return graphString;
	}
}