package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("books")
    private User user;

    public Book(){}

    public Book(String title, String author, User user){
        this.title = title;
        this.author = author;
        this.user = user;
    }

    public Long getId(){ return id; }

    public String getTitle() { return title; }
    public void setTitle(String title){ this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public User getUser(){ return user; }
    public void setUser(User user) { this.user = user; }
}