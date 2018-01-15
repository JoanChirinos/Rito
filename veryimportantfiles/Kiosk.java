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
    private CreditCard card;
    private String user;
    private String [] cart;

    // constructor
    public Kiosk(){
	// assign cc to user inputted ccNum via Keyboard.java
	// assign user to user's inputted name
	// System.out.print("Please enter your name: ");
	// user = Keyboard.readString();
	// System.out.println("Hi " + user + " ... Please enter your credit card number: ");
	// int ccNum = Keyboard.readInt();
	// if ccNum in cc database, cc.num = ccNum;
	// if not, ask if they want to make a new cc
    }

    public String strArray(String [] x) {
	// stringify inputted String array
     return "xd";
    }

    //*******************movie methods***************************

    public void home(Movie movies) {
	// view top 5 movies
     movies.search("");
    } // end of home


    public void decisionMaking(Movie movies) {
         String resp;
         System.out.println("What would you like to do?\nYou can \n1. search\n2. rent\n3. return a previously rented movie...");
         int choice = Keyboard.readInt();
         // search choice
         if (choice == 1) {
              System.out.println("What would you like to search for?");
              String search1 = Keyboard.readString();
              if (movies.search(search1)) {
                   System.out.println("Do you want to rent any of these?");
                   resp = Keyboard.readString().toLowerCase();
                   if (resp.equals("yes")) {
                        System.out.println("Which?");
                        resp = Keyboard.readString().toLowerCase();
                   }
              }
         }
          // end of search choice

          // rent choice
          else if (choice == 2) {
               System.out.println("Which movie would you like to rent?");
               resp = Keyboard.readString().toLowerCase();
          }
          // end of rent choice

          // return rental choice
          else if (choice == 3) {
               System.out.println("Please enter your CreditCard number to view your previously rented movies: ");
               long num = Keyboard.readLong();
               System.out.println("Now your pin: ");
               long pin = Keyboard.readLong();
               card = new CreditCard(num, pin);
               if (! (card.isValidNum(card.cardNum) && card.isValidPin(card.cardPin))){
                    System.out.println("It seems you made an oops...");
               }


          }
          // end of return rental choice

    } // end of decisionMaking



    //***********************************************************


    //*******************checkout methods************************

    public void listPrevRentals() {
	// check previous rentals
	// and print them in a vertical list
    }

    public void listOrders() {
	// print current orders for movies
	// in a vertical list
    }

    public void receipt() {
	// print out a receipt for the user
    }

    //***********************************************************
    public void go(){
	// start Kiosk
     Movie _movies = new Movie();
     System.out.println("Hello! Welcome to Rito's Rad Movies!\nPlease view our wicked collection of the past decade!");
     this.home(_movies);
     this.decisionMaking(_movies);
    }


}
