/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Shape.java
// Author: Dalton Coughlin
// Date: 4/27/2020
// Purpose: abstract class to be able to extend off of into different shapes 
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
//abstract class for shape that can be extended off of
abstract class Shape extends Rectangle {
    //variables for shape constructor and count for how many shapes created
    private static int creationCount = 0;
    private Color colorShape;
    private boolean shapeFill;
    //main constructor for shape object 
    public void Shape(Rectangle shape,Color colorShape, boolean shapeFill ){
        //setting shape object dimensions with rectangle passed
        this.setRect(shape.x,shape.y,shape.width,shape.height);
        this.colorShape=colorShape;
        this.shapeFill=shapeFill;
        creationCount += 1;
    }
    //setter for color with passed graphic from Shape object
    public void SetColor(Graphics g){
        g.setColor(this.colorShape);
    }
    //getter method for if the shape is solid or hollow
    public boolean GetSolid(){
          return this.shapeFill;
    }
    //returns creationcount count
    public static int GetNoOfShapes(){
        return creationCount;
    }
    //abstract method to be overridden by subclasses
    abstract void Draw(Graphics g);
    
}
