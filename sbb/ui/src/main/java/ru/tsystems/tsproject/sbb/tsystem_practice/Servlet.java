package ru.tsystems.tsproject.sbb.tsystem_practice;

import java.io.IOException;

/**
 * Created by apple on 30.09.14.
 */
public class Servlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        int id = (Integer) request.getAttribute("id");
        int count = (Integer) request.getAttribute("count");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        //PrintWriter out = response.getWriter();
        //out.println("<h1>myaaauuuu</h1>:" + id);
        //out.println(count);
    }
}
