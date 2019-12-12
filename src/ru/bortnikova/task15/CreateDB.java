package ru.bortnikova.task15;

import java.sql.*;

/**
 * создание таблиц role, users, user_role
 *
 * @author Bortnikova Olga
 */
public class CreateDB {

    /**
     * создание таблицы role - справочник ролей пользователей
     * @param connect соединение с БД
     * @throws SQLException
     */
    public static void role(Connection connect) throws SQLException {
        Statement statement = connect.createStatement();
        statement.execute(
                "DROP TABLE IF EXISTS role CASCADE ;"
                        + "CREATE TABLE role (\n"
                        + "id          integer primary key,\n"
                        + "name        varchar(14) NOT NULL,\n"
                        + "desctiption varchar(30));" +

                        "INSERT INTO role (id,name )\n"
                        + "VALUES\n"
                        + "   (1, 'Administration'),\n"
                        + "   (2, 'Clients'),\n"
                        + "   (3, 'Billing');");
    }

    /**
     * создание таблицы user_role - информация о ролях пользователей
     * @param connect соединение с БД
     * @throws SQLException
     */
    public static void user_role(Connection connect) throws SQLException {
        Statement statement = connect.createStatement();
        statement.execute(
                "DROP TABLE IF EXISTS user_role CASCADE ;"
                        + "CREATE TABLE user_role (\n"
                        + "user_id  integer references users (id) on delete cascade,\n"
                        + "role_id  integer references role (id) on delete cascade);");
    }

    /**
     * создание таблицы users - информация о пользователях
     * @param connect соединение с БД
     * @throws SQLException
     */
    public static void users(Connection connect) throws SQLException {
        Statement statement = connect.createStatement();
        statement.execute(
                "DROP TABLE IF EXISTS users CASCADE ;"
                        + "CREATE TABLE users (\n"
                        + "id          integer primary key,\n"
                        + "name        varchar(20) NOT NULL,\n"
                        + "birthday    date,\n"
                        + "login_id    integer,\n"
                        + "city        varchar(20),\n"
                        + "email       varchar(30),"
                        + "desctiption varchar(50));");
    }

}
