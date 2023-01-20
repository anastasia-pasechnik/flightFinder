# project started on 1-19-23
# flightFinder

from bs4 import BeautifulSoup
import requests

url = 'https://www.expedia.com'

page = requests.get (url)
soup = BeautifulSoup (page.text, "html.parser")

# display everything that is on web page
print(soup)