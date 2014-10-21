package ru.tsystems.tsproject.sbb.secure;

import ru.tsystems.tsproject.sbb.services.TimetableService;
import ru.tsystems.tsproject.sbb.transferObjects.RouteEntryTO;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class AddRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i = 1;
        RouteTO route = new RouteTO();
        while (request.getParameter("name" + i) != null) {
            RouteEntryTO routeEntry = new RouteEntryTO();
            routeEntry.setStationName(request.getParameter("name" + i));
            routeEntry.setHour(Integer.parseInt(request.getParameter("hour" + i)));
            routeEntry.setMinute(Integer.parseInt(request.getParameter("minute" + i)));
            routeEntry.setDistance(Integer.parseInt(request.getParameter("distance"+i)));
            routeEntry.setSeqNumber(i);
            route.getRouteEntries().add(routeEntry);
            i++;
        }
        TimetableService timetableService = new TimetableService();
        timetableService.addRoute(route);
        response.sendRedirect("getRoutes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addRoute.jsp").forward(request, response);
    }
}
