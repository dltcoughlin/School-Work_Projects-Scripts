/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: StateChangeable.java
// Author: Dalton Coughlin
// Date: 5/9/2020
// Purpose: interface to be expanded upon by the property class, holds abstract method to change state of proerpty
public interface StateChangable <T extends Enum>{
  //abstract method for property class to expand upon to change the state of a property object
  abstract void changeState(Status state);
}