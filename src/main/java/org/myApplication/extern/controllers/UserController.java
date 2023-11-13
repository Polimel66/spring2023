package org.myApplication.extern.controllers;

import org.myApplication.app.Entities.UserEntity;
import org.myApplication.app.repositories.UserRepository;
import org.myApplication.extern.Converters.BookConverter;
import org.myApplication.extern.Converters.UserConverter;
import org.myApplication.extern.Models.BookModel;
import org.myApplication.extern.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private BookConverter bookConverter;

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel newUser) {
        return userConverter.toModel(userRepository.save(userConverter.toEntity(newUser)));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable("id") Long id) {
        return userConverter.toModel(userRepository.findById(id).get());
    }

    @PostMapping("/change/{id}")
    public void changeUser(@RequestBody UserModel changedUser, @PathVariable("id") Long id) {
        UserEntity changedUserEntity = userConverter.toEntity(changedUser);
        changedUserEntity.setId(id);
        userRepository.save(changedUserEntity);
    }

    @GetMapping("/all")
    public List<UserModel> findAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> userConverter.toModel(userEntity))
                .collect(Collectors.toList());
    }

    @GetMapping("/getBooks/{id}")
    public List<BookModel> getBooks(@PathVariable("id") Long id) {
        return userRepository.findById(id).get().getBooks().stream().map(bookEntity -> bookConverter.toModel(bookEntity))
                .collect(Collectors.toList());
    }
}
