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

public class CreditCard {

     // Instance Variables

     private int cardNum; // stores card number
     private int cardPin; // stores card pin
     private int ePin; // stores encrypted pin


     // Constructors

     public CreditCard () {

     }

     public CreditCard (int num, int pin) {

     }


     // Methods

     private boolean isValid (input) {

     }

     private void encrypt (pin) {

     }

     private int genNum () {
          String output = "";
          for (int i = 0; i < 12; i++) {
               ouput += (int) (Math.random() * 10) + "";
          } long out = Long.parseLong(output);
          
          return out;
     }

     private int genPin () {
          int output = 0;
          for (int i = 0; i < 4; i++) {
               output +=
          }

     }

     public String toString () {

     }

}
