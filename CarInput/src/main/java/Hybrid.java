
import java.text.NumberFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Hybrid.java
// Author: Dalton Coughlin
// Date: 4/11/2020
// Purpose: Hybrid Subclass object
class Hybrid extends AutoMobile {
    //New variable for subclass 
    private int milesPerGallon;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //Hybrid sublclass with super fields and new milespergallon field
    public Hybrid(String makeModel, int salesPrice,int milesPerGallon){
        super(makeModel,salesPrice);
        this.milesPerGallon=milesPerGallon;
    }
    //Overridden Salestax method for new caculations based on milespergallon
    @Override
    public String salesTax(AutoMobile AutoMobile){
        if(milesPerGallon <40){
            return(String.valueOf(formatter.format(getSalesPrice()*.05-100)));
        }
        else{
            return (String.valueOf(formatter.format(getSalesPrice()*.05-100-((milesPerGallon-40))*2)));
        }
    }
    //Overridden tostring method for including miles per gallon 
    @Override
    public String toString(AutoMobile AutoMobile) {
        return("Make & Model: " + getMakeModel() + "\nSalesTax: " + salesTax(AutoMobile) + "\nSales Price: " +getSalesPrice() + "\nType: Hybrid " + "\nMiles Per Gallon: "+ milesPerGallon );
    }
}