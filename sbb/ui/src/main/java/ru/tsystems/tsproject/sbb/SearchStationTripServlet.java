package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.services.TimetableService;
import ru.tsystems.tsproject.sbb.transferObjects.TimetableTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 21.10.14.
 */
public class SearchStationTripServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimetableService timetableService = new TimetableService();
        List<TimetableTO> timetableList = new ArrayList<TimetableTO>();

        try {
            timetableList = timetableService.getRoutesByStation(request.getParameter("stationFrom"),
                    new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(request.getParameter("departureDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("stationTimetable",timetableList);
        request.getRequestDispatcher("searchstationtrip.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("searchstationtrip.jsp").forward(request, response);
    }
}
