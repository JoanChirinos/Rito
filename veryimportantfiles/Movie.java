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

public class Movie {

    private ArrayList<ArrayList<String>> _rawinfo;
    private ArrayList<String> _movieinfo;
    private ArrayList<String> _movienames;
    private ArrayList<Integer> _searchindices;

    //initializes the instance vars and fills them with required info
    public Movie() {
	_searchindices = new ArrayList<Integer>();
	_movienames = new ArrayList<String>();
	_movieinfo = new ArrayList<String>();
	_rawinfo = new ArrayList<ArrayList<String>>();
	CSVRW m = new CSVRW("MiscFiles/movies.csv");
	String mcsv = m.toString();
	for (String i : mcsv.split("\n")) {

	    //adding raw movie info split into a 2d array to _rawinfo
	    ArrayList<String> toadd = new ArrayList<String>();
	    for (String x : i.split("\\^"))
		toadd.add(x);
	    _rawinfo.add(toadd);

	    //adding movie info without carets or commas for searching
	    //to _movieinfo
	    _movieinfo.add(i.replace("^", " ").replace(",", " "));
	}

	//adding just the movie names to _movienames for pretty displaying
	for (int i = 0; i < m.get().size(); i++)
	    _movienames.add(m.get(i, 0));
    }//end constructor

    //searches through _movieinfo, grading each movie based on # of search terms
    //inside _movieinfo
    //saves the indices of the movies with the most correct search terms
    //in _searchindices, which is reset upon the start of a new search
    public boolean search(String searchterm) {

	//contains indices of best searches in _movieinfo
	_searchindices = new ArrayList<Integer>();
	searchterm = searchterm.toLowerCase();

	/*****************
	 * Movie scoring *
	 *****************/
	
	//each index in indices corresponds with the same index in _movienames,
	//_movieinfo, and _rawinfo
        int[] scores = new int[_movienames.size()];
	for (int i = 0; i < scores.length; i++) {
	    for (String s : searchterm.split(" ")) {
		//"if the search term is not a space or "the" AND the movie
		//contains the search term in its info, score the movie +1 point
		if ((!(s.equals(" "))) && (!(s.equals("the"))) &&
		    _movieinfo.get(i).toLowerCase().contains(s))
		    scores[i]++;
	    }
	}
	//add the indices of the 5 highest scoring movies to _searchindices
	for (int elcount = 0; elcount < 5; elcount++) {
	    int maxindex = 0;
	    for (int i = 0; i < scores.length; i++)
		if (scores[maxindex] < scores[i])
		    maxindex = i;
	    if (elcount == 0 && scores[maxindex] == 0) {
		System.out.println(maxindex + "  " + scores[maxindex]);
		System.out.println("No movies match your search terms");
		return false;
	    }
	    scores[maxindex] = -1;
	    _searchindices.add(maxindex);
	}

	/****************
	 * Stringifying *
	 ****************/
		
	String ret = "";
	for (int i = 0; i < _searchindices.size(); i++) {
	    ret += (i + 1) + ": " + _movienames.get(_searchindices.get(i));
	    ret += "\n";
	}
	if (ret.length() == 0) {
	    ret = "No movies match your search terms";
	    return false;
	}
	System.out.println(ret);
	return true;
    }//end search

    public boolean moreInfo(int num) {
	if (num > _searchindices.size()) {
	    System.out.println("Invalid number");
	    return false;
	}
	String ret = "\n";
	ArrayList<String> m = _rawinfo.get(_searchindices.get(num - 1));
	ret += "Movie name: " + m.get(0);
	ret += " (" + m.get(1) + ")";
	ret += "\nDirector(s): " + m.get(2);
	ret += "\nStars: " + m.get(3);
	System.out.println(ret + "\n");
	return true;
    }//end moreInfo

}//end class
