//Name: Dalton Coughlin
//Project Name: Project 2
//File Name: Polynomial.java
//Date: 12/5/2020
//Description: This script is uses for comparing polynomials, building terms, iterating through terms, and output to user
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
public class Polynomial implements Iterable<Polynomial.Term>, Comparable<Polynomial> {
	
	Comparator<Polynomial> compare;
	//term to be set and called throughout methods
	private Term term;

//method for putting Polynomials from the file into linked list using the createTerm method
public Polynomial(String fromFile) {
	term = null; 
	Scanner termScanner = new Scanner(fromFile);
	//grabs next double and int to build the polynomial and throws them into linked list using createTerm method
	try{
		while(termScanner.hasNext()){
			createTerm(termScanner.nextDouble(), termScanner.nextInt());
		}
	//exception for incorrect inputs
	} catch (Exception ex){
		throw new InvalidPolynomialSyntax("Incorrect inputs");
	}
}

//adds terms of the poly list to the Term constructor 
public void createTerm(double coefficient, int exponent ){
	//checks if expontent is negative
	if (exponent < 0){
		throw new InvalidPolynomialSyntax("No negative exponents");
	}
	Term current = term;
	//builds term object if at start
	if(current == null){
		term = new Term(coefficient, exponent);
		term.next = null;
	} else { 
		while(current.next != null){
		current = current.next;
	}	
		current.next = new Term(coefficient, exponent);// builds Term
	}

}
//method to compare two terms coefficent and exponents
@Override
public int compareTo(Polynomial otherPoly) {
	//builds terms to compare to each other
	Term thisPolynomialTerm = this.term;
	Term otherPolynomialTerm = otherPoly.term;
	// as long as both terms are not null runs the loop
	while (thisPolynomialTerm != null && otherPolynomialTerm != null){
		//checks if the expontent are not equal to each other
		if (thisPolynomialTerm.getExponent() != otherPolynomialTerm.getExponent()){
			return thisPolynomialTerm.getExponent() - otherPolynomialTerm.getExponent();
		//checks if cofficents are not equal to each other and returns negative one if otherPolynomialTerm is greater than and postive one if otherPolynomialTerm is less than
		}else if(thisPolynomialTerm.getCoefficient() != otherPolynomialTerm.getCoefficient()) {
			if(otherPolynomialTerm.getCoefficient()> thisPolynomialTerm.getCoefficient()){
				return -1;
		}else if(otherPolynomialTerm.getCoefficient()< thisPolynomialTerm.getCoefficient()){
			return 1;
		}
		}
		//grabs next in poly terms
		thisPolynomialTerm = thisPolynomialTerm.getNext();
		otherPolynomialTerm = otherPolynomialTerm.getNext();
	}
	// if both terms are null, return 0
	if (thisPolynomialTerm == null && otherPolynomialTerm == null){
		return 0;
	}
	if (thisPolynomialTerm == null){
		return -1;
	}else {
		return 1;
	}
}
//thorws polynomials from polynomialList and checks if the expontents are greater for weakorder
public int compareEx(Polynomial polynomial) {
	//builds term objects
	
	Term thisPolynomialTerm = this.term;
	Term otherPolynomialTerm = polynomial.term;
	//checks if terms are null
	while(thisPolynomialTerm != null && otherPolynomialTerm != null) {
		//checks if expontents are not equal to each other 
		if (thisPolynomialTerm.getExponent() != otherPolynomialTerm.getExponent()) {
			return thisPolynomialTerm.getExponent() - otherPolynomialTerm.getExponent();
		}else {
			thisPolynomialTerm = thisPolynomialTerm.getNext();
			otherPolynomialTerm = otherPolynomialTerm.getNext();
		}
	}
	if(thisPolynomialTerm == null && otherPolynomialTerm == null){
			return 0;
	}
	if (otherPolynomialTerm == null){
		return 1;
	}else {
		return -1;
	}
}
//method to build string 
@Override
public String toString() {
	StringBuilder createString = new StringBuilder();
	//builds string if is not negative and if is negative adds negative sign
	if (term.coefficient > 0){
		createString.append(term.toString());
	}else{
		createString.append(" - ").append(term.toString());
	}
	for(Term temp = term.next; temp != null; temp = temp.next) {
		if (temp.coefficient < 0) {
			createString.append(" - ").append(temp.toString());
		} else {
			createString.append(" + ").append(temp.toString());
		}
		}
			return createString.toString();

}

//overridden method to iterate through Term object
@Override
public Iterator<Term> iterator() {
	return new Iterator() {

		private Term current = getTerm();
		//checks if current and next is not null
		@Override
		public boolean hasNext() {
			return current != null && current.getNext() != null;
		}
		//returns next item in the Term data
		@Override
		public Term next() {
			Term data = current;
			current = current.next;
			return data;
		}
	};
}



//constructor for polynomials 
static class Term{
	private double coefficient;
	private int exponent;
	private Term next;
	//Term object
	private Term(double c, int e) {
		coefficient = c;
		exponent = e;
		next = null;
	}
	//returns the coefficient
	private double getCoefficient(){
		return this.coefficient;
	}
	//returns the exponent of the term
	private int getExponent(){
		return this.exponent;
	}
	private Term getNext(){
		return next;
	}
	//method to build string using the Term object
	@Override
	public String toString(){
		String term = String.format("%.1f", Math.abs(coefficient));
		//if no expontent return just coefficent
		if (exponent == 0) { 
			return term;
		//adds x to end of term if exponent equals one
		}else if(exponent == 1){ 
			return term + "x";
		} else{
			return term + "x^" + exponent;
		}
	}
}
//returns term of a term
private Term getTerm() {
	return term;
	}
}