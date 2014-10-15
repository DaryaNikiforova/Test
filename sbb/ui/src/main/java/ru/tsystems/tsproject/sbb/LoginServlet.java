package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<String> params = userService.getUser(request.getParameter("login"));
        //PrintWriter out = response.getWriter();
        if (params.size() > 0) {
            //out.println(params.get(0) + " " + params.get(1));
            HttpSession session = request.getSession();
            session.setAttribute("role", params.get(1));
            session.setAttribute("login", params.get(0));
        }
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
