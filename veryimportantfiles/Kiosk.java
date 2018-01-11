/*
    ____     _    __         
   / __ \   (_)  / /_   ____ 
  / /_/ /  / /  / __/  / __ \
 / _, _/  / /  / /_   / /_/ /
/_/ |_|  /_/   \__/   \____/ 
                             
------------------------------------
Aaron Li, Johnny Wong, Joan Chirinos
------------------------------------
*/

import jutils.*;

/**********************************************************************
 <notes and comments>
 **********************************************************************/

public class Kiosk {
    // inst vars
    private CreditCard cc;
    private String user;

    // constructor
    public Kiosk(){
	// assign cc to user inputted ccNum via Keyboard.java
	// assign user to user's inputted name
	System.out.println("Please enter your name: ");
	user = Keyboard.readString();
	System.out.println("Hi " + user + " ... Please enter your credit card number: ");
	int ccNum = Keyboard.readInt();
	// if ccNum in cc database, cc.num = ccNum;
	// if not, ask if they want to make a new cc
    }

    public static String strArray(String [] x) {
	// stringify inputted String array
    }
    
    public static String checkExp() {
	// check cc's expiration date
    }

    //*******************movie methods***************************

    public static void viewCollection() {
	// view top 100 movies 
    }

    public static void search() {
	// user inputs String via Keyboard.java
	// String contains info pertaining to movie(s) titles
    }
    

    //***********************************************************

    
    //*******************checkout methods************************

    public static void listPrevRentals() {
	// check previous rentals
	// and print them in a vertical list
    }

    public static void listOrders() {
	// print current orders for movies
	// in a vertical list
    }

    public static void receipt() {
	// print out a receipt for the user
    }

    //***********************************************************
    public static void start(){
	// start Kiosk
    }
    
    
}
