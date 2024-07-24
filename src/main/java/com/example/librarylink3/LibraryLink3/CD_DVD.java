package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CD")
public class CD_DVD extends Material {
    @Column(name = "disk_quantity")
    private int diskQuantity;

    @Column(name = "category")
    private String category;

    // Constructors
    public CD_DVD() {}

    public CD_DVD(String title, String authorProducer, String status, String branchId, String ageCategory, String shelfLocation, int diskQuantity, String category) {
        super(title, authorProducer, status, branchId, ageCategory, shelfLocation);
        this.diskQuantity = diskQuantity;
        this.category = category;
    }

    // Getters and Setters
    public int getDiskQuantity() {
        return diskQuantity;
    }

    public void setDiskQuantity(int diskQuantity) {
        this.diskQuantity = diskQuantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
