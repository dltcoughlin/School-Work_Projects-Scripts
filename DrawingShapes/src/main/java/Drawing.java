
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Drawing.java
// Author: Dalton Coughlin
// Date: 4/27/2020
// Purpose: class to draw shape inside Jpanel to be called by main frame
public class  Drawing extends JPanel{
    private Shape shape;
    @Override
    //method for paint number of shapes created and call Draw method from shape
    protected void paintComponent(Graphics g) { 
      g.drawString(Integer.toString(shape.GetNoOfShapes()),10,10); 
      shape.Draw(g);
   }
    //method to be called to set preffered size of dimension
    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(200, 200);
        return d;
    }
   //method to be called with shape being entered to set new shape and repaint it
   public void DrawShape(Shape shape) throws Exception{
            //checks if shape is within bounds and repaints shape
            if (contains(shape.x, shape.y) && contains(shape.x, shape.y + shape.height)&& contains(shape.x + shape.width, shape.y) && contains(shape.x + shape.width, shape.y + shape.height)) {
                this.shape = shape;
                repaint();
            //throws Outsidebounds exception
            } else {
                throw new OutsideBounds(100);
            }
   }


}
