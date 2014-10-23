package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.BuyTicketServlet;
import ru.tsystems.tsproject.sbb.mappers.Mapper;
import ru.tsystems.tsproject.sbb.mappers.MapperManager;
import ru.tsystems.tsproject.sbb.services.StationService;
import ru.tsystems.tsproject.sbb.transferObjects.StationTO;
import ru.tsystems.tsproject.sbb.validation.ValidationManager;
import ru.tsystems.tsproject.sbb.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 15.10.14.
 */
public class AddStationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BuyTicketServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
		try {
	        Mapper<StationTO> mapper = MapperManager.getMapper(StationTO.class);
	        StationTO stationTO = mapper.map(request);
	        Validator<StationTO> validator = ValidationManager.getValidator(stationTO);
	        if (validator.isValid()) {
	            StationService service = new StationService();
	            service.addStation(stationTO);
	            response.sendRedirect("getStations");
	        }
	        else {
                request.setAttribute("errors", validator.getErrors());
                request.getRequestDispatcher("addStation.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addStation.jsp").forward(request, response);
    }
}
