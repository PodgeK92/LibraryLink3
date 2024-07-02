package com.example.librarylink3.LibraryLink3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user/dashboard")
    public String showUserDashboard() {
        return "user_dashboard";  // This should match the name of your user dashboard HTML file
    }

    @GetMapping("/book_search")
    public String showBookSearch() {
        return "book_search";  // This should match the name of your book search HTML file
    }

    @GetMapping("/books_loaned")
    public String showBooksLoaned() {
        return "books_loaned";  // This should match the name of your books loaned HTML file
    }

    @GetMapping("/update_profile")
    public String showUpdateProfile() {
        return "update_profile";  // This should match the name of your update profile HTML file
    }
}
