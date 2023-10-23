package org.myApplication.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myApplication.app.User;
import org.myApplication.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements org.myApplication.app.interfaces.UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        log.info("Новый пользователь успешно сохранен");
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Пользователь успешно удален");
    }

    @Override
    public Optional<User> findUserById(Long id) {
        log.info("Пользователь с id - {} успешно найден", id);
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        log.info("Все пользователи из базы успешно найдены");
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("Все пользователи успешно удалены из базы данных");
    }

    @Override
    public User changeUser(User changedUser) {
        log.info("Данные заданного пользователя успешно изменены");
        return userRepository.save(changedUser);
    }
}