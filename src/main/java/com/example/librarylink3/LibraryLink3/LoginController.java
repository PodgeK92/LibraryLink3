package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String cardNumberId, @RequestParam String password, Model model) {
        Optional<User> user = userRepository.findByCardNumberIdAndPassword(cardNumberId, password);

        if (user.isPresent()) {
            return "redirect:/user/dashboard?cardNumberId=" + cardNumberId;
        } else {
            model.addAttribute("error", "Invalid card number or password");
            return "login";
        }
    }
}
