package ru.tsystems.tsproject.sbb.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by apple on 15.10.14.
 */
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        boolean isAdmin = true;
        //UserService user = new UserService();
        PrintWriter out = response.getWriter();
        if (request.isRequestedSessionIdValid()) {
            //out.println("valid");
            //out.println(request.getSession(false));
            HttpSession session = request.getSession(false);
            if (session.getAttribute("role") != null) {
                if (session.getAttribute("role").equals("admin")) {
                    chain.doFilter(req, resp);
                }
                else {
                    isAdmin = false;
                }
            }
            else {
                isAdmin = false;
            }
        }
        else {
            isAdmin = false;
        }
        //out.println("invalid");
        if (!isAdmin) {
            response.sendRedirect("/index.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
