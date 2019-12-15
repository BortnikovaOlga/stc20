package ru.bortnikova.task15.DAO.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ru.bortnikova.task15.ConnectionManager.ConnectionManagerJdbcImpl;
import ru.bortnikova.task15.Entety.Role;
/**
 * работа в таблице role
 *
 * @author Bortnikova Olga
 */
public class RoleDaoJdbcImpl implements RoleDao {

    private Connection connect;

    private static final String SQLstrStatement="INSERT INTO role (id, name, descrtiption) VALUES (?,?,?);";
    private static final String SQLstrCreate= "DROP TABLE IF EXISTS role CASCADE ;"
            + "CREATE TABLE role ("
            + "id          integer primary key, "
            + "name        varchar(14) NOT NULL, "
            + "descrtiption varchar(30));";

    public RoleDaoJdbcImpl() {
        this.connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
    }

    /**
     * метод для вставки новой записи
     * @param role объект Role
     * @return true при успешной операции, false если запись не добавлена
     */
    @Override
    public boolean add(Role role) {
        try ( PreparedStatement preparedStatement = connect.prepareStatement(SQLstrStatement);){

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
    public static void createBD() throws SQLException {
        Connection connect =ConnectionManagerJdbcImpl.getInstance().getConnection();
        Statement statement = connect.createStatement();
        statement.execute(SQLstrCreate);
    }
}
