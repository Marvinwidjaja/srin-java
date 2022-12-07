package com.example.srin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Data
@Document(collection="books")
public class Books {
    @Id
    private String id;
    @NotBlank(message= "Title cannot be blank") 
    private String title;
    @NotBlank(message= "Author cannot be blank")
    private String author;
    @NotBlank(message= "Genre cannot be blank")
    private String genre;
    @NotNull(message= "Pages cannot be blank")
    private Integer pages;
    @NotNull(message= " Year Published cannot be blank")
    private Integer year_published;
}
