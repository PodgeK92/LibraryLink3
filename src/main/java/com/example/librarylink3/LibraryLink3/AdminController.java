package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/login")
    public String showAdminLoginForm() {
        return "admin_login";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam String username, @RequestParam String password, Model model) {
        if (adminService.validateAdmin(username, password)) {
            model.addAttribute("username", username);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard";
    }

    @GetMapping("/admin/create")
    public String showCreateAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin_create";
    }

    @PostMapping("/admin/create")
    public String createAdmin(@ModelAttribute Admin admin) {
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);
        adminService.save(admin);
        return "redirect:/admin/dashboard";
    }
}
