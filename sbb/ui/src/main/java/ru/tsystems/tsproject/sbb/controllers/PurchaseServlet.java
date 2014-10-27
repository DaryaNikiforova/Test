package ru.tsystems.tsproject.sbb.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.TicketService;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 22.10.14.
 */
public class PurchaseServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(PurchaseServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerProvider emp = new EntityManagerProvider();
        EntityManager entityManager = emp.getEntityManager();
        FactoryDAO factoryDAO = new FactoryDAOImpl(entityManager);
        try {
            TicketTO ticket  = (TicketTO) request.getSession().getAttribute("ticket");
            TicketService ticketService = new TicketService(entityManager, factoryDAO);
            ticketService.AddTicket(ticket);
            request.setAttribute("isSuccess", true);
            request.getRequestDispatcher(request.getContextPath() + "/purchasesummary.jsp").forward(request, response);

        } catch (ServiceException ex) {
            LOGGER.error(ex);
            request.setAttribute("error", ex);
            request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index");
    }
}
