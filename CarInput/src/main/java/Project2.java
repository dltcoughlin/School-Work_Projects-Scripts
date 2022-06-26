/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Project2.java
// Author: Dalton Coughlin
// Date: 4/11/2020
// Purpose: Main frame methods and object, executable file of frame
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
class ProjectFrame extends JPanel { 
   //create input/output components
   //Variables for array location and results string to be called
   private String results;
   private int arrayLoc = 0;
   //Radio buttons for selection of vechile
   private JRadioButton hybridButton   = new JRadioButton("Hybrid", true);
   private JRadioButton electricButton    = new JRadioButton("Electric");
   private JRadioButton otherButton = new JRadioButton("Other");
   //labels for text fields
   private final JLabel makeModelLabel = new JLabel("Make and Model", JLabel.LEFT);
   private final JLabel salesPriceLabel = new JLabel("Sales Price", JLabel.LEFT);
   private final JLabel mpgLabel = new JLabel("Miles per Gallon", JLabel.LEFT);
   private final JLabel poundsLabel = new JLabel("Weight in Pounds", JLabel.LEFT);
   //text fields for inputs
   private JTextField makeModelTxt = new JTextField("",12);
   private JTextField salesPriceTxt = new JTextField("",12);
   private JTextField mpgTxt = new JTextField("",10);
   private JTextField poundsTxt = new JTextField("",10);
   private JTextField resultTxt = new JTextField("",13);
   //buttons for frame to compute tax, clear fields, and display report
   private final JButton computeSalesTax = new JButton("Compute Sales Tax");
   private final JButton clearFields = new JButton("Clear Fields");
   private final JButton displayReport = new JButton("Display Report");
   //main frame object
   public ProjectFrame () {
      //array for dispaly report
      AutoMobile autoArray[]=new AutoMobile[5];
      
      //button group for hybrid,electric, and other button
      ButtonGroup bGroup = new ButtonGroup();
      JFrame frame = new JFrame("Error Message");
      bGroup.add(hybridButton);
      bGroup.add(electricButton);
      bGroup.add(otherButton);
      
      //create the upper/inner/lower panel and set layouts
      JPanel innerPanel = new JPanel();   
      JPanel upperPanel = new JPanel();
      JPanel lowerPanel = new JPanel(); 
      innerPanel.setLayout(new GridLayout(3, 3)); 
      upperPanel.setLayout(new GridLayout(2, 2,20,0)); 
      lowerPanel.setLayout(new GridLayout(2, 1,4,4)); 
      //Settings text fields to non-editable
      resultTxt.setEditable(false);
      poundsTxt.setEditable(false);
      //Adding labels and text fields to upper panel
      upperPanel.add(makeModelLabel);
      upperPanel.add(makeModelTxt);
      upperPanel.add(salesPriceLabel);
      upperPanel.add(salesPriceTxt);
      //adding labels and text fields to lower panel
      lowerPanel.add(computeSalesTax);
      lowerPanel.add(resultTxt);
      lowerPanel.add(clearFields);
      lowerPanel.add(displayReport);
      //set border for innerp panel
      innerPanel.setBorder(BorderFactory.createTitledBorder("Automobile Type"));
      //adding labels and text fields to inner panel
      innerPanel.add(hybridButton);
      innerPanel.add(mpgLabel);
      innerPanel.add(mpgTxt);
      innerPanel.add(electricButton);
      innerPanel.add(poundsLabel);
      innerPanel.add(poundsTxt);
      innerPanel.add(otherButton);

      //adding panels to main frame
      add(upperPanel, BorderLayout.NORTH);
      add(innerPanel, BorderLayout.SOUTH); 	
      add(lowerPanel, BorderLayout.SOUTH); 
      //button for clearing fields which calls clear field method
      clearFields.addActionListener((ActionEvent e) -> {
          clearFieldsMethod();
      });
      //hybrid button selection action
      hybridButton.addActionListener((ActionEvent e) -> {
          mpgTxt.setEditable(true);
          poundsTxt.setEditable(false);
          clearFieldsMethod();
      });
      //electric button selection action
      electricButton.addActionListener((ActionEvent e) -> {
          mpgTxt.setEditable(false);
          poundsTxt.setEditable(true);
          clearFieldsMethod();
      });
      //other button selection action
      otherButton.addActionListener((ActionEvent e) -> {
          mpgTxt.setEditable(false);
          poundsTxt.setEditable(false);
          clearFieldsMethod();
      });
      //display report displays each object in auto array
      displayReport.addActionListener((ActionEvent e) -> {
          for (int i=0; i<autoArray.length;i++){
              if (autoArray[i] == null){
                  
              }
              else{
                  System.out.println(autoArray[i].toString(autoArray[i]));
                  System.out.println("");
              }
          }
      });
      //Computes sales tax button, grabs text fields and button selection for type of car
      //Builds object on type of car, caculates sales tax, displays sales tax, and puts new object in array
      computeSalesTax.addActionListener((ActionEvent e) -> {
          try {
              Integer.parseInt(salesPriceTxt.getText());
          }
          catch(NumberFormatException a){
              JOptionPane.showMessageDialog(frame, "Not a integer in Sales Price field.");
              salesPriceTxt.setText("");
              
          }
          if (hybridButton.isSelected() == true){
              try{
                  Integer.parseInt(mpgTxt.getText());
              }
              catch(NumberFormatException aa){
                  JOptionPane.showMessageDialog(frame, "Not a integer in pounds field.");
                  mpgTxt.setText("");
              }
              try{
                  Integer.parseInt(mpgTxt.getText());
                  Hybrid hybridSet = new Hybrid(makeModelTxt.getText(),Integer.parseInt(salesPriceTxt.getText()),Integer.parseInt(mpgTxt.getText()));
                  results=hybridSet.salesTax(hybridSet);
                  resultTxt.setText(results);
                  autoArray[arrayLoc]=hybridSet;
                  arrayLocCalc();
                  
              }
              catch(NumberFormatException ab){
              }
              
          }
          else if(electricButton.isSelected() == true){
              try{
                  Integer.parseInt(poundsTxt.getText());
              }
              catch(NumberFormatException ba){
                  JOptionPane.showMessageDialog(frame, "Not a integer in pounds field.");
                  poundsTxt.setText("");
              }
              try{
                  
                  Electric electricSet = new Electric(makeModelTxt.getText(),Integer.parseInt(salesPriceTxt.getText()),Integer.parseInt(poundsTxt.getText()));
                  results=electricSet.salesTax(electricSet);
                  resultTxt.setText(results);
                  autoArray[arrayLoc]=electricSet;
                  arrayLocCalc();
              }
              catch(NumberFormatException bb){
              }
              
          }
          else if(otherButton.isSelected() == true){
              try{
                  AutoMobile autoSet = new AutoMobile(makeModelTxt.getText(),Integer.parseInt(salesPriceTxt.getText()));
                  results=autoSet.salesTax(autoSet);
                  resultTxt.setText(results);
                  autoArray[arrayLoc]=autoSet;
                  arrayLocCalc();
                    }
              catch(NumberFormatException c){
                  salesPriceTxt.setText("");
              }
          }
      });
   }
   // Clear fields method to be called
   public void clearFieldsMethod(){
        makeModelTxt.setText("");
        salesPriceTxt.setText("");
        mpgTxt.setText("");
        poundsTxt.setText("");
        resultTxt.setText("");
    }
   //Method to handle array location 
   public int arrayLocCalc(){
       if (arrayLoc == 4){
                      arrayLoc = 0;
                  }
                  else{
                    arrayLoc +=1;
                  }
       return arrayLoc;
   }
}
//Main class to bring up Project 2 Frame
public class Project2 extends Frame {
   public Project2() {
      super("Automobile Sales Tax Calculator",430, 260);
      add(new ProjectFrame()); 
   }
   public static void main(String[] args) {
      Project2 scApp = 
         new Project2();
      scApp.display();
   }
}

