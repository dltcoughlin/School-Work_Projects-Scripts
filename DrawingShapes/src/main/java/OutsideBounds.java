/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// FileName: OutsideBounds.java
// Author: Dalton Coughlin
// Date: 4/27/2020
// Purpose: Outsidebounds exception method for catching outsidebounds error
public class OutsideBounds extends Exception {
    //Setting outsideboundserror variable to be sent
    private int outsideBoundsError;
    //constructor for outsidebounds exception
    public OutsideBounds(int outsideBoundsError) {
        this.outsideBoundsError = outsideBoundsError;
    }
    @Override
    //method to get message of error of outside bounds
    public String getMessage() {
        String Message = "";
        switch (outsideBoundsError) {
            case 100:
                Message = "OutsideBounds";
        }
        return Message;
    }


}