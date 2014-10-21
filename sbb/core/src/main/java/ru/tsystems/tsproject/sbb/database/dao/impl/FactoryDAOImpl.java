package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by apple on 04.10.14.
 */
public class FactoryDAOImpl implements FactoryDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
    private static EntityManager em = emf.createEntityManager();

    public FactoryDAOImpl() {
        emf = Persistence.createEntityManagerFactory("manager1");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager()
    {
        return em;
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

    //@Override
    //public TimetableDAO getTimetableDAO() {
      //  return new TimetableDAOImpl(em);
    //}

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

}
