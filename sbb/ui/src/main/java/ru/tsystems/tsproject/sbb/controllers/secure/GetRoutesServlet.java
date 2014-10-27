package ru.tsystems.tsproject.sbb.controllers.secure;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.RouteService;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Gets information about routes and send it to client.
 * Includes checking of client input.
 * @author Daria Nikiforova
 */
public class GetRoutesServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(GetRoutesServlet.class);

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
            RouteService routeService = new RouteService(entityManager, factoryDAO);
            List<RouteTO> routes = routeService.getAllRoutes();
            request.setAttribute("routes", routes);
            request.getRequestDispatcher(request.getContextPath() + "/secure/getRoutes.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }
    }
}
