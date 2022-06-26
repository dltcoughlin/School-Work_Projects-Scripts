/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: AutoMobile.java
// Author: Dalton Coughlin
// Date: 4/11/2020
// Purpose: Automobile Superclass Object
import java.text.NumberFormat;
class AutoMobile{
    //variables for Automobile class
    private String makeModel;
    private int salesPrice;
    //Currency formatter for to string
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //Automobile object constructor
    public AutoMobile(String makeModel,int salesPrice){
        this.makeModel = makeModel;
        this.salesPrice=salesPrice;
    }
    //Outputs String built from Automobile object
    public String toString(AutoMobile AutoMobile) {
        return("Make & Model: " + makeModel + "\nSalesTax: " + formatter.format(salesPrice*.05) + "\nSales Price: " +salesPrice );
    }
    //Returns string of sales tax caculation
    public String salesTax (AutoMobile AutoMobile){
      
      return (String.valueOf(formatter.format(salesPrice*.05)));
    }
    //Getter for make model field
    String getMakeModel(){
        return makeModel;
    }
    // Getter for sales price field
    int getSalesPrice(){
        return salesPrice;
    }
    //Setter for make model field
    public void setMakeModel(){
        this.makeModel= makeModel;
    }
    // Setter for sales price field
    public void setSalesPrice(){
        this.salesPrice= salesPrice;
    }

 }
