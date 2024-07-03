package com.example.librarylink3.LibraryLink3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByCardNumberId(String cardNumberId) {
        return userRepository.findById(cardNumberId).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public boolean validateUser(String cardNumberId, String password) {
        User user = userRepository.findById(cardNumberId).orElse(null);
        return user != null && user.getPassword().equals(password);
    }

    public String generateUniqueCardNumber(char libraryInitial) {
        Random random = new Random();
        String cardNumber;
        do {
            cardNumber = libraryInitial + String.format("%08d", random.nextInt(100000000));
        } while (userRepository.existsById(cardNumber)); // Check for uniqueness
        return cardNumber;
    }

    public User registerUser(User user, char libraryInitial) {
        user.setCardNumberId(generateUniqueCardNumber(libraryInitial));
        return userRepository.save(user);
    }

}
