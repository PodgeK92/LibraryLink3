package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findById(username).orElse(null);
        return user != null && user.getPassword().equals(password);
    }
}
