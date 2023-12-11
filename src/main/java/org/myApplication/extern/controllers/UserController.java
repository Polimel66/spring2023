package org.myApplication.extern.controllers;

import org.myApplication.app.interfaces.UserService;
import org.myApplication.extern.converters.BookConverter;
import org.myApplication.extern.converters.UserConverter;
import org.myApplication.extern.models.BookModel;
import org.myApplication.extern.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private BookConverter bookConverter;

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel newUser) {
        return userConverter.toModel(userService.saveUser(userConverter.toEntity(newUser)));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable("id") Long id) {
        return userConverter.toModel(userService.findUserById(id).get());
    }

    @PutMapping("/{userId}")
    public UserModel changeUser(@RequestBody UserModel changedUser, @PathVariable("userId") Long id) {
        return userConverter.toModel(userService.changeUser(userConverter.toEntity(changedUser), id));
    }

    @GetMapping
    public List<UserModel> findAllUsers() {
        return userService.findAllUsers().stream().map(userEntity -> userConverter
                .toModel(userEntity)).collect(Collectors.toList());
    }

    @GetMapping("/getBooks/{id}")
    public List<BookModel> getBooks(@PathVariable("id") Long id) {
        return userService.findUserById(id).get().getBooks().stream()
                .map(bookEntity -> bookConverter.toModel(bookEntity)).collect(Collectors.toList());
    }

    @GetMapping("/")
    public String home() {
        return "Hello, User Home!";
    }

    @GetMapping("/secured")
    public String secured() {
        return "Hello, User Secured!";
    }
}
