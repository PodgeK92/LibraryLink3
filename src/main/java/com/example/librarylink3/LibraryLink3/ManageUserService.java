package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageUserService {

    @Autowired
    private ManageUserRepository manageUserRepository;

    public List<User> findAllUsers() {
        return manageUserRepository.findAll();
    }

    public User findUserById(String cardNumberId) {
        return manageUserRepository.findById(cardNumberId).orElse(null);
    }

    public void saveUser(User user) {
        manageUserRepository.save(user);
    }

    public void deleteUser(String cardNumberId) {
        manageUserRepository.deleteById(cardNumberId);
    }

    public int getTotalUsersCount() {
        return (int) manageUserRepository.count();
    }
}

