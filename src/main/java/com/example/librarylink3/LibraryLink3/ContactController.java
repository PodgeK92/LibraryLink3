package com.example.librarylink3.LibraryLink3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }
}

