package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.UserService;
import ru.tsystems.tsproject.sbb.transferObjects.UserTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by apple on 14.10.14.
 */
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserTO user = new UserTO();
        try {
            UserService userService = new UserService();
            user = userService.getUser(request.getParameter("login"));
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Сервис недоступен");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("role", user.getRole());
            session.setAttribute("login", user.getLogin());
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
