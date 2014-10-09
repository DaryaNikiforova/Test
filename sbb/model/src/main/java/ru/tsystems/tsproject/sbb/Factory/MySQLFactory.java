package ru.tsystems.tsproject.sbb.Factory;

import ru.tsystems.tsproject.sbb.DAO.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by apple on 04.10.14.
 */
public class MySQLFactory implements DAOFactory {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
    private static EntityManager em = emf.createEntityManager();

    public MySQLFactory() {
        emf = Persistence.createEntityManagerFactory("manager1");
        em = emf.createEntityManager();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO(em);
    }

    @Override
    public TrainDAO getTrainDAO() {
        return new MySQLTrainDAO(em);
    }

    @Override
    public TicketDAO getTicketDAO() {
        return new MySQLTicketDAO(em);
    }

    @Override
    public TimetableDAO getTimetableDAO() {
        return new MySQLTimetableDAO(em);
    }

    @Override
    public StationDAO getStationDAO() {
        return new MySQLStationDAO(em);
    }

}
