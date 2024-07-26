package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "LibraryUser")
public class User {
    @Id
    @Column(name = "card_number_id", nullable = false, unique = true)  // Ensure the primary key is correctly defined
    private String cardNumberId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "town_city")
    private String townCity;

    @Column(name = "county")
    private String county;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "home_branch")
    private String homeBranch;

    @Column(name = "material_quantity")
    private int materialQuantity = 0;  // Default value

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    // No-argument constructor
    public User() {
    }

    // Parameterized constructor
    public User(String cardNumberId, String firstName, String lastName, String streetName, String townCity, String county, LocalDate dob, String homeBranch, int materialQuantity, String email, String phoneNumber, String password) {
        this.cardNumberId = cardNumberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.townCity = townCity;
        this.county = county;
        this.dob = dob;
        this.homeBranch = homeBranch;
        this.materialQuantity = materialQuantity;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    // Getters and setters

    public String getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(String cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getHomeBranch() {
        return homeBranch;
    }

    public void setHomeBranch(String homeBranch) {
        this.homeBranch = homeBranch;
    }

    public int getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(int materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Method to get the full address as a single string
    public String getAddress() {
        return streetName + ", " + townCity + ", " + county;
    }

}
