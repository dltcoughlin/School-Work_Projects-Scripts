//Name: Dalton Coughlin
//Project Name: Project 3
//File Name: BinaryTree.java
//Date: 12/7/2020
//Description: Constructor for BinaryTree, checks if binary tree is balanced, full, proper, the height, the amount of nodes, and outputs in sort order
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Arrays;
public class BinaryTree {

	Node parentNode;
	Node childNode;

	private Stack<Node> nodeStack = new Stack<>();
	//method to build tree calling recurisve method
	public BinaryTree (String input) throws InvalidTreeSyntax{
		String[] inputList = input.substring(1, input.length()-1).split("()"); 
		parentNode = new Node(inputList[0]);
		inputList = Arrays.copyOfRange(inputList, 1, inputList.length);
		//calls recursive method
		recursiveBinaryTree(inputList);
	}
	public String[] recursiveBinaryTree(String[] inputList) throws InvalidTreeSyntax {
			//checks if the node is a parent or a child 
			if (inputList[0].equals("(")){
				nodeStack.push(parentNode);
			if (childNode != null) {
				parentNode = childNode; 
			}
			//assign the current parentNode as the childNode of one on stack if closure of parenthesis
			}else if(inputList[0].equals(")")){
				try { 
					childNode = parentNode; 
					parentNode = nodeStack.pop();
				}catch (EmptyStackException emptyStack){ 
					throw new InvalidTreeSyntax("Incorrect parenthesis"); 
				}
			//if not a parentnode, will be a child node
			}else{ 
				childNode = new Node(inputList[0]);
				if (parentNode != null) { 
					parentNode.addchildNode(childNode); 
				}
			}
			//erases first element off array and thorws back into method
			inputList = Arrays.copyOfRange(inputList, 1, inputList.length);
			if (inputList.length != 0){
					return recursiveBinaryTree(inputList);
			}
			else{
				return null;
			}
	}

	//sends parentNode to recursion method to check if tree is balanced, difference of branches is not greater than one
	public boolean isBalanced() { 
		return recursiveIsBalanced(this.parentNode); 
	}
	//recursion method for checking balance of binary tree
	private boolean recursiveIsBalanced(Node root){
		if (root == null) {
			return true;
		}
		//checks if the values of the roots are less than or equal to zero and if not will return false
		return (Math.abs(recursiveHeight(root.left) - recursiveHeight(root.right)) <= 1) && (recursiveIsBalanced(root.left) && recursiveIsBalanced(root.right));
	}


	//Checks if binary tree is full and has the maximum number of nodes depending on it's height
	public boolean isFull() {
		return recursiveIsFull(this.parentNode, recursiveHeight(this.parentNode), 0); 
	}
	//recursion method for checking if binary tree is full
	private boolean recursiveIsFull(Node root, int height, int index) {
		if (root == null){ 
			return true; 
		}
		//checking height
		if (root.left == null && root.right == null) {
			return (height == index + 1); 
		}
		//if one of the roots are empty, tree is not full
		if (root.left == null || root.right == null){ 
			return false;
		}
		//recursive call to both put the left and right nodes to the method again with different height and index
		return recursiveIsFull(root.left, height, index+1) && recursiveIsFull(root.right, height, index+1);

	}

	//method to be called to check if binary tree is proper, if branches end with either 2 or 0 
	public boolean isProper() { 
		return recursiveIsProper(this.parentNode); 
	}
	//recursion method to throw the nodes to check if binarytree is proper
	private boolean recursiveIsProper(Node root) {
		if(root == null) {
			return true;
		}
		//checks if the right and left are equal and sends next nodes to method
		return ((root.left != null || root.right == null) && (root.left == null || root.right != null))&& (recursiveIsProper(root.left) && recursiveIsProper(root.right)); 
	}


	//counts height of binary tree
	public int height() {
		return recursiveHeight(this.parentNode)-1; 
	}
	//grabs max value of binary tree, left and right
	private int recursiveHeight(Node root) {
	//adds one to the greater of left and right, zero if null
		if (root == null){
			return 0;
		}
		else{
			return 1 + Math.max(recursiveHeight(root.left), recursiveHeight(root.right));
		}
	}
	//method to count nodes in binary tree
	public int nodes() { 
		return recursiveNodes(this.parentNode); 
	}
	//adds nodes together for output
	private int recursiveNodes(Node root) {
		if (root == null){
			return 0;
		}
		else{
			return 1 + recursiveNodes(root.left) + recursiveNodes(root.right);
		}
		
	}
	//outputting the nodes in order
	public String inOrder() {
		return recursiveInOrder(this.parentNode);
	}
	private String recursiveInOrder(Node root) {
		if (root == null){
			return "";
		}
		else{
			return "(" + recursiveInOrder(root.left) + root.input + recursiveInOrder(root.right) + ")";
		}
	}

	//sends node to string
	@Override
	public String toString() { 
		return parentNode.toString(); 
	}
	//Constructor for Node
	public static class Node {
		private String input;
		private Node left;
		private Node right;

		public Node(String input) { 
			this.input = input; 
		}
		//method for adding childnodes to the left and right when building binary tree
		private void addchildNode(Node childNode) throws InvalidTreeSyntax {
			if (this.left == null){
				this.setLeft(childNode); 
			}
			else if (this.right == null){ 
				this.setRight(childNode); 
			}
			else{ 
				throw new InvalidTreeSyntax("Nodes can only have 2 children");
			} 
		}
			 
		//Setting notes to the left and right
		private void setLeft(Node newLeft) { 
			left = newLeft; 
		}
		private void setRight(Node newRight) { 
			right = newRight; 
		}

		@Override
		public String toString() { 
			return toString(this); 
		}
		//recursively returning nodes
		private static String toString(Node root) {
			if (root == null){
				return "";
			}
			else{
				return "(" + root.input + toString(root.left) + toString(root.right) + ")";
			}
		}
	}
}