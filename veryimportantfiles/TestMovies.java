import jutils.Keyboard;

public class TestMovies {

    public static void main(String[] args) {
	Movie m = new Movie();
	System.out.print("Search term(s): ");
	String input = Keyboard.readString();
	if (m.search(input)) {
	    while (true) {
		System.out.println("Enter a number for more info, or \"back\""+
				   " to go back");
		String choice = Keyboard.readString();
		if (choice.toLowerCase().equals("back")) break;
		else m.moreInfo(Integer.parseInt(choice));
	    }
	}
    }

}//end class
