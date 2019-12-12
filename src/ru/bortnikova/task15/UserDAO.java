package ru.bortnikova.task15;

import java.sql.*;

/**
 * работа с таблицей user
 *
 * @author Bortnikova Olga
 */
public class UserDAO {
    private Connection connect;

    private static String SQLstrStatement =
            "INSERT INTO users (id, name, birthday, login_id, city, email, desctiption) VALUES (?,?,?,?,?,?,?);";

    public UserDAO(Connection connect) {
        this.connect = connect;
    }

    /**
     * создание таблицы users - информация о пользователях
     *
     * @throws SQLException
     */
    public void createDB() throws SQLException {
        String SQLstrCreate = "DROP TABLE IF EXISTS users CASCADE ;"
                + "CREATE TABLE users ("
                + "id          integer primary key, "
                + "name        varchar(20) NOT NULL, "
                + "birthday    date, "
                + "login_id    integer, "
                + "city        varchar(20),"
                + "email       varchar(30), "
                + "desctiption varchar(50));";
        Statement statement = connect.createStatement();
        statement.execute(SQLstrCreate);
    }

    /**
     * метод добавляет новую запись в таблицу users в соответствии с состоянием объекта user
     *
     * @param user объект User
     * @return true при успешной операции, false если запись не добавлена
     */
    public boolean insertDB(User user) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(SQLstrStatement)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setDate(3, Date.valueOf(user.getBithday()));
            preparedStatement.setInt(4, user.getLogin_id());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * метод добавляет записи в таблицу users
     *
     * @param userArr массив объектов User
     * @throws SQLException
     */
    public void insertDB(User[] userArr) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(
                SQLstrStatement);
        for (User u : userArr) {
            preparedStatement.setInt(1, u.getId());
            preparedStatement.setString(2, u.getName());
            preparedStatement.setDate(3, Date.valueOf(u.getBithday()));
            preparedStatement.setInt(4, u.getLogin_id());
            preparedStatement.setString(5, u.getCity());
            preparedStatement.setString(6, u.getEmail());
            preparedStatement.setString(7, u.getDescription());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }
}
