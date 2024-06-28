package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

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
    public String showAdminDashboard(Model model) {
        long availableBooksCount = bookService.countBooksAvailable();
        long totalMembersCount = userService.countUsers();
        long totalAdminsCount = adminService.countAdmins();

        model.addAttribute("availableBooksCount", availableBooksCount);
        model.addAttribute("totalMembersCount", totalMembersCount);
        model.addAttribute("totalAdminsCount", totalAdminsCount);

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

    @GetMapping("/admin/inventory")
    public String showInventory(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Book> bookPage = bookService.getBooksPage(PageRequest.of(page, 5));
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", page);
        return "inventory";
    }

    @GetMapping("/countAdmins")
    public Map<String, Long> countAdmins() {
        long count = adminService.countAdmins();
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return response;
    }

    @GetMapping("/countUsers")
    public Map<String, Long> countUsers() {
        long count = userService.countUsers();
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return response;
    }

    @GetMapping("/countBooksAvailable")
    public Map<String, Long> countBooksAvailable() {
        long count = bookService.countBooksAvailable();
        Map<String, Long> response = new HashMap<>();
        response.put("count", count);
        return response;
    }

}
