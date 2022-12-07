package com.example.srin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.srin.dao.booksRepository;
import com.example.srin.entity.Books;
import com.example.srin.exceptions.BookExceptions;

import jakarta.validation.ConstraintViolationException;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private booksRepository booksRepository;
    @Override
    public List<Books> getAllBooks(){
        List<Books> books= booksRepository.findAll();
        if(books.size()>0){
            return books;
        }else{
            return new ArrayList<Books>();
        }
    }
    @Override
    public void addBook(Books books) throws ConstraintViolationException,BookExceptions{
        Optional<Books> booksDuplicate = booksRepository.findDuplicatesWithoutID(books.getTitle(),books.getAuthor());
        if(booksDuplicate.isPresent()){
            throw new BookExceptions(BookExceptions.BookAlreadyExists());
        }else{
            booksRepository.save(books);
        }
        
    }
    @Override
    public void updateBook(String id, Books books) throws BookExceptions {
        Optional<Books> booksOptional= booksRepository.findById(id); 
        Optional<Books> duplicateBook = booksRepository.findDuplicates(id,books.getTitle(), books.getAuthor());
        if(booksOptional.isPresent()){
            if(duplicateBook.isPresent()){
                throw new BookExceptions(BookExceptions.BookAlreadyExists());
            }
             Books book_details= booksOptional.get();
             book_details.setTitle(books.getTitle()!=null?books.getTitle():book_details.getTitle());
             book_details.setAuthor(books.getAuthor()!=null ? books.getAuthor():book_details.getAuthor());
             book_details.setGenre(books.getGenre()!=null?books.getGenre():book_details.getGenre());
             book_details.setPages(books.getPages()!=null?books.getPages():book_details.getPages());
             book_details.setYear_published(books.getYear_published()!=null?books.getYear_published():book_details.getYear_published());
             booksRepository.save(book_details);
        }else{
            throw new BookExceptions(BookExceptions.NotFoundException(id)); 
        }
    }
    @Override
    public void deleteBook(String id) throws BookExceptions {
        Optional<Books> booksOptional= booksRepository.findById(id); 
        if(!booksOptional.isPresent()){
            throw new BookExceptions(BookExceptions.NotFoundException(id)); 
        }
        else{
            booksRepository.deleteById(id);
        }
        
    }


    
}
