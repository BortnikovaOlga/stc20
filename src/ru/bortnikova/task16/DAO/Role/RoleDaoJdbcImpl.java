package ru.bortnikova.task16.DAO.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.bortnikova.task16.ConnectionManager.ConnectionManagerJdbcImpl;
import ru.bortnikova.task16.Entety.Role;

/**
 * работа в таблице role
 *
 * @author Bortnikova Olga
 */
public class RoleDaoJdbcImpl implements RoleDao {

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoJdbcImpl.class.getName());
    private Connection connect;

    private static final String SQLstrInsert = "INSERT INTO role (id, name, descrtiption) VALUES (?,?,?);";
    private static final String SQLstrCreate = "DROP TABLE IF EXISTS role CASCADE ;"
            + "CREATE TABLE role ("
            + "id          integer primary key, "
            + "name        varchar(14) NOT NULL, "
            + "descrtiption varchar(30));";

    public RoleDaoJdbcImpl() {
        this.connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
    }

    /**
     * метод для вставки новой записи
     *
     * @param role объект Role
     * @return true при успешной операции, false если запись не добавлена
     */
    @Override
    public boolean add(Role role) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(SQLstrInsert);) {

            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());
            preparedStatement.setString(3, role.getDescription());
            preparedStatement.execute();
            logger.info("добавлена запись в таблицу role");
        } catch (SQLException e) {
            logger.error("Ошибка добавления в таблицу role", e);
            return false;
        }
        return true;
    }

    /**
     * метод для создания таблицы role
     *
     * @return true в случае успешной операции, false если таблица не создана
     */
    public static boolean createBD() {
        try {
            Connection connect = ConnectionManagerJdbcImpl.getInstance().getConnection();
            Statement statement = connect.createStatement();
            statement.execute(SQLstrCreate);
            logger.info("Создана таблица role");
        } catch (SQLException e) {
            logger.error("Ошибка создания таблицы role", e);
            return false;
        }
        return true;
    }

}
