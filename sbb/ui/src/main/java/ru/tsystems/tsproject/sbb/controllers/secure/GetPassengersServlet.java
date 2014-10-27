package ru.tsystems.tsproject.sbb.controllers.secure;

import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.TicketService;
import ru.tsystems.tsproject.sbb.services.helpers.NumberHelper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gets information about passengers, registered on trip, and send it to client.
 * Includes checking of client input.
 * @author Daria Nikiforova
 */
public class GetPassengersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Method processes GET request.
     * @param request {@link HttpServletRequest} object contained client request
     * @param response {@link HttpServletResponse} object contained server respond
     * @throws ServletException when error was occurred inside method
     * @throws IOException if GET request could not be handled
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerProvider emp = new EntityManagerProvider();
        EntityManager entityManager = emp.getEntityManager();
        FactoryDAO factoryDAO = new FactoryDAOImpl(entityManager);
        try {
            TicketService ticketService = new TicketService(entityManager, factoryDAO);
            if(NumberHelper.tryParseInt(request.getParameter("tripId"))) {
                int tripId = Integer.parseInt(request.getParameter("tripId"));
                request.setAttribute("passengers", ticketService.getPassengersByTrip(tripId));
                request.getRequestDispatcher(request.getContextPath() + "/secure/getPassengers.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/index");
            }
        } finally {
            entityManager.close();
        }
    }
}
