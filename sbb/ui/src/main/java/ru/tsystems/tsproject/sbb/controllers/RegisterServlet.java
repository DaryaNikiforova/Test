package ru.tsystems.tsproject.sbb.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.helpers.StringHelper;
import ru.tsystems.tsproject.sbb.mappers.Mapper;
import ru.tsystems.tsproject.sbb.mappers.MapperManager;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.UserService;
import ru.tsystems.tsproject.sbb.services.exceptions.UserAlreadyExistException;
import ru.tsystems.tsproject.sbb.transferObjects.UserTO;
import ru.tsystems.tsproject.sbb.validation.ValidationManager;
import ru.tsystems.tsproject.sbb.validation.Validator;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 12.10.14.
 */
public class RegisterServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("page", "registration");

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null) {
            response.sendRedirect(request.getContextPath() + "/index");
        }

        EntityManagerProvider emp = new EntityManagerProvider();
        EntityManager entityManager = emp.getEntityManager();
        FactoryDAO factoryDAO = new FactoryDAOImpl(entityManager);
        try {
            Mapper<UserTO> mapper = MapperManager.getMapper(UserTO.class);
            UserTO userTO = mapper.map(request);
            Validator<UserTO> validator = ValidationManager.getValidator(userTO);
            if (validator.isValid()) {
                UserService userService = new UserService(entityManager, factoryDAO);
                userService.addUser(userTO);
                session = request.getSession();
                session.setAttribute("role", userTO.getRole());
                session.setAttribute("login", userTO.getLogin());
                response.sendRedirect(request.getContextPath() + "/searchTrip");
            }
            else {
                request.setAttribute("errors", validator.getErrors());
                request.getRequestDispatcher(request.getContextPath() + "/registration.jsp").forward(request, response);
            }
        }
        catch (UserAlreadyExistException ex) {
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("login", StringHelper.encode(
                    "Такой пользователь уже существует (логин или имя, фамилия и дата рождения)"));
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(request.getContextPath() + "/registration.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login") != null) {
            response.sendRedirect(request.getContextPath() + "/index");
        } else {
            request.setAttribute("page", "registration");
            request.getRequestDispatcher(request.getContextPath() + "/registration.jsp").forward(request, response);
        }
    }
}
