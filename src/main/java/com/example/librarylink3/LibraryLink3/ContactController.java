package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam String name, @RequestParam String email, @RequestParam String message, Model model) {
        String subject = "New message from " + name;
        try {
            emailService.sendEmail(email, subject, message);
            model.addAttribute("successMessage", "Thank you for your message. We will get back to you soon.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while sending your message. Please try again later.");
        }
        return "contact";
    }

    @GetMapping("/logged_in_contact")
    public String showLoggedInContactPage() {
        return "logged_in_contact";
    }

    @PostMapping("/logged_in_contact")
    public String handleLoggedInContactForm(@RequestParam String name, @RequestParam String email, @RequestParam String message, Model model) {
        String subject = "New message from " + name;
        try {
            emailService.sendEmail(email, subject, message);
            model.addAttribute("successMessage", "Thank you for your message. We will get back to you soon.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while sending your message. Please try again later.");
        }
        return "logged_in_contact";
    }

}


