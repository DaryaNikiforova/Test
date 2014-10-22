package ru.tsystems.tsproject.sbb;

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
 * Created by apple on 22.10.14.
 */
public class PurchaseServlet extends HttpServlet {
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
        TicketService ticketService = new TicketService();
        ticket.setPrice(ticketService.calcPrice(ticket));
        request.getSession().setAttribute("ticket", ticket);
        request.getRequestDispatcher("purchasesummary.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
