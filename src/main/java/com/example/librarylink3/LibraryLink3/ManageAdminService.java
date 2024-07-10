package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageAdminService {

    @Autowired
    private ManageAdminRepository manageAdminRepository;

    public List<Admin> findAllAdmins() {
        return manageAdminRepository.findAll();
    }

    public Admin findAdminByUsername(String username) {
        return manageAdminRepository.findById(username).orElse(null);
    }

    public void saveAdmin(Admin admin) {
        manageAdminRepository.save(admin);
    }

    public void deleteAdmin(String username) {
        manageAdminRepository.deleteById(username);
    }
}

