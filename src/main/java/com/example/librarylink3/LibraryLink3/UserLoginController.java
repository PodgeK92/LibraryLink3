package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showUserLoginForm() {
        return "user_login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateUser(username, password)) {
            model.addAttribute("username", username);
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "user_login";
        }
    }
}

