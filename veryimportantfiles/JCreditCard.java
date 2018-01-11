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

	//set creditcard number. If that number exists, try again
	while (true) {
	    for (int i = 0; i < 9; i++)
		_ccnum += (int)(Math.random()*10);
	    System.out.println(creditCardExists(_ccnum));
	    if (!(creditCardExists(_ccnum))) break;
	}
	//set 4-digit pin. Save it encrypted
	_pin = "";
	while (true) {
	    System.out.print("4 digit pin: ");
	    _pin = Keyboard.readString();
	    if (_pin.length() != 4)
		System.out.println("Invalid pin\n");
	    else {
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
	}
	_pin = pinEncrypt(_pin);
    }//end CreditCard()

    //checks csv file to see if credit card number is valid
    public boolean creditCardExists(String ccnum) {
	CSVRW c = new CSVRW("creditcards.csv");
	for (int i = 0; i < c.get().size(); i++) {
	    if (c.get(i, 0).equals(ccnum)) return true;
	}
	return false;
    }//end creditCardExists
    
    /*
    public JCreditCard(String cc, String pin) {

	

    }//end CreditCard(String, String)
    */

    //changes pin to encrypted pin
    private String pinEncrypt(String pin) {
        String strpin = "" + ((Integer.parseInt(pin) + 1111) * 1121231234);
	String retpin = "";
	for (int i = 0; i < strpin.length(); i++) {
	    int index = Integer.parseInt(strpin.substring(i, i + 1));
	    retpin += ALPHABET.substring(index, index + 1);
	}
	return retpin;
    }//end pinEncrypt

    public String getccnum() {
	return _ccnum;
    }//end getccnum
    
}//end class
