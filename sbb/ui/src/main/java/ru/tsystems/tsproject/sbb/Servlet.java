package ru.tsystems.tsproject.sbb;

import java.io.IOException;
import java.io.PrintWriter;

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
        PrintWriter out = response.getWriter();
        out.println("<h1>You</h1>:" + id);
        out.println(count);
    }
}
