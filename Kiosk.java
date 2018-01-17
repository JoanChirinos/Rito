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
import java.util.Calendar;

/**********************************************************************
* default contructor -> ArrayList<String> cart is initialized to an empty
                          ArrayList<String>
                          price is initialized to a value of 3
  Methods:
  * void home (Movie movie) -> sends the user back to our default "page"
  * void home (Movie movie, String lastSearch) -> sends the user to a page similar
                                                  to our default, but displays 5 movies
                                                  related to lastSearch
  * void decisionMaking(Movie movie, String lastSearch) -> the main component of our project that ties
                                                           our whole project together - this is where all
                                                           of the conditionals are asked after user provides inputs
                                                           based on printed directions - it is recursively called until
                                                            the user exits or checksout a movie
   * void rent(Movie movie, String searchInfo) -> user adds a movie to the Kiosk's cart
   * void returnPrev() -> user returns their previously rented movies
 - * String findMovie (Movie movie, String name, int index) -> returns the name of a movie in our movies.csv dependent on
 -                                                             inputted search info
 + * String findMovie (Movie movie, String name, int index) -> finds movie based on passed String and user inputted int
   * boolean isValidNum (String input) -> returns true or false if inputted DebitCard number is stored in our Numbers.csv
   * boolean isValidPin (String input) -> returns true or false if inputted DebitCard pin is stored in our Numbers.csv
   * void generateCard() -> if the user would like, a new DebitCard card can be generated and added to Numbers.csv

**********************************************************************/

public class Kiosk {
    // inst vars
    private DebitCard card;
    private ArrayList<String> cart;
    private int price;
    private Calendar c;

    // constructor
    public Kiosk() {
	cart = new ArrayList<String>();
	price = 3;
	c = Calendar.getInstance();
    }


    //*******************movie methods***************************

    // this is Rito's default home page
    // containing the top 5 movies!
    // the user will often be referred back to our home page during their time renting
    // from our collection of rad movies!
    public void home(Movie movie) {
	// view top 5 movies
	System.out.println("Hello! Welcome to Rito's Rad Movies!\nPlease view our wicked collection of the past decade!\nWe are having a sale where all of the rentals cost $3!!!");
	movie.search("");
	this.decisionMaking(movie, "");
    } // end of home


    // overloaded version of home that passes that lastSearched terms/movie
    public void home(Movie movie, String lastSearch) {
	// view top 5 movies
	movie.search(lastSearch);
	this.decisionMaking(movie, lastSearch);
    } // end of home


    // the user can search, rent, return movies, or view their cart
    public void decisionMaking(Movie movie, String lastSearch) {

	String respStr;
	int respInt;
	int choice = 0;
	String search1 = lastSearch;
	while (true) {
	    System.out.println("What would you like to do?\nYou can \n1. search\n2. rent\n3. view cart\n4. fast-forward time!\n5. return movies \n6. Exit");
	    choice = Keyboard.readInt();
	    // search choice
	    if (choice == 1) {
		/*
		System.out.println("What would you like to search for?");
		search1 = Keyboard.readString();
		this.home(movie, search1);
		*/
		System.out.print("Search term(s): ");
		String terms = Keyboard.readString();
		if (movie.search(terms)) {
		    while (true) {
			System.out.println("Eneter a number for more info, or"+
					   " \"back\" to go back");
			String infochoice = Keyboard.readString();
			if (infochoice.toLowerCase().equals("back")) break;
			else {
			    try {
				movie.moreInfo(Integer.parseInt(infochoice));
			    }
			    catch (Exception e) {
				System.out.println("Are you sure that was " +
						   "valid?");
			    }
			}
		    }
		}
		
	    }
	    // end of search choice
	    
	    // rent choice
	    else if (choice == 2) {
		System.out.println("Please enter the NAME of the movie you'd" +
				   " like to rent:");
		String movietorent = Keyboard.readString();
		this.rent(movietorent, movie);
	    
	    }
	    
	    // end of rent choice

	    // view cart
	    else if (choice == 3) {
		if (cart.size() == 0) {
		    System.out.println("Nothing seems to be in your cart my dear...\n\n\n\n\n");
		    this.home(movie);
		}
		else {
		    this.listOrders();
		    System.out.println("Would you like to checkout?");
		    System.out.println("1. yes");
		    System.out.println("2. no");
		    respInt = Keyboard.readInt();
		    if (respInt == 1) {
			this.checkout();
		    }
		    else {
			System.out.println("aww...\n\n\n\n\n\n");
			this.home(movie);
		    }
		}

	    } // end of view cart
	    else if (choice == 4) {
		this.fastForward();
	    }
	    else if (choice == 5) {
		try {
		    returnPrev();
		}
		catch (Exception e) {
		    System.out.println("You didn't have any movies out!");
		}
		this.home(movie);
	    }
	    else if (choice == 6) return;
	    else {
		System.out.println("Please make a choice from 1-5\n\n\n\n\n");
		this.home(movie);
	    }

	}
    } // end of decisionMaking


    // user can choose out of the 5 current movies on display which to rent
    // uses error handling in case the user does not choose a number from 1-5
    // if the user does not decide to rent any, user moves back to home page
    public void rent(String movieName, Movie movie) {

	if (movie.search(movieName)) {
	    System.out.print("So you would like to rent " + movieName +
			     "? (y/n)\t");
	    String yesno = Keyboard.readString().toLowerCase();
	    if (yesno.equals("n")) {
		System.out.println("Bummer...");
		this.home(movie);
	    }
	    else if (yesno.equals("y")) {
		cart.add(movieName);
		System.out.println(movieName + " has been added to your cart");
		System.out.println("\n\n\n\n");
		this.home(movie);
	    }
	}
	else {
	    System.out.println("Please type the name exactly as it appears" +
			       " when you search for it!");
	    this.home(movie);
	}


	
	/*
	boolean didNotChoose = true;
	while (didNotChoose) {
	    System.out.println("Which movie would you like to rent?");
	    int respInt = Keyboard.readInt();
	    if (respInt < 1 || respInt > 5) {
		System.out.println("Sorry, but please pick a movie number from 1 to 5...");
	    }
	    else {
		didNotChoose = false;
		if (searchInfo.equals("")) {
		    movieName = movie._movienames.get(respInt - 1);
		}
		else {
		    movieName = this.findMovie(movie, searchInfo, respInt);
		}
		System.out.println("So you would like to rent " + movieName + " ?");
		System.out.println("1. yes");
		System.out.println("2. no");
		int respInt2 = Keyboard.readInt();
		if (respInt2 == 1) {
		    cart.add(movieName);
		    System.out.println(movieName + " has been added to your cart.");
		    System.out.println("\n\n\n\n");
		    this.home(movie);
		}
		else {
		    System.out.println("Bummer...\n\n\n\n");
		    this.home(movie);

		}
	    }
	}
	*/
    }//end rent

    public String findMovie (Movie movie, String name, int index) {

	return "";
	
	/*
	ArrayList<String> _movies = new ArrayList<String>();
	for (int i = 0; i < movie._movieinfo.size(); i++) {
	    if (_movies.size() > 5) {
		break;
	    }
	    if ( (movie._movieinfo.get(i).indexOf(name)) != -1) {
		_movies.add(movie._movienames.get(i));
	    }

	}
	for (int i = 0; i < _movies.size(); i++) {
	    System.out.println(_movies.get(i));
	}
	return _movies.get(index - 1);
	*/
    }

    //***********************************************************

    //*******************DebitCard methods************************

    public boolean isValidNum (String input) {
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

    public boolean isValidPin(String input) {
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
		try {
		    if (Long.valueOf(check.get(i, 1)) == temp) {
			return true;
		    }
		}
		catch (NumberFormatException e) {

		}
		catch (Exception e) {
		    System.out.println("Something went wrong. We will try to" +
				       " fix it, I promise");
		    return false;
		}
	    }
	    
	    return false;
	}
	return false;
    } // end of isValidPin()




    public boolean generateCard () {
	int resp;
	System.out.println("Would you like to generate a DebitCard?");
	System.out.println("1. yes");
	System.out.println("2. no");
	resp = Keyboard.readInt();
	if (resp == 1) {
	    System.out.println("Great!");
	    card = new DebitCard();
	    System.out.println("This is your card number: " + card.cardNum);
	    System.out.println("This is your pin number: " + card.cardPin);
	    System.out.println("Remember! Don't lose this information!");
	    return true;
	}
	else return false;
    }

    //***********************************************************

    //*******************checkout methods************************

    public void listPrevRentals(ArrayList<String []> prevRentals) {
	System.out.println("You have not returned the following:");
	for (int i = 0; i < prevRentals.size(); i++) {
	    System.out.println(i + ". " + prevRentals.get(0)[i]);
	}

    }

    public ArrayList<String []> hasPrevRentals (String cardNum) {
	// check previous rentals
	ArrayList<String []> prevRentals = new ArrayList<String []>();

	// CSV File Writing
	CSVRW check = new CSVRW("Numbers.csv");
	for (int i = 0; i < check.size() - 1; i++) {
	    if (check.get(i,0).equals(cardNum)) {
		System.out.println(check.get(i,3).split("|")[0]);
		prevRentals.add(check.get(i,3).split("|"));
	    }
	    if (prevRentals.size() > 0) {
		return prevRentals;
	    }
	}
	return prevRentals;
    }


    public void checkout() {
	// user checks out movies
	this.receipt();
	// cart is now emptied
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

    public void returnPrev() {
	String prev = prevRentals(this.getcardNum());
	CSVRW check = new CSVRW("Numbers.csv");
	for (int i = 0; i < check.size() - 1; i++) {
	    if (check.get(i,3).equals(prev)) {
		check.set(i, 3, "_");
		check.write("Numbers.csv");
		System.out.println("You have successfully returned your previous rentals!\n\n\n\n\n");
		break;
	    }
	}
    }

    public void receipt() {
	// print out a receipt for the user
	int numRentals = 0;
	System.out.println("Thank you for shopping with us number " + card.cardNum);
	System.out.println("You rented ");
	for (int itemCount = 0; itemCount < cart.size(); itemCount++) {
	    System.out.println( (itemCount + 1) + ". " + cart.get(itemCount));
	    numRentals++;
	    card.genMovie(card.cardNum, cart.get(itemCount), this.movieDue() + "");
	}
	System.out.println(card.deduct(card.cardNum, price * numRentals));
	System.out.println("Have a nice day!");

    }

    public void listPrevRentals(String prevRentals) {
  
	int numOwed = countOfChar(prevRentals, "|");
	if (numOwed > 0) {
	    System.out.println("You have not returned the following:");
	    String prev = prevRentals;
	    int index = prev.indexOf("|");
	    int count = 1;
	    while (numOwed > 0) {
		System.out.println(count + ". " + prev.substring(0, index));
		prev = prev.substring(index + 1);
		index = prev.indexOf("|");
		count++;
		numOwed--;
	    }
	}
    }
  
    
	
	
	
    // returns count of a Char present in a String
	public int countOfChar (String str, String search) {
	int count = 0;
	char letter = search.charAt(0);
	for (int i = 0; i < str.length(); i++) {
	    if (str.charAt(i) == letter) {
		count++;
	    }
	}
	return count;
    }
  
    // returns the previously rented movies associated with inputted cardNum
    
	public String prevRentals (String cardNum) {
	// check previous rentals
	String ret;
	// CSV File Writing
 
	CSVRW check = new CSVRW("Numbers.csv");
	for (int i = 0; i < check.size() - 1; i++) {
	    if (check.get(i,0).equals(cardNum)) {
		ret = check.get(i,3);
		return ret;
	    }
 
	}
	return "";
    }
 
    // accessor method to get current card's cardNum
    public String getcardNum () {
	return card.cardNum;
	
	    }

    //***********************************************************

    //************************Calendar methods*******************
    public void fastForward() {
	System.out.println("How many days would you like to fast forward?");
	String strdaystoskip = Keyboard.readString();
	try {
	    int daystoskip = Integer.parseInt(strdaystoskip);
	    if (daystoskip < 1) {
		System.out.println("Whoa, you can't go backwards xd");
		return;
	    }
	    else if (daystoskip > 15) {
		System.out.println("That's a bunch of late fees you'll " +
				   "have to pay, but you do you xd");
	    }
	    c.add(c.DAY_OF_YEAR, daystoskip);
	    System.out.println("It is now\n" +
			       c.get(Calendar.MONTH) + "/" +
			       c.get(Calendar.DATE) + "/" +
			       c.get(Calendar.YEAR));
	}
	catch (Exception e) {
	    System.out.println("Whoa, that's not a number xd");
	    return;
	}
    }//end fastForward
    
    public int movieDue() {
        return c.get(c.DAY_OF_YEAR) + 7;
    }//end movieDue

    
    //***********************************************************
    public void go() {
	// start Kiosk
	while (true) {
	    System.out.println("Do you have a DebitCard?");
	    System.out.println("1. yes");
	    System.out.println("2. no");
	    int resp = Keyboard.readInt();
	    if (resp == 2) {
		if (!(this.generateCard())) return;
		System.out.println("\n\n\n\n\n\n");
		break;
	    }
	    else if (resp == 1) {
		System.out.println("Please enter your DebitCard number: ");
		String num = Keyboard.readString();
		System.out.println("Now your pin: ");
		String pin = Keyboard.readString();
		if (this.isValidNum(num) && this.isValidPin(pin)) {
		    card = new DebitCard(num, pin);
		    System.out.println("success\n\n\n\n\n");
		    break;
		}
		else {
		    System.out.println("fail");
		}
	    }
	}
	Movie _movies = new Movie();
	this.home(_movies);
	
    }
    
}//end class
