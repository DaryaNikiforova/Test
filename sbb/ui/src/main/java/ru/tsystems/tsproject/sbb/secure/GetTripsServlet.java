package ru.tsystems.tsproject.sbb.secure;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimetableService timetableService = new TimetableService();
        request.getSession().setAttribute("trips", timetableService.getAllTrips());
        request.getRequestDispatcher("trips.jsp").forward(request, response);
    }
}
