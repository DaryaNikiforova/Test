package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.TimetableService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class GetTripsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetTripsServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TimetableService timetableService = new TimetableService();
            request.getSession().setAttribute("trips", timetableService.getAllTrips());
        } catch (Exception ex) {
            LOGGER.error("Ошибка приложения");
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("getTrips.jsp").forward(request, response);
    }
}
