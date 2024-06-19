package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByUsername(String username) {
        return adminRepository.findById(username).orElse(null);
    }

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public boolean validateAdmin(String username, String password) {
        Admin admin = adminRepository.findById(username).orElse(null);
        return admin != null && admin.getPassword().equals(password);
    }
}

