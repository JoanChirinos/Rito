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
* default constructor -> creates new card with random card number and pin
* overloaded constructor -> creates a new card with input number and pin

Methods:
* encrypt -> encrpts credit card pin
* isValidNum -> checks if the input matches a valid card number in Numbers.csv
* isvALIDpIN -> checks if the input matches a valid pin number(encrypted)
in Numbers.csv
* genNum -> generates a credit card number
* genPin -> generates a credit card pin number

**********************************************************************/

// add movie strings for movies
// add return date *long string

// money

import jutils.*;
import java.util.ArrayList;

public class CreditCard {

     // Instance Variables

     protected String cardNum; // stores card number
     protected String cardPin; // stores card pin
     protected String ePin; // stores encrypted pin
     protected String money;

     // Constructors

     public CreditCard () {
          cardNum = genNum();
          cardPin = genPin();
          ePin = encrypt(cardPin);
          money = "100000000";

          CSVRW check = new CSVRW("Numbers.csv");
          check.set(check.size() - 1, 0, cardNum);
          check.set(check.size() - 1, 1, ePin);
          check.set(check.size() - 1, 2, money);
          check.addRow();
          check.write("Numbers.csv");
     }

     public CreditCard (String num, String pin) {
          cardNum = num;
          cardPin = pin;
          ePin = encrypt(pin);
          money = "100000000";
     }


     // Methods

     protected boolean isValidNum (String input) {
          String nums = "0123456789";
          for (int i = 0; i < input.length(); i++) {
               if (nums.indexOf(input.charAt(i)) < 0) {
                    return false;
               }
          }

          CSVRW check = new CSVRW("Numbers.csv");
          if (input.length() == 12) {
               for (int i = 0; i < check.size() - 1; i++) {
                    if (Long.valueOf(check.get(i, 0)).equals(Long.valueOf(input))) {
                         return true;
                    }
               } return false;
          } return false;
     } // end of isValidNum()

     protected boolean isValidPin(String input) {
          String nums = "0123456789";
          for (int i = 0; i < input.length(); i++) {
               if (nums.indexOf(input.charAt(i)) < 0) {
                    return false;
               }
          }

          CSVRW check = new CSVRW("Numbers.csv");
          if (input.length() == 4) {
               long temp = (Long.valueOf(input) + 1029) * 384756;
               for (int i = 0; i <= check.size() - 1; i++) {
                    if (Long.valueOf(check.get(i, 1)) == temp) {
                         return true;
                    }
               } return false;
          } return false;
     } // end of isValidPin()

     protected String encrypt (String pin) {
          ePin = "" + (Long.valueOf(pin) + 1029) * 384756;
          return ePin;
     } // end of encrypt()

     protected String genNum () {
          String output = "";
          for (int i = 0; i < 12; i++) {
               output += (int) (Math.random() * 10);
          } return output;
     } // end of genNum()

     protected String genPin () {
          String output = "";
          for (int i = 0; i < 4; i++) {
               output += (int) (Math.random() * 10);
          } return output;
     } // end of genPin()

     protected String genMovie (String name, String date) {
          String out = name + "," + date + "|";

          CSVRW check = new CSVRW("Numbers.csv");
          check.set(check.size() - 1, 3, out);
          check.write("Numbers.csv");

          return out;
     } // end of genMovie

     protected String deduct (int amt) {
          if (Integer.parseInt(money) < amt) {
               return "Sorry, you don't have enough money.";
          } else {
               money = Integer.toString(Integer.parseInt(money) - amt);
               return "Transaction successful, " + amt + "was deducted from your account. You now have $" + money + " left in your debit card.";
          } ;
     } // end of deduct


} // end of CreditCard class
