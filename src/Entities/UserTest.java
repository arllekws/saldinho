package Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User("Hugo Ponciano", "hugo@example.com", "password123");

        assertEquals("Hugo Ponciano", user.getName());

        assertEquals("hugo@example.com", user.getEmail());

        assertEquals("password123", user.getPassword());
    }
}
