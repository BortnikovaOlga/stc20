package ru.bortnikova.task15;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
/**
 * демо - работа с БД
 *
 * @author Bortnikova Olga
 */
public class DZ_15 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        // создать соединение с БД
        try (Connection connect = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres");) {

            // создать таблицу пользователей users
            UserDAO userDAO =new UserDAO(connect);
            userDAO.createDB();

            User[] userArr = new User[]{
                    new User(1, "Anton", "1990-12-11", 123, "Innopolis", "AntonK@mail.ru", ""),
                    new User(2, "Marya", "1996-07-15", 124, "Kazan", "Marya@mail.ru", ""),
                    new User(3, "Olga", "1979-09-28", 125, "Rubtsovsk", "OlgaB@mail.ru", "")};

            // встаить записи в таблицу users из массива userArr
            userDAO.insertDB(userArr);

            // создать таблицу справочник ролей
            RoleDAO roleDAO=new RoleDAO(connect);
            roleDAO.createBD();

            roleDAO.insert(new Role(1,"Administration",""));
            roleDAO.insert(new Role(2,"Clients",""));
            roleDAO.insert(new Role(3,"Billing",""));


            // создать и заполнить таблицу соответствия ролей для пользователей
            UserRoleDAO userRoleDAO=new UserRoleDAO(connect);
            userRoleDAO.createDB();

            userRoleDAO.insertDB(new UserRole(1, 1));
            userRoleDAO.insertDB(new UserRole(2, 3));
            userRoleDAO.insertDB(new UserRole(3, 2));

            // переход на ручное управление коммитами
            connect.setAutoCommit(false);

            // добавить сведения о новом пользователе id = 4
            User user=new User(4,"Nyusha","2019-04-01",126,"Smeshariki","","");
            UserRole userRole=new UserRole(4,4); // здесь роль 4 не существует в справочнике

            // точка для отката транзакции
            Savepoint savepoint = connect.setSavepoint("A");

            if (userDAO.insertDB(user) && userRoleDAO.insertDB(userRole))
            connect.commit(); // если записи добавлены, делаем коммит
            else
            connect.rollback(savepoint); // иначе откат

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



