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

/**********************************************************************
Constructors:
* default constructor -> creates new card with random card number, pin, encrypts
                         the pin, and gives the user a starting $1,000,000
                         to rent movies
* overloaded constructor -> creates a new card with input number, pin, encrypts
                         the pin, and gives the user a starting $1,000,000
                         to rent movies

Methods:
* encrypt -> encrpts credit card pin
* isValidNum -> checks if the input matches a valid card number in Numbers.csv
* isValidPin -> checks if the input matches a valid pin number(encrypted)
  in Numbers.csv
* genNum -> generates a credit card number
* genPin -> generates a credit card pin number
* genMovie -> generates a string which stores the movies rented along with the
  date rented
* deduct -> deducts cost of transaction from user's debit card and returns
  appropriate user-dialogue

**********************************************************************/

import jutils.*;
import java.util.ArrayList;

public class DebitCard {

     // Instance Variables

     protected String cardNum; // stores card number
     protected String cardPin; // stores card pin
     protected String ePin; // stores encrypted pin
     protected String money; // stores amount of money


     // Constructors

     public DebitCard () {
          cardNum = genNum(); // generates new card number and stores it
          cardPin = genPin(); // generates new pin number and stores it
          ePin = encrypt(cardPin); // generates encrypted pin and stores it
          money = "1000000"; // gives starting money

          // CSV File Writing
          CSVRW check = new CSVRW("Numbers.csv");

          // sets card number, pin, and money
          check.set(check.size() - 1, 0, cardNum);
          check.set(check.size() - 1, 1, ePin);
          check.set(check.size() - 1, 2, money);

          // adds new row so user can make another debit card
          check.addRow();

          // write to file
          check.write("Numbers.csv");
     } // end of default constructor

     public DebitCard (String num, String pin) {
          cardNum = num; // sets card number to inputted number
          cardPin = pin; // sets pin number to inputted pin
          ePin = encrypt(pin); // encrypts and stores inputted pin number
          money = "1000000"; // gives starting money

     } // end of overloaded constructor


     // Methods

     protected boolean isValidNum (String input) {
          String nums = "0123456789"; // list of valid numbers to check for

          // for loop iterates through input and checks if all characters
          // are valid numbers
          for (int i = 0; i < input.length(); i++) {
               if (nums.indexOf(input.charAt(i)) < 0) {
                    return false;
               }
          }

          // CSV File Writing
          CSVRW check = new CSVRW("Numbers.csv");

          // if the input is a 12 digit card number, check if it already exists
          // in our csv file of existing numbers, if not, return false
          if (input.length() == 12) {
               for (int i = 0; i < check.size() - 1; i++) {
                    if (Long.valueOf(check.get(i, 0)).equals(Long.valueOf(input))) {
                         return true;
                    }
               } return false;
          } return false;
     } // end of isValidNum()

     protected boolean isValidPin(String input) {
          String nums = "0123456789"; // list of all valid numbers to check for

          // for loop iterates through input and check if all characters are
          // valid numbers. Returns false if it contains non-number character
          for (int i = 0; i < input.length(); i++) {
               if (nums.indexOf(input.charAt(i)) < 0) {
                    return false;
               }
          }

          // CSV File Writing
          CSVRW check = new CSVRW("Numbers.csv");

          // if the input is a 4 digit pin number, encrypt it, and check for
          // its existence in our csv file, returns false if not existing
          if (input.length() == 4) {
               long temp = (Long.valueOf(input) + 1029) * 384756;
               for (int i = 0; i <= check.size() - 1; i++) {
                    if (Long.valueOf(check.get(i, 1)) == temp) {
                         return true;
                    }
               } return false;
          } return false; // at this point return false because all the above failed
     } // end of isValidPin()

     protected String encrypt (String pin) {
          // this is our encryption method
          ePin = "" + (Long.valueOf(pin) + 1029) * 384756;
          return ePin; // return the encrypted number
     } // end of encrypt()

     protected String genNum () {
          String output = ""; // string to store generated number

          // for loop to create 12 random digits
          for (int i = 0; i < 12; i++) {
               output += (int) (Math.random() * 10);
          } return output; // returns generated number
     } // end of genNum()

     protected String genPin () {
          String output = ""; // string to store generated pin

          // for loop to create 4 random pin digits
          for (int i = 0; i < 4; i++) {
               output += (int) (Math.random() * 10);
          } return output; // return output
     } // end of genPin()

     protected void genMovie (String num, String name, String date) {
          // CSV File Writing
          CSVRW check = new CSVRW("Numbers.csv");

          // checks if input card number is valid
          if (isValidNum(num)) {
               // for loop to check for the index of the correct card number,
               // and then it appends the name of the movie rented and the date
               for (int i = 0; i < check.size() - 1; i++) {
                    if (check.get(i, 0).equals(num)) {
                         if (check.get(i, 3).equals(null)) {
                              check.set(i, 3, name + "," + date + "|");
                              check.write("Numbers.csv");
                         } else {
                              String temp = check.get(i, 3);
                              temp += name + "," + date + "|";
                              check.set(i, 3, temp);
                              check.write("Numbers.csv");
                         }
                    }
               }
          }
     } // end of genMovie

     protected String deduct (int amt) {
          // checks if the user has less money than the amount to be deducted
          if (Integer.parseInt(money) < amt) {
               return "Sorry, you don't have enough money."; // returns error message
          } else {
               // if user has enough money, deduct the amount for transaction
               money = Integer.toString(Integer.parseInt(money) - amt);
               // return transaction message
               return "Transaction successful, " + amt + "was deducted from your account. You now have $" + money + " left in your debit card.";
          }
     } // end of deduct


} // end of DebitCard class
