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
    private ArrayList<String> cart;

    // constructor
    public Kiosk() {
         cart = new ArrayList<String>();
    }


    //*******************movie methods***************************

    // this is Rito's default home page
    // containing the top 5 movies!
    // the user will often be referred back to our home page during their time renting
    // from our collection of rad movies!
    public void home(Movie movie) {
	// view top 5 movies
     System.out.println("Hello! Welcome to Rito's Rad Movies!\nPlease view our wicked collection of the past decade!");
     movie.search("");
     this.decisionMaking(movie);
    } // end of home


    // the user can search, rent, return movies, or view their cart
    public void decisionMaking(Movie movie) {

         String respStr;
         int respInt;
         int choice = 0;
         System.out.println("What would you like to do?\nYou can \n1. search\n2. rent\n3. view cart");
         choice = Keyboard.readInt();
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

         // view cart
         else if (choice == 3) {
              // <previous rental situation>

              if (cart.size() == 0) {
                   System.out.println("Nothing seems to be in your cart my dear...\n\n\n\n\n");
                   this.home(movie);
              }
              else {
                   this.listOrders();
                   System.out.println("Would you like to checkout?");
                   respStr = Keyboard.readString().toLowerCase();
                   if (respStr.equals("yes")) {
                        this.checkout();
                   }
                   else {
                        System.out.println("aww...\n\n\n\n\n\n");
                        this.home(movie);
                   }
              }

         } // end of view cart
         else {
              System.out.println("Please make a choice from 1-4\n\n\n\n\n");
              this.home(movie);
         }


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
                        System.out.println("\n\n\n\n\n\n");
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

    //*******************CreditCard methods************************

    public boolean isValidNum (long input) {
         String nums = "0123456789";
         String inp = String.valueOf(input);
         for (int i = 0; i < inp.length(); i++) {
              if (nums.indexOf(inp.charAt(i)) < 0) {
                   return false;
              }
         }

         CSVRW check = new CSVRW("Numbers.csv");
         if (inp.length() == 12) {
              for (int i = 0; i < check.size() - 1; i++) {
                   if (Long.valueOf(check.get(i, 0)).equals(Long.valueOf(inp))) {
                        return true;
                   }
              } return false;
         } return false;
    } // end of isValidNum()

    public boolean isValidPin(long input) {
         String nums = "0123456789";
         String inp = String.valueOf(input);
         for (int i = 0; i < inp.length(); i++) {
              if (nums.indexOf(inp.charAt(i)) < 0) {
                   return false;
              }
         }

         CSVRW check = new CSVRW("Numbers.csv");
         if (inp.length() == 4) {
              long temp = (Long.valueOf(inp) + 1029) * 384756;
              for (int i = 0; i <= check.size() - 1; i++) {
                   if (Long.valueOf(check.get(i, 1)) == temp) {
                        return true;
                   }
              } return false;
         } return false;
    } // end of isValidPin()




    public void generateCard () {
         String resp;
         System.out.println("Would you like to generate a CreditCard?");
         resp = Keyboard.readString().toLowerCase();
         if (resp.equals("yes")) {
              System.out.println("Great!");
              card = new CreditCard();
              System.out.println("This is your card number: " + card.cardNum);
              System.out.println("This is your pin number: " + card.cardPin);
              System.out.println("Remember! Don't lose this information!");
         }
    }

    //***********************************************************

    //*******************checkout methods************************

    // WIP
    public void historyCSVRW() {
         CSVRW history = new CSVRW("Numbers.csv");

    }

    // WIP
    public void listPrevRentals() {
	// check previous rentals
	// and print them in a vertical list
    }

    public void checkout() {
         // user checks out movies
         this.receipt();
         cart = new ArrayList<String>();
    }

    public void listOrders() {
	// print current orders for movies
	// in a vertical list
     System.out.println("\n\n\n\n\n\nYour cart:");
     for (int itemCount = 0; itemCount < cart.size(); itemCount++) {
          System.out.println( (itemCount + 1) + ". " + cart.get(itemCount));
     }
    }

    public void receipt() {
	// print out a receipt for the user
     System.out.println("Thank you for shopping with us number " + card.cardNum);
     System.out.println("You rented ");
     for (int itemCount = 0; itemCount < cart.size(); itemCount++) {
          System.out.println( (itemCount + 1) + ". " + cart.get(itemCount));
     }
     System.out.println("Have a nice day!");

    }

    //***********************************************************
    public void go(){
	// start Kiosk
     System.out.println("Do you have a CreditCard?");
     String respStr = Keyboard.readString().toLowerCase();
     if (respStr.equals("no")) {
          this.generateCard();
          System.out.println("\n\n\n\n\n\n");
     }
     else if (respStr.equals("yes")) {
          System.out.println("Please enter your CreditCard number: ");
          long num = Keyboard.readLong();
          System.out.println("Now your pin: ");
          long pin = Keyboard.readLong();
          if (this.isValidNum(num) && this.isValidPin(pin)) {
               System.out.println("success\n\n\n\n\n");
          }
          else {
               System.out.println("fail");
          }
     }
     Movie _movies = new Movie();
     this.home(_movies);

    }


}
