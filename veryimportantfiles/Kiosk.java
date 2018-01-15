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
import java.util.ArrayList;

/**********************************************************************
 <notes and comments>
 **********************************************************************/

public class Kiosk {
    // inst vars
    private CreditCard card;
    private String user;
    private ArrayList<String> cart;

    // constructor
    public Kiosk() {
         cart = new ArrayList<String>();
    }

    public String strArray(String [] x) {
	// stringify inputted String array
     return "xd";
    }

    //*******************movie methods***************************

    public void home(Movie movie) {
	// view top 5 movies
     System.out.println("Hello! Welcome to Rito's Rad Movies!\nPlease view our wicked collection of the past decade!");
     movie.search("");
     this.decisionMaking(movie);
    } // end of home


    public void decisionMaking(Movie movie) {

         String respStr;
         int respInt;
         System.out.println("What would you like to do?\nYou can \n1. search\n2. rent\n3. return a previously rented movie\n4. view cart");
         int choice = Keyboard.readInt();
         // search choice
         if (choice == 1) {
              System.out.println("What would you like to search for?");
              String search1 = Keyboard.readString();
              if (movie.search(search1)) {
                   System.out.println("Do you want to rent any of these?");
                   respStr = Keyboard.readString().toLowerCase();
                   if (respStr.equals("yes")) {
                        // this is not fully functional yet
                        // rent works for the top 5 movies only...
                        this.rent(movie);

                   }
              }
         }
          // end of search choice

          // rent choice
          else if (choice == 2) {
               this.rent(movie);

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
          else if (choice == 4) {

          }
          // end of return rental choice

    } // end of decisionMaking

    // user can choose out of the 5 current movies on display which to rent
    // uses error handling in case the user does not choose a number from 1-5
    // if the user does not decide to rent any, user moves back to home page
    public void rent(Movie movie) {
         boolean didNotChoose = true;
         while (didNotChoose) {
              System.out.println("Which movie would you like to rent?");
              int respInt = Keyboard.readInt();
              if (respInt < 1 || respInt > 5) {
                   System.out.println("Sorry, but please pick a movie number from 1 to 5...");
              }
              else {
                   didNotChoose = false;
                   System.out.println("So you would like to rent " + movie._movienames.get(respInt - 1) + " ?");
                   String respStr = Keyboard.readString().toLowerCase();
                   if (respStr.equals("yes")) {
                        cart.add(movie._movienames.get(respInt - 1));
                        System.out.println(movie._movienames.get(respInt - 1) + " has been added to your cart.");
                        this.home(movie);
                   }
                   else {
                        System.out.println("Bummer...\n\n\nHome Page");
                        this.home(movie);

                   }
              }
         }
    }


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
     this.home(_movies);

    }


}
