package com.codesoft.edu.service;

import com.codesoft.edu.model.Role;
import com.codesoft.edu.model.ToDo;
import com.codesoft.edu.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToDoServiceTests {

    ToDoService toDoService;
    UserService userService;
    private static ToDo testToDo;
    private static User validUser;

    private static Role role;


    @Autowired
    public ToDoServiceTests(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @BeforeEach
    public void setup() {
        if (role == null) {
            role = new Role();
            role.setName("NEW_ROLE");
        }

        if (validUser == null) {
            User newUser = new User();
            newUser.setRole(role);
            newUser.setFirstName("Mykola");
            newUser.setLastName("Bielousov");
            newUser.setEmail("mykola@gmail.com");
            newUser.setPassword("H1b3rn14n!");
            validUser = userService.create(newUser);
        }

        if (testToDo == null) {
            ToDo toDo = new ToDo();
            toDo.setTitle("Other");
            toDo.setOwner(validUser);
            testToDo = toDoService.create(toDo);
        }
    }

    @Test
    public void createTest() {
        assertNotNull(testToDo);
        assertNotNull(testToDo.getId());
    }

    @Test
    public void readByIdTest() {
        ToDo foundToDo = toDoService.readById(testToDo.getId());
        assertEquals(testToDo.getId(), foundToDo.getId());
    }

    @Test
    public void updateTest() {
        testToDo.setTitle("Updated ToDo");
        ToDo updatedToDo = toDoService.update(testToDo);
        assertEquals("Updated ToDo", updatedToDo.getTitle());
    }

    @Test
    public void deleteTest() {
        toDoService.delete(testToDo.getId());
        assertNull(toDoService.readById(testToDo.getId()));
    }

    @Test
    public void getAllTest() {
        List<ToDo> toDos = toDoService.getAll();
        assertFalse(toDos.isEmpty());
    }

    @Test
    public void getAllToDosByUserIdTest() {
        int expectedSize = 2;
        List<ToDo> toDos = toDoService.getByUserId(5L);
        assertEquals(expectedSize, toDos.size());
    }
}
