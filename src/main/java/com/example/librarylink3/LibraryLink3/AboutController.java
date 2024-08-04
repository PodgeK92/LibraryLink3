package com.example.librarylink3.LibraryLink3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}

