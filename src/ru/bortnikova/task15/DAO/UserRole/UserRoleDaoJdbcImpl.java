package ru.bortnikova.task15.DAO.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import ru.bortnikova.task15.ConnectionManager.ConnectionManagerJdbcImpl;
import ru.bortnikova.task15.Entety.UserRole;

/**
 * работа в таблице userrole
 *
 * @author Bortnikova Olga
 */

public class UserRoleDaoJdbcImpl implements UserRoleDao {

    private Connection connect;

    private static final String SQLstrStatement = "INSERT INTO userrole (user_id, role_id) VALUES (?,?);";
    private static final String SQLstrCreate = "DROP TABLE IF EXISTS userrole CASCADE ;"
            + "CREATE TABLE userrole ("
            + "role_id  integer references role on delete cascade , "
            + "user_id  integer references users on delete cascade ); ";

    public UserRoleDaoJdbcImpl() {
        this.connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
    }

    /**
     * метод создает таблицу userrole
     *
     * @throws SQLException
     */
    public static void createDB() throws SQLException {
        Connection connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
        Statement statement = connect.createStatement();
        statement.execute(SQLstrCreate);
    }

    /**
     * метод добавляет новую запись в таблицу userrole
     *
     * @param userRole объект UserRole
     * @return true при успешной операции, false если запись не добавлена
     */
    @Override
    public boolean add(UserRole userRole) {

        try (PreparedStatement preparedStatement = connect.prepareStatement(SQLstrStatement)) {
            preparedStatement.setInt(1, userRole.getUser_id());
            preparedStatement.setInt(2, userRole.getRole_id());
            preparedStatement.execute();

        } catch (SQLException e) {
            return false;
        }
        return true;

    }

}
