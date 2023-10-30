package org.myApplication.app.services;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.myApplication.app.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void changeUser() {
        userService.saveUser(new User(1L, "Bob", "boB7523", "@BobTg", "Moscow", "Oktyabrsky", new ArrayList<>()));
        userService.changeUser(new User(1L, "Bob", "11111", "@BobTg", "Moscow", "Oktyabrsky", new ArrayList<>()));
        assertEquals("11111", userService.findUserById(1L).get().getPassword());
    }

    @Test
    void saveUser() {
        User user = new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>());
        User dbUser = userService.saveUser(user);
        assertNotNull(dbUser);
        assertEquals("SaveTestNick321", dbUser.getNickname());
    }

    @Test
    void deleteAllUsers() {
        var firstUser = new User(1L, "TestNick321", "Test128956", "@TestTg", "Moscow", "Oktyabrsky", new ArrayList<>());
        var secondUser = new User(2L, "TestBob321", "Test126236", "@TestTg2", "Moscow", "Oktyabrsky", new ArrayList<>());
        var thirdUser = new User(3L, "TestSem321", "Test1235986", "@TestTg3", "Moscow", "Oktyabrsky", new ArrayList<>());
        userService.saveUser(firstUser);
        userService.saveUser(secondUser);
        userService.saveUser(thirdUser);
        userService.deleteAllUsers();
        assertEquals(new ArrayList<User>(), userService.findAllUsers());
    }

    @Test
    void findUserById() {
        User user = new User(1L, "TestNick321", "Test128956", "@TestTg", "Moscow", "Oktyabrsky", new ArrayList<>());
        userService.saveUser(user);
        var dbUser = userService.findUserById(1L);
        assertNotNull(dbUser);
        assertEquals("TestNick321", dbUser.get().getNickname());
    }

    @Test
    void findAllUsers() {
        var firstUser = new User(1L, "TestNick321", "Test128956", "@TestTg", "Moscow", "Oktyabrsky", new ArrayList<>());
        var secondUser = new User(2L, "TestBob321", "Test126236", "@TestTg2", "Moscow", "Oktyabrsky", new ArrayList<>());
        var thirdUser = new User(3L, "TestSem321", "Test1235986", "@TestTg3", "Moscow", "Oktyabrsky", new ArrayList<>());
        userService.saveUser(firstUser);
        userService.saveUser(secondUser);
        userService.saveUser(thirdUser);

        List<User> usersList = new ArrayList<>();
        usersList.add(firstUser);
        usersList.add(secondUser);
        usersList.add(thirdUser);

        var dbUsers = userService.findAllUsers();
        assertNotNull(dbUsers);
        assertEquals(usersList, dbUsers);
    }

    @Test
    void deleteUserById() {
        userService.saveUser(new User(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", new ArrayList<>()));
        userService.deleteUserById(1L);
        assertEquals(new ArrayList<User>(), userService.findAllUsers());
    }
}