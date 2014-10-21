package ru.tsystems.tsproject.sbb.database.dao;

import ru.tsystems.tsproject.sbb.database.entity.Role;
import ru.tsystems.tsproject.sbb.database.entity.User;

import java.util.Date;

/**
 * Created by apple on 14.10.14.
 */
public interface UserDAO {
    void addUser(String name, String surname, Date birthDate, String login, String password, Role role);

    //String getUsersRole(User user);

    Role getClientRole();

    //boolean isAdmin(User user);

    User getUser(String login);
}
