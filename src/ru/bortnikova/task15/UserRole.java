package ru.bortnikova.task15;

/**
 * объект UserRole соответствует записи в таблице user_role
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

    public int getUser_id() {
        return user_id;
    }

    public int getRole_id() {
        return role_id;
    }
}
