package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    @Autowired
    private UserService userService; // Add this to use the card number generation method

    @GetMapping("/admin/manage_users")
    public String showManageUsersPage(Model model) {
        int totalMembersCount = manageUserService.getTotalUsersCount();
        model.addAttribute("totalMembersCount", totalMembersCount);
        return "manage_users";
    }

    @GetMapping("/admin/get_users")
    @ResponseBody
    public List<User> getUsers() {
        return manageUserService.findAllUsers();
    }

    @GetMapping("/admin/get_user")
    @ResponseBody
    public User getUser(@RequestParam("cardNumberId") String cardNumberId) {
        return manageUserService.findUserById(cardNumberId);
    }

    @PostMapping("/admin/save_user")
    @ResponseBody
    public ApiResponse saveUser(@RequestBody User user) {
        try {
            // Generate card number if not provided (new user)
            if (user.getCardNumberId() == null || user.getCardNumberId().isEmpty()) {
                user.setCardNumberId(userService.generateUniqueCardNumber('G'));
            }
            manageUserService.saveUser(user);
            return new ApiResponse(true, "User saved successfully");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @DeleteMapping("/admin/delete_user")
    @ResponseBody
    public ApiResponse deleteUser(@RequestParam("cardNumberId") String cardNumberId) {
        try {
            manageUserService.deleteUser(cardNumberId);
            return new ApiResponse(true, "User deleted successfully");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }
}


