/*
* File: Homework 1
* Author: Dalton Coughlin
* Date: January 26, 2020
* Purpose: To Calculate Average of three test scores and convert a celsius tempature to farenheight
*/
// Import statements
import java.util.Scanner;
public class QuizAverage {
	public static void main(String[] args) { 
		// Variables to hold values
		int emplID = 0;
		float quizScoreOne = 0;
		float quizScoreTwo = 0;
		float quizScoreOne = 0;
		int age = 0;
		float tempature = 0;
		// Use the Scanner class to input data for the previous variables
		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter your Student EMPID (0 - 999999): ");
		// the nextFloat() method scans the next float value which is repeated for each input
		emplID = scannerIn.nextFloat();
		
		
		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter your quiz1 percentage score (0.0 – 100.0): ");
		quizScoreOne = scannerIn.nextFloat();

 		
		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter your quiz2 percentage score (0.0 – 100.0): ");
 		quizScoreTwo = scannerIn.nextFloat();

		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter your quiz3 percentage score (0.0 – 100.0): ");
 		quizScoreOne = scannerIn.nextFloat();
		
		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter your age in months (0-1440): ");
 		age= scannerIn.nextInt();

		Scanner scannerIn = new Scanner(System.in);
		System.out.println("Enter the current Temperature in degrees Celsius: ");
 		tempature = scannerIn.nextFloat();

	}
}