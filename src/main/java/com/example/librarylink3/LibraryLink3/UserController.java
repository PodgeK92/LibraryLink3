package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String showStep2Form(Model model) {
        return "sign_up_page2";
    }

    @GetMapping("/register/step3")
    public String showStep3Form(Model model) {
        return "sign_up_page3";
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody User user) {
        System.out.println("Received user data: " + user);
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userService.save(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateUser(username, password)) {
            model.addAttribute("username", username);
            return "redirect:/inventory";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
