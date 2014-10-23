package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.*;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.*;
import ru.tsystems.tsproject.sbb.services.helpers.TicketHelper;
import ru.tsystems.tsproject.sbb.services.helpers.TimeHelper;
import ru.tsystems.tsproject.sbb.transferObjects.TicketTO;

import javax.persistence.PersistenceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    ServiceDAO serviceDAO = factory.getServiceDAO();
    RateDAO rateDAO = factory.getRateDAO();

    private static final Logger LOGGER = Logger.getLogger(TicketService.class);

    public void AddTicket(TicketTO ticket) {

        Date purchaseDate = new Date();
        double minutes = (ticket.getDeparture().getTime()-purchaseDate.getTime())/(60*1000);
        try {
            if (minutes >= 10 && ticketDAO.findUser(ticket.getUserName(), ticket.getUserSurname(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ticket.getBirthDate()), ticket.getTripId()) == 0
                && ticket.getSeatNumber() > 0) {
                Ticket t = new Ticket();
                t.setPrice(ticket.getPrice());
                t.setSeat(ticket.getSeatNumber());
                Station s = new Station();
                s.setName(ticket.getStationFrom());
                s.setId(stationDAO.getStation(ticket.getStationFrom()).getId());
                t.setStationFrom(s);
                s.setName(ticket.getStationTo());
                s.setId(stationDAO.getStation(ticket.getStationTo()).getId());
                t.setStationTo(s);
                t.setUser(userDAO.getUser(ticket.getLogin()));
                t.setTrip(tripDAO.getTrip(ticket.getTripId()));
                t.setDate(purchaseDate);
                t.setRate(new Rate(ticket.getRateType()));
                if (ticket.getServices() != null) {
                    for (Long k : ticket.getServices().keySet()) {
                        Service service = serviceDAO.getServiceById(k.intValue());
                        t.getServices().add(service);
                    }
                }
                try {
                    ticketDAO.addTicket(t);
                }
                catch (PersistenceException ex) {

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TicketTO generateTicket(int tripId, String stationFrom, String stationTo, String login) {
        TicketTO ticket = new TicketTO();
        Trip trip = tripDAO.getTrip(tripId);
        ticket.setStationFrom(stationFrom);
        ticket.setStationTo(stationTo);
        ticket.setRoute(stationFrom + " &#8594; " + stationTo);
        ticket.setTrip(TicketHelper.getTrainInfo(trip));
        ticket.setTripId(tripId);
        RouteEntry reFrom = routeEntryDAO.getEntry(stationFrom, trip.getRoute().getId());
        RouteEntry reTo = routeEntryDAO.getEntry(stationTo, trip.getRoute().getId());
        Date depDate = TimeHelper.addHours(trip.getDepartureTime(), reFrom.getHour(), reFrom.getMinute());
        Date arrDate = TimeHelper.addHours(trip.getDepartureTime(), reTo.getHour(), reTo.getMinute());
        ticket.setDeparture(depDate);
        ticket.setArrival(arrDate);
        User user = userDAO.getUser(login);
        ticket.setUserName(user.getName());
        ticket.setLogin(login);
        ticket.setUserSurname(user.getSurname());
        ticket.setBirthDate(user.getBirthDate().toString());
        ticket.setSeats(getAvaliableSeats(stationFrom, stationTo, tripId, trip.getRoute().getId()));
        ticket.setTrainRate(trip.getTrain().getRate().getId());
        ticket.setRouteId(trip.getRoute().getId());
        Map<Long,String> services = new HashMap<Long, String>();
        List<Service> serviceList = serviceDAO.getServices();
        for (Service s : serviceList) {
            services.put(new Long(s.getId()), s.getName());
        }
        ticket.setServices(services);
        Map<Long,String> rates = new HashMap<Long, String>();
        List<Rate> rateList = rateDAO.getRates();
        for (Rate r : rateList) {
            rates.put(new Long(r.getId()), r.getName());
        }
        ticket.setRateTypes(rates);
        return ticket;
    }

    public double calcPrice(TicketTO ticket) {
        double price = 0;
        try {
            RouteEntry reFrom = routeEntryDAO.getEntry(ticket.getStationFrom(), ticket.getRouteId());
            RouteEntry reTo = routeEntryDAO.getEntry(ticket.getStationTo(), ticket.getRouteId());
            double distance = reTo.getDistance() - reFrom.getDistance();

            if (ticket.getServices().size() > 0) {
                Map<Long, String> services = ticket.getServices();
                for (Long k : services.keySet())
                    price += serviceDAO.getValueById(k.intValue());
            }
            price += distance * rateDAO.getValueById(ticket.getRateType()) * rateDAO.getValueById(ticket.getTrainRate());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Сбой при работе с базой данных", ex);
        }
        return price;
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
        for (int i = 1; i <= trip.getTrain().getSeatCount(); i++) {
            boolean isReserved = false;
            for (Ticket t : tickets) {
                if (t.getSeat() == i && stations.get(stationFrom) < stations.get(t.getStationTo().getName())
                        || stations.get(stationTo) < stations.get(t.getStationFrom().getName())) {
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
