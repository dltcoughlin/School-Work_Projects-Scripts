/* File: BubbleSort.java
 Author: Dalton Coughlin
 Date: 4/12/2022
 Purpose: Recursive bubble sort and iteartive bubble sort outputting to csv
 */

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public class BubbleSort
{
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static int Count = 0;
    //iterative bubble sort
    static void iterativeBubbleSort(int arr[], int n)
    { 
        int temp = 0;  
        for(int i=0; i < n; i++){  
            for(int j=1; j < (n-i); j++){  
                 if(arr[j-1] > arr[j]){  
                    BubbleSort.Count += 1;
                     temp = arr[j-1];  
                    arr[j-1] = arr[j];  
                    arr[j] = temp;  
                 }  
             }              
        }  
    }
    public static boolean isSorted(int[] arr) 
    {
        for (int i=0; i<arr.length-1; i++){
            if (arr[i] > arr[i+1]) {
                return false;
            }
        }
        return true;
    }
    //recursive bubblle sort
    static void recursiveBubbleSort(int arr[], int n)
    {
        if (n == 1)
            return;
        for (int i=0; i<n-1; i++){
            if (arr[i] > arr[i+1])
            {
                BubbleSort.Count += 1;
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        recursiveBubbleSort(arr, n-1);
    }
    //getting mean of array
    static double getAverage(int arr[]){  
        double total = 0;      
        for(int i=0; i< arr.length; i++){
        	total = total + arr[i];
        }
        double average = total / arr.length;
        return average;
    }
    //coefficent of variance calc
    static double getCoefficent(int arr[], double mean){
        double std = 0;
        for(int i=0; i< arr.length; i++){
        	std += Math.pow(arr[i]-mean,2);
        }
        return Double.parseDouble(df.format(Math.sqrt(std/(arr.length-1))/mean*100));
    }
    //unsorted expection
    static class UnsortedException extends Exception
{
      // Parameterless Constructor
      public UnsortedException() {}

      // Constructor that accepts a message
      public UnsortedException(String message)
      {
         super(message);
      }
 }
 //for JVM warm up
    public void m() {
    }
    protected static void load() {
        for (int i = 0; i < 100000; i++) {
            BubbleSort dummy = new BubbleSort();
            dummy.m();
        }
    }
    public static void main(String[] args)
    {
        //jvm warm up
        load();
        double[][] recursiveOutputArray = new double[10][5];
        Random rd = new Random(); 
        int[] arr = new int[10];
        int[] recursiveAverageArray = new int[50];
        int[] recursiveTimeArray= new int[50];
        int index = 0;
        //loop for length of array
        for(int c = 10; c <= 100;){
            recursiveAverageArray = new int[50];
            recursiveTimeArray= new int[50];
            //going through 50 data sets
            for (int b = 0; b < 50; b++){
                BubbleSort.Count = 0;
                arr = new int[c];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rd.nextInt((100 - 0) + 1) + 0; 
                }
                //caculation of time and calling function for recursive
                long start = System.nanoTime();    
                recursiveBubbleSort(arr, arr.length);
                long elapsedTime = System.nanoTime() - start;
                //checking if array is sorted
                try{
                    if(isSorted(arr) == false)
                    {
                        throw new UnsortedException ("Array is Unsorted!") ;
                    }
                }catch(UnsortedException ex){
                        System.out.println("Array is Unsorted!");
                        System.exit(0);
                }
                //getting variables for array
                recursiveTimeArray[b] = (int)elapsedTime;
                recursiveAverageArray[b] = BubbleSort.Count;
            }
            //building output array
            recursiveOutputArray[index][0] = c;
            recursiveOutputArray[index][1] = getAverage(recursiveAverageArray);
            recursiveOutputArray[index][2] = getCoefficent(recursiveAverageArray,getAverage(recursiveAverageArray));
            recursiveOutputArray[index][3] = getAverage(recursiveTimeArray);
            recursiveOutputArray[index][4] = getCoefficent(recursiveTimeArray,getAverage(recursiveTimeArray));
            index+=1;
            c+=10;
        }
        double[][] iteartiveOutputArray = new double[10][5];
        int[] iteartiveAverageArray = new int[50];
        int[] iteartiveimeArray= new int[50];
        index = 0;
        for(int c = 10; c <= 100;){
            iteartiveAverageArray = new int[50];
            iteartiveimeArray= new int[50];
            for (int b = 0; b < 50; b++){
                BubbleSort.Count = 0;
                arr = new int[c];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rd.nextInt((100 - 0) + 1) + 0; 
                }
                long start = System.nanoTime();    
                iterativeBubbleSort(arr, arr.length);
                long elapsedTime = System.nanoTime() - start;
                arr[0] = 150;
                try{
                    if(isSorted(arr) == false)
                    {
                        throw new UnsortedException ("Array is Unsorted!") ;

                    }
                }catch(UnsortedException ex){
                        System.out.println("Array is Unsorted!");
                        System.exit(0);
                }
                iteartiveimeArray[b] = (int)elapsedTime;
                iteartiveAverageArray[b] = BubbleSort.Count;
            }
            iteartiveOutputArray[index][0] = c;
            iteartiveOutputArray[index][1] = getAverage(iteartiveAverageArray);
            iteartiveOutputArray[index][2] = getCoefficent(iteartiveAverageArray,getAverage(iteartiveAverageArray));
            iteartiveOutputArray[index][3] = getAverage(iteartiveimeArray);
            iteartiveOutputArray[index][4] = getCoefficent(iteartiveimeArray,getAverage(iteartiveimeArray));
            index+=1;
            c+=10;
        }
        //outputting recrusive array
        try {
            FileWriter myWriter = new FileWriter("recursiveBubble.csv");
            for (int i = 0; i < recursiveOutputArray.length; i++) {
                for (int b = 0; b < recursiveOutputArray[i].length; b++) {
                    myWriter.write(Double.toString(recursiveOutputArray[i][b]) +",");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //outputting iterative array
        try {
            FileWriter myWriter = new FileWriter("iterativeBubble.csv");
            for (int i = 0; i < iteartiveOutputArray.length; i++) {
                for (int b = 0; b < iteartiveOutputArray[i].length; b++) {
                    myWriter.write(Double.toString(iteartiveOutputArray[i][b]) +",");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
