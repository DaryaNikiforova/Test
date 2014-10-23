package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.BuyTicketServlet;
import ru.tsystems.tsproject.sbb.mappers.Mapper;
import ru.tsystems.tsproject.sbb.mappers.MapperManager;
import ru.tsystems.tsproject.sbb.services.RouteService;
import ru.tsystems.tsproject.sbb.services.StationService;
import ru.tsystems.tsproject.sbb.services.TimetableService;
import ru.tsystems.tsproject.sbb.transferObjects.RouteEntryTO;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;
import ru.tsystems.tsproject.sbb.validation.ValidationManager;
import ru.tsystems.tsproject.sbb.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class AddRouteServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BuyTicketServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

		try {
	        StationService stationService = new StationService();
	        request.setAttribute("stations", stationService.getStations());

	        RouteService routeService = new RouteService();
	        Mapper<RouteTO> mapper = MapperManager.getMapper(RouteTO.class);
	        RouteTO routeTO = mapper.map(request);
	        Validator<RouteTO> validator = ValidationManager.getValidator(routeTO);
	        if (validator.isValid()) {
	            routeService.addRoute(routeTO);
	            response.sendRedirect("getRoutes");
	        }
	        else {
	            request.setAttribute("errors", validator.getErrors());
	            request.getRequestDispatcher("addRoute.jsp").forward(request, response);
	        }		
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StationService stationService = new StationService();
        request.setAttribute("stations", stationService.getStations());
        request.getRequestDispatcher("addRoute.jsp").forward(request, response);
    }
}
