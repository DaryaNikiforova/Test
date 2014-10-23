package ru.tsystems.tsproject.sbb.services;

import ru.tsystems.tsproject.sbb.database.dao.RouteDAO;
import ru.tsystems.tsproject.sbb.database.dao.RouteEntryDAO;
import ru.tsystems.tsproject.sbb.database.dao.StationDAO;
import ru.tsystems.tsproject.sbb.database.dao.impl.FactoryDAOImpl;
import ru.tsystems.tsproject.sbb.database.entity.Route;
import ru.tsystems.tsproject.sbb.database.entity.RouteEntry;
import ru.tsystems.tsproject.sbb.services.helpers.RouteHelper;
import ru.tsystems.tsproject.sbb.transferObjects.RouteEntryTO;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 23.10.2014.
 */
public class RouteService {
    FactoryDAOImpl factory = new FactoryDAOImpl();
    RouteDAO routeDAO = factory.getRouteDAO();
    StationDAO stationDAO = factory.getStationDAO();

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

}
