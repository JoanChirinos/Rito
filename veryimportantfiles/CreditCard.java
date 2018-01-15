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

     private long cardNum; // stores card number
     private long cardPin; // stores card pin
     private long ePin; // stores encrypted pin


     // Constructors

     public CreditCard () {
          cardNum = genNum;
          cardPin = genPin;
          encrypt(cardPin);
     }

     public CreditCard (long num, long pin) {
          cardNum = num;
          cardPin = pin;
          encrypt(cardPin);
     }


     // Methods

     private boolean isValid (long input) {
          String nums = "0123456789";
          String inp = String.valueOf(input);
          for (int i = 0; i < inp.length(); i++) {
               if (nums.indexOf(inp.charAt(i)) < 0) {
                    return false;
               }
          }

          CSVRW check = new CSVRW("Numbers.csv");
          if (inp.length() == 12) {
               for (long i = 0; i < check.size(); i++) {
                    if (Long.valueOf(check.get(i, 0)) == Long.valueOf(inp)) {
                         return false;
                    }
               } return true;
          }
          if (inp.length() == 4) {
               return true;
          } return false;
     } // end of isValid()

     private void encrypt (long pin) {
          ePin = (pin + 1029) * 384756;
          CSVRW poop = new CSVRW("Numbers.csv");
          poop.set(poop.size(), 1, toString(ePin));
          poop.write();
     } // end of encrypt()

     private long genNum () {
          String output = "";
          for (int i = 0; i < 12; i++) {
               ouput += ((int) (Math.random() * 10)) + "";
          } CSVRW poop = new CSVRW("Numbers.csv");
          poop.addRow();
          poop.set(poop.size(), 0, output));
          poop.write();
          return (Long.valueOf(output));
     } // end of genNum()

     private long genPin () {
          String output = "";
          for (int i = 0; i < 4; i++) {
               output += ((int) (Math.random() * 10)) + "";
          } return (Long.valueOf(output));
     } // end of genPin()


}
