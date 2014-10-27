package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.*;

import javax.persistence.EntityManager;

/**
 * Represents a factory for DAO entities. Creates entity manager and contains methods
 * for getting instances of DAO instances. The EntityManager object sends into constructor of
 * each entity class.
 * @author Daria Nikiforova
 */
public class FactoryDAOImpl implements FactoryDAO {

    private EntityManager em;

    public FactoryDAOImpl(EntityManager entityManager) {
        em = entityManager;
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl(em);
    }

    @Override
    public TrainDAO getTrainDAO() {
        return new TrainDAOImpl(em);
    }

    @Override
    public TicketDAO getTicketDAO() {
        return new TicketDAOImpl(em);
    }

    @Override
    public StationDAO getStationDAO() {
        return new StationDAOImpl(em);
    }

    @Override
    public RouteEntryDAO getRouteEntryDAO() {
        return new RouteEntryDAOImpl(em);
    }

    @Override
    public TripDAO getTripDAO() {
        return new TripDAOImpl(em);
    }

    @Override
    public RouteDAO getRouteDAO() {
        return new RouteDAOImpl(em);
    }

    @Override
    public RateDAO getRateDAO() {
        return new RateDAOImpl(em);
    }

    @Override
    public ServiceDAO getServiceDAO() {
        return new ServiceDAOImpl(em);
    }


}
