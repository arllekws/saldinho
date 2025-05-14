package Tests.Program;

import Entities.User;
import Program.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testRegisterUser_Success() {
        userService.registerUser("João", "joao@example.com", "123456789");
        User user = userService.login("joao@example.com", "123456789");
        assertNotNull(user);
        assertEquals("João", user.getName());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() {
        userService.registerUser("João", "joao@example.com", "123456789");
        userService.registerUser("Maria", "joao@example.com", "987654321");
        User user = userService.login("joao@example.com", "123456789");
        assertNotNull(user);
        assertEquals("João", user.getName());
    }

    @Test
    void testRegisterUser_WeakPassword() {
        userService.registerUser("João", "joao@example.com", "123");
        User user = userService.login("joao@example.com", "123");
        assertNull(user);
    }

    @Test
    void testLogin_Success() {
        userService.registerUser("João", "joao@example.com", "123456789");
        User user = userService.login("joao@example.com", "123456789");
        assertNotNull(user);
        assertEquals("João", user.getName());
    }

    @Test
    void testLogin_UserNotFound() {
        User user = userService.login("naoexiste@example.com", "123456789");
        assertNull(user);
    }

    @Test
    void testLogin_IncorrectPassword() {
        userService.registerUser("João", "joao@example.com", "123456789");
        User user = userService.login("joao@example.com", "senhaErrada");
        assertNull(user);
    }
}