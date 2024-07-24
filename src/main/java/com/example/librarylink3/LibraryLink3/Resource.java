package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@Table(name = "Resource")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "age_category")
    private String ageCategory;

    @Column(name = "shelf_location")
    private String shelfLocation;

    @Column(name = "branch_id")
    private String branchId;

    // Constructors
    public Resource() {}

    public Resource(String title, String ageCategory, String shelfLocation, String branchId) {
        this.title = title;
        this.ageCategory = ageCategory;
        this.shelfLocation = shelfLocation;
        this.branchId = branchId;
    }

    // Getters and Setters
    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
