package ru.tsystems.tsproject.sbb.database.dao.impl;

import ru.tsystems.tsproject.sbb.database.entity.Station;
import ru.tsystems.tsproject.sbb.database.dao.StationDAO;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by apple on 04.10.14.
 */
public final class StationDAOImpl implements StationDAO {
    private EntityManager entityManager;

    public StationDAOImpl(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addStation(Station station) {
        entityManager.persist(station);
    }

    @Override
    public Station getStation(String name) {
        return (Station) entityManager.createQuery("select s from Station s where s.name = :name")
                            .setParameter("name", name).getSingleResult();
    }

    @Override
    public boolean isStationExist(String name) {
        return !entityManager.createQuery("select s from Station s where s.name = :name")
                .setParameter("name", name)
                .getResultList()
                .isEmpty();
    }

    @Override
    public List<String> getStations() {
        return entityManager.createQuery("select s.name from Station s").getResultList();
    }

}
