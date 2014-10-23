package ru.tsystems.tsproject.sbb.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.mappers.Mapper;
import ru.tsystems.tsproject.sbb.mappers.MapperManager;

import ru.tsystems.tsproject.sbb.services.TrainService;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;
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
public class AddTrainServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddTrainServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
         try {
			TrainService trainService = new TrainService();
	        request.setAttribute("rates", trainService.getTrainRates());

	        Mapper<TrainTO> mapper = MapperManager.getMapper(TrainTO.class);
	        TrainTO trainTO = mapper.map(request);
	        Validator<TrainTO> validator = ValidationManager.getValidator(trainTO);
	        if (validator.isValid()) {
	            trainService.addTrain(trainTO);
	            response.sendRedirect("getTrains");
	        }
	        else {
	            request.setAttribute("errors", validator.getErrors());
	            request.getRequestDispatcher("addTrain.jsp").forward(request, response);
	        }
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Ошибка приложения");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TrainService trainService = new TrainService();
        request.setAttribute("rates", trainService.getTrainRates());
        request.getRequestDispatcher("addTrain.jsp").forward(request, response);
    }
}
