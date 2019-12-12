package ru.bortnikova.task15;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * класс для работы с таблицей users
 *
 * @author Bortnikova Olga
 */
public class User {
    private int id;
    private String name;
    private String bithday;
    private int login_id;
    private String city;
    private String email;
    private String description;

    public User(int id, String name, String bithday, int login_id, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.bithday = bithday;
        this.login_id = login_id;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    /**
     * метод добавляет новую запись в таблицу users в соответствии с состоянием объекта user
     *
     * @param connect соединение с БД
     * @return true при успешной операции, false если запись не добавлена
     */
    public boolean insertDB(Connection connect) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO users (id, name, birthday, login_id, city, email, desctiption) \n"
                        + "VALUES (?,?,?,?,?,?,?);");) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.setString(2, this.name);
            preparedStatement.setDate(3, Date.valueOf(this.bithday));
            preparedStatement.setInt(4, this.login_id);
            preparedStatement.setString(5, this.city);
            preparedStatement.setString(6, this.email);
            preparedStatement.setString(7, this.description);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * метод добавляет записи в таблицу users
     *
     * @param connect соединение с БД
     * @param user массив объектов User
     * @throws SQLException
     */
    public static void insertDB(Connection connect, User[] user) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO users (id, name, birthday, login_id, city, email, desctiption) \n"
                        + "VALUES (?,?,?,?,?,?,?);");
        for (User u : user) {
            preparedStatement.setInt(1, u.id);
            preparedStatement.setString(2, u.name);
            preparedStatement.setDate(3, Date.valueOf(u.bithday));
            preparedStatement.setInt(4, u.login_id);
            preparedStatement.setString(5, u.city);
            preparedStatement.setString(6, u.email);
            preparedStatement.setString(7, u.description);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

}
