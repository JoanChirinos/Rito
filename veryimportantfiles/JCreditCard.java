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

public class JCreditCard {

    private String _ccnum;
    private String _pin;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public JCreditCard() {

	//set creditcard number
	for (int i = 0; i < 9; i++)
	    _ccnum += (int)(Math.random()*10);
	_pin = "";
	while (true) {
	    System.out.print("4 digit pin: ");
	    _pin = Keyboard.readString();
	    try {
		int numpin = Integer.parseInt(_pin);
		if (numpin >= 0 && numpin <= 9999)
		    break;
		else System.out.println("Invalid pin\n");
	    }
	    catch (Exception e) {
		System.out.println("Invalid pin\n");
	    }
	}
	System.out.println(_pin);
	pinEncrypt();
	System.out.println(_pin);

    }//end CreditCard()

    //checks csv file to see if credit card number is valid
    public boolean creditCardExists() {
	return true;
    }

    /*
    public JCreditCard(String cc, String pin) {

	

    }//end CreditCard(String, String)
    */

    //changes pin to encrypted pin
    private void pinEncrypt() {
        String strpin = "" + ((Integer.parseInt(_pin) + 1111) * 1121231234);
	System.out.println(strpin);
	_pin = "";
	for (int i = 0; i < strpin.length(); i++) {
	    int index = Integer.parseInt(strpin.substring(i, i + 1));
	    _pin += ALPHABET.substring(index, index + 1);
	}
    }//end pinEncrypt

    public String getccnum() {
	return _ccnum;
    }//end getccnum
    
}//end class
