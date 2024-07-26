package com.example.librarylink3.LibraryLink3;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book_loans")
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_loan_id")
    private Integer bookLoanId;

    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "isbn")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "card_number_id", referencedColumnName = "card_number_id")
    private User user;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "renewals_number")
    private int renewalsNumber;

    @Column(name = "returned_on_date")
    private LocalDate returnedOnDate;

    // Getters and Setters

    public Integer getBookLoanId() {
        return bookLoanId;
    }

    public void setBookLoanId(Integer bookLoanId) {
        this.bookLoanId = bookLoanId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getRenewalsNumber() {
        return renewalsNumber;
    }

    public void setRenewalsNumber(int renewalsNumber) {
        this.renewalsNumber = renewalsNumber;
    }

    public LocalDate getReturnedOnDate() {
        return returnedOnDate;
    }

    public void setReturnedOnDate(LocalDate returnedOnDate) {
        this.returnedOnDate = returnedOnDate;
    }
}
