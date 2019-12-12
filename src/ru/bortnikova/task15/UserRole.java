package ru.bortnikova.task15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * класс для работы с таблицей user_role
 *
 * @author Bortnikova Olga
 */
public class UserRole {
    private int user_id;
    private int role_id;

    public UserRole(int user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    /**
     * метод добавляет новую запись в таблицу user_role
     *
     * @param connect соединение с БД
     * @return true при успешной операции, false если запись не добавлена
     */
    public boolean insertDB(Connection connect) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(

                "INSERT INTO user_role (user_id, role_id) \n"
                        + "VALUES (?,?);");) {
            preparedStatement.setInt(1, this.user_id);
            preparedStatement.setInt(2, this.role_id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * метод добавляет записи в таблицу user_role
     *
     * @param connect соединение с БД
     * @param urArr массив объектов UserRole
     * @throws SQLException
     */
    public static void insertDB(Connection connect, UserRole[] urArr) throws SQLException {
        PreparedStatement preparedStatement = connect.prepareStatement(
                "INSERT INTO user_role (user_id, role_id) \n"
                        + "VALUES (?,?);");
        for (UserRole ur : urArr ) {
            preparedStatement.setInt(1, ur.user_id);
            preparedStatement.setInt(2, ur.role_id);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

}
