package org.myApplication.app.interfaces;

import org.myApplication.app.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Метод сохранения нового пользователя в базу данных
     *
     * @param user новый пользователь
     * @return возвращает сохраненного пользователя
     */
    User saveUser(User user);

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
    Optional<User> findUserById(Long id);

    /**
     * Метод удаления всех пользователей из базы данных
     */
    void deleteAllUsers();

    /**
     * Метод получения всех пользователей из базы данных
     *
     * @return возвращает список всех пользователей в базе данных
     */
    List<User> findAllUsers();

    /**
     * Метод изменения данных пользователя в базе данных
     *
     * @param changedUser пользователь с измененными данными
     * @return возвращает измененного пользователя
     */
    User changeUser(User changedUser);
}
