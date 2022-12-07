package com.example.srin.services;

import java.util.List;

import com.example.srin.entity.Books;
import com.example.srin.exceptions.BookExceptions;

import jakarta.validation.ConstraintViolationException;


public interface BookService {
    public List<Books> getAllBooks();
    public void addBook(Books books) throws ConstraintViolationException, BookExceptions;
    public void updateBook(String id,Books books) throws BookExceptions; 
    public void deleteBook(String id) throws BookExceptions;
}
 