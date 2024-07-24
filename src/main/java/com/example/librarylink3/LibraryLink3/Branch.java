package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;

@Entity
@Table(name = "Branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "opening_time")
    private String openingTime;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "town_city")
    private String townCity;

    @Column(name = "county")
    private String county;

    // Constructors
    public Branch() {}

    public Branch(String name, String openingTime, String streetName, String townCity, String county) {
        this.name = name;
        this.openingTime = openingTime;
        this.streetName = streetName;
        this.townCity = townCity;
        this.county = county;
    }

    // Getters and Setters
    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}

