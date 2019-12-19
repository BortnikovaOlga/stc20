package ru.bortnikova.task16.DAO.User;

import ru.bortnikova.task16.Entety.User;

/**
 * интерфейс для объекта User
 * @author Bortnikova Olga
 */
public interface UserDao {
    boolean add(User user) ;
    boolean add(User[] userArr);
}
