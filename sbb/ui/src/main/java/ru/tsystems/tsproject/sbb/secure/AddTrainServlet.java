package ru.tsystems.tsproject.sbb.secure;

import ru.tsystems.tsproject.sbb.services.TimetableService;
import ru.tsystems.tsproject.sbb.transferObjects.TrainTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 19.10.14.
 */
public class AddTrainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TimetableService timetableService = new TimetableService();
        TrainTO train = new TrainTO();
        train.setNumber(Integer.parseInt(request.getParameter("number")));
        train.setName(request.getParameter("name"));
        train.setSeatCount(Integer.parseInt(request.getParameter("seatCount")));
        train.setRateId(Integer.parseInt(request.getParameter("rate")));
        timetableService.addTrain(train);
        response.sendRedirect("getTrains");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addTrain.jsp").forward(request, response);
    }
}
