//Name: Dalton Coughlin
//Project Name: Project 3
//File Name: Project3.java
//Date: 12/7/2020
//Description: This script is for building the input and output GUI and calling methods to Binary Tree
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class Project3 extends JFrame {

	private JTextField input = new JTextField(20);
	private JTextField output = new JTextField(20);
	private static BinaryTree inputTree;
	//building frame
	public Project3() {
		super("Binary Tree");
		setSize(700, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		JPanel panel = new JPanel(new FlowLayout());
		JComponent[] inputComponents = {new JLabel("Enter Expression"), input};
		JComponent[] outputComponents = {new JLabel("Output"), output};
		JButton[] buttonComponents = {new JButton("Make Tree"), new JButton("Is Balanced?"),new JButton("Is Full?"), new JButton("Is Proper?"), new JButton("Height"),new JButton("Nodes"), new JButton("Inorder")};
		JPanel outputPanel = new JPanel(new FlowLayout());
		JPanel inputPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		//adding comptonents to flow panels
		for (Component component: inputComponents) {
			inputPanel.add(component); 
		}
		add(inputPanel);
		for (Component component: buttonComponents) {
			buttonPanel.add(component); 
		}
		add(buttonPanel);
		for (Component component: outputComponents) {
			outputPanel.add(component); 
		}
		add(outputPanel);
		for (JButton button: buttonComponents){
			button.addActionListener(selector);
		}
		output.setEditable(false);
		setResizable(false);
	}
	//handles calling methods from Binary Tree
	private ActionListener selector = event -> {
		try {
			switch ((event.getActionCommand())){
				case "Make Tree":
					inputTree = new BinaryTree(input.getText());
					output.setText(inputTree.toString());
					break;
				case "Is Balanced?":
					output.setText(String.valueOf(inputTree.isBalanced()));
					break;
				case "Is Full?":
					output.setText(String.valueOf(inputTree.isFull()));
					break;
				case "Is Proper?":
					output.setText(String.valueOf(inputTree.isProper()));
					break;
				case "Height":
					output.setText(String.valueOf(inputTree.height()));
					break;
				case "Nodes":
					output.setText(String.valueOf(inputTree.nodes()));
					break;
				case "Inorder":
					output.setText(inputTree.inOrder());
					break;
			}
		//Sends exceptions to InvalidTreeSyntax
		}catch (InvalidTreeSyntax except) {
			JOptionPane.showMessageDialog(getParent(),except.getMessage());
		}catch (IndexOutOfBoundsException indexExcept) {
			JOptionPane.showMessageDialog(getParent(),"No input given");
		}
	};
	//main method to call frame
	public static void main(String[] args){
		Project3 frame = new Project3();
		frame.setVisible(true);
	}

}