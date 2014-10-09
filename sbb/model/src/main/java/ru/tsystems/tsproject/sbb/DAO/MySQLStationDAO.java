package ru.tsystems.tsproject.sbb.DAO;

import ru.tsystems.tsproject.sbb.Entity.Station;

import javax.persistence.EntityManager;

/**
 * Created by apple on 04.10.14.
 */
public final class MySQLStationDAO implements StationDAO {
    private EntityManager entityManager;

    public MySQLStationDAO(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addStation(String name) {
        entityManager.getTransaction().begin();
        Station ticket = new Station(name);
        entityManager.persist(ticket);
        entityManager.getTransaction().commit();
    }

}
