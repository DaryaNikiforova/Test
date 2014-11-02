package ru.tsystems.tsproject.sbb.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.providers.EntityManagerProvider;
import ru.tsystems.tsproject.sbb.services.TicketService;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;
import ru.tsystems.tsproject.sbb.services.helpers.NumberHelper;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 15.10.14.
 */
public class BuyTicketServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BuyTicketServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerProvider emp = new EntityManagerProvider();
        EntityManager entityManager = emp.getEntityManager();
        FactoryDAO factoryDAO = new FactoryDAOImpl(entityManager);
        try {
            TicketTO ticket = (TicketTO) request.getSession().getAttribute("ticket");
            if(NumberHelper.tryParseInt(request.getParameter("place")) &&
                    NumberHelper.tryParseInt(request.getParameter("rate"))) {
                ticket.setSeatNumber(Integer.parseInt(request.getParameter("place")));
                ticket.setRateType(Integer.parseInt(request.getParameter("rate")));

                Map<Long, String> rateTypes = ticket.getRateTypes();
                List<Integer> seats = ticket.getSeats();
                boolean first = ticket.getRateTypes().containsKey((long)ticket.getRateType());
                boolean second = ticket.getSeats().contains(ticket.getSeatNumber());
                if (first && second) {
                    String[] services = request.getParameterValues("services");
                    Map<Long, String> servicesMap = new HashMap<Long, String>();
                    if (services != null) {
                        for (String s : services) {
                            servicesMap.put(new Long(s), ticket.getServices().get(new Long(s)));
                        }
                    }
                    ticket.setServices(servicesMap);
                    ticket.setRateName(ticket.getRateTypes().get(new Long(ticket.getRateType())));
                    TicketService ticketService = new TicketService(entityManager, factoryDAO);
                    ticket.setPrice(ticketService.calcPrice(ticket));
                    request.getSession().setAttribute("ticket", ticket);
                    request.getRequestDispatcher(request.getContextPath() + "/purchasesummary.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Введены неверные данные");
                    request.setAttribute("tripId", ticket.getTripId());
                    request.setAttribute("stationTo", ticket.getStationTo());
                    request.setAttribute("stationFrom", ticket.getStationFrom());
                    request.getRequestDispatcher(request.getContextPath() + "/buyTicket.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Введены неверные данные");
                request.setAttribute("tripId", ticket.getTripId());
                request.setAttribute("stationTo", ticket.getStationTo());
                request.setAttribute("stationFrom", ticket.getStationFrom());
                request.getRequestDispatcher(request.getContextPath() + "/buyTicket.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Возникла ошибка при покупке билета");
            request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
        } finally {
            entityManager.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerProvider emp = new EntityManagerProvider();
        EntityManager entityManager = emp.getEntityManager();
        FactoryDAO factoryDAO = new FactoryDAOImpl(entityManager);
        try {
            String tripId = request.getParameter("tripId");
            String stationFrom = request.getParameter("stationFrom");
            String stationTo = request.getParameter("stationTo");
            String login = (String) request.getSession().getAttribute("login");
            if ((tripId != null && stationFrom != null && stationTo != null) && (!tripId.isEmpty() && !stationFrom.isEmpty() && !stationTo.isEmpty())) {
                TicketTO ticket = new TicketTO();
                try {
                    TicketService ticketService = new TicketService(entityManager, factoryDAO);
                    ticket = ticketService.generateTicket(Integer.parseInt(tripId), stationFrom, stationTo, login);
                } catch (ServiceException ex) {
                    LOGGER.error(ex);
                    request.setAttribute("error", ex);
                    request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
                }
                request.getSession().setAttribute("ticket", ticket);
                request.getRequestDispatcher(request.getContextPath() + "/buyTicket.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher(request.getContextPath() + "/searchtrip.jsp").forward(request, response);
            }
        } finally {
            entityManager.close();
        }
    }
}
