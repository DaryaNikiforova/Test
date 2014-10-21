package ru.tsystems.tsproject.sbb.database.dao;

import ru.tsystems.tsproject.sbb.database.entity.Ticket;

import java.util.List;

/**
 * Created by apple on 14.10.14.
 */
public interface TicketDAO {
    void addTicket(Ticket ticket);
    List<Ticket> getBoughtTickets(String stationFrom, String stationTo, int tripId);
}
