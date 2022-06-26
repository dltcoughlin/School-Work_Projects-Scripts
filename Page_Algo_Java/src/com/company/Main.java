package com.company;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> referenceString = new ArrayList<Integer>();
        ArrayList<Integer> physicalFrames = new ArrayList<Integer>();
        ArrayList<String> historyOutput;
        int i = 0;
        int physicalFrameCount = 5;
        int faults = 0;
        int arrayCount = 0;
        int c = 0;
        int checkRemove = 0;
        int removeFrame = 0;
        Hashtable<Integer,Integer> frameDict =new Hashtable<Integer,Integer>();
        System.out.println("Physical Page Count: " + physicalFrameCount);
        while (true)
        {
            c= 0;
            historyOutput = new ArrayList<String>();
            while (c < physicalFrameCount){
                historyOutput.add("Physical frame " + c + " | ");
                c++;
            }
            historyOutput.add("Page Faults      | " );
            historyOutput.add("Victim Frames    | ");
            System.out.println();
            System.out.println("Please choose from the following options:");
            System.out.println("0 - Exit");
            System.out.println("1 - Read reference string");
            System.out.println("2 - Generate reference string");
            System.out.println("3 - Display current reference string");
            System.out.println("4 - Simulate FIFO");
            System.out.println("5 - Simulate OPT");
            System.out.println("6 - Simulate LRU");
            System.out.println("7 - Simulate LFU");
            System.out.print("Please enter a selection: ");
            Scanner myObj = new Scanner(System.in);
            String selection = myObj.nextLine();
            switch (selection) {
                case "0":
                    System.out.println("Thank you for using the application.");
                    System.exit(0);
                    break;
                case "1":
                    System.out.println("Please enter reference string seperated by spaces: ");
                    myObj = new Scanner(System.in);
                    selection = myObj.nextLine();
                    String[] sep = selection.split("\\s+");
                    i = 0;
                    int num = 0;
                    referenceString = new ArrayList<Integer>();
                    while (i < sep.length) {
                        try{
                            num = Integer.parseInt(sep[i]);
                        }catch(Exception e){
                            System.out.println("Found a character that is not a number, please enter numbers 0-9");
                            referenceString = new ArrayList<Integer>();
                            break;
                        }
                        if (num < 10) {
                            referenceString.add(num);
                        } else {
                            System.out.println("Number must be within 0-9");
                            break;
                        }
                        i++;
                    }
                    System.out.print("Reference String: " + referenceString);
                    break;
                case "2":
                    System.out.print("Please enter how long for the reference string: ");
                    myObj = new Scanner(System.in);
                    selection = myObj.nextLine();
                    int min = 0;
                    int max = 9;
                    i = 0;
                    int randomNum;
                    referenceString = new ArrayList<Integer>();
                    while (i < Integer.parseInt(selection)) {
                        randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                        referenceString.add(randomNum);
                        i++;
                    }
                    System.out.print("Reference String: " + referenceString);
                    break;
                case "3":
                    System.out.print("Reference String: " + referenceString);
                    break;
                case "4":
                    if(referenceString.isEmpty()){
                        System.out.println("Please input or generate reference string first!");
                        break;
                    }
                    System.out.println("First In First Out");
                    System.out.println("Reference String: " + referenceString);
                    arrayCount = referenceString.size();
                    faults = 0;
                    i = 0;

                    physicalFrames = new ArrayList<Integer>();
                    while (i < arrayCount) {
                        System.out.println("************** FIFO STEP " + (i+1) + " **************");
                        System.out.println();
                        System.out.print("Reference String | ");
                        for (int b = 0; b < referenceString.size(); b++) {
                            System.out.print(referenceString.get(b) + " | ");
                        }
                        System.out.println();
                        if (physicalFrames.contains(referenceString.get(i))) {
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "  | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                        } else if (physicalFrames.size() < physicalFrameCount) {
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            physicalFrames.add(referenceString.get(i));
                            faults++;
                        } else {
                            faults++;
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + physicalFrames.get(0) +" | ");
                            physicalFrames.remove(0);
                            physicalFrames.add(referenceString.get(i));
                        }
                        for (int b = 0; b < physicalFrameCount; b++) {
                            try{
                                historyOutput.set(b, historyOutput.get(b) + physicalFrames.get(b) + " | ");
                            }catch(Exception e){
                                historyOutput.set(b, historyOutput.get(b) + "  | ");
                            }
                        }
                        for (int b = 0; b < historyOutput.size(); b++) {
                            System.out.println(historyOutput.get(b));
                        }
                        System.out.print("Please press enter to continue.");
                        myObj.nextLine();
                        i++;
                    }

                    System.out.println(physicalFrames);
                    System.out.println("Faults: "  + faults);
                    break;
                case "5":
                    if(referenceString.isEmpty()){
                        System.out.println("Please input or generate reference string first!");
                        break;
                    }
                    System.out.println("Optimal Page Replacement");
                    arrayCount = referenceString.size();
                    faults = 0;
                    i = 0;
                    removeFrame = 0;
                    physicalFrames = new ArrayList<Integer>();
                    frameDict =new Hashtable<Integer,Integer>();
                    int ref = 0;
                    for (int b = 0; b < arrayCount; b++) {
                        ref = referenceString.get(b);
                        if (frameDict.containsKey(ref)){
                            frameDict.put(referenceString.get(b), frameDict.get(referenceString.get(b))+1);
                        }else{
                            frameDict.put(referenceString.get(b), 1);
                        }
                    }
                    while (i < arrayCount) {
                        System.out.println("************** OPT STEP " + (i+1) + " **************");
                        System.out.println();
                        System.out.print("Reference String | ");
                        for (int b = 0; b < referenceString.size(); b++) {
                            System.out.print(referenceString.get(b) + " | ");
                        }
                        System.out.println();
                        if (physicalFrames.contains(referenceString.get(i))){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "  | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                        }else if (physicalFrames.size() < physicalFrameCount){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            physicalFrames.add(referenceString.get(i));
                            faults++;
                        }else{
                            checkRemove = 0;
                            for (int t = 0; t < physicalFrames.size(); t++) {
                                for (int b = i; b < referenceString.size(); b++) {
                                    if(referenceString.get(b) == (physicalFrames.get(t))){

                                        if(checkRemove < b){
                                            removeFrame = physicalFrames.get(t);
                                        }
                                        break;
                                    }else if(b == referenceString.size()-1){
                                        checkRemove = 10000;
                                        removeFrame = physicalFrames.get(t);
                                    }
                                }
                            }
                            faults++;
                            physicalFrames.remove(physicalFrames.indexOf(removeFrame));
                            physicalFrames.add(referenceString.get(i));
                            frameDict.put(removeFrame, frameDict.get(removeFrame)-1);
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + removeFrame +" | ");
                        }
                        for (int b = 0; b < physicalFrameCount; b++) {
                            try{
                                historyOutput.set(b, historyOutput.get(b) + physicalFrames.get(b) + " | ");
                            }catch(Exception e){
                                historyOutput.set(b, historyOutput.get(b) + "  | ");
                            }
                        }
                        for (int b = 0; b < historyOutput.size(); b++) {
                            System.out.println(historyOutput.get(b));
                        }
                        System.out.print("Please press enter to continue.");
                        myObj.nextLine();
                        i++;
                    }
                    System.out.println("Total Faults: "  + faults);
                    break;
                case "6":
                    if(referenceString.isEmpty()){
                        System.out.println("Please input or generate reference string first!");
                        break;
                    }
                    System.out.println("Least Recently Used");
                    arrayCount = referenceString.size();
                    faults = 0;
                    i = 0;
                    removeFrame = -1;
                    physicalFrames = new ArrayList<Integer>();
                    frameDict =new Hashtable<Integer,Integer>();
                    while (i < arrayCount) {
                        System.out.println("************** LRU STEP " + (i+1) + " **************");
                        System.out.println();
                        System.out.print("Reference String | ");
                        for (int b = 0; b < referenceString.size(); b++) {
                            System.out.print(referenceString.get(b) + " | ");
                        }
                        System.out.println();
                        if (physicalFrames.contains(referenceString.get(i))){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "  | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            frameDict.put(referenceString.get(i),0);
                        }else if (physicalFrames.size() < physicalFrameCount){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            physicalFrames.add(referenceString.get(i));
                            frameDict.put(referenceString.get(i), 0);
                            faults++;
                        }else{
                            Enumeration<Integer> enumeration = frameDict.keys();
                            checkRemove = 0;
                            while (enumeration.hasMoreElements()) {
                                int key = enumeration.nextElement();
                                if(frameDict.get(key) > checkRemove){
                                    removeFrame = key;
                                    checkRemove = frameDict.get(key);
                                }
                            }
                            faults++;
                            physicalFrames.remove(physicalFrames.indexOf(removeFrame));
                            frameDict.remove(removeFrame);
                            physicalFrames.add(referenceString.get(i));
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + removeFrame +" | ");
                            frameDict.put(referenceString.get(i), 0);
                        }
                        Enumeration<Integer> enumeration = frameDict.keys();
                        while (enumeration.hasMoreElements()) {
                            int key = enumeration.nextElement();
                            frameDict.put(key,frameDict.get(key)+1);
                        }
                        for (int b = 0; b < physicalFrameCount; b++) {
                            try{
                                historyOutput.set(b, historyOutput.get(b) + physicalFrames.get(b) + " | ");
                            }catch(Exception e){
                                historyOutput.set(b, historyOutput.get(b) + "  | ");
                            }
                        }
                        for (int b = 0; b < historyOutput.size(); b++) {
                            System.out.println(historyOutput.get(b));
                        }
                        System.out.print("Please press enter to continue.");
                        myObj.nextLine();
                        i++;
                    }
                    System.out.println("Total Faults: "  + faults);
                    break;
                case "7":
                    if(referenceString.isEmpty()){
                        System.out.println("Please input or generate reference string first!");
                        break;
                    }
                    System.out.println("Least Frequently Used");
                    arrayCount = referenceString.size();
                    faults = 0;
                    i = 0;
                    physicalFrames = new ArrayList<Integer>();
                    frameDict =new Hashtable<Integer,Integer>();
                    Hashtable<Integer,Integer> countDict =new Hashtable<Integer,Integer>();
                    while (i < arrayCount) {
                        System.out.println("************** LFU STEP " + (i+1) + "**************");
                        System.out.println();
                        System.out.print("Reference String | ");
                        for (int b = 0; b < referenceString.size(); b++) {
                            System.out.print(referenceString.get(b) + " | ");
                        }
                        System.out.println();
                        if (physicalFrames.contains(referenceString.get(i))){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "  | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            frameDict.put(referenceString.get(i),frameDict.get(referenceString.get(i))+1);
                            countDict.put(referenceString.get(i), 0);
                        }else if (physicalFrames.size() < physicalFrameCount){
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + "  | ");
                            physicalFrames.add(referenceString.get(i));
                            frameDict.put(referenceString.get(i), 1);
                            countDict.put(referenceString.get(i), 0);
                            faults++;
                        }else{
                            Enumeration<Integer> enumeration = countDict.keys();
                            checkRemove = 10000;
                            removeFrame = -1;
                            while (enumeration.hasMoreElements()) {
                                int key = enumeration.nextElement();
                                if(frameDict.get(key) <= checkRemove){
                                    if (removeFrame == -1){
                                        removeFrame = key;
                                        checkRemove = frameDict.get(key);
                                    }else if (countDict.get(key) > countDict.get(removeFrame)){
                                        removeFrame = key;
                                        checkRemove = frameDict.get(key);
                                    }
                                }
                            }
                            faults++;
                            physicalFrames.remove(physicalFrames.indexOf(removeFrame));
                            physicalFrames.add(referenceString.get(i));
                            historyOutput.set(historyOutput.size()-2, historyOutput.get(historyOutput.size()-2) + "F | ");
                            historyOutput.set(historyOutput.size()-1, historyOutput.get(historyOutput.size()-1) + removeFrame +" | ");
                            if (frameDict.containsKey(referenceString.get(i))){
                                frameDict.put(referenceString.get(i), frameDict.get(referenceString.get(i))+1);
                            }else{
                                frameDict.put(referenceString.get(i), 1);
                            }
                            countDict.remove(removeFrame);
                            countDict.put(referenceString.get(i), 0);
                        }
                        for (int b = 0; b < physicalFrameCount; b++) {
                            try{
                                historyOutput.set(b, historyOutput.get(b) + physicalFrames.get(b) + " | ");
                            }catch(Exception e){
                                historyOutput.set(b, historyOutput.get(b) + "  | ");
                            }
                        }
                        Enumeration<Integer> enumeration = countDict.keys();
                        while (enumeration.hasMoreElements()) {
                            int key = enumeration.nextElement();
                            countDict.put(key,countDict.get(key)+1);
                        }
                        for (int b = 0; b < historyOutput.size(); b++) {
                            System.out.println(historyOutput.get(b));
                        }
                        System.out.print("Please press enter to continue.");
                        myObj.nextLine();
                        i++;
                    }
                    System.out.println("Total Faults: "  + faults);
                    break;
                default:
                    break;
            }
        }
    }
}
