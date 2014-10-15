package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by apple on 12.10.14.
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        UserService userService = new UserService();
        userService.registerUser(request.getParameter("name"), request.getParameter("surname"),
                                 request.getParameter("birth"), request.getParameter("login"),
                                 request.getParameter("password"));

        PrintWriter out = response.getWriter();
        out.println(request.getParameter("name") + " " + request.getParameter("surname"));

        List<String> params = userService.getUser(request.getParameter("login"));
        if (params.size() > 0) {
            out.println("<br/>" + params.get(0) + " " + params.get(1));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);
    }
}
