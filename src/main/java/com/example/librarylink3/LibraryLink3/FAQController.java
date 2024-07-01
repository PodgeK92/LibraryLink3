package com.example.librarylink3.LibraryLink3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {

    @GetMapping("/faq")
    public String showFaqPage() {
        return "faq";
    }
}

