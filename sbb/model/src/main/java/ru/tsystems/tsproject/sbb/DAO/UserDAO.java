package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Role;
import ru.tsystems.tsproject.sbb.Entity.User;

import java.util.Date;

/**
 * Created by apple on 04.10.14.
 */
public interface UserDAO {
    void addUser(String name, String surname, Date birthDate, String login, String password, Role role);
    String getUsersRole(User user);
    Role getClientRole();
    boolean isAdmin(User user);
}
