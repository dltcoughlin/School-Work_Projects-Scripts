import java.awt.Component;

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.Scanner;

import java.util.*;

import java.lang.*;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JTextField;

public class GUICreator {

   private static Scanner scnr;

   private static File inp_file;

   private static String getLabel(String file_lines) {

       int cntr;

       file_lines.trim();

       for (cntr = 0; cntr < file_lines.length(); cntr++) {

             char tmp=file_lines.charAt(cntr);

             boolean b2 = Character.isLetter(tmp);

           if (!b2) {

               break;

           }

       }

       return file_lines.substring(0, cntr);

   }

   public static void main(String[] args) {

       String strng, lbl;

       try {

           inp_file = new File("Input.txt");

           scnr = new Scanner(inp_file);

           if (scnr.hasNextLine()) {

               strng = scnr.nextLine().trim();

               lbl = getLabel(strng);

               if (!lbl.equalsIgnoreCase("Window")) {

                   System.out.println("First label should be WINDOW");

                   return;

               }

               strng = strng.substring(lbl.length()).trim();

               JFrame frame = (JFrame) addCompntRec(strng, lbl);

               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               frame.setVisible(true);

           } else {

             System.out.println("Unknown Error");

           }

       } catch (FileNotFoundException exp) {

          

           System.out.println("File not found");

           exp.printStackTrace();

       } catch (Exception excep) {

           System.out.println("Unknown Error");

           excep.printStackTrace();

       }

   }

   private static ArrayList<Integer> getIntAray(String strng)

           throws Exception {

       int lp, lpj;

       ArrayList<Integer> result = new ArrayList<Integer>();

       for (lp = 0; lp < strng.length(); lp++) {

           for (lpj = lp; lpj < strng.length() && Character.isDigit(strng.charAt(lpj)); lpj++)

               ;

           if (lp != lpj) {

               result.add(Integer.parseInt(strng.substring(lp, lpj)));

           }

           lp = lpj;

       }

       return result;

   }

   private static Component addCompntRec(String strng, String lbl)

           throws Exception {

       String tempr;

       if (lbl.equalsIgnoreCase("Window")) {

           strng = strng.trim();

           JFrame frame;

           if (strng.charAt(0) == '"') {

               strng = strng.substring(1);

               tempr = strng.substring(0, strng.indexOf('\"'));

               frame = new JFrame(tempr);

               strng = strng.substring(strng.indexOf('"') + 1).trim();

           } else {

               frame = new JFrame("Default title");

           }

           if (strng.charAt(0) == '(') {

               tempr = strng.substring(0, strng.indexOf(')') + 1);

               strng = strng.substring(tempr.length()).trim();

               ArrayList<Integer> instr = getIntAray(tempr);

               if (instr.size() == 2) {

                   frame.setSize(instr.get(0), instr.get(1));

               }

           }

           tempr = getLabel(strng);

           strng = strng.substring(tempr.length()).trim();

           JPanel lpnl = new JPanel();

           if (tempr.equalsIgnoreCase("Layout")) {

               tempr = getLabel(strng);

               strng = strng.substring(tempr.length()).trim();

               if (tempr.equalsIgnoreCase("flow")) {

                   FlowLayout flw = new FlowLayout();

                   lpnl.setLayout(flw);

               }

               if (tempr.equalsIgnoreCase("grid")) {

                   if (strng.charAt(0) == '(') {

                       tempr = strng.substring(0, strng.indexOf(')') + 1);

                       strng = strng.substring(tempr.length()).trim();

                       ArrayList<Integer> instr = getIntAray(tempr);

                       GridLayout tmpLayout;

                       if (instr.size() == 2) {

                           tmpLayout = new GridLayout(instr.get(0),

                                   instr.get(1));

                           lpnl.setLayout(tmpLayout);

                       } else if (instr.size() == 4) {

                           tmpLayout = new GridLayout(instr.get(0),

                                   instr.get(1), instr.get(2), instr.get(3));

                           lpnl.setLayout(tmpLayout);

                       }

                   }

               }

           }

           while (true) {

               if (scnr.hasNextLine()) {

                   strng = scnr.nextLine().trim();

                   tempr = getLabel(strng);

                   if (tempr.equalsIgnoreCase("end")) {

                       break;

                   } else {

                       Component tmpCompnt = addCompntRec(

                               strng.substring(tempr.length()), tempr);

                       if (tmpCompnt != null) {

                           if (tmpCompnt.getClass() == frame.getClass()) {

                               System.out.println("Window cant be nested inside");

                           } else

                                {

                               lpnl.add(tmpCompnt);

                           }

                       }

                   }

               } else

                 {

                   System.out.println("Error in nesting");

                   break;

               }

           }

           frame.add(lpnl);

           return frame;

       }

       if (lbl.equalsIgnoreCase("panel")) {

           strng = strng.trim();

           JPanel pnel = new JPanel();

           tempr = getLabel(strng);

           strng = strng.substring(tempr.length()).trim();

           if (tempr.equalsIgnoreCase("Layout")) {

               tempr = getLabel(strng);

               strng = strng.substring(tempr.length()).trim();

               if (tempr.equalsIgnoreCase("flow")) {

                   FlowLayout flw = new FlowLayout();

                   pnel.setLayout(flw);

               }

               if (tempr.equalsIgnoreCase("grid")) {

                   if (strng.charAt(0) == '(') {

                       tempr = strng.substring(0, strng.indexOf(')') + 1);

                       strng = strng.substring(tempr.length()).trim();

                       ArrayList<Integer> instr = getIntAray(tempr);

                       GridLayout tmpLayout;

                       if (instr.size() == 2) {

                           tmpLayout = new GridLayout(instr.get(0),

                                   instr.get(1));

                           pnel.setLayout(tmpLayout);

                       } else if (instr.size() == 4) {

                           tmpLayout = new GridLayout(instr.get(0),

                                   instr.get(1), instr.get(2), instr.get(3));

                           pnel.setLayout(tmpLayout);

                       }

                   }

               }

           }

           while (true) {

               if (scnr.hasNextLine()) {

                   strng = scnr.nextLine().trim();

                   tempr = getLabel(strng);

                   if (tempr.equalsIgnoreCase("end")) {

                       break;

                   } else {

                       Component tmpCompnt = addCompntRec(

                               strng.substring(tempr.length()), tempr);

                       if (tmpCompnt != null) {

                           if (tmpCompnt.getClass() == new JFrame()

                                   .getClass()) {

                               System.out

                                       .println("Window cant be nested inside");

                           } else {

                               pnel.add(tmpCompnt);

                           }

                       }

                   }

               } else {

                   System.out.println("Error in nesting");

                   break;

               }

           }

           return pnel;

       }

       if (lbl.equalsIgnoreCase("button")) {

           strng = strng.trim();

           JButton button;

           if (strng.charAt(0) == '"') {

               strng = strng.substring(1);

               tempr = strng.substring(0, strng.indexOf('\"'));

               button = new JButton(tempr);

               strng = strng.substring(strng.indexOf('"') + 1).trim();

           } else {

               button = new JButton("Default title");

           }

           return button;

       }

     

       if (lbl.equalsIgnoreCase("lbl")) {

           strng = strng.trim();

           JLabel Label;

           if (strng.charAt(0) == '"') {

               strng = strng.substring(1);

               tempr = strng.substring(0, strng.indexOf('\"'));

               Label = new JLabel(tempr);

               strng = strng.substring(strng.indexOf('"') + 1).trim();

           } else {

               Label = new JLabel("Default title");

           }

           return Label;

       }

     

       if (lbl.equalsIgnoreCase("textfield")) {

           strng =strng.trim();

           ArrayList< Integer> li = getIntAray(strng);

           JTextField field = new JTextField(li.get(0));

           return field;

       }

       return null;

   }

}