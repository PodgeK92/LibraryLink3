package com.example.librarylink3.LibraryLink3;

public class RegistrationAPIResponse {
    private boolean success;
    private String message;
    private String cardNumberId;

    public RegistrationAPIResponse(boolean success, String message, String cardNumberId) {
        this.success = success;
        this.message = message;
        this.cardNumberId = cardNumberId;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCardNumberId() {
        return cardNumberId;
    }

    public void setCardNumberId(String cardNumberId) {
        this.cardNumberId = cardNumberId;
    }
}

