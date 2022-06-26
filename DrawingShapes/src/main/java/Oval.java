
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Oval.java
// Author: Dalton Coughlin
// Date: 4/27/2020
// Purpose: Oval subclass to extend shape, also draw app shape when called
public class Oval extends Shape{
    //constructor for oval class that extends shape
    public Oval(Rectangle shape,Color colorShape, boolean shapeFill){
      super.Shape(shape,colorShape, shapeFill);         		
    }
    //draw method to draw oval object that is sent with graphic
    @Override
    public void Draw(Graphics g){
        //setting color of graphic from SetColor method from Shape with Oval Object passed
        SetColor(g);
        //if solid is true graphic is built with oval object dimensions as a fill, if hollow it's built as hollow
        if (GetSolid()){
             g.fillOval(this.x, this.y, this.width, this.height);
        }
        else{
            g.drawOval(this.x, this.y, this.width, this.height);
        }
    }
    }
