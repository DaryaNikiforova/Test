package ru.tsystems.tsproject.sbb;

import ru.tsystems.tsproject.sbb.services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apple on 15.10.14.
 */
public class BuyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tripId = request.getParameter("tripId");
        String stationFrom = request.getParameter("stationFrom");
        String stationTo = request.getParameter("stationTo");
        String login = (String) request.getSession().getAttribute("login");
        if ((tripId != null && stationFrom!=null && stationTo!=null && login !=null) || (tripId!="" && stationFrom != "" && stationTo != "")) {
            TicketService ticketService = new TicketService();
            request.getSession().setAttribute("ticket", ticketService.generateTicket(Integer.parseInt(tripId), stationFrom, stationTo, login));
            request.getRequestDispatcher("buyTicket.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("searchtrip.jsp").forward(request, response);
        }
    }
}
