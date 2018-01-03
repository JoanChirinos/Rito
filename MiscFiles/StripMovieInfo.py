##     ____     _    __         
##    / __ \   (_)  / /_   ____ 
##   / /_/ /  / /  / __/  / __ \
##  / _, _/  / /  / /_   / /_/ /
## /_/ |_|  /_/   \__/   \____/ 
##                              
## ------------------------------------
## Aaron Li, Johnny Wong, Joan Chirinos
## ------------------------------------

from bs4 import BeautifulSoup
from urllib2 import urlopen
import urllib

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

def getmovieinfo():
    f = open('movies.csv', 'rU')
    movies = f.read()
    f.close()
    rewrite(movies, 'movies.csv')
    movies = movies.split("\n")
    print len(movies)
    movienames = []
    for i in movies:
        movienames = i.split(",")[0]
        movienames = movienames.replace(" ", "_")
        movienames += "(film)\n"
    

    f = open('test', 'w')
    f.write(movienames)
    f.close()

    












    
