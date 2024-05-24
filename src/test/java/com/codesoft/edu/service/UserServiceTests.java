package com.codesoft.edu.service;

import com.codesoft.edu.model.Role;
import com.codesoft.edu.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {

    UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public RoleService roleService;

    @Test
    @Order(1)
    public void getAllUsersTest() {
        int expectedSize = 3;
        List<User> users = userService.getAll();
        assertTrue(users.size() >= expectedSize, String.format("At least %d users should be in the USERS table", expectedSize));
    }

    @Test
    @Order(2)
    public void createUserTest() {

        Role role = new Role();
        role.setName("NEW_ROLE");

        User newUser = new User();
        newUser.setRole(role);
        newUser.setFirstName("Mykola");
        newUser.setLastName("Bielousov");
        newUser.setEmail("mykola@gmail.com");
        newUser.setPassword("H1b3rn14n!");
        userService.create(newUser);
        User retrievedUser = userService.readById(newUser.getId());
        assertEquals(newUser, retrievedUser, "Created user should match retrieved user");
    }

    @Test
    @Order(3)
    public void updateUserTest() {
        User userToUpdate = userService.readByEmail("mykola@gmail.com");
        long id = userToUpdate.getId();
        userToUpdate.setFirstName("Vasyl");
        userService.update(userToUpdate);
        User updatedUser = userService.readById(id);
        assertEquals("Vasyl", updatedUser.getFirstName(), "User's first name should be updated");
    }

    @Test
    @Order(4)
    public void deleteUserTest() {
        User userToDelete = userService.readByEmail("mykola@gmail.com");
        if (userToDelete != null) {
            long id = userToDelete.getId();
            userService.delete(id);
        }
        Assertions.assertNull(userService.readByEmail("mykola@gmail.com"), "Deleted user should not exist");
    }
}
