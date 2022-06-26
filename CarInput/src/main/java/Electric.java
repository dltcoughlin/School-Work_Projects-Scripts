
import java.text.NumberFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Electric.java
// Author: Dalton Coughlin
// Date: 4/11/2020
// Purpose: Electric Subclass Object
class Electric extends AutoMobile {
    //New variable for subclass 
    private int weightPounds;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    //Electric sublclass with super fields and new pounds field
    public Electric(String makeModel, int salesPrice,int weightPounds){
        super(makeModel,salesPrice);
        this.weightPounds=weightPounds;
    }
    //Overridden Salestax method for new caculations based on pounds
    @Override
    public String salesTax(AutoMobile AutoMobile){
        if(weightPounds <3000){
            return(String.valueOf(formatter.format(getSalesPrice()*.05-200)));
        }
        else{
            return (String.valueOf(formatter.format((getSalesPrice()*.05-150))));
        }
    }
    //Overridden tostring method for including pounds
    @Override
    public String toString(AutoMobile AutoMobile) {
        return("Make & Model: " + getMakeModel() + "\nSalesTax: " + salesTax(AutoMobile) + "\nSales Price: " +getSalesPrice() + "\nType: Electric" +"\nWeight In Pounds: "+ weightPounds);
    }
}
