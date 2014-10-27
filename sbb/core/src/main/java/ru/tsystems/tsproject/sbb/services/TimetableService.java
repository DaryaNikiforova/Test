package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.RouteEntryDAO;
import ru.tsystems.tsproject.sbb.database.dao.TripDAO;
import ru.tsystems.tsproject.sbb.database.entity.RouteEntry;
import ru.tsystems.tsproject.sbb.database.entity.Trip;
import ru.tsystems.tsproject.sbb.services.exceptions.ServiceException;
import ru.tsystems.tsproject.sbb.services.helpers.RouteHelper;
import ru.tsystems.tsproject.sbb.services.helpers.TimeHelper;
import ru.tsystems.tsproject.sbb.transferObjects.TimetableTO;
import ru.tsystems.tsproject.sbb.transferObjects.TripTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implements methods interacting with DAO methods. Needs to get and change data in database.
 * @author Daria Nikiforova
 */
public class TimetableService {
    private static final Logger LOGGER = Logger.getLogger(TimetableService.class);

    EntityManager entityManager;
    RouteEntryDAO routeEntryDAO;
    TripDAO tripDAO;
    FactoryDAO factoryDAO;

    public TimetableService(RouteEntryDAO routeEntryDAO, TripDAO tripDAO, FactoryDAO factoryDAO) {
        this.routeEntryDAO = routeEntryDAO;
        this.tripDAO = tripDAO;
        this.factoryDAO = factoryDAO;
    }

    public TimetableService(EntityManager entityManager, FactoryDAO factoryDAO) {
        this.entityManager = entityManager;
        this.factoryDAO = factoryDAO;
        routeEntryDAO = factoryDAO.getRouteEntryDAO();
        tripDAO = factoryDAO.getTripDAO();
    }
    /**
     * Returns timetable records routes contains specified dates in specified time intervals.
     * Throws exception if database connection is lost, bad request or error with transaction.
     * @param stationFrom begin of route.
     * @param stationTo end of route.
     * @param timeFrom interval begin.
     * @param timeTo interval end.
     * @return list of timetable records.
     * @throws ServiceException
     */
    public List<TimetableTO> searchTrains(String stationFrom, String stationTo, Date timeFrom, Date timeTo) throws ServiceException {
        List<TimetableTO> results = new ArrayList<TimetableTO>();
        List<RouteEntry> list = new ArrayList<RouteEntry>();
        try {
            list = routeEntryDAO.getRouteByStations(stationFrom, stationTo);
        }
        catch (PersistenceException ex) {
            LOGGER.error("Невозможно получить информацию о маршрутах");
            throw new ServiceException("Невозможно получить информацию о маршрутах");
        }
        TicketService ticketService = new TicketService(entityManager, factoryDAO);

            for (int i = 0; i < list.size(); i++) {
                int hour = list.get(i).getHour();
                int minute = list.get(i).getMinute();
                Date depTimeFrom = TimeHelper.getHourBack(timeFrom, hour, minute);
                Date depTimeTo = TimeHelper.getHourBack(timeTo, hour, minute);
                try {
                    List<Trip> trips = tripDAO.getTripsByInterval(list.get(i).getRoute().getId(), depTimeFrom, depTimeTo);
                    if (trips != null) {
                        for (Trip t : trips) {
                            TimetableTO result = new TimetableTO();
                            RouteEntry arrivalEntry = routeEntryDAO.getEntry(stationTo, list.get(i).getRoute().getId());
                            Date depDate = TimeHelper.addHours(t.getDepartureTime(), hour, minute);
                            Date arriveDate = TimeHelper.addHours(t.getDepartureTime(), arrivalEntry.getHour(), arrivalEntry.getMinute());
                            result.setTripId(t.getId());
                            result.setRouteName(RouteHelper.getRouteName(t.getRoute()));
                            result.setTrainName(t.getTrain().getName());
                            result.setDepDate(depDate);
                            result.setStationFrom(stationFrom);
                            result.setArriveDate(arriveDate);
                            result.setStationTo(stationTo);
                            result.setTime(RouteHelper.getRouteTime(t.getRoute(), stationFrom, stationTo));
                            result.setDistance(RouteHelper.getRouteDistance(t.getRoute()));
                            result.setTrainNumber(t.getTrain().getId());
                            result.setSeatCount(ticketService.getAvaliableSeats(stationFrom, stationTo, t.getId(), t.getRoute().getId()).size());
                            results.add(result);
                        }
                    }
                } catch (PersistenceException ex) {
                    LOGGER.error("Невозможно получить информацию о расписании");
                    throw new ServiceException("Невозможно получить информацию о расписании");
                }
            }
        return results;
    }

    /**
     * Returns timetable list for routes that includes specified station. Throws exception if database connection is lost,
     * bad request or error with transaction.
     * @param name station name.
     * @param depDate date of departure.
     * @return timetable list.
     * @throws ServiceException
     */
    public List<TimetableTO> getRoutesByStation(String name, Date depDate) throws ServiceException {
        List<RouteEntry> list = new ArrayList<RouteEntry>();
        try {
            list = routeEntryDAO.findRoutesByStation(name);
        } catch (PersistenceException ex) {
            LOGGER.error("Невозможно получить информацию о маршрутах");
            throw new ServiceException("Невозможно получить информацию о маршрутах");
        }
        List<TimetableTO> results = new ArrayList<TimetableTO>();
        for (int i=0; i<list.size(); i++) {
            int hour = list.get(i).getHour();
            int minute = list.get(i).getMinute();
            Date depTime = TimeHelper.getHourBack(depDate, hour, minute);
            try {
                List<Trip> trips = tripDAO.getTripsByRoute(list.get(i).getRoute().getId(), depTime);
                if (trips != null) {
                    for (int k = 0; k < trips.size(); k++) {
                        RouteEntry re = routeEntryDAO.getLastRouteEntry(trips.get(k).getRoute().getId());
                        if (!name.equals(re.getStation().getName())) {
                            TimetableTO result = new TimetableTO();
                            result.setRouteName(RouteHelper.getRouteName(trips.get(k).getRoute()));
                            result.setTrainName(trips.get(k).getTrain().getName());
                            result.setDepDate(TimeHelper.addHours(trips.get(k).getDepartureTime(), hour, minute));
                            result.setStationFrom(name);
                            result.setArriveDate(TimeHelper.addHours(trips.get(k).getDepartureTime(), re.getHour(), re.getMinute()));
                            result.setStationTo(re.getStation().getName());
                            result.setTrainNumber(trips.get(k).getTrain().getId());
                            results.add(result);
                        }
                    }
                }
            } catch (PersistenceException ex) {
                LOGGER.error("Невозможно получить информацию о расписании");
                throw new ServiceException("Невозможно получить информацию о расписании");
            }

        }
        return results;
    }

    /**
     * Returns trips list for routes that contains in database. Throws exception if database connection is lost,
     * bad request or error with transaction.
     * @return list of trips
     * @throws ServiceException
     */
    public List<TripTO> getAllTrips() throws ServiceException {
        List<Trip> trips = new ArrayList<Trip>();
        try {
            trips = tripDAO.getAllTrips();
        } catch (PersistenceException ex) {
            LOGGER.error("Невозможно получить информацию о рейсах");
            throw new ServiceException("Невозможно получить информацию о рейсах");
        }
        List<TripTO> result = new ArrayList<TripTO>();
        for (Trip t : trips) {
            TripTO trip = new TripTO();
            trip.setArrival(RouteHelper.getArrivalDate(t.getRoute(), t.getDepartureTime()));
            trip.setDeparture(t.getDepartureTime());
            trip.setNumber(t.getTrain().getId());
            trip.setSeatCount(t.getTrain().getSeatCount());
            String trainName = t.getTrain().getName();
            if (trainName != null) {
                trip.setRoute(RouteHelper.getRouteName(t.getRoute()) + " &laquo;" + trainName + "&raquo;");
            } else {
                trip.setRoute(RouteHelper.getRouteName(t.getRoute()));
            }
            trip.setId(t.getId());
            result.add(trip);
        }
        return result;
    }
}
