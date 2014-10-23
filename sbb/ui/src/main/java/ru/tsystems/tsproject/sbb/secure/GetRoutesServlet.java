package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.RouteService;
import ru.tsystems.tsproject.sbb.services.TimetableService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class GetRoutesServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetRoutesServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
        	RouteService routeService = new RouteService();
        	request.getSession().setAttribute("routes", routeService.getAllRoutes());
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Возникла ошибка при покупке билета");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("getRoutes.jsp").forward(request, response);
    }
}
