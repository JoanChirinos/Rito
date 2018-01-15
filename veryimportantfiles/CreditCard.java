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
     * isValid -> checks if the input matches a valid pin number or card number
     * genNum -> generates a credit card number
     * genPin -> generates a credit card pin number
     * toString -> returns credit card number and pin

**********************************************************************/
import jutils.*;

public class CreditCard {

     // Instance Variables

     protected long cardNum; // stores card number
     protected long cardPin; // stores card pin
     protected long ePin; // stores encrypted pin


     // Constructors

     public CreditCard () {
          cardNum = genNum();
          cardPin = genPin();
          ePin = encrypt(cardPin);

          CSVRW check = new CSVRW("Numbers.csv");
          check.set(check.size() - 1, 0, Long.toString(cardNum));
          check.set(check.size() - 1, 1, Long.toString(ePin));
          check.addRow();
          check.write("Numbers.csv");
     }

     public CreditCard (long num, long pin) {
          cardNum = num;
          cardPin = pin;
          ePin = encrypt(pin);

          CSVRW check = new CSVRW("Numbers.csv");
          check.set(check.size() - 1, 0, Long.toString(cardNum));
          check.set(check.size() - 1, 1, Long.toString(ePin));
          check.addRow();
          System.out.println(check.get(0,1));
          check.write("Numbers.csv");
     }


     // Methods

     protected boolean isValidNum (long input) {
          String nums = "0123456789";
          String inp = String.valueOf(input);
          for (int i = 0; i < inp.length(); i++) {
               if (nums.indexOf(inp.charAt(i)) < 0) {
                    return false;
               }
          }

          CSVRW check = new CSVRW("Numbers.csv");
          if (inp.length() == 12) {
               for (int i = 0; i < check.size(); i++) {
                    if (Long.valueOf(check.get(i, 0)) == Long.valueOf(inp)) {
                         return true;
                    }
               } return false;
          } return false;
     } // end of isValidNum()

     protected boolean isValidPin(long input) {
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
               for (int i = 0; i < check.size(); i++) {
                    if (Long.valueOf(check.get(i, 1)) == temp) {
                         return true;
                    }
               } return false;
          } return false;
     } // end of isValidPin()

     protected long encrypt (long pin) {
          ePin = (pin + 1029) * 384756;
          return ePin;
     } // end of encrypt()

     protected long genNum () {
          String output = "";
          for (int i = 0; i < 12; i++) {
               output += ((int) (Math.random() * 10)) + "";
          } return (Long.valueOf(output));
     } // end of genNum()

     protected long genPin () {
          String output = "";
          for (int i = 0; i < 4; i++) {
               output += ((int) (Math.random() * 10)) + "";
          } return (Long.valueOf(output));
     } // end of genPin()

} // end of CreditCard class
