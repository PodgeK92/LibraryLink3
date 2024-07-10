package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManageAdminController {

    @Autowired
    private ManageAdminService manageAdminService;

    @GetMapping("/admin/manage_admins")
    public String showManageAdminsPage(Model model) {
        return "manage_admins";
    }

    @GetMapping("/admin/get_admins")
    @ResponseBody
    public List<Admin> getAdmins() {
        return manageAdminService.findAllAdmins();
    }

    @GetMapping("/admin/get_admin")
    @ResponseBody
    public Admin getAdmin(@RequestParam("username") String username) {
        return manageAdminService.findAdminByUsername(username);
    }

    @PostMapping("/admin/save_admin")
    @ResponseBody
    public ApiResponse saveAdmin(@RequestBody Admin admin) {
        try {
            manageAdminService.saveAdmin(admin);
            return new ApiResponse(true, "Admin saved successfully");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @DeleteMapping("/admin/delete_admin")
    @ResponseBody
    public ApiResponse deleteAdmin(@RequestParam("username") String username) {
        try {
            manageAdminService.deleteAdmin(username);
            return new ApiResponse(true, "Admin deleted successfully");
        } catch (Exception e) {
            return new ApiResponse(false, e.getMessage());
        }
    }
}
