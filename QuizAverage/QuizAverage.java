/*
* File: Homework 1
* Author: Dalton Coughlin
* Date: January 26, 2020
* Purpose: To Calculate Average of three test scores and convert a celsius tempature to farhenheit
*/
// Import statements
import java.util.Scanner;
public class QuizAverage {
	public static void main(String[] args) { 
		// Variables to hold values
		int emplID = 0;
		float quizScoreOne = 0;
		float quizScoreTwo = 0;
		float quizScoreThree = 0;
		int ageInMonths = 0;
		float temperature = 0;
		float celsiusTemp= 0;
		float fahrenheitTemp = 0;
		//Implemting Line Break
		char degreeType = 'f';
		String newline = System.getProperty("line.separator");
		// Use the Scanner class to input data for the previous variables
		Scanner scannerIn = new Scanner(System.in);
		
		\\Prompting user for inputs which is repeated per input
		System.out.print("Enter your Student EMPID (0 - 999999): ");
		// the nextInt() method scans the next Int value which is repeated for each input
		emplID = scannerIn.nextInt();
		// Checking if input is within parameters given, if not reprompts user to enter again, repeated throughout inputs
		while ((emplID < 0) || (emplID >999999)) {
			System.out.print("Invalid ID was entered, please enter again: ");
			emplID= scannerIn.nextInt();
		}
		
		System.out.print("Enter your quiz1 percentage score (0.0 - 100.0): ");
 		quizScoreOne = scannerIn.nextFloat();
		while ((quizScoreOne < 0.0) || (quizScoreOne >100.0)) {
			System.out.print("Invalid Quiz Score 1 was entered, please enter again: ");
			quizScoreOne= scannerIn.nextFloat();
		}

 		
		System.out.print("Enter your quiz2 percentage score (0.0 - 100.0): ");
 		quizScoreTwo = scannerIn.nextFloat();
		while ((quizScoreTwo < 0.0) || (quizScoreTwo >100.0)) {
			System.out.print("Invalid Quiz Score 2 was entered, please enter again: ");
			quizScoreTwo= scannerIn.nextFloat();
		}

		System.out.print("Enter your quiz3 percentage score (0.0 - 100.0): ");
 		quizScoreThree = scannerIn.nextFloat();
		while ((quizScoreThree < 0.0) || (quizScoreThree >100.0)) {
			System.out.print("Invalid Quiz Score 3 was entered, please enter again: ");
			quizScoreThree= scannerIn.nextFloat();
		}
		
		System.out.print("Enter your age in months (0 - 1440): ");
 		ageInMonths= scannerIn.nextInt();
		while ((ageInMonths < 0) || (ageInMonths >1440)) {
			System.out.print("Invalid age was entered, please enter again: ");
			ageInMonths= scannerIn.nextInt();
		}
		
		System.out.print("Enter c to convert celsius to fahrenheit or f to convert fahrenheit to c: ");
		degreeType = scannerIn.next().charAt(0);
		while ((degreeType != 'f') && (degreeType != 'c')){
			System.out.print("Invalid char was entered, please enter again: ");
			degreeType=scannerIn.next().charAt(0);
		}
		
		System.out.print("Enter the current temperature in degrees: ");
 		temperature = scannerIn.nextFloat();
		
		// Averaging Quiz Scores of the three inputs
		float averageQuizScores = (quizScoreOne + quizScoreTwo + quizScoreThree)/3;
		
		// Cacluating how many years from the input of months given
		int ageInYears = ageInMonths/12;
		int leftOverMonths= ageInMonths%12;
		
		//Cacluating temperatures
		if (degreeType == 'c') {
			celsiusTemp = temperature;
			fahrenheitTemp = (temperature * 9/5) + 32;
		}
		else {
			fahrenheitTemp=temperature;
			celsiusTemp=(temperature - 32) * 5/9;
		}

		// Outputting the variables given
		System.out.println(newline+"** Thank You **");
		System.out.println(newline+"Employee ID");
		System.out.println("Student EMPLID: " + emplID +newline); 
		
		System.out.println("Quiz Scores");
		System.out.println("Quiz 1 Score: " + quizScoreOne);
		System.out.println("Quiz 2 Score: " + quizScoreTwo);
		System.out.println("Quiz 2 Score: " + quizScoreThree);
		System.out.println("Average Quiz Score: " + averageQuizScores );
		// Checking to see if the quiz score average was passing
		if (averageQuizScores > 70) {
			System.out.println("You Passed!"+ newline);
		}
		else {
			System.out.println("You Failed."+ newline);
		}
		System.out.println("Age");
		\\Checking whether the age in months entered has a remiander
		if (leftOverMonths == 0) {
			System.out.println("Age in years: " + ageInYears );
			System.out.println("Age in months: " + ageInMonths + newline);
		}
		else {
			System.out.println("Age in years: " + ageInYears );
			System.out.println("Age in months: " + ageInMonths);
			System.out.println("Age in years and months: " + ageInYears + " year & " + leftOverMonths +" months" + newline);
		}

		System.out.println("Temperature");
		System.out.println("Temperature in celsius: " + celsiusTemp + "\u00B0");
		System.out.println("Temperature in Fahrenheit: " + fahrenheitTemp + "\u00B0");
		
		

	}
}