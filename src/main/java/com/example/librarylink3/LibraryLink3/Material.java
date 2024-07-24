package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@Table(name = "Material")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "material_type")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long materialId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author_producer")
    private String authorProducer;

    @Column(name = "status")
    private String status;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "age_category")
    private String ageCategory;

    @Column(name = "shelf_location")
    private String shelfLocation;

    // Constructors
    public Material() {}

    public Material(String title, String authorProducer, String status, String branchId, String ageCategory, String shelfLocation) {
        this.title = title;
        this.authorProducer = authorProducer;
        this.status = status;
        this.branchId = branchId;
        this.ageCategory = ageCategory;
        this.shelfLocation = shelfLocation;
    }

    // Getters and Setters
    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorProducer() {
        return authorProducer;
    }

    public void setAuthorProducer(String authorProducer) {
        this.authorProducer = authorProducer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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
}
