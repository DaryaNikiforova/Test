package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.*;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.*;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 18.10.14.
 */
public class TicketService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    StationDAO stationDAO = factory.getStationDAO();
    UserDAO userDAO = factory.getUserDAO();
    TripDAO tripDAO = factory.getTripDAO();
    TicketDAO ticketDAO = factory.getTicketDAO();
    RouteEntryDAO routeEntryDAO = factory.getRouteEntryDAO();

    public void AddTicket(TicketTO ticket, String login) {
        Ticket t = new Ticket();
        t.setDate(ticket.getDate());
        t.setPrice(ticket.getPrice());
        t.setSeat(ticket.getSeat());
        Station s = new Station();
        s.setName(ticket.getStationFrom());
        s.setId(stationDAO.getStation(ticket.getStationFrom()).getId());
        t.setStationFrom(s);
        s.setName(ticket.getStationTo());
        s.setId(stationDAO.getStation(ticket.getStationTo()).getId());
        t.setStationTo(s);
        User u = new User();
        t.setUser(userDAO.getUser(login));
        t.setTrip(tripDAO.getTrip(ticket.getTripId()));
        ticketDAO.addTicket(t);
    }

    public List<Integer> getAvaliableSeats (String stationFrom, String stationTo, int tripId, int routeId) {
        List<Ticket> tickets = ticketDAO.getBoughtTickets(stationFrom, stationTo, tripId);
        Trip trip = tripDAO.getTrip(tripId);
        Map<String, Integer> stations =  new HashMap<String, Integer>();
        List<RouteEntry> entries = routeEntryDAO.getStations(routeId);
        for (RouteEntry r : entries) {
            stations.put(r.getStation().getName(), r.getSeqNumber());
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i < trip.getTrain().getSeatCount(); i++) {
            boolean isReserved = false;
            for (Ticket t : tickets) {
                if (t.getSeat() == i && stations.get(stationFrom) < stations.get(t.getStationTo().getName())
                        || stations.get(stationTo) < stations.get(t.getStationFrom())) {
                    isReserved = true;
                    break;
                }
            }
            if (!isReserved) {
                result.add(i);
            }
        }
        return result;
    }

}
