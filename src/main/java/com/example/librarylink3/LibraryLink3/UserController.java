package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "sign_up_page1";
    }

    @GetMapping("/register/step2")
    public String showStep2Form() {
        return "sign_up_page2";
    }

    @GetMapping("/register/step3")
    public String showStep3Form() {
        return "sign_up_page3";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<RegistrationAPIResponse> registerUser(@RequestBody User user) {
        try {
            // Assuming 'G' is the library initial for Galway City Library
            char libraryInitial = 'G';
            userService.registerUser(user, libraryInitial);
            return ResponseEntity.ok(new RegistrationAPIResponse(true, "User registered successfully", user.getCardNumberId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegistrationAPIResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage(@RequestParam("userName") String userName, @RequestParam("cardNumberId") String cardNumberId, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("cardNumberId", cardNumberId);
        return "welcome";
    }


    @GetMapping("/user/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestParam String cardNumberId, @RequestParam String password, Model model) {
        if (userService.validateUser(cardNumberId, password)) {
            model.addAttribute("cardNumberId", cardNumberId);
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Invalid card number or password");
            return "login";
        }
    }

    @GetMapping("/user/book_search")
    public String showBookSearchPage() {
        return "book_search";
    }

    @GetMapping("/user/book_search_results")
    public String showBookSearchResults(@RequestParam String bookName, @RequestParam String author,
                                        @RequestParam String isbn, @RequestParam String publisher,
                                        @RequestParam String format, @RequestParam String genre,
                                        @RequestParam String yearPublished, @RequestParam(defaultValue = "0") int page,
                                        Model model) {
        model.addAttribute("bookName", bookName);
        model.addAttribute("author", author);
        model.addAttribute("isbn", isbn);
        model.addAttribute("publisher", publisher);
        model.addAttribute("format", format);
        model.addAttribute("genre", genre);
        model.addAttribute("yearPublished", yearPublished);
        model.addAttribute("page", page);
        return "book_search_results";
    }

}
