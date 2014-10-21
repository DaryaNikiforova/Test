package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.*;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.*;
import ru.tsystems.tsproject.sbb.services.helpers.RouteHelper;
import ru.tsystems.tsproject.sbb.services.helpers.TimeHelper;
import ru.tsystems.tsproject.sbb.transferObjects.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 15.10.14.
 */
public class TimetableService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    RouteEntryDAO routeEntryDAO = factory.getRouteEntryDAO();
    TripDAO tripDAO = factory.getTripDAO();
    StationDAO stationDAO = factory.getStationDAO();
    TrainDAO trainDAO = factory.getTrainDAO();
    RouteDAO routeDAO = factory.getRouteDAO();

    public List<TimetableTO> searchTrains(String stationFrom, String stationTo, Date timeFrom, Date timeTo) {
        List<TimetableTO> results = new ArrayList<TimetableTO>();
        List<RouteEntry> list = routeEntryDAO.getRouteByStations(stationFrom, stationTo);
        TicketService ticketService = new TicketService();

        for (int i = 0; i < list.size(); i++) {
            int hour = list.get(i).getHour();
            int minute = list.get(i).getMinute();
            Date depTimeFrom = TimeHelper.getHourBack(timeFrom, hour, minute);
            Date depTimeTo = TimeHelper.getHourBack(timeTo, hour, minute);
            List<Trip> trips = tripDAO.getTripsByInterval(list.get(i).getRoute().getId(), depTimeFrom, depTimeTo);
            if (trips != null) {
                for (int k = 0; k < trips.size(); k++) {
                    TimetableTO result = new TimetableTO();
                    RouteEntry arrivalEntry = routeEntryDAO.getEntry(stationTo, list.get(i).getRoute().getId());
                    Date depDate = TimeHelper.addHours(trips.get(k).getDepartureTime(), hour, minute);
                    Date arriveDate = TimeHelper.addHours(trips.get(k).getDepartureTime(), arrivalEntry.getHour(), arrivalEntry.getMinute());
                    result.setRouteName(RouteHelper.getRouteName(trips.get(k).getRoute()));
                    result.setTrainName(trips.get(k).getTrain().getName());
                    result.setDepDate(depDate.toString());
                    result.setStationFrom(stationFrom);
                    result.setArriveDate(arriveDate.toString());
                    result.setStationTo(stationTo);
                    result.setTrainNumber(trips.get(k).getTrain().getId());
                    result.setSeatCount(ticketService.getAvaliableSeats(stationFrom, stationTo, trips.get(k).getId(), trips.get(k).getRoute().getId()).size());
                    results.add(result);
                }
            }
        }
        return results;
    }


    public List<TimetableTO> getRoutesByStation(String name) {
        List<RouteEntry> list = routeEntryDAO.findRoutesByStation(name);
        List<TimetableTO> results = new ArrayList<TimetableTO>();
        for (int i=0; i<list.size(); i++) {
            int hour = list.get(i).getHour();
            int minute = list.get(i).getMinute();
            List<Trip> trips = tripDAO.getTripsByRoute(list.get(i).getRoute().getId());
            if (trips!=null) {
                for (int k=0; k<trips.size(); k++) {
                    RouteEntry re = routeEntryDAO.getLastRouteEntry(list.get(i).getRoute().getId());
                    TimetableTO result = new TimetableTO();
                    //result.setDeparture(name);
                    //result.setArrival();
                    //result.setDepTime(TimeHelper.addHours(trips.get(k).getDepartureTime(), hour, minute));
                    //result.setTripId(trips.get(i).getId());
                    //result.setTrainNumber(trips.get(i).getTrain().getId());
                    //result.setArriveTime(TimeHelper.addHours(trips.get(k).getDepartureTime(), re.getHour(), re.getMinute()));
                    //Date depDate = TimeHelper.addHours(trips.get(k).getDepartureTime(), hour, minute);
                    //Date arriveDate = TimeHelper.addHours(trips.get(k).getDepartureTime(), arrivalEntry.getHour(), arrivalEntry.getMinute());
                    result.setRouteName(RouteHelper.getRouteName(trips.get(k).getRoute()));
                    result.setTrainName(trips.get(k).getTrain().getName());
                    result.setDepDate(TimeHelper.addHours(trips.get(k).getDepartureTime(), hour, minute).toString());
                    result.setStationFrom(name);
                    result.setArriveDate(TimeHelper.addHours(trips.get(k).getDepartureTime(), re.getHour(), re.getMinute()).toString());
                    result.setStationTo(re.getStation().getName());
                    result.setTrainNumber(trips.get(k).getTrain().getId());
                    results.add(result);
                }
            }

        }
        return results;
    }

    public void addStation(String station) {
        stationDAO.addStation(station);
    }

    public List<String> getStations() {
        return stationDAO.getStations();
    }

    public List<TrainTO> getTrains() {
        List<Train> list = trainDAO.getTrains();
        List<TrainTO> result = new ArrayList<TrainTO>();
        for (Train t:list) {
            TrainTO train = new TrainTO();
            train.setNumber(t.getId());
            train.setName(t.getName());
            train.setSeatCount(t.getSeatCount());
            train.setRateName(t.getRate().getName());
            train.setRateId(t.getRate().getId());
            result.add(train);
        }
        return result;
    }

    public void addTrain(TrainTO train) {
        trainDAO.addTrain(train.getNumber(), train.getSeatCount(), train.getName(), new Rate(train.getRateId()));
    }

    public List<RouteTO> getAllRoutes() {
        List<Route> routes = new ArrayList<Route>();
        List<RouteTO> results = new ArrayList<RouteTO>();
        for (Route r:routes) {
            RouteTO route = new RouteTO();
            route.setNumber(r.getId());
            route.setDistance(RouteHelper.getRouteDistance(r));
            route.setRoute(RouteHelper.getRouteName(r));
            route.setTime(RouteHelper.getRouteTime(r));
            results.add(route);
        }
        return results;
    }

    public void addRoute(RouteTO routeTO) {
        List<RouteEntryTO> routeEntries = routeTO.getRouteEntries();
        List<RouteEntry> result = new ArrayList<RouteEntry>();
        Route route = new Route();
        for (RouteEntryTO r : routeEntries) {
            RouteEntry routeEntry = new RouteEntry();
            routeEntry.setDistance(r.getDistance());
            routeEntry.setMinute(r.getMinute());
            routeEntry.setHour(r.getHour());
            routeEntry.setSeqNumber(r.getSeqNumber());
            routeEntry.setStation(stationDAO.getStation(r.getStationName()));
            routeEntry.setRoute(route);
            result.add(routeEntry);
        }
        route.setRouteEntries(result);
        routeDAO.addRoute(route);
    }

    public List<TripTO> getAllTrips() {
        List<Trip> trips = tripDAO.getAllTrips();
        List<TripTO> result = new ArrayList<TripTO>();
        for (Trip t : trips) {
            TripTO trip = new TripTO();
            trip.setArrival(RouteHelper.getArrivalDate(t.getRoute(), t.getDepartureTime()));
            trip.setDeparture(t.getDepartureTime());
            trip.setNumber(t.getTrain().getId());
            trip.setSeatCount(t.getTrain().getSeatCount());
            trip.setRoute(RouteHelper.getRouteName(t.getRoute()) + "\n" + t.getTrain().getName());
            result.add(trip);
        }
        return result;
    }
}
