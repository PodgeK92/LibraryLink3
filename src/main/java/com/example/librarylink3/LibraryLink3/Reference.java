package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("R")
public class Reference extends Resource {
    @Column(name = "category")
    private String category;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    // Constructors
    public Reference() {}

    public Reference(String title, String ageCategory, String shelfLocation, String branchId, String category, String isbn, String author) {
        super(title, ageCategory, shelfLocation, branchId);
        this.category = category;
        this.isbn = isbn;
        this.author = author;
    }

    // Getters and Setters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
