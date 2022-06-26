//Name: Dalton Coughlin
//Project Name: Project 4
//File Name: Project4.java
//Date: 12/14/2020
//Description: Main class that intakes the input file and runs through the methods that will run the depthFirstSearch method and outputting a paranthesizedlist, hierarchy, and unreacable classes
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project4 {
	static DirectedGraph graphSet = new DirectedGraph();
	public void readGraph() {
		//choosing file
		JFileChooser choice = new JFileChooser(new File("."));
		int option = choice.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				Scanner input = new Scanner(choice.getSelectedFile());
				while (input.hasNextLine()) {
					String edgeString = input.nextLine();
					String[] edge = edgeString.split(" ");
					//puts first node in the graph set
					if (graphSet.startingNode == null){
						graphSet.startingNode = graphSet.getVertex(edge[0]);
					}
					//once first node is in place, will add rest of nodes
					for (int i = 1; i < edge.length; i++) {
						graphSet.addEdge(edge[0], edge[i]);
					}
				}
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Project4().readGraph();
		//calls depth first search method
		graphSet.depthFirstSearch();
		//sends graphset to the paranthesizedlist tostring method
		System.out.println(graphSet.hList.toString());
		//sends graphset to the hierarchy tostring method
		System.out.println(graphSet.pList.toString());
		
		//displays the unreacable classes
		graphSet.displayUnreachableClasses();


	}
}
