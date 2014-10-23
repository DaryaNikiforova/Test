package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.StationService;
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
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 21.10.14.
 */
public class SearchStationTripServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SearchStationTripServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TimetableTO> timetableList = new ArrayList<TimetableTO>();
        try {
            TimetableService timetableService = new TimetableService();
            try {
                String stationFrom = request.getParameter("stationFrom");
                Date departureDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("departureDate"));
                timetableList = timetableService.getRoutesByStation(stationFrom, departureDate);
            } catch (ParseException e) {
                LOGGER.error(e);
                request.setAttribute("error", "Ввод неверного формата даты");
                request.getRequestDispatcher("error.jsp").forward(request,response);
                //e.printStackTrace();
            }
            StationService stationService = new StationService();
            request.setAttribute("stations", stationService.getStations());
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        request.getSession().setAttribute("stationTimetable",timetableList);
        request.getRequestDispatcher("searchstationtrip.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StationService stationService = new StationService();
            request.setAttribute("stations", stationService.getStations());
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
        request.getRequestDispatcher("searchstationtrip.jsp").forward(request, response);
    }
}
