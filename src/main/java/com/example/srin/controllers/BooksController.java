package com.example.srin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.srin.dao.booksRepository;
import com.example.srin.entity.Books;
import com.example.srin.exceptions.BookExceptions;
import com.example.srin.services.BookService;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BooksController {
    @Autowired
    private final booksRepository booksRepository;
    @Autowired
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        List<Books> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, books.size()>0 ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findGenre/{genre}")
    public ResponseEntity<?> findByGenre(@PathVariable("genre") String genre){
        try{
        List<Books> books = booksRepository.findBooksByGenre(genre);
        if(books.isEmpty()){
            return new ResponseEntity<>("Genre not available. Try searching other genre",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Books books){
        try{
            bookService.addBook(books);
            return new ResponseEntity<>(books,HttpStatus.OK);
        }
        catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(BookExceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT); 
        } 
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity<>("Sucessfully deleted Book",HttpStatus.OK);
        }
        catch(BookExceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }   

    @PutMapping("/editBook/{id}")
    public ResponseEntity<?> editBook(@PathVariable("id") String id,@RequestBody Books books){
        try{
            bookService.updateBook(id, books);
            return new ResponseEntity<>("Updated Book with ID "+ id+ " successfully", HttpStatus.OK);
        }catch(ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch(BookExceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
