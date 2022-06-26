//Name: Dalton Coughlin
//Project Name: Project 2
//File Name: Project2.java
//Date: 12/5/2020
//Description: This script is used for inputing a file, inputing list into a polynomial list, and checking if is weakorder
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.*;
public class Project2 {

//new Arraylist for the polynomials being entered
private static List<Polynomial> polynomialList = new ArrayList<>();


//main run function
public static void main(String[] args) {
processpolynomialList();
}
//Reads file into Arraylist and checks if file is empty or if the file is not found
public static ArrayList<String> fromFile() {
	ArrayList<String> inputList = new ArrayList<>();
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
	int response = fileChooser.showOpenDialog(null);
	if (response == JFileChooser.APPROVE_OPTION){
		File file = fileChooser.getSelectedFile();
		try {
			Scanner fileIn = new Scanner(file);
			if (file.length() != 0){
				if (file.isFile()){
					while (fileIn.hasNextLine()){
						String inputItem = fileIn.nextLine();
						inputList.add(inputItem);
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(),"File is empty");
			}
		}catch(FileNotFoundException noFile){
			JOptionPane.showMessageDialog(new JFrame(),"File is not found");
		}
	}
	return inputList;

}
//Checks if list is in weakorder
public static boolean weakOrder( List<Polynomial> polynomialList){
	boolean weakOrder = true;
	int i = 0;
	//sets variable to previous variable in list
	Polynomial previousEx = polynomialList.get(polynomialList.size()-1);
	if (polynomialList.size() == 2){
		i = 0;
	}
	else{
		i = polynomialList.size()-2;
	}
	//loops through polynomialList and compares the expontents, uses compareTo method in Polynomial file
	while(i >= 0){
		if (previousEx.compareEx(polynomialList.get(i)) < 0){
			weakOrder = false;
		}
		i--;
	}
	return weakOrder;
}
//method to process input list to the polynomiallist and checking if the list is ordered or in weakorder
public static void processpolynomialList(){
	try {
		ArrayList<String> arrayList = fromFile();
		for (String element : arrayList) {
			Polynomial poly = new Polynomial(element);
			System.out.println(poly);
			polynomialList.add(poly);
	}
	}catch (InvalidPolynomialSyntax message){
		JOptionPane.showMessageDialog(new JFrame(), message.getMessage());
	}
	//throws list to method in Orderedlist and checks if list is sorted
	System.out.println("Strong Ordered: " + OrderedList.checkSorted(polynomialList));
	//throws list to weakOrder method to see if it's weakorder
	System.out.println("Weak Ordered: " + weakOrder(polynomialList));
	
}
}


