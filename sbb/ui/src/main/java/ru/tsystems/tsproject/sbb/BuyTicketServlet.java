package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.TicketService;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 15.10.14.
 */
public class BuyTicketServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BuyTicketServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TicketTO ticket = (TicketTO) request.getSession().getAttribute("ticket");
        ticket.setSeatNumber(Integer.parseInt(request.getParameter("place")));
        ticket.setRateType(Integer.parseInt(request.getParameter("rate")));
        String[] services = request.getParameterValues("services");
        Map<Long, String> servicesMap = new HashMap<Long, String>();
        if (services!=null) {
            for (String s : services) {
                servicesMap.put(new Long(s), ticket.getServices().get(new Long(s)));
            }
        }
        ticket.setServices(servicesMap);
        ticket.setRateName(ticket.getRateTypes().get(new Long(ticket.getRateType())));
        try {
            TicketService ticketService = new TicketService();
            ticket.setPrice(ticketService.calcPrice(ticket));
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Возникла ошибка при покупке билета");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getSession().setAttribute("ticket", ticket);
        request.getRequestDispatcher("purchasesummary.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter("tripId");
        String stationFrom = request.getParameter("stationFrom");
        String stationTo = request.getParameter("stationTo");
        String login = (String) request.getSession().getAttribute("login");
        if ((tripId != null && stationFrom!=null && stationTo!=null && login !=null) || (tripId!="" && stationFrom != "" && stationTo != "")) {
            TicketTO ticket = new TicketTO();
            try {
                TicketService ticketService = new TicketService();
                ticket = ticketService.generateTicket(Integer.parseInt(tripId), stationFrom, stationTo, login);
            } catch (Exception ex) {
                LOGGER.error(ex);
                request.setAttribute("error", "Возникла ошибка при оформлении билета");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.getSession().setAttribute("ticket", ticket);
            request.getRequestDispatcher("buyTicket.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("searchtrip.jsp").forward(request, response);
        }
    }
}
