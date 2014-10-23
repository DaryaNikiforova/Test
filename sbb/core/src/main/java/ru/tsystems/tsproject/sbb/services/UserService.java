package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.UserDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.Role;
import ru.tsystems.tsproject.sbb.database.entity.User;
import ru.tsystems.tsproject.sbb.services.exception.UserAlreadyExistException;
import ru.tsystems.tsproject.sbb.transferObjects.UserTO;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by apple on 12.10.14.
 */
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    FactoryDAOImpl factory = new FactoryDAOImpl();
    UserDAO userDAO = factory.getUserDAO();

    public void registerUser(String name, String surname, String birthDate, String login, String password) throws UserAlreadyExistException {
        Role role = userDAO.getClientRole();
        try {
            if (!userDAO.isUserExist(login)) {
                userDAO.addUser(name, surname, new SimpleDateFormat("dd.MM.yyyy").parse(birthDate),login, password, role);
            }
            else {
                throw new UserAlreadyExistException("Пользователь уже существует в базе данных");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public UserTO getUser(String login) {
        User user = null;
        UserTO userTO = new UserTO();
        try {
            user = userDAO.getUser(login);
        } catch (PersistenceException ex) {
            LOGGER.error("Пользователь не найден");
        }
        //List<String> params = new ArrayList<String>();
        if (user != null) {
            userTO.setLogin(user.getLogin());
            userTO.setRole(user.getRole().getName());
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("dd.MM.yyyy");
            String d = sdf.format(user.getBirthDate());
            userTO.setName(user.getName());
            userTO.setSurname(user.getSurname());
            userTO.setPassword(user.getPassword());
            userTO.setBirthDate(d);
        }
        return userTO;
    }
}
