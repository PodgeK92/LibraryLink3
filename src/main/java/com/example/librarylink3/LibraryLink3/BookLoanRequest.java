package com.example.librarylink3.LibraryLink3;

public class BookLoanRequest {
    private String isbn;
    private String cardNumberId;

    public String getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(String cardNumberId) {
        this.cardNumberId = cardNumberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

