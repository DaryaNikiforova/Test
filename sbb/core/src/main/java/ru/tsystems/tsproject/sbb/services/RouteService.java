package ru.tsystems.tsproject.sbb.services;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.database.dao.FactoryDAO;
import ru.tsystems.tsproject.sbb.database.dao.RouteDAO;
import ru.tsystems.tsproject.sbb.database.dao.StationDAO;
import ru.tsystems.tsproject.sbb.database.entity.Route;
import ru.tsystems.tsproject.sbb.database.entity.RouteEntry;
import ru.tsystems.tsproject.sbb.database.entity.Station;
import ru.tsystems.tsproject.sbb.services.exceptions.StationNotFoundException;
import ru.tsystems.tsproject.sbb.services.helpers.RouteHelper;
import ru.tsystems.tsproject.sbb.transferObjects.RouteEntryTO;
import ru.tsystems.tsproject.sbb.transferObjects.RouteTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements methods interacting with DAO methods. Needs to get and change data in database.
 * @author Daria Nikiforova
 */
public class RouteService {
    /**
     * Creating factory to get needed DAO instance.
     */
    private static final Logger LOGGER = Logger.getLogger(Station.class);

    EntityManager entityManager;
    RouteDAO routeDAO;
    StationDAO stationDAO;

    public RouteService(RouteDAO routeDAO, StationDAO stationDAO, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.routeDAO = routeDAO;
        this.stationDAO = stationDAO;
    }

    public RouteService(EntityManager entityManager, FactoryDAO factoryDAO) {
        this.entityManager = entityManager;
        routeDAO = factoryDAO.getRouteDAO();
        stationDAO = factoryDAO.getStationDAO();
    }
    /**
     * Returns all trains routes from database.
     * @return list of trains routes.
     */
    public List<RouteTO> getAllRoutes() {
        List<Route> routes = routeDAO.getRoutes();
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


    /**
     * Adds route information to database. Throws exception if database connection is lost,
     * bad request or error with transaction.
     * @param routeTO route that need to add.
     * @throws StationNotFoundException
     */
    public void addRoute(RouteTO routeTO) throws StationNotFoundException {
        try {
            entityManager.getTransaction().begin();
            Route route = new Route();
            route.setRouteEntries(mapRouteEntries(routeTO.getRouteEntries(), route));
            routeDAO.addRoute(route);
            entityManager.getTransaction().commit();
            LOGGER.info("Добавление в таблицу маршрута");
        } finally {
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
    }

    List<RouteEntry> mapRouteEntries(List<RouteEntryTO> routeEntryTOs, Route route) throws StationNotFoundException {
        List<RouteEntry> routeEntries = new ArrayList<RouteEntry>();
        for (RouteEntryTO r : routeEntryTOs) {
            RouteEntry routeEntry = new RouteEntry();
            routeEntry.setDistance(r.getDistance());
            routeEntry.setMinute(r.getMinute());
            routeEntry.setHour(r.getHour());
            routeEntry.setSeqNumber(r.getSeqNumber());
            try {
                routeEntry.setStation(stationDAO.getStation(r.getStationName()));
            } catch (PersistenceException ex) {
                LOGGER.error("Ошибка при получении информации о станции");
                throw new StationNotFoundException("Ошибка при получении информации о станции");
            }
            routeEntry.setRoute(route);
            routeEntries.add(routeEntry);
        }
        return routeEntries;
    }

}
