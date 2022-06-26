/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Frame.java
// Author: Dalton Coughlin
// Date: 4/11/2020
// Purpose: Frame Object
import javax.swing.*;
public class Frame extends JFrame { 
    //Builds Frame object
   static final int WIDTH = 300, HEIGHT = 300;
   public Frame() {
      super("Frame"); 
      setFrame(WIDTH, HEIGHT);
   }	
   //Sets Frame Title
   public Frame(String title) {
      super(title);  
      setFrame(WIDTH, HEIGHT);
   }
   //Sets Frame Dimensions
   public Frame(String title, int width, int height) {
      super(title);        				
      setFrame(width, height);
   }
   //Sets display of frame
   public void display() {
      setVisible(true);
   }
   //Sets frame location
   private void setFrame(int width, int height) {
      setSize(width, height);     
      setLocationRelativeTo(null);  		
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
   }
} 

