package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByCardNumberId(String cardNumberId) {
        return userRepository.findById(cardNumberId).orElse(null);
    }

    public void updateUserProfile(User user) {
        User existingUser = userRepository.findById(user.getCardNumberId()).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            existingUser.setStreetName(user.getStreetName());
            existingUser.setTownCity(user.getTownCity());
            existingUser.setCounty(user.getCounty());
            userRepository.save(existingUser);
        }
    }
}
