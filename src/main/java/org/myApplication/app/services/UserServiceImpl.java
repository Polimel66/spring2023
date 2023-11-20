package org.myApplication.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.myApplication.app.entity.UserEntity;
import org.myApplication.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements org.myApplication.app.interfaces.UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity user) {
        var result = userRepository.save(user);
        log.info("Новый пользователь успешно сохранен");
        return result;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Пользователь успешно удален");
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        var result = userRepository.findById(id);
        log.info("Пользователь с id - {} успешно найден", id);
        return result;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        var result = userRepository.findAll();
        log.info("Все пользователи из базы успешно найдены");
        return result;
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("Все пользователи успешно удалены из базы данных");
    }

    @Override
    public UserEntity changeUser(UserEntity changedUser, Long userId) {
        return userRepository.findById(userId).map(userEntity -> {
            userEntity.setNickname(changedUser.getNickname());
            userEntity.setPassword(changedUser.getPassword());
            userEntity.setContact(changedUser.getContact());
            userEntity.setCity(changedUser.getCity());
            userEntity.setDistrict(changedUser.getDistrict());
            var result = userRepository.save(userEntity);
            log.info("Данные заданного пользователя успешно изменены");
            return result;
        }).orElseThrow(() -> new RuntimeException("\nИзменяемый пользователь не найден"));
    }
}
