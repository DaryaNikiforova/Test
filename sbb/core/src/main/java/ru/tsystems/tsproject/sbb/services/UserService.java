package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.UserDAO;
import ru.tsystems.tsproject.sbb.database.entity.Role;
import ru.tsystems.tsproject.sbb.database.entity.User;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 12.10.14.
 */
public class UserService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    UserDAO userDAO = factory.getUserDAO();

    public void registerUser(String name, String surname, String birthDate, String login, String password) {
        Role role = userDAO.getClientRole();
        if (userDAO.getUser(login) == null) {
            try {
                userDAO.addUser(name, surname, new SimpleDateFormat("dd-mm-yyyy").parse(birthDate),login, password, role);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getUser(String login) {
        User user = null;
        try {
            user = userDAO.getUser(login);
        } catch (Exception ex) {
        }
        List<String> params = new ArrayList<String>();
        if (user != null) {
            params.add(user.getLogin());
            params.add(user.getRole().getName());
        }
        return params;
    }


}
