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
 * Created by apple on 20.10.14.
 */
public class SearchTripServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimetableService timetableService = new TimetableService();
        List<TimetableTO> timetableList = new ArrayList<TimetableTO>();

        try {
            timetableList = timetableService.searchTrains(request.getParameter("stationFrom"), request.getParameter("stationTo"),
                    new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(request.getParameter("departure")),
                    new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(request.getParameter("arrival")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("timetable",timetableList);
        request.getRequestDispatcher("searchtrip.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("searchtrip.jsp").forward(request, response);
    }
}
