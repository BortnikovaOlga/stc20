package ru.bortnikova.task15;

import ru.bortnikova.task15.ConnectionManager.ConnectionManagerJdbcImpl;
import ru.bortnikova.task15.DAO.Role.RoleDao;
import ru.bortnikova.task15.DAO.Role.RoleDaoJdbcImpl;
import ru.bortnikova.task15.DAO.User.UserDao;
import ru.bortnikova.task15.DAO.User.UserDaoJdbcImpl;
import ru.bortnikova.task15.DAO.UserRole.UserRoleDao;
import ru.bortnikova.task15.DAO.UserRole.UserRoleDaoJdbcImpl;
import ru.bortnikova.task15.Entety.Role;
import ru.bortnikova.task15.Entety.User;
import ru.bortnikova.task15.Entety.UserRole;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * демо - работа с БД
 *
 * @author Bortnikova Olga
 */
public class DZ_15 {

    public static void main(String[] args) throws SQLException {
        //создаем таблицы
        UserDaoJdbcImpl.createDB();
        RoleDaoJdbcImpl.createBD();
        UserRoleDaoJdbcImpl.createDB();

        // создаем объекты управления таблицами
        RoleDao roleDao = new RoleDaoJdbcImpl();
        UserDao userDao = new UserDaoJdbcImpl();
        UserRoleDao userroleDao = new UserRoleDaoJdbcImpl();


        User[] userArr = new User[]{
                new User(1, "Anton", "1990-12-11", 123, "Innopolis", "AntonK@mail.ru", ""),
                new User(2, "Marya", "1996-07-15", 124, "Kazan", "Marya@mail.ru", ""),
                new User(3, "Olga", "1979-09-28", 125, "Rubtsovsk", "OlgaB@mail.ru", "")};

        // вставить записи в таблицу users из массива userArr
        userDao.add(userArr);

        // вставить записи справочник ролей
        roleDao.add(new Role(1, "Administration", ""));
        roleDao.add(new Role(2, "Clients", ""));
        roleDao.add(new Role(3, "Billing", ""));

        // заполнить таблицу соответствия ролей для пользователей
        userroleDao.add(new UserRole(1, 1));
        userroleDao.add(new UserRole(2, 3));
        userroleDao.add(new UserRole(3, 2));


        // переход на ручное управление коммитами
        Connection connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
        connect.setAutoCommit(false);

        // попробуем добавить неверные данные, для этого объявим нового пользователя
        User user = new User(4, "Nyusha", "2019-04-01", 126, "Smeshariki", "", "");
        UserRole userRole = new UserRole(4, 4); // роль 4 не существует в справочнике ролей

        // точка для отката транзакции
        Savepoint savepoint = connect.setSavepoint("A");

        if (userDao.add(user) & userroleDao.add(userRole))
            connect.commit(); // если записи добавлены, делаем коммит
        else
            connect.rollback(savepoint); // иначе откат

        connect.close();
    }
}



