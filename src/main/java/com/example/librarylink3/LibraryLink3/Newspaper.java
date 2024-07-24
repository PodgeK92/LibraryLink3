package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("N")
public class Newspaper extends Resource {
    @Column(name = "date")
    private String date;

    // Constructors
    public Newspaper() {}

    public Newspaper(String title, String ageCategory, String shelfLocation, String branchId, String date) {
        super(title, ageCategory, shelfLocation, branchId);
        this.date = date;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
