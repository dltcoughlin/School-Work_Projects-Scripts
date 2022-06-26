/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: Property.java
// Author: Dalton Coughlin
// Date: 5/9/2020
// Purpose: Property class that has the project object in it as well as exapnds interface
public class Property implements StateChangable{
	// Variables for constructor of property
	private String address;
	private int bedroom, squareFt, price;
	Status status;
        //constructor for property object
	public Property(String address, int bedroom, int squareFt, int price) {
		this.address = address;
		this.bedroom = bedroom;
		this.squareFt = squareFt;
		this.price = price;
		status = Status.FOR_SALE;
	}
        //Method to change the state of a property object
        @Override	
        public void changeState(Status state){    
		switch (state) {
		case FOR_SALE:
                    status = Status.FOR_SALE;
                    break;
		case UNDER_CONTRACT:
                    status = Status.UNDER_CONTRACT;
                    break;
		case SOLD:
                    status = Status.SOLD;
                    break;

		}

	}
        //method to return string of infromation from property object
        @Override
	public String toString(){
            return "Address: " + address + "\nTotal bedrooms: " + bedroom + "\nSquare footage: " + squareFt + "\nCurrent price: " + price + "\nCurrent status: " + status;
	}


}
