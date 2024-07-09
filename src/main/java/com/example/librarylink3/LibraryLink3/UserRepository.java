package com.example.librarylink3.LibraryLink3;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByCardNumberIdAndPassword(String cardNumberId, String password);

    Optional<User> findByCardNumberId(String cardNumberId);
}
