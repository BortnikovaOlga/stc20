package ru.bortnikova.task15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * работа в таблице role
 *
 * @author Bortnikova Olga
 */
public class RoleDAO {
    private Connection connect;
    private static String SQLstrStatement="INSERT INTO role (id, name, descrtiption) VALUES (?,?,?);";


    public RoleDAO(Connection connect) {
        this.connect = connect;
    }

    /**
     * метод для вставки новой записи
     * @param role объект Role
     * @return true при успешной операции, false если запись не добавлена
     */
    public boolean insert(Role role) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(SQLstrStatement)) {
            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());
            preparedStatement.setString(3, role.getDescription());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * метод для создания таблицы role
     * @throws SQLException
     */
    public void createBD() throws SQLException {
        String SQLstrCreate= "DROP TABLE IF EXISTS role CASCADE ;"
                + "CREATE TABLE role ("
                + "id          integer primary key, "
                + "name        varchar(14) NOT NULL, "
                + "descrtiption varchar(30));";

        Statement statement = connect.createStatement();
        statement.execute(SQLstrCreate);
    }
}
