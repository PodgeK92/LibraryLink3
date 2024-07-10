package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/user/update_profile")
    public String showUpdateProfilePage(Model model, @RequestParam(value = "cardNumberId", required = false) String cardNumberId) {
        if (cardNumberId != null) {
            User user = userProfileService.findUserByCardNumberId(cardNumberId);
            model.addAttribute("user", user);
        }
        return "update_profile";
    }

    @PostMapping("/user/update_profile")
    public String updateProfile(@ModelAttribute User user, Model model) {
        try {
            userProfileService.updateUserProfile(user);
            model.addAttribute("successMessage", "Profile updated successfully.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to update profile: " + e.getMessage());
        }
        return "update_profile";
    }
}



