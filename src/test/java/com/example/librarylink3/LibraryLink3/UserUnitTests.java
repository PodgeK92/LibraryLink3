package com.example.librarylink3.LibraryLink3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;


@SpringBootTest
public class UserUnitTests {

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        String cardNumberId = userService.generateUniqueCardNumber('G');
        String firstName = "firstName";
        String lastName = "lastName";
        String streetName = "streetName";
        String townCity = "townCity";
        String county = "county";
        LocalDate dob = LocalDate.of(2001, 1, 13);;
        String homeBranch = "Galway";
        int materialQuantity = 2;
        String email = "email@email.com";
        String phoneNumber = "phoneNumber";
        String password = "pass1";
        User newUser = new User(cardNumberId, firstName, lastName, streetName, townCity, county, dob, homeBranch, materialQuantity, email, phoneNumber, password);

        User registeredUser = userService.registerUser(newUser, 'G');
        User retrievedUser = userService.findByCardNumberId(registeredUser.getCardNumberId());

        assertTrue(retrievedUser.getFirstName().contains(firstName));
    }

}
