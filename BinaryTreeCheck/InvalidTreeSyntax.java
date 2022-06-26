//Name: Dalton Coughlin
//Project Name: Project 3
//File Name: InvalidTreeSyntax.java
//Date: 12/7/2020
//Description: This script is for handling invalid tree syntax errors from tree syntax
public class InvalidTreeSyntax extends Exception{

	public InvalidTreeSyntax(){
		super();
	}
	public InvalidTreeSyntax(String message){
			super(message);
	}
}