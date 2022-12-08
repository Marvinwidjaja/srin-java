# srin-java

Application is deployed on https://srin-java.herokuapp.com/

Import [SRIN.postman_collections.json](https://github.com/Marvinwidjaja/srin-java/blob/master/SRIN.postman_collection.json) into Postman to test each API endpoints available


Available API endpoints are 


GET

"https://srin-java.herokuapp.com/books" - Get all available books

GET 

"https://srin-java.herokuapp.com/findGenre/$GenreName" - Get books with $GenreName. $GenreName is case-sensitive therefore be careful with capitalization

POST

"https://srin-java.herokuapp.com/addBook" - add books with the following JSON raw body</br>(String title, String author, String genre, Integer pages, Integer year_published)
<pre>
{</br>
   "title": "Book_NAME",</br>
   "author": "AUTHOR_NAME",</br>
   "genre": "GENRE", </br>
   "pages": $PAGES,</br>
   "year_published" : $YEAR_PUBLISHED</br>
}
</pre>
