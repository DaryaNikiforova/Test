package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.StationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class GetStationsServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GetStationsServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	StationService service = new StationService();
        	request.getSession().setAttribute("stations", service.getStations());
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("getStations.jsp").forward(request, response);
    }
}
