package ru.tsystems.tsproject.sbb.controllers.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.TrainService;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Gets information about trains, send it to client.
 * Includes checking of client input.
 * @author Daria Nikiforova
 */
public class GetTrainsServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetTrainsServlet.class);

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
            TrainService trainService = new TrainService(entityManager, factoryDAO);
            request.setAttribute("trains", trainService.getTrains());
            request.getRequestDispatcher(request.getContextPath() + "/secure/getTrains.jsp").forward(request, response);
        } catch (ServiceException ex) {
            LOGGER.error(ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }
    }
}
