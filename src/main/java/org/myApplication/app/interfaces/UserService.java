package org.myApplication.app.interfaces;

import org.myApplication.app.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    User changeUser(User changedUser);
}
