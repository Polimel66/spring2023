package org.myApplication.app.services;

import org.junit.jupiter.api.Test;
import org.myApplication.app.entity.UserEntity;
import org.myApplication.domain.enums.Genres;
import org.myApplication.domain.enums.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    @Sql({"/users_data.sql"})
    void changeUser() {
        userService.changeUser(new UserEntity(1L, "Bob", "11111", "@BobTg", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()), 1L);
        assertEquals("11111", userService.findUserById(1L).get().getPassword());
    }

    @Test
    void saveUser() {
        UserEntity user = new UserEntity(1L, "SaveTestNick321", "SaveTest128956", "@SaveTestTg", "SaveMoscow", "SaveOktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>());
        UserEntity dbUser = userService.saveUser(user);
        assertNotNull(dbUser);
        assertEquals("SaveTestNick321", dbUser.getNickname());
    }

    @Test
    @Sql({"/users_data.sql"})
    void deleteAllUsers() {
        userService.deleteAllUsers();
        assertEquals(new ArrayList<UserEntity>(), userService.findAllUsers());
    }

    @Test
    @Sql({"/users_data.sql"})
    void findUserById() {
        var dbUser = userService.findUserById(1L);
        assertNotNull(dbUser);
        assertEquals("TestNick321", dbUser.get().getNickname());
    }

    @Test
    @Sql({"/users_data.sql"})
    void findAllUsers() {
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(new UserEntity(1L, "TestNick321", "Test128956", "@TestTg", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()));
        usersList.add(new UserEntity(2L, "TestBob321", "Test126236", "@TestTg2", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()));
        usersList.add(new UserEntity(3L, "TestSem321", "Test1235986", "@TestTg3", "Moscow", "Oktyabrsky", List.of(Genres.DETECTIVE), new ArrayList<>()));
        var dbUsers = userService.findAllUsers();
        assertNotNull(dbUsers);
        assertIterableEquals(usersList, dbUsers);
    }

    @Test
    @Sql({"/users_data.sql"})
    void deleteUserById() {
        userService.deleteUserById(1L);
        assertEquals(Optional.empty(), userService.findUserById(1L));
    }
}