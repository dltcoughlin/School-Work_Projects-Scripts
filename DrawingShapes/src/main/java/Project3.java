/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Project3.java
// Author: Dalton Coughlin
// Date: 4/27/2020
// Purpose: Main frame methods and object, executable file of frame
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import javax.swing.JOptionPane;
class ProjectFrame extends JPanel { 
   //arrays for comboboxes
   String[] shapeStrings = { "Oval","Rectangle" };
   String[] fillTypeStrings = {"Hollow", "Solid"};
   String[] colorStrings = {"Black","Red","Orange","Yellow","Green","Blue","Magenta"};
   //labels for text fields
   private final JLabel shapeTypeLabel = new JLabel("Shape Type", JLabel.LEFT);
   private final JLabel fillTypeLabel = new JLabel("Fill Type", JLabel.LEFT);
   private final JLabel colorLabel = new JLabel("Color", JLabel.LEFT);
   private final JLabel widthLabel = new JLabel("Width", JLabel.LEFT);
   private final JLabel heightLabel = new JLabel("Height", JLabel.LEFT);
   private final JLabel xCoordinateLabel = new JLabel("x Coordinate", JLabel.LEFT);
   private final JLabel yCoordinateLabel = new JLabel("y Coordinate", JLabel.LEFT);
   //text fields for inputs
   private JTextField widthTxt = new JTextField("",8);
   private JTextField heightTxt = new JTextField("",8);
   private JTextField xCoordinateTxt = new JTextField("",8);
   private JTextField yCoordinateTxt = new JTextField("",8);
   //buttons for frame to draw shape
   private final JButton drawButton = new JButton("Draw");
   //main frame object
   public ProjectFrame () {
       //comboboxes for selectoins
      JComboBox<String> shapeBox = new JComboBox<>(shapeStrings);
      JComboBox<String> fillBox = new JComboBox<>(fillTypeStrings);
      JComboBox<String> colorBox = new JComboBox<>(colorStrings);
      //create the upper/inner/lower panel and set layouts
      JPanel leftPanel = new JPanel();   
      JPanel rightPanel = new JPanel();
      JPanel lowerPanel = new JPanel(); 
      //setting gird layouts for left and lower panel
      leftPanel.setLayout(new GridLayout(8, 8)); 
      lowerPanel.setLayout(new GridLayout(2, 1,4,4)); 
      //adding inputs to left panel
      leftPanel.add(shapeTypeLabel);
      leftPanel.add(shapeBox);
      leftPanel.add(fillTypeLabel);
      leftPanel.add(fillBox);
      leftPanel.add(colorLabel);
      leftPanel.add(colorBox);
      leftPanel.add(widthLabel);
      leftPanel.add(widthTxt);
      leftPanel.add(heightLabel);
      leftPanel.add(heightTxt);
      leftPanel.add(xCoordinateLabel);
      leftPanel.add(xCoordinateTxt);
      leftPanel.add(yCoordinateLabel);
      leftPanel.add(yCoordinateTxt);
      //adding button lower panel
      lowerPanel.add(drawButton);
      //setting prefferedsize for initial right panel
      rightPanel.setPreferredSize(new Dimension(200,200));
      //adding panels to main frame
      add(leftPanel, BorderLayout.WEST);
      add(rightPanel, BorderLayout.EAST);
      add(lowerPanel,BorderLayout.SOUTH);
      //creating border for right panel
      rightPanel.setBorder(BorderFactory.createTitledBorder("Shape Drawing"));
      //draw button action when clicked
      drawButton.addActionListener((ActionEvent e) -> {
         //building variables to be used to send to Drawshape method
         Drawing newDrawing=new Drawing();
         newDrawing.setSize(newDrawing.getPreferredSize());
         Rectangle newRect;
         //trys to build rectangle and if not applicale it will return numberformat exception
         try{
             newRect=new Rectangle(Integer.parseInt(xCoordinateTxt.getText()),Integer.parseInt(yCoordinateTxt.getText()),Integer.parseInt(widthTxt.getText()),Integer.parseInt(heightTxt.getText()));
         }
         catch(NumberFormatException q){
             JOptionPane.showMessageDialog(null, "Please enter numbers in the field", "Error", JOptionPane.WARNING_MESSAGE);
            return;
         }
         //builds fill type variables and sets fill type to true or false based on selection
         String fillType=(String)fillBox.getSelectedItem();
         boolean fillTypeB;
         if (fillType == "Solid"){
             fillTypeB=true;
         }
         else{
             fillTypeB=false;
         }
         //builds color select and sets fill type to color seleceted
         String colorSelect = colorBox.getSelectedItem().toString().toLowerCase();
         Color newColor;
         try{
            Field colorField = Color.class.getField(colorSelect);
            newColor=(Color) colorField.get(null);
         }
         catch(Exception b){
             newColor=null;
         }
         //gets shape type and builds new shape and sends to Drawshape method to be drawn
         String shapeType=(String)shapeBox.getSelectedItem();
         try {
            if (shapeType == "Oval"){
                Oval newShape = new Oval(newRect,newColor,fillTypeB);
                newDrawing.DrawShape(newShape);
            }
            else{
                Rectangular newShape = new Rectangular(newRect,newColor,fillTypeB);
                newDrawing.DrawShape(newShape);
            }


            //resets rightPanel and adds the new drawing and repaints it
            rightPanel.removeAll();
            rightPanel.revalidate();
            rightPanel.add(newDrawing);
            rightPanel.repaint();
         }
         //exception catch outside bounds error which is thrown by drawshape
         catch(Exception y){
             JOptionPane.showMessageDialog(null, "The shape it out of the panel", "Error", JOptionPane.WARNING_MESSAGE);
         }
      });

    }
}
//Main class to bring up Project 2 Frame
public class Project3 extends Frame {
   public Project3() {
      super("Geometric Drawing",450, 325);
      add(new ProjectFrame()); 
   }
   public static void main(String[] args) {
      Project3 scApp = new Project3();
      scApp.display();
      
     
      
   }
}
