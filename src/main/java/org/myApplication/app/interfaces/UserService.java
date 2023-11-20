package org.myApplication.app.interfaces;

import org.myApplication.app.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Метод сохранения нового пользователя в базу данных
     *
     * @param user новый пользователь
     * @return возвращает сохраненного пользователя
     */
    UserEntity saveUser(UserEntity user);

    /**
     * Метод удаления пользователя по id из базы данных
     *
     * @param id идентификатор пользователя, которого нужно удалить
     */
    void deleteUserById(Long id);

    /**
     * Метод получения пользователя из базы данных по id
     *
     * @param id идентификатор пользователя, которого нужно вернуть
     * @return возвращает найденного по id пользователя
     */
    Optional<UserEntity> findUserById(Long id);

    /**
     * Метод удаления всех пользователей из базы данных
     */
    void deleteAllUsers();

    /**
     * Метод получения всех пользователей из базы данных
     *
     * @return возвращает список всех пользователей в базе данных
     */
    List<UserEntity> findAllUsers();

    /**
     * Метод изменения данных пользователя в базе данных
     * @param changedUser измененный пользователь
     * @param userId идентификатор изменяемого пользователя
     * @return возвращает измененного пользователя
     */
    UserEntity changeUser(UserEntity changedUser, Long userId);
}
