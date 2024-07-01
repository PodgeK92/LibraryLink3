package com.example.librarylink3.LibraryLink3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/home")
    public String showHomePageAlias() {
        return "home";
    }
}

