package org.myApplication.extern;

import lombok.AllArgsConstructor;
import org.myApplication.app.User;
import org.myApplication.app.interfaces.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
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
    public Optional<User> getUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public User changeUser(User changedUser) {
        return userService.changeUser(changedUser);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
