package com.example.librarylink3.LibraryLink3;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("AB")
public class AudioBook extends Material {
    @Column(name = "genre")
    private String genre;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "language")
    private String language;

    // Constructors
    public AudioBook() {}

    public AudioBook(String title, String authorProducer, String status, String branchId, String ageCategory, String shelfLocation, String genre, String isbn, String language) {
        super(title, authorProducer, status, branchId, ageCategory, shelfLocation);
        this.genre = genre;
        this.isbn = isbn;
        this.language = language;
    }

    // Getters and Setters
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
