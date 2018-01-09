##     ____     _    __         
##    / __ \   (_)  / /_   ____ 
##   / /_/ /  / /  / __/  / __ \
##  / _, _/  / /  / /_   / /_/ /
## /_/ |_|  /_/   \__/   \____/ 
##                              
## ------------------------------------
## Aaron Li, Johnny Wong, Joan Chirinos
## ------------------------------------

##FORMAT:
## movieName^releaseYear^actor1,actor2,...^...

from bs4 import BeautifulSoup
from urllib2 import urlopen
import urllib
import time

def makeUrlList(url):
    names = []
    soup = make_soup(url)
    names += [[a for a in soup.findAll('a')]]
    return names
def makeurllists():
    names = []
    urllist = ["https://www.rottentomatoes.com/top/bestofrt/?year=2017", "https://www.rottentomatoes.com/top/bestofrt/?year=2016", "https://www.rottentomatoes.com/top/bestofrt/?year=2015", "https://www.rottentomatoes.com/top/bestofrt/?year=2014", "https://www.rottentomatoes.com/top/bestofrt/?year=2013", 'https://www.rottentomatoes.com/top/bestofrt/?year=2012', 'https://www.rottentomatoes.com/top/bestofrt/?year=2011', 'https://www.rottentomatoes.com/top/bestofrt/?year=2010', 'https://www.rottentomatoes.com/top/bestofrt/?year=2009', 'https://www.rottentomatoes.com/top/bestofrt/?year=2008', 'https://www.rottentomatoes.com/top/bestofrt/?year=2007', 'https://www.rottentomatoes.com/top/bestofrt/?year=2006']
    for url in urllist:
        names += makeUrlList(url)
    return names
def make_soup(url):
    html = urlopen(url).read()
    return BeautifulSoup(html, 'lxml')
def rewrite(torewrite, fname):
    f = open(fname, "w")
    f.write(torewrite)
    f.close()


## now gets lead actors but i was too lazy to change the names
def getdirector(movie):
    try:
        url = getlink(movie)
        if url == "none":
            return "unknown"
        soup = make_soup(url)
        soup = str(soup)
        soup = soup.split('Stars:')[1]
        soup = soup.split("</div>")[0]
        soup = soup.split('itemprop="name">')[1:]
        for i in range(len(soup)):
            soup[i] = soup[i].split("</span>")[0]
        soup = str(soup).replace("'", "").replace('"', "")
        return soup[1:len(soup) - 1]
    except:
        x = 1
    return "unknown"


def getlink(movie):
    try:
        url = "http://www.imdb.com/find?q=" + movie
        soup = make_soup(url)
        soup = str(soup)
        soup = soup.split('<a href="/title/')[3]
        soup = soup.split('/')[0]
        url = "http://www.imdb.com/title/" + soup
        return url
    except:
        l = 1
    return "none"


##now change to addActors:
  ##...,director,[actor1,actor2,...]
def addDirectors():
    start = time.time()
    f = open('movies.csv', 'rU')
    movielist = f.read();
    f.close()
    rewrite(movielist, 'movies.csv')

    movielist = movielist.split("\n")
    for i in xrange(len(movielist)):
        movielist[i] = movielist[i].split("^")
        director = getdirector(movielist[i][0].replace(" ", "+"))
        movielist[i] += [director]
        movielist[i] = "^".join(movielist[i])
    movielist = "\n".join(movielist)

    f = open('test', 'w')
    f.write(movielist)
    f.close()
    end = time.time()
    try:
        minutes = (int((end - start)/60))
        seconds = end - start - (minutes * 60)
        print "Time taken: " + str(minutes) + ":" + str(round(seconds, 3))
        print "In seconds: " + str(round(seconds, 3))
    except:
        lmao = 1

def fixformat():
    f = open('movies.csv', 'rU')
    s = f.read()
    f.close()
    rewrite(s, 'movies.csv')
    s = s.replace(",", "^")
    s = s.split("\n")
    for i in xrange(len(s)):
        if len(s[i].split("^")) > 3:
            s[i] = "LMAOOOOOOO"
    s = "\n".join(s)
    f = open('moviest.csv', 'w')
    f.write(s)
    f.close()
    print len(s.split("\n"))

##so far movies.csv is in format:
##movieName,year,director







    
