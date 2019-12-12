package ru.bortnikova.task15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * работа в таблице user_role
 *
 * @author Bortnikova Olga
 */

public class UserRoleDAO {
    private Connection connect;
    private static String SQLstrStatement = "INSERT INTO user_role (user_id, role_id) VALUES (?,?);";

    public UserRoleDAO(Connection connect) {
        this.connect = connect;
    }

    /**
     * метод создает таблицу user_role
     *
     * @throws SQLException
     */
    public void createDB() throws SQLException {
        String SQLstrCreate = "DROP TABLE IF EXISTS user_role CASCADE ;"
                + "CREATE TABLE user_role ("
                + "user_id  integer references users (id) on delete cascade,"
                + "role_id  integer references role (id) on delete cascade);";
        Statement statement = connect.createStatement();
        statement.execute(SQLstrCreate);
    }

    /**
     * метод добавляет новую запись в таблицу user_role
     *
     * @param ur объект UserRole
     * @return true при успешной операции, false если запись не добавлена
     */
    public boolean insertDB(UserRole ur) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(SQLstrStatement)) {
            preparedStatement.setInt(1, ur.getUser_id());
            preparedStatement.setInt(2, ur.getRole_id());
            preparedStatement.execute();
            } catch (SQLException e) {
            return false;
        }
        return true;
    }

}
