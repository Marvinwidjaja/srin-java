package com.example.srin.exceptions;

public class BookExceptions extends Exception{

    private static final long serialVersionUID = 1L;

    public BookExceptions(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return "No matching Book with the following ID " + id + " found";
    }

    public static String BookAlreadyExists(){
        return "Book with given name already exists";
    }

    public static String GenreNotFound(){
        return "Genre not available. Try searching other genre";
    }
    public static String BookIsEmpty(){
        return "There are no books available";
    }

}
