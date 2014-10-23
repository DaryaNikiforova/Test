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
                    result.setTripId(trips.get(k).getId());
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


    public List<TimetableTO> getRoutesByStation(String name, Date depDate) {
        List<RouteEntry> list = routeEntryDAO.findRoutesByStation(name);
        List<TimetableTO> results = new ArrayList<TimetableTO>();
        for (int i=0; i<list.size(); i++) {
            int hour = list.get(i).getHour();
            int minute = list.get(i).getMinute();
            Date depTime = TimeHelper.getHourBack(depDate, hour, minute);
            //Date depTimeTo = TimeHelper.getHourBack(timeTo, hour, minute);
            List<Trip> trips = tripDAO.getTripsByRoute(list.get(i).getRoute().getId(), depTime);
            if (trips!=null) {
                for (int k=0; k<trips.size(); k++) {
                    RouteEntry re = routeEntryDAO.getLastRouteEntry(trips.get(k).getRoute().getId());
                    if (!name.equals(re.getStation().getName())) {
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

        }
        return results;
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
            trip.setRoute(RouteHelper.getRouteName(t.getRoute()) + " " + t.getTrain().getName());
            result.add(trip);
        }
        return result;
    }
}
