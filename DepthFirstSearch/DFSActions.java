//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: DFSActions.java
//Date: 12/14/2020
//Description: interface for methods for calling DFS actions
public interface DFSActions<V> {
	//method to process vertexes
	public void processVertex(V vertex);
	//method to descend vertexes
	public void descendVertex(V vertex);
	//method to ascend vertexes
	public void ascendVertex(V vertex);
	//method to check if a cycle is occuring
	public void cycleDetected();
}
