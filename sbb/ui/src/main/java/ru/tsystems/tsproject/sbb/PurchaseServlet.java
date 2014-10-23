package ru.tsystems.tsproject.sbb;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.services.TicketService;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

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
        TicketTO ticket  = (TicketTO) request.getSession().getAttribute("ticket");

        try {
            TicketService ticketService = new TicketService();
            ticketService.AddTicket(ticket);
        } catch (Exception ex) {
            LOGGER.error(ex);
            request.setAttribute("error", "Возникла ошибка при покупке билета");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
