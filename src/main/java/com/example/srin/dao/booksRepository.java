package com.example.srin.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.srin.entity.Books;
@Repository

public interface booksRepository extends MongoRepository<Books, String>{
    public List<Books> findBooksByGenre(String genre);
    @Query("{'title':?0}")
    Optional<Books> findBooksByTitle(String title);
    @Query("{'id':{$ne:?0},'title':?1,'author':?2}")
    Optional<Books> findDuplicates(String id,String title,String author);
    @Query("{'title':?0,'author':?1}")
    Optional<Books> findDuplicatesWithoutID(String title,String author);
}
