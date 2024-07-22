package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String from, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("librarylink03@gmail.com"); // Always send to this email
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(from); // The sender's email
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error details
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }
    }
}

