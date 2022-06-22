/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Project4.java
// Author: Dalton Coughlin
// Date: 5/9/2020
// Purpose: Main frame methods and object, executable file of frame
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.JOptionPane;
import java.util.*; 
import java.util.concurrent.*; 
class ProjectFrame extends JPanel { 
   //setting up treemap for real estate database
   TreeMap<Integer,Property> realEstateDatabase = new TreeMap<Integer,Property>();
   //arrays for combo boxes to pull from
   String[] processChoices = { "Insert","Delete","Find" };
   Status[] changeStatusChoices = { Status.FOR_SALE,Status.UNDER_CONTRACT,Status.SOLD };
   //labels for text fields
   private final JLabel transactionNoLabel = new JLabel("Transaction No: ", JLabel.LEFT);
   private final JLabel addressLabel = new JLabel("Address: ", JLabel.LEFT);
   private final JLabel bedroomsLabel = new JLabel("Bedrooms: ", JLabel.LEFT);
   private final JLabel squareFootageLabel = new JLabel("Square Footage: ", JLabel.LEFT);
   private final JLabel priceLabel = new JLabel("Price: ", JLabel.LEFT);
   //text fields for inputs
   private JTextField transactionNoTxt = new JTextField("",12);
   private JTextField addressTxt = new JTextField("",8);
   private JTextField bedroomsTxt = new JTextField("",8);
   private JTextField squareFootageTxt = new JTextField("",8);
   private JTextField priceTxt = new JTextField("",8);
   //buttons for frame to draw shape
   private final JButton processButton = new JButton("Process");
   private final JButton changeStatusButton = new JButton("Change Status");
   //main frame object
   public ProjectFrame () {
       //comboboxes for selectoins
      JComboBox<String> choicesBox = new JComboBox<>(processChoices);
      JComboBox<Status> changeStatusBox = new JComboBox<>(changeStatusChoices);
      //create the upper/inner/lower panel and set layouts
      JPanel topPanel = new JPanel();   
      JPanel lowerPanel = new JPanel(); 
      //setting gird layouts for left and lower panel
      topPanel.setLayout(new GridLayout(6,4,4,10)); 
      lowerPanel.setLayout(new GridLayout(2, 1,8,8)); 
      lowerPanel.setPreferredSize(new Dimension(275,50));
      //adding inputs to left panel
      topPanel.add(transactionNoLabel);
      topPanel.add(transactionNoTxt);
      topPanel.add(addressLabel);
      topPanel.add(addressTxt);
      topPanel.add(bedroomsLabel);
      topPanel.add(bedroomsTxt);
      topPanel.add(squareFootageLabel);
      topPanel.add(squareFootageTxt);
      topPanel.add(priceLabel);
      topPanel.add(priceTxt);
      lowerPanel.add(processButton);
      lowerPanel.add(choicesBox);
      lowerPanel.add(changeStatusButton);
      lowerPanel.add(changeStatusBox);
      //setting prefferedsize for initial right panel
      //adding panels to main frame
      add(topPanel, BorderLayout.NORTH);
      add(lowerPanel,BorderLayout.SOUTH);
      //grays out options when find and delete option is selected in combobox
      choicesBox.addActionListener((ActionEvent c) -> { {
            String choiceSelection=(String)choicesBox.getSelectedItem();
            switch(choiceSelection){
                case "Insert":
                    addressTxt.setText("");
                    bedroomsTxt.setText("");
                    squareFootageTxt.setText("");
                    priceTxt.setText("");
                    addressTxt.setEditable(true);
                    bedroomsTxt.setEditable(true);
                    squareFootageTxt.setEditable(true);
                    priceTxt.setEditable(true);
                    break;
                case "Find":
                    addressTxt.setText("");
                    bedroomsTxt.setText("");
                    squareFootageTxt.setText("");
                    priceTxt.setText("");
                    addressTxt.setEditable(false);
                    bedroomsTxt.setEditable(false);
                    squareFootageTxt.setEditable(false);
                    priceTxt.setEditable(false);
                    break;
                case "Delete":
                    addressTxt.setText("");
                    bedroomsTxt.setText("");
                    squareFootageTxt.setText("");
                    priceTxt.setText("");
                    addressTxt.setEditable(false);
                    bedroomsTxt.setEditable(false);
                    squareFootageTxt.setEditable(false);
                    priceTxt.setEditable(false);
                    break;
         }
      }});
      //process button action listener, used to interact with tree map
      processButton.addActionListener((ActionEvent e) -> {
         //getting selections from comboboxes
         String choiceSelection=(String)choicesBox.getSelectedItem();
         Status changeStatusSelection=(Status)changeStatusBox.getSelectedItem();
         //checking if transaction number is a integer
         try{
                Integer.parseInt(transactionNoTxt.getText());
         } 
         catch(Exception NumberFormatException){
               JOptionPane.showMessageDialog(null, "Transaction Number Not A Number!", "Error", JOptionPane.WARNING_MESSAGE);
         }
        Integer transactionNumber=Integer.parseInt(transactionNoTxt.getText());
        //switch to do commands based on selection of choice selection box
        switch(choiceSelection){
            //inserts information into real estae database tree map
            case "Insert":
                if(realEstateDatabase.get(transactionNumber) != null){
                    JOptionPane.showMessageDialog(null, "Transaction Number Already Entered!", "Error", JOptionPane.WARNING_MESSAGE);
                    break;
                }
                else{
                    try{
                        Property newProperty=new Property(addressTxt.getText(),Integer.parseInt(bedroomsTxt.getText()),Integer.parseInt(squareFootageTxt.getText()),Integer.parseInt(priceTxt.getText()));
                        newProperty.changeState(changeStatusSelection);
                        realEstateDatabase.put(transactionNumber,newProperty);
                        JOptionPane.showMessageDialog(null, "Successfully Entered Property!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    catch(Exception NumberFormatException){
                        JOptionPane.showMessageDialog(null, "Integer Fields Are Not A Number!", "Error", JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                        }
            //lookups property in tree map based on transaction number
            case "Find":
                if(realEstateDatabase.get(transactionNumber) != null){
                    JOptionPane.showMessageDialog(null, realEstateDatabase.get(transactionNumber).toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } 
                else{
                    JOptionPane.showMessageDialog(null, "Transaction Number Not Found!", "Error", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            //deletes property in tree map based on tranasctions number
            case "Delete":
                if(realEstateDatabase.get(transactionNumber) != null){
                    realEstateDatabase.remove(transactionNumber);
                    JOptionPane.showMessageDialog(null, "Successfully Deleted Property!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    break;
                } 
                else{
                    JOptionPane.showMessageDialog(null, "Transaction Number Not Found!", "Error", JOptionPane.WARNING_MESSAGE);
                    break;
                }
         }
         
      });
      //change status button action listener to change the status of property
      changeStatusButton.addActionListener((ActionEvent e) -> {
          Status changeStatusSelection=(Status)changeStatusBox.getSelectedItem();
          try{
                Integer.parseInt(transactionNoTxt.getText());
          } 
          catch(Exception NumberFormatException){
               JOptionPane.showMessageDialog(null, "Transaction Number Not A Number!", "Error", JOptionPane.WARNING_MESSAGE);
          }
          //changes status of property based on tranasctions number entered
          Integer transactionNumber=Integer.parseInt(transactionNoTxt.getText());
          Property changeProperty=realEstateDatabase.get(transactionNumber);
          changeProperty.changeState(changeStatusSelection);
          JOptionPane.showMessageDialog(null, "Successfully Changed Property!", "Success", JOptionPane.INFORMATION_MESSAGE);
          
      });
     }
}

//Main class to bring up Project 4 Frame
public class Project4 extends Frame {
   public Project4() {
      super("Real Estate Database",300,275);
      add(new ProjectFrame()); 
   }
   public static void main(String[] args) {
      Project4 scApp = new Project4();
      scApp.display();
      
     
      
   }
}
